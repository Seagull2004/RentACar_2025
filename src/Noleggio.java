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

    // Costruttore
    public Noleggio(int id, Veicolo veicoloNoleggiato, Utente cliente, Stazione stazionePartenza, 
                    Stazione stazioneArrivo, LocalDate dataPartenza, 
                    LocalDate dataArrivoPrevisto, LocalDate dataArrivoEffettivo) {
        this.id = id;
        this.veicoloNoleggiato = veicoloNoleggiato;
        this.cliente = cliente;
        this.stazionePartenza = stazionePartenza;
        this.stazioneArrivo = stazioneArrivo;
        this.dataPartenza = dataPartenza;
        this.dataArrivoPrevista = dataArrivoPrevisto;
        this.dataArrivoEffettiva = dataArrivoEffettivo;
    }

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


    // Metodo per completare il noleggio, aggiornando la data di arrivo effettiva e i km percorsi
    public void completaNoleggio(LocalDate dataArrivoEffettiva, int kmPercorsiDuranteIlNoleggio) {
        if (veicoloNoleggiato.getStazioneAttuale() != null){                                                                                                                                           // verifica la precondizione: il veicolo non deve essere già parcheggiato in una stazione
            throw new IllegalArgumentException("il veicolo è già parcheggiato nella stazione %s", String veicolo.StazioneAttuale);    
        }
        if (id.dataArrivoEffettiva <= Noleggio.dataArrivoPrevista){                                                                                           // verifica la precondizione: data e ora di consegna non devono avvenire prima della dataArrivoPrevista  
            throw new IllegalArgumentException("prima di parcheggiare il veicolo deve scadere il noleggio");        
        }
        if (stazioneArrivo.calcolaPostiLiberi < veicoloNoleggiato.dimensioni){                                                                          
            throw new stazionePienaException("non ci sono posti per il veicolo, provare nella stazione più vicina in via x....");
        }else{
            stazioneArrivo.parcheggiaVeicolo(veicoloNoleggiato);
            veicoloNoleggiato.aggiornaStazioneAttuale(staioneArrivo);
            veicoloNoleggiato.kmPercorsi = veicoloNoleggiato.kmPercorsi + kmPercorsiDuranteIlNoleggio;
            veicoloNoleggiato.oreDiUtilizzo = veicoloNoleggiato.oreDiUtilizzo + calcolatore.calcolaDurataNoleggio(id);
            cliente.noleggiAttivi.remove(id);                                                                                                                             // rimuove dal cliente i noleggi atttivi, manca il metodo remove?.
            notifyStazioneDelCompletamenteNoleggio(id, stazioneArrivo);
            notifyVeicoloDelCompletamenteNoleggio(id, veicoloNoleggiato);                                                                                                                                           
        }      
    }
    
    // Metodo  notifyStazioneDelCompletamenteNoleggio():
    public void notifyStazioneDelCompletamenteNoleggio(int id, Stazione stazioneArrivo){            // id è l'id del noleggio e stazioneArrivoè la stazione di arrivo a cui notifico la fine di un noleggio
        Stazione.riceviFineNoleggio(id, stazioneArrivo);
    }
    
    // Metodo notifyVeicoloDelCompletamenteNoleggio():
    public void notifyVeicoloDelCompletamenteNoleggio(int id, Veicolo veicoloNoleggiato){         
        Veicolo.riceviFineNoleggio(id, veicoloNoleggiato);
    }
}
