package pfe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
	 */
	public connexion_interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.disabledShadow"));
		frame.setBounds(100, 100, 682, 467);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom d'utilisateur :");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel.setBounds(390, 65, 195, 30);
		frame.getContentPane().add(lblNewLabel);

		userField = new JTextField();
		userField.setBounds(390, 106, 232, 42);
		frame.getContentPane().add(userField);
		userField.setColumns(10);

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

					
				}else {
					dbNameField.setVisible(true);
					lblNewLabel_1_1.setVisible(true);
					separator_2.setVisible(true);
				}
				
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Oracle", "MySQL", "SQL Server" }));
		comboBox.setBounds(390, 24, 232, 30);

		frame.getContentPane().add(comboBox);
	}
}