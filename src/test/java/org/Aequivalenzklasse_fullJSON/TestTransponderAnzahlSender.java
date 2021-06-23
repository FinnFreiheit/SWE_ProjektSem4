package org.Aequivalenzklasse_fullJSON;

import controller.AggregatStrategyFactory.AggregatContext;
import controller.AggregatStrategyFactory.Aggregate.DeutscheTVSender;
import controller.AggregatStrategyFactory.Aggregate.TransponderAnzahlSender;
import model.CollectSatellitesAndChannelsInformation;
import model.Satellite;
import model.SatelliteProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit test für den Fall einer nichtleeren Input JSON
 */
public class TestTransponderAnzahlSender
{

    String property;
    String[] value;

    CollectSatellitesAndChannelsInformation satellitesAndChannelsInformation;
    SatelliteProperty satelliteProperty;

    Map<Satellite, List<Satellite.Channel>> satelliteChannelMap;

    /**
     * Einlesen aller Einträge der Test Eingabe JSON. Es wird eine satelliteChannelMap erstellt
     */
    @Before
    public void init()
    {
        this.property = "all";
        this.value = new String[]{""};

        this.satellitesAndChannelsInformation =
                new CollectSatellitesAndChannelsInformation("src/test/java/org/Aequivalenzklasse_fullJSON/testResources/testSatellites.json");
        this.satelliteProperty =
                new SatelliteProperty(this.property, this.value, this.satellitesAndChannelsInformation);

        satelliteChannelMap = this.satelliteProperty.getSatelliteMap();

    }

    /**
     * Es wird das Aggregat getestest, welches alle Transponder des Satelliten mit Namen ABS-2A filtert.
     * Es wird aus der Map der Test Input JSON gefiltert.
     * Erwartet werden zwei Transponder und mit je 1 Radiosender und 2 Fehrnsehsender
     */
    @Test
    public void aggregat()
    {
        AggregatContext aggregatContext = new AggregatContext();

        TransponderAnzahlSender transponderAnzahlSenderAgg = new TransponderAnzahlSender();

        aggregatContext.setStrategy(transponderAnzahlSenderAgg);
        transponderAnzahlSenderAgg.setPathInformation("src/test/java/org/Aequivalenzklasse_fullJSON/testResources/testSatellites.json");

        Map<String, List<String>> transponderAnzahlSender= aggregatContext.aggregatAnwenden();

        assertEquals(2,transponderAnzahlSender.entrySet().size());

        for (Map.Entry<String, List<String>> entry : transponderAnzahlSender.entrySet())
        {
            String satellite = entry.getKey();
            List<String> anzahl = entry.getValue();

            assertEquals(2,anzahl.size());
            assertEquals("Radiosender|1",anzahl.get(0));
            assertEquals("Fehrnsehsender|2",anzahl.get(1));
        }

    }


}
