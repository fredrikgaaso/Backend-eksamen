package PGR209.Eksam.Repo;

import PGR209.Eksam.Model.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartsRepo extends JpaRepository<Parts, Long> {
}
