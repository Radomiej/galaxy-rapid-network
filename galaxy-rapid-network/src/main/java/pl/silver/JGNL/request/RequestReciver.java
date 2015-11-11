package pl.silver.JGNL.request;

import com.esotericsoftware.kryonet.Connection;

public interface RequestReciver
{

	public Object recivedRequest(Connection connection, Object object);
	
}
