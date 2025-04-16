package ugb.sat.madsi;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ugb.sat.madsi.domain.CarRepository;
import ugb.sat.madsi.domain.Car;
import ugb.sat.madsi.domain.OwnerRepository;
import ugb.sat.madsi.domain.Owner;
import ugb.sat.madsi.domain.AppUser;
import ugb.sat.madsi.domain.AppUserRepository;




@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	private final CarRepository repository;
	private final OwnerRepository orepository;
	private final AppUserRepository urepository;
	public CardatabaseApplication(CarRepository repository, OwnerRepository orepository, AppUserRepository urepository) {
		this.repository = repository;
		this.orepository = orepository;
		this.urepository = urepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}
	private void createUsers() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		urepository.save(new AppUser("obkthiam", encoder.encode("usera"), "USER"));
		urepository.save(new AppUser("Omar", encoder.encode("51431"), "ADMIN"));
	}
	

	@Override
public void run(String... args) throws Exception {
    if (orepository.count() == 0 && repository.count() == 0) {
        Owner owner1 = new Owner("John", "Johnson");
        Owner owner2 = new Owner("Mary", "Robinson");
        orepository.saveAll(Arrays.asList(owner1, owner2));

        repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2023, 59000, owner1));
        repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner2));
        repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2022, 39000, owner2));
    }

    repository.findAll().forEach(car ->
        logger.info("brand: {}, model: {}", car.getBrand(), car.getModel())
    );

    if (urepository.count() == 0) {
        createUsers();
    }
}

		
}
