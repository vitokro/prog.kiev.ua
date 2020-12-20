package OOPHomeWork4;

public class NoStudentsInGroupException extends Exception {
    @Override
    public String getMessage() {
        return "Cannot delete student from the group, cause no such student is in the group or group is empty";
    }
}
