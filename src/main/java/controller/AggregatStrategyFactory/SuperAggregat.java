package controller.AggregatStrategyFactory;

import model.CollectConfigInformation;
import model.CollectSatellitesAndChannelsInformation;
import model.Satellite;
import model.SatelliteProperty;

import java.util.List;
import java.util.Map;

public abstract class SuperAggregat
{
    private CollectConfigInformation information;
    private CollectSatellitesAndChannelsInformation satellitesAndChannelsInfo;


    private SatelliteProperty satelliteProperty;


    public SuperAggregat(String Property, String[] Value)
    {
        this.information = new CollectConfigInformation();
        this.satellitesAndChannelsInfo =
                new CollectSatellitesAndChannelsInformation(this.information.getSatellitesPath());

        this.firstFilter(Property, Value);

    }

    public CollectConfigInformation getInformation()
    {
        return information;
    }


    private void firstFilter(String Property, String[] Value)
    {

        this.satelliteProperty = new SatelliteProperty(Property, Value, this.satellitesAndChannelsInfo);
    }


    public void filter(String Property, String[] Value)
    {
        Map<Satellite, List<Satellite.Channel>> filteredSatellitesAndChannelsInfo =
                this.satelliteProperty.getSatelliteMap();
        this.satelliteProperty = new SatelliteProperty(filteredSatellitesAndChannelsInfo, Property, Value,
                                                       this.satellitesAndChannelsInfo);

    }

    public SatelliteProperty getSatelliteProperty()
    {
        return satelliteProperty;
    }

    public void setPathInformation(String satellitesPath){
        this.information.setSatellitesPath(satellitesPath);
    }
}
