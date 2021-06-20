package model;


import controller.AggregatStrategyFactory.AggregatContext;
import view.AusgabeContext;

import java.util.List;
import java.util.Map;

import javax.swing.JFrame;


public class App
{

    public static void main( String[] args )
    {
    	AggregatContext aggregatContext = new AggregatContext();
    	AusgabeContext ausgabeContext = new AusgabeContext();
    	ausgabeContext.MapAusgabe(aggregatContext.aggregatAnwenden());
    }
}
