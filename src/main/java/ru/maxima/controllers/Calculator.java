package ru.maxima.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class Calculator {
    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "x") Double x,
                             @RequestParam(value = "y") Double y,
                             @RequestParam(value = "value") String value,
                             Model model){


        double sum = 0.0;
        switch (value){
            case "plus":
                sum = x + y;
                break;
            case "minus":
                sum = x - y;
                break;
            case "div":
                sum = x / y;
                break;
            case "um":
                sum = x * y;
                break;
        }
        model.addAttribute("sum",  sum);
        return "calculator";
    }
}
