package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ReportsContoroller {

    // 日報一覧画面
    @GetMapping(value = "/reports")
    public String list(Model model) {

        //model.addAttribute("listSize", employeeService.findAll().size());
        //model.addAttribute("employeeList", employeeService.findAll());

        return "reports/list";
    }

}
