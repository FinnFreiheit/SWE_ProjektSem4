package controller.AggregatStrategyFactory.Aggregate;

import controller.AggregatStrategyFactory.AggregatStrategy;
import controller.AggregatStrategyFactory.SuperAggregat;
import model.*;

/**
* Implementierung des Aggregierens aller Deutschen TV Sender.
* Das Ergebnis wird in eine Map gespeichert
 */
public class DeutscheTVSender  extends SuperAggregat implements AggregatStrategy
{

    public DeutscheTVSender()
    {
        super();
    }

    /**
     * Die Map wird nach den der Sprache: Deutsch und dem Typ: TV-Sender gefiltert.
     * @return gefilterte Satelliten Map mit deutschen TV-Sendern
     */
    @Override
    public MapFrame executeAlgorithm()
    {
        firstFilter("language", new String[]{"deutsch"});
        filter("type",new String[]{"TV"});
        return new MapFrame(getSatelliteProperty().getSatelliteMap());
    }
}
