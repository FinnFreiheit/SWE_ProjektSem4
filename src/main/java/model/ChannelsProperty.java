package model;



import java.util.*;

/**
 * Klasse für die Verarbeitung und Filterung einer Channel Map.
 */
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

	/**
	 * Filtert mit den übergebenen Filtern die Channel Map und gibt die neue Map zurück
	 *
	 * @param channelMapAll Map mit den Daten aller Satelliten
	 * @param ci Satelliten und Channel Informationen
	 * @return gefilterte Channel Map
	 */
	private Map<Satellite.Channel, List<Satellite>> createNewMap(Map<Satellite.Channel, List<Satellite>> channelMapAll, CollectSatellitesAndChannelsInformation ci)
	{
		Map<Satellite.Channel, List<Satellite>> channelMapNew = new TreeMap<>();

		// Iteration über alle Einträge der Satelliten Map
		for(Map.Entry<Satellite.Channel, List<Satellite>> entry : channelMapAll.entrySet())
		{
			Satellite.Channel channel = entry.getKey();
			List<Satellite> satList = entry.getValue();

			// Filterung nach den übergebenen Eigenschaften
			for(String value : values)
			{
				switch(property.toLowerCase())
				{
					// Filterung nach Sprache
					case "language": 
						Map<String, String> lang = ci.createLanguageMap();
						String language = lang.get(value.toLowerCase());
						if(channel.a_pid.toLowerCase().contains(language))
						{
							channelMapNew.put(channel, satList);
						}
			
					break;

					// Filterung nach a_pid
					case "a_pid":
						if(channel.a_pid.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;

					// Filterung nach Name
					case "name":
						if(channel.name.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;

					// Filterung nach Res
					case "res": 
						if(channel.res.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;

					// Filterung nach URL
					case "url":
						if(channel.url.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;

					// Filterung nach SID
					case "sid":
						if(channel.sid.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;

					// Filterung nach V_PID
					case "v_pid":
						if(channel.v_pid.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;

					// Filterung nach Package
					case "package":
						if(channel.packge.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;

					// Filterung nach Type (TV, Radio)
					case "type":
						if(channel.type.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;

					// Filterung nach Enc
					case "enc":
						if(channel.enc.toLowerCase().contains(value.toLowerCase()))
						{
							channelMapNew.put(channel, satList);
						}
					break;

					// Filterung nach Compression
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

	/**
	 * Gibt einen Klon der privaten Channel Map zurück
	 *
	 * @return Channel Map
	 */
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
