package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "ดรัณภพ สุริเตอร์ ");
        model.addAttribute("studentID", "673380402-4");
        return "home"; // ไม่ใช่ path ไฟล์ แค่ "ชื่อ view" เชิงตรรกะเท่านั้น
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("message", "ชื่อดรัณภพ สุริเตอร์");
        return "about"; // ไม่ใช่ path ไฟล์ แค่ "ชื่อ view" เชิงตรรกะเท่านั้น
    }
}