package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapFrame
{
    public Map<Satellite, List<Satellite.Channel>> map;
    public Map<String, List<String>> stringMap;

    public MapFrame(Map<Satellite, List<Satellite.Channel>> map)
    {
        this.map = map;
        this.stringMap = this.getStringMap();
    }

    public Map<String, List<String>> getStringMap()
    {
        Map<String, List<String>> mapString = new TreeMap<>();
        for(Map.Entry<Satellite,List<Satellite.Channel>> entry : this.map.entrySet())
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
