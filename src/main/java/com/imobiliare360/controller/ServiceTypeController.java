package com.imobiliare360.controller;
import com.imobiliare360.dto.ProviderDto;
import com.imobiliare360.dto.ProviderServicesDto;
import com.imobiliare360.dto.ServiceTypeDto;
import com.imobiliare360.service.ProviderService;
import com.imobiliare360.service.ProviderServiceService;
import com.imobiliare360.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/providers")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;



    @PostMapping(value="/serviceType")
    public ResponseEntity<String> createServiceType(@RequestBody ServiceTypeDto serviceTypeDto) {
        System.out.println(serviceTypeDto);

        serviceTypeService.save(serviceTypeDto);

        return new ResponseEntity<String>("ServiceType was created with great success!", HttpStatus.OK);

    }


}
