package pfe;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Connexion extends JFrame {

    private final JTextField utilisateurField = new JTextField(20);
    private final JTextField motDePasseField = new JTextField(20);

    private final Properties properties = new Properties();

    public Connexion() {
        super("Connexion");

        // Chargement des propriétés
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new FlowLayout());

        add(new JLabel("Utilisateur :"));
        add(utilisateurField);

        add(new JLabel("Mot de passe :"));
        add(motDePasseField);

        // Affectation des valeurs des champs de texte
        utilisateurField.setText(properties.getProperty("utilisateur"));
        motDePasseField.setText(properties.getProperty("motDePasse"));

        // Enregistrement des propriétés à la fermeture de la fenêtre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                properties.setProperty("utilisateur", utilisateurField.getText());
                properties.setProperty("motDePasse", motDePasseField.getText());

                try (FileOutputStream output = new FileOutputStream("config.properties")) {
                    properties.store(output, "Configuration de la connexion");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Connexion();
    }
}