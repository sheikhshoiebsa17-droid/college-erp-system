package com.college.sms.service;

import com.college.sms.entity.Marks;

import java.util.List;
import java.util.Optional;
import com.college.sms.dto.SubjectPerformanceDTO;

public interface MarksService {

    Marks saveMarks(Marks marks);

    List<Marks> getAllMarks();

    Optional<Marks> getMarksById(Long id);

    Marks updateMarks(Marks marks);

    void deleteMarks(Long id);

    List<Marks> searchMarks(String keyword);

    // ================= Dashboard Statistics =================

    long getTotalMarksRecords();

    long getPassedStudents();

    long getFailedStudents();

    Double getAverageMarks();

    Integer getHighestMarks();

    Marks getTopPerformer();
    List<SubjectPerformanceDTO> getSubjectPerformance();
    List<Object[]> getGradeDistribution();
}