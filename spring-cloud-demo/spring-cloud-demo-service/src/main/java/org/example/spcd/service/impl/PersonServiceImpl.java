package org.example.spcd.service.impl;

import org.example.spcd.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public Integer getPersonAge(String name) {
        return 18;
    }
}
