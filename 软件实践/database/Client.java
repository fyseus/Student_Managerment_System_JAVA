package server;

import database.StudentItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

public class Client {
	Socket socket;
	
	//没有构造函数。在每个人个人的Item类里面，请在构造函数中调用一下client.connect(IP,port)函数，以连接服务器。IP，port在自测阶段可以随意设置
	Client(){
		
	}
			
	//发送信息，用于后端识别和决定调用函数。
	void sendMessage(String s) throws IOException {
		sendObject(s);
	}
	
	//得到服务器返回信息，以进行前端相应函数的处理。
	String getBackMessage() throws IOException, ClassNotFoundException {
		return (String)getBackObject();
	}
	
	//传输数据包/对象。在代码中的体现就是这样的：
	//Client client;（构造函数内）
	//addActionListener(){
	//    client.sendMessage((String)message);
	//    client.sendObject((...Item)object);
	//    String back_message=client.getBackMessage();
	//    ...Item back_Item=client.getBackObject();
	//}
	//所有的书写都只需要直接调就可以了，服务器大家不要考虑，那是我的事情
	void sendObject(Object obj) throws IOException {
		OutputStream output=socket.getOutputStream();
		ObjectOutputStream object_output=new ObjectOutputStream(output);
		object_output.writeObject(obj);
	}
	
	//得到后端处理或查找后返回的数据包/对象
	Object getBackObject() throws IOException, ClassNotFoundException {
	    InputStream input=socket.getInputStream();
	    ObjectInputStream object_input=new ObjectInputStream(input);
	    Object obj=object_input.readObject();
	    return obj;
	}
	
	void connect(String address,int port) throws UnknownHostException, IOException { // 连接套接字方法
		socket=new Socket(address,port);
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		Client client=new Client();
		//client.connect("2.0.1.97", 8992);
		
		//************************此处添加客户端测试代码********************
		while(true) {
			client.connect("2.0.1.97", 8992);
			//************测试信息传输**********
			//String input="Hello";
			String input="I am YCY";
			
			client.sendMessage(input);
			//Thread.sleep(500);
			
			String s=client.getBackMessage();
			System.out.println(s);
			//***********************************/
			
			//*************测试对象传输**********
			StudentItem student=
					new StudentItem("21xxxxxxx","杨晨晔", "09018208", 100.0,"6219 xxxxxx xxxxxx xxx");
			client.sendObject(student);
			
			Object student_back;
			student_back=client.getBackObject();
			
			System.out.println(student_back);
			client.socket.close();
			//***********************************/
			Thread.sleep(2000);
		}
		//*****************************************************************
	}
}
