import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;


// questa classe la fa Michele Ongaro
public class Calcolatore {
    private static final double SOVRAPPREZZO_EXTRA_ORARIO = 1.30;

    public double calcolaCostoTotaleDiUnNoleggio(Noleggio noleggio) {
        if (noleggio.getDataArrivoEffettiva() == null) {
            throw new IllegalArgumentException("La data di arrivo effettiva non può essere null");
        }

        long oreEffettive = ChronoUnit.HOURS.between(
                noleggio.getDataPartenza().atStartOfDay(), 
                noleggio.getDataArrivoEffettiva().atStartOfDay()
        );

        long orePreviste = ChronoUnit.HOURS.between(
                noleggio.getDataPartenza().atStartOfDay(), 
                noleggio.getDataArrivoPrevista().atStartOfDay()
        );

        double tariffaBase = noleggio.getVeicoloNoleggiato().getTariffaOraria() * orePreviste;
        double costoTotale = tariffaBase;

        if (oreEffettive > orePreviste) {
            long oreExtra = oreEffettive - orePreviste;
            costoTotale += oreExtra * noleggio.getVeicoloNoleggiato().getTariffaOraria() * SOVRAPPREZZO_EXTRA_ORARIO;
        }

        return Math.max(0, costoTotale);
    }

    public Period calcolaDurataNoleggio(Noleggio noleggio) {
        if (noleggio.getDataPartenza() == null || 
            noleggio.getDataArrivoEffettiva() == null || 
            noleggio.getDataArrivoPrevista() == null ||
            noleggio.getVeicoloNoleggiato() == null || 
            noleggio.getCliente() == null) {
            throw new IllegalArgumentException("Tutti gli attributi del noleggio devono essere definiti");
        }

        return Period.between(noleggio.getDataPartenza(), noleggio.getDataArrivoEffettiva());
    }
}

