package br.com.reclameaqui.teste.integracao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.reclameaqui.teste.TesteApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteApplicationTests {

	@Test
	public void contextLoads() {
		TesteApplication.main(new String[] {});
		assertTrue(Boolean.TRUE);
	}

}