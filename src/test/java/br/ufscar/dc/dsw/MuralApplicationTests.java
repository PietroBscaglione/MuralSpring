package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.repository.MessageRepository;
import br.ufscar.dc.dsw.repository.MuralRepository;
import br.ufscar.dc.dsw.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class MuralApplicationTests {

	@MockitoBean
	private MessageRepository messageRepository;

	@MockitoBean
	private MuralRepository muralRepository;

	@MockitoBean
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

}
