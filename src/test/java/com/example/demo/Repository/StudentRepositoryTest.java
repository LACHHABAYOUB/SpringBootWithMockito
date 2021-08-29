package com.example.demo.Repository;

import com.example.demo.Entiry.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    Student student1, student2 ,student3;

    @BeforeEach
    void setup(){
        student1 = Student.builder().name("Esayas").gpa(3.5).build();
        student2 = Student.builder().name("Samuel").gpa(3.5).build();
        student3 = Student.builder().name("ayoub").gpa(3.8).build();

    }

    @Test
    void testFindAll(){

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        List<Student> studentListAll = Arrays.asList(student1,student2,student3);

        assertEquals(studentListAll, studentRepository.findAll());

    }

    @Test
    void testFindByGpa(){

        studentRepository.save(student1);
        studentRepository.save(student2);
        List<Student> studentList = Arrays.asList(student1,student2);

        assertEquals(studentList, studentRepository.findByGpa(3.5));

    }

    @Test
    void testFindById(){

        student1 = studentRepository.save(student1);
        assertEquals(student1, studentRepository.findById(student1.getId()).get());

    }

    @Test
    void testFindByName(){

        student2 = studentRepository.save(student2);
        assertEquals(student2, studentRepository.findByName("Samuel"));
    }



    @Test
    void testSave(){
        studentRepository.save(student1);
        assertEquals(1, studentRepository.findAll().size() );

    }

    @Test
    void testUpdate(){

//        assertEquals(student1, studentRepository.save(student1));
        Student updatedStudent = studentRepository.save(student1);
        updatedStudent.setName("Naga");

        assertEquals("Naga", studentRepository.save(updatedStudent).getName() );

    }


    @Test
    void testDelete(){

//        assertEquals(student1, studentRepository.save(student1));
        student1 = studentRepository.save(student1);
        student2 = studentRepository.save(student2);
        studentRepository.deleteById(student2.getId());

        assertEquals(1, studentRepository.findAll().size());

    }




}