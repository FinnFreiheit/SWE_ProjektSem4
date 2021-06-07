package Strategy;

import org.dhbw.freiheit.satellite.Satellite;

import java.util.List;
import java.util.Map;

/*
* Gekapseltes Sortierverhalten. Für jede Sortierung eine eigene Klasse, die dieses Interface implementiert.
* siehe DeutscheTVSender.
* */
public interface Strategy
{
    public Map<Satellite.Channel, List<Satellite>> executeAlgorithm();
}
