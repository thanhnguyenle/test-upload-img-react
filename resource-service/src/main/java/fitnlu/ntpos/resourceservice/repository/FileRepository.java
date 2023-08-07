package fitnlu.ntpos.resourceservice.repository;

import fitnlu.ntpos.resourceservice.entities.FileEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FileRepository extends JpaRepository<FileEntities, Long> {
    Optional<FileEntities> findById(final String id);
}
