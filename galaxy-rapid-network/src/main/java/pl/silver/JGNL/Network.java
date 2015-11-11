package pl.silver.JGNL;

import java.util.ArrayList;
import java.util.List;

public class Network
{	
	public final static int TIMEOUT = 1000;	
	public final static int WRITE_BUFFER = 256 * 1024;	
	public final static int READ_BUFFER = 256 * 1024;
	public static int portTCP = 56555;
	public static int portUDP = 56777;
	
	@SuppressWarnings("rawtypes")
	static private List<Class> rergisterClasses = new ArrayList<Class>();


	@SuppressWarnings("rawtypes")
	public static void registerClasses(Class... classToRegister) {
		for(Class c : classToRegister){
			rergisterClasses.add(c);
		}		
	}
}
