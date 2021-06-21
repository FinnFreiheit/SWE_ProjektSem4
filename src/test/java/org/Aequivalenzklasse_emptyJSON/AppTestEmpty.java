package org.Aequivalenzklasse_emptyJSON;

import model.CollectSatellitesAndChannelsInformation;
import model.Satellite;
import model.SatelliteProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit Test für den Fall einer leeren JSON Input File.
 */
public class AppTestEmpty
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
                new CollectSatellitesAndChannelsInformation("src/test/java/org/Aequivalenzklasse_emptyJSON/testResources/testSatellites.json");
        this.satelliteProperty =
                new SatelliteProperty(this.property, this.value, this.satellitesAndChannelsInformation);

        satelliteChannelMap = this.satelliteProperty.getSatelliteMap();

    }

    /**
     * Erstellung eines Arrays aller eingelesenen Satelliten. Erwartet werden 0 Satelliten.
     */
    @Test
    public void getSatelliteInfosAndWriteIntoClasses()
    {
        Satellite[] satellites = this.satellitesAndChannelsInformation.createSatelliteArray();
        assertEquals(0,satellites.length);
    }

    /**
     * Gesamte Map, welche aus dem Dummy Input eingelesen wurde, wird ausgegeben. Erwarten 0 Einträge.
     */
    @Test
    public void satellitePropertyTest()
    {
        assertEquals(0,satelliteChannelMap.entrySet().size());

    }



}
