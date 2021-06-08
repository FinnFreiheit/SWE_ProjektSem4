package AggregatStrategyFactory;

public class AggregatContext
{

    private final AggregatStrategyFactory aggregatStrategyFactory = new AggregatStrategyFactory();
    private AggregatStrategy aggregatStrategy = aggregatStrategyFactory.getStrategy();

    public void aggregatAnwenden(){
        aggregatStrategy.executeAlgorithm();
    }

    public void setStrategy(AggregatStrategy sortierMethode){
        this.aggregatStrategy = sortierMethode;
    }
}

/*
* Context kann auch eine abstrakte Klasse sein.
 */