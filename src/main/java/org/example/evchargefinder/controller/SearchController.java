package org.example.evchargefinder.controller;
import com.google.gson.Gson;
import org.example.evchargefinder.model.AuthDetails;
import org.example.evchargefinder.model.ChargingStations;
import org.example.evchargefinder.model.Search;
import org.example.evchargefinder.model.SearchPayload;
import org.example.evchargefinder.services.AuthService;
import org.example.evchargefinder.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/search/*")
public class SearchController {
    @GetMapping("/ChargingStation")
    public ResponseEntity<ArrayList<ChargingStations>> getChargingStation(@RequestBody SearchPayload searchPl) throws IOException {

        Search.Root result = getFuzzySearch(searchPl.query, searchPl.connectorType, searchPl.minPower, searchPl.maxPower);
        ArrayList<ChargingStations> stations = transformChargingStations(result.results);
        return ResponseEntity.status(200).body(stations);
    }

    public Search.Root getFuzzySearch(String query,String connectorType,String minPower,String maxPower) throws IOException {

        String APIKey = "SdH2J8zBZrg0qKSzAC5obuqEVNpeBS7q";
        String sUrl = "https://api.tomtom.com/search/2/search/" + query +
                      ".json?limit=10&radius=1000&minFuzzyLevel=1&maxFuzzyLevel=2&categorySet=7309&connectorSet="+ connectorType +
                      "&view=Unified&relatedPois=off&key=" + APIKey +
                      "&ofs=10"+(minPower.isEmpty()?"": "&minPowerKW="+minPower) +
                      (maxPower.isEmpty()?"":"&maxPowerKW="+ maxPower);
        okhttp3.OkHttpClient sclient = new okhttp3.OkHttpClient().newBuilder()
                .build();
        okhttp3.Request srequest = new okhttp3.Request.Builder()
                .url(sUrl)
                .method("GET", null)
                .build();

        okhttp3.Call scall = sclient.newCall(srequest);

        try (okhttp3.Response sresponse = scall.execute()) {
            if (sresponse.isSuccessful()) {
                Gson sson = new Gson();
                Search.Root sdata = sson.fromJson(sresponse.body().string(), Search.Root.class);
                if (sdata != null) {
                    return sdata;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

    }

    public ArrayList<ChargingStations> transformChargingStations(ArrayList<Search.Result> results)
    {
        ArrayList<ChargingStations> chargingStations = new ArrayList<>();
        for (Search.Result rs : results) {
            ChargingStations cs = new ChargingStations();
            cs.id = rs.id;
            cs.address = new ChargingStations.Address();
            Gson ason = new Gson();
            String adrs =  ason.toJson(rs.address);
            cs.address = ason.fromJson(adrs,ChargingStations.Address.class);
            cs.chargingPark = new ChargingStations.ChargingPark();
            cs.chargingPark.connectors = new ArrayList<ChargingStations.Connector>();
            for(Search.Connector con : rs.chargingPark.connectors) {
                Gson cson = new Gson();
                String connect = cson.toJson(con);
                ChargingStations.Connector c;
                c = cson.fromJson(connect, ChargingStations.Connector.class);
                cs.chargingPark.connectors.add(c);
            }
            Gson pson = new Gson();
            String pos = pson.toJson(rs.position);
            cs.position = pson.fromJson(pos,ChargingStations.Position.class);

            chargingStations.add(cs);
        }
        return  chargingStations;
    }
}

