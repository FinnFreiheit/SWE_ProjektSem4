package satellite;

import java.util.*;

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

    private Map<Satellite, List<Satellite.Channel>> createNewMap(Map<Satellite, List<Satellite.Channel>> satelliteMapAll,
                                                                 CollectSatellitesAndChannelsInformation ci )
    {
        Map<Satellite, List<Satellite.Channel>> satelliteMapNew = new TreeMap<>();

        satelliteMapAll.forEach((k,v)->
                                {
                                    if(k.sat.equals("Astra 1L")){
                                        System.out.println("Treffer neu");
                                    }
                                });


        for(Map.Entry<Satellite,List<Satellite.Channel>> entry : satelliteMapAll.entrySet())
        {
            Satellite satellite = entry.getKey();
            if(satellite.sat.equals("Astra 1L"))
            {
                System.out.println("moin");
            }
            List<Satellite.Channel> channelList = entry.getValue();
            List<Satellite.Channel> channelListTemp = new ArrayList<>();


            for(String value:values)
            {
                channelListTemp.clear();
                switch (property.toLowerCase())
                {
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

                    case "a_pid":
                        for(Satellite.Channel channel : channelList)
                        {
                            if (channel.a_pid.toLowerCase().contains(value.toLowerCase()))
                            {
                                channelListTemp.add(channel);
                            }
                        }
                        satelliteMapNew.put(satellite, channelListTemp);
                    break;

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
                }
            }
        }
        return satelliteMapNew;
    }

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

        /*
        Set<Satellite> setSatellites = new TreeSet<>();
        for(Map.Entry<Satellite, List<Satellite.Channel>> entry : this.satelliteMap.entrySet())
        {
            //Satellite.Channel channel = entry.getKey();
            //System.out.printf("%3d ", i++);
            //channel.print();
            Satellite satellites = entry.getKey();
            setSatellites.add(satellites);

        }
        */

        System.out.println("Alle Satelliten : ");
        for(Map.Entry<Satellite, List<Satellite.Channel>> entry : this.satelliteMap.entrySet())
        {
            Satellite satellite = entry.getKey();
            satellite.print(entry.getValue());
        }
    }

}
