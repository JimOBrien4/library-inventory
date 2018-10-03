import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SpinnerNumberModel;

public class obrienNewBook extends JFrame {

	private JPanel contentPane;
	private JTextField bookIDTF;
	private JTextField booknameTF;
	private JTextField authorTF;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param output 
	 * @param queryBuilder 
	 * @param modelbl 
	 */
	public obrienNewBook(QueryBuilder queryBuilder, JTable output, JLabel modelbl) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				exit(queryBuilder, output, modelbl);
			}
		});
		setTitle("Insert New Book - OBrien Book Face");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookId = new JLabel("Book ID:");
		lblBookId.setBounds(10, 11, 46, 14);
		contentPane.add(lblBookId);
		
		JLabel lblBookName = new JLabel("Book Name:");
		lblBookName.setBounds(10, 36, 85, 14);
		contentPane.add(lblBookName);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(10, 61, 46, 14);
		contentPane.add(lblAuthor);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(10, 86, 85, 14);
		contentPane.add(lblCategory);
		
		JLabel lblNewLabel = new JLabel("Wholesale Price:");
		lblNewLabel.setBounds(10, 111, 124, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRetailPrice = new JLabel("Retail Price:");
		lblRetailPrice.setBounds(10, 136, 105, 14);
		contentPane.add(lblRetailPrice);
		
		JLabel lblQoh = new JLabel("QOH:");
		lblQoh.setBounds(10, 161, 46, 14);
		contentPane.add(lblQoh);
		
		JLabel lblMinQuantity = new JLabel("Min Quantity:");
		lblMinQuantity.setBounds(10, 186, 85, 14);
		contentPane.add(lblMinQuantity);
		
		bookIDTF = new JTextField();
		bookIDTF.setToolTipText("Enter an unused number to serve as the unique identifer for the book.");
		bookIDTF.setBounds(173, 11, 105, 20);
		contentPane.add(bookIDTF);
		bookIDTF.setColumns(10);
		
		booknameTF = new JTextField();
		booknameTF.setToolTipText("Enter the title of the book.");
		booknameTF.setBounds(173, 36, 105, 20);
		contentPane.add(booknameTF);
		booknameTF.setColumns(10);
		
		authorTF = new JTextField();
		authorTF.setToolTipText("Enter the author of the book.");
		authorTF.setBounds(173, 61, 105, 20);
		contentPane.add(authorTF);
		authorTF.setColumns(10);
		
		JComboBox categoryCombo = new JComboBox();
		categoryCombo.setToolTipText("Select the category that the book best fits into.");
		categoryCombo.setModel(new DefaultComboBoxModel(new String[] {"Humor", "Biography", "Autobiography", "Literature", "Mystery", "GraphicNovel", "YoungAdult", "Romance", "Scifi", "Other"}));
		categoryCombo.setBounds(173, 86, 105, 20);
		contentPane.add(categoryCombo);
		
		JLabel label = new JLabel("$");
		label.setBounds(173, 114, 16, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("$");
		label_1.setBounds(173, 139, 16, 14);
		contentPane.add(label_1);
		
		JSpinner wholesaleDollarsSpinner = new JSpinner();
		wholesaleDollarsSpinner.setToolTipText("Enter the dollar amount of the wholesale price.");
		wholesaleDollarsSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		wholesaleDollarsSpinner.setBounds(183, 111, 39, 20);
		contentPane.add(wholesaleDollarsSpinner);
		
		JSpinner retailDollarsSpinner = new JSpinner();
		retailDollarsSpinner.setToolTipText("Enter the dollar amount of the retail price.");
		retailDollarsSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		retailDollarsSpinner.setBounds(183, 136, 39, 20);
		contentPane.add(retailDollarsSpinner);
		
		JSpinner qohSpinner = new JSpinner();
		qohSpinner.setToolTipText("Enter the current quantity on hand of the book being entered.");
		qohSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		qohSpinner.setBounds(183, 161, 39, 20);
		contentPane.add(qohSpinner);
		
		JSpinner minQuantSpinner = new JSpinner();
		minQuantSpinner.setToolTipText("Enter the minimum acceptable quantity of the book being entered.");
		minQuantSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		minQuantSpinner.setBounds(183, 186, 39, 20);
		contentPane.add(minQuantSpinner);
		
		JLabel label_2 = new JLabel(".");
		label_2.setBounds(232, 114, 16, 14);
		contentPane.add(label_2);
		
		JSpinner wholesaleCentsSpinner = new JSpinner();
		wholesaleCentsSpinner.setToolTipText("Enter the cent amount of the wholesale price.");
		wholesaleCentsSpinner.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		wholesaleCentsSpinner.setBounds(242, 111, 36, 20);
		contentPane.add(wholesaleCentsSpinner);
		
		JLabel label_3 = new JLabel(".");
		label_3.setBounds(232, 139, 16, 14);
		contentPane.add(label_3);
		
		JSpinner retailCentsSpinner = new JSpinner();
		retailCentsSpinner.setToolTipText("Enter the cent amount of the retail price.");
		retailCentsSpinner.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		retailCentsSpinner.setBounds(242, 136, 36, 20);
		contentPane.add(retailCentsSpinner);
		
		JButton btnInsert = new JButton("OK");
		btnInsert.setToolTipText("Create a new entry in the database with this data.");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "INSERT INTO inventory(BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant) VALUES(";
				query += "'" + bookIDTF.getText() + "',";
				query += "'" + booknameTF.getText() + "',";
				query += "'" + authorTF.getText() + "',";
				query += "'" + categoryCombo.getSelectedItem() + "',";
				String wholesaleCents = "00";
				if((Integer) wholesaleCentsSpinner.getValue() < 10){
					wholesaleCents = "0" + wholesaleCentsSpinner.getValue();
				}
				else{
					wholesaleCents = wholesaleCentsSpinner.getValue() + "";
				}
				String wholesaleprice = wholesaleDollarsSpinner.getValue() + "." + wholesaleCents;
				query += wholesaleprice + ",";
				String retailCents = "00";
				if((Integer) retailCentsSpinner.getValue() < 10){
					retailCents = "0" + retailCentsSpinner.getValue();
				}
				else{
					retailCents = retailCentsSpinner.getValue() + "";
				}
				String retailprice = retailDollarsSpinner.getValue() + "." + retailCents;
				query += retailprice + ",";
				query += qohSpinner.getValue() + ",";
				query += minQuantSpinner.getValue() + ");";
				
				System.out.println(query);
				
				ResultSet rs = null;
				Statement stmt = null;
				
				try{
					Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/OBrienBookFace.accdb");
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT BookID From Inventory WHERE BookID = " + bookIDTF.getText() + " OR BookName = '" + booknameTF.getText() + "';");
					
					if(rs.next()){
						JOptionPane.showMessageDialog(obrienNewBook.this,"Specified Book ID or name is already in use. Please input a different Book ID.","Duplicate Book Error",JOptionPane.ERROR_MESSAGE);
						bookIDTF.grabFocus();
					}
					else{
						//System.out.println("Good!");
						stmt.execute(query);
						JOptionPane.showMessageDialog(obrienNewBook.this, "Book Successfully Inserted");
					}
					
					
					rs.close();
					conn.close();
					
				}
				catch (SQLException ex)
				{
					System.out.println("SQL Exception: " + ex.getMessage());
					System.out.println("SQL State: " + ex.getSQLState());
					System.out.println("Vendor Error: " + ex.getErrorCode());
					ex.printStackTrace();
				}
				
			}
		});
		btnInsert.setBounds(10, 211, 89, 23);
		contentPane.add(btnInsert);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Return to main window without altering the database.");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit(queryBuilder, output, modelbl);
			}
		});
		btnCancel.setBounds(109, 211, 89, 23);
		contentPane.add(btnCancel);
	}

	
	protected void exit(QueryBuilder queryBuilder, JTable output, JLabel modelbl) {
		queryBuilder.buildQuery(output,modelbl);
		this.dispose();
		
	}
}
