import java.util.HashMap;
import java.util.Map;


	public class Utilisateur {
	    private String nom;
	    private int numeroIdentification;
	    private static boolean cotisationAJour; // Ajout de l'état de cotisation
	    private Map<Livre, Integer> livresEmpruntes;

	    public Utilisateur(String nom, int numeroIdentification) {
	        this.nom = nom;
	        this.numeroIdentification = numeroIdentification;
	        this.cotisationAJour = true; // Par défaut, la cotisation est à jour
	        this.livresEmpruntes = new HashMap<>();}
	       
	       
	     public String getNom() {
			return nom;
		}


		public void setNom(String nom) {
			this.nom = nom;
		}


		public int getNumeroIdentification() {
			return numeroIdentification;
		}


		public void setNumeroIdentification(int numeroIdentification) {
			this.numeroIdentification = numeroIdentification;
		}


		public Map<Livre, Integer> getLivresEmpruntes() {
			return livresEmpruntes;
		}


		public void setLivresEmpruntes(Map<Livre, Integer> livresEmpruntes) {
			this.livresEmpruntes = livresEmpruntes;
		}


			// Méthode pour emprunter un livre
	        public void emprunterLivre(Livre livre) {
	            // Vérifier si l'utilisateur a ses cotisations à jour
	            if (!cotisationAJour) {
	                System.out.println("Votre cotisation n'est pas à jour. Vous ne pouvez pas emprunter de livre.");
	                return;
	            }

	            // Vérifier si l'utilisateur a déjà emprunté le livre plus de deux fois
	            if (livresEmpruntes.containsKey(livre) && livresEmpruntes.get(livre) >= 2) {
	                System.out.println("Vous avez déjà emprunté ce livre plus de deux fois.");
	                return;
	            }

	            // Mettre à jour le nombre de fois que l'utilisateur emprunte ce livre
	            livresEmpruntes.put(livre, livresEmpruntes.getOrDefault(livre, 0) + 1);
	        }
	        public boolean aEmprunteLivre(Livre livre) {
	            return livresEmpruntes.containsKey(livre);
	        }

	        // Méthode pour retourner un livre
	        public void retournerLivre(Livre livre) {
	            livresEmpruntes.remove(livre);
	        }

	        // Méthodes getters et setters

	        public static boolean isCotisationAJour() {
	            return cotisationAJour;
	        }

	        public void setCotisationAJour(boolean cotisationAJour) {
	            this.cotisationAJour = cotisationAJour;
	        }
	        @Override
	        public String toString() {
	    		return "Utilisateur [nom=" + nom + ", identifiant=" + numeroIdentification + "]";
	        }
	    }
	