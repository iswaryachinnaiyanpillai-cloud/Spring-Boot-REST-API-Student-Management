package com.iswarya.student_management.repository;

import com.iswarya.student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailIgnoreCase(String email);

    List<Student> findByDepartment_Id(Long departmentId);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchByName(@Param("keyword") String keyword);

    @Query("SELECT s FROM Student s WHERE LOWER(s.department.name) = LOWER(:departmentName)")
    List<Student> findByDepartmentName(@Param("departmentName") String departmentName);
}
