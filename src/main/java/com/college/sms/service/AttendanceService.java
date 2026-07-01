package com.college.sms.service;

import com.college.sms.entity.Attendance;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    Attendance saveAttendance(Attendance attendance);

    List<Attendance> getAllAttendance();

    Optional<Attendance> getAttendanceById(Long id);

    Attendance updateAttendance(Attendance attendance);

    void deleteAttendance(Long id);
}