package br.com.reclameaqui.teste;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.reclameaqui.teste.documents.Cliente;
import br.com.reclameaqui.teste.documents.Endereco;
import br.com.reclameaqui.teste.dtos.ConsultaReclamacaoRequestDTO;
import br.com.reclameaqui.teste.exceptions.ClienteNaoEncontradoException;
import br.com.reclameaqui.teste.util.Utils;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TesteApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestCliente {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	 public void test() throws Exception {

		this.mockMvc.perform(get("/teste")
	        		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", Matchers.hasToString("teste ok")));
	 }
	
	
/*
 *   @InjectMocks
    private HelloController helloController;
 
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }
 
    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
 */
	
	
	/*
	 
	 .andExpect(jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(jsonPath("$.content[0].email").value("user1@example.com"))
            .andExpect(jsonPath("$.content[0].firstName").value("User"))
            .andExpect(jsonPath("$.content[0].lastName").value("One"))
            .andExpect(jsonPath("$.content[1].email").value("user2@example.com"))
            .andExpect(jsonPath("$.content[1].firstName").value("User"))
            .andExpect(jsonPath("$.content[1].lastName").value("Two"))
            .andExpect(jsonPath("$.totalElements").value(2))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.size").value(20))
            .andExpect(jsonPath("$.number").value(0))
            .andExpect(jsonPath("$.first").value("true"))
            .andExpect(jsonPath("$.last").value("true"));
	 */
	
	@Test
	 public void testQuantidadeDeClientes() throws Exception {

		this.mockMvc.perform(get("/clientes")
	        		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", Matchers.hasSize(9)));
	 }
	
	@Test
	 public void testQuantidadeDeReclamacoesEmpresaECidade() throws Exception {
		ConsultaReclamacaoRequestDTO consulta = new ConsultaReclamacaoRequestDTO("Samsung","São Paulo");

		this.mockMvc.perform(post("/clientes/consultar/reclamantes")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(Utils.asJsonString(consulta))
	        	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.clientesQueReclamaram", Matchers.hasToString("5")));
	 }
	
	@Test
	 public void testQuantidadeDeReclamacoesEmpresa() throws Exception {
		ConsultaReclamacaoRequestDTO consulta = new ConsultaReclamacaoRequestDTO();
		consulta.setEmpresa("Samsung");

		this.mockMvc.perform(post("/clientes/consultar/reclamantes")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(Utils.asJsonString(consulta))
	        	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.clientesQueReclamaram", Matchers.hasToString("8")));
	 }
	
	@Test
	 public void testQuantidadeDeReclamacoesCidade() throws Exception {
		ConsultaReclamacaoRequestDTO consulta = new ConsultaReclamacaoRequestDTO();
		consulta.setCidade("São Paulo");

		this.mockMvc.perform(post("/clientes/consultar/reclamantes")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(Utils.asJsonString(consulta))
	        	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.clientesQueReclamaram", Matchers.hasToString("6")));
	 }
	
	@Test
	 public void testQuantidadeReclamantes() throws Exception {

		this.mockMvc.perform(get("/reclamacoes")
	        		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", Matchers.hasSize(14)));
	 }
	
	
	@Test
	 public void testCRUDCliente() throws Exception {
		Endereco endereco = new Endereco("07346444", "Rua Liberdade", "1243", "Jardim Independente", "Campinas");
		Cliente cliente = new Cliente("Jessica", "1945784854", "25042859730", endereco);

		this.mockMvc.perform(post("/cliente")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(Utils.asJsonString(cliente)));
		
		this.mockMvc.perform(get("/cliente/cpf/" + cliente.getCpf())
        		.contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome", Matchers.hasToString("Jessica")));
		
		cliente.setNome("Patricia");
		
		this.mockMvc.perform(put("/cliente/cpf/" + cliente.getCpf())
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(Utils.asJsonString(cliente)));
		
		this.mockMvc.perform(get("/cliente/cpf/" + cliente.getCpf())
        		.contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome", Matchers.hasToString("Patricia")));
		
		this.mockMvc.perform(delete("/cliente/cpf/" + cliente.getCpf())
        		.contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
		
	 }
	//   @Test(expected = ArithmeticException.class)
	
	@Test()
	 public void testException() throws Exception {

		this.mockMvc.perform(delete("/cliente/cpf/" + "52247215122")
	    		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	
	
	
	/*
	@Test
	 public void testBuscarCepVariasTentativasColocando0ECepNaoEncontrado() throws Exception {
	        Endereco vo = new Endereco();
	        vo.setCep("11111111");
	        Gson gson = new Gson();
	        String json = gson.toJson(vo);
	        
	        this.mockMvc.perform(get("/buscarCepJson")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(json))
	            .andExpect(status().isInternalServerError());
	    }
	
	@Test
	 public void testBuscarCepVariasTentativasColocando0() throws Exception {
	        Endereco vo = new Endereco();
	        vo.setCep("09951385");
	        Gson gson = new Gson();
	        String json = gson.toJson(vo);
	        
	        this.mockMvc.perform(get("/buscarCepJson")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(json))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.cep" , is("09951380")));
	    }
	
	@Test
	 public void testBuscarCepInvalidoException() throws Exception {
	        Endereco vo = new Endereco();
	        vo.setCep("023");
	        Gson gson = new Gson();
	        String json = gson.toJson(vo);
	        
	        this.mockMvc.perform(get("/buscarCepJson")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(json))
	            	.andExpect(status().isInternalServerError());
	 }
	
	@Test
	 public void testBuscarCepVazioException() throws Exception {
	        Endereco vo = new Endereco();
	        vo.setCep("");
	        Gson gson = new Gson();
	        String json = gson.toJson(vo);
	        
	        this.mockMvc.perform(get("/buscarCepJson")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(json))
	            .andExpect(status().isInternalServerError());
	    }
	    
	    */
}
