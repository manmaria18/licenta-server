package com.imobiliare360.controller;


import com.imobiliare360.dto.BillDto;
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
@RequestMapping(value = "/api/bills")

public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private ProviderService providerService;

    @GetMapping(value = "/search/{houseId}")
    public ResponseEntity<?> search(@PathVariable Long houseId)
    {
        List<BillDto> billsDtos;

        try {
            billsDtos = billService.search(houseId);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<String>("Could not fetch the bills with the given homeId", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<BillDto>>((MultiValueMap<String, String>) billsDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/mine")
    public ResponseEntity<List<BillDto>> getMyBills( @CurrentUser UserPrincipal currentUser)
    {
        return new ResponseEntity<List<BillDto>>(billService.getAllForUser(currentUser.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<BillDto>> getAll()
    {
        return new ResponseEntity<List<BillDto>>(billService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable String id)
    {

        Long idNumeric;
        try {
            idNumeric = Long.parseLong(id);
        }
        catch (Exception e)
        {
            return new ResponseEntity<String>("Please provide a numeric id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BillDto billDto;

        try {
            billDto = billService.get(idNumeric);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<String>("The bill with the provided id does not exist!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<BillDto>(billDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id,
                                    @CurrentUser UserPrincipal currentUser)
    {
        Long idNumeric;
        try {
            idNumeric = Long.parseLong(id);
        }
        catch (Exception e)
        {
            return new ResponseEntity<String>("Please provide a numeric id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BillDto billDto;

        try {
            billDto = billService.delete(idNumeric);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<String>("The bill with the provided id does not exist!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<BillDto>(billDto, HttpStatus.OK);

    }

    @PostMapping(value="/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> create(//@RequestPart("images") List<MultipartFile> images,
                                         @RequestPart("bill") BillDto billDto,
                                         Long homeId)
                                         //@CurrentUser UserPrincipal currentUser)
    {
        System.out.println(billDto);
        //System.out.println(billDto.getLocation().getLatitude());


        billService.save(billDto,homeId);

        return new ResponseEntity<String>("Bill was created with great success!", HttpStatus.OK);

    }

    @GetMapping(value="/test")
    public ResponseEntity<String> createTest()
    //@CurrentUser UserPrincipal currentUser)
    {
        BillDto billDto = new BillDto();
        billDto.setBillType(BillType.GAS);
        billDto.setSum(120);
        billDto.setDeadline(Calendar.getInstance().getTime());
        billDto.setIssueDate(Calendar.getInstance().getTime());
        List<ProviderServiceEntity> providedServices = new ArrayList<>();
        ProviderServiceEntity pse1 = new ProviderServiceEntity(BillType.ELECTRICITY,120);
        ProviderServiceEntity pse2 = new ProviderServiceEntity(BillType.GAS,120);
        providedServices.add(pse1);
        //providerServicesService.save(pse1);
        providedServices.add(pse2);
        //providerService.save(pse2);
        ProviderEntity provider = new ProviderEntity("Electrica",providedServices);
        //providerService.save(provider);
        billDto.setIssuedBy(provider);
        billDto.setHouseId(1L);
        System.out.println(billDto);
        //System.out.println(billDto.getLocation().getLatitude());


        billService.save(billDto,1L);

        return new ResponseEntity<String>("Bill was created with great success!", HttpStatus.OK);

    }

    @PutMapping(value="/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> edit(//@RequestPart("images") List<MultipartFile> images,
                                       @RequestPart("bill") BillDto billDto)
                                       //@CurrentUser UserPrincipal currentUser)
    {
        System.out.println(billDto);
        //System.out.println(homeDto.getLocation().getLatitude());


        billService.edit(billDto);

        return new ResponseEntity<String>("Bill was updated with great success!", HttpStatus.OK);

    }


}
