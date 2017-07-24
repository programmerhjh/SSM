package com.ssm.model;

import Validator.ValidatorGroup1;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by acer on 2017/6/24.
 */
@Table(name = "user")
public class User {

    @Id
    @NotNull(groups = {ValidatorGroup1.class})
    private int id;
    @NotNull(groups = {ValidatorGroup1.class})
    private String name;
    @NotNull(groups = {ValidatorGroup1.class})
    private String password;


    private String phone;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date birth;
    private String description;
    private Integer age;
    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", description='" + description + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
