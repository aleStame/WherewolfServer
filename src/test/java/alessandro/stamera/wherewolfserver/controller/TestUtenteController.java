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
import org.springframework.test.web.servlet.RequestBuilder;
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
        esempio = getUtentiEsempio();
        mocking();
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
        controllaRisultato(get("/utenti/login?username=" + username + "&password=" + password), risultato);
    }

    @ParameterizedTest @CsvSource
    (
        { "marco, passworddimarco, ERRORE!!! Nome utente già inserito", "pinuccio, pwdpinuccio, Registrazione avvenuta correttamente" }
    )
    public void registrazione(String username, String password, String risultato) throws Exception
    {
        controllaRisultato(post("/utenti/registrazione?username=" + username + "&password=" + password), risultato);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "marco, passwordsecret, newpwd, Password cambiata correttamente",
            "bruce, batmanbeyond, batmanbeyond, ERRORE!!! Password uguale alla precedente",
            "andrea, skibidiboppi, ped32312231, ERRORE!!! Inserire la password attuale corretta"
        }
    )
    public void cambioPassword(String username, String vecchiaPassword, String nuovaPassword, String risultato) throws Exception
    {
        String URL = "/utenti/cambioPassword?username=" + username + "&vecchiaPassword=" + vecchiaPassword + "&nuovaPassword=" + nuovaPassword;
        controllaRisultato(post(URL), risultato);
    }

    private Utente[] getUtentiEsempio()
    {
        String[][] credenziali = { { "andrea", "andrea1998" }, { "marco", "passwordsecret" }, { "bruce", "batmanbeyond" } };
        Utente[] utenti = new Utente[credenziali.length];
        for(int i = 0; i < utenti.length; i++) utenti[i] = new Utente(credenziali[i][0], credenziali[i][1]);
        return utenti;
    }

    private void mocking()
    {
        given(repo.findAll()).willReturn(of(esempio));
        for(Utente utente : esempio) given(repo.findById(utente.getUsername())).willReturn(Optional.of(utente));
    }

    private void controllaRisultato(RequestBuilder richiesta, String risultato) throws Exception
    {
        assertThat(eseguiRichiesta(richiesta).getResponse().getContentAsString()).isEqualTo(risultato);
    }

    private MvcResult eseguiRichiesta(RequestBuilder richiesta) throws Exception
    {
        return mockMvc.perform(richiesta).andExpect(status().isOk()).andReturn();
    }

}