package ru.gorbenko.controller;

import ru.gorbenko.DAO.StudentsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gorbenko.DAO.SubjectDAO;


@Controller
public class ViewController {

    private StudentsDAO studentsDAO;
    private SubjectDAO subjectDAO;


    public ViewController(@Autowired StudentsDAO studentsDAO, @Autowired SubjectDAO subjectDAO) {
        this.studentsDAO = studentsDAO;
        this.subjectDAO = subjectDAO;
    }

    @GetMapping("/students")
    public String students(Model model){
        model.addAttribute("students", studentsDAO.showAll());
        model.addAttribute("selectedstudents", studentsDAO.showSelected(1, 25));
        return "students";
    }
    @GetMapping("/subjects")
    public String subjects(Model model){
        model.addAttribute("subjects", subjectDAO.showAll());
        model.addAttribute("selectedsubjects", subjectDAO.showMaxHour());
        return "subjects";
    }
}
