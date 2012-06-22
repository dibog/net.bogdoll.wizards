package net.bogdoll.wizards;

public final class Message {
	enum Status { DEBUG, INFO, WARN, ERROR };
	
	final Status status;
	final String message;
	
	public Message(Status aStatus, String aMessage) {
		status = aStatus;
		message = aMessage;
	}
	
	public static Message debug(String aMessage) {
		return new Message(Status.DEBUG, aMessage);
	}

	public static Message info(String aMessage) {
		return new Message(Status.INFO, aMessage);
	}
	
	public static Message warn(String aMessage) {
		return new Message(Status.WARN, aMessage);
	}
	
	public static Message error(String aMessage) {
		return new Message(Status.ERROR, aMessage);
	}
}
