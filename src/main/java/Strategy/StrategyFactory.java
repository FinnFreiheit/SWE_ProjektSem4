package Strategy;

import org.dhbw.freiheit.satellite.CollectConfigInformation;

public class StrategyFactory
{
    /**
     * Alle konkrete Strategy-Algorithmen müssen hier instaziert werden. (Aggregate)
     */
    private final Strategy aggregat1 = new DeutscheTVSender();

    public Strategy getStrategy()
    {
        /**
         * Wenn it Reflection:
         * Strategy strategy = (Strategy)Class.forName(information.Aggregat().newInstance();
         * return strategy
         *
         * Ist besser mit Reflection, dann muss die Factory nie wieder bearbeitet werden.
         * http://www.codeadventurer.de/?p=3390
         */
        CollectConfigInformation information = new CollectConfigInformation();
        information.creatConfigValues();
        if (information.getAggregat().equals("aggregat1"))
        {
            return aggregat1;
        }
        return null;
    }
}
