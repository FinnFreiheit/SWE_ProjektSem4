package satellite;



import java.util.*;
import java.util.stream.Collectors;

public class ChannelsProperty 
{
	String property;
	String[] values;
	Map<Satellite.Channel, List<Satellite>> channelMap;
	Map<Satellite.Channel, List<Satellite>> channelMapNewNew;
	
	public ChannelsProperty(String property, String[] values, CollectSatellitesAndChannelsInformation ci)
	{
		this.property = property;
		this.values = values;
		this.channelMap = createNewMap(ci.createChannelSatellitesMap(), ci);
	}
	
	public ChannelsProperty(Map<Satellite.Channel, List<Satellite>> baseMap, String property, String[] values, CollectSatellitesAndChannelsInformation ci)
	{
		this.property = property;
		this.values = values;
		this.channelMap = createNewMap(baseMap, ci);
	}
	
	private Map<Satellite.Channel, List<Satellite>> createNewMap(Map<Satellite.Channel, List<Satellite>> channelMapAll, CollectSatellitesAndChannelsInformation ci)
	{
		Map<Satellite.Channel, List<Satellite>> channelMapNew = new TreeMap<>();
		
		for(Map.Entry<Satellite.Channel, List<Satellite>> entry : channelMapAll.entrySet())
		{
			Satellite.Channel channel = entry.getKey();
			List<Satellite> satList = entry.getValue();
		
			for(String value : values)
			{
				switch(property.toLowerCase())
				{
					case "language": 
						Map<String, String> lang = ci.createLanguageMap();
						String language = lang.get(value.toLowerCase());
						if(channel.a_pid.toLowerCase().contains(language))
						{
							channelMapNew.put(channel, satList);
						}
			
					break;
					case "a_pid":
						if(channel.a_pid.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
					case "name":
						if(channel.name.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
					case "res": 
						if(channel.res.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
					case "url":
						if(channel.url.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
					case "sid":
						if(channel.sid.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
					case "v_pid":
						if(channel.v_pid.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
					case "package":
						if(channel.packge.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
					case "type":
						if(channel.type.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
					case "enc":
						if(channel.enc.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
					case "compression":
						if(channel.compression.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;
				}
			}
		}
		this.channelMapNewNew = channelMapNew;
		return channelMapNew;
	}
	
	public Map<Satellite.Channel, List<Satellite>> getChannelMap()
	{
		Map<Satellite.Channel, List<Satellite>> clone = new TreeMap<>();
		for(Map.Entry<Satellite.Channel, List<Satellite>> entry : this.channelMap.entrySet())
		{
			Satellite.Channel channel = entry.getKey();
			List<Satellite> satList = entry.getValue();
			
			clone.put(channel,  satList);
		}
		return clone;
	}

	
	public void print()
	{
		System.out.printf("%d Channels ", this.channelMapNewNew.size());
		if(this.property.toLowerCase().equals("language"))
		{
			System.out.println("senden in den Sprachen");
			for(String value : this.values)
			{
				System.out.println("- " + value);
			}
		}
		else if(this.property.toLowerCase().equals("type"))
		{
			if(this.values[0].toLowerCase().equals("tv")) System.out.println("sind TV-Sender ");
			else System.out.println("sind Radio-Sender ");
		}
		System.out.println();
		int i = 1;
		Set<Satellite> setSatellites = new TreeSet<>();
		for(Map.Entry<Satellite.Channel, List<Satellite>> entry : this.channelMapNewNew.entrySet())
		{
			//Satellite.Channel channel = entry.getKey();
			//System.out.printf("%3d ", i++);
			//channel.print();
			List<Satellite> satellites = entry.getValue();

			for(Satellite satellite : satellites)
			 {
				 setSatellites.add(satellite);
			 }

		}

		System.out.println("Alle Satelliten : ");
		for(Satellite satellite : setSatellites)
		{
			satellite.print();
		}
	}
}
