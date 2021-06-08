package AggregatStrategyFactory;

import satellite.CollectConfigInformation;
import satellite.CollectSatellitesAndChannelsInformation;

public class SuperAggregat
{
    private CollectConfigInformation information;
    private CollectSatellitesAndChannelsInformation satellitesAndChannelsInfo;


    public SuperAggregat()
    {
        this.information =  new CollectConfigInformation();
        this.satellitesAndChannelsInfo =  new CollectSatellitesAndChannelsInformation(
            this.information.getSatellitesPath());
    }

    public CollectConfigInformation getInformation()
    {
        return information;
    }

    public CollectSatellitesAndChannelsInformation getSatellitesAndChannelsInfo()
    {
        return satellitesAndChannelsInfo;
    }
}
