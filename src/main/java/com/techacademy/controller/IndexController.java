package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
//@RequestMapping("/")
public class IndexController {

    private final ReportService service;
    public IndexController(ReportService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/")
    // list.htmlでログイン氏名を表示させるため、UserDetailをモデルに突っ込んで、getName()してemployeenameと命名
    public String getList(Model model,@AuthenticationPrincipal UserDetail userDetail) {
        model.addAttribute("employeename", userDetail.getEmployee().getName());
        // ログイン者の日報のみををModelに登録
        model.addAttribute("reportlist", service.getLoginReportList(userDetail.getEmployee()));
        // user/list.htmlに画面遷移
        return "/index";
    }
}