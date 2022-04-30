package com.ismail.fullstackspringbootreact.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class StudentService
{
    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO)
    {
        this.studentDAO = studentDAO;
    }

    public List<Student> getAllStudents()
    {
        return studentDAO.getAllStudents();
    }

    public void addNewStudent(Student student)
    {
        addNewStudent(null, student);
    }

    public void addNewStudent(UUID studentId, Student student)
    {
        UUID newStudentId = Optional.ofNullable(studentId).orElse(UUID.randomUUID());

        // todo: verify that email is not taken

        // add student
       int statusCode = studentDAO.addNewStudent(newStudentId, student);

        System.out.println("addNewStudent: " + statusCode + ": " + student);
    }
}
