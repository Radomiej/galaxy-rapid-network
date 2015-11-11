package pl.silver.JGNL;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import pl.silver.JGNL.event.EventReciver;
import pl.silver.JGNL.request.Answer;
import pl.silver.JGNL.request.Request;
import pl.silver.JGNL.request.RequestReciver;
import pl.silver.json.LibGdxJsonSerialization;

public class JGNLServer
{
	Server server;

	private RequestReciver requestReciver;

	private EventReciver eventReciver;

	private final Listener commonListener;

	
	
	public JGNLServer() {
		commonListener = new Listener.ThreadedListener(new Listener() {
			@Override
			public void received(Connection connection, Object object) {
				if (object instanceof Request) {
					Request request = (Request) object;
					if (requestReciver != null) {						
						Object answerMessage =	requestReciver.recivedRequest(connection, object);
						Answer answer = new Answer(request, answerMessage);
						connection.sendTCP(answer);
					}
				}
				else if(object instanceof Answer){
					RemoteClient client = (RemoteClient) connection;
					Answer answer = (Answer) object;
					client.recivedAnswer(answer);
				}
				else {
					if (eventReciver != null) eventReciver.recivedEvent((Transmission) connection, object);
				}
			}
		});
	}

	public void start(int tcp, int udp) {
		server = new Server(Network.WRITE_BUFFER, Network.READ_BUFFER, new LibGdxJsonSerialization()) {
			@Override
			protected Connection newConnection() {
				RemoteClient newPlayer = new RemoteClient();
				newPlayer.addListener(commonListener);
				return newPlayer;
			}
		};
		server.start();

		try {
			server.bind(tcp, udp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void close() {
		server.close();
	}

	public RequestReciver getRequestReciver() {
		return requestReciver;
	}

	public void setRequestReciver(RequestReciver requestReciver) {
		this.requestReciver = requestReciver;
	}

	public EventReciver getEventReciver() {
		return eventReciver;
	}

	public void setEventReciver(EventReciver eventReciver) {
		this.eventReciver = eventReciver;
	}
	public void sendEvent(Object object) {
		server.sendToAllTCP(object);
	}
}