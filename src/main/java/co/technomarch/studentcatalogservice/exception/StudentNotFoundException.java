package co.technomarch.studentcatalogservice.exception;

public class StudentNotFoundException extends Throwable {
    public StudentNotFoundException() {
        super("Student not found");
    }
}
