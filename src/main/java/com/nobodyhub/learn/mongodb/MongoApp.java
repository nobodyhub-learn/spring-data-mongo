package com.nobodyhub.learn.mongodb;

import com.nobodyhub.learn.mongodb.domain.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * @author yan_h
 */
@Component
public class MongoApp {
    private static final Log log = LogFactory.getLog(MongoApp.class);

    @Autowired
    MongoTemplate mongoTemplate;

    public void execute() {

        List<Person> people = mongoTemplate.findAll(Person.class);
        log.info("Number of people= : " + people.size());
        Person p = new Person("Joe", 34);

        mongoTemplate.insert(p);
        log.info("Insert" + p);

        people = mongoTemplate.findAll(Person.class);
        log.info("Number of people= : " + people.size());

        p = mongoTemplate.findById(p.getId(), Person.class);
        log.info("Found" + p);

        people = mongoTemplate.findAll(Person.class);
        log.info("Number of people= : " + people.size());


        mongoTemplate.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
        p = mongoTemplate.findOne(query(where("name").is(("Joe"))), Person.class);
        log.info("Update" + p);

        log.info("Number of people= : " + people.size());
        mongoTemplate.remove(p);
        people = mongoTemplate.findAll(Person.class);
        log.info("Number of people= : " + people.size());

        mongoTemplate.dropCollection("person");
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MongoConfiguration.class);
        MongoApp app = (MongoApp) context.getBean("mongoApp");
        app.execute();
    }
}
