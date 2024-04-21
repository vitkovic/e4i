package e4i.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import e4i.domain.Collaboration;

/**
 * Spring Data  repository for the Collaboration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollaborationRepository extends JpaRepository<Collaboration, Long> {
}
