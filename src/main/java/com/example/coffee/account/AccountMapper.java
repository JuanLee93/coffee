package com.example.coffee.account;

import com.example.coffee.account.domain.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<Account.Member> getAllAccount();

    void updateIsBuyTrue(int memberId);

    void updateIsBuyAllFalse();

}
