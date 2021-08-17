package org.example.spcd.controller;

import org.example.spcd.bean.Person;
import org.example.spcd.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonFeignController {

    @Autowired
    private PersonFacade personFacade;

    @RequestMapping("feign/person/age")
    public String personAge(){
        Person person = new Person();
        person.setName("Xiao Ming");
        person.setAge(18);
        return personFacade.getPersonAge(person);
    }
}
