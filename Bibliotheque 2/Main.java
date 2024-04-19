import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Création d'une instance de l'application de gestion de bibliothèque
                GestionBibliothequeApp gestionBibliothequeApp = new GestionBibliothequeApp();

                // Affichage de l'interface utilisateur de l'application
                gestionBibliothequeApp.showGUI();
            }
        });
    }
}
