# Classi di RentACar

> [!note]
> in questo file trovi l'elenco delle classi del progetto comprese di attributi e metodi con invarianti e contratti

## Utente
### Attributi
- `String: codiceFiscale`
- `String: nome`
- `String: cognome`
- `String: cellulare`
- `List<Noleggio>: noleggiAttivi`

#### Invariante
- codiceFiscale deve essere non nullo e seguire il formato corretto di un codice fiscale italiano.
- nome, cognome, cellulare devono essere non nulli e non vuoti.
- La lista noleggiAttivi non deve contenere duplicati e può essere vuota.

---

## Veicolo
### Attributi
- `String: targa`
- `String: tipo (furgone, bus, auto)`
- `Strina: modello`
- `int. kmPercorsi`
- `int: oreUtilizzo`
- `LocalDate: annoImmatricolazione`
- `double: tariffaOraria`
- `Stazione: stazioneAttuale`
- `int: dimensione`
- `List<Periodo>: periodiGiaOccupatiDaNoleggi`
#### Invariante
- tariffaOraria deve essere sempre positiva.
- stazioneAttuale può essere null solo se il veicolo è in uso
- kmPercorsi, oreUtilizzo e dimensione devono essere valori non negativi


### Metodi
#### `void aggiornaTariffaOraria(double nuovaTariffa)`
pre: 
- nuovaTariffa deve essere positivo
post:
- modifica lo stato di Veicolo cambiando il valore di this.tariffaOraria

#### `void aggiornaStazione(Stazione nuovaStazione)`
post:
- modifica lo stato di Veicolo cambiando il valore di this.stazioneAttuale
- se il veicolo viene noleggiato e inizia il suo viaggio, la stazione attuale è NULL

#### `double getKmMediAllOra()`
pre:
- this.oreUtilizzo deve essere positivo
post: 
- ritorna sempre un valore double >= 0 che rappresenta i km medi che la macchina percorre in un ora basandosi sugli attributi di istanza

#### `void aggiungiKmPercorsi(int km)`
pre:
- il parametro km deve essere >= 0
post:
- this.kmPercorsi verrà modificato
- this.kmPercorsi avrà un valore maggiore rispetto a prima

#### `void aggiungiOreDiUtilizzo (int ore)`
pre:
- il parametro ore deve essere >= 0
post:
- this.oreUtilizzo verrà modificato
- this.oreUtilizzo avrà un valore maggiore rispetto a prima

#### `void aggiungiGiorniDiUtilizzo(int giorni)`
pre:
- il parametro giorni deve essere >= 0
post:
- this.oreUtilizzo verrà modificato
- this.oreUtilizzo avrà un valore maggiore rispetto a prima

#### `boolean controlla DisponibilitàPeriodo(LocalDate inizio, LocalDate fine)`
pre:
- il parametro fine deve essere >= del parametro inizio
post:
- restituisce vero se la macchina è disponibile in quel range e falso se non lo è

#### `boolean controllaSeInUnaStazione()`
post:
- vero se il veicolo si trova parcheggiata in una stazione, falso altrimenti 

#### `void riceviNotificaDiProgrammazioneNoleggio(Noleggio noleggio)`
pre:
- il parametro noleggio non deve essere NULL
post:
- this.periodiGiaOccupatiDaNoleggi cambia di stato
- this.periodiGiaOccupatiDaNoleggi non ci saranno accavallamenti di date

#### `void riceviNotificaDiInizioNoleggio(Noleggio noleggio)`
pre:
- il parametro noleggio non deve essere NULL
post:
- this.stazioneAttuale cambierà stato

#### `void riceviNotificaDiProlungamentoNoleggio(Noleggio noleggio)`
pre:
- il parametro noleggio non deve essere NULL
post:
- this.periodiGiaOccupatiDaNoleggi cambia di stato
- this.periodiGiaOccupatiDaNoleggi non ci saranno accavallamenti di date

