package com.college.sms.repository;

import com.college.sms.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;import com.college.sms.dto.SubjectPerformanceDTO;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    @Query("""
SELECT m
FROM Marks m
WHERE
LOWER(m.student.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
OR
LOWER(m.student.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
OR
LOWER(m.subject) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Marks> searchMarks(@Param("keyword") String keyword);
// Dashboard Statistics

    long countByResult(String result);

    @Query("SELECT AVG(m.totalMarks) FROM Marks m")
    Double getAverageMarks();

    @Query("SELECT MAX(m.totalMarks) FROM Marks m")
    Integer getHighestMarks();

    Marks findTopByOrderByTotalMarksDesc();
    @Query("""
SELECT new com.college.sms.dto.SubjectPerformanceDTO(

m.subject,

AVG(m.totalMarks)

)

FROM Marks m

GROUP BY m.subject

""")
    List<SubjectPerformanceDTO> getSubjectPerformance();
    @Query("""
SELECT m.grade, COUNT(m)
FROM Marks m
GROUP BY m.grade
ORDER BY m.grade
""")
    List<Object[]> getGradeDistribution();
}