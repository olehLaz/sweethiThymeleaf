package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;
/*
http://localhost:8080/
 */
/*
    @GetMapping("/") //адрес
    public String greet(Map<String, Object> model) {
        return "greeting.html"; //greeting.html
    }

    */
    //http://localhost:8080/main
    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages =  messageRepo.findAll();
        model.put("messages", messages);

        return "main"; //main.mustache
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam(required=false, defaultValue="") String filter,
                      @RequestParam String tag,
                      Model model) {
        Message message =  new Message(text, tag);
        messageRepo.save(message);


        Iterable<Message> messages =  messageRepo.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
            Iterable<Message> messages; // List имплементирует интерфейс Iterable

            if(filter != null && !filter.isEmpty()) {
                messages = messageRepo.findByTag(filter); //возвращает List
            } else {
                messages = messageRepo.findAll(); //возвращает Iterable
            }
            model.put("messages", messages);


        return "main";
    }




}