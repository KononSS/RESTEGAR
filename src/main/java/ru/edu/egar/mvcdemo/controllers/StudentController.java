package ru.edu.egar.mvcdemo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.edu.egar.mvcdemo.entity.Student;
import ru.edu.egar.mvcdemo.services.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.findOne(id);
    }

    @PostMapping
    public HttpStatus createStudent(@RequestBody Student student) {
         studentService.saveStudent(student);
         return HttpStatus.CREATED;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
    @PutMapping("/{id}")
    public HttpStatus updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Optional<Student> updatedStudent = studentService.update(id, studentDetails);
        if (updatedStudent.isPresent()) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
