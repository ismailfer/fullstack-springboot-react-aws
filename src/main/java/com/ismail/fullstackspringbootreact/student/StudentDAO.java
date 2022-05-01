package com.ismail.fullstackspringbootreact.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    List<Student> getAllStudents()
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
        // casting gender to gender datatype by using ::gender
        String sql = "" +
                "INSERT INTO student (student_id, first_name, last_name, email, gender) " +
                "VALUES ( ?, ?, ?, ?, ?::gender)";

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


    boolean isEmailTaken(String email)
    {
        String sql = "SELECT EXISTS ( SELECT 1 FROM student WHERE email = '" + email + "')";

        Boolean taken = jdbcTemplate.queryForObject(sql, Boolean.class);

        log.info("isEmailTaken: " + email + ": " + taken);

        return taken;

    }

    List<StudentCourse> getAllCoursesForStudent(@PathVariable("studentId") UUID studentId)
    {
        String sql = "select * from student join student_course using (student_id) join course using (course_id) where student_id = ?";

        return jdbcTemplate.query(
                sql,
                new Object[]{studentId},
                mapStudentCourseFromDb());
    }

    private RowMapper<StudentCourse> mapStudentCourseFromDb()
    {
        return (resultSet, i) ->
        {
             //(UUID studentId, UUID courseId, LocalDate startDate, LocalDate endDate, Integer grade,
            // String courseName, String courseDescription, String teacherName)

            Integer grade = Optional.ofNullable(resultSet.getString("grade"))
                    .map(Integer::parseInt)
                    .orElse(null);

            StudentCourse sc = new StudentCourse(
                    UUID.fromString(resultSet.getString("student_id")),
                    UUID.fromString(resultSet.getString("course_id")),
                    resultSet.getDate("start_date").toLocalDate(),
                    resultSet.getDate("end_date").toLocalDate(),
                    grade,
                    resultSet.getString("course_name"),
                    resultSet.getString("description"),
                    resultSet.getString("department"),
                    resultSet.getString("teacher_name")
                    );

            return sc;

        };
    }
}
