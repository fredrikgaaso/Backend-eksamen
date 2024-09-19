package PGR209.Eksam.Repo;

import PGR209.Eksam.Model.Subassembly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubassemblyRepo extends JpaRepository<Subassembly, Long> {
}
