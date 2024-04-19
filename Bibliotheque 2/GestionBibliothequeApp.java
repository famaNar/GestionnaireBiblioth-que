import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionBibliothequeApp {
    private JTextField isbnTextField;
    private JTextField identifiantUtilisateurTextField;

    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    private ArrayList<Livre> listeLivres = new ArrayList<>();
    private Bibliotheque bibliotheque = new Bibliotheque();

    public GestionBibliothequeApp() {
        this.bibliotheque = new Bibliotheque();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionBibliothequeApp().showGUI());
    }

    public void showGUI() {
        JFrame frame = new JFrame("Gestion de bibliothèque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Création de la barre de menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fichierMenu = new JMenu("Menu");
        JMenuItem quitterMenuItem = new JMenuItem("Quitter");
        fichierMenu.add(quitterMenuItem);
        menuBar.add(fichierMenu);
        frame.setJMenuBar(menuBar);

        // Création de la barre d'outils
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(new Color(139, 69, 19)); 
        
        JButton gestionLivresButton = new JButton("Gestion des livres");
        gestionLivresButton.setPreferredSize(new Dimension(200, 50)); 
        JButton gestionEmpruntsButton = new JButton("Gestion des emprunts");
        gestionEmpruntsButton.setPreferredSize(new Dimension(200, 50)); 
        JButton gestionUtilisateursButton = new JButton("Gestion des utilisateurs");
        gestionUtilisateursButton.setPreferredSize(new Dimension(200, 50)); 
        JButton statButton = new JButton("Statitistique");
        statButton.setPreferredSize(new Dimension(200, 50)); 

        // Définir la taille des boutons
        Dimension buttonSize = new Dimension(200, 100); // Largeur : 200 pixels, Hauteur : 50 pixels
        gestionLivresButton.setPreferredSize(buttonSize);
        gestionEmpruntsButton.setPreferredSize(buttonSize);
        gestionUtilisateursButton.setPreferredSize(buttonSize);
        statButton.setPreferredSize(buttonSize);
        // Ajout des actions aux boutons
        gestionLivresButton.addActionListener(e -> gestionLivres());
        gestionEmpruntsButton.addActionListener(e -> gestionEmprunts());
        gestionUtilisateursButton.addActionListener(e -> gestionUtilisateurs());
        statButton.addActionListener(e -> afficherStatistiques());
      
        // Ajout des boutons à la barre d'outils
        toolBar.add(gestionLivresButton);
        toolBar.add(gestionEmpruntsButton);
        toolBar.add(gestionUtilisateursButton);
        toolBar.add(statButton);

        // Ajout de la barre d'outils au nord du conteneur principal
        frame.add(toolBar, BorderLayout.NORTH);

        // Réduction de la taille de l'image
        ImageIcon icon = new ImageIcon("library.png");
        Image img = icon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(imageLabel, BorderLayout.CENTER);

        // Définir la taille préférée, emballer et afficher la fenêtre
        frame.setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Action pour quitter
        quitterMenuItem.addActionListener(e -> quitter());
    }

    private void gestionUtilisateurs() {
        JFrame frame = new JFrame("Gestion des utilisateurs");

        JButton ajouterButton = new JButton("Ajouter un utilisateur");
        ajouterButton.setPreferredSize(new Dimension(250, 50));
        JButton listerUtilisateurButton = new JButton("Lister les utilisateurs");
        listerUtilisateurButton.setPreferredSize(new Dimension(250, 50));
        JButton supprimerButton = new JButton("Supprimer un utilisateur");
        supprimerButton.setPreferredSize(new Dimension(250, 50));


        // Ajoutez des ActionListener aux boutons pour effectuer les actions correspondantes
        ajouterButton.addActionListener(e -> ajouterUtilisateur(frame)); 
        listerUtilisateurButton.addActionListener(e -> listerUtilisateurs()); 
        supprimerButton.addActionListener(e -> supprimerUtilisateur(frame)); 

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(ajouterButton);
        panel.add(listerUtilisateurButton);
        panel.add(supprimerButton);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void listerUtilisateurs() {
        // Créer une chaîne pour stocker les informations des utilisateurs
        StringBuilder utilisateursInfo = new StringBuilder();
        utilisateursInfo.append("Utilisateurs inscrits à la bibliothèque :\n");
        for (Utilisateur utilisateur : utilisateurs) {
            utilisateursInfo.append("- ").append(utilisateur.getNom()).append(" (Identifiant : ").append(utilisateur.getNumeroIdentification()).append(")\n");
        }

        JTextArea utilisateursTextArea = new JTextArea(utilisateursInfo.toString());
        JOptionPane.showMessageDialog(null, utilisateursTextArea, "Liste des utilisateurs", JOptionPane.INFORMATION_MESSAGE);
    }

	private void gestionLivres() {
        JFrame frame = new JFrame("Gestion des livres");

        JButton ajouterButton = new JButton("Ajouter un livre");
        ajouterButton.setPreferredSize(new Dimension(250, 50));
        JButton listerLivresButton = new JButton("Lister les livres");
        listerLivresButton.setPreferredSize(new Dimension(250, 50));
        JButton supprimerButton = new JButton("Supprimer un livre");
        supprimerButton.setPreferredSize(new Dimension(250, 50));
        JButton rechercherButton = new JButton("Rechercher un livre");
        rechercherButton.setPreferredSize(new Dimension(250, 50));

        // Ajoutez des ActionListener aux boutons pour effectuer les actions correspondantes
        ajouterButton.addActionListener(e -> ajouterLivre(frame)); 
        listerLivresButton.addActionListener(e -> listerLivres()); 
        supprimerButton.addActionListener(e -> supprimerLivre(frame)); 
        rechercherButton.addActionListener(e -> rechercherLivre(frame)); 

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(ajouterButton);
        panel.add(listerLivresButton);
        panel.add(supprimerButton);
        panel.add(rechercherButton);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void gestionEmprunts() {
        JFrame frame = new JFrame("Gestion des emprunts");

        isbnTextField = new JTextField(10); // Champ de texte pour l'ISBN
        identifiantUtilisateurTextField = new JTextField(10); // Champ de texte pour l'identifiant de l'utilisateur

        JButton emprunterButton = new JButton("Emprunter un livre");
        emprunterButton.setPreferredSize(new Dimension(250, 50));
        emprunterButton.addActionListener(e -> emprunterLivre(frame));

        JButton retournerButton = new JButton("Retourner un livre");
        retournerButton.setPreferredSize(new Dimension(250, 50));
        retournerButton.addActionListener(e -> retournerLivre());

        JPanel panel = new JPanel(new GridLayout(3, 1)); // Ajout du troisième champ pour les champs de texte
        panel.add(new JLabel("ISBN :"));
        panel.add(isbnTextField);
        panel.add(new JLabel("Identifiant de l'utilisateur :"));
        panel.add(identifiantUtilisateurTextField);

        // Ajout des boutons et des champs de texte au panel
        panel.add(emprunterButton);
        panel.add(retournerButton);
     // Ajouter un ActionListener au bouton "Emprunter un livre"
        emprunterButton.addActionListener(e -> {
            // Récupérer l'ISBN du livre et l'identifiant de l'utilisateur à partir des champs de texte ou d'autres composants Swing
            int isbn = Integer.parseInt(isbnTextField.getText());
            int identifiant = Integer.parseInt(identifiantUtilisateurTextField.getText());
            
            // Appeler la méthode emprunterLivre avec le JFrame actuel, l'ISBN et l'identifiant
            boolean empruntReussi = bibliotheque.emprunterLivre(frame, isbn, identifiant);
            
            // Si l'emprunt a réussi, afficher un message de succès
            if (empruntReussi) {
                JOptionPane.showMessageDialog(frame, "Livre emprunté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void emprunterLivre(JFrame frame) {
        try {
            int isbn = Integer.parseInt(isbnTextField.getText());
            int identifiant = Integer.parseInt(identifiantUtilisateurTextField.getText());

            // Validation des champs de texte pour s'assurer qu'ils contiennent des entiers valides
            if (isbn <= 0 || identifiant <= 0) {
                JOptionPane.showMessageDialog(frame, "Veuillez saisir des valeurs d'ISBN et d'identifiant valides.",
                        "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean empruntReussi = bibliotheque.emprunterLivre(frame, isbn, identifiant);
            if (empruntReussi) {
                JOptionPane.showMessageDialog(frame, "Livre emprunté avec succès.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Erreur lors de l'emprunt du livre.", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez saisir des valeurs d'ISBN et d'identifiant valides.",
                    "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ajouterUtilisateur(JFrame parentFrame) {
        JTextField nomField = new JTextField(20);
        JTextField idField = new JTextField(10);

        // Ajout d'un JComboBox pour permettre à l'utilisateur de choisir le type de livre
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nom :"));
        panel.add(nomField);
        panel.add(new JLabel("Identifiant :"));
        panel.add(idField);
       
        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Ajouter un utilisateur",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nom = nomField.getText();
            int identifiant = Integer.parseInt(idField.getText());

            // Créez un nouvel utilisateur avec les informations fournies
            Utilisateur nouvelUtilisateur = new Utilisateur(nom, identifiant);

            // Ajoutez le nouvel utilisateur à la liste des utilisateurs
            utilisateurs.add(nouvelUtilisateur);

            // Affichez un message de succès
            JOptionPane.showMessageDialog(parentFrame, "Utilisateur ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

            // Affichez une boîte de dialogue pour confirmer le type de livre choisi
        }
    }   

    private void supprimerUtilisateur(JFrame parentFrame) {
        JTextField nomField = new JTextField(20);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nom de l'utilisateur à supprimer :"));
        panel.add(nomField);
        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Supprimer un utilisateur",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String nom = nomField.getText();
            // Recherchez l'utilisateur dans votre liste d'utilisateurs
            Utilisateur utilisateurASupprimer = null;
            for (Utilisateur utilisateur : utilisateurs) {
                if (utilisateur.getNom().equalsIgnoreCase(nom)) {
                    utilisateurASupprimer = utilisateur;
                    break;
                }
            }
            if (utilisateurASupprimer != null) {
                // Supprimez l'utilisateur de la liste d'utilisateurs
                utilisateurs.remove(utilisateurASupprimer);
                JOptionPane.showMessageDialog(parentFrame, "Utilisateur supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Utilisateur non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void listerLivres() {
        // Créez un modèle de table
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Titre");
        model.addColumn("Auteur");
        model.addColumn("Emprunté");

        for (Livre livre : listeLivres) {
            String etatEmprunt = livre.estEmprunte() ? "Oui" : "Non";
            model.addRow(new Object[]{livre.getTitre(), livre.getAuteur(), etatEmprunt, livre.getType()});
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

         JFrame frame = new JFrame("Liste des livres");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    private void supprimerLivre(JFrame frame) {
        JTextField titreField = new JTextField(20);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Titre du livre à supprimer :"));
        panel.add(titreField);
        int result = JOptionPane.showConfirmDialog(frame, panel, "Supprimer un livre",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String titre = titreField.getText();
            // Recherchez le livre dans votre liste de livres
            Livre livreASupprimer = null;
            for (Livre livre : listeLivres) {
                if (livre.getTitre().equalsIgnoreCase(titre)) {
                    livreASupprimer = livre;
                    break;
                }
            }
            if (livreASupprimer != null) {
                // Supprimez le livre de la liste des livres
                listeLivres.remove(livreASupprimer);
                // Supprimez le livre de la bibliothèque
                bibliotheque.supprimerLivre(livreASupprimer);
                JOptionPane.showMessageDialog(frame, "Livre supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Livre non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void ajouterLivre(JFrame parentFrame) {
        // Boîte de dialogue pour saisir les informations sur le livre
        JTextField titreField = new JTextField(20);
        JTextField auteurField = new JTextField(20);
        JTextField anneeField = new JTextField(5);
        JTextField isbnField = new JTextField(10);
        JComboBox<String> typeLivreComboBox = new JComboBox<>(new String[]{"Roman", "Essai", "LivreAudio"});

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ISBN :"));
        panel.add(isbnField);
        panel.add(new JLabel("Titre :"));
        panel.add(titreField);
        panel.add(new JLabel("Auteur :"));
        panel.add(auteurField);
        panel.add(new JLabel("Année de publication :"));
        panel.add(anneeField);
        panel.add(new JLabel("Type de livre :"));
        panel.add(typeLivreComboBox);

        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Ajouter un livre",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Récupérer les valeurs saisies par l'utilisateur
                String titre = titreField.getText();
                String auteur = auteurField.getText();
                int annee = Integer.parseInt(anneeField.getText());
                int isbn = Integer.parseInt(isbnField.getText());
                String typeLivreChoisi = (String) typeLivreComboBox.getSelectedItem();

                // Créer une instance de la classe de livre spécifique en fonction du type choisi
                Livre livre;
                switch (typeLivreChoisi) {
                    case "Roman":
                        livre = new Livre(titre, auteur, annee, isbn);
                        break;
                    case "Essai":
                        livre = new Livre(titre, auteur, annee, isbn);
                        break;
                    case "LivreAudio":
                        livre = new Livre(titre, auteur, annee, isbn);
                        break;
                    default:
                        throw new IllegalArgumentException("Type de livre non reconnu : " + typeLivreChoisi);
                }

                // Ajouter le livre à la bibliothèque
                bibliotheque.ajouterLivre(livre);
                listeLivres.add(livre); // Ajouter le livre à la liste des livres

                JOptionPane.showMessageDialog(parentFrame, "Livre ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(parentFrame, "Vous avez choisi le type de livre : " + typeLivreChoisi, "Type de livre choisi", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parentFrame, "Veuillez saisir une année valide et un ISBN valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(parentFrame, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


 private void rechercherLivre(JFrame frame) {
        String titreRecherche = JOptionPane.showInputDialog("Entrez le titre du livre à rechercher :");

        // Vérifiez si le titre recherché est vide ou null
        if (titreRecherche != null && !titreRecherche.isEmpty()) {
            boolean livreTrouve = false;
            for (Livre livre : listeLivres) {
                if (livre.getTitre() != null && livre.getTitre().equalsIgnoreCase(titreRecherche)) {
                    // Livre trouvé, affichez les détails du livre
                    JOptionPane.showMessageDialog(null, "Livre trouvé : " + livre);
                    livreTrouve = true;
                    break;
                }
            }
            if (!livreTrouve) {
                // Aucun livre trouvé avec ce titre
                JOptionPane.showMessageDialog(null, "Aucun livre trouvé avec le titre : " + titreRecherche);
            }
        } else {
            // Le titre recherché est vide ou null
            JOptionPane.showMessageDialog(null, "Titre invalide.");
        }
    }

    private void retournerLivre() {
        // Boîte de dialogue pour saisir les informations sur le retour
        JTextField utilisateurField = new JTextField(20);
        JTextField livreField = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nom de l'utilisateur :"));
        panel.add(utilisateurField);
        panel.add(new JLabel("Titre du livre à retourner :"));
        panel.add(livreField);

        int result = JOptionPane.showConfirmDialog(panel, panel, "Retourner un livre",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Récupérer les valeurs saisies par l'utilisateur
        	int isbn = Integer.parseInt(isbnTextField.getText());
            int identifiant = Integer.parseInt(identifiantUtilisateurTextField.getText());

            // Récupérer l'utilisateur et le livre correspondants dans la bibliothèque
            Utilisateur utilisateur = bibliotheque.rechercherUtilisateurParIdentifiant(identifiant);
            Livre livre = bibliotheque.rechercherLivreParISBN(isbn);

            if (utilisateur != null && livre != null) {
                // Retourner le livre
                bibliotheque.retournerLivre(utilisateur, livre);
                JOptionPane.showMessageDialog(panel, "Livre retourné avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Utilisateur ou livre introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void afficherStatistiques() {
        // Nombre total de livres dans la bibliothèque
        int totalLivres = listeLivres.size();
        // Nombre total d'utilisateurs inscrits à la bibliothèque
        int totalUtilisateurs = utilisateurs.size();

        // Construction de la chaîne de caractères pour les statistiques
        StringBuilder statistiques = new StringBuilder();
        statistiques.append("Livres dans la bibliothèque :\n");
        for (Livre livre : listeLivres) {
            statistiques.append("- ").append(livre.getTitre()).append(" par ").append(livre.getAuteur()).append("\n");
        }

        statistiques.append("\nUtilisateurs inscrits à la bibliothèque :\n");
        for (Utilisateur utilisateur : utilisateurs) {
            statistiques.append("- ").append(utilisateur.getNom()).append(" (Identifiant : ").append(utilisateur.getNumeroIdentification()).append(")\n");
        }

        statistiques.append("\nNombre total de livres dans la bibliothèque : ").append(totalLivres).append("\n");
        statistiques.append("Nombre total d'utilisateurs inscrits à la bibliothèque : ").append(totalUtilisateurs);

        // Mettre à jour le composant graphique avec les statistiques
        JTextArea statistiquesTextArea = new JTextArea(statistiques.toString());
        JOptionPane.showMessageDialog(null, statistiquesTextArea, "Statistiques de la bibliothèque", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void quitter() {
        int response = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
