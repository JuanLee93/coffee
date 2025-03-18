package com.example.coffee.account;

import com.example.coffee.account.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService {

    private final AccountMapper accountMapper;
    List<Account.Member> members = new ArrayList<>();

    public List<Account.Member> GetAllAccount(){
        List<Account.Member> members = accountMapper.getAllAccount();
        //list형태의 객체를 stream으로 출력 (외부 반복자)
        members.stream().forEach(
            s -> log.error("멤버 객체 확인 = " + s)
        );
        return members;
    }

    public Account.ChooseBuyerForInform getBuyerByCurrentDate() {
        members = accountMapper.getAllAccount();

        Account.ChooseBuyerForInform inform = new Account.ChooseBuyerForInform();

        LocalDateTime datetime = LocalDateTime.now();
        String currentDate = String.valueOf(datetime.getDayOfWeek());

        if (currentDate.equals("SATURDAY") || currentDate.equals("SUNDAY")) {
            inform.setMessage("오늘은 휴일입니다. 커피를 사는날이 아닙니다.");
            inform.setBuyerInform("없음.");
        } else {
            String buyer = getNextBuyer(); // 커피를 사야 할 사람 찾기
            inform.setMessage("오늘은 커피를 먹는날!");
            inform.setBuyerInform("오늘의 커피 계산은 " + buyer + " 님 입니다.");
        }

        inform.setDateInform("오늘의 날짜는 " + currentDate + " 입니다.");

        return inform;
    }

    // 커피를 사는 사람을 결정하는 로직
    private String getNextBuyer() {
        int buyCount = 0;

        for (Account.Member member : members) {
            if (member.isBuy()) {  // 커피를 산 사람
                buyCount++;
            } else {  // 커피를 안 산 사람
                accountMapper.updateIsBuyTrue(member.getId());
                return member.getName();
            }
        }

        // 만약 모든 사람이 커피를 샀다면, 첫 번째 사람에게 커피를 사게 함
        if (buyCount == members.size()) {
            //커피를 산 사람들의 isBuy 값을 리셋 + 첫번째 사람만 true
            accountMapper.updateIsBuyAllFalse();
            accountMapper.updateIsBuyTrue(members.get(0).getId());
            return members.get(0).getName();
        }


        return "없음."; // 예외 처리 (모든 사람이 커피를 샀다면)
    }

}
