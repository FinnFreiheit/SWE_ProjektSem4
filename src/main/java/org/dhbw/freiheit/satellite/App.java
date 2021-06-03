package org.dhbw.freiheit.satellite;

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
    	final int DEBUG = 3;
    	
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
		    	CollectInformation ci = new CollectInformation(JSON_FILE);
		    	ci.printSetOfAllSatellites();
		    	// ci.printSetOfAllChannels();
		    	ci.printSetOfAllChannelsByLanguage("ger");
		    	ci.printSetOfAllTVChannelsByLanguage("ger");
		    	ci.printSatelliteArray();
		    	ci.printSatelliteNameChannelsMap();
		    	ci.printChannelSatellitesMap();
	    	break;
	    	case 4:
		        // Konfiguration des Filterns
		    	new ConfigurationWindow();
	    	break;
    	}
    }
}
