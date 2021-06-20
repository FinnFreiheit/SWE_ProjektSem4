package controller.AggregatStrategyFactory;


import model.Satellite;

import java.util.List;
import java.util.Map;

/**
 * The type Aggregat context.
 */
public class AggregatContext
{

    private final AggregatStrategyFactory aggregatStrategyFactory = new AggregatStrategyFactory();
    private AggregatStrategy aggregatStrategy = aggregatStrategyFactory.getStrategy();

    /**
     * Aggregat anwenden map.
     *
     * @return the map
     */
    public Map<String, List<String>> aggregatAnwenden(){
        return aggregatStrategy.executeAlgorithm();
    }

    /**
     * Set strategy.
     *
     * @param sortierMethode the sortier methode
     */
    public void setStrategy(AggregatStrategy sortierMethode){
        this.aggregatStrategy = sortierMethode;
    }
}
