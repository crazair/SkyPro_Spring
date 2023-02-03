package ru.hogwarts.school.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) throws EmptyResultDataAccessException {
        studentRepository.deleteById(id);
    }

    public Collection<Student> filterStudentByAge(int age) {
        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).toList();
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> filterStudentByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findStudentByAgeBetween(minAge, maxAge);
    }


    public int getCount() {
        return studentRepository.getCount();
    }

    public String getAverageAge() {
        return String.format("%.2f", studentRepository.getAverageAge()) ;
    }

    public List<Student> getFiveLast() {
        return studentRepository.getFiveLast();
    }
}