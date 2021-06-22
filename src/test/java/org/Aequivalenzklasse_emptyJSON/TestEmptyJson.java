package org.Aequivalenzklasse_emptyJSON;


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
 * Unit test für den Fall einer leeren Input JSON
 */
public class TestEmptyJson {


    String property;
    String[] value;

    CollectSatellitesAndChannelsInformation satellitesAndChannelsInformation;
    SatelliteProperty satelliteProperty;


    Map<String, List<String>> satelliteChannelMapString;


    /**
     * Einlesen aller Einträge der Test Eingabe JSON. Es wird eine satelliteChannelMap erstellt und ein Aggregat angwendet
     */
    @Before
    public void init() {
        this.property = "all";
        this.value = new String[]{""};
        String dataJsonPath = "src/test/java/org/Aequivalenzklasse_emptyJSON/testResources/testSatellites.json";
        this.satellitesAndChannelsInformation =
                new CollectSatellitesAndChannelsInformation(dataJsonPath);
        this.satelliteProperty =
                new SatelliteProperty(this.property, this.value, this.satellitesAndChannelsInformation);

        AggregatContext aggregatContext = new AggregatContext();

        DeutscheTVSender deutscheTVSenderAgg = new DeutscheTVSender();

        aggregatContext.setStrategy(deutscheTVSenderAgg);
        deutscheTVSenderAgg.setPathInformation(dataJsonPath);
        satelliteChannelMapString = aggregatContext.aggregatAnwenden();

    }

    /**
     * Testet ob in die Datei geschrieben wird.
     */
    @Test
    public void testWrightToFile() {
        OutputJSON outputJSON = new OutputJSON();
        outputJSON.outputMap(satelliteChannelMapString);
        try (BufferedReader br = new BufferedReader(new FileReader("resources/sortedSatellites.json"))) {
            String line = br.readLine();
            assertTrue("Datei ist nicht leer", line.length() == 2);
        } catch (FileNotFoundException e) {
            fail("Datei nicht gefunden");
        } catch (IOException e) {
            fail("Fehler");
        }
    }


}
