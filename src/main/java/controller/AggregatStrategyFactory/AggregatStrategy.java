package controller.AggregatStrategyFactory;


import model.Satellite;

import java.util.List;
import java.util.Map;

/**
 * Gekapseltes Sortierverhalten. Für jede Sortierung eine eigene Klasse, die dieses Interface implementiert.
 *
 */
public interface AggregatStrategy {
    /**
     * Die Methode implementiert das spezielle Sortierverfahren.
     *
     * @return Das Ergebnis der Sortirung wird in eine Map: key=String, value=String Liste umgewandelt.
     * bei der Umwandlung kann die getStringMap() Methode aufgerufen werden.
     */
    Map<String, List<String>> executeAlgorithm();

    /**
     * Der Nutzer implementiert eine Sortiermethode. Das Ergebnis der Sortierung befindet sich in einer Satelliten und
     * Channel Map. Diese Map, muss in ein (Sting: String Liste) Map umgewandelt werden.
     * Die Strings müssen folgende Struktur aufweisen. key:value,key:value,....,key:value
     * Die Struktur ist in der toString Methode der Satellite und Channel Klasse eingehlaten.
     *
     * @param map die umzuwandelde Map
     * @return eine Map: key=String, value=Sting Liste.
     */
    Map<String, List<String>> getStringMap(Map<Satellite, List<Satellite.Channel>> map);
}
