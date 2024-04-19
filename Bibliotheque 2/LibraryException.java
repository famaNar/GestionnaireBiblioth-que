public class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }

    // Exemple d'exception pour un livre non trouvé
    public static class LivreNonTrouveException extends LibraryException {
        public LivreNonTrouveException(String ISBN) {
            super("Livre non trouvé avec l'ISBN : " + ISBN);
        }
    }

    // Exemple d'exception pour un utilisateur non trouvé
    public static class UtilisateurNonTrouveException extends LibraryException {
        public UtilisateurNonTrouveException(int numeroIdentification) {
            super("Utilisateur non trouvé avec le numéro d'identification : " + numeroIdentification);
        }
    }

    // Exemple d'exception pour un emprunt non autorisé
    public static class EmpruntNonAutoriseException extends LibraryException {
        public EmpruntNonAutoriseException() {
            super("Emprunt non autorisé !");
        }
    }
    
}

