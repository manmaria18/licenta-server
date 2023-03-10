package com.imobiliare360.controller;

import com.imobiliare360.dto.HomeDto;
import com.imobiliare360.security.CurrentUser;
import com.imobiliare360.security.UserPrincipal;
import com.imobiliare360.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping(value = "/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name)
    {
        List<HomeDto> homeDtos;

        try {
            homeDtos = homeService.search(name);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<String>("Could not fetch the homes with the given name", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<HomeDto>>(homeDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/mine")
    public ResponseEntity<List<HomeDto>> getMyHouses( @CurrentUser UserPrincipal currentUser)
    {
        return new ResponseEntity<List<HomeDto>>(homeService.getAllForUser(currentUser.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<HomeDto>> getAll()
    {
        return new ResponseEntity<List<HomeDto>>(homeService.getAll(), HttpStatus.OK);
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

        HomeDto homeDto;

        try {
            homeDto = homeService.get(idNumeric);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<String>("The home with the provided id does not exist!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<HomeDto>(homeDto, HttpStatus.OK);
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

        HomeDto homeDto;

        try {
            homeDto = homeService.delete(idNumeric);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<String>("The home with the provided id does not exist!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<HomeDto>(homeDto, HttpStatus.OK);
    }


    @PostMapping(value="/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> create(@RequestPart("home") HomeDto homeDto,
                                         @CurrentUser UserPrincipal currentUser)
    {
        System.out.println(homeDto);
        System.out.println(homeDto.getLocation().getLatitude());


        try {
            homeService.save(homeDto, currentUser.getId());
        } catch (IOException e) {
            return new ResponseEntity<String>("Home was not created...", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Home was created with great success!", HttpStatus.OK);

    }

    @PutMapping(value="/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> edit(@RequestPart("images") List<MultipartFile> images,
                                         @RequestPart("home") HomeDto homeDto,
                                         @CurrentUser UserPrincipal currentUser)
    {
        System.out.println(homeDto);
        System.out.println(homeDto.getLocation().getLatitude());


        try {
            homeService.edit(homeDto, images, currentUser.getId());
        } catch (IOException e) {
            return new ResponseEntity<String>("Home was not created...", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Home was created with great success!", HttpStatus.OK);

    }
}
