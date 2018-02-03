package com.heima.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * xuan
 * 2018/2/3
 */
@Entity
@Table(name = "t_user")
public class User2 {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 20)
    @Pattern(regexp = "[_a-zA-Z\\x{4e00}-\\x{9fa5}]{6,20}")
    private String name;

    @Min(1)
    @Max(150)
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
