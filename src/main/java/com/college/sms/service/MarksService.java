package com.college.sms.service;

import com.college.sms.entity.Marks;

import java.util.List;
import java.util.Optional;

public interface MarksService {

    Marks saveMarks(Marks marks);

    List<Marks> getAllMarks();

    Optional<Marks> getMarksById(Long id);

    Marks updateMarks(Marks marks);

    void deleteMarks(Long id);

}