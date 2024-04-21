package e4i.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import e4i.domain.Company;
import e4i.domain.Message;
import e4i.domain.PortalUser;
import e4i.domain.Thread;
import e4i.repository.CompanyRepository;
import e4i.repository.MessageRepository;
import e4i.repository.PortalUserRepository;
import e4i.repository.ThreadRepository;

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
    
    @Transactional(readOnly = true)
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
        
        List<PortalUser> companyPortalUsers = portalUserRepository.findAllByCompany(company);

//        mailService.sendMessageNotificationMail(message, thread, company, companyPortalUsers);
    }
}
