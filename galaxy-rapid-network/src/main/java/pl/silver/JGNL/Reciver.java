package pl.silver.JGNL;

import java.util.LinkedList;

public class Reciver
{
	private LinkedList<Object> stackIncomingEvent = new LinkedList<Object>();
	
	public Object getOldestMessage(){
		return stackIncomingEvent.pollFirst();
	}
	
	public void putIncomingMessage(Object incomingTransmission){
		stackIncomingEvent.addLast(incomingTransmission);
	}
	
	public int countMessage(){
		return stackIncomingEvent.size();
	}
}
