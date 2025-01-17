package com.example.sql_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService service){this.studentService = service;}

    //GET FUNCTIONS

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //POST FUNCTIONS
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Student>> createStudents(@RequestBody List<Student> students){
        List<Student> addedStudents = studentService.createStudents(students);
        return ResponseEntity.ok(addedStudents);
    }

    //UPDATE FUNCTIONS
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> updatedStudent = studentService.updateStudent(id, student);
        return updatedStudent.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    //DELETE FUNCTIONS
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        if(studentService.deleteStudent(id)){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




}
