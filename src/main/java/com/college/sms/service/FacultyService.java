package com.college.sms.service;

import com.college.sms.entity.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyService {

    Faculty saveFaculty(Faculty faculty);

    List<Faculty> getAllFaculty();

    Optional<Faculty> getFacultyById(Long id);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(Long id);
}