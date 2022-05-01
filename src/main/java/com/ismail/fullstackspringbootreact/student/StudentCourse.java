package com.ismail.fullstackspringbootreact.student;

import java.time.LocalDate;
import java.util.UUID;

public class StudentCourse
{
    // student_course table

    private final UUID studentId;

    private final UUID courseId;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final Integer grade;

    // Course table

    private final String courseName;

    private final String courseDescription;

    private final String department;

    private final String teacherName;

    public StudentCourse(UUID studentId, UUID courseId, LocalDate startDate, LocalDate endDate, Integer grade, String courseName, String courseDescription, String department, String teacherName)
    {
        this.studentId = studentId;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.department = department;
        this.teacherName = teacherName;
    }

    public String getDepartment()
    {
        return department;
    }

    public UUID getStudentId()
    {
        return studentId;
    }

    public UUID getCourseId()
    {
        return courseId;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public Integer getGrade()
    {
        return grade;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getCourseDescription()
    {
        return courseDescription;
    }

    public String getTeacherName()
    {
        return teacherName;
    }

    @Override
    public String toString()
    {
        return "StudentCourse{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", grade=" + grade +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
