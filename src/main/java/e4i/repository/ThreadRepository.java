package e4i.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import e4i.domain.Thread;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Thread entity.
 */
@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    @Query(value = "select distinct thread from Thread thread left join fetch thread.advertisements",
        countQuery = "select count(distinct thread) from Thread thread")
    Page<Thread> findAllWithEagerRelationships(Pageable pageable);
    
    @Query(value = "select distinct thread from Thread thread "
    		+ "where thread.companySender.id = :companyId "
    		+ "or thread.companyReceiver.id = :companyId",
            countQuery = "select count(distinct thread) from Thread thread")
        Page<Thread> findAllByCompany(@Param("companyId") Long companyId, Pageable pageable);
    
    @Query(value = "select distinct thread from Thread thread where thread.companySender.id = :companyId",
            countQuery = "select count(distinct thread) from Thread thread")
        Page<Thread> findAllByCompanySender(@Param("companyId") Long companyId, Pageable pageable);
    
    @Query(value = "select distinct thread from Thread thread where thread.companyReceiver.id = :companyId",
            countQuery = "select count(distinct thread) from Thread thread")
        Page<Thread> findAllByCompanyReceiver(@Param("companyId") Long companyId, Pageable pageable);

    @Query("select distinct thread from Thread thread left join fetch thread.advertisements")
    List<Thread> findAllWithEagerRelationships();

    @Query("select thread from Thread thread left join fetch thread.advertisements where thread.id =:id")
    Optional<Thread> findOneWithEagerRelationships(@Param("id") Long id);
}
