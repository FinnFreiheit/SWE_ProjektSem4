package Strategy;

public class Context
{
    Strategy strategy = new DeutscheTVSender();

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