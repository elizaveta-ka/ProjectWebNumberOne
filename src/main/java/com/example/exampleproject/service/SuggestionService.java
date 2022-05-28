package com.example.exampleproject.service;

import com.example.exampleproject.model.Suggestion;
import com.example.exampleproject.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionService {

    private SuggestionRepository suggestionRepository;

    @Autowired
    public SuggestionService(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    public List<Suggestion> findAll() {
        return suggestionRepository.findAll();
    }
}
