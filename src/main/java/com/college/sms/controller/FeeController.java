package com.college.sms.controller;

import com.college.sms.entity.Fee;
import com.college.sms.service.FeeService;
import com.college.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fees")
public class FeeController {

    private final FeeService feeService;
    private final StudentService studentService;

    public FeeController(FeeService feeService,
                         StudentService studentService) {

        this.feeService = feeService;
        this.studentService = studentService;
    }

    // ================= View All Fees =================

    @GetMapping
    public String listFees(Model model) {

        model.addAttribute(
                "fees",
                feeService.getAllFees());

        return "fees/fees";
    }

    // ================= Add Fee =================

    @GetMapping("/new")
    public String addFeeForm(Model model) {

        model.addAttribute(
                "fee",
                new Fee());

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "fees/fee-form";
    }

    // ================= Save Fee =================

    @PostMapping("/save")
    public String saveFee(@ModelAttribute Fee fee) {

        feeService.saveFee(fee);

        return "redirect:/fees";
    }

    // ================= Edit Fee =================

    @GetMapping("/edit/{id}")
    public String editFee(@PathVariable Long id,
                          Model model) {

        Fee fee = feeService.getFeeById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid Fee Id : " + id));

        model.addAttribute("fee", fee);

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "fees/fee-form";
    }

    // ================= Update Fee =================

    @PostMapping("/update")
    public String updateFee(@ModelAttribute Fee fee) {

        feeService.updateFee(fee);

        return "redirect:/fees";
    }

    // ================= Delete Fee =================

    @GetMapping("/delete/{id}")
    public String deleteFee(@PathVariable Long id) {

        feeService.deleteFee(id);

        return "redirect:/fees";
    }

}