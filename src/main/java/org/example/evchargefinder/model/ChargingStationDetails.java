package org.example.evchargefinder.model;

import java.util.ArrayList;
public class ChargingStationDetails {
    public class Availability{
        public Current current;
        public ArrayList<PerPowerLevel> perPowerLevel;
    }

    public class Connector{
        public String type;
        public int total;
        public Availability availability;
    }

    public class Current{
        public int available;
        public int occupied;
        public int reserved;
        public int unknown;
        public int outOfService;
    }

    public class PerPowerLevel{
        public double powerKW;
        public int available;
        public int occupied;
        public int reserved;
        public int unknown;
        public int outOfService;
    }

    public class Root{
        public ArrayList<Connector> connectors;
        public String chargingAvailability;
    }


}
