import java.util.List;

/**
 * Utente
 * Di questa classse si occupa Alessandro
 */
public class Utente {
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String cellulare;
    private List<Noleggio> noleggiAttivi;

    // Costruttore
    public Utente(String codiceFiscale, String nome, String cognome, String cellulare, List<Noleggio> noleggiAttivi) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.cellulare = cellulare;
        this.noleggiAttivi = noleggiAttivi;
    }
}
