package com.college.sms.repository;

import com.college.sms.entity.Fee;
import com.college.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

    // ================= Student =================

    List<Fee> findByStudent(Student student);

    List<Fee> findBySemester(Integer semester);

    List<Fee> findByPaymentStatus(String paymentStatus);

    // ================= Dashboard =================

    @Query("SELECT COUNT(f) FROM Fee f")
    long getTotalFeeRecords();

    @Query("SELECT SUM(f.totalFee) FROM Fee f")
    Double getTotalFees();

    @Query("SELECT SUM(f.paidAmount) FROM Fee f")
    Double getCollectedFees();

    @Query("SELECT SUM(f.balanceAmount) FROM Fee f")
    Double getPendingFees();

    @Query("SELECT COUNT(f) FROM Fee f WHERE f.paymentStatus='Paid'")
    long getPaidStudents();

    @Query("SELECT COUNT(f) FROM Fee f WHERE f.paymentStatus='Pending'")
    long getPendingStudents();

}