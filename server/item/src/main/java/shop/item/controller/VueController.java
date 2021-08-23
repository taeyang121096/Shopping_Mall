package shop.item.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class VueController {

    @GetMapping("/vue")
    public String vue(){

        return "vue/index";
    }
}
