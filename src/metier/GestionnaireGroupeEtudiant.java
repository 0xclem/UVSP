package metier;

import persistance.AbstractDAOFactory;
import persistance.DAO;
import java.util.ArrayList;

/**
 * Classe gestionnaire pour la gestion des Groupe
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class GestionnaireGroupeEtudiant {

    /**
     * DAO des groupes
     */
    DAO<Groupe> groupeDao;

    /**
     * Liste des groupes
     */
    private ArrayList<Groupe> listeGroupes;

    /**
     * Instance unique de la classe GestionnaireGroupe -> singleton
     */
    private static GestionnaireGroupeEtudiant gesGroupe;

    /**
     * Constructeur prive pour une classe singleton
     */
    private GestionnaireGroupeEtudiant() {
    	groupeDao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY)
                                      .getGroupeDAO();
        listeGroupes = groupeDao.getListe();
    }

    /**
     * Methode qui retourne l'instance du gestionnaire si elle existe,
     * Sinon utilise le constructeur pour la creer
     * @return l'instance du gestionaire
     */
    public static GestionnaireGroupeEtudiant getInstance() {
        if (gesGroupe == null)
                gesGroupe = new GestionnaireGroupeEtudiant();
        return (gesGroupe);
    }
    
    /**
     * Recuperation de la liste des groupes
     * @return un ArrayList<Groupe> qui contient les groupes
     */
    public ArrayList<Groupe> getListeGroupes() {
        return listeGroupes;
    }

    /**
     * M�thode qui permet l'ajout d'un groupe
     * @param lib Libell� du groupe
     * @param pere Pere du groupe
     */
    public void addGroupe(String lib, Groupe pere) {
        Groupe groupe = new Groupe(lib, pere);
        Boolean ok= groupeDao.create(groupe);
        if ( ok )
            listeGroupes.add(groupe);
    }
    
    /**
     * M�thode qui permet la modification d'un groupe
     * @param lib Libell� du groupe
     * @param pere Pere du groupe
     */
    public void updateEnseignant(Groupe groupe, String lib, Groupe pere) {
        listeGroupes.remove(groupe);
        groupe.setLibelle(lib);
        groupe.setPere(pere);

        Boolean ok= groupeDao.update(groupe);
        if ( ok )
            listeGroupes.add(groupe);
    }
    
    /**
     * M�thode qui permet de supprimer un groupe
     * @param groupe Groupe � supprimer
     */
    public void deleteGroupe(Groupe groupe) {
        Boolean ok= groupeDao.delete(groupe);
        if ( ok )
            listeGroupes.remove(groupe);
    }
}
