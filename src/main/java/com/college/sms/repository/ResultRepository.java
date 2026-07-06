package com.college.sms.repository;

import com.college.sms.entity.Result;
import com.college.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    // ================= CRUD =================

    List<Result> findByStudent(Student student);

    List<Result> findBySemester(Integer semester);

    List<Result> findByStudentAndSemester(Student student,
                                          Integer semester);

    // ================= Dashboard Analytics =================

    @Query("SELECT COUNT(r) FROM Result r")
    long getTotalResults();

    @Query("SELECT AVG(r.sgpa) FROM Result r")
    Double getAverageSGPA();

    @Query("SELECT AVG(r.cgpa) FROM Result r")
    Double getAverageCGPA();

    @Query("SELECT COUNT(r) FROM Result r WHERE r.resultStatus='Pass'")
    long getPassedStudents();

    @Query("SELECT COUNT(r) FROM Result r WHERE r.resultStatus='Fail'")
    long getFailedStudents();


    // ================= Charts =================

    @Query("""
SELECT r.semester, AVG(r.sgpa)
FROM Result r
GROUP BY r.semester
ORDER BY r.semester
""")
    List<Object[]> getSemesterSGPA();

    @Query("""
SELECT r.semester, COUNT(r)
FROM Result r
GROUP BY r.semester
ORDER BY r.semester
""")
    List<Object[]> getSemesterDistribution();

}