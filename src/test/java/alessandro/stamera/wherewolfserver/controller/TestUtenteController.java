package alessandro.stamera.wherewolfserver.controller;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static java.util.List.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebMvcTest(UtenteController.class) public final class TestUtenteController
{

    @Autowired private MockMvc mockMvc;

    @MockBean private UtenteRepository repo;

    private Utente[] esempio;

    @BeforeEach public void setUp()
    {
        esempio = new Utente[3];
        esempio[0] = new Utente("andrea", "andrea1998");
        esempio[1] = new Utente("marco", "passwordsecret");
        esempio[2] = new Utente("bruce", "batmanbeyond");
        given(repo.findAll()).willReturn(of(esempio));
        for(Utente utente : esempio) given(repo.findById(utente.getUsername())).willReturn(Optional.of(utente));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "andrea, andrea1998, Login eseguito correttamente", "marco, passwordsecret, Login eseguito correttamente",
            "bruce, batmanbeyond, Login eseguito correttamente", "andrea, pwdsbagliata, ERRORE!!! Username o password errate",
            "gino, batmanbeyond, ERRORE!!! Username o password errate", "utentefinto, pwdfinta, ERRORE!!! Username o password errate"
        }
    )
    public void login(String username, String password, String risultato) throws Exception
    {
        var mvcResult = mockMvc.perform(get("/utenti/login?username=" + username + "&password=" + password)).andExpect(status().isOk())
            .andReturn();
        controllaRisultato(mvcResult, risultato);
    }

    @ParameterizedTest @CsvSource
    (
        { "marco, passworddimarco, ERRORE!!! Nome utente già inserito", "pinuccio, pwdpinuccio, Registrazione avvenuta correttamente" }
    )
    public void registrazione(String username, String password, String risultato) throws Exception
    {
        var mvcResult = mockMvc.perform(post("/utenti/registrazione?username=" + username + "&password=" + password))
            .andExpect(status().isOk()).andReturn();
        controllaRisultato(mvcResult, risultato);
    }

    private void controllaRisultato(MvcResult mvcResult, String risultato) throws Exception
    {
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(risultato);
    }

}