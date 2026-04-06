package com.example.demo.student;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/student")
public class StudentController {
 private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService =studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public  void registerStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }


    @DeleteMapping(path = "{studentId}")
    public  void deleteStudent(@PathVariable("studentId") int studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        studentService.update(studentId, student);
    }





}
