package e4i.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import e4i.domain.Message;

/**
 * Spring Data  repository for the Message entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findAllByThreadIdOrderByDatetimeDesc(Long threadId);
	
	List<Message> findAllByThreadIdOrderByDatetimeAsc(Long threadId);
	
	List<Message> findAllByThreadIdAndIsDeletedSenderOrderByDatetimeDesc(Long threadId, Boolean isDeletedSender);
	
	List<Message> findAllByThreadIdAndIsDeletedSenderOrderByDatetimeAsc(Long threadId, Boolean isDeletedSender);
	
	List<Message> findAllByThreadIdAndIsDeletedReceiverOrderByDatetimeDesc(Long threadId, Boolean isDeletedReceiver);
	
	List<Message> findAllByThreadIdAndIsDeletedReceiverOrderByDatetimeAsc(Long threadId, Boolean isDeletedReceiver);
	

	Long countByThreadId(Long threadId);
	
	Long countByThreadIdAndIsDeletedSender(Long threadId, Boolean isDeletedSender);
	
	Long countByThreadIdAndIsDeletedReceiver(Long threadId, Boolean isDeletedReceiver);
	
	
	
	Optional<Message> findFirstByThreadIdOrderByDatetimeDesc(Long threadId);
	
	Optional<Message> findFirstByThreadIdAndIsDeletedSenderOrderByDatetimeDesc(Long threadId, Boolean isDeletedSender);
	
	Optional<Message> findFirstByThreadIdAndIsDeletedReceiverOrderByDatetimeDesc(Long threadId, Boolean isDeletedReceiver);
}
