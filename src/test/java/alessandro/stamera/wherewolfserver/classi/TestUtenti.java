package alessandro.stamera.wherewolfserver.classi;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TestUtenti
{

    private Utenti utenti;

    @BeforeEach public void setUp()
    {
        UtenteRepository repo = mock(UtenteRepository.class);
        when(repo.findAll()).thenReturn(of(getUtentiEsempio()));
        utenti = new Utenti(repo);
    }

    @ParameterizedTest @CsvSource({ "andrea, pwdsbagliata", "gino, batmanbeyond", "utentefinto, pwdfinta" })
    public void loginNonRiuscito(String username, String password) { assertThat(utenti.login(username, password)).isFalse(); }

    @ParameterizedTest @CsvSource({ "andrea, andrea1998", "marco, passwordsecret", "bruce, batmanbeyond" })
    public void loginRiuscito(String username, String password) { assertThat(utenti.login(username, password)).isTrue(); }

    private Utente[] getUtentiEsempio()
    {
        String[][] credenziali = { { "andrea", "andrea1998" }, { "marco", "passwordsecret" }, { "bruce", "batmanbeyond" } };
        Utente[] utenti = new Utente[credenziali.length];
        for(int i = 0; i < utenti.length; i++) utenti[i] = new Utente(credenziali[i][0], credenziali[i][1]);
        return utenti;
    }

}