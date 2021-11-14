package com.example.study.sbddj.springbootdatajpademo.repository;

import com.example.study.sbddj.springbootdatajpademo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsersRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    List<User> findByName(String name);
    List<User> findByNameAndAge(String name,Integer age);
    List<User> findByNameLike(String name);

    @Query("from User where name = ?1")
    List<User> queryByNameUseHQL(String name);
    @Query(value = "select * from user where name=?1",nativeQuery = true)
    List<User> queryByNameUseSQL(String name);
    @Query("update User set name=?1 where id=?2")
    @Modifying
    @Transactional
    void updateUserNameById(String name, Long id);


}
