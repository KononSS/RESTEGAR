package ru.edu.egar.mvcdemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.edu.egar.mvcdemo.entity.Student;
import ru.edu.egar.mvcdemo.service.StudentService;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "index";
    }

    @GetMapping("/add_student")
    public String getAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add_student";
    }

    @PostMapping("/add_student")
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_student";
        }

        service.saveStudent(student);
        return "redirect:/";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("student", service.findOne(id));
        return "edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";

        service.update(id, student);
        return "redirect:/";
    }
}
