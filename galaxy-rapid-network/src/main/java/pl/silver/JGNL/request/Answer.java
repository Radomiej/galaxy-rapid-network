package pl.silver.JGNL.request;

public class Answer
{
	private int messageId = -1;
	private Object messageData;

	public Answer() {
	}
	public Answer(Request request, Object answerMessage) {
		messageId = request.getMessageId();
		this.setMessageData(answerMessage);
	}

	public Object getMessageData() {
		return messageData;
	}

	public void setMessageData(Object messageData) {
		this.messageData = messageData;
	}

	public int getMessageId() {
		return messageId;
	}
}
