package com.college.sms.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ================= Database Errors =================

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDatabaseException(
            DataIntegrityViolationException ex,
            Model model) {

        model.addAttribute(
                "errorTitle",
                "Database Error");

        model.addAttribute(
                "errorMessage",
                "This operation cannot be completed because the record is being used in another module.");

        return "error/error";
    }

    // ================= General Errors =================

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(
            Exception ex,
            Model model) {

        model.addAttribute(
                "errorTitle",
                "Unexpected Error");

        model.addAttribute(
                "errorMessage",
                "Something went wrong. Please try again later.");

        return "error/error";
    }

}