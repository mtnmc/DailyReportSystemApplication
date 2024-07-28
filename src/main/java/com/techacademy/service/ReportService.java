package com.techacademy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.constants.ErrorKinds;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository ) {
        this.reportRepository = reportRepository;
    }

    // 日報保存
    @Transactional
    public ErrorKinds save(Report report) {

        // 重複チェック
        ErrorKinds result = reportDateCheck(report);
        if (ErrorKinds.CHECK_OK != result) {
            return result;
        }

        report.setDeleteFlg(false);

        LocalDateTime now = LocalDateTime.now();
        report.setCreatedAt(now);
        report.setUpdatedAt(now);

        reportRepository.save(report);
        return ErrorKinds.SUCCESS;
    }

    // 日報削除
    @Transactional
    public ErrorKinds delete(Integer id) {

        Report report = findById(id);
        LocalDateTime now = LocalDateTime.now();
        report.setUpdatedAt(now);
        report.setDeleteFlg(true);

        return ErrorKinds.SUCCESS;
    }


    // 日報一覧表示処理
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    // 日報一覧1人分の全件表示処理
    public List<Report> findByEmployeeCode(String employeeCode){

        return reportRepository.findByEmployeeCode(employeeCode);
    }


    // 1件を検索
    public Report findById(Integer id) {

        Optional<Report> option = reportRepository.findById(id);

        Report report = option.orElse(null);
        return report;
    }



    // 日報更新処理
    @Transactional
    public ErrorKinds update(String employeeCode, Integer id, LocalDate reportDate, String title, String content) {


        ErrorKinds result = reportUpdateCheck(id, reportDate, employeeCode);
        if(ErrorKinds.CHECK_OK != result) {
            return result;
        }

        Report report = findById(id);

        report.setReportDate(reportDate);
        report.setTitle(title);
        report.setContent(content);

        report.setUpdatedAt(LocalDateTime.now());


        reportRepository.save(report);
        return ErrorKinds.SUCCESS;
    }


    // ログイン者の入力した日付の日報データの重複
    private ErrorKinds reportDateCheck(Report report) {

        Optional<Report> option = reportRepository.findByReportDateAndEmployeeCode(report.getReportDate(), report.getEmployeeCode());
        Report checkDate = option.orElse(null);

        if(checkDate != null) {
            return ErrorKinds.DATECHECK_ERROR;
        }

        return ErrorKinds.CHECK_OK;
    }


    // 日報更新 同一の日付・従業員の日報重複チェック
    private ErrorKinds reportUpdateCheck(Integer id, LocalDate reportDate, String employeeCode) {

        Optional<Report> option = reportRepository.findByReportDateAndEmployeeCode(reportDate, employeeCode);
        Report report = option.orElse(null);


        if(report == null) {
            return ErrorKinds.CHECK_OK;
        }

        if(report.getId().equals(id)) {
            return ErrorKinds.CHECK_OK;
        }

        return ErrorKinds.DATECHECK_ERROR;
    }




}