#### `void riceviNotificaDiTerminazioneNoleggio(Noleggio noleggio)`
pre:
- il parametro noleggio non deve essere NULL
post:
- this.stazioneAttuale cambierà stato
- this.kmPercorsi cambierà di stato
- this.oreUtilizzo cambierà di stato
- this.periodiGiaOccuapatiDaNoleggi cambierà di stato ed eliminerà il range di date relativo al noleggio appena concluso

---

## Noleggio

### Attributi
- `int: id`
- `Veicolo: veicoloNoleggiato`
- `Utente: cliente`
- `Stazione: stazionePartenza`
- `Stazione: stazioneArrivo`
- `LocalDate: dataPartenza`
- `LocalDate: dataArrivoPrevista`
- `LocalDate: dataArrivoEffettiva`
#### Invariante
- stazionePartenza e stazioneArrivo non devono essere null.
- veicoloNoleggiato e cliente devono essere associati correttamente e non nulli.

### Metodi
#### `void completaNoleggio(dataArrivoEffettiva, kmPercorsiDurantellNoleggio)`
pre:
- dataArrivoEffettiva deve essere >= di this.dataArrivoPrevista
- veicoloNoleggiato.StazioneAttuale = null, (vedere se va bene null)
- id.dataArrivoEffettiva >= id.dataArrivoPrevista.
post:
- notificheremo la stazione di arrivo di ospitare il veicolo del noleggio
- notificheremo il veicolo stesso di avere percorso un numero di chilometri pari a kmPercorsiDuranteNoleggio
- veicoloNoleggiato.stazioneAttuale = id.stazioneArrivo 
- tramite veicoloNoleggiato.aggiornaStazioneAttuale(Stazione staioneArrivo),
- veicoloNoleggiato.kmPercorsi = veicoloNoleggiato.kmPercorsi + kmPercorsiDuranteIlNoleggio
- tramite veicoloNoleggiato.aggiungiKmPercorsi(int (kmPercorsiDuranteIlNoleggio)), 
- veicoloNoleggiato.oreDiUtilizzo = veicoloNoleggiato.oreDiUtilizzo + (id.dataArrivoEffettiva - id.dataPartenza) 
- tramite calcolatore.calcolaDurataNoleggio(id),
- stazioneArrivo.listVeicoli = stazioneArrivo.listVeicoli + veicoloNoleggiato
- tramite stazioneArrivo.parcheggiaVeicolo(veicoloNoleggiato).
- L'id del noleggio sarà rimosso da cliente.noleggiAttivi.  

#### `void notificaLaStazioneDelCompletamentoDelNoleggio()`
pre:
- veicoloNoleggiato.stazioneAttuale = stazioneArrivo,
post: 
- la stazione riceverà questa notifica e modificherà il suo stato
- La notifica è stata ricevuta dalla stazioneArrivo tramite Stazione.receiveFineNoleggio(id). 

#### `void notificaVeicoloDelCompletamentoNoleggio(int kmPercorsiDuranteIlNoleggio, Noleggio noleggio)`
pre: 
- this.dataArrivoEffettiva e this.dataPartenza siano non NULL
- veicoloNoleggiato.StazioneAttuale = stazioneArrivo.
post:
- il veicolo del noleggio riceverà questa notifica e modificherà il suo stato 
- La notifica è stata ricevuta dal veicoloNoleggiato tramite veicoloNoleggiato.receiveFineNoleggio(id). 


#### `void notificaLaStazioneDeInizioDelNoleggio()`
post:
- notifichiamo la Stazione del fatto che il Veicolo è stato prese e quindi dovrà modificare il suo stato

#### `void notificaVeicoloDeInizioDelNoleggio()`
post:
- notifichiamo il Veicolo del fatto che il Veicolo è stato prese e quindi dovrà modificare il suo stato

#### `void notificaVeicoloDellaProgrammazioneDelNoleggio()`
post: 
- notifichiamo il Veicolo del fatto che il Veicolo verrà noleggiato in un certo range di date e quindi dovrà modificare il suo stato

