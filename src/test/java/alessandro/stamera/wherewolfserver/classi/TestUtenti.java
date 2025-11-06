package alessandro.stamera.wherewolfserver.classi;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
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
        verificaNoEccezione(() -> registrazione("pinuccio", "pwdpinuccio"));
    }

    @Test public void registrazioneNonRiuscita()
    {
        verificaEccezione(() -> registrazione("marco", "passworddimarco"), "ERRORE!!! Nome utente già inserito");
    }

    @Test public void cambioPasswordRiuscito()
    {
        verificaNoEccezione(() -> cambioPassword("marco", "passwordsecret", "newpwd"));
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
        verificaEccezione(() -> cambioPassword(username, vecchiaPassword, nuovaPassword), risultato);
    }

    @Test public void disiscrizioneRiuscita() { verificaNoEccezione(() -> disiscrizione("andrea", "andrea1998")); }

    @ParameterizedTest @CsvSource
    (
        {
            "marco, passwordcasual, ERRORE!!! Inserire la password corretta", "sulpicio, batmanbeyond, ERRORE!!! Utente non esistente",
            "sigismondo, pwdfantasiosa, ERRORE!!! Utente non esistente"
        }
    )
    public void disiscrizioneNonRiuscita(String username, String password, String risultato)
    {
        verificaEccezione(() -> disiscrizione(username, password), risultato);
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

    public void verificaNoEccezione(ThrowingCallable operazione) { assertThatNoException().isThrownBy(operazione); }

    public void verificaEccezione(ThrowingCallable operazione, String risultato)
    {
        assertThatIllegalArgumentException().isThrownBy(operazione).withMessage(risultato);
    }

    private void registrazione(String username, String password) { utenti.registrazione(username, password); }

    private void cambioPassword(String username, String vecchiaPassword, String nuovaPassword)
    {
        utenti.cambioPassword(username, vecchiaPassword, nuovaPassword);
    }

    private void disiscrizione(String username, String password) { utenti.disiscrizione(username, password); }

    private Utente[] getUtentiEsempio()
    {
        String[][] credenziali = { { "andrea", "andrea1998" }, { "marco", "passwordsecret" }, { "bruce", "batmanbeyond" } };
        Utente[] utenti = new Utente[credenziali.length];
        for(int i = 0; i < utenti.length; i++) utenti[i] = new Utente(credenziali[i][0], credenziali[i][1]);
        return utenti;
    }

}