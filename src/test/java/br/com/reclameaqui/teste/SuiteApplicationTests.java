package br.com.reclameaqui.teste;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.reclameaqui.teste.service.TestClienteService;
import br.com.reclameaqui.teste.service.TestReclamacaoService;
import br.com.reclameaqui.teste.utils.TestUtils;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestClienteService.class,
        TestReclamacaoService.class,
        TestUtils.class
})
public class SuiteApplicationTests {
    @Test
    public void contextLoads() {
    	TesteApplication.main(new String[] {});
		assertTrue(Boolean.TRUE);
    }
}