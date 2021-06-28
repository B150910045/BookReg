import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class BookRegWindow {

	private JFrame frame;
	private JTable table;
	private JTextField txt_author;
	private JTextField txt_bookName;
	private JTextField txt_price;
	
	String[] col = {"id", "author", "bookName", "price"};
	String[][] data;
	DefaultTableModel model = new DefaultTableModel(data, col);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookRegWindow window = new BookRegWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookRegWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Book book = new Book();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); 
		
		table = new JTable(model);
		table.setBounds(10, 70, 360, 200);
		frame.getContentPane().add(table);
		table.setCellSelectionEnabled(true);
		
		JLabel lbl_author = new JLabel("Author");
		lbl_author.setBounds(10, 10, 60, 20);
		frame.getContentPane().add(lbl_author);
		
		JLabel lbl_BookName = new JLabel("Book Name");
		lbl_BookName.setBounds(120, 10, 60, 20);
		frame.getContentPane().add(lbl_BookName);
		
		JLabel lbl_price = new JLabel("Price");
		lbl_price.setBounds(230, 10, 60, 20);
		frame.getContentPane().add(lbl_price);
		
		txt_author = new JTextField();
		txt_author.setBounds(10, 30, 100, 20);
		frame.getContentPane().add(txt_author);
		txt_author.setColumns(10);
		
		txt_bookName = new JTextField();
		txt_bookName.setColumns(10);
		txt_bookName.setBounds(120, 30, 100, 20);
		frame.getContentPane().add(txt_bookName);
		
		txt_price = new JTextField();
		txt_price.setColumns(10);
		txt_price.setBounds(230, 30, 100, 20);
		frame.getContentPane().add(txt_price);

		JButton btn_insert = new JButton("insert");
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book.insert(txt_author.getText(), txt_bookName.getText(), Integer.parseInt(txt_price.getText()));
			}
		});
		btn_insert.setBounds(340, 10, 70, 40);
		frame.getContentPane().add(btn_insert);
		
		JButton btn_ref = new JButton("refresh");
		btn_ref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				    
					model.setRowCount(0);
					ResultSet rs = book.getBookInfo();
					while (rs.next()) {
						int id = rs.getInt("id");
					    String author = rs.getString("author");
					    String bookName = rs.getString("bookName");
					    int price = rs.getInt("price");
					    model.insertRow(0, new Object[] {id, author, bookName, price});
					 }
				} catch (SQLException e1) {
					System.out.println("SQL exception " + e1);
				}catch (NullPointerException e1) {
					System.out.println("Null exception " + e1);
				}
			}
		});
		btn_ref.setBounds(410, 10, 70, 40);
		frame.getContentPane().add(btn_ref);
		
		JButton btn_save = new JButton("save");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int nRow = model.getRowCount();
				    for (int i = 0 ; i < nRow ; i++) {
				    	int j = 0;
				    	String author = (String)model.getValueAt(i,j + 1);
				    	String bookName = (String)model.getValueAt(i,j + 2);
				    	int price = (int)model.getValueAt(i,j + 3);
				    	book.update(author, bookName, price, i);
				    }
				}catch(Exception e1) {e1.printStackTrace();}
			}
		});
		btn_save.setBounds(380, 70, 100, 40);
		frame.getContentPane().add(btn_save);

		JButton btn_conn = new JButton("connect");
		btn_conn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!book.mysql.dbConnect()) {
					book.mysql.dbConnect();
					System.out.println("connected");
				}
			}
		});
		btn_conn.setBounds(380, 120, 100, 40);
		frame.getContentPane().add(btn_conn);
		
		JButton btn_discon = new JButton("disconnect");
		btn_discon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(book.mysql.dbConnect()) {
					book.mysql.dbClose();
					System.out.println("disconnected");
				}
			}
		});
		btn_discon.setBounds(380, 170, 100, 40);
		frame.getContentPane().add(btn_discon);
	}
}
