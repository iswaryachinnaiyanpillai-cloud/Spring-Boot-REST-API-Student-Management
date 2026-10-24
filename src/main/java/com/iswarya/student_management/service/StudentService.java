package com.iswarya.student_management.service;

import com.iswarya.student_management.entity.Department;
import com.iswarya.student_management.entity.Student;
import com.iswarya.student_management.repository.DepartmentRepository;
import com.iswarya.student_management.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository,
                          DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public Student create(Student student, Long departmentId) {
        student.setId(null);
        if (studentRepository.findByEmailIgnoreCase(student.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
        student.setDepartment(department);
        return studentRepository.save(student);
    }

    public Student update(Long id, Student input, Long departmentId) {
        Student existing = getById(id);
        studentRepository.findByEmailIgnoreCase(input.getEmail()).ifPresent(found -> {
            if (!found.getId().equals(id)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
            }
        });
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));

        existing.setName(input.getName());
        existing.setEmail(input.getEmail());
        existing.setAge(input.getAge());
        existing.setDepartment(department);
        return studentRepository.save(existing);
    }

    public void delete(Long id) {
        Student student = getById(id);
        studentRepository.delete(student);
    }

    public List<Student> searchByName(String keyword) {
        return studentRepository.searchByName(keyword);
    }

    public List<Student> getByDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
        return studentRepository.findByDepartment_Id(departmentId);
    }

    public List<Student> getByDepartmentName(String name) {
        return studentRepository.findByDepartmentName(name);
    }
}
