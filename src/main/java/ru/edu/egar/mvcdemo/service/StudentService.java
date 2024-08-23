package ru.edu.egar.mvcdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.edu.egar.mvcdemo.entity.Student;
import ru.edu.egar.mvcdemo.repo.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final StudentRepository studentRepository;

    @Transactional
    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student findOne(long id) {
        Optional<Student> foundPerson = studentRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void update(long id, Student updatedStudent) {
        updatedStudent.setId(id);
        studentRepository.save(updatedStudent);
    }

    @Transactional
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
