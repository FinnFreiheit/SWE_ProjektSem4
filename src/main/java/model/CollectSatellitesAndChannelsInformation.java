package model;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Klasse, welche die eingelesenen JSON Informationen verwaltet und formatiert
 */
public class CollectSatellitesAndChannelsInformation {

	
	JSONArray satellites;
	
	public CollectSatellitesAndChannelsInformation(String file)
	{
		this.satellites = ReadJSONFile.createJSONArrayListForAllSatellites(file);
	}

	/**
	 * Erstellt eine Map mit allen verfügbaren Sprachen
	 *
	 * @return Map mit allen verfügbaren Sprachen
	 */
	public Map<String, String> createLanguageMap()
	{
		Map<String, String> langMap = new TreeMap<>();
		langMap.put("deutsch", "ger");
		langMap.put("arabisch", "ara");
		langMap.put("polnisch", "pol");
		langMap.put("russisch", "rus");
		langMap.put("englisch", "eng");
		langMap.put("italienisch", "ita");
		langMap.put("tuerkisch", "tur");
		langMap.put("chinesisch", "chi");
		langMap.put("spanisch", "spa");
		langMap.put("franzoesisch", "fre");
		
		return langMap;
	}

	/**
	 * Erstellt eine Menge aus allen Satelliten, welche in der Eingabe JSON stehen
	 *
	 * @return Set aus Satelliten
	 */
	public Set<String> createSetOfAllSatellites()
	{
		Set<String> satellitesSet = new TreeSet<>();
		for(Object sat : this.satellites)
		{
			JSONObject satellite = (JSONObject)sat;
			String satName = (String) satellite.get("sat");
			satellitesSet.add(satName); 
		}
		return satellitesSet;
	}

	/**
	 * Gibt alle Satelliten in der Konsole aus
	 */
	public void printSetOfAllSatellites()
	{
		Set<String> satellitesSet = this.createSetOfAllSatellites();
		int i = 1;
		for(String sat : satellitesSet)
		{
			System.out.printf("%-3d %-15s %n", i++, sat);
		}
	}

	/**
	 * Erstellt eine Menge aus allen Channeln, welche in der Eingabe JSON stehen
	 *
	 * @return Set aus Channeln
	 */
	public Set<String> createSetOfAllChannelNames()
	{
		Set<String> channelsSet = new TreeSet<>();
		for(Object sat : this.satellites)
		{
			JSONObject satellite = (JSONObject)sat;
			JSONArray allChannelsOfSatellite = (JSONArray) satellite.get("channels");
			for(Object channel : allChannelsOfSatellite)
			{
				JSONObject channelJSON = (JSONObject)channel;
				String channelName = (String) channelJSON.get("name");
				channelsSet.add(channelName); 
			}
		}
		return channelsSet;
	}

	public void printSetOfAllChannels()
	{
		Set<String> channelsSet = this.createSetOfAllChannelNames();
		int i = 1;
		for(String channelName : channelsSet)
		{
			System.out.printf("%-3d %-15s %n", i++, channelName);
		}
	}
	
	/*
	 * language kann sein: "ger", "spa", "ukr", "chi", 
	 */

	/**
	 * Erzegugt eine Menge an Channel-Namen, die in der übergebenen Sprache senden.
	 *
	 * @param language String der gewünschten Sprache
	 * @return Set aus Channeln-Namen der gewünschten Sprache
	 */
	public Set<String> createSetOfAllChannelNamesByLanguage(String language)
	{
		Set<String> channelsSet = new TreeSet<>();
		String lang = language.toLowerCase();
		for(Object sat : this.satellites)
		{
			JSONObject satellite = (JSONObject)sat;
			JSONArray allChannelsOfSatellite = (JSONArray) satellite.get("channels");
			for(Object channel : allChannelsOfSatellite)
			{
				JSONObject channelJSON = (JSONObject)channel;
				String channelName = (String) channelJSON.get("name");
				String apid = (String) channelJSON.get("a_pid");
				if(apid.contains(lang))
				{
					channelsSet.add(channelName); 
				}
			}
		}
		return channelsSet;
	}

	/**
	 * Erzegugt eine Menge an TV-Kanal-Namen, die in der übergebenen Sprache senden.
	 *
	 * @param language String der gewünschten Sprache
	 * @return Set aus TV-Kanal-Namen der gewünschten Sprache
	 */
	public Set<String> createSetOfAllTVChannelNamesByLanguage(String language)
	{
		Set<String> channelsSet = new TreeSet<>();
		String lang = language.toLowerCase();
		for(Object sat : this.satellites)
		{
			JSONObject satellite = (JSONObject)sat;
			JSONArray allChannelsOfSatellite = (JSONArray) satellite.get("channels");
			for(Object channel : allChannelsOfSatellite)
			{
				JSONObject channelJSON = (JSONObject)channel;
				String channelName = (String) channelJSON.get("name");
				String apid = (String) channelJSON.get("a_pid");
				String type = (String) channelJSON.get("type");
				if(apid.contains(lang) && type.equals("TV"))
				{
					channelsSet.add(channelName); 
				}
			}
		}
		return channelsSet;
	}


	public void printSetOfAllChannelsByLanguage(String language)
	{
		Set<String> channelsSet = this.createSetOfAllChannelNamesByLanguage(language);
		int i = 1;
		for(String channelName : channelsSet)
		{
			System.out.printf("%-3d %-15s %n", i++, channelName);
		}
	}

