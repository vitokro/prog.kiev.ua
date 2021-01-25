package ua.kiev.prog.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.json.JsonMessages;
import ua.kiev.prog.json.Message;
import ua.kiev.prog.users.Data;

public enum MessageList {
	INSTANCE;

    private final Gson gson;
	private final List<Message> list = new ArrayList<>();
	private Data data = Data.INSTANCE;


	MessageList() {
		gson = new GsonBuilder().create();
	}
	
	public synchronized void add(Message m) {
		list.add(m);
	}
	
	public synchronized String toJSON(String login, int n) {
		List<Message> loginList = list.stream()
				.filter(message -> Objects.equals(login, message.getFrom())
									|| message.getTo() == null
									|| Objects.equals(login, message.getTo())
									|| data.getChatRooms(login).contains(message.getChatRoom()))
				.collect(Collectors.toList());
		if (n >= loginList.size())
			return null;
		return gson.toJson(new JsonMessages(loginList, n));
	}
}
