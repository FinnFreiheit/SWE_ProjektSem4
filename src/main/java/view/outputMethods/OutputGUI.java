package view.outputMethods;


import model.Satellite;
import view.AusgabeStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class OutputGUI extends JFrame implements AusgabeStrategy
{
    String[] columnNames;
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
    public void outputMap(Map<String, List<String>> sortedList)
    {
        JTextArea text = new JTextArea(generateString(sortedList));
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

    public String generateString(Map<String, List<String>> stringListMap)
    {
        StringBuilder mainString = new StringBuilder();

        for(Map.Entry<String, List<String>> entry : stringListMap.entrySet())
        {
            String stringMod = entry.getKey().replace("|", ": ");
            mainString.append(stringMod.replace(",",", "));
            mainString.append("\n");
            for( String string : entry.getValue())
            {
                String stringModChannel = string.replace("|",": ");
                mainString.append("---\t").append(stringModChannel.replace(",",", "));
                mainString.append("\n");
            }
        }

        return mainString.toString();
    }



}