	/**
	 * Gibt die Menge an TV-Kanal-Namen einer gewünschten Sprache in der Konsole aus
	 *
	 * @param language language String der gewünschten Sprache
	 */
	public void printSetOfAllTVChannelsByLanguage(String language)
	{
		Set<String> channelsSet = this.createSetOfAllTVChannelNamesByLanguage(language);
		int i = 1;
		for(String channelName : channelsSet)
		{
			System.out.printf("%-3d %-15s %n", i++, channelName);
		}
	}

	/**
	 * Erstellt ein Array aus allen Satelliten.
	 *
	 * @return Array von Satelliten
	 */
	public Satellite[] createSatelliteArray()
	{
		Satellite[] satellites = new Satellite[this.satellites.size()];
		int index = 0;
		for(Object sat : this.satellites)
		{
			JSONObject satellite = (JSONObject)sat;
			satellites[index++] = new Satellite(satellite);
		}
		return satellites;		
	}

	/**
	 * Gibt ein Array aus allen Satelliten in der Konsole aus
	 */
	public void printSatelliteArray()
	{
		Satellite[] satellites = createSatelliteArray();
		for(Satellite sat : satellites)		
		{
			sat.print();
		}
	}
	
	/**
	 * Schluessel ist Satellit
	 * Werte sind die Channels dieses Satelliten als Liste
	 *
	 * @return Map der Schluessel-Werte-Paare
	 */
	public Map<Satellite, List<Satellite.Channel>> createSatelliteChannelsMap()
	{
		Satellite[] satellites = createSatelliteArray();
		Map<Satellite, List<Satellite.Channel>> satMap = new TreeMap<>();
		for(Satellite sat : satellites)
		{
			List<Satellite.Channel> channelList = new ArrayList<>();
			channelList.addAll(Arrays.asList(sat.channels));
			satMap.put(sat, channelList);
		}
		return satMap;
	}
	
	/**
	 * Schluessel ist Channel
	 * Werte sind die Satelliten dieses Channels als Liste
	 *
	 * @return Map der Schluessel-Werte-Paare
	 */
	public Map<Satellite.Channel, List<Satellite>> createChannelSatellitesMap()
	{
		Satellite[] satellites = createSatelliteArray();
		Map<Satellite.Channel, List<Satellite>> channelMap = new TreeMap<>();
		
		for(Satellite sat : satellites)	
		{
			for(Satellite.Channel channel : sat.channels)
			{
			if(channelMap.containsKey(channel))
				{
					List<Satellite> satList = channelMap.get(channel);
					satList.add(sat);
				}
				else
				{
					List<Satellite> satList = new ArrayList<>();
					satList.add(sat);
					channelMap.put(channel, satList);
				}
			}
		}
		return channelMap;
	}
	
	/**
	 * Schluessel ist Name des Satelliten
	 * Werte sind Menge aller Channels dieses Satelliten
	 *
	 * @return Map der Schluessel-Werte-Paare
	 */
	public Map<String, Set<Satellite.Channel>> createSatelliteNameChannelsMap()
	{
		Satellite[] satellites = createSatelliteArray();
		Map<String, Set<Satellite.Channel>> satMap = new TreeMap<>();
		
		for(Satellite sat : satellites)		
		{
			if(satMap.containsKey(sat.sat))
			{
				Set<Satellite.Channel> channels = satMap.get(sat.sat);
				for(Satellite.Channel c : sat.channels)
				{
					channels.add(c);
				}
			}
			else
			{
				Set<Satellite.Channel> channels = new TreeSet<>();
				for(Satellite.Channel c : sat.channels)
				{
					channels.add(c);
				}
				satMap.put(sat.sat, channels);
			}
		}
		return satMap;
	}
	
	public void printSatelliteNameChannelsMap()
	{
		Map<String, Set<Satellite.Channel>> satMap = createSatelliteNameChannelsMap();
		for(Map.Entry<String, Set<Satellite.Channel>> satellite : satMap.entrySet())
		{
			String nameSat = satellite.getKey();
			Set<Satellite.Channel> setChannels = satellite.getValue();
			System.out.println(nameSat);
			int i = 1;
			for(Satellite.Channel c : setChannels)
			{
				System.out.printf("----------- %-3d %-15s %n", i++, c.name);
			}
		}
	}
	
	public void printChannelSatellitesMap()
	{
		Map<Satellite.Channel, List<Satellite>> channelMap = createChannelSatellitesMap();

		for(Map.Entry<Satellite.Channel, List<Satellite>> entry : channelMap.entrySet())
		{
			String nameChannel = entry.getKey().name;  // Name des Channels
			List<Satellite> satList = entry.getValue();
			System.out.println(nameChannel);
			int i = 1;
			for(Satellite s : satList)
			{
				System.out.printf("----------- %-3d %-15s %n", i++, s.sat);
			}
		}
	}

	/**
	 * Erstellt ein Array aus allen APids aller Channel.
	 *
	 * @return Array von APids
	 */
	public Set<String> createSetOfAllChannelAPids()
	{
		Set<String> channelsSet = new TreeSet<>();
		for(Object sat : this.satellites)
		{
			JSONObject satellite = (JSONObject)sat;
			JSONArray allChannelsOfSatellite = (JSONArray) satellite.get("channels");
			for(Object channel : allChannelsOfSatellite)
			{
				JSONObject channelJSON = (JSONObject)channel;
				String channelName = (String) channelJSON.get("a_pid");
				channelsSet.add(channelName); 
			}
		}
		return channelsSet;
	}

	public void printSetOfAllChannelAPids()
	{
		Set<String> channelsSet = this.createSetOfAllChannelAPids();
		int i = 1;
		for(String channelName : channelsSet)
		{
			System.out.printf("%-3d %-15s %n", i++, channelName);
		}
	}
	
}
