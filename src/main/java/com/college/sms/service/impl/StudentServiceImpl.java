package com.college.sms.service.impl;

import com.college.sms.entity.Student;
import com.college.sms.repository.StudentRepository;
import com.college.sms.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.college.sms.dto.DepartmentCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public long getStudentCount() {
        return studentRepository.count();
    }
    @Override
    public List<Student> getLatestStudents() {

        return studentRepository.findTop5ByOrderByIdDesc();

    }
    @Override
    public List<DepartmentCountDTO> getDepartmentStatistics() {

        return studentRepository.getDepartmentStatistics();

    }

    @Override
    public List<Student> searchStudents(String keyword) {

        return studentRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword,
                        keyword
                );
    }
    @Override
    public Page<Student> getStudentsPage(int pageNo) {

        Pageable pageable = PageRequest.of(pageNo - 1, 10);

        return studentRepository.findAll(pageable);

    }
}