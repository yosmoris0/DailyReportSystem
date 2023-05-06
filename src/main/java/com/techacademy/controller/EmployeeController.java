package com.techacademy.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // 追加
import org.springframework.validation.annotation.Validated; // 追加
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());
        // user/list.htmlに画面遷移
        return "employee/list";
    }

    /** 詳細画面を表示 */
    @GetMapping("/detail/{id}/")
    public String getDetail(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // User更新画面に遷移
        return "Employee/detail";
    }


    /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // Employee登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated Employee employee, BindingResult res, Model model) {
        if(res.hasErrors()) {
            // エラーあり
            return getRegister(employee);
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        employee.setCreatedAt(timestamp);
        employee.setUpdatedAt(timestamp);
        employee.setDeleteFlag(0);
        employee.getAuthentication().setEmployee(employee);

        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getUpdate(@PathVariable Integer id, Employee employee, Model model) {
        Employee employee2 = id != null ? service.getEmployee(id) : employee;
        // Modelに登録
        model.addAttribute("employee", employee2);
        // Employee更新画面に遷移
        return "employee/update";
    }

    /** Employee更新処理 */
    @PostMapping("/update/{id}/")
    public String postUpdate(@PathVariable Integer id, @Validated Employee employee, BindingResult res, Model model) {

//        if(res.hasErrors()) {
//            // エラーあり
//            return getUpdate(null, employee, model);
//        }
        //DBから更新対象を取得
        Employee employee2 = service.getEmployee(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        employee2.setUpdatedAt(timestamp);
        employee2.setName(employee.getName());
        employee2.getAuthentication().setPassword(employee.getAuthentication().getPassword());
        employee2.getAuthentication().setRole(employee.getAuthentication().getRole());

        // Employee登録
        service.saveEmployee(employee2);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
//    @SuppressWarnings("unused")
//    private String getEmployee(Employee employee) {
//        return null;
//    }

    /** Employee論理削除処理 */
    @GetMapping("/delete/{id}/")
    public String getDelete(@PathVariable("id") Integer id, Model model)  {
        Employee employee = service.getEmployee(id);
        // Employeeのdelete_flagを更新
        employee.setDeleteFlag(1);
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

}