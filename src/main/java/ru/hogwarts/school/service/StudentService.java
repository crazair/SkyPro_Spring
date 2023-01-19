package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private Map<Long, Student> students = new HashMap<>();
    private long counter = 0;

    public Student addStudent(Student student) {
        if (students.containsValue(student)) {
            return null;
        }
        student.setId(++counter);
        students.put(counter, student);
        return student;
    }

    public Student getStudent(long id) {
        return students.get(id);
    }

    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> filterByAge(int age) {
        return students.values().stream().filter(s -> s.getAge() == age).toList();
    }
}
