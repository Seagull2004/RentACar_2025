import java.time.LocalDate;

/**
 * Noleggio
 * di questa classe si occupa Alessandro
 */
public class Noleggio {
    private int id;
    private Veicolo veicoloNoleggiato;
    private Utente cliente;
    private Stazione stazionePartenza;
    private Stazione stazioneArrivo;
    private LocalDate dataPartenza;
    private LocalDate dataArrivoPrevista;
    private LocalDate dataArrivoEffettiva;


    public int getId() {
        return this.id;

    };
    public Veicolo getVeicoloNoleggiato() {
        return this.veicoloNoleggiato;
    };
    public Utente getCliente() {
        return this.cliente;
    };
    public Stazione getStazionePartenza() {
        return this.stazionePartenza;
    };
    public Stazione getStazioneArrivo() {
        return this.stazioneArrivo;
    };
    public LocalDate getDataPartenza() {
        return this.dataPartenza;
    };
    public LocalDate getDataArrivoPrevista() {
        return this.dataArrivoPrevista;
    };
    public LocalDate getDataArrivoEffettiva() {
        return this.dataArrivoEffettiva;
    };


    
}
