package org;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import controller.AggregatStrategyFactory.AggregatContext;
import controller.AggregatStrategyFactory.Aggregate.DeutscheTVSender;
import model.CollectSatellitesAndChannelsInformation;
import model.MapFrame;
import model.Satellite;
import model.SatelliteProperty;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    String property;
    String[] value;

    CollectSatellitesAndChannelsInformation satellitesAndChannelsInformation;
    SatelliteProperty satelliteProperty;

    Map<Satellite, List<Satellite.Channel>> satelliteChannelMap;


    @Before
    public void init()
    {
        this.property = "language";
        this.value = new String[]{"deutsch"};

        this.satellitesAndChannelsInformation =
                new CollectSatellitesAndChannelsInformation("src/test/java/org/testResources/testSatellites.json");
        this.satelliteProperty =
                new SatelliteProperty(this.property, this.value, this.satellitesAndChannelsInformation);

        satelliteChannelMap = this.satelliteProperty.getSatelliteMap();

    }

    /**
     * Test: Dummmy Satteliten JSON einlesen. Erwartet ein Satelliten mit drei Channel.
     */
    @Test
    public void getSatelliteInfosAndWriteIntoClasses()
    {
        Satellite[] satellites = this.satellitesAndChannelsInformation.createSatelliteArray();
        assertEquals(1,satellites.length);
        assertEquals(3,satellites[0].getChannels().length);
    }

    /**
     * Liste wird nach Sprache Deutsch Sortiert. Erwarten ein Satellite mit Name=sat.
     * Erwarten zwei Channel mit Name=Radio-Channel-ger
     */
    @Test
    public void sattelitePropertyTest()
    {
        for (Map.Entry<Satellite, List<Satellite.Channel>> entry : satelliteChannelMap.entrySet())
        {
            Satellite satellite = entry.getKey();
            List<Satellite.Channel> channels = entry.getValue();

            assertEquals(satellite.get("sat"), "sat");

            assertEquals(2,channels.size());

            assertEquals("Radio-Channel-ger", channels.get(0).get("name"));
            assertEquals("TV-Channel-ger", channels.get(1).get("name"));
        }


    }

    /**
     * Funktioniert nicht, da Map in Super erstellt wird, bevor wir Path ändern können. Somit wird nie die Test JSON
     * ausgelsen. Sondern immer Original.
     * Könnten auch aufs Original testen. Ist ansich wumpe.
     */
    @Test
    public void aggregat()
    {
        AggregatContext aggregatContext = new AggregatContext();

        DeutscheTVSender deutscheTVSenderAgg = new DeutscheTVSender();

        aggregatContext.setStrategy(deutscheTVSenderAgg);
        deutscheTVSenderAgg.setPathInformation("src/test/java/org/testResources/testSatellites.json");

        MapFrame deutscheTVSender = aggregatContext.aggregatAnwenden();


        for (Map.Entry<Satellite, List<Satellite.Channel>> entry : deutscheTVSender.map.entrySet())
        {
            Satellite satellite = entry.getKey();
            List<Satellite.Channel> channels = entry.getValue();

            assertEquals("sat", satellite.get("sat"));

            assertEquals(1,channels.size());

            assertEquals("TV-Channel-ger", channels.get(0).get("name"));
        }


    }


}
