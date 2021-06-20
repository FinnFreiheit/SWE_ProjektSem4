package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Verschachtelte Satellitenklasse. Beinhaltet alle Satellitenattribute und eine Channel Klasse mit
 * alles Channelattributen
 */
public class Satellite implements Comparable<Satellite>{
    String pol;
    String sat;
    String orbital;
    String freq;
    String sym;
    Channel[] channels;

    Satellite(JSONObject sat)
    {
        this.sat = (String) sat.get("sat");
        this.pol = (String) sat.get("pol");
        this.orbital = (String) sat.get("orbital");
        this.freq = (String) sat.get("freq");
        this.sym = (String) sat.get("sym");
        JSONArray channelArray = (JSONArray) sat.get("channels");
        this.channels = new Channel[channelArray.size()];
        int index = 0;
        for(Object channel : channelArray)
        {
        	JSONObject channelJSON = (JSONObject)channel;
        	this.channels[index++] = new Channel(channelJSON, this);
        }
    }
	/**
	 * Gibt je nach input String ein privates Attribut der Satellite Klasse zurück
	 *
	 * @param property Attribut das zurückgegeben werden soll
	 * @return Attribut
	 */
   	public String get(String property)
	{
		switch(property.toLowerCase())
		{
    		case "sat": return this.sat;
    		case "pol": return this.pol;
    		case "orbital": return this.orbital;
    		case "freq": return this.freq;
    		case "sym": return this.sym;
		}
		return "";
	}

	/**
	 * gibt ein Array mit den Channels zurück
	 * @return Channel Array
	 */
   	public Channel[] getChannels()
   	{
   		return this.channels;
   	}





	/**
	 * Erzeugt ein String aus einem Satellitenobjekt.
	 * key:value,key:value,.....,key:value
	 * @return String
	 */
	public String toString(){
   		return String.format("sat:%s,orbital:%s,pol:%s,freq:%s,sym:%s",
							 this.sat, this.orbital, this.pol, this.freq, this.sym);
	}

	/**
	 * Vergleicht alle Attribute des übergebenen Satelliten mit denen des Objektes. Ist ein Attribut
	 * gleich, so wird überprüft ob sich auch die anderen Attribute gleichen
	 *
	 * @param sat zu vergleichender Satellite
	 * @return 0 wenn die Namen identisch sind; Wert kleiner 0 wenn der String lexikographisch kleiner ist,
	 * 	Wert größer 0 der String lexikographisch größer ist
	 */
	@Override
	public int compareTo(Satellite sat)
	{
		if(this.sat.equals(sat.sat))
		{
			if(this.sym.equals(sat.sym))
			{
				if(this.pol.equals(sat.pol))
				{
					if(this.orbital.equals(sat.orbital))
					{
						return this.freq.compareTo(sat.freq);
					} else return this.orbital.compareTo(sat.orbital);
				} else return this.pol.compareTo(sat.pol);
			} else return this.sym.compareTo(sat.sym);
		} else return this.sat.compareTo(sat.sat);
	}

	/**
	 * Vergleicht ob sich zwei Satteliten Namen gleichen
	 *
	 * @param o zu vergleichends Objekt
	 * @return True wenn sich die Satelliten gleichen, False wenn nicht
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Satellite)) return false;
		Satellite satellite = (Satellite) o;
		return pol.equals(satellite.pol) && sat.equals(satellite.sat) && orbital.equals(satellite.orbital) &&
				freq.equals(satellite.freq) && sym.equals(satellite.sym);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(pol, sat, orbital, freq, sym);
	}


	/**
	 * Verschachtelte Channel Klasse, enthält alle Channel Attribute
	 */
	public class Channel implements Comparable<Channel>{
		String a_pid;
		String name;
		String res;
		String url;
		String sid;
		String v_pid;
		String packge;
		String type;
		String enc;
		String compression;
		Satellite satellite;

		Channel(JSONObject channel, Satellite satellite)
		{
			this.type = (String) channel.get("type");
			this.a_pid = (String) channel.get("a_pid");
			this.sid = (String) channel.get("sid");
			this.v_pid = (String) channel.get("v_pid");
			this.compression = (String) channel.get("compression");
			this.url = (String) channel.get("url");
			this.enc = (String) channel.get("enc");
			this.name = (String) channel.get("name");
			this.res = (String) channel.get("res");
			this.packge = (String) channel.get("package");
			this.satellite = satellite;
		}

		/**
		 * Gibt je nach input String ein privates Attribut der Channel Klasse zurück
		 *
		 * @param property Attribut das zurückgegeben werden soll
		 * @return Attribut
		 */
		public String get(String property)
		{
			switch(property.toLowerCase())
			{
				case "type": return this.type;
				case "a_pid": return this.a_pid;
				case "sid": return this.sid;
				case "v_pid": return this.v_pid;
				case "compression": return this.compression;
				case "url": return this.url;
				case "enc": return this.enc;
				case "name": return this.name;
				case "res": return this.res;
				case "package": return this.packge;
			}
			return "";
		}

		/**
		 * Vergleicht den übergebenen Namen des Channels mit dem Namen des Channel Objektes
		 *
		 * @param o zu vergleichender Channel
		 * @return 0 wenn die Namen identisch sind; Wert kleiner 0 wenn der String lexikographisch kleiner ist,
		 * 	Wert größer 0 der String lexikographisch größer ist
		 */
		@Override
		public int compareTo(Channel o)
		{
			return this.name.compareTo(o.name);
		}

		/**
		 * Vergleicht ob sich zwei Objekte Namen gleichen
		 *
		 * @param o zu vergleichends Objekt
		 * @return True wenn sich die Namen gleichen, False wenn nicht
		 */
		@Override
		public boolean equals(Object o)
		{
			if(o==null) return false;
			if(this==o) return true;
			if(this.getClass()!=o.getClass()) return false;

			Channel channel =(Channel)o;
			return this.name.equals(channel.name);
		}

		@Override
		public int hashCode()
		{
			return this.name.hashCode();
		}


		/**
		 * Erzeugt ein String aus einem Channelobjekt
		 *
		 * key:value,key:value.....
		 *
		 * @return String
		 */
		public String toString()
		{
			return String.format("name:%s,a_pid:%s,res:%s,url:%s,sid:%s,v_pid:%s,packge:%s,type:%s,enc:%s," +
										 "compression:%s", this.name,this.a_pid , this.res, this.url, this.sid,
								 this.v_pid, this.packge, this.type, this.enc, this.compression);
		}

	}
}
