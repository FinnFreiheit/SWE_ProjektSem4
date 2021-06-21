package view.outputMethods;



import model.Satellite;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import view.AusgabeStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
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
        JSONArray jsonArraySat = new JSONArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for(Map.Entry<String, List<String>> entry : sortedList.entrySet()){
           StringBuilder satString = jsonString(entry.getKey());
           satString.append(",\"channel\":[");
           for(String channel : entry.getValue()){
               String channelSB = jsonString(channel) +
                       "},";
               satString.append(channelSB);
           }
           satString.setLength(satString.length()-1);
           satString.append("]},");
           stringBuilder.append(satString);
        }
        stringBuilder.setLength(stringBuilder.length()-1);
        stringBuilder.append("]");
        try {
            file = new FileWriter("resources/sortedSatellites.json");
            file.write(String.valueOf(stringBuilder));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public StringBuilder jsonString(String toJsonString){
        StringBuilder stringbuilder = new StringBuilder("{\"");
        stringbuilder.append(toJsonString);
        stringbuilder = new StringBuilder(stringbuilder.toString().replace("|" , "\":\"")
                .replace(",","\",\""));
        stringbuilder.append("\"");
        return stringbuilder;
    }

    /**
     * Wandelt die in der Map gespeicherten Informationen in einen JSON kompatiblen String um.
     *
     * @return JSON-String der in der Map enthaltenen Informationen
     */
    public JSONArray maptoString()
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

