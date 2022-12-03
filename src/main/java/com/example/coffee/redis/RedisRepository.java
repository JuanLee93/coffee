package com.example.coffee.redis;

import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<Person, String> {
}
