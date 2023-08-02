package com.university.studentservice.service;

import com.university.studentservice.entity.StudentDto;
import com.university.studentservice.entity.StudentResponse;

public interface StudentService {

    StudentDto addStudent(StudentDto studentDto);

    StudentResponse getStudent(long id);
}
