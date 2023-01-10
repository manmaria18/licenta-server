package com.imobiliare360.controller;

import com.imobiliare360.dto.HomeLocationDto;
import com.imobiliare360.dto.LocationDto;
import com.imobiliare360.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;


    @GetMapping(value = "/")
    public ResponseEntity<List<LocationDto>> getAll()
    {
        return new ResponseEntity<List<LocationDto>>(locationService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/homelocations")
    public ResponseEntity<List<HomeLocationDto>> getAllWithHome()
    {
        return new ResponseEntity<List<HomeLocationDto>>(locationService.getAllHomeLocations(), HttpStatus.OK);
    }

}
