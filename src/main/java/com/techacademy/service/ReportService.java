package com.techacademy.service;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 追加
import com.techacademy.entity.Report;
import com.techacademy.repository.Employee;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository repository) {
        this.reportRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return reportRepository.findAll();
    }

    /** ログイン者の日報のみを検索して返す */
//    public List<Report> getLoginReportList(employee) {
//        // リポジトリのfindAllメソッドを呼び出す
//        return reportRepository.findByEmployee(employee);
    }
    
    /** Reportを1件検索して返す */
    public Report getReport(Integer id) {
        return reportRepository.findById(id).get();
    }

    /** Reportの登録を行なう */
    @Transactional
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

}