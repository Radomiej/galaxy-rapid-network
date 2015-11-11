package pl.silver.JGNL.test;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import pl.silver.JGNL.JGNLClient;
import pl.silver.JGNL.JGNLServer;
import pl.silver.JGNL.Network;
import pl.silver.JGNL.request.Future;

public class ClientWorkingTest
{
	public static class TestObjectClass
	{

	}

	public static class AnotherTestObjectClass
	{

	}

//	@Test
	public void testWorkServerAndClient(){		
				
		JGNLClient client = new JGNLClient();
		
		try {
			client.connect("127.0.0.1", Network.portTCP, Network.portUDP);
		} catch (IOException e) {			
			e.printStackTrace();
			fail("Problem z nawiązaniem połączenia");
		}
		
		
		Future future = client.sendRequest("Joł");
		
		while(true){
			if(future.isAnswer()){
				System.out.println(future.getAnswerMessage());
				break;
			}
		}
		client.close();
	}
}
