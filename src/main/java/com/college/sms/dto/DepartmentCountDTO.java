package com.college.sms.dto;

public class DepartmentCountDTO {

    private String department;
    private Long total;

    public DepartmentCountDTO(String department, Long total) {
        this.department = department;
        this.total = total;
    }

    public String getDepartment() {
        return department;
    }

    public Long getTotal() {
        return total;
    }
}