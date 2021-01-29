package ua.kiev.prog.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.Utils;

import java.io.IOException;
import java.util.Date;

public class Message {
	private Date date = new Date();
	private String from;
	private String to;
	private String text;
	private String chatRoom;
	private int fileId;


	public Message(String from, String text) {
		this.from = from;
		this.text = text;
	}

	public Message(String from, String to, String text) {
		this.from = from;
		this.to = to;
		this.text = text;
	}

	public Message(String from, String to, String text, String chatRoom) {
		this.from = from;
		this.to = to;
		this.text = text;
		this.chatRoom = chatRoom;
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("[").append(date)
				.append(", From: ").append(from).append(", To: ").append(getTo())
				.append("] ").append(text)
                .toString();
	}

	public int send() throws IOException {
		return Utils.send("/add", toJSON());
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		if (to == null)
			return to = "All";
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}

	public int getFileId() {
		return fileId;
	}
}
