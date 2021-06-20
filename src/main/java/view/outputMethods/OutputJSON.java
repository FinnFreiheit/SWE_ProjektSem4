package view.outputMethods;



import model.Satellite;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import view.AusgabeStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;


/**
 * Implementierung der Ausgabe als JSON File.
 */
public class OutputJSON implements AusgabeStrategy {
    JSONObject sortedListJSON;
    private static FileWriter file;


    /**
     * Schreibt die gefilterte Map in eine JSON Datei
     *
     * @param sortedList sortierte Map mit allen gefilterten Satelliten und Channels
     */
    @Override
    public void outputMap(Map<String, List<String>> sortedList) {
        /*
      JSONArray js = maptoString(sortedList);

        try {

            file = new FileWriter("resources/sortedSatellites.json");
            file.write(String.valueOf(js));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
    }

    /**
     * Wandelt die in der Map gespeicherten Informationen in einen JSON kompatiblen String um.
     *
     * @param sortedList sortierte Map mit allen gefilterten Satelliten und Channels
     * @return JSON-String der in der Map enthaltenen Informationen
     */
    public JSONArray maptoString(Map<Satellite, List<Satellite.Channel>> sortedList)
    {
        /*
        JSONArray jsonArraySat = new JSONArray();
        for (Map.Entry<Satellite, List<Satellite.Channel>> entry : sortedList.entrySet()) {
            JSONObject satJson = new JSONObject();
            String[] satAttributs = entry.getKey().getAttributs();
            String[] satValues = entry.getKey().toStringArray();
            JSONArray jsonArrayChannel = new JSONArray();
            for (Satellite.Channel channel : entry.getValue()) {
                String[] channelsAttr = channel.getAttributs();
                String[] channelString = channel.toStringArray();
                JSONObject jsonChannel = new JSONObject();
                for (int index = 0; index < channelsAttr.length; index++) {
                    jsonChannel.put(channelsAttr[index], channelString[index]);
                }
                jsonArrayChannel.add(jsonChannel);
            }
            for (int index = 0; index < satAttributs.length; index++) {

                satJson.put(satAttributs[index], satValues[index]);
            }
            satJson.put("channels", jsonArrayChannel);
            jsonArraySat.add(satJson);
        }
        return jsonArraySat;
    }

         */
        //obacht
        return null;
    }
}

