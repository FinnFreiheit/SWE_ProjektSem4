package view.outputMethods;

import model.Satellite;
import org.json.simple.JSONObject;
import view.AusgabeStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Implementierung der Ausgabe als JSON File.
 */
public class OutputJSON implements AusgabeStrategy
{
    JSONObject sortedListJSON;
    private static FileWriter file;


    /**
     * Schreibt die gefilterte Map in eine JSON Datei
     *
     * @param sortedList sortierte Map mit allen gefilterten Satelliten und Channels
     */
    @Override
    public void outputMap(Map<Satellite, List<Satellite.Channel>> sortedList)
    {
       try
       {
           file = new FileWriter("resources/sortedSatellites.json");
           file.write(maptoString(sortedList));
       } catch (IOException e)
       {
           e.printStackTrace();
       }
    }

    /**
     * Wandelt die in der Map gespeicherten Informationen in einen JSON kompatiblen String um.
     *
     * @param sortedList sortierte Map mit allen gefilterten Satelliten und Channels
     * @return JSON-String der in der Map enthaltenen Informationen
     */
    public String maptoString(Map<Satellite, List<Satellite.Channel>> sortedList)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for(Map.Entry<Satellite, List<Satellite.Channel>> entry : sortedList.entrySet())
        {
            Satellite sat = entry.getKey();
            stringBuilder.append(sat.toJSONString());
            stringBuilder.append("[");
            for(Satellite.Channel channel: entry.getValue())
            {
                stringBuilder.append(channel.toJSONString());
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append("]");
            stringBuilder.append("\n\t},");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
