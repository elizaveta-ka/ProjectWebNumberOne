package com.example.exampleproject.controller;

import com.example.exampleproject.model.Review;
import com.example.exampleproject.model.Suggestion;
import com.example.exampleproject.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SuggestionController {

    private SuggestionService suggestionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/suggestion")
    public String findAll(Model model) {
        List<Suggestion> suggestions = suggestionService.findAll();
        model.addAttribute("suggestions", suggestions);
        return "suggestion";
    }
}
