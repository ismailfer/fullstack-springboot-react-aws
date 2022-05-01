package com.ismail.fullstackspringbootreact.student;

import com.ismail.fullstackspringbootreact.exception.ApiRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
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
        List<Student> list = studentService.getAllStudents();

        return list;
    }

    @GetMapping(path = "{studentId}/courses")
    public List<StudentCourse> getAllCoursesForStudent(@PathVariable("studentId") UUID studentId)
    {
        log.info("getAllCoursesForStudent() " + studentId);

        return studentService.getAllCoursesForStudent(studentId);
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }
}
