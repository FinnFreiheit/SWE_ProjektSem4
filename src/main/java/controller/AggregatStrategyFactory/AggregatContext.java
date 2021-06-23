package controller.AggregatStrategyFactory;


import java.util.List;
import java.util.Map;

/**
 * Die Klasse ruft die Factory auf und instanziiert
 * das Factory Objekt und führt die Strategie aus
 */
public class AggregatContext {

    private final AggregatStrategyFactory aggregatStrategyFactory = new AggregatStrategyFactory();
    private AggregatStrategy aggregatStrategy = aggregatStrategyFactory.getStrategy();

    /**
     * Strategiemethode des Aggregats wird ausgeführt
     *
     * @return the map
     */
    public Map<String, List<String>> aggregatAnwenden() {
        return aggregatStrategy.executeAlgorithm();
    }

    /**
     * Set strategy.
     *
     * @param sortierMethode the sortier methode
     */
    public void setStrategy(AggregatStrategy sortierMethode) {
        this.aggregatStrategy = sortierMethode;
    }
}
