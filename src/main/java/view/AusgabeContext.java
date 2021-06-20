package view;


import model.Satellite;

import java.util.List;
import java.util.Map;

public class AusgabeContext
{
    private final AusgabeStrategyFactory ausgabeStrategyFactory = new AusgabeStrategyFactory();
    private final AusgabeStrategy ausgabeStrategy = ausgabeStrategyFactory.getStrategy();

    public void MapAusgabe(Map<String, List<String>> sortedList)
    {
         ausgabeStrategy.outputMap(sortedList);
    }
}
