package satellite;

import AggregatStrategyFactory.AggregatContext;

import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class App 
{
	public static final String JSON_FILE = "resources/satellites.json";
    
    private static void showSatelliteTable() {
        //Create and set up the window.
        JFrame frame = new JFrame("Satellites");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        SatelliteTable newContentPane = new SatelliteTable(ReadJSONFile.createDataArrayForAllSatellites(JSON_FILE));
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main( String[] args )
    {
    	final int DEBUG = 4;
    	
    	switch(DEBUG)
    	{
	    	case 1: 
		    	// alle Satelliten unsortiert auf die Konsole ausgeben
		        ReadJSONFile.printAllSatellitesOnConsole(JSON_FILE);
	        break;
	    	case 2:
		        // Tabelle in Fenster erzeugen
		        javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            	showSatelliteTable();
		            }
		        });
	        break;
	    	case 3:
		        // CollectInformation --> unterschiedliche Konsolenausgaben
		    	CollectSatellitesAndChannelsInformation ci = new CollectSatellitesAndChannelsInformation(JSON_FILE);
		    	ci.printSetOfAllSatellites();
		    	// ci.printSetOfAllChannels();
		    	ci.printSetOfAllChannelsByLanguage("ger");
		    	ci.printSetOfAllTVChannelsByLanguage("ger");
		    	ci.printSatelliteArray();
		    	ci.printSatelliteNameChannelsMap();
		    	ci.printChannelSatellitesMap();
		    	ci.printSetOfAllChannelAPids();
	    	break;
	    	case 4:
	    		CollectSatellitesAndChannelsInformation ci1 = new CollectSatellitesAndChannelsInformation(JSON_FILE);
		    	String property1 = "language";
		    	String[] values1 = { "deutsch" };
		    	ChannelsProperty cp1 = new ChannelsProperty(property1, values1, ci1);
		    	cp1.print();
		    	Map<Satellite.Channel, List<Satellite>> map1 = cp1.getChannelMap();
		    	String property2 = "type";
		    	String[] values2 = { "R" };
		    	ChannelsProperty cp2 = new ChannelsProperty(map1, property2, values2, ci1);
		    	cp2.print();
		    	
	    	break;
			case 5:
				CollectConfigInformation temp = new CollectConfigInformation();
				break;
			case 6:
				AggregatContext foo = new AggregatContext();
				foo.aggregatAnwenden();
				break;
    	}
    }
}
