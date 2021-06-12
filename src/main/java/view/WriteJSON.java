package view;

import model.Satellite;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WriteJSON
{
    Map<Satellite, List<Satellite.Channel>> sortedList;
    JSONObject sortedListJSON;
    private static FileWriter file;

    public WriteJSON(Map<Satellite, List<Satellite.Channel>> sortedList)
    {
        this.sortedList = sortedList;
        this.sortedListJSON = new JSONObject(this.sortedList);
    }

    public void writeToFile()
    {
        System.out.println(sortedListJSON);
        try
        {
            file = new FileWriter("resources/temp.json");
            file.write(sortedListJSON.toJSONString());

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }



}
