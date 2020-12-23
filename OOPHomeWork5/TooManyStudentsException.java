package OOPHomeWork5;

public class TooManyStudentsException extends  Exception{

    @Override
    public String getMessage() {
        return "Group is full! You may add only 10 students to the group, not 11 or more";
    }
}
