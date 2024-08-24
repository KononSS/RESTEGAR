package ru.edu.egar.mvcdemo.services;

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
    public Optional<Student> update(long id, Student updatedStudent) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()){
        student.get().setLastName(updatedStudent.getLastName());
        student.get().setFirstName(updatedStudent.getFirstName());
        student.get().setPhone(updatedStudent.getPhone());
        student.get().setAge(updatedStudent.getAge());
        studentRepository.save(updatedStudent);
        }
        return student;
    }

    @Transactional
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
