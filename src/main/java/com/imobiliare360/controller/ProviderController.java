package com.imobiliare360.controller;
import com.imobiliare360.dto.BillDto;
import com.imobiliare360.dto.ProviderDto;
import com.imobiliare360.entity.BillType;
import com.imobiliare360.entity.ProviderServiceEntity;
import com.imobiliare360.entity.ProviderEntity;
import com.imobiliare360.security.CurrentUser;
import com.imobiliare360.security.UserPrincipal;
import com.imobiliare360.service.BillService;
import com.imobiliare360.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/providers")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @PostMapping(value="/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> create(//@RequestPart("images") List<MultipartFile> images,
                                         @RequestPart("provider") ProviderDto providerDto,
                                         Long providerId)
    //@CurrentUser UserPrincipal currentUser)
    {
        System.out.println(providerDto);
        //System.out.println(billDto.getLocation().getLatitude());


        providerService.save(providerDto,providerId);

        return new ResponseEntity<String>("Bill was created with great success!", HttpStatus.OK);

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
