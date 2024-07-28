package com.techacademy.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.constants.ErrorKinds;
import com.techacademy.constants.ErrorMessage;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping

public class ReportContoroller {


    // 日報一覧画面
    @GetMapping(value = "/reports")
    public String list(Model model) {


        return "reports/list";
    }

    // 日報詳細画面
    @GetMapping(value = "/reports/detail")//serviceつくるまでいったんdetailで接続させる
    public String detail(Model model) {


        //model.addAttribute("employee", employeeService.findByCode(code));
        return "/reports/detail";
    }

    // 日報新規登録画面
    @GetMapping(value = "/reports/new")
    public String create(@ModelAttribute Report report) {


        return "reports/new";
    }

    // 日報新規登録処理
    @PostMapping("reports/new")
    public String add(@Validated Report report, BindingResult res, Model model) {

        if(res.hasErrors()) {
            // エラーあり
            return create(report);
        }

        return "redirect:/reports";
    }

    // 従業員削除処理
    @PostMapping(value = "/reports/detail")
    public String delete(Model model) {


        return "redirect:/reports";
    }

    // 日報更新画面
    @GetMapping(value = "reports/update")
    public String edit(Report report, Model model) {


        //model.addAttribute("employee", employeeService.findByCode(code));
        return "/reports/update";
    }

    // 日報更新処理
    @PostMapping(value = "reports/update")
    public String update(@Validated Report report, BindingResult res, Model model) {

        if(res.hasErrors()) {
            // エラーあり
            return edit(report,model);
        }


        return "redirect:/reports";
    }


}
