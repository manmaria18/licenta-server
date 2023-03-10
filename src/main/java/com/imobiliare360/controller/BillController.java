package com.imobiliare360.controller;


import com.imobiliare360.dto.BillDto;
import com.imobiliare360.dto.HomeDto;
import com.imobiliare360.entity.BillType;
import com.imobiliare360.security.CurrentUser;
import com.imobiliare360.security.UserPrincipal;
import com.imobiliare360.service.BillService;
import com.imobiliare360.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/bills")

public class BillController {
    @Autowired
    private BillService billService;

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
        billDto.setIssuedBy("Eon");
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
