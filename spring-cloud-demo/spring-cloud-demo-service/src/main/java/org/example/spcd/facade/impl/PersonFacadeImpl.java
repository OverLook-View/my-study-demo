package org.example.spcd.facade.impl;

import feign.Param;
import org.example.spcd.bean.Person;
import org.example.spcd.facade.PersonFacade;
import org.example.spcd.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonFacadeImpl implements PersonFacade {
    @Autowired
    private PersonService personService;
    @PostMapping("/person/age")
    @Override
    public String getPersonAge(@RequestBody Person person) {
        return person.getName()+" is "+person.getAge()+" years old this year";
    }
}
