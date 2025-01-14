# Classi di RentACar
## Utente
### Attributi
String: codiceFiscale
String: nome
String: cognome
String: cellulare
List\<Noleggio\>: noleggiAttivi

## Veicolo
### Attributi
String: targa
String: tipo (furgone, bus, auto)
Strina: modello
int. kmPercorsi
int: oreUtilizzo
LocalDate: annoImmatricolazione
double: tariffaOraria
Stazione: stazioneAttuale
int: dimensione
List\<Periodo\>: periodiGiaOccupatiDaNoleggi
### Metodi
pre: 
- nuovaTariffa deve essere positivo
post:
- modifica lo stato di Veicolo cambiando il valore di this.tariffaOraria
void aggiornaTariffaOraria(double nuovaTariffa)

---

post:
- modifica lo stato di Veicolo cambiando il valore di this.stazioneAttuale
- se il veicolo viene noleggiato e inizia il suo viaggio, la stazione attuale è NULL
void aggiornaStazione(Stazione nuovaStazione)

---

pre:
- this.oreUtilizzo deve essere positivo
post: 
- ritorna sempre un valore double >= 0 che rappresenta i km medi che la macchina percorre in un ora basandosi sugli attributi di istanza
double getKmMediAllOra()

---

pre:
- il parametro km deve essere >= 0
post:
- this.kmPercorsi verrà modificato
- this.kmPercorsi avrà un valore maggiore rispetto a prima
void aggiungiKmPercorsi(int km)

---

pre:
- il parametro ore deve essere >= 0
post:
- this.oreUtilizzo verrà modificato
- this.oreUtilizzo avrà un valore maggiore rispetto a prima
  `void aggiungiOreDiUtilizzo (int ore)`

--- 

pre:
- il parametro giorni deve essere >= 0
post:
- this.oreUtilizzo verrà modificato
- this.oreUtilizzo avrà un valore maggiore rispetto a prima
`void aggiungiGiorniDiUtilizzo(int giorni)`

---

pre:
- il parametro fine deve essere >= del parametro inizio
post:
- restituisce vero se la macchina è disponibile in quel range e falso se non lo è
`boolean controlla DisponibilitàPeriodo(LocalDate inizio, LocalDate fine)`

---

post:
- vero se il veicolo si trova parcheggiata in una stazione, falso altrimenti 
`boolean controllaSeInUnaStazione()`

---

pre:
- il parametro noleggio non deve essere NULL
post:
- this.periodiGiaOccupatiDaNoleggi cambia di stato
- this.periodiGiaOccupatiDaNoleggi non ci saranno accavallamenti di date
`void riceviNotificaDiProgrammazioneNoleggio(Noleggio noleggio)`

--- 

pre:
- il parametro noleggio non deve essere NULL
post:
- this.stazioneAttuale cambierà stato
`void riceviNotificaDiInizioNoleggio(Noleggio noleggio)`

---

pre:
- il parametro noleggio non deve essere NULL
post:
- this.periodiGiaOccupatiDaNoleggi cambia di stato
- this.periodiGiaOccupatiDaNoleggi non ci saranno accavallamenti di date
`void riceviNotificaDiProlungamentoNoleggio(Noleggio noleggio)`

--- 


pre:
- il parametro noleggio non deve essere NULL
post:
- this.stazioneAttuale cambierà stato
- this.kmPercorsi cambierà di stato
- this.oreUtilizzo cambierà di stato
- this.periodiGiaOccuapatiDaNoleggi cambierà di stato ed eliminerà il range di date relativo al noleggio appena concluso
`void riceviNotificaDiTerminazioneNoleggio(Noleggio noleggio)`

## Noleggio
### Attributi
int: id
Veicolo: veicoloNoleggiato
Utente: cliente
Stazione: stazionePartenza
Stazione: stazioneArrivo
LocalDate: dataPartenza
LocalDate: dataArrivoPrevista
LocalDate: dataArrivoEffettiva
### Metodi

pre:
- dataArrivoEffettiva deve essere >= di this.dataArrivoPrevista
post:
- notificheremo la stazione di arrivo di ospitare il veicolo del noleggio
- notificheremo il veicolo stesso di avere percorso un numero di chilometri pari a kmPercorsiDuranteNoleggio
`void completaNoleggio(dataArrivoEffettiva, kmPercorsiDurantellNoleggio)`

---

post: 
- la stazione riceverà questa notifica e modificherà il suo stato
`void notificaLaStazioneDelCompletamentoDelNoleggio()`

---

pre: 
- this.dataArrivoEffettiva e this.dataPartenza siano non NULL
post:
- il veicolo del noleggio riceverà questa notifica e modificherà il suo stato 
`void notificaVeicoloDelCompletamentoNoleggio(int kmPercorsiDuranteIlNoleggio, Noleggio noleggio)`

---

post:
- notifichiamo la Stazione del fatto che il Veicolo è stato prese e quindi dovrà modificare il suo stato
`void notificaLaStazioneDeInizioDelNoleggio()`

--- 

post:
- notifichiamo il Veicolo del fatto che il Veicolo è stato prese e quindi dovrà modificare il suo stato
`void notificaVeicoloDeInizioDelNoleggio()`

---

post: 
- notifichiamo il Veicolo del fatto che il Veicolo verrà noleggiato in un certo range di date e quindi dovrà modificare il suo stato
`void notificaVeicoloDellaProgrammazioneDelNoleggio()`

--- 

post: 
- notifichiamo il Veicolo del fatto che il Veicolo verrà noleggiato più a lungo quindi dovrà modificare il suo stato
`void notificaIlVeicoloDelProlungamentoDelNoleggio()`
