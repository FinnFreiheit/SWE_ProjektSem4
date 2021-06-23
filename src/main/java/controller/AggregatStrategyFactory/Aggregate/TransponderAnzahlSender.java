package controller.AggregatStrategyFactory.Aggregate;

import controller.AggregatStrategyFactory.AggregatStrategy;
import controller.AggregatStrategyFactory.SuperAggregat;

import model.Satellite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
* Implementierung des Aggregierens von Radio Sendern von Transpondern eines Satelliten.
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
     * @return gefilterte Satelliten Map mit Radio Sendern von Transpondern eines Satelliten
     */
    @Override
    public Map<String, List<String>> executeAlgorithm()
    {
        firstFilter("sat", new String[]{"ABS-2A"});
        return getStringMap(getSatelliteProperty().getSatelliteMap());
    }

    /**
     * wandelt eine Map<Satellite, List<Satellite.Channel>> in eine Map<String, List<String>> mit Strings
     * in Format key:value um
     *
     * @param map die umzuwandelnde Map
     * @return StringMap
     */
    @Override
    public Map<String, List<String>> getStringMap(Map<Satellite, List<Satellite.Channel>> map)
    {
        Map<String, List<String>> mapChannelCount = new TreeMap<>();
        for (Map.Entry<Satellite, List<Satellite.Channel>> entry : map.entrySet()) {
            Satellite satellite = entry.getKey();
            String satelliteString = satellite.toString();
            List<String> channelCountString = new ArrayList<>();
            int tvChannelCount = 0;
            int radioChannelCount = 0;
            for(Satellite.Channel channel : entry.getValue())
            {
                if (channel.get("type").toLowerCase().contains("R".toLowerCase())){
                    radioChannelCount += 1;
                }
                else if (channel.get("type").toLowerCase().contains("TV".toLowerCase())){
                    tvChannelCount += 1;
                }
            }
            channelCountString.add("Radiosender|"+ radioChannelCount);
            channelCountString.add("Fehrnsehsender|" + tvChannelCount);

            mapChannelCount.put(satelliteString, channelCountString);
        }
        return mapChannelCount;
    }
}
