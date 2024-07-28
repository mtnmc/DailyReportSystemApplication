package com.techacademy.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techacademy.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{

    List<Report> findByEmployeeCode(String employeeCode);

    Optional<Report> findByReportDateAndEmployeeCode(LocalDate reportDate, String employeeCode);

}
