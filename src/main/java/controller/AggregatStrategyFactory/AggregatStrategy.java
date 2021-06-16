package controller.AggregatStrategyFactory;

import model.Satellite;

import java.util.List;
import java.util.Map;

/**
 * Gekapseltes Sortierverhalten. Für jede Sortierung eine eigene Klasse, die dieses Interface implementiert.
 *
 * @see controller/AggregatStrategyFactory/Aggregate/DeutscheTVSender.java
 */
public interface AggregatStrategy
{
    public Map<Satellite, List<Satellite.Channel>> executeAlgorithm();
}
