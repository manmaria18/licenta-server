package com.emperia.controller;
import com.emperia.dto.BillDto;
import com.emperia.dto.BillIndexDto;
import com.emperia.security.CurrentUser;
import com.emperia.security.UserPrincipal;
import com.emperia.service.BillService;
import com.emperia.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping(value="/index")
    public ResponseEntity<BillDto> submitIndex(@RequestBody BillIndexDto billIndexDto,
                                               @CurrentUser UserPrincipal currentUser)
    {

        System.out.println(billIndexDto);


        BillDto newBill = paymentService.submitIndex(billIndexDto);

        return new ResponseEntity<BillDto>(newBill, HttpStatus.OK);

    }

}
