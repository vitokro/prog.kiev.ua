package pro_hw2;

import java.io.IOException;

public class SerialSaveException extends IOException {
    public SerialSaveException() {
        super();
    }

    public SerialSaveException(String message) {
        super(message);
    }
}
