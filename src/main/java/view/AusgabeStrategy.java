package view;

import model.Satellite;

import java.util.List;
import java.util.Map;

public interface AusgabeStrategy
{
    public void outputMap(Map<Satellite, List<Satellite.Channel>> sortedList);
}
