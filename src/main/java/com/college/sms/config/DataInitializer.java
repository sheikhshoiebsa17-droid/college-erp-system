package com.college.sms.config;

import com.college.sms.entity.Course;
import com.college.sms.entity.Faculty;
import com.college.sms.entity.Student;
import com.college.sms.entity.User;
import com.college.sms.repository.CourseRepository;
import com.college.sms.repository.FacultyRepository;
import com.college.sms.repository.StudentRepository;
import com.college.sms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner initDefaultData(
            UserRepository userRepository,
            CourseRepository courseRepository,
            FacultyRepository facultyRepository,
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Admin Users
            if (!userRepository.existsByUsername("Sheikh")) {
                User sheikh = new User();
                sheikh.setUsername("Sheikh");
                sheikh.setPassword(passwordEncoder.encode("Shoieb@27#09"));
                sheikh.setRole("ADMIN");
                sheikh.setEnabled(true);
                userRepository.save(sheikh);
                log.info("Default admin user 'Sheikh' created successfully.");
            }

            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                admin.setEnabled(true);
                userRepository.save(admin);
                log.info("Default admin user 'admin' created successfully.");
            }

            // 2. Courses
            if (courseRepository.count() == 0) {
                Course c1 = new Course(null, "CS301", "Algorithm design Analysis", "CSE", "3", 4, "dr gayatri", "Its based on design analysis");
                Course c2 = new Course(null, "CS302", "Data Structures", "ECE", "2", 3, "Dr. Veer", "Its a future usable subject");
                Course c3 = new Course(null, "IS306", "BDMS", "ISE", "3", 4, "Dr. Habib", "Hi its a imp subject used for database");
                Course c4 = new Course(null, "IS304", "Operating System", "CSE", "3", 3, "Dr shukala", "Its also a database subject");
                courseRepository.saveAll(List.of(c1, c2, c3, c4));
                log.info("Default courses initialized successfully.");
            }

            // 3. Faculty
            if (facultyRepository.count() == 0) {
                Faculty f1 = new Faculty(null, "Sujay", "DS", "sujay456@gmail.com", "7512486321", "Male", "ME", "HOD Dept of ME");
                Faculty f2 = new Faculty(null, "Uma", "manu", "umamanu@gmail.com", "4568712365", "Female", "EEE", "Assistant professor");
                Faculty f3 = new Faculty(null, "Mahantesh", "VR", "manta23@gmail.com", "4528796541", "Male", "ME", "HOD Dept of ME");
                Faculty f4 = new Faculty(null, "Shivu", "kumar", "shivu45@gmail.com", "4562136524", "Male", "CSE", "Assistant professor");
                facultyRepository.saveAll(List.of(f1, f2, f3, f4));
                log.info("Default faculty initialized successfully.");
            }

            // 4. Students
            if (studentRepository.count() == 0) {
                Student s1 = new Student(null, "sheikhshoieb", "Ahamad", "sheikhshoiebsa17@gmail.com", "7676123458", "Nayakanahatty , Challakere", "Male", "ISE", "6");
                Student s2 = new Student(null, "ayan", "ssr", "ayan123@gmail.com", "5498724621", "nayandahalli", "Male", "CSE", "5");
                Student s3 = new Student(null, "vishwas", "Gavc", "vishwas@gmail.com", "1657972145", "Holalkere,chitradurga", "Male", "EEE", "4");
                Student s4 = new Student(null, "Surya", "Sans", "surya34@gmail.com", "1452639875", "Summanahalli, Bengaluru", "Male", "CIVIL", "5");
                Student s5 = new Student(null, "Surabhi", "Rack", "surabhi397@gmail.com", "4562879622", "Sunkadakatte", "Female", "CSE", "5");
                Student s6 = new Student(null, "raya", "sr", "sraya@gmail.com", "5465879123", "Bengaluru", "Male", "ECE", "7");
                Student s7 = new Student(null, "Mohammed", "Vasim", "vasim@gmail.com", "8296999564", "Mahadevapura", "Male", "EEE", "6");
                studentRepository.saveAll(List.of(s1, s2, s3, s4, s5, s6, s7));
                log.info("Default students initialized successfully.");
            }
        };
    }
}
