package PGR209.Eksam;

import PGR209.Eksam.Model.*;
import PGR209.Eksam.Repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class EksamApplication {

	public static void main(String[] args) {
		SpringApplication.run(EksamApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(
			CustomerRepo customerRepo,
			AddressRepo addressRepo,
			MachineRepo machineRepo,
			OrderRepo orderRepo,
			PartsRepo partsRepo,
			SubassemblyRepo subassemblyRepo) {

		return args -> {
			if (customerRepo.count() == 0) {
				Customer customer = customerRepo.save(new Customer("Customer1", "customer1@gmail.com"));
				Address address = addressRepo.save(new Address("Address1"));
				customer.getAddresses().add(address);

				customerRepo.save(customer);

				Customer customer2 = customerRepo.save(new Customer("Customer2", "customer2@gmail.com"));
				Address address2 = addressRepo.save(new Address("Address2"));
				customer.getAddresses().add(address2);

				customerRepo.save(customer2);

				Customer customer3 = customerRepo.save(new Customer("Customer3", "customer3@gmail.com"));
				Address address3 = addressRepo.save(new Address("Address3"));
				customer.getAddresses().add(address3);

				customerRepo.save(customer3);

				Customer customer4 = customerRepo.save(new Customer("Customer4", "customer4@gmail.com"));
				Address address4 = addressRepo.save(new Address("Address4"));
				customer.getAddresses().add(address4);

				customerRepo.save(customer4);

				Customer customer5 = customerRepo.save(new Customer("Customer5", "customer5@gmail.com"));
				Address address5 = addressRepo.save(new Address("Address5"));
				customer.getAddresses().add(address5);

				customerRepo.save(customer5);

				Customer customer6 = customerRepo.save(new Customer("Customer6", "customer6@gmail.com"));
				Address address6 = addressRepo.save(new Address("Address6"));
				customer.getAddresses().add(address6);

				customerRepo.save(customer6);

				Customer customer7 = customerRepo.save(new Customer("Customer7", "Customer7@gmail.com"));
				Address address7 = addressRepo.save(new Address("Address7"));
				customer.getAddresses().add(address7);

				customerRepo.save(customer7);

				Customer customer8 = customerRepo.save(new Customer("Customer8", "customer8@gmail.com"));
				Address address8 = addressRepo.save(new Address("Address8"));
				customer.getAddresses().add(address8);

				customerRepo.save(customer8);

				Customer customer9 = customerRepo.save(new Customer("Customer9", "customer9@gmail.com"));
				Address address9 = addressRepo.save(new Address("Address9"));
				customer.getAddresses().add(address9);

				customerRepo.save(customer9);

				Customer customer10 = customerRepo.save(new Customer("Customer10", "customer10@gmail.com"));
				Address address10 = addressRepo.save(new Address("Address10"));
				customer.getAddresses().add(address10);

				customerRepo.save(customer10);

				Customer customer11 = customerRepo.save(new Customer("Customer11", "customer11@gmail.com"));
				Address address11 = addressRepo.save(new Address("Address11"));
				customer.getAddresses().add(address11);

				customerRepo.save(customer11);

				Customer customer12 = customerRepo.save(new Customer("Customer12", "customer12@gmail.com"));
				Address address12 = addressRepo.save(new Address("Address12"));
				customer.getAddresses().add(address12);

				customerRepo.save(customer12);

				Customer customer13 = customerRepo.save(new Customer("Customer13", "customer13@gmail.com"));
				Address address13 = addressRepo.save(new Address("Address13"));
				customer.getAddresses().add(address13);

				customerRepo.save(customer13);

				Customer customer14 = customerRepo.save(new Customer("Customer14", "customer14@gmail.com"));
				Address address14 = addressRepo.save(new Address("Address14"));
				customer.getAddresses().add(address14);

				customerRepo.save(customer14);

				Customer customer15 = customerRepo.save(new Customer("Customer15", "customer15@gmail.com"));
				Address address15 = addressRepo.save(new Address("Address15"));
				customer.getAddresses().add(address15);

				customerRepo.save(customer15);

				Machine machine = machineRepo.save(new Machine("Machine1"));
				Subassembly subassembly = subassemblyRepo.save(new Subassembly("Subassembly1"));
				Parts parts = partsRepo.save(new Parts("Part1"));
				machine.getSubassemblies().add(subassembly);
				subassembly.getParts().add(parts);

				subassemblyRepo.save(subassembly);
				machineRepo.save(machine);

				Orders orders = new Orders();
				orders.setCustomer(customer);
				orders.getMachine().add(machine);
				orderRepo.save(orders);

				Machine machine2 = machineRepo.save(new Machine("Machine2"));
				Subassembly subassembly2 = subassemblyRepo.save(new Subassembly("Subassembly2"));
				Parts parts2 = partsRepo.save(new Parts("Part2"));
				machine.getSubassemblies().add(subassembly2);
				subassembly.getParts().add(parts2);

				subassemblyRepo.save(subassembly2);
				machineRepo.save(machine2);

				Orders orders2 = new Orders();
				orders2.setCustomer(customer2);
				orders2.getMachine().add(machine2);
				orderRepo.save(orders2);

				Machine machine3 = machineRepo.save(new Machine("Machine3"));
				Subassembly subassembly3 = subassemblyRepo.save(new Subassembly("Subassembly3"));
				Parts parts3 = partsRepo.save(new Parts("Part3"));
				machine.getSubassemblies().add(subassembly3);
				subassembly.getParts().add(parts3);

				subassemblyRepo.save(subassembly3);
				machineRepo.save(machine3);

				Orders orders3 = new Orders();
				orders3.setCustomer(customer3);
				orders3.getMachine().add(machine3);
				orderRepo.save(orders3);

				Machine machine4 = machineRepo.save(new Machine("Machine4"));
				Subassembly subassembly4 = subassemblyRepo.save(new Subassembly("Subassembly4"));
				Parts parts4 = partsRepo.save(new Parts("Part4"));
				machine.getSubassemblies().add(subassembly4);
				subassembly.getParts().add(parts4);

				subassemblyRepo.save(subassembly4);
				machineRepo.save(machine4);

				Orders orders4 = new Orders();
				orders4.setCustomer(customer4);
				orders4.getMachine().add(machine4);
				orderRepo.save(orders4);

				Machine machine5 = machineRepo.save(new Machine("Machine5"));
				Subassembly subassembly5 = subassemblyRepo.save(new Subassembly("Subassembly5"));
				Parts parts5 = partsRepo.save(new Parts("Part5"));
				machine.getSubassemblies().add(subassembly5);
				subassembly.getParts().add(parts5);

				subassemblyRepo.save(subassembly5);
				machineRepo.save(machine5);

				Orders orders5 = new Orders();
				orders5.setCustomer(customer5);
				orders5.getMachine().add(machine5);
				orderRepo.save(orders5);

				Machine machine6 = machineRepo.save(new Machine("Machine6"));
				Subassembly subassembly6 = subassemblyRepo.save(new Subassembly("Subassembly6"));
				Parts parts6 = partsRepo.save(new Parts("Part6"));
				machine.getSubassemblies().add(subassembly6);
				subassembly.getParts().add(parts6);

				subassemblyRepo.save(subassembly6);
				machineRepo.save(machine6);

				Orders orders6 = new Orders();
				orders6.setCustomer(customer6);
				orders6.getMachine().add(machine6);
				orderRepo.save(orders6);

				Machine machine7 = machineRepo.save(new Machine("Machine7"));
				Subassembly subassembly7 = subassemblyRepo.save(new Subassembly("Subassembly7"));
				Parts parts7 = partsRepo.save(new Parts("Part7"));
				machine.getSubassemblies().add(subassembly7);
				subassembly.getParts().add(parts7);

				subassemblyRepo.save(subassembly7);
				machineRepo.save(machine7);

				Orders orders7 = new Orders();
				orders7.setCustomer(customer7);
				orders7.getMachine().add(machine7);
				orderRepo.save(orders7);

				Machine machine8 = machineRepo.save(new Machine("Machine8"));
				Subassembly subassembly8 = subassemblyRepo.save(new Subassembly("Subassembly8"));
				Parts parts8 = partsRepo.save(new Parts("Part8"));
				machine.getSubassemblies().add(subassembly8);
				subassembly.getParts().add(parts8);

				subassemblyRepo.save(subassembly8);
				machineRepo.save(machine8);

				Orders orders8 = new Orders();
				orders8.setCustomer(customer8);
				orders8.getMachine().add(machine8);
				orderRepo.save(orders8);

				Machine machine9 = machineRepo.save(new Machine("Machine9"));
				Subassembly subassembly9 = subassemblyRepo.save(new Subassembly("Subassembly9"));
				Parts parts9 = partsRepo.save(new Parts("Part9"));
				machine.getSubassemblies().add(subassembly9);
				subassembly.getParts().add(parts9);

				subassemblyRepo.save(subassembly9);
				machineRepo.save(machine9);

				Orders orders9 = new Orders();
				orders9.setCustomer(customer9);
				orders9.getMachine().add(machine9);
				orderRepo.save(orders9);

				Machine machine10 = machineRepo.save(new Machine("Machine10"));
				Subassembly subassembly10 = subassemblyRepo.save(new Subassembly("Subassembly10"));
				Parts parts10 = partsRepo.save(new Parts("Part10"));
				machine.getSubassemblies().add(subassembly10);
				subassembly.getParts().add(parts10);

				subassemblyRepo.save(subassembly10);
				machineRepo.save(machine10);

				Orders orders10 = new Orders();
				orders10.setCustomer(customer10);
				orders10.getMachine().add(machine10);
				orderRepo.save(orders10);

				Machine machine11 = machineRepo.save(new Machine("Machine11"));
				Subassembly subassembly11 = subassemblyRepo.save(new Subassembly("Subassembly11"));
				Parts parts11 = partsRepo.save(new Parts("Part11"));
				machine.getSubassemblies().add(subassembly11);
				subassembly.getParts().add(parts11);

				subassemblyRepo.save(subassembly11);
				machineRepo.save(machine11);

				Orders orders11 = new Orders();
				orders11.setCustomer(customer11);
				orders11.getMachine().add(machine11);
				orderRepo.save(orders11);

				Machine machine12 = machineRepo.save(new Machine("Machine12"));
				Subassembly subassembly12 = subassemblyRepo.save(new Subassembly("Subassembly12"));
				Parts parts12 = partsRepo.save(new Parts("Part12"));
				machine.getSubassemblies().add(subassembly12);
				subassembly.getParts().add(parts12);

				subassemblyRepo.save(subassembly12);
				machineRepo.save(machine12);

				Orders orders12 = new Orders();
				orders12.setCustomer(customer12);
				orders12.getMachine().add(machine12);
				orderRepo.save(orders12);
			}

		};

	}

}
