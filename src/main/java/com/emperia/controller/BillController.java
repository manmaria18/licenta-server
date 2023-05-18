package com.emperia.controller;


import com.emperia.dto.*;
import com.emperia.security.CurrentUser;
import com.emperia.security.UserPrincipal;
import com.emperia.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/bills")

public class BillController {
    @Autowired
    private BillService billService;


    @PostMapping(value="/")
    public ResponseEntity<String> create(@RequestBody BillDto billDto)
    {

        billService.save(billDto);

        return new ResponseEntity<String>("Bill was created with great success!", HttpStatus.OK);

    }

    @PutMapping(value="/")
    public ResponseEntity<String> edit(@RequestBody BillDto billDto)
    {
        System.out.println(billDto);

        billService.edit(billDto);

        return new ResponseEntity<String>("Bill was updated with great success!", HttpStatus.OK);

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


    @GetMapping(value = "/search/{houseId}")
    public ResponseEntity<?> searchByHouseId(@PathVariable Long houseId)
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

    @GetMapping(value = "/")
    public ResponseEntity<List<BillDto>> getAll()
    {
        return new ResponseEntity<List<BillDto>>(billService.getAll(), HttpStatus.OK);
    }


    @GetMapping(value = "/mine")
    public ResponseEntity<List<BillDto>> getMyBills( @CurrentUser UserPrincipal currentUser)
    {
        return new ResponseEntity<List<BillDto>>(billService.getAllForUser(currentUser.getId()), HttpStatus.OK);
    }

    @GetMapping(value ="/generate/all")
    public ResponseEntity<String> generateAllBills(){

         billService.generateAllBills();

        return new ResponseEntity<String>("All Bills generated succesfully", HttpStatus.OK);
    }

}
