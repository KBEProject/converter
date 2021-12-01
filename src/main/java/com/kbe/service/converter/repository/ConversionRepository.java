package com.kbe.service.converter.repository;

import com.kbe.service.converter.model.Coin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ConversionRepository extends MongoRepository<Coin, String> {

    @Query("{id:'?0'}")
    Coin findConversionByID(String id);

    @Query("{userId:'?0'}")
    List<Coin> findConersionsOfUser(String userId);
}
