package AggregatStrategyFactory;

import satellite.ChannelsProperty;
import satellite.CollectConfigInformation;
import satellite.CollectSatellitesAndChannelsInformation;
import satellite.Satellite;

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
    public Map<Satellite.Channel, List<Satellite>> executeAlgorithm()
    {
        CollectSatellitesAndChannelsInformation satellitesAndChannelsInformation = getSatellitesAndChannelsInfo();

        String firstPropertyLanguage = "language";
        String[] firstValuesGerman = { "deutsch" };
        ChannelsProperty firstChannelsProperty = new ChannelsProperty(firstPropertyLanguage, firstValuesGerman,
                                                  satellitesAndChannelsInformation);

        Map<Satellite.Channel, List<Satellite>> filteredSatellitesAndChannelsInfo = firstChannelsProperty.getChannelMap();
        String secondPropertyType = "type";
        String[] secondValuesTv = { "TV" };
        ChannelsProperty secondChannelsProperty = new ChannelsProperty(filteredSatellitesAndChannelsInfo,secondPropertyType,
                                                    secondValuesTv,satellitesAndChannelsInformation);
        secondChannelsProperty.print();

        return secondChannelsProperty.getChannelMap();
    }
}
