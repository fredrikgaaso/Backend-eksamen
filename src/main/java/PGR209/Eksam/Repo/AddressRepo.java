package PGR209.Eksam.Repo;

import PGR209.Eksam.Model.Address;
import PGR209.Eksam.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
}
