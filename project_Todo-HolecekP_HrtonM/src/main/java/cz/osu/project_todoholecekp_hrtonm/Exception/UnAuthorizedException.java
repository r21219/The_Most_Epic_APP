package cz.osu.project_todoholecekp_hrtonm.Exception;

public class UnAuthorizedException extends RecordNotFoundException{
    public UnAuthorizedException(String message) {
        super(message);
    }
}
