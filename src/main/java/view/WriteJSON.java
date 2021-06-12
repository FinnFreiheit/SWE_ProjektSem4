package view;

import model.Satellite;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WriteJSON
{
    Map<Satellite, List<Satellite.Channel>> sortedList;
    JSONObject sortedListJSON;
    private static FileWriter file;
    Set<Satellite> setSatellites;

    public WriteJSON(Map<Satellite, List<Satellite.Channel>> sortedList)
    {
        this.sortedList = sortedList;
        this.setSatellites = this.sortedList.keySet();
        //this.sortedListJSON = new JSONObject(setSatellites);
    }

    public void writeToFile()
    {
        System.out.println(sortedListJSON);
        try
        {
            file = new FileWriter("resources/temp.json");
            file.write(this.setSatellites.toString());

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }



}
