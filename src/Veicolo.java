import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

/**
 * Veicolo
 * Di questa classe si occupa Enea
 */
public class Veicolo {
    private String targa;
    private String tipo; // (furgone, bus, auto)
    private String modello;
    private int kmPercorsi;
    private int oreUtilizzo;
    private LocalDate annoImmatricolazione;
    private double tariffaOraria;
    private Stazione stazioneAttuale;
    private int dimensione;
    private Collection<Period> periodiGiaOccupatiDaNoleggi;

    public String getTarga() {
        return this.targa;
    };
    public String getTipo() {
        return this.tipo;
    }; 
    public String getModello() {
        return this.modello;
    };
    public int getKmPercorsi() {
        return this.kmPercorsi;
    };
    public int getOreUtilizzo() {
        return this.oreUtilizzo;
    };
    public LocalDate getAnnoImmatricolazione() {
        return this.annoImmatricolazione;
    };
    public double getTariffaOraria() {
        return this.tariffaOraria;
    };
    public Stazione getStazioneAttuale() {
        return this.stazioneAttuale;
    };
    public int getDimensione() {
        return this.dimensione;
    };
    public Collection<Period> getPeriodiGiaOccupatiDaNoleggi() {
        return this.periodiGiaOccupatiDaNoleggi;
    };
}

