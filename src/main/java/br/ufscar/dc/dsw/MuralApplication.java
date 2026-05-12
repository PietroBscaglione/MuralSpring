package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MuralApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(MuralApplication.class, args);

		var userRepository = context.getBean(UserRepository.class);
		if (userRepository.findByUsername("admin").isEmpty()) {
			userRepository.save("admin", "admin", "ADMIN");
		}
		if (userRepository.findByUsername("user").isEmpty()) {
			userRepository.save("user", "user", "USER");
		}
	}

}
