package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        return student!=null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getStudents() {
        Collection<Student> students = studentService.getAllStudents();
        return !students.isEmpty() ? ResponseEntity.ok(students) : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student temp = studentService.updateStudent(student);
        return temp!=null ? ResponseEntity.ok(temp) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<?> filterByAge(@PathVariable int age) {
        Collection<Student> temp = studentService.filterStudentByAge(age);
        if (temp.isEmpty()) {
            return new ResponseEntity<>("Нет студентов такого возраста", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(temp);
    }
}
