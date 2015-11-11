package pl.silver.JGNL.event;

import pl.silver.JGNL.Transmission;

import com.esotericsoftware.kryonet.Connection;

public interface EventReciver
{
	public void recivedEvent(Transmission connection, Object object);

}
