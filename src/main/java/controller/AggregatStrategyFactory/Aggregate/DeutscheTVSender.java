package controller.AggregatStrategyFactory.Aggregate;

import controller.AggregatStrategyFactory.AggregatStrategy;
import controller.AggregatStrategyFactory.SuperAggregat;
import model.CollectSatellitesAndChannelsInformation;
import model.Satellite;
import model.SatelliteProperty;
import model.*;

import java.util.List;
import java.util.Map;

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
    public Map<Satellite, List<Satellite.Channel>> executeAlgorithm()
    {
        firstFilter("language", new String[]{"deutsch"});
        filter("type",new String[]{"TV"});
        return getSatelliteProperty().getSatelliteMap();
    }
}
