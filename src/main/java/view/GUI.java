package view;

import model.Satellite;

import javax.swing.*;
import java.util.List;
import java.util.Map;


public class GUI extends JFrame {
    JPanel jPanel = new JPanel();
    Map<Satellite, List<Satellite.Channel>> satelliteListMap;

    public GUI(Map<Satellite, List<Satellite.Channel>> SatelliteListMap) {
        this.satelliteListMap = SatelliteListMap;
        setSize(600, 800);
        setVisible(true);
        setTitle("Satelliten");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setjPanel();
    }

    public void setjPanel() {
        JLabel jLabel = new JLabel();
        //jLabel.setText();
    }

    public String MapToString(){
        StringBuilder returnString = new StringBuilder();


        for(Map.Entry<Satellite, List<Satellite.Channel>> entry : this.satelliteListMap.entrySet())
        {
            Satellite satellite = entry.getKey();
            satellite.print(entry.getValue());
        }
        return "Hi";
    }


}
