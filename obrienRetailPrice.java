import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class obrienRetailPrice extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param queryBuilder 
	 * @param output 
	 * @param modelbl 
	 */
	public obrienRetailPrice(QueryBuilder queryBuilder, JTable output, JLabel modelbl) {
		setTitle("Sort By Retail Price - OBrien Book Face");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 299, 137);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMin = new JLabel("Min:");
		lblMin.setBounds(10, 11, 46, 14);
		contentPane.add(lblMin);
		
		JLabel lblMax = new JLabel("Max:");
		lblMax.setBounds(10, 36, 46, 14);
		contentPane.add(lblMax);
		
		JSpinner spinnerMin = new JSpinner();
		spinnerMin.setToolTipText("When enabled, set the minimum retail price of books to be displayed.");
		spinnerMin.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		spinnerMin.setEnabled(false);
		spinnerMin.setBounds(44, 8, 46, 20);
		contentPane.add(spinnerMin);
		
		JSpinner spinnerMax = new JSpinner();
		spinnerMax.setToolTipText("When enabled, set the maximum retail price of books to be displayed.");
		spinnerMax.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		spinnerMax.setEnabled(false);
		spinnerMax.setBounds(44, 36, 46, 20);
		contentPane.add(spinnerMax);
		
		JCheckBox enableMin = new JCheckBox("Filter Minimum Price");
		enableMin.setToolTipText("When selected, books below the specified minimum price wil be filtered out.");
		enableMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(enableMin.isSelected()){
					spinnerMin.setEnabled(true);
				}
				else{
					spinnerMin.setEnabled(false);
				}
			}
		});
		enableMin.setBounds(96, 8, 181, 23);
		contentPane.add(enableMin);
		
		JCheckBox enableMax = new JCheckBox("Filter Maximum Price");
		enableMax.setToolTipText("When selected, books above the specified maximum price wil be filtered out.");
		enableMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(enableMax.isSelected()){
					spinnerMax.setEnabled(true);
				}
				else{
					spinnerMax.setEnabled(false);
				}
			}
		});
		enableMax.setBounds(96, 33, 142, 23);
		contentPane.add(enableMax);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setToolTipText("Use specified filters.");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(spinnerMax.isEnabled()){
					queryBuilder.setMaxPrice((double) spinnerMax.getValue());
				}
				else{
					queryBuilder.setMaxPrice(-1);
				}
				if(spinnerMin.isEnabled()){
					queryBuilder.setMinPrice((double) spinnerMin.getValue());
				}
				else{
					queryBuilder.setMinPrice(-1);
				}
				closeWindow(queryBuilder, output, modelbl);
			}
		});
		btnConfirm.setBounds(10, 67, 89, 23);
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Return to main window without altering settings.");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow(queryBuilder, output, modelbl);
			}
		});
		btnCancel.setBounds(109, 67, 89, 23);
		contentPane.add(btnCancel);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
						closeWindow(queryBuilder, output, modelbl);
			}
		});
	}


	protected void closeWindow(QueryBuilder queryBuilder, JTable output, JLabel modelbl) {
		queryBuilder.buildQuery(output,modelbl);
		this.dispose();
		
	}

	
	
	
}
