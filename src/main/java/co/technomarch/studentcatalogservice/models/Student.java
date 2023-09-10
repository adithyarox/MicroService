package co.technomarch.studentcatalogservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Document("student")
public class Student {

    @Id
    @NotBlank(message = "Student id is required")
    private String id;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Date of birth is required")
    private LocalDate dob;
    private String gender;
    @Min(value = 1, message = "Semester must be greater than 1")
    @Max(value = 8, message = "Semester must be less than 8")
    private int semester;

    public Student() {
    }

    public Student(String studentId, String name, LocalDate dob, String gender, int semester) {
        this.name = name;
        this.id = studentId;
        this.dob = dob;
        this.gender = gender;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
