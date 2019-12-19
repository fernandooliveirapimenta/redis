package com.sample.dal;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

/**
 * Created by OmiD.HaghighatgoO on 8/21/2019.
 */

@Data
@NoArgsConstructor
@RedisHash(value = "User", timeToLive = 20)
public class User implements Serializable{

    @Id
    private Long id;

//    @Indexed
    private String name;
//    @Indexed
    private String surname;
//    @Indexed
    private String age;

    private Long ttl;


    public User(Long id, String name, String surname, String age, Long ttl) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.ttl = ttl;

    }
}
