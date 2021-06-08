package Strategy;

public class Context
{
    /**
     * Instanz der Faktory. Faktory ließt Configfile und bestimmt die richtige Strategy durch
     * getStrategy.
     * Besser wenn Verwendung von Reflection. Dann muss name in der Config idetentisch mit Klassenname
     * von Algorithmus. (name Config zB: DeutscheTVSender)
     */
    private final StrategyFactory strategyFactory = new StrategyFactory();
    private Strategy strategy = strategyFactory.getStrategy();

    public void aggregatAnwenden(){
        strategy.executeAlgorithm();
    }

    public void setStrategy(Strategy sortierMethode){
        this.strategy = sortierMethode;
    }
}

/*
* Context kann auch eine Abstrakte Klasse sein.
*
* Faktory um Strategy zuzuweisen */