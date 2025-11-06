package alessandro.stamera.wherewolfserver.classi;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public final class TestUtenti
{

    private UtenteRepository repo;

    private Utenti utenti;

    @BeforeEach public void setUp()
    {
        repo = mock(UtenteRepository.class);
        when(repo.findAll()).thenReturn(of(getUtentiEsempio()));
        utenti = new Utenti(repo);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "andrea, andrea1998, true", "marco, passwordsecret, true", "bruce, batmanbeyond, true", "andrea, pwdsbagliata, false",
            "gino, batmanbeyond, false", "utentefinto, pwdfinta, false"
        }
    )
    public void login(String username, String password, boolean risultato) { assertThat(utenti.login(username, password)).isEqualTo(risultato); }

    private Utente[] getUtentiEsempio()
    {
        String[][] credenziali = { { "andrea", "andrea1998" }, { "marco", "passwordsecret" }, { "bruce", "batmanbeyond" } };
        Utente[] utenti = new Utente[credenziali.length];
        for(int i = 0; i < utenti.length; i++) utenti[i] = new Utente(credenziali[i][0], credenziali[i][1]);
        return utenti;
    }

}