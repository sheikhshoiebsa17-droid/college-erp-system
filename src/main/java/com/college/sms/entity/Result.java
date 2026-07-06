package com.college.sms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private Integer semester;

    @Column(nullable = false)
    private Integer totalCredits;

    @Column(nullable = false)
    private Double sgpa;

    @Column(nullable = false)
    private Double cgpa;

    @Column(nullable = false)
    private Integer classRank;

    @Column(nullable = false)
    private Integer departmentRank;

    @Column(nullable = false)
    private String resultStatus;

}