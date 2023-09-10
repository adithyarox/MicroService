package co.technomarch.studentcatalogservice.exception;

public class ResourceNotFoundException extends Throwable {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
