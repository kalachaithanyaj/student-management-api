package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private Student updatedStudent;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
       return studentRepository.findAll();
    }

   public void addNewStudent(Student student)  {
       Optional<Student> studentByName = studentRepository.findStudentByName(student.getName());
       if(studentByName.isPresent()){
           throw new IllegalStateException("name exists");
       }


       studentRepository.save(student);
   }

   public void deleteStudent(int studentId){
       boolean b = studentRepository.existsById(studentId);
       if(!b){
           throw new IllegalStateException("id not found");
       }

       studentRepository.deleteById(studentId);

   }

@Transactional
    public void update(int studentId,Student updatedStudent) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("id not exists"));
        student.setName(updatedStudent.getName());
        student.setAge(updatedStudent.getAge());

    }
}
