package alessandro.stamera.wherewolfserver.classi;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Optional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TestUtenti
{

    private Utenti utenti;

    @BeforeEach public void setUp() { utenti = new Utenti(getRepositoryEsempio()); }

    @ParameterizedTest @CsvSource({ "andrea, pwdsbagliata", "gino, batmanbeyond", "utentefinto, pwdfinta" })
    public void loginNonRiuscito(String username, String password) { assertThat(login(username, password)).isFalse(); }

    @ParameterizedTest @CsvSource({ "andrea, andrea1998", "marco, passwordsecret", "bruce, batmanbeyond" })
    public void loginRiuscito(String username, String password) { assertThat(login(username, password)).isTrue(); }

    @Test public void registrazioneRiuscita()
    {
        assertThatNoException().isThrownBy(() -> registrazione("pinuccio", "pwdpinuccio"));
    }

    @Test public void registrazioneNonRiuscita()
    {
        assertThatIllegalArgumentException().isThrownBy(() -> registrazione("marco", "passworddimarco"))
            .withMessage("ERRORE!!! Nome utente già inserito");
    }

    @Test public void cambioPasswordRiuscito()
    {
        assertThatNoException().isThrownBy(() -> cambioPassword("marco", "passwordsecret", "newpwd"));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "bruce, batmanbeyond, batmanbeyond, ERRORE!!! Password uguale alla precedente",
            "andrea, skibidiboppi, ped32312231, ERRORE!!! Inserire la password attuale corretta"
        }
    )
    public void cambioPasswordNonRiuscito(String username, String vecchiaPassword, String nuovaPassword, String risultato)
    {
        assertThatIllegalArgumentException().isThrownBy(() -> cambioPassword(username, vecchiaPassword, nuovaPassword)).withMessage(risultato);
    }

    private UtenteRepository getRepositoryEsempio()
    {
        UtenteRepository repo = mock(UtenteRepository.class);
        Utente[] esempio = getUtentiEsempio();
        for(Utente utente : esempio) given(repo.findById(utente.getUsername())).willReturn(Optional.of(utente));
        when(repo.findAll()).thenReturn(List.of(esempio));
        return repo;
    }

    private boolean login(String username, String password) { return utenti.login(username, password); }

    private void registrazione(String username, String password) { utenti.registrazione(username, password); }

    private void cambioPassword(String username, String vecchiaPassword, String nuovaPassword)
    {
        utenti.cambioPassword(username, vecchiaPassword, nuovaPassword);
    }

    private Utente[] getUtentiEsempio()
    {
        String[][] credenziali = { { "andrea", "andrea1998" }, { "marco", "passwordsecret" }, { "bruce", "batmanbeyond" } };
        Utente[] utenti = new Utente[credenziali.length];
        for(int i = 0; i < utenti.length; i++) utenti[i] = new Utente(credenziali[i][0], credenziali[i][1]);
        return utenti;
    }

}