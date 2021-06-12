package controller.AggregatStrategyFactory;

import model.Satellite;

import java.util.List;
import java.util.Map;

public class AggregatContext
{

    private final AggregatStrategyFactory aggregatStrategyFactory = new AggregatStrategyFactory();
    private AggregatStrategy aggregatStrategy = aggregatStrategyFactory.getStrategy();

    public Map<Satellite, List<Satellite.Channel>> aggregatAnwenden(){
        return aggregatStrategy.executeAlgorithm();
    }

    public void setStrategy(AggregatStrategy sortierMethode){
        this.aggregatStrategy = sortierMethode;
    }
}

/*
* Context kann auch eine abstrakte Klasse sein.
 */