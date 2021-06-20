package model;

import java.util.*;

/**
 * Klasse für die Verarbeitung und Filterung einer Satelliten Map.
 */
public class SatelliteProperty
{
    String property;
    String[] values;
    Map<Satellite, List<Satellite.Channel>> satelliteMap;

    public SatelliteProperty(String property, String[] values, CollectSatellitesAndChannelsInformation ci)
    {
        this.property = property;
        this.values = values;
        this.satelliteMap = createNewMap(ci.createSatelliteChannelsMap(), ci);
    }

    public SatelliteProperty(Map<Satellite, List<Satellite.Channel>> baseMap, String property, String[] values,
             CollectSatellitesAndChannelsInformation ci)
    {
        this.property = property;
        this.values = values;
        this.satelliteMap = createNewMap(baseMap, ci);
    }

    /**
     * Filtert mit den übergebenen Filtern die Satelliten Map und gibt die neue Map zurück
     *
     * @param satelliteMapAll Map mit den Daten aller Satelliten
     * @param ci Satelliten und Channel Informationen
     * @return gefilterte Satelliten Map
     */
    private Map<Satellite, List<Satellite.Channel>> createNewMap(Map<Satellite, List<Satellite.Channel>> satelliteMapAll,
                                                                 CollectSatellitesAndChannelsInformation ci )
    {
        Map<Satellite, List<Satellite.Channel>> satelliteMapNew = new TreeMap<>();

        // Iteration über alle Einträge der Satelliten Map
        for(Map.Entry<Satellite,List<Satellite.Channel>> entry : satelliteMapAll.entrySet())
        {
            Satellite satellite = entry.getKey();
            List<Satellite.Channel> channelList = entry.getValue();
            List<Satellite.Channel> channelListTemp = new ArrayList<>();

            // Filterung nach den übergebenen Eigenschaften
            for(String value:values)
            {
                channelListTemp.clear();
                switch (property.toLowerCase())
                {
                    // Filterung nach Sprache
                    case "language":
                        Map<String, String> lang = ci.createLanguageMap();
                        String language = lang.get(value.toLowerCase());

                        for(Satellite.Channel channel : channelList)
                        {
                            if (channel.a_pid.toLowerCase().contains(language))
                            {
                                channelListTemp.add(channel);
                            }
                        }
                        if(channelListTemp.size() != 0)
                        {
                            satelliteMapNew.put(satellite, channelListTemp);
                        }

                    break;

                    // Filterung nach a_pid
                    case "a_pid":
                        for(Satellite.Channel channel : channelList)
                        {
                            if (channel.a_pid.toLowerCase().contains(value.toLowerCase()))
                            {
                                channelListTemp.add(channel);
                            }
                        }
                        if(channelListTemp.size() != 0)
                        {
                            satelliteMapNew.put(satellite, channelListTemp);
                        }

                        break;

                    // Filterung nach Type (TV, Radio)
                    case "type":
                        for(Satellite.Channel channel : channelList)
                        {
                            if (channel.type.toLowerCase().contains(value.toLowerCase()))
                            {
                                channelListTemp.add(channel);
                            }
                        }
                        if(channelListTemp.size() != 0)
                        {
                            satelliteMapNew.put(satellite, channelListTemp);
                        }
                    break;
                    // Filterung nach bestimmten Frequenzen
                    case "sat":
                        if (satellite.sat.toLowerCase().contains(value.toLowerCase()))
                        {
                            satelliteMapNew.put(satellite, channelList);
                        }
                    break;


                }
            }
        }
        return satelliteMapNew;
    }

    /**
     * Gibt einen Klon der privaten Satelliten Map zurück
     *
     * @return Satelliten Map
     */
    public Map<Satellite, List<Satellite.Channel>> getSatelliteMap()
    {
        Map<Satellite, List<Satellite.Channel>> clone = new TreeMap<>();
        for(Map.Entry<Satellite, List<Satellite.Channel>> entry : this.satelliteMap.entrySet())
        {
            Satellite satellite = entry.getKey();
            List<Satellite.Channel> channelList = entry.getValue();

            clone.put(satellite,channelList);
        }
        return clone;
    }

    /**
     * Ausgabe eines SatelliteProperty Objektes auf der Konsole
     */
    public void print()
    {
        System.out.printf("%d Satelliten ", this.satelliteMap.size());
        if(this.property.toLowerCase().equals("language"))
        {
            System.out.println("haben Sender in den Sprachen");
            for(String value : this.values)
            {
                System.out.println("- " + value);
            }
        }
        else if(this.property.toLowerCase().equals("type"))
        {
            if(this.values[0].toLowerCase().equals("tv")) System.out.println("haben TV-Sender ");
            else System.out.println("haben Radio-Sender ");
        }
        System.out.println();
        int i = 1;

        System.out.println("Alle Satelliten : ");
        for(Map.Entry<Satellite, List<Satellite.Channel>> entry : this.satelliteMap.entrySet())
        {
            Satellite satellite = entry.getKey();
            satellite.print(entry.getValue());
        }
    }
}
