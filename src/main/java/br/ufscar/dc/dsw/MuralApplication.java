package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.dto.UserCreateDTO;
import br.ufscar.dc.dsw.repository.UserRepository;
import br.ufscar.dc.dsw.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MuralApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(MuralApplication.class, args);

		var userRepository = context.getBean(UserRepository.class);
		var userService = context.getBean(UserService.class);
		if (userRepository.findByUsername("admin").isEmpty()) {
			userService.create(new UserCreateDTO("admin", "admin", "ADMIN"));
		}
		if (userRepository.findByUsername("user").isEmpty()) {
			userService.create(new UserCreateDTO("user", "user", "USER"));
		}
	}

}
