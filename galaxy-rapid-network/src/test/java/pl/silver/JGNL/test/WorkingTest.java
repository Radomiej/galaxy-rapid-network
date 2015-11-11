package pl.silver.JGNL.test;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import pl.silver.JGNL.JGNLClient;
import pl.silver.JGNL.JGNLServer;
import pl.silver.JGNL.Network;

public class WorkingTest
{
//	@Test
	public void testWorkServerAndClient(){
		
		
		//Network.registerClasses(AnyEvent.class);
		JGNLServer server = new JGNLServer();		
		server.start(Network.portTCP, Network.portUDP);
		
		
		waitOnStartServer();
		
		
		JGNLClient client = new JGNLClient();
		try {
			client.connect("127.0.0.1", Network.portTCP, Network.portUDP);
		} catch (IOException e) {			
			e.printStackTrace();
			fail("Problem z nawiązaniem połączenia");
		}
		
	}

	private void waitOnStartServer() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
