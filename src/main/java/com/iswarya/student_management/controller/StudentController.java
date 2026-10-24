package com.iswarya.student_management.controller;

import com.iswarya.student_management.entity.Student;
import com.iswarya.student_management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam String name) {
        return service.searchByName(name);
    }

    @GetMapping("/department/{departmentId}")
    public List<Student> getByDepartment(@PathVariable Long departmentId) {
        return service.getByDepartment(departmentId);
    }

    @GetMapping("/by-department-name")
    public List<Student> getByDepartmentName(@RequestParam String name) {
        return service.getByDepartmentName(name);
    }

    @PostMapping
    public Student create(@Valid @RequestBody Student student,
                          @RequestParam Long departmentId) {
        return service.create(student, departmentId);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id,
                          @Valid @RequestBody Student student,
                          @RequestParam Long departmentId) {
        return service.update(id, student, departmentId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
