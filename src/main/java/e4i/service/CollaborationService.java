package e4i.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import e4i.domain.Advertisement;
import e4i.domain.Collaboration;
import e4i.domain.PortalUser;
import e4i.repository.CollaborationRepository;
import e4i.repository.PortalUserRepository;

import java.time.Instant;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Collaboration}.
 */
@Service
@Transactional
public class CollaborationService {

    private final Logger log = LoggerFactory.getLogger(CollaborationService.class);

    private final CollaborationRepository collaborationRepository;
    
    @Autowired
    AdvertisementService advertisementService;
    
    @Autowired
    PortalUserRepository portalUserRepository;
    
    @Autowired
    PortalUserService portalUserService;

    public CollaborationService(CollaborationRepository collaborationRepository) {
        this.collaborationRepository = collaborationRepository;
    }

    /**
     * Save a collaboration.
     *
     * @param collaboration the entity to save.
     * @return the persisted entity.
     */
    public Collaboration save(Collaboration collaboration) {
        log.debug("Request to save Collaboration : {}", collaboration);
        return collaborationRepository.save(collaboration);
    }

    /**
     * Get all the collaborations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Collaboration> findAll(Pageable pageable) {
        log.debug("Request to get all Collaborations");
        return collaborationRepository.findAll(pageable);
    }


    /**
     * Get one collaboration by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Collaboration> findOne(Long id) {
        log.debug("Request to get Collaboration : {}", id);
        return collaborationRepository.findById(id);
    }

    /**
     * Delete the collaboration by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Collaboration : {}", id);
        collaborationRepository.deleteById(id);
    }
    
    @Transactional
    public Collaboration createCollaborationForAdvertisementAndPortalUserCompany(Advertisement advertisement, PortalUser portalUser) {
             
        Collaboration collaboration = new Collaboration();
        collaboration.setAdvertisement(advertisement);
        collaboration.setCompanyOffer(advertisement.getCompany());
        collaboration.setCompanyRequest(portalUser.getCompany());
        collaboration.setDatetime(Instant.now());
        collaboration.setIsAccepted(false);
        
        Collaboration result = this.save(collaboration);
        
        return result; 
    };
}
