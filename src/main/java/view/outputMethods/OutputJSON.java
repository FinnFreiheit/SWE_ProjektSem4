package view.outputMethods;


import view.AusgabeStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Implementierung der Ausgabe als JSON File.
 */
public class OutputJSON implements AusgabeStrategy {
    /**
     * Schreibt die gefilterte Map in eine JSON Datei
     *
     * @param SortedList sortierte Map mit allen gefilterten Satelliten und Channels
     */
    @Override
    public void outputMap(Map<String, List<String>> SortedList) {
        StringBuilder stringBuilderJson = buildJson(SortedList);
        try {
            FileWriter file = new FileWriter("resources/sortedSatellites.json");
            file.write(String.valueOf(stringBuilderJson));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * wandelt einen String in ein JSON-Format um
     * @param toJsonString String der umgewandelt werden soll
     * @return String im JSON-Format
     */
    private StringBuilder stringToJson(String toJsonString) {
        StringBuilder stringbuilder = new StringBuilder("{\"");
        stringbuilder.append(toJsonString);
        stringbuilder = new StringBuilder(stringbuilder.toString().replace("|", "\":\"")
                .replace(",", "\",\"").replace("\u0009"," "));
        stringbuilder.append("\"");
        return stringbuilder;
    }

    /**
     * Erzeugt aus den Channels und den Satelliten eine JSON-Ausgabe
     * @param SortedList Map die in ein JSON-Format gebracht werden soll
     * @return String im JSON-Format als StringBuilder
     */
    private StringBuilder buildJson(Map<String, List<String>> SortedList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Map.Entry<String, List<String>> entry : SortedList.entrySet()) {
            StringBuilder satString = stringToJson(entry.getKey());
            satString.append(",\"channel\":[");
            for (String channel : entry.getValue()) {
                String channelSB = stringToJson(channel) +
                        "},";
                satString.append(channelSB);
            }
            satString.setLength(satString.length() - 1);
            satString.append("]},");
            stringBuilder.append(satString);
        }
        if(stringBuilder.charAt(stringBuilder.length() - 1) == ','){
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        stringBuilder.append("]");
        return stringBuilder;
    }
}

