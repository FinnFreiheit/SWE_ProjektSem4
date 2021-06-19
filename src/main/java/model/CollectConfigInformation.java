package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Klasse, die die Eigenschaften aus der Config-File verwaltet
 */
public class CollectConfigInformation
{


    private String satellitesPath;
    private String aggregat;
    private String output;
    private final JSONArray configJSONArray;


    public CollectConfigInformation()
    {
        this.configJSONArray = ReadJSONFile.createJSONArrayListFromConfig("resources/config.json");
        JSONObject configInfo = (JSONObject) this.configJSONArray.get(0);
        this.satellitesPath = (String)configInfo.get("satellitesPath");
        this.aggregat = (String)configInfo.get("aggregat");
        this.output = (String)configInfo.get("output");
    }

    /**
     * Gibt den Pfad zur JSON, welche sie Infos über Satelliten und Channels beinhaltet zurück
     *
     * @return Pfad zur JSON
     */
    public String getSatellitesPath()
    {
        return this.satellitesPath;
    }

    /**
     * Gibt das anzuwendende Aggregat zurück
     *
     * @return Aggregat
     */
    public String getAggregat()
    {
        return aggregat;
    }

    /**
     * Gibt das gewünschte Output Format zurück
     *
     * @return Output Format
     */
    public String getOutput()
    {
        return output;
    }

    public void setSatellitesPath(String satellitesPath)
    {
        this.satellitesPath = satellitesPath;
    }
}