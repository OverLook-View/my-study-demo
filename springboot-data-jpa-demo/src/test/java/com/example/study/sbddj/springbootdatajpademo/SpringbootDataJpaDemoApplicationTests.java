package com.example.study.sbddj.springbootdatajpademo;

import com.example.study.sbddj.springbootdatajpademo.entity.User;
import com.example.study.sbddj.springbootdatajpademo.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootDataJpaDemoApplicationTests {

    @Autowired
    private UsersRepository usersRepository;
    @Test
    void contextLoads() {
        List<User> list = usersRepository.findAll();
        list.forEach(System.out::println);

        System.out.println();
        list = usersRepository.findByName("Jack");
        list.forEach(System.out::println);

        System.out.println();
        list=usersRepository.findByNameAndAge("Jone",18);
        list.forEach(System.out::println);

        System.out.println();
        list=usersRepository.findByNameLike("J%");
        list.forEach(System.out::println);
    }

    @Test
    public void test(){
        List<User> list = usersRepository.queryByNameUseHQL("Jack");
        list.forEach(System.out::println);
        System.out.println();

        List<User> list1 = usersRepository.queryByNameUseSQL("Jone");
        list1.forEach(System.out::println);
        System.out.println();

        usersRepository.updateUserNameById("Jack",2l);
    }

    @Test
    public void test1(){
        User user=new User();
        user.setName("Xiaoming");
        user.setAge(8);
        user.setEmail("xiaoming@test.com");
        user = usersRepository.save(user);
        System.out.println(user);

        user.setAge(18);
        user = usersRepository.save(user);
        System.out.println(user);

        List<User> list = usersRepository.findAll();
        list.forEach(System.out::println);
        System.out.println();

        usersRepository.deleteById(user.getId());
    }

}
