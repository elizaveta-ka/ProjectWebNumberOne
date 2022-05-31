package com.example.exampleproject.controller;

import com.example.exampleproject.model.Suggestion;
import com.example.exampleproject.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SuggestionController {

    private SuggestionRepository suggestionRepository;

    @Autowired
    public SuggestionController(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    @GetMapping("/suggestion")
    public String findAll(Model model) {
        List<Suggestion> suggestions = suggestionRepository.findAll();
        model.addAttribute("suggestions", suggestions);
        return "suggestion";
    }
}
