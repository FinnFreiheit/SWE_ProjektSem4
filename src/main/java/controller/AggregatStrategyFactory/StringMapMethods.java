package controller.AggregatStrategyFactory;

import model.Satellite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StringMapMethods
{
    public Map<Satellite, List<Satellite.Channel>> map;
    public Map<String, List<String>> stringMap;

    /* Konstruktor überladen mit einer anderen Map,
        getStringMap überladen mit der Map. Aber an sich alles scheiße.
     */
    public StringMapMethods(Map<Satellite, List<Satellite.Channel>> map)
    {
        this.map = map;
        this.stringMap = this.getStringMap(map);
    }

    public StringMapMethods(Map<Satellite, List<Satellite.Channel>> map, String type)
    {
        this.map = map;
        this.stringMap = this.getSatelliteMapToChannelCount(map);
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

    public Map<String, List<String>> getSatelliteMapToChannelCount(Map<Satellite, List<Satellite.Channel>> map)
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
            channelCountString.add(radioChannelCount + " Channel des Types Radio");
            channelCountString.add(tvChannelCount + " Channel des Types TV");

            mapChannelCount.put(satelliteString, channelCountString);
        }
        return mapChannelCount;
    }


}
