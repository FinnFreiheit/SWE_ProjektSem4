package org.dhbw.freiheit.satellite;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        	this.channels[index++] = new Channel(channelJSON);
        }
    }
    
    class Channel implements Comparable<Channel>{
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
    	
    	Channel(JSONObject channel)
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
    	}

		@Override
		public int compareTo(Channel o) {
			return this.name.compareTo(o.name);
		}
    }
    
    public void print()
    {
        System.out.printf("%n%-14s %-10s %-6s %-8s %-8s %n", 
        		this.sat, this.orbital, this.pol, this.freq, this.sym);
        for(Channel c : this.channels)
        {
            System.out.printf("---------- %-22s %-8s %-8s %-8s %n", c.name, c.compression, c.enc, c.a_pid);
        }
    }

	@Override
	public int compareTo(Satellite o) {
		return this.sat.compareTo(o.sat);
	}
}
