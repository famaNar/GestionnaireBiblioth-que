public class Livre {
    private String titre;
    private String auteur;
    private int anneePublication;
    private int isbn;
    private boolean emprunte;
	private String type;

    public boolean isEmprunte() {
		return emprunte;
	}

	public void setEmprunte(boolean emprunte) {
		this.emprunte = emprunte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


    public Livre(String titre, String auteur, int anneePublication, int isbn) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.isbn = isbn;
        this.emprunte = false; 
    }

    // Méthodes getters et setters

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public boolean estEmprunte() {
        return emprunte;
    }

    public void emprunter() {
        this.emprunte = true;
    }

    public void retourner() {
        this.emprunte = false;
    }

    @Override
    public String toString() {
        return "Livre [titre=" + titre + ", auteur=" + auteur + ", anneePublication=" + anneePublication + ", isbn="
                + isbn + ", emprunte=" + emprunte + "]";
    }
}
