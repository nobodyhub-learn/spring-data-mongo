package com.nobodyhub.learn.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;

/**
 * @author yan_h
 */
@Configuration
@ComponentScan("com.nobodyhub.learn.mongodb")
public class MongoConfiguration extends AbstractMongoConfiguration {
    @Bean
    @Override
    public MongoClient mongoClient() {
        return new MongoClient("javis-server");
    }

    @Bean
    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate template = super.mongoTemplate();
        template.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        template.setWriteConcern(WriteConcern.MAJORITY);
        return template;
    }

    @Override
    protected String getDatabaseName() {
        return "mydatabase";
    }
}
