package controller.AggregatStrategyFactory.Aggregate;

import controller.AggregatStrategyFactory.AggregatStrategy;
import controller.AggregatStrategyFactory.SuperAggregat;
import model.CollectSatellitesAndChannelsInformation;
import model.Satellite;
import model.SatelliteProperty;
import model.*;

import java.util.List;
import java.util.Map;

/*
* Implementierung des Sortierung, zum Beispiel alle Deutschen TV Sender bestimmten.
* Ergebnis wird in eine Map gespeichert */
public class DeutscheTVSender  extends SuperAggregat implements AggregatStrategy
{
    public DeutscheTVSender()
    {
        super("language", new String[]{"deutsch"});
    }

    @Override
    public Map<Satellite, List<Satellite.Channel>> executeAlgorithm()
    {
        filter("type",new String[]{"TV"});
        return getSatelliteProperty().getSatelliteMap();
    }
}
