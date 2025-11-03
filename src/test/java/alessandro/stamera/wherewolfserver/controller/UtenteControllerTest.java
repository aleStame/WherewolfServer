package alessandro.stamera.wherewolfserver.controller;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static java.util.List.of;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(UtenteController.class) public final class UtenteControllerTest
{

    @Autowired private MockMvc mockMvc;

    @MockBean private UtenteRepository repo;

    @Test public void testAllUtenti() throws Exception
    {
        Utente u1 = new Utente("alice", "secret"), u2 = new Utente("bob", "1234");
        given(repo.findAll()).willReturn(of(u1, u2));
        var mvcResult = mockMvc.perform(get("/utenti")).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).contains("alice", "bob");
    }
}