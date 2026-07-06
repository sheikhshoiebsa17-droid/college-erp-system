package com.college.sms.service;

import com.college.sms.entity.Result;
import com.college.sms.entity.Student;

import java.util.List;
import java.util.Optional;

public interface ResultService {

    // ================= CRUD =================

    List<Result> getAllResults();

    Optional<Result> getResultById(Long id);

    Result saveResult(Result result);

    Result updateResult(Result result);

    void deleteResult(Long id);

    // ================= Student =================

    List<Result> getResultsByStudent(Student student);

    // ================= Semester =================

    List<Result> getResultsBySemester(Integer semester);

    // ================= Student + Semester =================

    List<Result> getStudentSemesterResult(Student student,
                                          Integer semester);


    // ================= Dashboard Analytics =================

    long getTotalResults();

    Double getAverageSGPA();

    Double getAverageCGPA();

    long getPassedStudents();

    long getFailedStudents();

    // ================= Charts =================

    List<Object[]> getSemesterSGPA();

    List<Object[]> getSemesterDistribution();
}