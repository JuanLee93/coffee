package com.example.coffee.account;

import com.example.coffee.account.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountMapper {
    List<Account.Member> GetAllAccount();
    Account.ChooseBuyerSelector ChooseBuyerSelector();
}
