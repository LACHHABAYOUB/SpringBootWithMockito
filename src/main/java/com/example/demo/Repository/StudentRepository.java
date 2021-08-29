package com.example.demo.Repository;

import com.example.demo.Entiry.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    @Query("select s from Student s where s.name = ?1")
    Student findByName(String name);

    @Query("select s from Student s where s.gpa = ?1")
    List<Student> findByGpa(Double Gpa);
}
