package model;


import controller.AggregatStrategyFactory.AggregatContext;
import view.AusgabeContext;

/**
 * Ausgangspunkt der Applikation. Das Configfile wird eingelesen.
 * Die Datei satellites.json wird eingelesen.
 * Das in der Config-Datei angegebene Aggregat wird ausgeführt.
 * Die in der Config-Datei angegebene Ausgabe wird genutzt um das ergebnis der Aggregierung anzuzeigen.
 */
public class App {
    /**
     * Main-Methode der Applikation
     *
     * @param args keine Parameterübergabe vorgesehen.
     */
    public static void main(String[] args) {
        AggregatContext aggregatContext = new AggregatContext();
        AusgabeContext ausgabeContext = new AusgabeContext();
        ausgabeContext.MapAusgabe(aggregatContext.aggregatAnwenden());
    }
}
