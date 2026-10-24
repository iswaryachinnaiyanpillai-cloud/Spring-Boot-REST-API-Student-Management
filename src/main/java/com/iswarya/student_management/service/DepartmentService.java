package com.iswarya.student_management.service;

import com.iswarya.student_management.entity.Department;
import com.iswarya.student_management.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Department getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
    }

    public Department create(Department department) {
        department.setId(null);
        if (repository.findByNameIgnoreCase(department.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Department name already exists");
        }
        return repository.save(department);
    }

    public Department update(Long id, Department input) {
        Department existing = getById(id);
        repository.findByNameIgnoreCase(input.getName()).ifPresent(found -> {
            if (!found.getId().equals(id)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Department name already exists");
            }
        });
        existing.setName(input.getName());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Department department = getById(id);
        repository.delete(department);
    }

    public List<Department> search(String keyword) {
        return repository.searchByName(keyword);
    }
}
