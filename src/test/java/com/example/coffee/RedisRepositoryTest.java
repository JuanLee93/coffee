package com.example.coffee;

import com.example.coffee.account.domain.Menu;
import com.example.coffee.redis.Person;
import com.example.coffee.redis.RedisRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class RedisRepositoryTest {

    @Autowired
    private RedisRepository redisRepository;

    private static final Logger logger = LoggerFactory.getLogger(RedisRepositoryTest.class);

    @Test
    void test(){
        Person person = new Person("Park", 20);

        //값을 redis에 저장
        redisRepository.save(person);

        logger.error("Person 객체 Name 확인 : "+person.getName());

        //keyspace:id 값을 가져옴
        redisRepository.findById(person.getId());

        //Person Entity의 @RedisHash에 정의되어 있는 keyspace(people)에 속한 키의 갯수를 구함
        redisRepository.count();

        //삭제
        redisRepository.delete(person);
    }
}
