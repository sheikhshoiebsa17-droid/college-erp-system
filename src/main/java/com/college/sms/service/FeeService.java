package com.college.sms.service;

import com.college.sms.entity.Fee;
import com.college.sms.entity.Student;

import java.util.List;
import java.util.Optional;

public interface FeeService {

    // ================= CRUD =================

    List<Fee> getAllFees();

    Optional<Fee> getFeeById(Long id);

    Fee saveFee(Fee fee);

    Fee updateFee(Fee fee);

    void deleteFee(Long id);

    // ================= Search =================

    List<Fee> getFeesByStudent(Student student);

    List<Fee> getFeesBySemester(Integer semester);

    List<Fee> getFeesByPaymentStatus(String paymentStatus);

    // ================= Dashboard =================

    long getTotalFeeRecords();

    Double getTotalFees();

    Double getCollectedFees();

    Double getPendingFees();

    long getPaidStudents();

    long getPendingStudents();

}