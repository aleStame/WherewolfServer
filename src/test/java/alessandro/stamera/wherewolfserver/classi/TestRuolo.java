package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.*;
import static alessandro.stamera.wherewolfserver.classi.Fazione.*;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    private static final String ESEMPIO_NOME = "Ruolo", ESEMPIO_DESCRIZIONE = "Descrizione generica";

    @ParameterizedTest @CsvSource({ "1, 2, 3" })
    public void testLune(int lune) { assertThat(getRuolo(VILLAGGIO, BIANCA, lune).getLune()).isEqualTo(lune); }

    @ParameterizedTest @CsvSource({ "BIANCA, NERA" })
    public void testAura(Aura aura) { assertThat(getRuolo(VAMPIRO, aura, 1).getAura()).isEqualTo(aura); }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA", "LUPO_BRANCO", "LUPO_SOLITARIO", "VAMPIRO", "NOSFERATU", "NEGROMANTE", "POSSEDUTO", "VILLAGGIO", "CITTA", "CRIMINALI",
            "AMANTI", "INQUISIZIONE"
        }
    )
    public void testFazione(Fazione fazione) { assertThat(getRuolo(fazione, BIANCA, 3).getFazione()).isEqualTo(fazione); }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, NESSUNA", "LUPO_BRANCO, CREATURE_OMBRA", "LUPO_SOLITARIO, CREATURE_OMBRA", "VAMPIRO, CREATURE_OMBRA",
            "NOSFERATU, CREATURE_OMBRA", "NEGROMANTE, CREATURE_OMBRA", "POSSEDUTO, CREATURE_OMBRA", "VILLAGGIO, UOMINI", "CITTA, UOMINI",
            "CRIMINALI, UOMINI", "AMANTI, UOMINI", "INQUISIZIONE, UOMINI"
        }
    )
    public void testCategoria(Fazione fazione, Categoria categoria)
    {
        assertThat(getRuolo(fazione, NERA, 2).getCategoria()).isEqualTo(categoria);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, BIANCA, 1", "NESSUNA, BIANCA, 2", "NESSUNA, BIANCA, 3", "NESSUNA, NERA, 1", "NESSUNA, NERA, 2", "NESSUNA, NERA, 3",
            "LUPO_BRANCO, BIANCA, 1", "LUPO_BRANCO, BIANCA, 2", "LUPO_BRANCO, BIANCA, 3", "LUPO_BRANCO, NERA, 1", "LUPO_BRANCO, NERA, 2",
            "LUPO_BRANCO, NERA, 3", "LUPO_SOLITARIO, BIANCA, 1", "LUPO_SOLITARIO, BIANCA, 2", "LUPO_SOLITARIO, BIANCA, 3",
            "LUPO_SOLITARIO, NERA, 1", "LUPO_SOLITARIO, NERA, 2", "LUPO_SOLITARIO, NERA, 3", "VAMPIRO, BIANCA, 1", "VAMPIRO, BIANCA, 2",
            "VAMPIRO, BIANCA, 3", "VAMPIRO, NERA, 1", "VAMPIRO, NERA, 2", "VAMPIRO, NERA, 3", "NOSFERATU, BIANCA, 1", "NOSFERATU, BIANCA, 2",
            "NOSFERATU, BIANCA, 3", "NOSFERATU, NERA, 1", "NOSFERATU, NERA, 2", "NOSFERATU, NERA, 3", "NEGROMANTE, BIANCA, 1",
            "NEGROMANTE, BIANCA, 1", "NEGROMANTE, BIANCA, 2", "NEGROMANTE, BIANCA, 3", "NEGROMANTE, NERA, 1", "NEGROMANTE, NERA, 2",
            "NEGROMANTE, NERA, 3", "POSSEDUTO, BIANCA, 1", "POSSEDUTO, BIANCA, 2", "POSSEDUTO, BIANCA, 3", "POSSEDUTO, NERA, 1",
            "POSSEDUTO, NERA, 2", "POSSEDUTO, NERA, 3", "VILLAGGIO, BIANCA, 1", "VILLAGGIO, BIANCA, 2", "VILLAGGIO, BIANCA, 3",
            "VILLAGGIO, NERA, 1", "VILLAGGIO, NERA, 2", "VILLAGGIO, NERA, 3", "CITTA, BIANCA, 1", "CITTA, BIANCA, 2", "CITTA, BIANCA, 3",
            "CITTA, NERA, 1", "CITTA, NERA, 2", "CITTA, NERA, 3", "CRIMINALI, BIANCA, 1", "CRIMINALI, BIANCA, 2", "CRIMINALI, BIANCA, 3",
            "CRIMINALI, NERA, 1", "CRIMINALI, NERA, 2", "CRIMINALI, NERA, 3", "AMANTI, BIANCA, 1", "AMANTI, BIANCA, 2", "AMANTI, BIANCA, 3",
            "AMANTI, NERA, 1", "AMANTI, NERA, 2", "AMANTI, NERA, 3", "INQUISIZIONE, BIANCA, 1", "INQUISIZIONE, BIANCA, 2",
            "INQUISIZIONE, BIANCA, 3", "INQUISIZIONE, NERA, 1", "INQUISIZIONE, NERA, 2", "INQUISIZIONE, NERA, 3"
        }
    )
    public void testNome(Fazione fazione, Aura aura, int lune) { verificaStringa(getRuolo(fazione, aura, lune).getNome(), ESEMPIO_NOME); }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, BIANCA, 1", "NESSUNA, BIANCA, 2", "NESSUNA, BIANCA, 3", "NESSUNA, NERA, 1", "NESSUNA, NERA, 2", "NESSUNA, NERA, 3",
            "LUPO_BRANCO, BIANCA, 1", "LUPO_BRANCO, BIANCA, 2", "LUPO_BRANCO, BIANCA, 3", "LUPO_BRANCO, NERA, 1", "LUPO_BRANCO, NERA, 2",
            "LUPO_BRANCO, NERA, 3", "LUPO_SOLITARIO, BIANCA, 1", "LUPO_SOLITARIO, BIANCA, 2", "LUPO_SOLITARIO, BIANCA, 3",
            "LUPO_SOLITARIO, NERA, 1", "LUPO_SOLITARIO, NERA, 2", "LUPO_SOLITARIO, NERA, 3", "VAMPIRO, BIANCA, 1", "VAMPIRO, BIANCA, 2",
            "VAMPIRO, BIANCA, 3", "VAMPIRO, NERA, 1", "VAMPIRO, NERA, 2", "VAMPIRO, NERA, 3", "NOSFERATU, BIANCA, 1", "NOSFERATU, BIANCA, 2",
            "NOSFERATU, BIANCA, 3", "NOSFERATU, NERA, 1", "NOSFERATU, NERA, 2", "NOSFERATU, NERA, 3", "NEGROMANTE, BIANCA, 1",
            "NEGROMANTE, BIANCA, 1", "NEGROMANTE, BIANCA, 2", "NEGROMANTE, BIANCA, 3", "NEGROMANTE, NERA, 1", "NEGROMANTE, NERA, 2",
            "NEGROMANTE, NERA, 3", "POSSEDUTO, BIANCA, 1", "POSSEDUTO, BIANCA, 2", "POSSEDUTO, BIANCA, 3", "POSSEDUTO, NERA, 1",
            "POSSEDUTO, NERA, 2", "POSSEDUTO, NERA, 3", "VILLAGGIO, BIANCA, 1", "VILLAGGIO, BIANCA, 2", "VILLAGGIO, BIANCA, 3",
            "VILLAGGIO, NERA, 1", "VILLAGGIO, NERA, 2", "VILLAGGIO, NERA, 3", "CITTA, BIANCA, 1", "CITTA, BIANCA, 2", "CITTA, BIANCA, 3",
            "CITTA, NERA, 1", "CITTA, NERA, 2", "CITTA, NERA, 3", "CRIMINALI, BIANCA, 1", "CRIMINALI, BIANCA, 2", "CRIMINALI, BIANCA, 3",
            "CRIMINALI, NERA, 1", "CRIMINALI, NERA, 2", "CRIMINALI, NERA, 3", "AMANTI, BIANCA, 1", "AMANTI, BIANCA, 2", "AMANTI, BIANCA, 3",
            "AMANTI, NERA, 1", "AMANTI, NERA, 2", "AMANTI, NERA, 3", "INQUISIZIONE, BIANCA, 1", "INQUISIZIONE, BIANCA, 2",
            "INQUISIZIONE, BIANCA, 3", "INQUISIZIONE, NERA, 1", "INQUISIZIONE, NERA, 2", "INQUISIZIONE, NERA, 3"
        }
    )
    public void testDescrizione(Fazione fazione, Aura aura, int lune)
    {
        verificaStringa(getRuolo(fazione, aura, lune).getDescrizione(), ESEMPIO_DESCRIZIONE);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, BIANCA, 1", "NESSUNA, BIANCA, 2", "NESSUNA, BIANCA, 3", "NESSUNA, NERA, 1", "NESSUNA, NERA, 2", "NESSUNA, NERA, 3",
            "LUPO_BRANCO, BIANCA, 1", "LUPO_BRANCO, BIANCA, 2", "LUPO_BRANCO, BIANCA, 3", "LUPO_BRANCO, NERA, 1", "LUPO_BRANCO, NERA, 2",
            "LUPO_BRANCO, NERA, 3", "LUPO_SOLITARIO, BIANCA, 1", "LUPO_SOLITARIO, BIANCA, 2", "LUPO_SOLITARIO, BIANCA, 3",
            "LUPO_SOLITARIO, NERA, 1", "LUPO_SOLITARIO, NERA, 2", "LUPO_SOLITARIO, NERA, 3", "VAMPIRO, BIANCA, 1", "VAMPIRO, BIANCA, 2",
            "VAMPIRO, BIANCA, 3", "VAMPIRO, NERA, 1", "VAMPIRO, NERA, 2", "VAMPIRO, NERA, 3", "NOSFERATU, BIANCA, 1", "NOSFERATU, BIANCA, 2",
            "NOSFERATU, BIANCA, 3", "NOSFERATU, NERA, 1", "NOSFERATU, NERA, 2", "NOSFERATU, NERA, 3", "NEGROMANTE, BIANCA, 1",
            "NEGROMANTE, BIANCA, 1", "NEGROMANTE, BIANCA, 2", "NEGROMANTE, BIANCA, 3", "NEGROMANTE, NERA, 1", "NEGROMANTE, NERA, 2",
            "NEGROMANTE, NERA, 3", "POSSEDUTO, BIANCA, 1", "POSSEDUTO, BIANCA, 2", "POSSEDUTO, BIANCA, 3", "POSSEDUTO, NERA, 1",
            "POSSEDUTO, NERA, 2", "POSSEDUTO, NERA, 3", "VILLAGGIO, BIANCA, 1", "VILLAGGIO, BIANCA, 2", "VILLAGGIO, BIANCA, 3",
            "VILLAGGIO, NERA, 1", "VILLAGGIO, NERA, 2", "VILLAGGIO, NERA, 3", "CITTA, BIANCA, 1", "CITTA, BIANCA, 2", "CITTA, BIANCA, 3",
            "CITTA, NERA, 1", "CITTA, NERA, 2", "CITTA, NERA, 3", "CRIMINALI, BIANCA, 1", "CRIMINALI, BIANCA, 2", "CRIMINALI, BIANCA, 3",
            "CRIMINALI, NERA, 1", "CRIMINALI, NERA, 2", "CRIMINALI, NERA, 3", "AMANTI, BIANCA, 1", "AMANTI, BIANCA, 2", "AMANTI, BIANCA, 3",
            "AMANTI, NERA, 1", "AMANTI, NERA, 2", "AMANTI, NERA, 3", "INQUISIZIONE, BIANCA, 1", "INQUISIZIONE, BIANCA, 2",
            "INQUISIZIONE, BIANCA, 3", "INQUISIZIONE, NERA, 1", "INQUISIZIONE, NERA, 2", "INQUISIZIONE, NERA, 3"
        }
    )
    public void testContadino(Fazione fazione, Aura aura, int lune) { verificaFalso(getRuolo(fazione, aura, lune).isContadino()); }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, BIANCA, 1", "NESSUNA, BIANCA, 2", "NESSUNA, BIANCA, 3", "NESSUNA, NERA, 1", "NESSUNA, NERA, 2", "NESSUNA, NERA, 3",
            "LUPO_BRANCO, BIANCA, 1", "LUPO_BRANCO, BIANCA, 2", "LUPO_BRANCO, BIANCA, 3", "LUPO_BRANCO, NERA, 1", "LUPO_BRANCO, NERA, 2",
            "LUPO_BRANCO, NERA, 3", "LUPO_SOLITARIO, BIANCA, 1", "LUPO_SOLITARIO, BIANCA, 2", "LUPO_SOLITARIO, BIANCA, 3",
            "LUPO_SOLITARIO, NERA, 1", "LUPO_SOLITARIO, NERA, 2", "LUPO_SOLITARIO, NERA, 3", "VAMPIRO, BIANCA, 1", "VAMPIRO, BIANCA, 2",
            "VAMPIRO, BIANCA, 3", "VAMPIRO, NERA, 1", "VAMPIRO, NERA, 2", "VAMPIRO, NERA, 3", "NOSFERATU, BIANCA, 1", "NOSFERATU, BIANCA, 2",
            "NOSFERATU, BIANCA, 3", "NOSFERATU, NERA, 1", "NOSFERATU, NERA, 2", "NOSFERATU, NERA, 3", "NEGROMANTE, BIANCA, 1",
            "NEGROMANTE, BIANCA, 1", "NEGROMANTE, BIANCA, 2", "NEGROMANTE, BIANCA, 3", "NEGROMANTE, NERA, 1", "NEGROMANTE, NERA, 2",
            "NEGROMANTE, NERA, 3", "POSSEDUTO, BIANCA, 1", "POSSEDUTO, BIANCA, 2", "POSSEDUTO, BIANCA, 3", "POSSEDUTO, NERA, 1",
            "POSSEDUTO, NERA, 2", "POSSEDUTO, NERA, 3", "VILLAGGIO, BIANCA, 1", "VILLAGGIO, BIANCA, 2", "VILLAGGIO, BIANCA, 3",
            "VILLAGGIO, NERA, 1", "VILLAGGIO, NERA, 2", "VILLAGGIO, NERA, 3", "CITTA, BIANCA, 1", "CITTA, BIANCA, 2", "CITTA, BIANCA, 3",
            "CITTA, NERA, 1", "CITTA, NERA, 2", "CITTA, NERA, 3", "CRIMINALI, BIANCA, 1", "CRIMINALI, BIANCA, 2", "CRIMINALI, BIANCA, 3",
            "CRIMINALI, NERA, 1", "CRIMINALI, NERA, 2", "CRIMINALI, NERA, 3", "AMANTI, BIANCA, 1", "AMANTI, BIANCA, 2", "AMANTI, BIANCA, 3",
            "AMANTI, NERA, 1", "AMANTI, NERA, 2", "AMANTI, NERA, 3", "INQUISIZIONE, BIANCA, 1", "INQUISIZIONE, BIANCA, 2",
            "INQUISIZIONE, BIANCA, 3", "INQUISIZIONE, NERA, 1", "INQUISIZIONE, NERA, 2", "INQUISIZIONE, NERA, 3"
        }
    )
    public void testContadinoNormale(Fazione fazione, Aura aura, int lune) { verificaFalso(getRuolo(fazione, aura, lune).isContadinoNormale()); }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, BIANCA, 1", "NESSUNA, BIANCA, 2", "NESSUNA, BIANCA, 3", "NESSUNA, NERA, 1", "NESSUNA, NERA, 2", "NESSUNA, NERA, 3",
            "LUPO_BRANCO, BIANCA, 1", "LUPO_BRANCO, BIANCA, 2", "LUPO_BRANCO, BIANCA, 3", "LUPO_BRANCO, NERA, 1", "LUPO_BRANCO, NERA, 2",
            "LUPO_BRANCO, NERA, 3", "LUPO_SOLITARIO, BIANCA, 1", "LUPO_SOLITARIO, BIANCA, 2", "LUPO_SOLITARIO, BIANCA, 3",
            "LUPO_SOLITARIO, NERA, 1", "LUPO_SOLITARIO, NERA, 2", "LUPO_SOLITARIO, NERA, 3", "VAMPIRO, BIANCA, 1", "VAMPIRO, BIANCA, 2",
            "VAMPIRO, BIANCA, 3", "VAMPIRO, NERA, 1", "VAMPIRO, NERA, 2", "VAMPIRO, NERA, 3", "NOSFERATU, BIANCA, 1", "NOSFERATU, BIANCA, 2",
            "NOSFERATU, BIANCA, 3", "NOSFERATU, NERA, 1", "NOSFERATU, NERA, 2", "NOSFERATU, NERA, 3", "NEGROMANTE, BIANCA, 1",
            "NEGROMANTE, BIANCA, 1", "NEGROMANTE, BIANCA, 2", "NEGROMANTE, BIANCA, 3", "NEGROMANTE, NERA, 1", "NEGROMANTE, NERA, 2",
            "NEGROMANTE, NERA, 3", "POSSEDUTO, BIANCA, 1", "POSSEDUTO, BIANCA, 2", "POSSEDUTO, BIANCA, 3", "POSSEDUTO, NERA, 1",
            "POSSEDUTO, NERA, 2", "POSSEDUTO, NERA, 3", "VILLAGGIO, BIANCA, 1", "VILLAGGIO, BIANCA, 2", "VILLAGGIO, BIANCA, 3",
            "VILLAGGIO, NERA, 1", "VILLAGGIO, NERA, 2", "VILLAGGIO, NERA, 3", "CITTA, BIANCA, 1", "CITTA, BIANCA, 2", "CITTA, BIANCA, 3",
            "CITTA, NERA, 1", "CITTA, NERA, 2", "CITTA, NERA, 3", "CRIMINALI, BIANCA, 1", "CRIMINALI, BIANCA, 2", "CRIMINALI, BIANCA, 3",
            "CRIMINALI, NERA, 1", "CRIMINALI, NERA, 2", "CRIMINALI, NERA, 3", "AMANTI, BIANCA, 1", "AMANTI, BIANCA, 2", "AMANTI, BIANCA, 3",
            "AMANTI, NERA, 1", "AMANTI, NERA, 2", "AMANTI, NERA, 3", "INQUISIZIONE, BIANCA, 1", "INQUISIZIONE, BIANCA, 2",
            "INQUISIZIONE, BIANCA, 3", "INQUISIZIONE, NERA, 1", "INQUISIZIONE, NERA, 2", "INQUISIZIONE, NERA, 3"
        }
    )
    public void testContadinoMostro(Fazione fazione, Aura aura, int lune) { verificaFalso(getRuolo(fazione, aura, lune).isContadinoMostro()); }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, BIANCA, 1", "NESSUNA, BIANCA, 2", "NESSUNA, BIANCA, 3", "NESSUNA, NERA, 1", "NESSUNA, NERA, 2", "NESSUNA, NERA, 3",
            "LUPO_BRANCO, BIANCA, 1", "LUPO_BRANCO, BIANCA, 2", "LUPO_BRANCO, BIANCA, 3", "LUPO_BRANCO, NERA, 1", "LUPO_BRANCO, NERA, 2",
            "LUPO_BRANCO, NERA, 3", "LUPO_SOLITARIO, BIANCA, 1", "LUPO_SOLITARIO, BIANCA, 2", "LUPO_SOLITARIO, BIANCA, 3",
            "LUPO_SOLITARIO, NERA, 1", "LUPO_SOLITARIO, NERA, 2", "LUPO_SOLITARIO, NERA, 3", "VAMPIRO, BIANCA, 1", "VAMPIRO, BIANCA, 2",
            "VAMPIRO, BIANCA, 3", "VAMPIRO, NERA, 1", "VAMPIRO, NERA, 2", "VAMPIRO, NERA, 3", "NOSFERATU, BIANCA, 1", "NOSFERATU, BIANCA, 2",
            "NOSFERATU, BIANCA, 3", "NOSFERATU, NERA, 1", "NOSFERATU, NERA, 2", "NOSFERATU, NERA, 3", "NEGROMANTE, BIANCA, 1",
            "NEGROMANTE, BIANCA, 1", "NEGROMANTE, BIANCA, 2", "NEGROMANTE, BIANCA, 3", "NEGROMANTE, NERA, 1", "NEGROMANTE, NERA, 2",
            "NEGROMANTE, NERA, 3", "POSSEDUTO, BIANCA, 1", "POSSEDUTO, BIANCA, 2", "POSSEDUTO, BIANCA, 3", "POSSEDUTO, NERA, 1",
            "POSSEDUTO, NERA, 2", "POSSEDUTO, NERA, 3", "VILLAGGIO, BIANCA, 1", "VILLAGGIO, BIANCA, 2", "VILLAGGIO, BIANCA, 3",
            "VILLAGGIO, NERA, 1", "VILLAGGIO, NERA, 2", "VILLAGGIO, NERA, 3", "CITTA, BIANCA, 1", "CITTA, BIANCA, 2", "CITTA, BIANCA, 3",
            "CITTA, NERA, 1", "CITTA, NERA, 2", "CITTA, NERA, 3", "CRIMINALI, BIANCA, 1", "CRIMINALI, BIANCA, 2", "CRIMINALI, BIANCA, 3",
            "CRIMINALI, NERA, 1", "CRIMINALI, NERA, 2", "CRIMINALI, NERA, 3", "AMANTI, BIANCA, 1", "AMANTI, BIANCA, 2", "AMANTI, BIANCA, 3",
            "AMANTI, NERA, 1", "AMANTI, NERA, 2", "AMANTI, NERA, 3", "INQUISIZIONE, BIANCA, 1", "INQUISIZIONE, BIANCA, 2",
            "INQUISIZIONE, BIANCA, 3", "INQUISIZIONE, NERA, 1", "INQUISIZIONE, NERA, 2", "INQUISIZIONE, NERA, 3"
        }
    )
    public void testContadinoEroe(Fazione fazione, Aura aura, int lune) { verificaFalso(getRuolo(fazione, aura, lune).isContadinoEroe()); }

    private Ruolo getRuolo(Fazione fazione, Aura aura, int lune)
    {
        return new Ruolo(ESEMPIO_NOME, fazione, aura, ESEMPIO_DESCRIZIONE, lune);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}