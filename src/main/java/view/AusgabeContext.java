package view;

import model.MapFrame;
import model.Satellite;

import java.util.List;
import java.util.Map;

public class AusgabeContext
{
    private final AusgabeStrategyFactory ausgabeStrategyFactory = new AusgabeStrategyFactory();
    private final AusgabeStrategy ausgabeStrategy = ausgabeStrategyFactory.getStrategy();

    public void MapAusgabe(MapFrame sortedList)
    {
         ausgabeStrategy.outputMap(sortedList);
    }
}
