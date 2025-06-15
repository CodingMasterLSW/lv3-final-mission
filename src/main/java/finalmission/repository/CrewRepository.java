package finalmission.repository;

import finalmission.domain.Crew;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {

    Optional<Crew> findByEmail(String email);
}
