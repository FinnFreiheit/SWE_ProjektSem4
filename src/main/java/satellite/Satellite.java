package satellite;

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
   	
   	public Channel[] getChannels()
   	{
   		return this.channels;
   	}
    
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

		@Override
		public int compareTo(Channel o) 
		{
			return this.name.compareTo(o.name);
		}
		
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
		
	    public void print()
	    {
	        System.out.printf("--- %-22s %-8s %-8s %-8s %n", this.name, this.compression, this.enc, this.a_pid);
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
	public int compareTo(Satellite o) 
	{
		return this.sat.compareTo(o.sat);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o==null) return false;
		if(this==o) return true;
		if(this.getClass()!=o.getClass()) return false;
		
		Satellite satellite =(Satellite)o;
		return this.sat.equals(satellite.sat);
	}
	
	@Override
	public int hashCode()
	{
		return this.sat.hashCode();
	}
}
