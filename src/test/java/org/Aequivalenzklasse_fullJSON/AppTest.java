package org.Aequivalenzklasse_fullJSON;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import controller.AggregatStrategyFactory.AggregatContext;
import controller.AggregatStrategyFactory.Aggregate.DeutscheTVSender;
import model.CollectSatellitesAndChannelsInformation;

import model.Satellite;
import model.SatelliteProperty;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Unit test für den Fall einer nichtleeren Input JSON
 */
public class AppTest
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
     * Erstellung eines Arrays aller eingelesenen Satelliten. Erwartet zwei Satelliten mit je drei Channel.
     */
    @Test
    public void getSatelliteInfosAndWriteIntoClasses()
    {
        Satellite[] satellites = this.satellitesAndChannelsInformation.createSatelliteArray();
        assertEquals(2,satellites.length);
        assertEquals(3,satellites[0].getChannels().length);
        assertEquals(3,satellites[1].getChannels().length);
    }

    /**
     * Gesamte Map, welche aus dem Dummy Input eingelesen wurde, wird ausgegeben. Erwarten zwei Satellite mit dem Namen=ABS-2A.
     * Erwarten 3 Channel, der erste Channel trägt bei beiden Satelliten den Name=TV-Channel-eng
     */
    @Test
    public void satellitePropertyTest()
    {
        for (Map.Entry<Satellite, List<Satellite.Channel>> entry : satelliteChannelMap.entrySet())
        {
            Satellite satellite = entry.getKey();
            List<Satellite.Channel> channels = entry.getValue();

            assertEquals("ABS-2A", satellite.get("sat"));

            assertEquals(3,channels.size());

            assertEquals("TV-Channel-eng", channels.get(0).get("name"));
        }



    }


}
