package com.emperia.controller;
import com.emperia.dto.ProviderDto;
import com.emperia.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    


    @PostMapping(value="/")
    public ResponseEntity<String> create(@RequestBody ProviderDto providerDto)
    {
        System.out.println(providerDto);

        providerService.save(providerDto);

        return new ResponseEntity<String>("Succesfully created", HttpStatus.OK);

    }

    @GetMapping(value="/")
    public ResponseEntity<List<ProviderDto>> getAll() {
        List<ProviderDto> ProviderDtoList = providerService.findAll();

        return new ResponseEntity<List<ProviderDto>>(ProviderDtoList, HttpStatus.OK);

    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        providerService.delete(id);

        return new ResponseEntity<String>("Succesfully deleted", HttpStatus.OK);

    }

    @PutMapping(value="/")
    public ResponseEntity<String> update(@RequestBody ProviderDto ProviderDto) {
        providerService.update(ProviderDto);

        return new ResponseEntity<String>("Succesfully updated", HttpStatus.OK);

    }

    

}
