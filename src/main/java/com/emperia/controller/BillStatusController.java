package com.emperia.controller;
import com.emperia.dto.BillStatusDto;
import com.emperia.dto.BillStatusDto;
import com.emperia.service.BillStatusService;
import com.emperia.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/billStatus")
public class BillStatusController {

    @Autowired
    private BillStatusService billStatusService;


    @PostMapping(value="/")
    public ResponseEntity<String> create(@RequestBody BillStatusDto billStatusDto) {

        billStatusService.save(billStatusDto);

        return new ResponseEntity<String>("Created with great success!", HttpStatus.OK);

    }

    @GetMapping(value="/")
    public ResponseEntity<List<BillStatusDto>> getAll() {
        List<BillStatusDto> serviceTypeDtoList = billStatusService.findAll();

        return new ResponseEntity<List<BillStatusDto>>(serviceTypeDtoList, HttpStatus.OK);

    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        billStatusService.delete(id);

        return new ResponseEntity<String>("Succesfully deleted", HttpStatus.OK);

    }

    @PutMapping(value="/")
    public ResponseEntity<String> update(@RequestBody BillStatusDto serviceTypeDto) {
        billStatusService.update(serviceTypeDto);

        return new ResponseEntity<String>("Succesfully updated", HttpStatus.OK);

    }

}
