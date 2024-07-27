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

    String kariname = "煌木　太郎(仮)";
    String karidate = "2024-7-27";
    String kariredate = "20231023(仮)";
    String karititle = "煌木　太郎の記載、タイトル(仮)";
    String karimemo = "煌木　太郎の記載、今日は暑かった。(仮)";


    // 日報一覧画面
    @GetMapping(value = "/reports")
    public String list(Model model) {

        model.addAttribute("kariname", kariname);
        model.addAttribute("karidate", karidate);
        model.addAttribute("karititle", karititle);

        return "reports/list";
    }

    // 日報詳細画面
    @GetMapping(value = "/reports/detail")//serviceつくるまでいったんdetailで接続させる
    public String detail(Model model) {

        model.addAttribute("kariname", kariname);
        model.addAttribute("karititle", karititle);
        model.addAttribute("karimemo", karimemo);
        model.addAttribute("karidate", karidate);
        model.addAttribute("kariredate", kariredate);


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

        model.addAttribute("kariname", kariname);
        model.addAttribute("karititle", karititle);
        model.addAttribute("karimemo", karimemo);
        model.addAttribute("karidate", karidate);
        model.addAttribute("kariredate", kariredate);

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
