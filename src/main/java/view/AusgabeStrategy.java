package view;

import model.Satellite;

import java.util.List;
import java.util.Map;

/**
 * Gekapseltes Ausgabeverhalten. Für jede Ausgabe gibt eine eigene Klasse, die dieses Interface implementiert.
 */
public interface AusgabeStrategy
{
    public void outputMap(Map<Satellite, List<Satellite.Channel>> sortedList);
}
