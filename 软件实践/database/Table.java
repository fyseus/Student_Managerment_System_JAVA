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
	
	/*构造函数，第一个参数为数据库名（指你们自己进行测试的时候在控制面板--管理工具--ODBC数据源
	--系统DSN里面添加的数据库源名，！！！注意不是数据库文件accdb的名字！！！）
	第二个参数为表名，指在数据库内你们操作的表名（"Table_1","Table_2"这样），可以先不赋（因为一个数据库源可能有多个表）
	！！！但是注意，更改当前访问表表名时，一定要调用setTablename函数！！！因为表名一改，数据库里面的结果集等中间变量可能都会随之更改，尽量不要构造
	这种麻烦
	*/
	public Table(String db_name,String tb_name) {
		database_name=db_name;
		table_name=tb_name;
		
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			url="jdbc:odbc:"+database_name;
			connection = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
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
	
	//无表名构造函数
	public Table(String db_name) {
		database_name=db_name;
		table_name="";
		url=null;
		connection=null;
		statement=null;
		result_set=null;
		sql=null;
	}
	
	//返回表的行数
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
	
	//如果要修改当前表名，请使用该函数
	//注意该函数与后面创建表函数的区别。创建表createTable函数，是针对当前数据库创建新表，与当前访问表名无关！！！
	public void setTableName(String tb_name) {
		try {
			table_name=tb_name;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			url="jdbc:odbc:"+database_name;
			connection = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
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
	
	/*item指条件属性，value指条件属性需要满足的值
	 * target指要更改的属性，target_value指要更改的值
	 * 如，要将name为ycy的条目student_number改成09018xxx
	 * 则需要调用函数table.setString("name","ycy","student_number","09018xxx")，注意引号！
	*/
	public void setString(String item,String value,String target, String target_value) {
		try {
			String s="UPDATE "+table_name+" SET "+item+" = '"+value+"' WHERE "+
					target+" = '"+target_value+"'";
			
			connection = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
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
	
	//获得函数，得到第row行数据的属性值————注意，我们所有的数据全部为string类型
	//如果需要更改数据格式，请自行调用String、Integer等包的函数
	//如，想输入int参数i，那么输入数据库时要改为Integer.toString(i);
	//反之，若想将输出String转回int，则调用String.toInt(s)这样（具体函数我记不得了，这个直接百度就能出来，或者查书）
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
	
	//这个函数算是废的，不要用
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
	
	//这个函数无需输入行号，通过ID来查找记录。
	//！！！注意，每一个记录条目，都必须有一个ID号，且必须放在第一行，格式自定，因为这是同属性记录条目识别（包括同一本书的两个副本）唯一的标签！！！
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
	
	//插入记录。第一个item为所有添加的属性列表，必须为LinkedList<String>类型，第二项则为item各个对应属性的值
	//如，要插入记录name=”ycy",student_number="090182xxx",
	//则需调用如下代码：
	//Linkedlist<String>item,list;
	//item.add("name");item.add("ycy");
	//list.add("student_number");list.add("090182xxx");
	//table.insertRecord(item,list);
	//注意，属性不一定要完全，比如不知道一卡通号，可以不将之加入列表。但是两个参数的属性和值一定要对应，否则将发生错误
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
			
			connection = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			result_set=statement.executeQuery(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//这个，是添加新完整的record，list是各项的值。由于没有item属性参数，添加时必须保证list的参数顺序与表内一致（缺省的list项可用""）
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
			
			connection = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			result_set=statement.executeQuery(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//删除记录，输入值为行号
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
 	
 	//删除属性item值为value的所有条目，可以是ID（此时item="ID"）
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
 	
 	//寻找所有item属性为value值的记录，并返回满足条件的ID列表。这里再次强调，每个条目必须有ID，在第一列
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
 	
 	//查找ID在record_ID列表中，属性item等于value的条目。这主要是为了方便查找满足多个条件的条目。调用时，先调用第一个search函数，
 	//再将第一个函数的返回值，作为第二个search函数的record_ID参数调进去。
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
	
 	//创建当前数据库下的新表。第二个para_list为属性列表，注意，第一项一定要为ID！！！
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
			
			connection = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	//建议别用，前两个参数和上一个一样，第三个参数为所有属性的类型列表。我们规定，所有数据库记录条目一律用String
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
			
			connection = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	//注意，这里面drop这个操作是把整个表删除，操作完以后连表的结构都没有了
 	public void dropTable(String tb_name) {
 		try {
			String s="DROP TABLE "+tb_name;
			System.out.println(s);
			
			connection = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	//delete与drop不同，delete只删除表内容，不删除结构，即操作完，表还在，但是是空表
 	public void deleteTable(String tb_name) {
 		try {
			String s="DELETE * FROM "+tb_name;
			System.out.println(s);
			
			connection = DriverManager.getConnection(url, "", "");//没有用户名和密码的时候直接为空
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
	                ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(s);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

 	//测试代码，如果前面还不熟悉，请自行阅读理解，或直接提问
	public static void main(String args[]) {
 		Table t=new Table("Test","Table_1");
 		for (int i=1;i<=t.getTableRowCount();i++)
 			System.out.println(t.getString(i,"name")+"  "+t.getString(i,"studentNumber")+"  "+t.getString(i,"cardNumber"));
 		
 		//t.deleteRecord(5);
 		//t.deleteRecord(4);
 		
 		LinkedList<String> list=new LinkedList<>();
 		list.add("12");
 		list.add("朱沚易");
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
 		t.setString("studentNumber", "090182_x","name","杨晨晔");
 		
 		
 		for (int i=1;i<=t.getTableRowCount();i++)
 			System.out.println(t.getString(i,"name")+"  "+t.getString(i,"studentNumber")+"  "+t.getString(i,"cardNumber"));
 		
 		
 		
 		LinkedList<String> l=t.searchRecord("name", "程纳川");
 		String s=l.get(0);
 		System.out.println(s);
 		
 		LinkedList<String> l_2=t.searchRecord("name", "程纳川", l);
 		String s_2=l_2.get(0);
 		System.out.println(s_2);
 		
 		System.out.println(t.getTableRowCount());
 		
 		System.out.println(t.getString("3","name"));
 		System.out.println(t.getString(1,2));
 		
 		//t.setString("studentNumber", "090182_2","name","杨晨晔");
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
