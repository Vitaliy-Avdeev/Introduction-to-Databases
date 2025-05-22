package ru.hogwarts.school.service;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositiry.FacultyRepository;


import java.util.Collection;

@Service
public class FacultyService {
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Работает метод addFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        logger.info("Работает метод getFaculty с id = {}", id);
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Работает метод editeFaculty с факультетом = {}", faculty);
        Faculty existing = facultyRepository.findById(faculty.getId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));

        existing.setName(existing.getName());
        existing.setColor(existing.getColor());

        return facultyRepository.save(existing);
    }

    public void deleteFaculty(long id) {
        logger.info("Работает метод deleteFaculty с id = {}", id);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Работает метод getFacultyByColor с цветом = {}", color);
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Работает метод getAllFaculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultyByNameAndColor(String name, String color) {
        logger.info("Работает метод getFacultyByNameAndColor с именем = {} и цветом = {}", name, color);

        return facultyRepository.findFacultyByNameAndColorIgnoreCase(name, color);
    }
}