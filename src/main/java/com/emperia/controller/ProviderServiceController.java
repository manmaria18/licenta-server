package com.emperia.controller;
import com.emperia.dto.ProviderServicesDto;
import com.emperia.dto.ServiceTypeDto;
import com.emperia.service.ProviderServiceService;
import com.emperia.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/providerService")
public class ProviderServiceController {

    @Autowired
    private ProviderServiceService providerServiceService;


    @PostMapping(value="/")
    public ResponseEntity<String> create(@RequestBody ProviderServicesDto providerServicesDto) {

        providerServiceService.save(providerServicesDto);

        return new ResponseEntity<String>("providerServicesDto was created with great success!", HttpStatus.OK);

    }

    @GetMapping(value="/")
    public ResponseEntity<List<ProviderServicesDto>> getAll() {
        List<ProviderServicesDto> providerServicesDtoList = providerServiceService.findAll();

        return new ResponseEntity<List<ProviderServicesDto>>(providerServicesDtoList, HttpStatus.OK);

    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        providerServiceService.delete(id);

        return new ResponseEntity<String>("Succesfully deleted providerServiceService", HttpStatus.OK);

    }

    @PutMapping(value="/")
    public ResponseEntity<String> update(@RequestBody ProviderServicesDto providerServicesDto) {
        providerServiceService.update(providerServicesDto);

        return new ResponseEntity<String>("Succesfully updated service type", HttpStatus.OK);

    }

}
