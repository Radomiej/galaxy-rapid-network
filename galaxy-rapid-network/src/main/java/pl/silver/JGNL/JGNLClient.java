package pl.silver.JGNL;

import java.io.IOException;
import java.util.HashMap;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import pl.silver.JGNL.event.ServerEventReciver;
import pl.silver.JGNL.request.Answer;
import pl.silver.JGNL.request.Future;
import pl.silver.JGNL.request.Request;
import pl.silver.json.LibGdxJsonSerialization;

public class JGNLClient implements Transmission
{
	private Client client;

	HashMap<Integer, Future> futures = new HashMap<Integer, Future>();
	
	private ServerEventReciver reciver;
	

	public void connect(String host, int tcp, int udp) throws IOException {

		createClient();
		addListener();
		startClient(host, tcp, udp);
	}

	private void startClient(String host, int tcp, int udp) throws IOException {
		client.start();
		try {
			client.connect(Network.TIMEOUT, host, tcp, udp);
		} catch (IOException ex) {
			throw ex;
		}
	}

	private void addListener() {
		client.addListener(new Listener() {

			@Override
			public void connected(Connection connection) {
				System.out.println("connected");
				super.connected(connection);
			}

			@Override
			public void idle(Connection connection) {
//				System.out.println("idle");
				super.idle(connection);
			}

			@Override
			public void received(Connection connection, Object object) {
				if (object instanceof Answer) {
					Answer answer = (Answer) object;
					Future future = futures.get(answer.getMessageId());
					future.setAnswer(answer);
					futures.remove(future.getMessageId());
				}
//				System.out.println("recived2");
				if(reciver != null) {
					reciver.reciveEvent(object);
				}
			}

			@Override
			public void disconnected(Connection connection) {
				System.out.println("disconned");
			}
		});
	}

	private void createClient() {
		this.client = new Client(Network.WRITE_BUFFER, Network.READ_BUFFER, new LibGdxJsonSerialization());
	}

	public Future sendRequest(Object messageData) {

		Request request = new Request();
		Future future = new Future(request.getMessageId());
		request.setMessage(messageData);
		client.sendTCP(request);
		registerAnswerFuture(future);
		return future;

	}

	private void registerAnswerFuture(Future future) {
		futures.put(future.getMessageId(), future);
	}

	public void sendEvent(Object string) {
		client.sendTCP(string);
	}

	public void close() {
		client.close();
	}
	public ServerEventReciver getReciver() {
		return reciver;
	}
	public void setReciver(ServerEventReciver reciver) {
		this.reciver = reciver;
	}
}
