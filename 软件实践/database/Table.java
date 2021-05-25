package database;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import javax .swing.*;

public class Table {
	String database_name;
	String table_name;
	String url;
	Connection connection;
	Statement statement;
	ResultSet result_set;
	PreparedStatement sql;
	
	/*���캯������һ������Ϊ���ݿ�����ָ�����Լ����в��Ե�ʱ���ڿ������--������--ODBC����Դ
	--ϵͳDSN������ӵ����ݿ�Դ����������ע�ⲻ�����ݿ��ļ�accdb�����֣�������
	�ڶ�������Ϊ������ָ�����ݿ������ǲ����ı�����"Table_1","Table_2"�������������Ȳ�������Ϊһ�����ݿ�Դ�����ж����
	����������ע�⣬���ĵ�ǰ���ʱ����ʱ��һ��Ҫ����setTablename������������Ϊ����һ�ģ����ݿ�����Ľ�������м�������ܶ�����֮���ģ�������Ҫ����
	�����鷳
	*/
	public Table(String db_name,String tb_name) {
		database_name=db_name;
		table_name=tb_name;
		
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			url="jdbc:odbc:"+database_name;
			connection = DriverManager.getConnection(url, "", "");//û���û����������ʱ��ֱ��Ϊ��
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);//,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			result_set=statement.executeQuery("select * from "+table_name);
			sql=connection.prepareStatement("select * from "+table_name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//�ޱ������캯��
	public Table(String db_name) {
		database_name=db_name;
		table_name="";
		url=null;
		connection=null;
		statement=null;
		result_set=null;
		sql=null;
	}
	
	//���ر������
	public int getTableRowCount() {
		int count=0;
		try {	
			result_set.first();
			if(result_set.wasNull())
				return count;
			
			do {
				count++;
			}while(result_set.next());		

			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	//���Ҫ�޸ĵ�ǰ��������ʹ�øú���
	//ע��ú�������洴�����������𡣴�����createTable����������Ե�ǰ���ݿⴴ���±��뵱ǰ���ʱ����޹أ�����
	public void setTableName(String tb_name) {
		try {
			table_name=tb_name;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			url="jdbc:odbc:"+database_name;
			connection = DriverManager.getConnection(url, "", "");//û���û����������ʱ��ֱ��Ϊ��
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			result_set=statement.executeQuery("select * from "+table_name);
			sql=connection.prepareStatement("select * from "+table_name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/*itemָ�������ԣ�valueָ����������Ҫ�����ֵ
	 * targetָҪ���ĵ����ԣ�target_valueָҪ���ĵ�ֵ
	 * �磬Ҫ��nameΪycy����Ŀstudent_number�ĳ�09018xxx
	 * ����Ҫ���ú���table.setString("name","ycy","student_number","09018xxx")��ע�����ţ�
	*/
	public void setString(String item,String value,String target, String target_value) {
		try {
			String s="UPDATE "+table_name+" SET "+item+" = '"+value+"' WHERE "+
					target+" = '"+target_value+"'";
			
			connection = DriverManager.getConnection(url, "", "");//û���û����������ʱ��ֱ��Ϊ��
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			statement.executeQuery(s);
			//sql.executeUpdate();
			
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}
	
	//��ú������õ���row�����ݵ�����ֵ��������ע�⣬�������е�����ȫ��Ϊstring����
	//�����Ҫ�������ݸ�ʽ�������е���String��Integer�Ȱ��ĺ���
	//�磬������int����i����ô�������ݿ�ʱҪ��ΪInteger.toString(i);
	//��֮�����뽫���Stringת��int�������String.toInt(s)���������庯���Ҽǲ����ˣ����ֱ�ӰٶȾ��ܳ��������߲��飩
	public String getString(int row,String item) {
		try {
			
			result_set.first();
			
			do {
				if(result_set.getRow()==row)
					return result_set.getString(item);
			}while(result_set.next());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
	
	//����������Ƿϵģ���Ҫ��
	public String getString(int row,int item) {
		try {
			
			result_set.first();
			
			do {
				if(result_set.getRow()==row)
					return result_set.getString(item);
			}while(result_set.next());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
	
	//����������������кţ�ͨ��ID�����Ҽ�¼��
	//������ע�⣬ÿһ����¼��Ŀ����������һ��ID�ţ��ұ�����ڵ�һ�У���ʽ�Զ�����Ϊ����ͬ���Լ�¼��Ŀʶ�𣨰���ͬһ���������������Ψһ�ı�ǩ������
	public String getString(String ID_Number, String item) {
		try {
			result_set.first();
			do {
				if(result_set.getString("ID").equals(ID_Number))
					return result_set.getString(item);
			}while(result_set.next());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
	
	//�����¼����һ��itemΪ������ӵ������б�����ΪLinkedList<String>���ͣ��ڶ�����Ϊitem������Ӧ���Ե�ֵ
	//�磬Ҫ�����¼name=��ycy",student_number="090182xxx",
	//����������´��룺
	//Linkedlist<String>item,list;
	//item.add("name");item.add("ycy");
	//list.add("student_number");list.add("090182xxx");
	//table.insertRecord(item,list);
	//ע�⣬���Բ�һ��Ҫ��ȫ�����粻֪��һ��ͨ�ţ����Բ���֮�����б������������������Ժ�ֵһ��Ҫ��Ӧ�����򽫷�������
	public void insertRecord(LinkedList<String> item,LinkedList<String> list) {
		// TODO Auto-generated method stub
		try {
			String data_string=" (";
			for(int i=0;i<list.size();i++) {
				data_string=data_string+""+item.get(i)+",";
			}
			
			data_string=data_string.substring(0,data_string.length()-1)+") values (";
			
			for(int i=0;i<list.size();i++) {
				data_string=data_string+"'"+list.get(i)+"',";
			}
			
			data_string=data_string.substring(0,data_string.length()-1)+")";
			
			//data_string=" ("+item.get(0)+") VALUES ('"+list.get(0)+"')";
			String s="insert into "+table_name+data_string;
			//System.out.println(s);
			
			connection = DriverManager.getConnection(url, "", "");//û���û����������ʱ��ֱ��Ϊ��
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			result_set=statement.executeQuery(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//������������������record��list�Ǹ����ֵ������û��item���Բ��������ʱ���뱣֤list�Ĳ���˳�������һ�£�ȱʡ��list�����""��
	public void insertRecord(LinkedList<String> list) {
		try {
			String data_string=" values (";
			
			for(int i=0;i<list.size();i++) {
				data_string=data_string+"'"+list.get(i)+"',";
			}
			
			data_string=data_string.substring(0,data_string.length()-1)+")";
			
			//data_string=" ("+item.get(0)+") VALUES ('"+list.get(0)+"')";
			String s="insert into "+table_name+data_string;
			System.out.println(s);
			
			connection = DriverManager.getConnection(url, "", "");//û���û����������ʱ��ֱ��Ϊ��
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			result_set=statement.executeQuery(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ɾ����¼������ֵΪ�к�
 	public void deleteRecord(int row) {
		try {
			
			result_set.first();
			
			do{
				if(result_set.getRow()==row) {
					result_set.deleteRow();
					sql.executeUpdate();
				}
			}while(result_set.next());
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	
 	//ɾ������itemֵΪvalue��������Ŀ��������ID����ʱitem="ID"��
 	public void deleteRecord(String item,String value) {
 		try {
			
			result_set.first();
			
			do{
				if(result_set.getString(item).equals(value)) {
					result_set.deleteRow();
					sql.executeUpdate();
				}
			}while(result_set.next());
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	//Ѱ������item����Ϊvalueֵ�ļ�¼������������������ID�б������ٴ�ǿ����ÿ����Ŀ������ID���ڵ�һ��
 	public LinkedList<String> searchRecord(String item, String value) {
 		LinkedList<String> list=new LinkedList<>();
 		try {
			result_set.first();
		
	 		do {
	 			if (result_set.getString(item).equals(value))
	 				list.add(getString(result_set.getRow(),"ID"));
	 		}while(result_set.next());
	 		
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		return list;
 	}
 	
 	//����ID��record_ID�б��У�����item����value����Ŀ������Ҫ��Ϊ�˷��������������������Ŀ������ʱ���ȵ��õ�һ��search������
 	//�ٽ���һ�������ķ���ֵ����Ϊ�ڶ���search������record_ID��������ȥ��
 	public LinkedList<String> searchRecord(String item, String value, LinkedList<String> record_ID){
 		LinkedList<String> list=new LinkedList<>();
 		try {
			result_set.first();
		    int current=0;
		    String current_ID="";
			
	 		do {
	 			current_ID=record_ID.get(current);
	 			if (result_set.getString(item).equals(value)
	 					&&result_set.getString("ID").equals(current_ID))
	 				list.add(current_ID);
	 			
	 			current++;
	 			
	 		}while(current<record_ID.size());
	 		
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		return list;
 	}
	
 	//������ǰ���ݿ��µ��±��ڶ���para_listΪ�����б�ע�⣬��һ��һ��ҪΪID������
 	public void createTable(String tb_name,LinkedList<String> para_list) {
 		try {
			String data_string="\n(\n";
			
			for(int i=0;i<para_list.size();i++) {
				data_string=data_string+para_list.get(i)+" string,\n";
			}
			
			data_string=data_string.substring(0,data_string.length()-2)+"\n)";
			
			//data_string=" ("+item.get(0)+") VALUES ('"+list.get(0)+"')";
			String s="CREATE TABLE "+tb_name+data_string;
			System.out.println(s);
			
			connection = DriverManager.getConnection(url, "", "");//û���û����������ʱ��ֱ��Ϊ��
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	//������ã�ǰ������������һ��һ��������������Ϊ�������Ե������б����ǹ涨���������ݿ��¼��Ŀһ����String
 	public void createTable(String tb_name,LinkedList<String> para_list,LinkedList<String> type_list) {
 		try {
			String data_string="\n(\n";
			
			for(int i=0;i<para_list.size();i++) {
				data_string=data_string+para_list.get(i)+" "+type_list.get(i)+",\n";
			}
			
			data_string=data_string.substring(0,data_string.length()-2)+"\n)";
			
			//data_string=" ("+item.get(0)+") VALUES ('"+list.get(0)+"')";
			String s="CREATE TABLE "+tb_name+data_string;
			System.out.println(s);
			
			connection = DriverManager.getConnection(url, "", "");//û���û����������ʱ��ֱ��Ϊ��
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	//ע�⣬������drop��������ǰ�������ɾ�����������Ժ�����Ľṹ��û����
 	public void dropTable(String tb_name) {
 		try {
			String s="DROP TABLE "+tb_name;
			System.out.println(s);
			
			connection = DriverManager.getConnection(url, "", "");//û���û����������ʱ��ֱ��Ϊ��
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	//delete��drop��ͬ��deleteֻɾ�������ݣ���ɾ���ṹ���������꣬���ڣ������ǿձ�
 	public void deleteTable(String tb_name) {
 		try {
			String s="DELETE * FROM "+tb_name;
			System.out.println(s);
			
			connection = DriverManager.getConnection(url, "", "");//û���û����������ʱ��ֱ��Ϊ��
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

 	//���Դ��룬���ǰ�滹����Ϥ���������Ķ���⣬��ֱ������
	public static void main(String args[]) {
 		Table t=new Table("Test","Table_1");
 		for (int i=1;i<=t.getTableRowCount();i++)
 			System.out.println(t.getString(i,"name")+"  "+t.getString(i,"studentNumber")+"  "+t.getString(i,"cardNumber"));
 		
 		//t.deleteRecord(5);
 		//t.deleteRecord(4);
 		
 		LinkedList<String> list=new LinkedList<>();
 		list.add("12");
 		list.add("��b��");
 		list.add("213182488");
 		list.add("09018211");
 		
 		LinkedList<String> item=new LinkedList<>();
 		item.add("ID");
 		item.add("name");
 		item.add("cardNumber");
 		item.add("studentNumber");

 		LinkedList<String> list_2=new LinkedList<>();
 		list_2.add("13");
 		list_2.add("xxx");
 		list_2.add("21318248x");
 		list_2.add("0901821x");
 		
 		LinkedList<String> type_list=new LinkedList<>();
 		type_list.add("int");
 		type_list.add("int");
 		type_list.add("int");
 		type_list.add("int");
 		
 		//t.insertRecord(item,list);
 		//t.insertRecord(list_2);
 		t.setString("studentNumber", "090182_x","name","���");
 		
 		
 		for (int i=1;i<=t.getTableRowCount();i++)
 			System.out.println(t.getString(i,"name")+"  "+t.getString(i,"studentNumber")+"  "+t.getString(i,"cardNumber"));
 		
 		
 		
 		LinkedList<String> l=t.searchRecord("name", "���ɴ�");
 		String s=l.get(0);
 		System.out.println(s);
 		
 		LinkedList<String> l_2=t.searchRecord("name", "���ɴ�", l);
 		String s_2=l_2.get(0);
 		System.out.println(s_2);
 		
 		System.out.println(t.getTableRowCount());
 		
 		System.out.println(t.getString("3","name"));
 		System.out.println(t.getString(1,2));
 		
 		//t.setString("studentNumber", "090182_2","name","���");
 		for (int i=1;i<=t.getTableRowCount();i++)
 			System.out.println(t.getString(i,"name")+"  "+t.getString(i,"studentNumber")+"  "+t.getString(i,"cardNumber"));
 		
 		//t.createTable("Table_2", item);
 		t.deleteTable("Table_2");
 		//t.deleteTable("Table_4");
 		//*
		try {
			t.statement.close();
			t.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//*/
 	}
}
