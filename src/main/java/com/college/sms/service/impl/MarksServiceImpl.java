package com.college.sms.service.impl;

import com.college.sms.entity.Marks;
import com.college.sms.repository.MarksRepository;
import com.college.sms.service.MarksService;
import org.springframework.stereotype.Service;
import com.college.sms.dto.SubjectPerformanceDTO;

import java.util.List;
import java.util.Optional;

@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;

    public MarksServiceImpl(MarksRepository marksRepository) {

        this.marksRepository = marksRepository;

    }

    @Override
    public Marks saveMarks(Marks marks) {

        return marksRepository.save(marks);

    }

    @Override
    public List<Marks> getAllMarks() {

        return marksRepository.findAll();

    }

    @Override
    public Optional<Marks> getMarksById(Long id) {

        return marksRepository.findById(id);

    }

    @Override
    public Marks updateMarks(Marks marks) {

        return marksRepository.save(marks);

    }

    @Override
    public void deleteMarks(Long id) {

        marksRepository.deleteById(id);

    }

    @Override
    public List<Marks> searchMarks(String keyword) {

        return marksRepository.searchMarks(keyword);

    }
    @Override
    public long getTotalMarksRecords() {

        return marksRepository.count();

    }

    @Override
    public long getPassedStudents() {

        return marksRepository.countByResult("Pass");

    }

    @Override
    public long getFailedStudents() {

        return marksRepository.countByResult("Fail");

    }

    @Override
    public Double getAverageMarks() {

        return marksRepository.getAverageMarks();

    }

    @Override
    public Integer getHighestMarks() {

        return marksRepository.getHighestMarks();

    }

    @Override
    public Marks getTopPerformer() {

        return marksRepository.findTopByOrderByTotalMarksDesc();

    }
    @Override
    public List<SubjectPerformanceDTO> getSubjectPerformance() {

        return marksRepository.getSubjectPerformance();

    }
    @Override
    public List<Object[]> getGradeDistribution() {

        return marksRepository.getGradeDistribution();

    }
}