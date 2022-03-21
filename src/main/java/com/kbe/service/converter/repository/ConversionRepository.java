package com.kbe.service.converter.repository;

import com.kbe.service.converter.model.Conversion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ConversionRepository extends MongoRepository<Conversion, String> {

    @Query("{id:'?0'}")
    Conversion findConversionByID(String id);

    @Query("{user:'?0'}")
    List<Conversion> findConversionsOfUser(String user);
}
