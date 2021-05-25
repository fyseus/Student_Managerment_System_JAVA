import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.Table;

public class use {
	//增加
	public void addBook{
		Table mytable=new Table("test","Book");
		ResultSet _resultset=mytable.getResult_set();
		String sql="insert into Book(BookName,Aurthor,BookID,Price,BookClass,State) VALUES(?,?,?,?,?,?)";
		PreparedStatement ptmt=mytable.getConnection().prepareStatement(sql);
		ptmt.setString(1,);
		ptmt.setString(2,);
		ptmt.setString(3,);
		ptmt.setString(4,);
		ptmt.setString(5,);
		ptmt.setString(6,);
		ptmt.executeUpdate();	
		ptmt.close();
	}
	public void addBorrowed{
		Table mytable=new Table("test","Borrowed");
		ResultSet _resultset=mytable.getResult_set();
		String sql="insert into Borrowed(StudentName,CardID,BorrowedTime,ReturnTime,BookID,ContinueTime,State,BookName,Author) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt=mytable.getConnection().prepareStatement(sql);
		ptmt.setString(1,);
		ptmt.setString(2,);
		ptmt.setString(3,);
		ptmt.setString(4,);
		ptmt.setString(5,);
		ptmt.setString(6,);
		ptmt.setString(7,);
		ptmt.setString(8,);
		ptmt.setString(9,);
		ptmt.executeUpdate();	
		ptmt.close();
	}
	public void addHistory{
		Table mytable=new Table("test","History");
		ResultSet _resultset=mytable.getResult_set();
		String sql="insert into History(StudentName,CardID,Timet,BookID,Operation,BookName,Author) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ptmt=mytable.getConnection().prepareStatement(sql);
		ptmt.setString(1,);
		ptmt.setString(2,);
		ptmt.setString(3,);
		ptmt.setString(4,);
		ptmt.setString(5,);
		ptmt.setString(6,);
		ptmt.setString(7,);
		ptmt.executeUpdate();	
		ptmt.close();
	}
	//修改
	public int changeSpace() {
		Table mytable=new Table("test","Space");
		ResultSet _resultset=mytable.getResult_set();
		Connection con=mytable.getConnection();
		try {
			String sql="update Space  set  State=?,People=?,IDcrad=?,StartTime=?,StopTime=? where SpaceID=?";
			PreparedStatement pt=con.prepareStatement(sql);
			pt.setString(6, SpaceID);
			pt.setString(1, "已被预约");
			pt.setString(2, "null");
			pt.setString(3, "000");
			pt.setString(4, Time);
			pt.setString(5, StopTime);
			return pt.executeUpdate();
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}finally {
			try {
				mytable.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
	//查询，
	public void Search() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		Table mytable=new Table("test","Book");
		ResultSet _resultset=mytable.getResult_set();
		try {
			while(_resultset.next()){
				String A_name=_resultset.getString("Aurthor");
				if(A_name.contains(tp2.getText())) {
					Vector Bookdata = new Vector();
					/*System.out.println(_resultset.getString("BookName"));
					System.out.println(_resultset.getString("Aurthor"));
					System.out.println(_resultset.getString("BookID"));
					System.out.println(_resultset.getString("Price"));
					System.out.println(_resultset.getString("BookClass"));
					System.out.println(_resultset.getString("State"));*/
					Bookdata.add(_resultset.getString("BookName"));
					Bookdata.add(A_name);
					Bookdata.add(_resultset.getString("BookID"));
					Bookdata.add(_resultset.getString("Price"));
					Bookdata.add(_resultset.getString("BookClass"));
					Bookdata.add(_resultset.getString("State"));
					dtm.addRow(Bookdata);
				}
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}
	//删除
	public void delete() {
		Table mytable=new Table("test","Book");
		Statement st=mytable.getConnection().createStatement();
		String sql="delete from 表名 where 具体的条件"; 
		st.executeUpdate(st);               //删除数据的du执行
	}
	//填充表格
	public void fillTable() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		Table mytable=new Table("test","Book");
		ResultSet _resultset=mytable.getResult_set();
		try {
			while(_resultset.next()){
				Vector Bookdata = new Vector();
				/*System.out.println(_resultset.getString("BookName"));
				System.out.println(_resultset.getString("Aurthor"));
				System.out.println(_resultset.getString("BookID"));
				System.out.println(_resultset.getString("Price"));
				System.out.println(_resultset.getString("BookClass"));
				System.out.println(_resultset.getString("State"));*/
				Bookdata.add(_resultset.getString("BookName"));
				Bookdata.add(_resultset.getString("Aurthor"));
				Bookdata.add(_resultset.getString("BookID"));
				Bookdata.add(_resultset.getString("Price"));
				Bookdata.add(_resultset.getString("BookClass"));
				Bookdata.add(_resultset.getString("State"));
				dtm.addRow(Bookdata);
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}finally {
			try {
				mytable.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
