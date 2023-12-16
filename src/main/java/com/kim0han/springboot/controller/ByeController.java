package com.kim0han.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ByeController {
    @GetMapping("/bye-string")
    @ResponseBody
    public String byeString(@RequestParam("name") String name){
        return "Bye " + name;
    }

    @GetMapping("/bye-json")
    @ResponseBody
    public Bye byeJson(@RequestParam("name") String name){
        Bye bye = new Bye();
        bye.setName(name);
        bye.setAnswer(bye.getName());

        return bye;
    }

    static class Bye {
        String name;
        String answer;

        public void setAnswer(String name){
            this.answer = "GoodBye " + name;
        }

        public String getAnswer(){
            return answer;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
