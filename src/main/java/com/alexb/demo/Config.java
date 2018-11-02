package com.alexb.demo;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

@SpringBootApplication
@RestController
@Slf4j
public class Config {

    public static void main(String[] args) {
        SpringApplication.run(Config.class);
    }

    @PostMapping(value = "/save")
    Person save(@RequestBody Person person) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Person>> validate = validator.validate(person);

        validate.stream()
                .map(ConstraintViolation::getMessage)
                .forEach(msg->log.info(msg));
        return person;
    }

}

@Setter
class Person {
    @NotNull
    private int age;
}

