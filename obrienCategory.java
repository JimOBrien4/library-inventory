import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class obrienCategory extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param queryBuilder 
	 * @param output 
	 */
	public obrienCategory(QueryBuilder queryBuilder, JTable output, JLabel modelbl) {
		setTitle("Sort By Category - OBrien Book Face");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 454, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxHumor = new JCheckBox("Humor");
		chckbxHumor.setBounds(6, 31, 110, 23);
		contentPane.add(chckbxHumor);
		
		JCheckBox chckbxBiography = new JCheckBox("Biography");
		chckbxBiography.setBounds(6, 57, 110, 23);
		contentPane.add(chckbxBiography);
		
		JCheckBox chckbxAutobiography = new JCheckBox("Autobiography");
		chckbxAutobiography.setBounds(6, 83, 110, 23);
		contentPane.add(chckbxAutobiography);
		
		JCheckBox chckbxLiterature = new JCheckBox("Literature");
		chckbxLiterature.setBounds(118, 31, 109, 23);
		contentPane.add(chckbxLiterature);
		
		JCheckBox chckbxMystery = new JCheckBox("Mystery");
		chckbxMystery.setBounds(118, 57, 109, 23);
		contentPane.add(chckbxMystery);
		
		JCheckBox chckbxGraphicNovel = new JCheckBox("Graphic Novel");
		chckbxGraphicNovel.setBounds(118, 83, 109, 23);
		contentPane.add(chckbxGraphicNovel);
		
		JCheckBox chckbxYoungAdult = new JCheckBox("Young Adult");
		chckbxYoungAdult.setBounds(229, 31, 146, 23);
		contentPane.add(chckbxYoungAdult);
		
		JCheckBox chckbxRomance = new JCheckBox("Romance");
		chckbxRomance.setBounds(229, 57, 146, 23);
		contentPane.add(chckbxRomance);
		
		JCheckBox chckbxScienceFiction = new JCheckBox("Science Fiction");
		chckbxScienceFiction.setBounds(229, 83, 146, 23);
		contentPane.add(chckbxScienceFiction);
		
		JCheckBox chckbxOther = new JCheckBox("Other");
		chckbxOther.setBounds(6, 109, 146, 23);
		contentPane.add(chckbxOther);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setToolTipText("Only display the selected categories.");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxHumor.isSelected()){
					queryBuilder.setCategory("humor", true);
				}
				else{
					queryBuilder.setCategory("humor", false);
				}
				
				//chckbxBiography
				if(chckbxBiography.isSelected()){
					queryBuilder.setCategory("biography", true);
				}
				else{
					queryBuilder.setCategory("biography", false);
				}
				//chckbxAutobiography = new JCheckBox("Autobiography");
				if(chckbxAutobiography.isSelected()){
					queryBuilder.setCategory("autobiography", true);
				}
				else{
					queryBuilder.setCategory("autobiography", false);
				}
				//chckbxLiterature = new JCheckBox("Literature");
				if(chckbxLiterature.isSelected()){
					queryBuilder.setCategory("literature", true);
				}
				else{
					queryBuilder.setCategory("literature", false);
				}
				
				//chckbxMystery = new JCheckBox("Mystery");
				if(chckbxMystery.isSelected()){
					queryBuilder.setCategory("mystery", true);
				}
				else{
					queryBuilder.setCategory("mystery", false);
				}
				//chckbxGraphicNovel = new JCheckBox("Graphic Novel");
				if(chckbxGraphicNovel.isSelected()){
					queryBuilder.setCategory("graphicNovel", true);
				}
				else{
					queryBuilder.setCategory("graphicNovel", false);
				}
				//chckbxYoungAdult = new JCheckBox("Young Adult");
				if(chckbxYoungAdult.isSelected()){
					queryBuilder.setCategory("youngAdult", true);
				}
				else{
					queryBuilder.setCategory("youngAdult", false);
				}
				//chckbxRomance = new JCheckBox("Romance");
				if(chckbxRomance.isSelected()){
					queryBuilder.setCategory("romance", true);
				}
				else{
					queryBuilder.setCategory("romance", false);
				}
				//chckbxScienceFiction = new JCheckBox("Science Fiction");
				if(chckbxScienceFiction.isSelected()){
					queryBuilder.setCategory("scifi", true);
				}
				else{
					queryBuilder.setCategory("scifi", false);
				}
				//xOther = new JCheckBox("Other");
				if(chckbxOther.isSelected()){
					queryBuilder.setCategory("other", true);
				}
				else{
					queryBuilder.setCategory("other", false);
				}
				closeWindow(queryBuilder, output, modelbl);
				
			}
		});
		btnConfirm.setBounds(6, 139, 89, 23);
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Return to main window without altering settings.");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow(queryBuilder, output, modelbl);
			}
		});
		btnCancel.setBounds(105, 139, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblSelectWhichCategories = new JLabel("Select which categories to display:");
		lblSelectWhichCategories.setBounds(10, 10, 385, 14);
		contentPane.add(lblSelectWhichCategories);
		
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
