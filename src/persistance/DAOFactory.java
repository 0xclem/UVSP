package persistance;
import metier.*;

/**
 * Classe permettant de mod�liser une fabrique concr�te de DAOs.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class DAOFactory extends AbstractDAOFactory {
    
    private static final DAOFactory instance = new DAOFactory();
    
    /**
     * M�thode permettant de retourner l'instance unique de type DAOFactory
     * @return DAOFactory - Instance unique de l'objet DAOFactory
     */
    public final static DAOFactory getDAOFactory () {
        return instance;
    }

    /**
     * M�thode instanciatrice d'un objet DAO pour les mati�res.
     * Cette m�thode utilise le design pattern singleton : l'objet MatiereDAO est instanci� une seule fois
     * @return MatiereDAO - Un nouvel objet MatiereDAO s'il n'a pas d�j� �t� instanci�, l'objet existant sinon
     */
    public DAO<Matiere> getMatiereDAO(){
        return MatiereDAO.getInstance();
    }

    /**
     * M�thode instanciatrice d'un objet DAO pour les batiments.
     * Cette m�thode utilise le design pattern singleton : l'objet BatimentDAO est instanci� une seule fois
     * @return BatimentDAO - Un nouvel objet BatimentDAO s'il n'a pas d�j� �t� instanci�, l'objet existant sinon
     */
    public DAO<Batiment> getBatimentDAO(){
        return BatimentDAO.getInstance();
    }

    /**
     * M�thode instanciatrice d'un objet DAO pour les enseignants.
     * Cette m�thode utilise le design pattern singleton : l'objet EnseignantDAO est instanci� une seule fois
     * @return EnseignantDAO - Un nouvel objet EnseignantDAO s'il n'a pas d�j� �t� instanci�, l'objet existant sinon
     */
    public DAO<Enseignant> getEnseignantDAO(){
        return EnseignantDAO.getInstance();
    }

    /**
     * M�thode instanciatrice d'un objet DAO pour les caract�ristiques.
     * Cette m�thode utilise le design pattern singleton : l'objet CaracteristiqueDAO est instanci� une seule fois
     * @return CaracteristiqueDAO - Un nouvel objet CaracteristiqueDAO s'il n'a pas d�j� �t� instanci�, l'objet existant sinon
     */
    public DAO<Caracteristique> getCaracteristiqueDAO(){
        return CaracteristiqueDAO.getInstance();
    }

    /**
     * Méthode instanciatrice d'un objet DAO pour les UEs.
     * Cette m�thode utilise le design pattern singleton : l'objet UEDAO est instanci� une seule fois
     * @return UEDAO - Un nouvel objet UEDAO s'il n'a pas d�j� �t� instanci�, l'objet existant sinon
     */
    public DAO<UE> getUEDAO() {
        return UEDAO.getInstance();
    }

    /**
     * M�thode instanciatrice d'un objet DAO pour les r�servations.
     * Cette m�thode utilise le design pattern singleton : l'objet ReservationDAO est instanci� une seule fois
     * @return ReservationDAO - Un nouvel objet ReservationDAO s'il n'a pas d�j� �t� instanci�, l'objet existant sinon
     */
    public DAO<Reservation> getReservationDAO() {
        return ReservationDAO.getInstance();
    }

    /**
     * M�thode instanciatrice d'un objet DAO pour les cours.
     * Cette m�thode utilise le design pattern singleton : l'objet CoursDAO est instanci� une seule fois
     * @return CoursDAO - Un nouvel objet CoursDAO s'il n'a pas d�j� �t� instanci�, l'objet existant sinon
     */
    public DAO<Cours> getCoursDAO() {
        return CoursDAO.getInstance();
    }
    
    /**
     * M�thode instanciatrice d'un objet DAO pour les types de cours.
     * Cette m�thode utilise le design pattern singleton : l'objet TypeCoursDAO est instanci� une seule fois
     * @return TypeCoursDAO - Un nouvel objet TypeCoursDAO s'il n'a pas d�j� �t� instanci�, l'objet existant sinon
     */
    public DAO<TypeCours> getTypeCoursDAO() {
        return TypeCoursDAO.getInstance();
    }

    /**
     * M�thode instanciatrice d'un objet DAO pour les cr�neaux
     * Cette m�thode utilise le design pattern singleton : l'objet CreneauDAO est instanci� une seule fois
     * @return CreneauDAO - Un nouvel objet CreneauDAO s'il n'a pas d�j� �t� instanci�, l'objet existant sinon
     */
    public DAO<Creneau> getCreneauDAO() {
    	return CreneauDAO.getInstance();
    }

    /**
     * M�thode instanciatrice d'un objet DAO pour les Groupes
     * Cette méthode utilise le design pattern singleton : l'objet GroupeDAO est instancié une seule fois
     * @author Fanny Couturier
     * @return SEEDAO - Un nouvel objet GroupeDAO s'il n'a pas déjà été instancié, l'objet existant sinon
     * @version 1.0
     */
    public DAO<Groupe> getGroupeDAO() {
    	return GroupeDAO.getInstance();
    }

     /**
     * Méthode instanciatrice d'un objet DAO pour les salles.
     * Cette méthode utilise le design pattern singleton : l'objet SalleDAO est instancié une seule fois
     * @author Maxime Valière
     * @return SalleDAO - Un nouvel objet SalleDAO s'il n'a pas déjà été instancié, l'objet existant sinon
     * @version 1.0
     */
     public DAO<Salle> getSalleDAO() {
        return SalleDAO.getInstance();
     }
     
     /**
     * Méthode instanciatrice d'un objet DAO pour les enseignements.
     * Cette méthode utilise le design pattern singleton : l'objet EnseignementDAO est instancié une seule fois
     * @author Boris Kuete
     * @return EnseignementDAO - Un nouvel objet EnseignementDAO s'il n'a pas déjà été instancié, l'objet existant sinon
     * @version 1.0
     */
     public DAO<Enseignement> getEnseignementDAO() {
    	 return EnseignementDAO.getInstance();
     }
}