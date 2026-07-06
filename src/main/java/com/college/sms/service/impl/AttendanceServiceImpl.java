package com.college.sms.service.impl;

import com.college.sms.entity.Attendance;
import com.college.sms.repository.AttendanceRepository;
import com.college.sms.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    @Override
    public Attendance updateAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
    @Override
    public List<Attendance> searchAttendance(String keyword) {

        return attendanceRepository
                .findByStudentFirstNameContainingIgnoreCase(keyword);

    }
    @Override
    public long getAttendanceCount() {

        return attendanceRepository.count();

    }

    @Override
    public long getPresentCountToday() {

        return attendanceRepository.countByAttendanceDateAndStatus(
                LocalDate.now(),
                "Present"
        );

    }

    @Override
    public long getAbsentCountToday() {

        return attendanceRepository.countByAttendanceDateAndStatus(
                LocalDate.now(),
                "Absent"
        );

    }

    @Override
    public double getAttendancePercentage() {

        long total = attendanceRepository.countByAttendanceDate(LocalDate.now());

        if (total == 0) {

            return 0;

        }

        long present = getPresentCountToday();

        return (present * 100.0) / total;

    }
}