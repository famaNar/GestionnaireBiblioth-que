import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Bibliotheque {
	private List<Livre> listeLivres;
	private List<Utilisateur> listeUtilisateurs;
	private Map<Utilisateur, List<Livre>> empruntsUtilisateurs;

	public Bibliotheque() {
		this.listeLivres = new ArrayList<>();
		this.listeUtilisateurs = new ArrayList<>();
		this.empruntsUtilisateurs = new HashMap<>();
	}

	public List<Livre> getListeLivres(String titreLivre) {
		return listeLivres;
	}

	public List<Utilisateur> getListeUtilisateur(String nomUtilisateur) {
		return listeUtilisateurs;
	}

	public void ajouterLivre(Livre livre) {
		listeLivres.add(livre);
	}

	public void supprimerLivre(Livre livre) {
		listeLivres.remove(livre);
	}

	public Livre rechercherLivre(String recherche) {
		for (Livre livre : listeLivres) {
			if (livre.getTitre().equalsIgnoreCase(recherche) || livre.getAuteur().equalsIgnoreCase(recherche)) {
				return livre;
			}
		}
		return null;
	}

	public boolean retournerLivre(Utilisateur utilisateur, Livre livre) {
		// Vérifier si l'utilisateur et le livre existent dans la bibliothèque
		if (!listeUtilisateurs.contains(utilisateur) || !listeLivres.contains(livre)) {
			return false;
		}

		// Marquer le livre comme retourné
		livre.retourner();

		// Retirer le livre de la liste des emprunts de l'utilisateur
		List<Livre> livresEmpruntes = empruntsUtilisateurs.get(utilisateur);
		if (livresEmpruntes != null) {
			livresEmpruntes.remove(livre);
		}

		return true;
	}

	public Livre rechercherLivreParISBN(int isbn) {
		for (Livre livre : listeLivres) {
			if (livre.getIsbn() == isbn) {
				return livre;
			}
		}
		return null;
	}

	public Utilisateur rechercherUtilisateurParIdentifiant(int nomUtilisateur) {
		for (Utilisateur utilisateur : listeUtilisateurs) {
			if (utilisateur.getNumeroIdentification() == nomUtilisateur) {
				return utilisateur;
			}
		}
		return null;
	}

	public boolean emprunterLivre(JFrame frame, int isbn, int identifiant) {
		// Recherche du livre par son ISBN
		Livre livre = rechercherLivreParISBN(isbn);
		// Recherche de l'utilisateur par son identifiant
		Utilisateur utilisateur = rechercherUtilisateurParIdentifiant(identifiant);

		// Vérification de l'existence du livre et de l'utilisateur
		if (livre != null && utilisateur != null) {
			// Vérification de la cotisation de l'utilisateur
			if (utilisateur.isCotisationAJour()) {
				// Tentative d'emprunt du livre par l'utilisateur
				boolean empruntReussi = emprunterLivre(utilisateur, livre);
				if (empruntReussi) {
					// L'emprunt a réussi, donc on retourne true
					return true;
				} else {
					// Gestion de l'échec de l'emprunt (par exemple, le livre est déjà emprunté)
					JOptionPane.showMessageDialog(frame, "Le livre ne peut pas être emprunté pour le moment.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// L'utilisateur n'est pas à jour avec ses cotisations
				JOptionPane.showMessageDialog(frame, "L'utilisateur n'est pas à jour avec ses cotisations.", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// Livre ou utilisateur introuvable
			JOptionPane.showMessageDialog(frame, "Livre ou utilisateur introuvable.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
		// Si on arrive ici, l'emprunt a échoué, donc on retourne false
		return false;
	}

	private boolean emprunterLivre(Utilisateur utilisateur, Livre livre) {
		// Vérifier si l'utilisateur peut emprunter le livre en fonction de ses
		// cotisations
		if (utilisateur.isCotisationAJour()) {
			// Assurez-vous que le livre n'est pas déjà emprunté
			if (!livre.estEmprunte()) {
				// Vérifier si l'utilisateur n'a pas déjà emprunté le nombre maximal de livres
				List<Livre> emprunts = empruntsUtilisateurs.getOrDefault(utilisateur, new ArrayList<>());
				if (emprunts.size() < 2) {
					// Ajouter le livre à la liste des emprunts de l'utilisateur
					emprunts.add(livre);
					empruntsUtilisateurs.put(utilisateur, emprunts);

					// Marquer le livre comme emprunté
					livre.emprunter();

					// L'emprunt a réussi, donc on retourne true
					return true;
				} else {
					// L'utilisateur a déjà emprunté le nombre maximal de livres
					return false;
				}
			} else {
				// Le livre est déjà emprunté
				return false;
			}
		} else {
			// L'utilisateur n'est pas à jour avec ses cotisations
			return false;
		}
	}

	public void afficherStatistiques() {
		// Nombre total de livres dans la bibliothèque
		int totalLivres = listeLivres.size();
		// Nombre total d'utilisateurs inscrits à la bibliothèque
		int totalUtilisateurs = listeUtilisateurs.size();

		// Affichage des livres de la bibliothèque
		System.out.println("Livres dans la bibliothèque :");
		for (Livre livre : listeLivres) {
			System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
		}

		// Affichage des utilisateurs inscrits à la bibliothèque
		System.out.println("\nUtilisateurs inscrits à la bibliothèque :");
		for (Utilisateur utilisateur : listeUtilisateurs) {
			System.out.println(
					"- " + utilisateur.getNom() + " (Identifiant : " + utilisateur.getNumeroIdentification() + ")");
		}

		// Affichage du nombre total de livres et d'utilisateurs dans la bibliothèque
		System.out.println("\nNombre total de livres dans la bibliothèque : " + totalLivres);
		System.out.println("Nombre total d'utilisateurs inscrits à la bibliothèque : " + totalUtilisateurs);
	}

	public Component getEmpruntsUtilisateurs() {
		// TODO Auto-generated method stub
		return null;
	}

}
