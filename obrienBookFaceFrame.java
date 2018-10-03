import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Color;

public class obrienBookFaceFrame extends JFrame {

	private JPanel contentPane;
	private JTable output;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					obrienBookFaceFrame frame = new obrienBookFaceFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public obrienBookFaceFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				exit();
			}
		});
		setTitle("O'Brien Book Face");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//event handler will initiate exit() on attempted close
		setBounds(100, 100, 1378, 730);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//create the menubar and add the file menu and exit item
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		mnFile.add(mntmExit);
		
		//output for sort and filter modes at bottom of the window
		JLabel modelbl = new JLabel("New label");
		modelbl.setFont(new Font("Courier New", Font.PLAIN, 11));
		modelbl.setForeground(Color.GRAY);
		modelbl.setBounds(10, 613, 1342, 57);
		contentPane.add(modelbl);
		
		//create the table that will contain all of our data
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
		
			}
		});
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 1342, 591);
		contentPane.add(scrollPane);
		
		output = new JTable();
		output.setFont(new Font("Courier New", Font.PLAIN, 11));
		scrollPane.setViewportView(output);
		output.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"BookID", "BookName", "AuthorName", "Category", "WholesalePrice", "RetailPrice", "QOH", "MinQuant"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Double.class, Double.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		output.getColumnModel().getColumn(0).setResizable(false);
		output.getColumnModel().getColumn(0).setPreferredWidth(50);
		output.getColumnModel().getColumn(1).setPreferredWidth(250);
		output.getColumnModel().getColumn(2).setPreferredWidth(100);
		output.getColumnModel().getColumn(6).setPreferredWidth(50);
		output.getColumnModel().getColumn(7).setPreferredWidth(50);
		
		//load SQL driver
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} 
		catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		//create the object that remembers out settings and creates and runs our queries, and have it populate our jtable
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.buildQuery(output,modelbl);
		
		//continue populating the menu
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmAddBook = new JMenuItem("Add Book...");
		mntmAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {//creates popup with dialog to add book
					obrienNewBook frame = new obrienNewBook(queryBuilder, output, modelbl);
					frame.setVisible(true);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});
		mnTools.add(mntmAddBook);
		
		JMenu mnSetSort = new JMenu("Set Sort");//allows the user to specify a sort mode
		mnTools.add(mnSetSort);
		
		JMenuItem mntmByBookName = new JMenuItem("By Book Name");
		mntmByBookName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryBuilder.setSortBy("BookName");
				queryBuilder.buildQuery(output,modelbl);
			}
		});
		mnSetSort.add(mntmByBookName);
		
		JMenuItem mntmByRetailPrice = new JMenuItem("By Retail Price");
		mntmByRetailPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryBuilder.setSortBy("RetailPrice");
				queryBuilder.buildQuery(output,modelbl);
			}
		});
		mnSetSort.add(mntmByRetailPrice);
		
		JMenuItem mntmByCategory = new JMenuItem("By Category");
		mntmByCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryBuilder.setSortBy("Category");
				queryBuilder.buildQuery(output,modelbl);
			}
		});
		mnSetSort.add(mntmByCategory);
		
		JMenu mnSetFilter = new JMenu("Set Filter");//allows the user to specify filters
		mnTools.add(mnSetFilter);
		
		JMenuItem mntmByRetailPrice_1 = new JMenuItem("By Retail Price...");
		mntmByRetailPrice_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					obrienRetailPrice frame = new obrienRetailPrice(queryBuilder, output, modelbl);
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		mnSetFilter.add(mntmByRetailPrice_1);
		
		JMenuItem mntmByCategory_1 = new JMenuItem("By Category...");
		mntmByCategory_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {//allows users to enable to disable viewing certain categories
					obrienCategory frame = new obrienCategory(queryBuilder, output, modelbl);
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		mnSetFilter.add(mntmByCategory_1);
		//clear sort settings
		JMenuItem mntmRemoveSort = new JMenuItem("Remove Sort");
		mntmRemoveSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryBuilder.setSortBy("BookID");
				queryBuilder.buildQuery(output,modelbl);
			}
		});
		mnTools.add(mntmRemoveSort);
		//clear filter settings
		JMenuItem mntmRemoveFilter = new JMenuItem("Remove Filter");
		mntmRemoveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryBuilder.setMaxPrice(-1);
				queryBuilder.setMinPrice(-1);
			
				
				for (Object category : queryBuilder.categories.keySet().toArray()){
					queryBuilder.setCategory(category + "", false);
				}
				queryBuilder.buildQuery(output,modelbl);
			}
		});
		mnTools.add(mntmRemoveFilter);
		//create help menu
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmShowHelp = new JMenuItem("Show Help...");
		mntmShowHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(obrienBookFaceFrame.this,"<html><table><tr><td><b>Function</b></td><td><b>Menu Location</b></td><td><b>Description</b></td></tr><tr><td>Add Book</td><td>Tools -> Add Book...</td><td>Displays dialog to insert a new book into the database. Book ID and name must be an unused/unique to identify the book, but the rest of the data </td></tr><tr><td></td><td></td><td>does not have to be unique. </td></tr><tr><td>Set Sort</td><td>Tools -> Set Sort</td><td>Selecting \"By Book Name\" \"By Retail Price,\" and \"By Category\" will reorder the displayed data by book name, retail price, and category respectively. </td></tr><tr><td>Filter By Retail Price</td><td>Tools -> Set Filter -> By Retail Price...</td><td>Provides checkboxes to enable and disable minimum and maximum prices of books to display. Both can be selected to show a specific range. </td></tr><tr><td>Filter By Retail Category</td><td>Tools -> Set Filter -> By Category...</td><td>Provides checkboxes to select which categories to display. If none are selected, all categories will be displayed. </td></tr><tr><td>Remove Sort</td><td>Tools -> Remove Sort</td><td>Removes any specified sort setting and redisplays data sorted by Book ID. </td></tr><tr><td>Remove Filter</td><td>Tools -> Remove Filter</td><td>Removes any specified filter setting and redisplays all data. </td></tr></table></html>","Help",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmShowHelp);
		
		
		
		
					
		
			}
	
	public void exit(){
		if((JOptionPane.showConfirmDialog(this,"Are you sure you want to quit?","Confirm Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)){
			this.dispose();
		}
	}
}


class QueryBuilder {
	String query = null;
	String sortBy = "BookID";
	double minPrice = -1;
	double maxPrice = -1;
	JTable output;
	JLabel modelbl;
	//saves whether or not each category is being sorted by - true for being sorted by, false for not being sorted by
	HashMap<String,Boolean> categories = new HashMap(10);
	{
		categories.put("humor",false);
		categories.put("biography",false);
		categories.put("autobiography",false);
		categories.put("literature",false);
		categories.put("mystery",false);
		categories.put("graphicNovel",false);
		categories.put("youngAdult",false);
		categories.put("romance",false);
		categories.put("scifi",false);
		categories.put("other",false);
	}
	
	
	public QueryBuilder(){
		
	}
	//get and set different data fields
	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
		System.out.println(this.minPrice);//debug
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	public void setCategory(String category,Boolean sort){
		this.categories.remove(category);
		this.categories.put(category, sort);
	}
	
	//builds and returns query based on above settings
	public void buildQuery(JTable output, JLabel modelbl){
		this.query = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory WHERE 1 = 1 ";
		if(minPrice > 0){
			this.query += "AND RetailPrice > " + minPrice + " "; 
		}
		if(maxPrice > 0){
			this.query += "AND RetailPrice < " + maxPrice + " "; 
		}
		boolean usingCategory = false;
		for (Map.Entry<String,Boolean> category : categories.entrySet()){
			if(usingCategory == false && category.getValue()){
				this.query += "AND (1 = 2 ";
				usingCategory = true;
			}
			if(category.getValue()){
				this.query += "OR Category = '" + category.getKey()  + "' ";
			}
		}
		if(usingCategory == true){
			this.query+= ") ";
		}
		this.query += "ORDER BY " + this.sortBy + ";";
		

		System.out.println(this.query);//debug
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			//establish database connection
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/BookFace.accdb");
			stmt = conn.createStatement();
			//run the query and save results in the result set
			rs = stmt.executeQuery(query);
			
			//clear out the current table data
			while(output.getRowCount() > 0){
				((DefaultTableModel) output.getModel()).removeRow(0);
			}	
			
			//repopulate the jtable
			
			int numColumns = rs.getMetaData().getColumnCount();
			
			while(rs.next()){
				Object[] row = new Object[numColumns];
				
				for (int i = 0; i < numColumns; i++){
					row[i] = rs.getObject(i+1);
				}
				
				((DefaultTableModel) output.getModel()).insertRow(rs.getRow() - 1, row);
			}
			
			rs.close();
			conn.close();
			
			//creates a summary of all current sorts and filters to be displayed at the bottom of the screen
			String summary = "Displaying all books ";
			if((minPrice > 0) & (maxPrice > 0)){
				summary += "between $" + minPrice + " and $" + maxPrice + " ";
			}
			else if(minPrice > 0){
				summary += "above $" + minPrice + " ";
			}
			else if(maxPrice > 0){
				summary += "below $" + maxPrice + " ";
			}
			
			if(categories.containsValue(true)){
				summary += "from the following categories: ";
				for (Entry<String, Boolean> category : categories.entrySet()){
					if(category.getValue()){
						summary += category.getKey() + ", ";
					}
				}
			}
			summary += "sorted by ";
			if(sortBy != "BookID"){
				summary += sortBy + ".";
			}
			else{
				summary += "default sort.";
			}
			modelbl.setText(summary);
		}
		catch (SQLException ex)
		{
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("Vendor Error: " + ex.getErrorCode());
			ex.printStackTrace();
		}
	}
}
