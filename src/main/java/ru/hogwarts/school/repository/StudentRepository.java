package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findStudentByAgeBetween(int minAge, int maxAge);

    @Query(value = "select avg(age) from student", nativeQuery = true)
    float getAverageAge();
}