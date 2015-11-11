package pl.silver.JGNL;

import java.util.HashMap;

import pl.silver.JGNL.request.Answer;
import pl.silver.JGNL.request.AnswerReciver;
import pl.silver.JGNL.request.Future;
import pl.silver.JGNL.request.Request;

import com.esotericsoftware.kryonet.Connection;

public class RemoteClient extends Connection implements Transmission
{
	private AnswerReciver answerReciver;

	HashMap<Integer, Future> futures = new HashMap<Integer, Future>();

	public void recivedAnswer(Answer answer) {
		if (answerReciver != null) answerReciver.recivedAnswer(answer);
	}

	public AnswerReciver getAnswerReciver() {
		return answerReciver;
	}

	public void setAnswerReciver(AnswerReciver answerReciver) {
		this.answerReciver = answerReciver;
	}

	@Override
	public void sendEvent(Object event) {
		sendTCP(event);
	}

	@Override
	public Future sendRequest(Object messageData) {
		Request request = new Request();
		Future future = new Future(request.getMessageId());
		request.setMessage(messageData);
		sendTCP(request);
		registerAnswerFuture(future);
		return future;

	}

	private void registerAnswerFuture(Future future) {
		futures.put(future.getMessageId(), future);
	}
}
