package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.*;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VAMPIRO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    private static final String ESEMPIO_NOME = "Ruolo", ESEMPIO_DESCRIZIONE = "Descrizione generica";

    @ParameterizedTest @CsvSource({ "1, 2, 3" })
    public void testLune(int lune) { assertThat(getRuolo(VILLAGGIO, BIANCA, lune).lune()).isEqualTo(lune); }

    @ParameterizedTest @CsvSource({ "BIANCA, NERA" })
    public void testAura(Aura aura) { assertThat(getRuolo(VAMPIRO, aura, 1).aura()).isEqualTo(aura); }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA", "LUPO_BRANCO", "LUPO_SOLITARIO", "VAMPIRO", "NOSFERATU", "NEGROMANTE", "POSSEDUTO", "VILLAGGIO", "CITTA", "CRIMINALI",
            "AMANTI", "INQUISIZIONE"
        }
    )
    public void testFazione(Fazione fazione) { assertThat(getRuolo(fazione, BIANCA, 3).fazione()).isEqualTo(fazione); }

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

    @Test public void testNome() { assertThat(getRuolo(NESSUNA, BIANCA, 1).nome()).isEqualTo(ESEMPIO_NOME); }

    @Test public void testDescrizione() { assertThat(getRuolo(CITTA, NERA, 3).descrizione()).isEqualTo(ESEMPIO_DESCRIZIONE); }

    private Ruolo getRuolo(Fazione fazione, Aura aura, int lune)
    {
        return new Ruolo(ESEMPIO_NOME, fazione, aura, ESEMPIO_DESCRIZIONE, lune);
    }

}