package org.example.evchargefinder.model;

import java.util.ArrayList;

public class ChargingStations {
    public String id;
    public Address address;
    public Position position;
    public ChargingPark chargingPark;

    public static class ChargingPark{
        public ArrayList<Connector> connectors;
    }
    public static class Connector{
        public String connectorType;
        public int ratedPowerKW;
        public int voltageV;
        public int currentA;
        public String currentType;
    }
    public class Position{
        public double lat;
        public double lon;
    }
    public static class Address{
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
}

