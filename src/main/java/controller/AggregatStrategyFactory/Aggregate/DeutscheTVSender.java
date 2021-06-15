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
public class DeutscheTVSender extends SuperAggregat implements AggregatStrategy
{
    public DeutscheTVSender()
    {
        super();
    }

    @Override
    public Map<Satellite, List<Satellite.Channel>> executeAlgorithm()
    {
        CollectSatellitesAndChannelsInformation satellitesAndChannelsInformation = getSatellitesAndChannelsInfo();

        String firstPropertyLanguage = "language";
        String[] firstValuesGerman = { "deutsch" };
        SatelliteProperty firstSatelliteProperty = new SatelliteProperty(firstPropertyLanguage, firstValuesGerman,
                                                                         satellitesAndChannelsInformation);


        //firstSatelliteProperty.print();

        Map<Satellite, List<Satellite.Channel>> filteredSatellitesAndChannelsInfo = firstSatelliteProperty.getSatelliteMap();
        String secondPropertyType = "type";
        String[] secondValuesTv = { "TV" };
        SatelliteProperty secondChannelsProperty = new SatelliteProperty(filteredSatellitesAndChannelsInfo,
                                                                    secondPropertyType,
                                                    secondValuesTv,satellitesAndChannelsInformation);
        //secondChannelsProperty.print();
        return secondChannelsProperty.getSatelliteMap();
    }
}