#### `void notificaIlVeicoloDelProlungamentoDelNoleggio()`
post: 
- notifichiamo il Veicolo del fatto che il Veicolo verrà noleggiato più a lungo quindi dovrà modificare il suo stato

---

## Stazione
### Attributi
#todo
#### Invariante
#todo
### Metodi
#todo

---

## Calcolatore
### Metodi
#### `double calcolaCostoTotaleDiUnNoleggio(Noleggio noleggio)`
pre:
- Il noleggio deve avere sapere la data di arrivo effettiva
post:
- restituisce un numero >= 0
- tiene in considerazione di un sovrapprezzo della tariffa (30% in più) per le ore extra non patuite dal noleggio (dataArrivoPrevista)
#### `Period calcolaDurataNoleggio(Noleggio noleggio)`
pre: 
- Il noleggio deve avere sapere la data di partenza
- Il noleggio deve avere sapere la data di arrivo effettiva
- Il noleggio deve avere sapere la data di arrivo prevista
- Il noleggio deve avere sapere il veicolo coinvolto nel noleggio
- Il noleggio deve avere sapere l'utente che ha noleggiato il veicolo
post:
- restituisce anni, giorni e ore che relative alla durata del noleggio dalla data di partenza a quella di arrivo effettiva


---

## RentACar
### Attributi
- `String: partitaIVA`
- `List<Noleggio>: elencoNoleggiAttivi`
- `List<Stazione>: elencoStazioniDiNoleggio`
- `Calcolatore: calcolatore`
#### Invariante
- `partitaIVA` deve essere un valore valido secondo il formato italiano e non nullo.
- `elencoNoleggiAttivi` contiene solo noleggi validi e attivi.
- `elencoStazioniDiNoleggio` non deve contenere duplicati e tutte le stazioni devono essere valide.
- `factory` e `calcolatore` devono essere istanze valide e non null.
### Metodi
#### `String disponibilità(String idStazione, String nomeStazione, String luogoStazione, String tipoVeicolo, String modelloVeicolo, double tariffaOraria, LocalDate dataImmatricolazione, int posti)`
pre:
- `idStazione`, `nomeStazione`, e `luogoStazione` non devono essere nulli o vuoti.
- Il tipo e il modello del veicolo devono corrispondere ai valori esistenti.
- La data di immatricolazione deve essere valida.
- `posti` deve essere >= 0.
post:
- Restituisce una stringa che descrive la disponibilità dei veicoli in base ai parametri forniti.

---

#### `Noleggio creaNoleggio(Utente utente, String luogo, String tipo, String modello, int posti, LocalDate dataInizio, LocalDate dataFine, Stazione stazioneRitiro, Stazione stazioneConsegna) throws NoleggioInvalidoException`
pre:
- `utente`, `luogo`, `tipo`, `modello`, `dataInizio`, `dataFine`, `stazioneRitiro` e `stazioneConsegna` non devono essere nulli.
- `dataFine` deve essere >= `dataInizio`.
- `posti` deve essere >= 0.
- La disponibilità del veicolo richiesto deve essere verificata.
post:
- Genera e restituisce un nuovo noleggio valido, aggiornando `elencoNoleggiAttivi` con il nuovo noleggio.

---

#### `Utente creaUtente(String cf, String nome, String cognome, String cellulare)`
pre:
- `cf`, `nome`, `cognome`, e `cellulare` non devono essere nulli o vuoti.
- `cf` deve rispettare il formato corretto di un codice fiscale italiano.
post:
- Restituisce un'istanza di `Utente` con i dati forniti.

---

#### `Noleggio creaNoleggio(Veicolo veicoloNoleggiato, Utente cliente, Stazione stazionePartenza, Stazione stazioneArrivo, LocalDate dataPartenza, LocalDate dataArrivoPrevista) throws NoleggioInvalidoException`
pre:
- Tutti i parametri devono essere non nulli.
- `dataArrivoPrevista` deve essere >= `dataPartenza`.
- Il veicolo deve essere disponibile nel periodo richiesto.
post:
- Genera un nuovo noleggio valido e aggiorna `elencoNoleggiAttivi`.

