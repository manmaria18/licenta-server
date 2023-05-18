package com.emperia.controller;
import com.emperia.dto.ServiceTypeDto;
import com.emperia.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/serviceType")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;


    @PostMapping(value="/")
    public ResponseEntity<String> create(@RequestBody ServiceTypeDto serviceTypeDto) {
        System.out.println(serviceTypeDto);

        serviceTypeService.save(serviceTypeDto);

        return new ResponseEntity<String>("ServiceType was created with great success!", HttpStatus.OK);

    }

    @GetMapping(value="/")
    public ResponseEntity<List<ServiceTypeDto>> getAll() {
        List<ServiceTypeDto> serviceTypeDtoList = serviceTypeService.findAll();

        return new ResponseEntity<List<ServiceTypeDto>>(serviceTypeDtoList, HttpStatus.OK);

    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        serviceTypeService.delete(id);

        return new ResponseEntity<String>("Succesfully deleted service type", HttpStatus.OK);

    }

    @PutMapping(value="/")
    public ResponseEntity<String> update(@RequestBody ServiceTypeDto serviceTypeDto) {
        serviceTypeService.update(serviceTypeDto);

        return new ResponseEntity<String>("Succesfully updated service type", HttpStatus.OK);

    }

}
