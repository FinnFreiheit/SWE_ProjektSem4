package org.Aequivalenzklasse_emptyJSON;

import controller.AggregatStrategyFactory.AggregatContext;
import controller.AggregatStrategyFactory.Aggregate.DeutscheTVSender;
import model.CollectSatellitesAndChannelsInformation;
import model.Satellite;
import model.SatelliteProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit test für den Fall einer leeren Input JSON
 */
public class TestEmptyDeutscheTVSender
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
        this.property = "language";
        this.value = new String[]{"deutsch"};

        this.satellitesAndChannelsInformation =
                new CollectSatellitesAndChannelsInformation("src/test/java/org/Aequivalenzklasse_emptyJSON/testResources/testSatellitesEmpty.json");
        this.satelliteProperty =
                new SatelliteProperty(this.property, this.value, this.satellitesAndChannelsInformation);

        satelliteChannelMap = this.satelliteProperty.getSatelliteMap();

    }

    /**
     * Es wird das Aggregat getestest, welches Deutsche TV-Sender filtert. Es wird aus der Map der Test Input
     * JSON gefiltert. Erwartet werden 0 Einträge
     */
    @Test
    public void aggregat()
    {
        AggregatContext aggregatContext = new AggregatContext();

        DeutscheTVSender deutscheTVSenderAgg = new DeutscheTVSender();

        aggregatContext.setStrategy(deutscheTVSenderAgg);
        deutscheTVSenderAgg.setPathInformation("src/test/java/org/Aequivalenzklasse_emptyJSON/testResources/testSatellitesEmpty.json");

        Map<String, List<String>> deutscheTVSender = aggregatContext.aggregatAnwenden();

        assertEquals(0,deutscheTVSender.entrySet().size());

    }


}
