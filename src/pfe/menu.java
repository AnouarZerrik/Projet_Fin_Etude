package pfe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class menu extends JFrame {
    private JMenuBar menuBar;
    private JMenu dataInMenu, dataOutMenu, cheminMenu, tabBordMenu, configMenu;
    private JMenuItem excelItem, csvItem, siteItem, tousItem, serveurItem, lierItem;

    public menu() {
        setTitle("Ma fenêtre");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définition des couleurs personnalisées
        Color bgColor = new Color(25, 25, 112); // Bleu foncé
        Color fgColor = new Color(255, 100, 100); // Blanc

        // Création de la barre de menus
        menuBar = new JMenuBar();

        // Modification de l'apparence des menus
        UIManager.put("Menu.background", bgColor);
        UIManager.put("Menu.foreground", fgColor);
        UIManager.put("MenuItem.background", bgColor);
        UIManager.put("MenuItem.foreground", fgColor);

        // Ajout des menus à la barre de menus
        dataInMenu = new JMenu("Data-in");
        dataOutMenu = new JMenu("Data-out");
        cheminMenu = new JMenu("Chemin");
        tabBordMenu = new JMenu("Tab_bord");
        configMenu = new JMenu("Config");
        menuBar.add(dataInMenu);
        menuBar.add(dataOutMenu);
        menuBar.add(cheminMenu);
        menuBar.add(tabBordMenu);
        menuBar.add(configMenu);

        // Ajout des éléments de menu à chaque menu
        excelItem = new JMenuItem("Excel");
        csvItem = new JMenuItem("CSV");
        dataInMenu.add(excelItem);
        dataInMenu.add(csvItem);

        excelItem = new JMenuItem("Excel");
        csvItem = new JMenuItem("CSV");
        dataOutMenu.add(excelItem);
        dataOutMenu.add(csvItem);

        siteItem = new JMenuItem("Pour un site");
        tousItem = new JMenuItem("Pour tous");
        cheminMenu.add(siteItem);
        cheminMenu.add(tousItem);

        tabBordMenu.add(new JMenuItem("Tab_bord"));

        serveurItem = new JMenuItem("Serveur");
        lierItem = new JMenuItem("Lier");
        configMenu.add(serveurItem);
        configMenu.add(lierItem);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        menu fenetre = new menu();
        fenetre.setVisible(true);
    }
}
