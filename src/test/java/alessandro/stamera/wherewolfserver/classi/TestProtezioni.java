package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestProtezioni
{

    private Protezioni protezioni;

    private RuoliFactory factory;

    @BeforeEach public void setUp()
    {
        protezioni = new Protezioni();
        factory = new RuoliFactory();
    }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Giovane lupo" })
    public void testCreatureOmbra(String nome)
    {
        protezioni.aggiungiProtezioneCreatureOmbra();
        verificaPresenza(factory.getRuolo(nome));
    }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Giovane lupo" })
    public void testCappuccettoRosso(String nome)
    {
        protezioni.aggiungiProtezioneLupi();
        verificaPresenza(factory.getRuolo(nome));
    }

    @Test public void testPerdiProtezioni()
    {
        protezioni.perdiProtezioni();
        for(int i = 0; i < factory.getNumeroRuoli(); i++) assertThat(isPresente(factory.getRuolo(factory.getNome(i)))).isFalse();
    }

    @ParameterizedTest @CsvSource({ "Guaritore, Goblin, Leprecauno" }) public void testProtezioneMistici(String nome)
    {
        protezioni.aggiungiProtezioneMistici();
        verificaPresenza(factory.getRuolo(nome));
    }

    private void verificaPresenza(Ruolo ruolo) { assertThat(isPresente(ruolo)).isTrue(); }

    private boolean isPresente(Ruolo ruolo) { return protezioni.isPresente(ruolo); }

}