package com.example.sql_practice;

import com.example.sql_practice.Student;
import com.example.sql_practice.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    //dependently inject studentRepository
    @Autowired
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Get Functions
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }


    //Create Functions
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> createStudents(List<Student> students){
        return studentRepository.saveAll(students);
    }

    //Update Functions
    public Optional<Student> updateStudent(Long id, Student student){
        if(studentRepository.existsById(id)){
            student.setId(id);
            return Optional.of(studentRepository.save(student));
        }
        else{
            return Optional.empty();
        }
    }


    //Delete Functions
    public boolean deleteStudent(Long id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }


}
