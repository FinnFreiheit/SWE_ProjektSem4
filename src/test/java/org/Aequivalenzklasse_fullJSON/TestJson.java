package org.Aequivalenzklasse_fullJSON;


import controller.AggregatStrategyFactory.AggregatContext;
import controller.AggregatStrategyFactory.Aggregate.DeutscheTVSender;

import model.CollectSatellitesAndChannelsInformation;


import model.SatelliteProperty;
import org.junit.Before;
import org.junit.Test;
import view.outputMethods.OutputJSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit test für den Fall einer nichtleeren Input JSON
 */
public class TestJson {


    String property;
    String[] value;

    CollectSatellitesAndChannelsInformation satellitesAndChannelsInformation;
    SatelliteProperty satelliteProperty;


    Map<String, List<String>> satelliteChannelMapString;


    /**
     * Einlesen aller Einträge der Test Eingabe JSON. Es wird eine satelliteChannelMap erstellt
     */
    @Before
    public void init() {
        this.property = "all";
        this.value = new String[]{""};

        this.satellitesAndChannelsInformation =
                new CollectSatellitesAndChannelsInformation("src/test/java/org/Aequivalenzklasse_fullJSON/testResources/testSatellites.json");
        this.satelliteProperty =
                new SatelliteProperty(this.property, this.value, this.satellitesAndChannelsInformation);

        AggregatContext aggregatContext = new AggregatContext();

        DeutscheTVSender deutscheTVSenderAgg = new DeutscheTVSender();

        aggregatContext.setStrategy(deutscheTVSenderAgg);
        deutscheTVSenderAgg.setPathInformation("src/test/java/org/Aequivalenzklasse_fullJSON/testResources/testSatellites.json");
        satelliteChannelMapString = aggregatContext.aggregatAnwenden();

    }

    /**
     * Testet ob in die Datei geschrieben wird.
     */
    @Test
    public void testWrightToFile() {
        OutputJSON outputJSON = new OutputJSON();
        outputJSON.outputMap(satelliteChannelMapString);
        try (BufferedReader br = new BufferedReader(new FileReader("resources/output.json"))) {
            String line = br.readLine();
            assertTrue("Datei ist nicht leer", line.length() >= 2);
        } catch (FileNotFoundException e) {
            fail("Datei nicht gefunden");
        } catch (IOException e) {
            fail("Fehler");
        }
    }


}
