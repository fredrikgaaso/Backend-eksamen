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
			if (customerRepo.count() == 0) {
				Customer customer = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address);

				customerRepo.save(customer);

				Customer customer2 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address2 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address2);

				customerRepo.save(customer2);

				Customer customer3 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address3 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address3);

				customerRepo.save(customer3);

				Customer customer4 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address4 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address4);

				customerRepo.save(customer4);

				Customer customer5 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address5 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address5);

				customerRepo.save(customer5);

				Customer customer6 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address6 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address6);

				customerRepo.save(customer6);

				Customer customer7 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address7 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address7);

				customerRepo.save(customer7);

				Customer customer8 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address8 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address8);

				customerRepo.save(customer8);

				Customer customer9 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address9 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address9);

				customerRepo.save(customer9);

				Customer customer10 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address10 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address10);

				customerRepo.save(customer10);

				Customer customer11 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address11 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address11);

				customerRepo.save(customer11);

				Customer customer12 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address12 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address12);

				customerRepo.save(customer12);

				Customer customer13 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address13 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address13);

				customerRepo.save(customer13);

				Customer customer14 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address14 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address14);

				customerRepo.save(customer14);

				Customer customer15 = customerRepo.save(new Customer("Hank", "Hank@gmail.com"));
				Address address15 = addressRepo.save(new Address("hanks gate 22"));
				customer.getAddresses().add(address15);

				customerRepo.save(customer15);

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
				orderRepo.save(orders);

				Machine machine2 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly2 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts2 = partsRepo.save(new Parts("Chip"));
				machine2.getSubassemblies().add(subassembly2);
				subassembly2.getParts().add(parts2);

				subassemblyRepo.save(subassembly2);
				machineRepo.save(machine2);

				Orders orders2 = new Orders();
				orders2.setCustomer(customer2);
				orders2.getMachine().add(machine2);
				orderRepo.save(orders2);

				Machine machine3 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly3 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts3 = partsRepo.save(new Parts("Chip"));
				machine3.getSubassemblies().add(subassembly3);
				subassembly3.getParts().add(parts3);

				subassemblyRepo.save(subassembly3);
				machineRepo.save(machine3);

				Orders orders3 = new Orders();
				orders3.setCustomer(customer3);
				orders3.getMachine().add(machine3);
				orderRepo.save(orders3);

				Machine machine4 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly4 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts4 = partsRepo.save(new Parts("Chip"));
				machine4.getSubassemblies().add(subassembly4);
				subassembly4.getParts().add(parts4);

				subassemblyRepo.save(subassembly4);
				machineRepo.save(machine4);

				Orders orders4 = new Orders();
				orders4.setCustomer(customer4);
				orders4.getMachine().add(machine4);
				orderRepo.save(orders4);

				Machine machine5 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly5 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts5 = partsRepo.save(new Parts("Chip"));
				machine5.getSubassemblies().add(subassembly5);
				subassembly5.getParts().add(parts5);

				subassemblyRepo.save(subassembly5);
				machineRepo.save(machine5);

				Orders orders5 = new Orders();
				orders5.setCustomer(customer5);
				orders5.getMachine().add(machine5);
				orderRepo.save(orders5);

				Machine machine6 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly6 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts6 = partsRepo.save(new Parts("Chip"));
				machine6.getSubassemblies().add(subassembly6);
				subassembly6.getParts().add(parts6);

				subassemblyRepo.save(subassembly6);
				machineRepo.save(machine6);

				Orders orders6 = new Orders();
				orders6.setCustomer(customer6);
				orders6.getMachine().add(machine6);
				orderRepo.save(orders6);

				Machine machine7 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly7 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts7 = partsRepo.save(new Parts("Chip"));
				machine7.getSubassemblies().add(subassembly7);
				subassembly7.getParts().add(parts7);

				subassemblyRepo.save(subassembly7);
				machineRepo.save(machine7);

				Orders orders7 = new Orders();
				orders7.setCustomer(customer7);
				orders7.getMachine().add(machine7);
				orderRepo.save(orders7);

				Machine machine8 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly8 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts8 = partsRepo.save(new Parts("Chip"));
				machine8.getSubassemblies().add(subassembly8);
				subassembly8.getParts().add(parts8);

				subassemblyRepo.save(subassembly8);
				machineRepo.save(machine8);

				Orders orders8 = new Orders();
				orders8.setCustomer(customer8);
				orders8.getMachine().add(machine8);
				orderRepo.save(orders8);

				Machine machine9 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly9 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts9 = partsRepo.save(new Parts("Chip"));
				machine9.getSubassemblies().add(subassembly9);
				subassembly9.getParts().add(parts9);

				subassemblyRepo.save(subassembly9);
				machineRepo.save(machine9);

				Orders orders9 = new Orders();
				orders9.setCustomer(customer9);
				orders9.getMachine().add(machine9);
				orderRepo.save(orders9);

				Machine machine10 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly10 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts10 = partsRepo.save(new Parts("Chip"));
				machine10.getSubassemblies().add(subassembly10);
				subassembly10.getParts().add(parts10);

				subassemblyRepo.save(subassembly10);
				machineRepo.save(machine10);

				Orders orders10 = new Orders();
				orders10.setCustomer(customer10);
				orders10.getMachine().add(machine10);
				orderRepo.save(orders10);

				Machine machine11 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly11 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts11 = partsRepo.save(new Parts("Chip"));
				machine11.getSubassemblies().add(subassembly11);
				subassembly11.getParts().add(parts11);

				subassemblyRepo.save(subassembly11);
				machineRepo.save(machine11);

				Orders orders11 = new Orders();
				orders11.setCustomer(customer11);
				orders11.getMachine().add(machine11);
				orderRepo.save(orders11);

				Machine machine12 = machineRepo.save(new Machine("Mac"));
				Subassembly subassembly12 = subassemblyRepo.save(new Subassembly("Motherboard"));
				Parts parts12 = partsRepo.save(new Parts("Chip"));
				machine12.getSubassemblies().add(subassembly12);
				subassembly12.getParts().add(parts12);

				subassemblyRepo.save(subassembly12);
				machineRepo.save(machine12);

				Orders orders12 = new Orders();
				orders12.setCustomer(customer12);
				orders12.getMachine().add(machine12);
				orderRepo.save(orders12);
			}
			System.out.println("hello world");

		};

	}

}
