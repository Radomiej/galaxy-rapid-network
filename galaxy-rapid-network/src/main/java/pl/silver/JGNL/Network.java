package pl.silver.JGNL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.KryoSerialization;

import pl.silver.JGNL.request.Answer;
import pl.silver.JGNL.request.Request;

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


	public static void register(EndPoint endPoint) {
		
		if(!(endPoint.getSerialization() instanceof KryoSerialization)) {return;}
		Kryo kryo = endPoint.getKryo();
		
		kryo.setAsmEnabled(true);
		
		for(Class c : rergisterClasses){
			kryo.register(c);
		}	
		
		//Native class
		kryo.register(char[].class);
		kryo.register(List.class);
		kryo.register(LinkedList.class);
		kryo.register(ArrayList.class);
		kryo.register(String.class);
		kryo.register(Object.class);	
		kryo.register(HashMap.class);
		kryo.register(HashSet.class);	
		//Libgdx classes
		kryo.register(Vector2.class);	
		kryo.register(Rectangle.class);	
		//Libraries class
		kryo.register(Answer.class);	
		kryo.register(Request.class);	
	}
}
