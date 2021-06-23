package view;


import java.util.List;
import java.util.Map;
/**
 * Die Klasse ruft die Factory auf und instanziiert
 * das Factory Objekt und führt die Strategie aus
 */
public class AusgabeContext {
    private final AusgabeStrategyFactory ausgabeStrategyFactory = new AusgabeStrategyFactory();
    private final AusgabeStrategy ausgabeStrategy = ausgabeStrategyFactory.getStrategy();

    /**
     * Strategiemethode der Ausgabe wird ausgeführt
     *
     * @param sortedList Auszugebende Liste
     */
    public void MapAusgabe(Map<String, List<String>> sortedList) {
        ausgabeStrategy.outputMap(sortedList);
    }
}
