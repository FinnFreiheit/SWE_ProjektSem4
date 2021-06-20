package org;

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
 * Unit Test für den Fall einer leeren JSON Input File.
 */
public class AppTestEmpty
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
                new CollectSatellitesAndChannelsInformation("src/test/java/org/testResources/testSatellitesEmpty.json");
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
        assertEquals(0,satellites.length);
    }

    /**
     * Liste wird nach Sprache Deutsch Sortiert. Erwarten ein Satellite mit Name=sat.
     * Erwarten zwei Channel mit Name=Radio-Channel-ger
     */
    @Test
    public void sattelitePropertyTest()
    {
        assertEquals(0,satelliteChannelMap.entrySet().size());

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

        TransponderAnzahlSender transponderAnzahlSenderAgg = new TransponderAnzahlSender();

        aggregatContext.setStrategy(transponderAnzahlSenderAgg);
        transponderAnzahlSenderAgg.setPathInformation("src/test/java/org/testResources/testSatellitesEmpty.json");

        Map<Satellite, List<Satellite.Channel>> transponderAnzahlSender = aggregatContext.aggregatAnwenden();

        assertEquals(0,transponderAnzahlSender.entrySet().size());


    }


}
