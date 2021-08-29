package com.example.demo.Service.Impl;

import com.example.demo.Entiry.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService;

    Student student1, student2 ,student3;

    @BeforeEach
    void setup(){
        student1 = Student.builder().id(1).name("Esayas").gpa(3.5).build();
        student2 = Student.builder().name("Samuel").gpa(3.5).build();
        student3 = Student.builder().name("ayoub").gpa(3.8).build();
    }


    @Test
    void findAll() {
        List<Student> studentListAll = Arrays.asList(student1,student2,student3);
        Mockito.when(studentRepository.findAll()).thenReturn(studentListAll);

        assertEquals(studentListAll, studentService.findAll());

    }

    @Test
    void findById() {

        Mockito.when(studentRepository.findById(student1.getId())).thenReturn(Optional.of(student1));

        assertEquals(student1, studentService.findById(student1.getId()));

    }

    @Test
    void testFindByGpa() {
        List<Student> studentList = Arrays.asList(student1,student2);
        Mockito.when(studentRepository.findByGpa(3.5)).thenReturn(studentList);
        assertEquals(studentList, studentService.findByGpa(3.5));
    }

    @Test
    void testFindByName(){
        student2 = studentRepository.save(student2);
        assertEquals(student2, studentService.findByName("Samuel"));
    }
    
    @Test
    void save() {
        Mockito.when(studentRepository.save(student1)).thenReturn(student1);

        assertEquals(student1, studentService.save(student1));
    }

    @Test
    void update() {
        Mockito.when(studentRepository.save(student1)).thenReturn(student1);

        assertEquals(student1, studentService.update(student1));

    }

    @Test
    void delete() {
        studentService.delete(student1.getId());
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(student1.getId());

    }
}