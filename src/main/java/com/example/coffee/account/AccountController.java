package com.example.coffee.account;

import com.example.coffee.account.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/getAllAccount")
    public ResponseEntity<List<Account.Member>> GetAllAccount(){
        List<Account.Member> account = accountService.GetAllAccount();
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping(value = "/getBuyerByCurrentDate")
    public ResponseEntity<Account.ChooseBuyerForInform> GetBuyerByCurrentDate(){
        Account.ChooseBuyerForInform response = accountService.getBuyerByCurrentDate();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
