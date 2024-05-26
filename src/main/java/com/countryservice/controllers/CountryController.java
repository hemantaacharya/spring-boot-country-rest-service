package com.countryservice.controllers;

import com.countryservice.beans.Country;
import com.countryservice.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping
    public List<Country> getCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/id/{id}")
    public Country getCountryByID(@PathVariable(value = "id") int id) {
        return countryService.getCountryById(id);
    }

    @GetMapping("/name/{countryname}")
    public Country getCountryByName(@PathVariable(value = "countryname") String countryName) {
        return countryService.getCountryByName(countryName);
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PutMapping
    public Country updateCountry(@RequestBody Country country) {
        return countryService.updateCountry(country); // Assuming you have an update method in the service
    }

    @DeleteMapping("/{id}")
    public AddResponse deleteCountry(@PathVariable(value = "id") int id) {
        return countryService.deleteCountry(id);
    }
}