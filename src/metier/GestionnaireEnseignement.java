package metier;

import persistance.AbstractDAOFactory;
import persistance.DAO;
import java.util.ArrayList;

/**
 * Classe servant de fa�ade entre le package "vue" et les classe m�tiers du package "persistance".
 * Cette classe contient une liste des enseignements pr�sentes dans la base de donn�es,
 * et elle impl�mente le design pattern Singleton afin de n'�tre instanci�e qu'une seule fois.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class GestionnaireEnseignement {

    //Attributs
    DAO<Enseignement> enseignementDAO;
    private ArrayList<Enseignement> listeEnseignements;
    private static GestionnaireEnseignement ges_Enseignement;

    /**
     * Constructeur d'un objet GestionnaireEnseignement.
     * Son acc�s est priv� afin de contr�ler l'instanciation d'un objet unique (application du design pattern Singleton).
     */
    private GestionnaireEnseignement() {
        enseignementDAO = AbstractDAOFactory.getFactory(
        AbstractDAOFactory.DAO_FACTORY).getEnseignementDAO();
        listeEnseignements = enseignementDAO.getListe();
    }

    /** M�thode statique permettant de retourner le gestionnaire
     *  "GestionnaireEnseignement". Si il n'est pas encore cr�e, la m�thode
     *  l'instancie.
     *  @return Retourne le gestionnaire "GestionnaireEnseignement"
     */
    public static GestionnaireEnseignement getInstance() {
        if (ges_Enseignement == null) {
            ges_Enseignement = new GestionnaireEnseignement();
        }
        return ges_Enseignement;
    }

    /** M�thode permettant de supprimer un enseignement. Si la
     *  suppression est r�alis�e sans probl�me, elle met � jour la liste des
     *  enseignements par la m�me occasion.
     *  @param ens L'enseignement � supprimer.
     *  @return Renvoie VRAI si la suppression s'est r�alis�e. FAUX sinon.
     */
    public boolean deleteEnseignement(Enseignement ens) {
        Boolean ok;
        ok = enseignementDAO.delete(ens);
        if (ok) {
            listeEnseignements.remove(ens);
        }
        return ok;
    }

    /** M�thode permettant d'obtenir la liste des enseignements.
     *  @return Renvoie une liste contenant les enseignements.
     */
    public ArrayList<Enseignement> getListeEnseignements() {
        return this.listeEnseignements;
    }

    /** M�thode permettant d'ajouter un nouvel enseignement.
     * @param typeEns Le type de l'enseignement.
     * @param mat La code de la mati�re de l'enseignement.
     * @param tGpe Le type de groupe de l'enseignement.
     * @param nbHeure Le nombre d'heures r�el de l'enseignement.
     * @return Renvoie VRAI si l'ajout a r�ussi. FAUX sinon.
     */
    public boolean addEnseignement(Matiere mat,Cours cours, Groupe groupe, Enseignant enseignant, double nbHeure) {

        Boolean ok;
        Enseignement ens = new Enseignement(groupe, nbHeure, enseignant, cours);
        ok = enseignementDAO.create(ens);
        if (ok) {
            listeEnseignements.add(ens);
        }
        return ok;
    }

    /** M�thode permettant de modifier un enseignement.
     * @param typeEns Le type d'enseignement modifi�.
     * @param mat La mati�re maodifi�e.
     * @param tGpe Le type de groupe modifi�.
     * @param nbHeure Le nombre d'heures r�el modifi�, de l'enseignement.
     * @param ancien L'enseignement � modifier.
     * @return Renvoie VRAI si la modification a r�ussi. FAUX sinon.
     */
    public void updateEnseignement(Enseignement ens, Cours cours, Groupe groupe, Enseignant enseignant, double nbHeure) {
        listeEnseignements.remove(ens);
        ens.setCours(cours);
        ens.setGroupe(groupe);
        ens.setEnseignant(enseignant);
        ens.setNbHeureReel(nbHeure);

        Boolean ok= enseignementDAO.update(ens);
        if ( ok )
            listeEnseignements.add(ens);
    }
}
