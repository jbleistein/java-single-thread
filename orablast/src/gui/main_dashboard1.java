package gui;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import db_access.create_db_connections;
import db_access.db_connect;
//import mem_structs.dbname_hash_map;
import mem_structs.hash_map;
import thread.blast_thread;
import thread.build_jtable;
import thread.monitor_db_thread1;


import javax.swing.JScrollPane;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.table.*;
import javax.swing.JSlider;
import javax.swing.JMenuBar;


public class main_dashboard1 extends Thread implements ActionListener {
	

	public static JFrame frmOracleRrtDashboard;
	public static JTable table_1;
	public static DefaultTableModel model;
	public static DefaultTableModel model_2;
	public static DefaultTableModel model_3;
	JButton btnNewButton;
	JButton btnNewButton_1;
	JButton button;
	public static JTextArea textArea;
	public static blast_thread bt1;
	public static blast_thread bt2;
	JButton btnBrowse;
	File files_in_output_dir[];

	String DB_SERVER="192.168.240.134";
	String DB_SERVER_PORT="1521";
	String DB_SERVER_SERVICE_NAME="pdb1.ats.local";
	String DB_USER_NAME="app";
	String DB_USER_PWD="app123";
	String ORA_JDBC_CONNECT_STRING;
	static String [] array;
	monitor_db_thread1 mt1;
	public static hash_map hm;
	JPanel panel;
	public static JFrame pbjf2;
	public static JFrame pbjf1;
    Connection c2=null;
	private JTextField textField_1;
	JPopupMenu popupMenu;
    JMenuItem menuItemAdd;
    public static build_jtable bjt1;
    private JTable table;
    private JTable dod_am_csv_file_data_table;
    private JTable dod_gen_ddl_stmts_table;

 

	
	public main_dashboard1() {
	
		frmOracleRrtDashboard = new JFrame();
		frmOracleRrtDashboard.setTitle("Oracle RRT Dashboard");
		frmOracleRrtDashboard.setBounds(100, 100, 891, 591);
		frmOracleRrtDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOracleRrtDashboard.getContentPane().setLayout(new BoxLayout(frmOracleRrtDashboard.getContentPane(), BoxLayout.X_AXIS));
		frmOracleRrtDashboard.setLocationRelativeTo(null);
		frmOracleRrtDashboard.setVisible(true);
		frmOracleRrtDashboard.setResizable(false);
		
		frmOracleRrtDashboard.addWindowListener(new java.awt.event.WindowAdapter() {
		
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        
		    	int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
				    
					System.exit(0);
				}
			
		    }
		});
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenu menu_2 = new JMenu("Settings");
		JMenu menu_3 = new JMenu("Help");
				
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Exit");
		menu.add(menuItem);
		
		menuBar.add(menu_2);
		JMenu menu_4 = new JMenu("Blast");
		JMenuItem menuItem_2 = new JMenuItem("Output file settings");
		menu_2.add(menu_4);
		menu_4.add(menuItem_2);
	
		JMenu menu_5 = new JMenu("DOD");
		JMenuItem menuItem_4 = new JMenuItem("DOD setting");
		menu_2.add(menu_5);
		menu_5.add(menuItem_4);
		

		/*JMenu menu_6 = new JMenu("Tablespace");
		JMenuItem menuItem_5 = new JMenuItem("Tablespace setting");
		menu_2.add(menu_6);
		menu_6.add(menuItem_5);
		*/

		/*JMenu menu_7 = new JMenu("DB Open Check");
		JMenuItem menuItem_6 = new JMenuItem("DB Open Check setting");
		menu_2.add(menu_7);
		menu_7.add(menuItem_6);
		*/
		menuBar.add(menu_3);
		JMenuItem menuItem_3 = new JMenuItem("About OraBlast");
		menu_3.add(menuItem_3);
		
		
		frmOracleRrtDashboard.setJMenuBar(menuBar);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmOracleRrtDashboard.getContentPane().add(tabbedPane);
		
	    panel = new JPanel();
		tabbedPane.addTab("Blast", null, panel, null);
		panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("DOD", null, panel_1, null);
		panel_1.setLayout(null);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Generated Oracle DDL statement:");
		lblNewLabel_1.setBounds(32, 231, 249, 16);
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane_3_1_1 = new JScrollPane((Component) null);
		scrollPane_3_1_1.setBounds(32, 259, 807, 164);
		panel_1.add(scrollPane_3_1_1);
		
		dod_gen_ddl_stmts_table = new JTable();
		scrollPane_3_1_1.setRowHeaderView(dod_gen_ddl_stmts_table);
		
		JButton dod_browse_button = new JButton("Browse");
		dod_browse_button.setBounds(32, 466, 117, 29);
		panel_1.add(dod_browse_button);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("CSV/Excel Acccess Manager file data");
		lblNewLabel_1_1_1.setBounds(42, 441, 249, 16);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("CSV/Excel Acccess Manager file data: ");
		lblNewLabel_1_1_1_1.setBounds(32, 16, 249, 16);
		panel_1.add(lblNewLabel_1_1_1_1);
		

		/*JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Tablespace", null, panel_4, null);
		panel_4.setLayout(null);
		*/
		
		btnNewButton = new JButton("Blast");
		btnNewButton.setBounds(747, 353, 117, 29);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setBounds(630, 353, 117, 29);
		panel.add(btnNewButton_1);

		textArea = new JTextArea();
		textArea.setBounds(274, 22, 568, 319);
		panel.add(textArea);
				
	   model = new DefaultTableModel();
       model_2 = new DefaultTableModel();
       model_3 = new DefaultTableModel();
		        
		model.addColumn("Host");
        model.addColumn("DB");   
        model.addColumn("Status");
 

		
		/*JSlider slider = new JSlider();
		slider.setBounds(289, 187, 445, 29);
		panel_4.add(slider);
		*/
		
        /*JScrollPane scrollPane_5 = new JScrollPane((Component) null);
		scrollPane_5.setBounds(259, 242, 401, 126);
		panel_4.add(scrollPane_5);
		*/
        
		/*JLabel lblNewLabel = new JLabel("Primary space:");
		lblNewLabel.setBounds(259, 214, 132, 16);
		panel_4.add(lblNewLabel);
		*/
        
        /*
		JScrollPane scrollPane_4 = new JScrollPane((Component) null);
		scrollPane_4.setBounds(259, 391, 401, 126);
		panel_4.add(scrollPane_4);
		*/
        
		/*JLabel lblStandbyFilesystems = new JLabel("Standby space:");
		lblStandbyFilesystems.setBounds(259, 368, 132, 16);
		panel_4.add(lblStandbyFilesystems);
		*/
        
		/*JLabel lblDatafileSize = new JLabel("Datafile size:");
		lblDatafileSize.setBounds(215, 174, 132, 16);
		panel_4.add(lblDatafileSize);
		*/
        
		/*JButton btnNewButton_3 = new JButton("Apply");
		btnNewButton_3.setBounds(747, 488, 117, 29);
		panel_4.add(btnNewButton_3);
		*/
        
		/*JLabel lblDatafiles = new JLabel("Datafiles:");
		lblDatafiles.setBounds(215, 6, 132, 16);
		panel_4.add(lblDatafiles);
		*/

		model_2.addColumn("Tspace");
		model_2.addColumn("Total");
		model_2.addColumn("Used");
		model_2.addColumn("Free");
		
		
		model_3.addColumn("Name");
		model_3.addColumn("Account");
		model_3.addColumn("User");
		model_3.addColumn("UID");
		model_3.addColumn("Ent Name");
		model_3.addColumn("Ent Value");
		model_3.addColumn("Action");
		

		dod_am_csv_file_data_table = new JTable(model_3);
		//scrollPane_3_1.setRowHeaderView(dod_am_csv_file_data_table);
		

		JScrollPane scrollPane_3_1 = new JScrollPane(dod_am_csv_file_data_table);
		scrollPane_3_1.setBounds(32, 44, 807, 164);
		panel_1.add(scrollPane_3_1);
		
		JButton dod_blast_button = new JButton("Blast");
		dod_blast_button.setBounds(747, 466, 117, 29);
		panel_1.add(dod_blast_button);
		
		

		/*JTable table_2 = new JTable(model_2);
		table_2.setBounds(6, 22, 133, 483);
		panel_4.add(table_2);
		
		
		JScrollPane scrollPane_2 = new JScrollPane((table_2));
		scrollPane_2.setBounds(0, 307, 228, 210);
		panel_4.add(scrollPane_2);
		
		table = new JTable();
		table.setBounds(42, 240, 24, -25);
		panel_4.add(table);	
		*/

		table_1 = new JTable(model);
		//table_1.setBounds(6, 22, 133, 483);
		table_1.setBounds(6, 6, 197, 448);
		panel.add(table_1); // Add to blast tab
		frmOracleRrtDashboard.getContentPane().add(tabbedPane);
		
		

		/*JPanel panel_2 = new JPanel();
		tabbedPane.addTab("DB Open check", null, panel_2, null);
		frmOracleRrtDashboard.getContentPane().add(tabbedPane);
		*/

		

		textField_1 = new JTextField();
		textField_1.setBounds(16, 465, 145, 29);
		panel.add(textField_1);
		textField_1.setColumns(10);

		RowSorter<? extends TableModel> rs = table_1.getRowSorter();
        if (rs == null) {
            table_1.setAutoCreateRowSorter(true);
            rs = table_1.getRowSorter();
        }

        TableRowSorter<? extends TableModel> rowSorter =
                (rs instanceof TableRowSorter) ? (TableRowSorter<? extends TableModel>) rs : null;

        if (rowSorter == null) {
            throw new RuntimeException("Cannot find appropriate rowSorter: " + rs);
        }

        textField_1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update(e);
            }

            private void update(DocumentEvent e) {
                String text = textField_1.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

		
		JScrollPane scrollPane = new JScrollPane(table_1);
		//scrollPane.setBounds(10, 21, 197, 425);
		scrollPane.setBounds(6, 6, 197, 448);
		panel.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(274, 22, 568, 319);
		panel.add(scrollPane_1);
		
		button = new JButton("Clear");
		button.setBounds(173, 466, 117, 29);
		panel.add(button);
		btnNewButton.addActionListener(this);
		button.addActionListener(this);
		btnNewButton_1.addActionListener(this);
				
		//Build list of databases in table.
        
	       bjt1 = new build_jtable();
	       bjt1.start();
	       

			//Progress bar while building list of databases in table
	       

		     EventQueue.invokeLater(new Runnable() {
	        	public void run() {
					try {
					
				        pbjf2 = new JFrame();
				        pbjf2.setSize(200,100);
					    pbjf2.setLocationRelativeTo(null);
					    JPanel jp2= new JPanel();
					    JProgressBar pb2 = new JProgressBar();	
					    pb2.setIndeterminate(true);
					    jp2.add(pb2);
					    pbjf2.getContentPane().add(jp2);
					    pbjf2.setAlwaysOnTop(true);
					    JLabel jl3 = new JLabel("Building database list...");
					    jp2.add(jl3);
					    pbjf2.pack();
					    pbjf2.setVisible(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});		 	        

	        JPopupMenu popupMenu = new JPopupMenu();
	        menuItemAdd = new JMenuItem("View output");
		    table_1.setComponentPopupMenu(popupMenu);
		    
		    popupMenu.add(menuItemAdd);
	        menuItemAdd.setEnabled(true);
	        menuItemAdd.addActionListener(this);
	        
	       /* menuItemAdd.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                    System.out.println("CLICKED");
	            }
	        });*/
	 

		    
		    btnBrowse = new JButton("Browse");
		    btnBrowse.setBounds(513, 353, 117, 29);
		    panel.add(btnBrowse);
		    
		   // ActionListener actionListener = new PopupActionListener();
	    
	        
	        
	        btnBrowse.addActionListener(this);
	        
	        
	       //Move old output files, if they exist, from the output directory to the archive directory when the app starts.
	       //Note on MacOS the .DS_Store file is a hidden file created in all directories. Be aware of this.
	        
	        File output_dir = new File("../output/"); //Need to come back and change this object to point to the cfg file property or the env var if set. - 11/07/2020
	        
	        files_in_output_dir = output_dir.listFiles();
	        	
	        	if (files_in_output_dir.length > 0) {
	        		
	        		System.out.println("There are files in the output dir");
	        		
	        		for (int i=0; i < files_in_output_dir.length;i++) {
	        			
	        			files_in_output_dir[i].renameTo(new File("../archive/"+files_in_output_dir[i].getName()));
	        			
	        			//Debug for the file move action.
	        			//System.out.println("Moved file: " +files_in_output_dir[i]+ " to: ../archive/" +files_in_output_dir[i].getName());
	        			
	        		}
	        		
	        
	        		
	        	} else {
	        			
	        				System.out.println("There are no files in the output dir");
	        			
     		}
	
	}
	
	public void actionPerformed(ActionEvent e) {
	
		
		if (e.getSource() == menuItemAdd) { //View output popup menu item
			

				String file_name = blast_thread.hm2.get_pdb_ind_hm(table_1.getValueAt(table_1.getSelectedRow(), 1).toString());	
			
				  File f = new File(file_name);
					
				  try {
					Desktop.getDesktop().edit(f);
				} catch (IOException a) {
					
					System.out.println("CLICKED AND ABENDED");
					a.printStackTrace();
				}
			
		}
		
		if(e.getSource() == btnBrowse) {
			
			JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    fileChooser.showOpenDialog(null);
		    
		}
		
		
		if (e.getSource() == button) {
			
			table_1.clearSelection();
			
			
			for (int a=0;a < table_1.getRowCount();a++) {
				
				table_1.setValueAt("", a, 2);
				
				
			}
			
			
		}
		
		if (e.getSource() == btnNewButton_1) {
			
			textArea.setText(null);
			
		
		}
			
			if (e.getSource() == btnNewButton) {
				//blast
				
				
				int [] SelectedRow = table_1.getSelectedRows();
				
				for (int i = 0;i < SelectedRow.length;i++) {
					
					String data = (String) table_1.getValueAt(SelectedRow[i], 1);	
					
				}
				
				
				array = new String[SelectedRow.length];
				
				for (int i = 0; i < array.length;i++ )
				
				{
					
					array[i] = (String) table_1.getValueAt(SelectedRow[i], 1);
					
				}
				
				    //Build hash table structure in memory so that the blast threads can look up the index number of the GUI table row by db name to mark each run 
				    //as SUCCESS or ERROR.
				      
				      hm = new hash_map();
					  hm.create_pdb_ind_hm();
					  
					//Test to see if main SQL text area in the main dashboard is null.
					  
					  if (textArea.getText().equals("")) {

						     EventQueue.invokeLater(new Runnable() {
					        	public void run() {
									try {
										
										JOptionPane optionPane1 = new JOptionPane("SQL text area is blank. Please fill", JOptionPane.ERROR_MESSAGE);
							  			JDialog dialog = optionPane1.createDialog("Error");
							  			dialog.setAlwaysOnTop(true);
							  			dialog.setVisible(true);
							  			
										
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});		 	        

					  }
					  

					  else if (SelectedRow.length == 0) {
							
							EventQueue.invokeLater(new Runnable() {
					        	public void run() {
									try {
										
										JOptionPane optionPane1 = new JOptionPane("No selected databases for blast. Please select one or more databases", JOptionPane.ERROR_MESSAGE);
							  			JDialog dialog = optionPane1.createDialog("Error");
							  			dialog.setAlwaysOnTop(true);
							  			dialog.setVisible(true);
							  			
										
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});		 	        
							
							
						}
					  
					  else {
				      
					  
				      
					   //Start DB worker threads.   
						  
						  
					      bt1 = new blast_thread(array);
					      bt1.start();
					
		
						    //Progress bar while running blast
						    
						    
						    pbjf1 = new JFrame();
						    pbjf1.setSize(200,100);
						    pbjf1.setLocationRelativeTo(null);
						    JPanel jp1 = new JPanel();
						    JProgressBar pb1 = new JProgressBar();	
						    pb1.setIndeterminate(true);
						    jp1.add(pb1);
						    pbjf1.getContentPane().add(jp1);
						    JLabel jl2 = new JLabel("Blasting SQL out to databases...");
						    jp1.add(jl2);
						    pbjf1.pack();
						    pbjf1.setVisible(true);
						    
						    
				    
				  //Thread to monitor other threads. Nothing fancy from a concurrency perspective just know when the DB worker threads are done running.
				    
				    mt1 = new monitor_db_thread1();
				    mt1.start();
				    
					  }
				  }
	}
	
	}