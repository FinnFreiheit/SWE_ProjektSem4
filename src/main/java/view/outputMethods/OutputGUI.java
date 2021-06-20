package view.outputMethods;

import model.MapFrame;
import model.Satellite;
import view.AusgabeStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class OutputGUI extends JFrame implements AusgabeStrategy
{
    String[] columnNames;
    Object[][] arr;
    Object[][] array;

    JPanel panel;
    JScrollPane scrollPane;

    public OutputGUI()
    {

        columnNames = new String[]{"Satellite", "Orbital", "Freq", "Sym", "Pol"};
        setTitle("Ausgabe JSON");

        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new BoxLayout(panel,1));

    }

    @Override
    public void outputMap(MapFrame sortedList)
    {
        JTextArea text = new JTextArea(generateString(sortedList.map));
        text.setEditable(false);
        scrollPane = new JScrollPane(text);

        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                panel.add(scrollPane);
                add(panel);
                pack();
                setExtendedState(JFrame.MAXIMIZED_HORIZ);
                setVisible(true);
            }
        });
    }

    public String generateString(Map<Satellite, List<Satellite.Channel>> map)
    {
        StringBuilder mainString = new StringBuilder();

        for(Map.Entry<Satellite, List<Satellite.Channel>> entry : map.entrySet())
        {
            mainString.append(entry.getKey().toString());
            mainString.append("\n");
            for(Satellite.Channel channel: entry.getValue())
            {
               mainString.append("---\t" + channel.toString());
               mainString.append("\n");
            }
        }

        return mainString.toString();
    }


}
