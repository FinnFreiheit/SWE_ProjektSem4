package model;

import java.util.List;
import java.util.Map;

public class MapFrame
{
    public Map<Satellite, List<Satellite.Channel>> map;

    public MapFrame(Map<Satellite, List<Satellite.Channel>> map)
    {
        this.map = map;
    }
}
