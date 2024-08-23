package ru.edu.egar.mvcdemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.edu.egar.mvcdemo.entity.Student;
import ru.edu.egar.mvcdemo.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;
    private final StudentService studentService;

    @GetMapping()
    public List<Student> getStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createStudent(@RequestBody Student student) {
         studentService.saveStudent(student);
         return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Student student = studentService.findOne(id);
        studentService.deleteById(id);
        return ResponseEntity.ok().toString();
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student student = studentService.findOne(id);
        student.setLastName(studentDetails.getLastName());
        student.setFirstName(studentDetails.getFirstName());
        student.setPhone(studentDetails.getPhone());
        student.setAge(studentDetails.getAge());
        studentService.saveStudent(student);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
