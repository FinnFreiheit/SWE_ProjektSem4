package satellite;

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
	 * die Datei file (satellite.json) wird eingelesen
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
            for (Object o : temp)
            {
                System.out.println(o);
            }
            return (JSONArray) obj;
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static JSONArray createJSONArrayListFromConfig(String file)
    {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(file))
        {
            Object obj = jsonParser.parse(reader);
            return (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    // -------------------------- Konsolenausgabe unsortiert - alle Satelliten -------------------
    
    /**
     * gibt das JSONArray aller Satelliten auf die Konsole aus
     * ruft für jeden Satelliten printOneSatelliteOnConsole() auf, 
     * um dessen Eigenschaften auszugeben
     * @param file : die JSON-Datei, die alle Satelliten enthält
     */
    public static void printAllSatellitesOnConsole(String file) 
    {

        JSONArray satelliteList = createJSONArrayListForAllSatellites(file);
        for(Object sat : satelliteList)
        {
        	JSONObject satJSON = (JSONObject)sat;
        	printOneSatelliteOnConsole(satJSON);
        }
    }
    
    /**
     * gibt die Eigenschaften eines einzelnen Satelliten auf die Konsole aus
     * ruft die Methode printOneChannelOnConsole() auf, um alle Channels eines
     * Satelliten auf die Konsole auszugeben
     * @param satellite : der einzelne Satellit, dessen Eigenschaften ausgegeben werden
     */
    private static void printOneSatelliteOnConsole(JSONObject satellite) 
    {
        String sat = (String) satellite.get("sat");   
        String pol = (String) satellite.get("pol");  
        String orbital = (String) satellite.get("orbital");   
        String freq = (String) satellite.get("freq");  
        String sym = (String) satellite.get("sym"); 
        System.out.printf("%n%-14s %-10s %-6s %-8s %-8s %n", sat, orbital, pol, freq, sym);
        
        JSONArray channelArray = (JSONArray) satellite.get("channels");
        for(Object channel : channelArray)
        {
        	JSONObject channelJSON = (JSONObject)channel;
        	printOneChannelOnConsole(channelJSON);
        }
    }
    
    /**
     * gibt die Eigenschaft eines einzelnen Channels auf die Konsole aus
     * @param channel : der einzelne Channel, dessen Eigenschaften ausgegeben werden
     */
    private static void printOneChannelOnConsole(JSONObject channel) 
    {
        String type = (String) channel.get("type");   
        String a_pid = (String) channel.get("a_pid");  
        String sid = (String) channel.get("sid");   
        String v_pid = (String) channel.get("v_pid");  
        String compression = (String) channel.get("compression"); 
        String url = (String) channel.get("url");   
        String enc = (String) channel.get("enc");  
        String name = (String) channel.get("name");   
        String res = (String) channel.get("res");  
        String pckge = (String) channel.get("package");
        System.out.printf("---------- %-22s %-8s %-8s %-8s %n", name, compression, enc, a_pid);
    }
 
    // -------------------------- Object[][]-Erzeugung unsortiert - alle Satelliten (fuer Table) -------------------
    
    /**
     * erzeugt aus dem JSONArray aller Satelliten ein data-Object[][]
     * wird für die Tabelle in der GUI verwendet - siehe SatelliteTable
     * @param file : die JSON-Datei, die alle Satelliten enthält
     * @return Object[][] aller Satelliten
     */
    public static Object[][] createDataArrayForAllSatellites(String file) 
    {
    		JSONArray satelliteList = createJSONArrayListForAllSatellites(file);
            
            Object[][] data = new Object[satelliteList.size()][5];
            for(int i=0; i<satelliteList.size(); i++)
            {
            	JSONObject sat = (JSONObject)satelliteList.get(i);
            	data[i] = createDataArrayForOneSatellite(sat);
            }
            
           return data;
    }
    
    /**
     * erzeugt ein einfaches data-Object[] für einen Satelliten
     * wird für die Tabelle in der GUI verwendet - siehe SatelliteTable
     * @param satellite : der einzelne Satellit, für den das data-Array erzeugt wird
     * @return Object[] aller Eigenschaften eines Satelliten (ausser "channels")
     */
    private static Object[] createDataArrayForOneSatellite(JSONObject satellite) 
    {
    	Object[] data = new Object[satellite.size()-1]; // -1 weil channels ignoriert wird
    	
        data[0] =  satellite.get("sat"); 
        data[1] =  satellite.get("orbital");
        data[2] =  satellite.get("freq");  
        data[3] =  satellite.get("sym"); 
        data[4] =  satellite.get("pol");
        
        // satellite.get("channels") nicht beachtet (selbst ein Array)
        
        return data;
    }
    
}