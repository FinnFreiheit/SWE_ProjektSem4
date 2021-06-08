package AggregatStrategyFactory;

import satellite.Satellite;

import java.util.List;
import java.util.Map;

/*
* Implementierung des Sortierung, zum Beispiel alle Deutschen TV Sender bestimmten.
* Ergebnis wird in eine Map gespeichert */
public class DeutscheTVSender implements AggregatStrategy
{
    @Override
    public Map<Satellite.Channel, List<Satellite>> executeAlgorithm()
    {
        //Test
        System.out.println("Moin");
        return null;
    }
}
