package AggregatStrategyFactory;

import satellite.CollectConfigInformation;

import java.lang.reflect.InvocationTargetException;

/**
 * In der Faktory wird das Configfile gelesen. Die Aggregat Information wird verwendet.
 * es wird eine Instanz der Aggregatklasse initialisiert vom typ Strategy. Diese Instanz hat die Methode
 * executeAlgorithm() implementiert.
 */
public class AggregatStrategyFactory
{
    /** gibt die passende Strategy für das Aggregat in der Config zurück.
     * @return Die in der Config angegebene Strategy
     */
    public AggregatStrategy getStrategy()
    {
        CollectConfigInformation information = new CollectConfigInformation();

        try
        {
            return (AggregatStrategy) Class.forName(information.getAggregat()).getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException
                | ClassNotFoundException | InvocationTargetException | NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
