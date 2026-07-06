package com.college.sms.service.impl;

import com.college.sms.entity.Fee;
import com.college.sms.entity.Student;
import com.college.sms.repository.FeeRepository;
import com.college.sms.service.FeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    public FeeServiceImpl(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    // ================= CRUD =================

    @Override
    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    @Override
    public Optional<Fee> getFeeById(Long id) {
        return feeRepository.findById(id);
    }

    @Override
    public Fee saveFee(Fee fee) {
        return feeRepository.save(fee);
    }

    @Override
    public Fee updateFee(Fee fee) {
        return feeRepository.save(fee);
    }

    @Override
    public void deleteFee(Long id) {
        feeRepository.deleteById(id);
    }

    // ================= Search =================

    @Override
    public List<Fee> getFeesByStudent(Student student) {
        return feeRepository.findByStudent(student);
    }

    @Override
    public List<Fee> getFeesBySemester(Integer semester) {
        return feeRepository.findBySemester(semester);
    }

    @Override
    public List<Fee> getFeesByPaymentStatus(String paymentStatus) {
        return feeRepository.findByPaymentStatus(paymentStatus);
    }

    // ================= Dashboard =================

    @Override
    public long getTotalFeeRecords() {
        return feeRepository.getTotalFeeRecords();
    }

    @Override
    public Double getTotalFees() {
        return feeRepository.getTotalFees();
    }

    @Override
    public Double getCollectedFees() {
        return feeRepository.getCollectedFees();
    }

    @Override
    public Double getPendingFees() {
        return feeRepository.getPendingFees();
    }

    @Override
    public long getPaidStudents() {
        return feeRepository.getPaidStudents();
    }

    @Override
    public long getPendingStudents() {
        return feeRepository.getPendingStudents();
    }

}