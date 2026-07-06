package com.college.sms.dto;

public class SubjectPerformanceDTO {

    private String subject;

    private Double averageMarks;

    public SubjectPerformanceDTO(String subject,
                                 Double averageMarks) {

        this.subject = subject;
        this.averageMarks = averageMarks;

    }

    public String getSubject() {

        return subject;

    }

    public Double getAverageMarks() {

        return averageMarks;

    }

}