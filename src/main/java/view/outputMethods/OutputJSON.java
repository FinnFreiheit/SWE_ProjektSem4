package view.outputMethods;

import model.Satellite;
import org.json.simple.JSONObject;
import view.AusgabeStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OutputJSON implements AusgabeStrategy
{
    JSONObject sortedListJSON;
    private static FileWriter file;



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

    public String maptoString(Map<Satellite, List<Satellite.Channel>> sortedList)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for(Map.Entry<Satellite, List<Satellite.Channel>> entry : sortedList.entrySet())
        {
            Satellite sat = entry.getKey();
            stringBuilder.append(sat.toString());
            stringBuilder.append("[");
            for(Satellite.Channel channel: entry.getValue())
            {
                stringBuilder.append(channel.toString());
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append("]");
            stringBuilder.append("\n\t},");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
