package controller.AggregatStrategyFactory.Aggregate;

import controller.AggregatStrategyFactory.AggregatStrategy;
import controller.AggregatStrategyFactory.SuperAggregat;
import model.MapFrame;
import model.Satellite;

import java.util.List;
import java.util.Map;

/**
* Implementierung des Aggregierens von Radio Sendern von Transponern eines Satelliten.
* Das Ergebnis wird in eine Map gespeichert
 */
public class TransponderAnzahlSender extends SuperAggregat implements AggregatStrategy
{

    public TransponderAnzahlSender()
    {
        super();
    }

    /**
     * Legt das zweite zu filternde Attribute fest und gibt die
     * gefilterte Map zurück
     *
     * @return gefilterte Satelliten Map mit Radio Sendern von Transponern eines Satelliten
     */
    @Override
    public MapFrame executeAlgorithm()
    {
        firstFilter("sat", new String[]{"ABS-2A"});
        return new MapFrame(getSatelliteProperty().getSatelliteMap(), "getCount");
    }
}
