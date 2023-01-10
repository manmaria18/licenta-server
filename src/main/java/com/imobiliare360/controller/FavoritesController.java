package com.imobiliare360.controller;

import com.imobiliare360.dto.FavoritesListDto;
import com.imobiliare360.security.CurrentUser;
import com.imobiliare360.security.UserPrincipal;
import com.imobiliare360.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;


    @GetMapping(value = "/mine")
    public ResponseEntity<FavoritesListDto> getAll(@CurrentUser UserPrincipal currentUser)
    {
        System.out.println("mine");
        return new ResponseEntity<FavoritesListDto>(favoritesService.getAllForUser(currentUser.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Long> like(@CurrentUser UserPrincipal currentUser,
                                       @PathVariable String id)
    {
        System.out.println("like");
        favoritesService.like(currentUser.getId(), Long.parseLong(id));
        return new ResponseEntity<Long>(Long.parseLong(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> unlike(@CurrentUser UserPrincipal currentUser,
                                       @PathVariable String id)
    {
        System.out.println("unlike");
        favoritesService.unlike(currentUser.getId(), Long.parseLong(id));
        return new ResponseEntity<Long>(Long.parseLong(id), HttpStatus.OK);
    }


}
