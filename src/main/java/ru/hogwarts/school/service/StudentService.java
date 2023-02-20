package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        log.debug("Вызван метод addStudent c student = {}", student);
        return studentRepository.save(student);
    }

    public Student getStudent(long id) {
        log.debug("Вызван метод getStudent c id = {}", id);
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Student student) {
        log.debug("Вызван метод updateStudent c student = {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) throws EmptyResultDataAccessException {
        log.debug("Вызван метод deleteStudent c id = {}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> filterStudentByAge(int age) {
        log.debug("Вызван метод filterStudentByAge c age = {}", age);
        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).toList();
    }

    public Collection<Student> getAllStudents() {
        log.debug("Вызван метод getAllStudents ");
        return studentRepository.findAll();
    }

    public Collection<Student> filterStudentByAgeBetween(int minAge, int maxAge) {
        log.debug("Вызван метод filterStudentByAgeBetween c minAge = {} и с maxAge = {}", minAge, maxAge);
        return studentRepository.findStudentByAgeBetween(minAge, maxAge);
    }

    public String getAverageAge() {
        log.debug("Вызван метод getAverageAge");
        Double result = studentRepository.findAll().stream()
                .parallel()
                .mapToDouble(Student::getAge)
                .average().orElse(0);
        return String.format("%.1f", result);
    }

    public Collection<String> getStudentsStartsWithA() {
        log.debug("Вызван метод getStudentsStartsWithA");
        return studentRepository.findAll().stream()
                .parallel()
                .map(a -> a.getName().toUpperCase())
                .filter(a -> a.startsWith("A"))
                .sorted()
                .toList();
    }

}