package PGR209.Eksam;

import PGR209.Eksam.Model.*;
import PGR209.Eksam.Repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EksamApplication {

	public static void main(String[] args) {
		SpringApplication.run(EksamApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			CustomerRepo customerRepo,
			AddressRepo addressRepo,
			MachineRepo machineRepo,
			OrderRepo orderRepo,
			PartsRepo partsRepo,
			SubassemblyRepo subassemblyRepo) {

		return args -> {
			if (customerRepo.count() != 0) {

			}else {	Customer customer = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address);
				customerRepo.save(customer);

				Machine machine = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts = partsRepo.save(new Parts("Chip"));
				machine.getSubassemblies().add(subassembly);
				subassembly.getParts().add(parts);

				subassemblyRepo.save(subassembly);
				machineRepo.save(machine);

				Orders orders = new Orders();
				orders.setCustomer(customer);
				orders.getMachine().add(machine);
				orderRepo.save(orders);}
		};
	}

}
