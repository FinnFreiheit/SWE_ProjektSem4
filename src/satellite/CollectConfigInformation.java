package org.dhbw.freiheit.satellite;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CollectConfigInformation
{
    private String satellitesPath;
    private String aggregat;
    private String output;
    private final JSONArray configJSONArray;


    public CollectConfigInformation()
    {
        this.configJSONArray = ReadJSONFile.createJSONArrayListFromConfig("resources/config.json");
    }

    public void creatConfigValues()
    {
        JSONObject configInfo = (JSONObject) this.configJSONArray.get(0);
        this.satellitesPath = (String)configInfo.get("satellitesPath");
        this.aggregat = (String)configInfo.get("aggregat");
        this.output = (String)configInfo.get("output");
    }

    public String getSatellitesPath()
    {
        return satellitesPath;
    }

    public String getAggregat()
    {
        return aggregat;
    }

    public String getOutput()
    {
        return output;
    }
}
