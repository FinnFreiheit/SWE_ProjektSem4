package AggregatStrategyFactory;

import satellite.Satellite;

import java.util.List;
import java.util.Map;

/*
* Gekapseltes Sortierverhalten. Für jede Sortierung eine eigene Klasse, die dieses Interface implementiert.
* siehe DeutscheTVSender.
* */
public interface AggregatStrategy
{
    public Map<Satellite, List<Satellite.Channel>> executeAlgorithm();
}
