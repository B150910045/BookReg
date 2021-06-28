import java.sql.*;
public class Book {
	public ResultSet set = null;
	public MySQLConnection mysql = new MySQLConnection("products", "root", "cody");
	
	public Book() {
		try {
			mysql.dbConnect();
			String query = "CREATE TABLE IF NOT EXISTS books(  \r\n"
					+ "    id int NOT NULL AUTO_INCREMENT,  \r\n"
					+ "    author varchar(45) NOT NULL,  \r\n"
					+ "    bookName varchar(35) NOT NULL,  \r\n"
					+ "    price int NOT NULL,  \r\n"
					+ "    PRIMARY KEY (id)  \r\n"
					+ ");  ";
			
			mysql.execute(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(String author, String bookName, int price, int id) {
		String query = "UPDATE books\r\n"
				+ "SET author = '"+author+"', bookName= '"+bookName+"', price = "+price+"\r\n"
				+ "WHERE id = "+id+";";
		mysql.execute(query);
	}
	
	public void insert(String author, String bookName, int price) {
		String query = "INSERT INTO books ( author, bookName, price )    \r\n"
				+ "VALUES    \r\n"
				+ "( '" + author + "', '" + bookName + "', " + price + ")";
		mysql.execute(query);
	}
	
	public ResultSet getBookInfo() {
		try {
			String query = "select * from books";
			set = mysql.getResult(query);
			return set;
		}catch(Exception e) {
			return null;
		}
	}
	
}
