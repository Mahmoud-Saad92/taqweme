package eg.bazinga.taqweme.exceptions;

public class ResourceCannotCreatedException extends Exception {

    private static final long serialVersionUID = 1L;

    public ResourceCannotCreatedException(String message){
        super(message);
    }
}
