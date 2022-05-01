package com.ismail.fullstackspringbootreact.student;

import com.ismail.fullstackspringbootreact.exception.ApiRequestException;
import com.ismail.fullstackspringbootreact.util.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class StudentService
{
    private final StudentDAO studentDAO;

    private final EmailValidator emailValidator;

    @Autowired
    public StudentService(StudentDAO studentDAO, EmailValidator emailValidator)
    {
        this.studentDAO = studentDAO;
        this.emailValidator = emailValidator;
    }

    public List<Student> getAllStudents()
    {
        return studentDAO.getAllStudents();
    }

    List<StudentCourse> getAllCoursesForStudent(UUID studentId)
    {
        return studentDAO.getAllCoursesForStudent(studentId);
    }

    public void addNewStudent(Student student)
    {
        addNewStudent(null, student);
    }

    public void addNewStudent(UUID studentId, Student student)
    {
        UUID newStudentId = Optional.ofNullable(studentId).orElse(UUID.randomUUID());

        // verify that email is not taken

        if (!emailValidator.test(student.getEmail()))
            throw new ApiRequestException("Invalid email address: " + student.getEmail());

        // verify the email is not taken
        if (studentDAO.isEmailTaken(student.getEmail()))
            throw new ApiRequestException("Email is taken: " + student.getEmail());

        // add student
        int statusCode = studentDAO.addNewStudent(newStudentId, student);

        System.out.println("addNewStudent: " + statusCode + ": " + student);
    }
}
