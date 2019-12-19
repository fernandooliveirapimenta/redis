package com.sample.dal;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by OmiD.HaghighatgoO on 8/21/2019.
 */

@Repository
public interface UserSprRepository extends CrudRepository<User, Long>  {

     List<User> findByName(String name) ;
     List<User> findByNameAndSurname(String name , String surname) ;



}
