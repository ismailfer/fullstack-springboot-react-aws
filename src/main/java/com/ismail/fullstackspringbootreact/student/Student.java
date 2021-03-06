package com.ismail.fullstackspringbootreact.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Student
{
    private final UUID studentId;

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    @NonNull
    private final String email;

    @NonNull
    private final Gender gender;

    enum Gender { MALE, FEMALE}

    // JsonProperty used to map json object to the field
    public Student(
            @JsonProperty("studentId") UUID studentId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("gender") Gender gender)
    {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }


    public UUID getStudentId()
    {
        return studentId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public Gender getGender()
    {
        return gender;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
