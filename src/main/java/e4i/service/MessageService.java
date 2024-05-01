package e4i.service;

import e4i.domain.Advertisement;
import e4i.domain.Collaboration;
import e4i.domain.Company;
import e4i.domain.Message;
import e4i.domain.PortalUser;
import e4i.domain.Thread;
import e4i.repository.AdvertisementRepository;
import e4i.repository.CompanyRepository;
import e4i.repository.MessageRepository;
import e4i.repository.PortalUserRepository;
import e4i.repository.ThreadRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Message}.
 */
@Service
@Transactional
public class MessageService {

    private final Logger log = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;
    
    @Autowired
    PortalUserRepository portalUserRepository;
    
    @Autowired
    ThreadRepository threadRepository;
    
    @Autowired
    CompanyRepository companyRepository;
    
    @Autowired
    AdvertisementRepository advertisementRepository;
    
    @Autowired
    MailService mailService;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Save a message.
     *
     * @param message the entity to save.
     * @return the persisted entity.
     */
    public Message save(Message message) {
        log.debug("Request to save Message : {}", message);
        return messageRepository.save(message);
    }

    /**
     * Get all the messages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Message> findAll(Pageable pageable) {
        log.debug("Request to get all Messages");
        return messageRepository.findAll(pageable);
    }


    /**
     * Get one message by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Message> findOne(Long id) {
        log.debug("Request to get Message : {}", id);
        return messageRepository.findById(id);
    }

    /**
     * Delete the message by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Message : {}", id);
        messageRepository.deleteById(id);
    }
    
    @Transactional
    public void sendNotificationMail(Message message) {
    	
    	// Videti kako ovo bolje uraditi!!!
    	Thread thread = threadRepository.getOne(message.getThread().getId());
        PortalUser portalUserSender = portalUserRepository.getOne(message.getPortalUserSender().getId());
        Company portalUserSenderCompany = companyRepository.getOne(portalUserSender.getCompany().getId());
        Company companySender = companyRepository.getOne(thread.getCompanySender().getId()); 
        Company companyReceiver = companyRepository.getOne(thread.getCompanyReceiver().getId());
    	
        Company company = companySender;
        if (portalUserSenderCompany.getId() == companySender.getId()) {
        	company = companyReceiver;
        }
        
        List<PortalUser> companyPortalUsers = portalUserRepository.findAllByCompanyAndDoNotify(company, true);
		 
        String messageNotificationContent = prepareMessageNotificationContent(message, thread, company);
        
		mailService.sendMessageNotificationMail(messageNotificationContent, companyPortalUsers);
    }
    
    @Transactional
    public Message createFirstMessageInThreadCollaboration(Thread thread, Collaboration collaboration, PortalUser portalUser) {
    	
    	String content = "Kompanija '" + thread.getCompanySender().getName() + "' "
    			+ "Vam je u uputila zahtev za saradnju za oglas '" + collaboration.getAdvertisement().getTitle() + "'. "
    			+ "Saradnju možete potvrditi klikom na opciju 'Potvrdi saradnju' u zaglavlju ove poruke.";
//    			+ "Ovo je automatski generisana poruka. "
//    			+ "Odgovor na ovu poruku biće poslat kompaniji koja je uputila zahtev za saradnju.";
    	
    	Message message = new Message();
        message.setThread(thread);
        message.setPortalUserSender(portalUser);
        message.setContent(content);
        message.setDatetime(Instant.now());
        message.setIsRead(false);
        message.setIsDeletedSender(false);
        message.setIsDeletedReceiver(false);
    	
    	Message result = this.save(message);
    	
    	return result;
    }
    
    @Transactional
    public Message createConfirmMessageInThreadCollaboration(Thread thread, Collaboration collaboration, PortalUser portalUser) {
    	
    	String content = "Kompanija '" + thread.getCompanyReceiver().getName() + "' "
    			+ "je u potvrdila zahtev za saradnju za oglas '" + collaboration.getAdvertisement().getTitle() + "'. ";
    	
    	Message message = new Message();
        message.setThread(thread);
        message.setPortalUserSender(portalUser);
        message.setContent(content);
        message.setDatetime(Instant.now());
        message.setIsRead(false);
        message.setIsDeletedSender(false);
        message.setIsDeletedReceiver(false);
    	
    	Message result = this.save(message);
    	
    	return result;
    }
    
    public String prepareMessageNotificationContent(Message message, Thread thread, Company company) {
    	
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        ZonedDateTime zonedDateTime = message.getDatetime().atZone(ZoneId.systemDefault());
        
        String advertisementString = "";
        Optional<Advertisement> advertisementOptional = advertisementRepository.findOneByThreads(thread);        
        if (advertisementOptional.isPresent()) {
        	Advertisement advertisement = advertisementOptional.get();
        	advertisementString = "<p><b>Oglas: </b><span>" + advertisement.getTitle() + "</span></p>";
        }
    	
        String senderString = message.getPortalUserSender().getCompany().getName()
        		+ " - " + message.getPortalUserSender().getUser().getFirstName()
        		+ " " + message.getPortalUserSender().getUser().getLastName();
        
        String homeURL = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        String companyMessagesLink = homeURL + "/b2b/company/" + company.getId() + "/threads";
        
        String content = "<div>"
        		+ "<p>Imate novu poruku na B2B portalu za kompaniju " + company.getName() + ".</p>"
        		+ "<br>"
        		+ "<p><b>Vreme: </b><span>" + dateTimeFormatter.format(zonedDateTime) + "</span></p>"
        		+ "<p><b>Pоšiljalac: </b><span>" + senderString + "</span></p>"
        		+ advertisementString
        		+ "<p><b>Upit: </b><span>" + thread.getSubject() + "</span></p>"
        		+ "<hr>"
        		+ "<p style='white-space: pre-line;'>" + message.getContent() + "</p>"
        		+ "<hr>"
        		+ "<p>Na poruku možete odgovoriti sa "
        		+ "<a href='" + companyMessagesLink + "'>profila Vaše kompanije<a>.</p>"
        		+ "<p>Ovo je automatski poslata poruka, ne odgovarati na ovaj mail.</p>";
             
    	return content;
    }
}
