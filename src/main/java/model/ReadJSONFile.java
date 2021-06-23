package model;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONFile {

	/**
	 * erzeugt ein JSONArray aus allen Satelliten
	 * die Datei file (model.satellite.json) wird eingelesen
	 * Array aus allen Satelliten erzeugt, wobei ein Satellite folgende Form hat:
	 * {"channels":[{"res":"SD","package":" ","name":"Hope Channel Fiji","v_pid":"3005","enc":"Verimatrix ","type":"TV","compression":"MPEG-4","url":"","a_pid":"3006  eng","sid":"1510"},
	 * {"res":"SD","package":" ","name":"Channel 2 (Fiji)","v_pid":"2101","enc":"Verimatrix ","type":"TV","compression":"MPEG-4","url":"","a_pid":"3001  ","sid":"2405"}]
	 * ,"sym":"7500","orbital":"172° E","sat":"Eutelsat 172B","freq":"3952","pol":"H"}
	 * 
	 * @param file : die JSON-Datei, die alle Satelliten enthält
	 * @return JSONArray aller Satelliten
	 */
    public static JSONArray createJSONArrayListForAllSatellites(String file) 
    {
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(file))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray temp = (JSONArray) obj;
            return (JSONArray) obj;
 
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Erzeugt eine JSON-Array-List aus den Daten, welche in der Konfig-File stehen.
     *
     * @param file Pfad zur Config-File
     * @return null
     */
    public static JSONArray createJSONArrayListFromConfig(String file)
    {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(file))
        {
            Object obj = jsonParser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    
}