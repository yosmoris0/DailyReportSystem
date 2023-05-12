package com.techacademy.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // 追加
import org.springframework.validation.annotation.Validated; // 追加
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {

    private final ReportService service;
    public ReportController(ReportService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    // list.htmlでログイン氏名を表示させるため、UserDetailをモデルに突っ込んで、getName()してemployeenameと命名
    public String getList(Model model,@AuthenticationPrincipal UserDetail userDetail) {
        model.addAttribute("employeename", userDetail.getEmployee().getName());
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getReportList());
        // user/list.htmlに画面遷移
        return "report/list";
    }

    /** 詳細画面を表示 */
    @GetMapping("/detail/{id}/")
    // detail.htmlでログイン氏名を表示させるため、UserDetailをモデルに突っ込んで、getName()してemployeenameと命名
    public String getDetail(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal UserDetail userDetail) {
        model.addAttribute("employeename", userDetail.getEmployee().getName());
        // Modelに登録
        model.addAttribute("report", service.getReport(id));
        // User更新画面に遷移
        return "report/detail";
    }

    /** Report新規登録画面を表示 */
    @GetMapping("/register")
    // register.htmlでログイン氏名を表示させるため、UserDetailをモデルに突っ込んで、getName()してemployeenameと命名
    public String getRegister(@ModelAttribute Report report, Model model, @AuthenticationPrincipal UserDetail userDetail) {
        model.addAttribute("employeename", userDetail.getEmployee().getName());

        // Employee登録画面に遷移
        return "report/register";
    }

    /** Report新規登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated Report report, BindingResult res, Model model, @AuthenticationPrincipal UserDetail userDetail) {
//        if(res.hasErrors()) {
//            // エラーあり
//            return getRegister(employee);
//        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        report.setCreatedAt(timestamp);
        report.setUpdatedAt(timestamp);
//        report.setEmployeeId(userDetail.getEmployee().getId());

        // Employee登録
        service.saveReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }

    /** Report更新画面を表示 */
    @GetMapping("/update/{id}/")
    // update.htmlでログイン氏名を表示させるため、UserDetailをモデルに突っ込んで、getName()してemployeenameと命名
    public String getUpdate(@PathVariable Integer id, Report report, Model model, @AuthenticationPrincipal UserDetail userDetail) {
        model.addAttribute("employeename", userDetail.getEmployee().getName());
        Report report2 = id != null ? service.getReport(id) : report;
        // Modelに登録
        model.addAttribute("report", report2);
        // Employee更新画面に遷移
        return "report/update";
    }

    /** Employee更新処理 */
    @PostMapping("/update/{id}/")
    public String postUpdate(@PathVariable Integer id, @Validated Report report, BindingResult res, Model model) {

//        if(res.hasErrors()) {
//            // エラーあり
//            return getUpdate(null, employee, model);
//        }
        //DBから更新対象を取得
        Report report2 = service.getReport(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        report2.setUpdatedAt(timestamp);

        // Report登録
        service.saveReport(report2);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
        }

}