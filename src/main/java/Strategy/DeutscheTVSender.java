package Strategy;

import org.dhbw.freiheit.satellite.Satellite;

import java.util.List;
import java.util.Map;

/*
* Implementierung des Sortierung, zum Beispiel alle Deutschen TV Sender bestimmten.
* Ergebnis wird in eine Map gespeichert */
public class DeutscheTVSender implements Strategy
{
    @Override
    public Map<Satellite.Channel, List<Satellite>> executeAlgorithm()
    {
        return null;
    }
}
