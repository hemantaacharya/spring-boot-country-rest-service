package com.countryservice.services;

import com.countryservice.beans.Country;
import com.countryservice.controllers.AddResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryService {
    private static Map<Integer, Country> countryIdMap = new HashMap<>();

    static {
        // Seed data
        Country nepalCountry = new Country(1, "Nepal", "Kathmandu");
        Country usaCountry = new Country(2, "USA", "Washington");
        Country ukCountry = new Country(3, "UK", "London");
        Country chinaCountry = new Country(4, "China", "Beijing");

        countryIdMap.put(1, nepalCountry);
        countryIdMap.put(2, usaCountry);
        countryIdMap.put(3, ukCountry);
        countryIdMap.put(4, chinaCountry);
    }

    public List<Country> getAllCountries() {
        return new ArrayList<>(countryIdMap.values());
    }

    public Country getCountryById(int id) {
        return countryIdMap.get(id);
    }

    public Country getCountryByName(String countryName) {
        return countryIdMap.values().stream()
                .filter(country -> country.getCountryName().equalsIgnoreCase(countryName))
                .findFirst()
                .orElse(null);
    }

    public Country addCountry(Country country) {
        int id = getMaxId();
        country.setId(id);
        countryIdMap.put(id, country);
        return country;
    }

    // Utility method to get max id and ensure unique id generation
    private static int getMaxId() {
        return countryIdMap.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }

    public Country updateCountry(Country country) {
        if (countryIdMap.containsKey(country.getId())) {
            countryIdMap.put(country.getId(), country);
            return country;
        }
        return null; // Indicate that the country was not found
    }

    public AddResponse deleteCountry(int id) {
        Country removedCountry = countryIdMap.remove(id);
        AddResponse res = new AddResponse();
        if (removedCountry != null) {
            res.setMsg("Country deleted.");
            res.setId(id);
        } else {
            res.setMsg("Country with ID " + id + " not found.");
        }
        return res;
    }
}