package ua.kiev.prog.json;

import ua.kiev.prog.json.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonMessages {
    private final List<Message> list = new ArrayList<>();

    public List<Message> getList() {
        return Collections.unmodifiableList(list);
    }
}
