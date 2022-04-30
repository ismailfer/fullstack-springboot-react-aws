package com.ismail.fullstackspringbootreact.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
// either set "api/students" here; or set spring.servlet.context-path=/api
@RequestMapping("students")
@Slf4j
public class StudentController
{
    private final StudentService studentService;

    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents()
    {
        log.info("getAllStudents()");

        if (true)
            throw new RuntimeException("Cannot get students!");

        /* timer to slow the request
        try
        {
            Thread.sleep(1000L);
        } catch (Exception e)
        {
        }
        */

        List<Student> list = studentService.getAllStudents();

        return list;
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }
}
