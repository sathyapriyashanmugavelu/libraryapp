package com.tw.vapasi.libraryapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class HomeController {
    @RequestMapping("/")
    String home(Model model) {
        return "home";
    }
}
