package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyService {

    private static final Logger log = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        log.debug("Вызван метод addFaculty с faculty = {}", faculty);
        if (facultyRepository.existsById(faculty.getId())) {
            return null;
        }
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        log.debug("Вызван метод getFaculty с id = {}", id);
        return facultyRepository.findById(id).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        log.debug("Вызван метод updateFaculty с faculty = {}", faculty);
        if (facultyRepository.existsById(faculty.getId())) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void deleteFaculty(long id) throws EmptyResultDataAccessException {
        log.debug("Вызван метод deleteFaculty с id = {}", id);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> filterFacultyByColor(String color) {
        log.debug("Вызван метод filterFacultyByColor с color = {}", color);
        return facultyRepository.findAll().stream().filter(s -> s.getColor().equals(color)).toList();
    }

    public Collection<Faculty> findFacultyByColorOrName(String color, String name) {
        log.debug("Вызван метод findFacultyByColorOrName с color = {} и с name = {}" , color, name);
        return facultyRepository.findFacultiesByColorContainingIgnoreCaseOrNameContainingIgnoreCase(color, name);
    }

    public String getMostLongName() {
        log.debug("Вызван метод getMostLongName");
        return facultyRepository.findAll().stream()
                .parallel()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}