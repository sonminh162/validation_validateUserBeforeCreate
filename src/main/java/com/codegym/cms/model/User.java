package com.codegym.cms.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User implements org.springframework.validation.Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String number = user.getPhoneNumber();
        String lastName = user.getLastName();
        String firstName = user.getFirstName();
        int age = user.getAge();
        String email = user.getEmail();
        //Rỗng
        ValidationUtils.rejectIfEmpty(errors,"firstName","number.empty");
        ValidationUtils.rejectIfEmpty(errors,"lastName","number.empty");
        //validate độ dài
        if(lastName.length() > 45 || lastName.length()<5){
            errors.rejectValue("lastName","name.length");
        }
        if(firstName.length() > 45 || firstName.length()<5){
            errors.rejectValue("firstName","name.length");
        }
        if(number.length() > 11 || number.length()<10){
            errors.rejectValue("phoneNumber","number.length");
        }
        if(age<18){
            errors.rejectValue("age","age.length");
        }
        //bắt đầu bằng số 0
        if (!number.startsWith("0")){
            errors.rejectValue("phoneNumber", "number.startsWith");
        }
        //kiểu số
        if(!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("number","number.matches");
        }
        if(!email.matches("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")){
            errors.rejectValue("email","email.matches");
        }
    }
}
