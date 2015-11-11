package pl.silver.JGNL.request;

import java.util.concurrent.atomic.AtomicBoolean;

public class Future
{
	private long sendTime;
	private int messageId;
	private Answer answer;
	private AtomicBoolean isAnswer = new AtomicBoolean(false);

	public Future(int messageId2) {
		messageId = messageId2;
		sendTime = System.currentTimeMillis();
	}

	public long getSendTime() {
		return sendTime;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public Object getAnswerMessage() {
		return answer.getMessageData();
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
		isAnswer.set(true);
	}

	public boolean isAnswer() {
		return isAnswer.get();
	}
}
