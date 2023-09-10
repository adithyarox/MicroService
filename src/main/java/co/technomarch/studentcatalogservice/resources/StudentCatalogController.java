package co.technomarch.studentcatalogservice.resources;

import co.technomarch.studentcatalogservice.exception.StudentNotFoundException;
import co.technomarch.studentcatalogservice.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class StudentCatalogController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/get-all-students")
    @CrossOrigin
    public List<Student> getAllStudents()   {
        return studentRepository.findAll();
    }

    @PostMapping("/get-student-by-id")
    public Student getStudentById(@RequestBody String studentId) throws StudentNotFoundException {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null)
            throw new StudentNotFoundException();

        return student;
    }

    @PostMapping("/get-student-by-name")
    public Student getStudentByName(@RequestBody String studentName) {
        return studentRepository.findStudentByName(studentName);
    }

    @PostMapping("/add-student")
    @CrossOrigin
    public String addStudent(@RequestBody Student student) {

        studentRepository.save(student);

        return "Student added successfully";
    }

    @PostMapping("/add-students")
    public String addStudents(@RequestBody List<Student> students)   {
        studentRepository.saveAll(students);

        return "Students added successfully";
    }

    @DeleteMapping("/remove-student")
    @CrossOrigin
    public String removeStudent(@RequestBody Student student) {
        studentRepository.delete(student);

        return "Student removed successfully";
    }

    @DeleteMapping("/remove-student-by-id")
    @CrossOrigin
    public String removeStudentById(@RequestBody String studentId)    {
        studentRepository.deleteById(studentId);

        return "Removed Succesfully";
    }

    @DeleteMapping("/remove-all-students")
    public String removeAllStudents()    {
        studentRepository.deleteAll();

        return "All students removed successfully";
    }

    @PostMapping("/update-student")
    public String updateStudent(@RequestBody Student student) {
        studentRepository.save(student);

        return "Student updated successfully";
    }

    @PostMapping("/find-students-by-semester")
    public List<Student> findStudentsBySemester(@RequestBody String semester) {
        return studentRepository.findAllBySemester(Integer.parseInt(semester));
    }

    @PostMapping("/find-students-by-gender")
    public List<Student> findStudentsByGender(@RequestBody String gender)   {
        return studentRepository.findAllByGender(gender);
    }

}
