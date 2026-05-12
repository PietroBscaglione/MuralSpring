package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.repositories.IMessageDAO;
import br.ufscar.dc.dsw.repositories.IUserDAO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class MuralApplicationTests {

	@MockitoBean
	private IMessageDAO messageDAO;

	@MockitoBean
	private IUserDAO userDAO;

	@Test
	void contextLoads() {
	}

}
