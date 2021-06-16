package view;

import model.CollectConfigInformation;

import java.lang.reflect.InvocationTargetException;

public class AusgabeStrategyFactory
{
    public AusgabeStrategy getStrategy()
    {
        CollectConfigInformation information = new CollectConfigInformation();

        try
        {
            return (AusgabeStrategy) Class.forName(information.getOutput()).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
