package view;

import model.CollectConfigInformation;

import java.lang.reflect.InvocationTargetException;

/**
 * In der Faktory wird das Configfile gelesen. Die Output Information wird verwendet.
 * es wird eine Instanz der Outputklasse initialisiert vom typ AusgabeStrategy. Diese Instanz hat die Methode
 * outputMap() implementiert.
 */
public class AusgabeStrategyFactory {
    public AusgabeStrategy getStrategy() {
        CollectConfigInformation information = new CollectConfigInformation();

        try {
            return (AusgabeStrategy) Class.forName(information.getOutput()).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
