package pl.silver.JGNL.request;

import java.util.concurrent.atomic.AtomicInteger;

public class Request
{
	private static AtomicInteger uniqueMessageId = new AtomicInteger(0);
	private static int getUniqueMessageId(){
		return uniqueMessageId.getAndIncrement();
	}
	
	private int messageId = getUniqueMessageId();
	private Object message;
	
	public int getMessageId() {
		return messageId;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = (String) message;
	}
	
}
