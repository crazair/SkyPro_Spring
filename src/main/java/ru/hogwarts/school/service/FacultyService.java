package ru.hogwarts.school.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            return null;
        }
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void deleteFaculty(long id) throws EmptyResultDataAccessException {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> filterFacultyByColor(String color) {
        return facultyRepository.findAll().stream().filter(s -> s.getColor().equals(color)).toList();
    }

    public Collection<Faculty> findFacultyByColorOrName(String color, String name) {
        return facultyRepository.findFacultiesByColorContainingIgnoreCaseOrNameContainingIgnoreCase(color, name);
    }
}