package com.kbe.service.converter.config;

import com.kbe.service.converter.repository.ConversionRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = ConversionRepository.class)
@Configuration
public class MongoDBConfig {

}
