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
	
	//û�й��캯������ÿ���˸��˵�Item�����棬���ڹ��캯���е���һ��client.connect(IP,port)�����������ӷ�������IP��port���Բ�׶ο�����������
	Client(){
		
	}
			
	//������Ϣ�����ں��ʶ��;������ú�����
	void sendMessage(String s) throws IOException {
		sendObject(s);
	}
	
	//�õ�������������Ϣ���Խ���ǰ����Ӧ�����Ĵ���
	String getBackMessage() throws IOException, ClassNotFoundException {
		return (String)getBackObject();
	}
	
	//�������ݰ�/�����ڴ����е����־��������ģ�
	//Client client;�����캯���ڣ�
	//addActionListener(){
	//    client.sendMessage((String)message);
	//    client.sendObject((...Item)object);
	//    String back_message=client.getBackMessage();
	//    ...Item back_Item=client.getBackObject();
	//}
	//���е���д��ֻ��Ҫֱ�ӵ��Ϳ����ˣ���������Ҳ�Ҫ���ǣ������ҵ�����
	void sendObject(Object obj) throws IOException {
		OutputStream output=socket.getOutputStream();
		ObjectOutputStream object_output=new ObjectOutputStream(output);
		object_output.writeObject(obj);
	}
	
	//�õ���˴������Һ󷵻ص����ݰ�/����
	Object getBackObject() throws IOException, ClassNotFoundException {
	    InputStream input=socket.getInputStream();
	    ObjectInputStream object_input=new ObjectInputStream(input);
	    Object obj=object_input.readObject();
	    return obj;
	}
	
	void connect(String address,int port) throws UnknownHostException, IOException { // �����׽��ַ���
		socket=new Socket(address,port);
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		Client client=new Client();
		//client.connect("2.0.1.97", 8992);
		
		//************************�˴���ӿͻ��˲��Դ���********************
		while(true) {
			client.connect("2.0.1.97", 8992);
			//************������Ϣ����**********
			//String input="Hello";
			String input="I am YCY";
			
			client.sendMessage(input);
			//Thread.sleep(500);
			
			String s=client.getBackMessage();
			System.out.println(s);
			//***********************************/
			
			//*************���Զ�����**********
			StudentItem student=
					new StudentItem("21xxxxxxx","���", "09018208", 100.0,"6219 xxxxxx xxxxxx xxx");
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
