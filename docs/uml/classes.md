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
#todo
#### Invariante
#todo
### Metodi
#todo

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
