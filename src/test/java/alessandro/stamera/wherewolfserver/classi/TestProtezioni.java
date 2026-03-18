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
        verificaPresenza(nome);
    }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Giovane lupo" })
    public void testCappuccettoRosso(String nome)
    {
        protezioni.aggiungiProtezioneLupi();
        verificaPresenza(nome);
    }

    @Test public void testPerdiProtezioni()
    {
        protezioni.perdiProtezioni();
        verificaAssenza(factory.getCappuccettoRosso());
        for(int i = 0; i < factory.getNumeroRuoli(); i++) verificaAssenza(getRuolo(factory.getNome(i)));
    }

    @ParameterizedTest @CsvSource({ "Guaritore, Goblin, Leprecauno" }) public void testProtezioneMistici(String nome)
    {
        protezioni.aggiungiProtezioneMistici();
        verificaPresenza(nome);
    }

    private void verificaAssenza(Ruolo ruolo) { assertThat(isPresente(ruolo)).isFalse(); }

    private void verificaPresenza(String nome) { verificaPresenza(getRuolo(nome)); }

    private Ruolo getRuolo(String nome) { return factory.getRuolo(nome); }

    private void verificaPresenza(Ruolo ruolo) { assertThat(isPresente(ruolo)).isTrue(); }

    private boolean isPresente(Ruolo ruolo) { return protezioni.isPresente(ruolo); }

}