package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.IstanzaRuolo;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;

import java.util.*;
import java.util.function.Predicate;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.IstanzaRuolo.values;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.*;

public final class RuoliFactory
{

    private final Map<String, Ruolo> ruoli;

    public RuoliFactory()
    {
        ruoli = new LinkedHashMap<>();
        caricaRuoli();
    }

    public Ruolo getRuolo(String nome)
    {
        Ruolo ruolo;
        switch(nome)
        {
            case "Cacciatore" -> ruolo = getCacciatore();
            case "Cappuccetto rosso" -> ruolo = getCappuccettoRosso();
            case "Goblin" -> ruolo = getGoblin();
            case "Eremita" -> ruolo = getEremita();
            case "Ladra" -> ruolo = getLadra();
            case "Leprecauno" -> ruolo = getLeprecauno();
            case "Sidhe" -> ruolo = getSidhe();
            default -> ruolo = ruoli.get(nome);
        }
        return ruolo;
    }

    public int getNumeroRuoli() { return ruoli.size(); }

    public String getNome(int posizione) { return getChiavi().stream().toList().get(posizione); }

    public Ruolo[] getLupi() { return filtraRuoli(ruolo -> ruolo.isLupo() || ruolo.isContadinoLupo()); }

    public Ruolo[] getMistici() { return filtraRuoli(mistico -> mistico.isMistico() && !mistico.isMedium() && !mistico.isPiccoloPopolo()); }

    public Ruolo[] getCreatureOmbra()
    {
        return filtraRuoli(ruolo -> ruolo.isTrattoPresente(CREATURA_OMBRA) || ruolo.getCategoria() == CREATURE_OMBRA);
    }

    public void annullaSegnalazioni()
    {
        annullaVoti();
        resettaRomeo();
        resettaAmato();
        resettaSegnalazioneAzzeccagarbugli();
        resettaNonMorto();
        resettaMaledetto();
        eliminaTrattiContadinoLupo();
        ripristinaFazioniOriginali();
    }

    private void ripristinaFazioniOriginali()
    {
        for(String nome : getChiavi()) if(!isGuardiaCorrotta(nome) && !isLupoSolitario(nome)) ripristinaFazioneOriginale(nome);
    }

    private void ripristinaFazioneOriginale(String nome) { ottieniRuolo(nome).ripristinaFazioneOriginale(); }

    private boolean isGuardiaCorrotta(String nome) { return ottieniRuolo(nome).isGuardiaCorrotta(); }

    private boolean isLupoSolitario(String nome) { return ottieniRuolo(nome).isLupoSolitario(); }

    private void eliminaTrattiContadinoLupo()
    {
        Ruolo ruolo = ottieniRuolo("Contadino discendente dei lupi");
        Tratto[] tratti = new Tratto[] { CREATURA_OMBRA, LUPO_MANNARO };
        for(Tratto tratto : tratti) ruolo.eliminaTratto(tratto);
    }

    private void resettaNonMorto() { for(String nome : getChiavi()) if(isNonMorto(nome)) resettaNonMorto(nome); }

    private boolean isNonMorto(String nome) { return ottieniRuolo(nome).isTrattoPresente(NON_MORTO); }

    private void resettaMaledetto() { for(String nome : getChiavi()) if(!isContadinoMostro(nome)) annullaMaledizione(nome); }

    private boolean isContadinoMostro(String nome) { return ottieniRuolo(nome).isContadinoMostro(); }

    private void annullaMaledizione(String nome) { ottieniRuolo(nome).eliminaTratto(MALEDETTO); }

    private void resettaNonMorto(String nome) { ottieniRuolo(nome).eliminaTratto(NON_MORTO); }

    private void annullaVoti() { for(String chiave : getChiavi()) annullaVoti(chiave); }

    private void annullaVoti(String chiave) { ottieniRuolo(chiave).annullaVoti(); }

    private void resettaRomeo() { for(String chiave : getChiavi()) resettaRomeo(chiave); }

    private void resettaRomeo(String chiave) { ottieniRuolo(chiave).resettaRomeo(); }

    private void resettaAmato() { for(String nome : getChiavi()) getResettaAmato(nome); }

    private void getResettaAmato(String nome) { ottieniRuolo(nome).resettaAmato(); }

    private void resettaSegnalazioneAzzeccagarbugli() { for(String nome : getChiavi()) annullaSegnalazioneAzzeccagarbugli(nome); }

    private void annullaSegnalazioneAzzeccagarbugli(String nome) { ottieniRuolo(nome).annullaSegnalazioneAzzeccagarbugli(); }

    private Set<String> getChiavi() { return ruoli.keySet(); }

    private Ruolo getCappuccettoRosso()
    {
        Ruolo ruolo = ottieniRuolo("Cappuccetto rosso");
        ruolo.aggiungiProtezione(getLupi());
        return ruolo;
    }

    private Ruolo getGoblin() { return getPiccoloPopolo("Goblin"); }

    private Ruolo getEremita() { return getPersonaggioProtetto("Eremita"); }

    private Ruolo getLadra() { return getPersonaggioProtetto("Ladra"); }

    private Ruolo getLeprecauno() { return getPiccoloPopolo("Leprecauno"); }

    private Ruolo getSidhe() { return getPiccoloPopolo("Sidhe"); }

    private Ruolo getCacciatore()
    {
        Ruolo ruolo = ottieniRuolo("Cacciatore");
        ruolo.aggiungiProtezione(ottieniRuolo("Nonna"));
        return ruolo;
    }

    private Ruolo getPersonaggioProtetto(String nome)
    {
        Ruolo ruolo = ottieniRuolo(nome);
        ruolo.aggiungiProtezione(getCreatureOmbra());
        if(ruolo.isEremita()) ruolo.aggiungiProtezione(getRuolo("Contadino discendente dei lupi"));
        return ruolo;
    }

    private void caricaRuoli() { for(IstanzaRuolo istanza : values()) aggiungiRuolo(istanza); }

    private Ruolo getPiccoloPopolo(String nome)
    {
        Ruolo ruolo = ottieniRuolo(nome);
        ruolo.aggiungiProtezione(getMistici());
        return ruolo;
    }

    private Ruolo[] filtraRuoli(Predicate<Ruolo> predicato)
    {
        return toArray(ruoli.values().stream().filter(predicato).toList());
    }

    private void aggiungiRuolo(IstanzaRuolo istanza)
    {
        Ruolo ruolo = istanza.getRuolo();
        ruoli.put(getNome(ruolo), ruolo);
    }

    private String getNome(Ruolo ruolo)
    {
        String nome;
        if(ruolo.isContadino()) nome = getNomeContadino(ruolo);
        else nome = ruolo.getNome();
        return nome;
    }

    private String getNomeContadino(Ruolo ruolo)
    {
        String nome;
        if(ruolo.isContadinoNormale()) nome = "Contadino normale";
        else if(ruolo.isContadinoEroe()) nome = "Contadino eroe";
        else if(ruolo.isContadinoMostro()) nome = "Contadino mostro";
        else nome = "Contadino discendente dei lupi";
        return nome;
    }

    private Ruolo[] toArray(List<Ruolo> lista)
    {
        Ruolo[] array = new Ruolo[lista.size()];
        lista.toArray(array);
        return array;
    }

    private Ruolo ottieniRuolo(String nome) { return ruoli.get(nome); }

}