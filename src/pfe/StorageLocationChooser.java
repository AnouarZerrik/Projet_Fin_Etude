package pfe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StorageLocationChooser extends JFrame implements ActionListener {
	private JTextField textField;
	private JFileChooser fileChooser;
	
	
	
	public String user;
	public String password;
	public String database;
	public String db;

	public StorageLocationChooser(String user,String password, String database , String db) {
		
		this.password = password;
		this.user = user;
		this.database = database;

		this.db = db;
		
		setTitle("Sélectionnez l'emplacement de stockage");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(500, 200);

		// Création d'un panneau principal avec une bordure vide pour l'espacement
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Création d'un panneau pour le texte et le bouton de sélection de fichier
		JPanel filePanel = new JPanel(new BorderLayout());
		filePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel fileLabel = new JLabel("Emplacement de stockage : ");
		textField = new JTextField(30);
		textField.setEditable(false);
		JButton browseButton = new JButton("Parcourir");
		browseButton.addActionListener(this);

		filePanel.add(fileLabel, BorderLayout.WEST);
		filePanel.add(textField, BorderLayout.CENTER);
		filePanel.add(browseButton, BorderLayout.EAST);

		// Ajout des panneaux au panneau principal
		mainPanel.add(filePanel, BorderLayout.CENTER);

		// Création d'un panneau pour les boutons OK et Annuler
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton okButton = new JButton("OK");
		okButton.addActionListener(this);
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(this);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);

		// Ajout du panneau de boutons au panneau principal
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Création du sélecteur de fichier
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// Ajout du panneau principal à la fenêtre
		add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Parcourir")) {
			// Afficher le sélecteur de fichier et mettre à jour le champ de texte
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				textField.setText(file.getAbsolutePath());
			}
		} else if (e.getActionCommand().equals("OK")) {
			// Récupérer l'emplacement de stockage et faire quelque chose avec
			String storageLocation = textField.getText();
			PreparedStatement stmt = null;
	        ResultSet rs = null;
	        FileWriter writer = null;
	        connection con = new connection();
			switch (db) {
			
			case "Oracle":
		     // String url = "jdbc:oracle:thin:@localhost:1521:xe?useServerPrepStmts=false"; // include useServerPrepStmts=false option
		       // String user = StorageLocationChooser.this.user; //replace with your own database username
		        //String password = StorageLocationChooser.this.password; //replace with your own database password
				
				con.connection(this.user, this.password);
		        
		        try {
		                 
		            String query = "SELECT * FROM RESULTAT"; //replace with your own table name
		            stmt = con.connection.prepareStatement(query);
		            rs = stmt.executeQuery();
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		            String timestamp = sdf.format(new Date());
		            String fileName = storageLocation + "\\csv" + timestamp + ".csv"; //replace with your own file name prefix
		            writer = new FileWriter(fileName);
		            ResultSetMetaData metadata = rs.getMetaData();
		            int numColumns = metadata.getColumnCount();
		            for (int i = 1; i <= numColumns; i++) {
		                writer.write(metadata.getColumnName(i));
		                if (i != numColumns) {
		                    writer.write(",");
		                }
		            }
		            writer.write("\n");
		            while (rs.next()) {
		                StringBuilder sb = new StringBuilder();
		                for (int i = 1; i <= numColumns; i++) {
		                    sb.append(rs.getString(i));
		                    if (i != numColumns) {
		                        sb.append(",");
		                    }
		                }
		                writer.write(sb.toString());
		                writer.write("\n");
		            }
		            JOptionPane.showMessageDialog(null, "Success");
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        } finally {
		            try {
		                if (rs != null) {
		                    rs.close();
		                }
		                if (stmt != null) {
		                    stmt.close();
		                }
		                if (con.connection != null) {
		                	con.connection.close();
		                }
		                if (writer != null) {
		                    writer.close();
		                }
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
		        }
			case "MySQL":
				

			      //String url = "jdbc:oracle:thin:@localhost:1521:xe?useServerPrepStmts=false"; // include useServerPrepStmts=false option
			      //  String user = StorageLocationChooser.this.user; //replace with your own database username
			        //String password = StorageLocationChooser.this.password; //replace with your own database password
			        //Connection conn = null;
				con.connection2(this.user, this.password,this.database);
			        try {
			        	
			            String query = "SELECT * FROM RESULTAT"; //replace with your own table name
			            stmt = con.connection.prepareStatement(query);
			            rs = stmt.executeQuery();
			            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			            String timestamp = sdf.format(new Date());
			            String fileName = storageLocation + "\\csv" + timestamp + ".csv"; //replace with your own file name prefix
			            writer = new FileWriter(fileName);
			            ResultSetMetaData metadata = rs.getMetaData();
			            int numColumns = metadata.getColumnCount();
			            for (int i = 1; i <= numColumns; i++) {
			                writer.write(metadata.getColumnName(i));
			                if (i != numColumns) {
			                    writer.write(",");
			                }
			            }
			            writer.write("\n");
			            while (rs.next()) {
			                StringBuilder sb = new StringBuilder();
			                for (int i = 1; i <= numColumns; i++) {
			                    sb.append(rs.getString(i));
			                    if (i != numColumns) {
			                        sb.append(",");
			                    }
			                }
			                writer.write(sb.toString());
			                writer.write("\n");
			            }
			            JOptionPane.showMessageDialog(null, "Success");
			        } catch (Exception e1) {
			            e1.printStackTrace();
			        } finally {
			            try {
			                if (rs != null) {
			                    rs.close();
			                }
			                if (stmt != null) {
			                    stmt.close();
			                }
			                if (con.connection != null) {
			                	con.connection.close();
			                }
			                if (writer != null) {
			                    writer.close();
			                }
			            } catch (Exception e1) {
			                e1.printStackTrace();
			            }
			        }
				
				
				
			}
			dispose();
		} else if (e.getActionCommand().equals("Annuler")) {
			// Fermer la fenêtre sans rien faire
			dispose();
		}
	}

}
