package com.artur.engineer.controllers;

import com.artur.engineer.entities.Subject;
import com.artur.engineer.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Subject> getAllSubjects() {
        // This returns a JSON or XML with the users
        return subjectRepository.findAll();
    }

}