---

#### `Stazione creaStazione(String nome, String regione, String provincia, String città, String via, String numeroCivico, int postiTotali, int postiLiberi)`
pre:
- `nome`, `regione`, `provincia`, `città`, `via` e `numeroCivico` non devono essere nulli o vuoti.
- `postiTotali` e `postiLiberi` devono essere >= 0 e `postiLiberi` deve essere <= `postiTotali`.
post:
- Restituisce una nuova istanza di `Stazione` con i dati forniti e aggiorna `elencoStazioniDiNoleggio`.

---

#### `Veicolo creaVeicolo(String targa, String tipo, String modello, LocalDate annoImmatricolazione, double tariffaOraria, Stazione stazioneAttuale, int dimensione)`
pre:
- `targa`, `tipo`, e `modello` non devono essere nulli o vuoti.
- `annoImmatricolazione` deve essere valido.
- `tariffaOraria` deve essere > 0.
- `dimensione` deve essere >= 0.
post:
- Genera un nuovo veicolo valido e restituisce l'istanza.

---

## Dashboard
### Attributi
#todo
#### Invariante
#todo
### Metodi
#todo

---

## StazionePienaException
### Attributi
#todo
#### Invariante
#todo
### Metodi
#todo

---

## NoleggioInvalidoException
### Attributi
#todo
#### Invariante
#todo
### Metodi
#todo


## Dashboard
### Attributi
- `RentACar: azienda`  

### Metodi

#### `void visualizzaInfoUtente(String id)`
pre:
- `id` non deve essere nullo o vuoto.
- Deve esistere un utente con l'ID fornito nell'azienda.
post:
- Visualizza le informazioni relative all'utente con l'ID specificato.

---

#### `void visualizzaInfoTuttiUtenti()`
pre:
- Devono esistere utenti registrati nell'azienda.
post:
- Visualizza le informazioni di tutti gli utenti registrati.

---

#### `void visualizzaInfoVeicolo(String targa)`
pre:
- `targa` non deve essere nulla o vuota.
- Deve esistere un veicolo con la targa specificata.
post:
- Visualizza le informazioni relative al veicolo con la targa specificata.

---

#### `void visualizzaInfoTuttiVeicoli()`
pre:
- Devono esistere veicoli registrati nell'azienda.
post:
- Visualizza le informazioni di tutti i veicoli registrati.

---

#### `void visualizzaInfoNoleggio(String idNoleggio)`
pre:
- `idNoleggio` non deve essere nullo o vuoto.
- Deve esistere un noleggio con l'ID specificato.
post:
- Visualizza le informazioni relative al noleggio con l'ID specificato.

---

#### `void visualizzaInfoNoleggiAttivi()`
pre:
- Devono esistere noleggi attivi nell'azienda.
post:
- Visualizza le informazioni di tutti i noleggi attivi.

---

## StazionePienaException
### Attributi
- `String: idStazione`  (Identificativo della stazione piena.)
- `int: postiTotali`  (Numero totale di posti disponibili nella stazione.)
- `int: postiOccupati`  (Numero di posti attualmente occupati nella stazione.)
### Invariante
- `postiOccupati` deve essere minore o uguale a `postiTotali`.
### Metodi
#### Costruttore
pre:
- `String idStazione`
- `int postiTotali`
- `int postiOccupati`
post:
- Inizializza gli attributi con i valori forniti.

---

#### `String getMessaggioErrore()`
post:
- Restituisce un messaggio descrittivo nel formato:  
  `"La stazione [idStazione] è piena: [postiOccupati]/[postiTotali] posti occupati."`

---

## NoleggioInvalidoException
### Attributi
- `String: motivo`  (Descrizione del motivo per cui il noleggio è considerato invalido)
- `Noleggio: noleggioInvalido`  (Riferimento al noleggio che ha causato l'eccezione (opzionale))

### Invariante
- `motivo` non deve essere nullo o vuoto.

