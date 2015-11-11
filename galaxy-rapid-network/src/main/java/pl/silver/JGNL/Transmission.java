package pl.silver.JGNL;

import pl.silver.JGNL.request.Future;

public interface Transmission
{
	public void sendEvent(Object event);
	public Future sendRequest(Object request);
}
