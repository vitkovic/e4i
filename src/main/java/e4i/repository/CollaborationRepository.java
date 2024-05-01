package e4i.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import e4i.domain.Collaboration;
import e4i.domain.Thread;

/**
 * Spring Data  repository for the Collaboration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollaborationRepository extends JpaRepository<Collaboration, Long> {
	
	@Query("SELECT collaboration FROM Collaboration collaboration " +
	        "WHERE (collaboration.companyOffer.id = :companyId " +
	        "OR (collaboration.companyRequest.id = :companyId)) ")
	Page<Collaboration> findAllByCompany(@Param("companyId") Long companyId, Pageable pageable);
    
    @Query("SELECT collaboration FROM Collaboration collaboration " +
            "WHERE (collaboration.companyOffer.id = :companyId) ")
	Page<Collaboration> findAllByCompanyOffer(@Param("companyId") Long companyId, Pageable pageable);
    
    @Query("SELECT collaboration FROM Collaboration collaboration " +
            "WHERE (collaboration.companyRequest.id = :companyId) ")
	Page<Collaboration> findAllByCompanyRequest(@Param("companyId") Long companyId, Pageable pageable);

    Optional<Collaboration> findOneByThreads(Thread thread);
}
