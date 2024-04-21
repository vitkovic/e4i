package e4i.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import e4i.domain.Thread;
import e4i.repository.ThreadRepository;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Thread}.
 */
@Service
@Transactional
public class ThreadService {

    private final Logger log = LoggerFactory.getLogger(ThreadService.class);

    private final ThreadRepository threadRepository;

    public ThreadService(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    /**
     * Save a thread.
     *
     * @param thread the entity to save.
     * @return the persisted entity.
     */
    public Thread save(Thread thread) {
        log.debug("Request to save Thread : {}", thread);
        return threadRepository.save(thread);
    }

    /**
     * Get all the threads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Thread> findAll(Pageable pageable) {
        log.debug("Request to get all Threads");
        return threadRepository.findAll(pageable);
    }


    /**
     * Get all the threads with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Thread> findAllWithEagerRelationships(Pageable pageable) {
        return threadRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one thread by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Thread> findOne(Long id) {
        log.debug("Request to get Thread : {}", id);
        return threadRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the thread by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Thread : {}", id);
        threadRepository.deleteById(id);
    }
}
