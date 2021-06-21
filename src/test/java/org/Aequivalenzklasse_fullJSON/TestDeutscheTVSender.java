package org.Aequivalenzklasse_fullJSON;

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
 * Unit test für den Fall einer nichtleeren Input JSON
 */
public class TestDeutscheTVSender
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
     * Es wird das Aggregat getestest, welches Deutsche TV-Sender filtert. Es wird aus der Map der Test Input
     * JSON gefiltert. Erwartet wird ein Eintrag mit einem Satelliten und einem Deutschen TV Sender
     */
    @Test
    public void aggregat()
    {
        AggregatContext aggregatContext = new AggregatContext();

        DeutscheTVSender deutscheTVSenderAgg = new DeutscheTVSender();

        aggregatContext.setStrategy(deutscheTVSenderAgg);
        deutscheTVSenderAgg.setPathInformation("src/test/java/org/Aequivalenzklasse_fullJSON/testResources/testSatellites.json");

        Map<String, List<String>> deutscheTVSender = aggregatContext.aggregatAnwenden();

        assertEquals(1,deutscheTVSender.entrySet().size());

        for (Map.Entry<String, List<String>> entry : deutscheTVSender.entrySet())
        {
            String satellite = entry.getKey();
            List<String> channels = entry.getValue();

            assertEquals("sat:ABS-2A,orbital:orbital,pol:pol,freq:freq,sym:sym", satellite);
            assertEquals(1,channels.size());
            assertEquals("name:TV-Channel-ger,a_pid:a_pid3 ger,res:res3,url:url3,sid:sid3,v_pid:v_pid3,packge:package3,type:TV,enc:enc3,compression:compression3", channels.get(0));
        }

    }


}
