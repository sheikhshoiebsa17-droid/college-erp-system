package com.college.sms.service.impl;

import com.college.sms.entity.Result;
import com.college.sms.entity.Student;
import com.college.sms.repository.ResultRepository;
import com.college.sms.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    // ================= Get All =================

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    // ================= Get By ID =================

    @Override
    public Optional<Result> getResultById(Long id) {
        return resultRepository.findById(id);
    }

    // ================= Save =================

    @Override
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    // ================= Update =================

    @Override
    public Result updateResult(Result result) {
        return resultRepository.save(result);
    }

    // ================= Delete =================

    @Override
    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    // ================= Student =================

    @Override
    public List<Result> getResultsByStudent(Student student) {
        return resultRepository.findByStudent(student);
    }

    // ================= Semester =================

    @Override
    public List<Result> getResultsBySemester(Integer semester) {
        return resultRepository.findBySemester(semester);
    }

    // ================= Student + Semester =================

    @Override
    public List<Result> getStudentSemesterResult(Student student,
                                                 Integer semester) {

        return resultRepository.findByStudentAndSemester(
                student,
                semester
        );

    }
// ================= Dashboard Analytics =================

    @Override
    public long getTotalResults() {

        return resultRepository.getTotalResults();

    }

    @Override
    public Double getAverageSGPA() {

        return resultRepository.getAverageSGPA();

    }

    @Override
    public Double getAverageCGPA() {

        return resultRepository.getAverageCGPA();

    }

    @Override
    public long getPassedStudents() {

        return resultRepository.getPassedStudents();

    }

    @Override
    public long getFailedStudents() {

        return resultRepository.getFailedStudents();

    }
    // ================= Charts =================

    @Override
    public List<Object[]> getSemesterSGPA() {

        return resultRepository.getSemesterSGPA();

    }

    @Override
    public List<Object[]> getSemesterDistribution() {

        return resultRepository.getSemesterDistribution();

    }
}