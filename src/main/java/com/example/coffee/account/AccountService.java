package com.example.coffee.account;

import com.example.coffee.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);
    AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public List<Account.Member> GetAllAccount(){
        List<Account.Member> member = accountMapper.GetAllAccount();
        //list형태의 객체를 stream으로 출력 (외부 반복자)
        member.stream().forEach(s -> logger.error("s :"+s));
        return member;
    }

    public Account.ChooseBuyerForInform getBuyerByCurrentDate() {
        Account.ChooseBuyerSelector chooseBuyer = accountMapper.ChooseBuyerSelector();
        Account.ChooseBuyerForInform inform = new Account.ChooseBuyerForInform();

        LocalDateTime datetime = LocalDateTime.now();

        String buyer = chooseBuyer.getName();
        String currentDate = String.valueOf(datetime.getDayOfWeek());

        if(currentDate.equals("SATURDAY") || currentDate.equals("SUNDAY")){
            currentDate = "오늘의 날짜는 "+currentDate+" 입니다.";
            inform.setMessage("오늘은 휴일입니다. 커피를 사는날이 아닙니다.");
            inform.setBuyerInform("없음.");
        } else {
            currentDate = "오늘의 날짜는 "+currentDate+" 입니다.";
            buyer = "오늘의 커피 계산은 "+buyer+" 님 입니다.";
            inform.setBuyerInform(buyer);
        }

        inform.setDateInform(currentDate);
        return inform;
    }
}
