package com.imobiliare360.controller;
import com.imobiliare360.dto.LocationDto;
import com.imobiliare360.dto.ProviderDto;
import com.imobiliare360.dto.ProviderServicesDto;
import com.imobiliare360.dto.ServiceTypeDto;
import com.imobiliare360.service.ProviderService;
import com.imobiliare360.service.ProviderServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/providers")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProviderServiceService providerServiceService;


    @GetMapping(value = "/services/")
    public ResponseEntity<List<ProviderServicesDto>> getAll()
    {
        return new ResponseEntity<List<ProviderServicesDto>>(providerServiceService.getAll(), HttpStatus.OK);
    }


    @PostMapping(value="/")
    public ResponseEntity<String> create(@RequestBody ProviderDto providerDto)
    //@CurrentUser UserPrincipal currentUser)
    {
        System.out.println(providerDto);
        //System.out.println(billDto.getLocation().getLatitude());


        providerService.save(providerDto);

        return new ResponseEntity<String>("Provider was created with great success!", HttpStatus.OK);

    }


    @PostMapping(value="/services/")
    public ResponseEntity<String> createProviderService(@RequestBody ProviderServicesDto providerServiceDto)
    //@CurrentUser UserPrincipal currentUser)
    {
        System.out.println(providerServiceDto);
        //System.out.println(billDto.getLocation().getLatitude());


        providerServiceService.save(providerServiceDto);

        return new ResponseEntity<String>("Provider was created with great success!", HttpStatus.OK);

    }


    @GetMapping(value="/test")
    public ResponseEntity<String> createTest()
    //@CurrentUser UserPrincipal currentUser)
    {
//        ProviderDto providerDto = new ProviderDto();
//        providerDto.setBillType(BillType.GAS);
//        billDto.setSum(120);
//        billDto.setDeadline(Calendar.getInstance().getTime());
//        billDto.setIssueDate(Calendar.getInstance().getTime());
//        ProviderServiceEntity pse1 = new ProviderServiceEntity(BillType.ELECTRICITY,120);
//        List<ProviderServiceEntity> providedServices = new ArrayList<>();
//        ProviderServiceEntity pse2 = new ProviderServiceEntity(BillType.GAS,120);
//        providedServices.add(pse1);
//        providedServices.add(pse2);
//        ProviderEntity provider = new ProviderEntity("Electrica",providedServices);
//        billDto.setIssuedBy(provider);
//        billDto.setHouseId(1L);
//        System.out.println(billDto);
//        //System.out.println(billDto.getLocation().getLatitude());
//
//
//        billService.save(billDto,1L);
//
        return new ResponseEntity<String>("Bill was created with great success!", HttpStatus.OK);

    }


}
