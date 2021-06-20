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

	/*
	 * language kann sein: "ger", "spa", "ukr", "chi", 
	 */


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
			List<Satellite.Channel> channelList = new ArrayList<>(Arrays.asList(sat.channels));
			satMap.put(sat, channelList);
		}
		return satMap;
	}

}
