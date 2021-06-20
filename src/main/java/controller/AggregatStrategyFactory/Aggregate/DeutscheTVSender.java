package controller.AggregatStrategyFactory.Aggregate;

import controller.AggregatStrategyFactory.AggregatStrategy;
import controller.AggregatStrategyFactory.SuperAggregat;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
* Implementierung des Aggregierens aller Deutschen TV Sender.
* Das Ergebnis wird in eine Map gespeichert
 */
public class DeutscheTVSender  extends SuperAggregat  implements AggregatStrategy
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
    public Map<String, List<String>> executeAlgorithm()
    {
        firstFilter("language", new String[]{"deutsch"});
        filter("type",new String[]{"TV"});
        return getStringMap(getSatelliteProperty().getSatelliteMap());
    }

    /**
     * erzeugt eine  Map key: String, value: String List.
     *
     * @param map die umzuwandelnde Map
     * @return Map key: String, value: String List.
     */
    public Map<String, List<String>> getStringMap(Map<Satellite, List<Satellite.Channel>> map)
    {
        Map<String, List<String>> mapString = new TreeMap<>();
        for(Map.Entry<Satellite,List<Satellite.Channel>> entry : map.entrySet())
        {
            Satellite satellite = entry.getKey();
            String satelliteString = satellite.toString();
            List<String> channelsString = new ArrayList<>();
            for(Satellite.Channel channel : entry.getValue())
            {
                channelsString.add(channel.toString());
            }
            mapString.put(satelliteString,channelsString);
        }

        return mapString;
    }
}
