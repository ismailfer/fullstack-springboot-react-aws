package com.ismail.fullstackspringbootreact.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class StudentDAO
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> getAllStudents()
    {
        /*
        List<Student> list = List.of(
                new Student(UUID.randomUUID(), "James", "Bond", "james.bond@gmail.com", Student.Gender.MALE),
                new Student(UUID.randomUUID(), "Elisa", "Tamara", "elisa.tamara@gmail.com", Student.Gender.FEMALE)
        );

        return list;
         */

        String sql = "" +
                "SELECT " +
                "  student_id, " +
                "  first_name, " +
                "  last_name, " +
                "  email, " +
                "  gender " +
                "FROM student order by first_name, last_name";

        //System.out.println(sql);

        //sql = "select * from student";

        //sql = "select   student_id,  first_name,last_name,email,gender from student";

        List<Student> list = jdbcTemplate.query(sql, getStudentRowMapper());

        return list;
    }

    int addNewStudent(UUID studentId, Student student)
    {
        String sql = "" +
                "INSERT INTO student (student_id, first_name, last_name, email, gender) " +
                "VALUES ( ?, ?, ?, ?, ?)";

        int updateStatus = jdbcTemplate.update(sql, studentId, student.getFirstName(), student.getLastName(), student.getEmail(), student.getGender().name()
                .toUpperCase());

        return updateStatus;
    }


    private RowMapper<Student> getStudentRowMapper()
    {
        return (resultSet, i) -> {

            UUID studentId = UUID.fromString(resultSet.getString("student_id"));
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String emailName = resultSet.getString("email");
            Student.Gender gender = Student.Gender.valueOf(resultSet.getString("gender").toUpperCase());

            Student student = new Student(studentId, firstName, lastName, emailName, gender);

            return student;
        };
    }


}
