package pfe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class connexion_interface {

	public JFrame frame;
	private static JTextField userField;
	private static JTextField dbNameField;
	private static JTextField passwordField;
	JLabel lblNewLabel_3 = new JLabel("");
	private final Properties properties = new Properties();
	static String db;

	/**
	 * Launch the application.
	 */

	static String[] values_con() {
		String tab[] = null;

		tab[0] = userField.getText();
		tab[1] = passwordField.getText();

		return tab;
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	 * @throws FileNotFoundException 
	 */
	public connexion_interface() throws FileNotFoundException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.disabledShadow"));
		frame.setBounds(100, 100, 682, 467);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom d'utilisateur :");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel.setBounds(390, 65, 195, 30);
		frame.getContentPane().add(lblNewLabel);
		
		
		try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		userField = new JTextField();
		userField.setBounds(390, 106, 232, 42);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		//userField.setText(xml.get_element_xml()[0]);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 342, 434);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(
				new ImageIcon(connexion_interface.class.getResource("/img/wallpaperflare.com_wallpaper (3).jpg")));
		lblNewLabel_1.setBounds(0, 0, 367, 482);
		panel.add(lblNewLabel_1);

		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblMotDePasse.setBounds(390, 159, 173, 30);
		frame.getContentPane().add(lblMotDePasse);

		JLabel lblNewLabel_1_1 = new JLabel("Nom de la base de données :");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(390, 253, 232, 30);
		frame.getContentPane().add(lblNewLabel_1_1);

		dbNameField = new JTextField();
		dbNameField.setColumns(10);
		dbNameField.setBounds(390, 295, 232, 42);
		frame.getContentPane().add(dbNameField);

		JButton connectButton = new JButton("Se Connecter");
		connectButton.setForeground(new Color(0, 0, 0));
		connectButton.setBackground(new Color(175, 238, 238));
		connectButton.setFont(new Font("Arial Black", Font.PLAIN, 13));
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				String username = userField.getText();
				String password = new String(((JPasswordField) passwordField).getPassword());
				String database = dbNameField.getText();

				switch (db) {
				case "Oracle":
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						String url = "jdbc:oracle:thin:@localhost:1521:xe";
						try {
							Connection con = DriverManager.getConnection(url, username, password);
							Menu_global menu = new Menu_global(username,password,db,"");
							properties.setProperty("orcl_user", userField.getText());
			                properties.setProperty("orcl_password", passwordField.getText());
			                
			                try (FileOutputStream output = new FileOutputStream("config.properties")) {
			                    properties.store(output, "Configuration de la connexion");
			                } catch (IOException ex) {
			                    ex.printStackTrace();
			                }
							menu.frame.setVisible(true);
							frame.setVisible(false);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} catch (ClassNotFoundException ex) {
					}
					break;
				case "MySQL":
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database + "",
								username, password);
						Menu_global menu = new Menu_global(username,password,db,database);
						properties.setProperty("mysql_user", userField.getText());
		                properties.setProperty("mysql_password", passwordField.getText());
		                properties.setProperty("mysql_db", dbNameField.getText());
		                
		                try (FileOutputStream output = new FileOutputStream("config.properties")) {
		                    properties.store(output, "Configuration de la connexion");
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
						menu.frame.setVisible(true);

						frame.setVisible(false);
						// String url = "jdbc:mysql://localhost:3306/" +
						// dbName+"?characterEncoding=UTF-8";
						// Connection con = DriverManager.getConnection(url, user, password);
						// System.out.println("success");
					} catch (SQLException ex) {
					} catch (ClassNotFoundException ex) {
					}
					break;
				case "SQL Server":
				}
			}
		});
		connectButton.setBounds(435, 359, 154, 42);
		frame.getContentPane().add(connectButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(390, 146, 209, 2);
		frame.getContentPane().add(separator);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(390, 335, 209, 2);
		frame.getContentPane().add(separator_2);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(390, 200, 232, 42);
		frame.getContentPane().add(passwordField);
		
	

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(390, 240, 209, 2);
		frame.getContentPane().add(separator_1);

		lblNewLabel_3.setBounds(468, 376, 92, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db = (String) comboBox.getSelectedItem();
				
		
				
				if (db=="Oracle")
				{
					
					dbNameField.setVisible(false);
					lblNewLabel_1_1.setVisible(false);
					separator_2.setVisible(false);
					
					
					userField.setText(properties.getProperty("orcl_user"));
					passwordField.setText(properties.getProperty("orcl_password"));
					
				
					
				}else {
					dbNameField.setVisible(true);
					lblNewLabel_1_1.setVisible(true);
					separator_2.setVisible(true);
					userField.setText(properties.getProperty("mysql_user"));
					passwordField.setText(properties.getProperty("mysql_password"));
					dbNameField.setText(properties.getProperty("mysql_db"));
				
				
					
				}
				
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Oracle", "MySQL", "SQL Server" }));
		comboBox.setBounds(390, 24, 232, 30);

		frame.getContentPane().add(comboBox);
		
		
		
		
	}
}