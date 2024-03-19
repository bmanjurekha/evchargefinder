package org.example.evchargefinder.model;

import java.util.ArrayList;
public class Search {

    public class Address{
        public String streetNumber;
        public String streetName;
        public String municipality;
        public String countrySubdivision;
        public String countrySubdivisionName;
        public String countrySubdivisionCode;
        public String postalCode;
        public String countryCode;
        public String country;
        public String countryCodeISO3;
        public String freeformAddress;
        public String localName;
        public String municipalitySubdivision;
    }

    public class BtmRightPoint{
        public double lat;
        public double lon;
    }

    public class CategorySet{
        public int id;
    }

    public class ChargingAvailability{
        public String id;
    }

    public class ChargingPark{
        public ArrayList<Connector> connectors;
    }

    public class Classification{
        public String code;
        public ArrayList<Name> names;
    }

    public class Connector{
        public String connectorType;
        public int ratedPowerKW;
        public int voltageV;
        public int currentA;
        public String currentType;
    }

    public class DataSources{
        public ChargingAvailability chargingAvailability;
    }

    public class Name{
        public String nameLocale;
        public String name;
    }

    public class Poi{
        public String name;
        public ArrayList<CategorySet> categorySet;
        public String url;
        public ArrayList<String> categories;
        public ArrayList<Classification> classifications;
    }

    public class Position{
        public double lat;
        public double lon;
    }

    public class Result{
        public String type;
        public String id;
        public double score;
        public String info;
        public Poi poi;
        public Address address;
        public Position position;
        public Viewport viewport;
        public ChargingPark chargingPark;
        public DataSources dataSources;
    }

    public class Root{
        public Summary summary;
        public ArrayList<Result> results;
    }

    public class Summary{
        public String query;
        public String queryType;
        public int queryTime;
        public int numResults;
        public int offset;
        public int totalResults;
        public int fuzzyLevel;
        public ArrayList<Object> queryIntent;
    }

    public class TopLeftPoint{
        public double lat;
        public double lon;
    }

    public class Viewport{
        public TopLeftPoint topLeftPoint;
        public BtmRightPoint btmRightPoint;
    }
}


