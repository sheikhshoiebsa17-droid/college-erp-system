package com.college.sms.controller;

import com.college.sms.service.AttendanceService;
import com.college.sms.service.CourseService;
import com.college.sms.service.FacultyService;
import com.college.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final StudentService studentService;
    private final FacultyService facultyService;
    private final CourseService courseService;
    private final AttendanceService attendanceService;

    public DashboardController(StudentService studentService,
                               FacultyService facultyService,
                               CourseService courseService,
                               AttendanceService attendanceService) {

        this.studentService = studentService;
        this.facultyService = facultyService;
        this.courseService = courseService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        // Statistics
        model.addAttribute("studentCount",
                studentService.getStudentCount());

        model.addAttribute("facultyCount",
                facultyService.getFacultyCount());

        model.addAttribute("courseCount",
                courseService.getCourseCount());

        // Latest Students
        model.addAttribute("latestStudents",
                studentService.getLatestStudents());

        // Latest Faculty
        model.addAttribute("latestFaculty",
                facultyService.getLatestFaculty());

        // Attendance Statistics
        model.addAttribute("attendanceCount",
                attendanceService.getAttendanceCount());

        model.addAttribute("presentToday",
                attendanceService.getPresentCountToday());

        model.addAttribute("absentToday",
                attendanceService.getAbsentCountToday());

        model.addAttribute("attendancePercentage",
                attendanceService.getAttendancePercentage());
        model.addAttribute(
                "departmentStats",
                studentService.getDepartmentStatistics());
        return "dashboard/dashboard";
    }

}