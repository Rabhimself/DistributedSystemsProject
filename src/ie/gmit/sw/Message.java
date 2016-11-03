package ie.gmit.sw;

import java.io.*;
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public Message(String message){
		this.msg = message;
	}
	
	public String message(){
		return msg;
	}
}
