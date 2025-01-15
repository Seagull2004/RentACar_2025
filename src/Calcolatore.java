import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;


// questa classe la fa Michele Ongaro
public class Calcolatore {
    private static final double SOVRAPPREZZO_EXTRA_ORARIO = 1.30;

    public double calcolaCostoTotaleDiUnNoleggio(Noleggio noleggio) {
        if (noleggio.dataArrivoEffettiva == null) {
            throw new IllegalArgumentException("La data di arrivo effettiva non può essere null");
        }

        long oreEffettive = ChronoUnit.HOURS.between(
                noleggio.dataPartenza.atStartOfDay(), 
                noleggio.dataArrivoEffettiva.atStartOfDay()
        );

        long orePreviste = ChronoUnit.HOURS.between(
                noleggio.dataPartenza.atStartOfDay(), 
                noleggio.dataArrivoPrevista.atStartOfDay()
        );

        double tariffaBase = noleggio.veicoloNoleggiato.tariffaOraria * orePreviste;
        double costoTotale = tariffaBase;

        if (oreEffettive > orePreviste) {
            long oreExtra = oreEffettive - orePreviste;
            costoTotale += oreExtra * noleggio.veicoloNoleggiato.tariffaOraria * SOVRAPPREZZO_EXTRA_ORARIO;
        }

        return Math.max(0, costoTotale);
    }

    public Period calcolaDurataNoleggio(Noleggio noleggio) {
        if (noleggio.dataPartenza == null || 
            noleggio.dataArrivoEffettiva == null || 
            noleggio.dataArrivoPrevista == null ||
            noleggio.veicoloNoleggiato == null || 
            noleggio.cliente == null) {
            throw new IllegalArgumentException("Tutti gli attributi del noleggio devono essere definiti");
        }

        return Period.between(noleggio.dataPartenza, noleggio.dataArrivoEffettiva);
    }
}

