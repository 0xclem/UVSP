package metier;

import persistance.*;
import java.util.ArrayList;

/**
 * Classe servant de façade entre le package "vue" et les classe m�tiers du package "persistance".
 * Cette classe contient une liste des types de groupe d'�tudiants pr�sente dans la base de donn�es,
 * et elle impl�mente le design pattern Singleton afin de n'�tre instanci�e qu'une seule fois.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class GestionnaireGroupeEtudiant {

    //Attributs
    DAO<TypeGroupe> typeGroupeDAO;
    private ArrayList<TypeGroupe> listeTypeGroupe;
    private static GestionnaireGroupeEtudiant gesGpeEtudiant;


    /**
     * Constructeur d'un objet GestionnaireGroupeEtudiant.
     * Son acc�s est priv� afin de contr�ler l'instanciation d'un objet unique (application du design pattern Singleton).
     */
    private GestionnaireGroupeEtudiant() {
        typeGroupeDAO = AbstractDAOFactory.getFactory(
                AbstractDAOFactory.DAO_FACTORY).getTypeGroupeDAO();
        listeTypeGroupe = typeGroupeDAO.getListe();
    }

    /** M�thode statique permettant de retourner le gestionnaire
     *  "GestionnaireGpeEtudiant". Si il n'est pas encore cr�e, la m�thode
     *  l'instancie.
     *  @return Retourne le gestionnaire "GestionnaireGpeEtudiant"
     */
    public static GestionnaireGroupeEtudiant getGestionnaireGpeEtudiant() {
        if (gesGpeEtudiant == null) {
            gesGpeEtudiant = new GestionnaireGroupeEtudiant();
        }
        return gesGpeEtudiant;
    }


    /** M�thode permettant de supprimer un type de groupe. Si la
     *  suppression est r�alis�e sans probl�me, elle met � jour la liste des
     *  types de groupe par la m�me occasion.
     *  @param typeGpe Le type de groupe � supprimer.
     *  @return Renvoie VRAI si la suppression s'est r�alis�e. FAUX sinon.
     */
    public boolean deleteTypeGroupe(TypeGroupe typeGpe) {
        Boolean ok;
        ok = typeGroupeDAO.delete(typeGpe);
        if (ok) {
            listeTypeGroupe.remove(typeGpe);
        }
        return ok;
    }

    /** M�thode permettant d'obtenir la liste des types de groupe.
     *  @return Renvoie une liste contenant les types de groupe.
     */
    public ArrayList<TypeGroupe> getListeTypeGroupe() {
        return this.listeTypeGroupe;
    }

    /** M�thode permettant d'ajouter un nouveau type de groupe.
     * @param libelle Nom du nouveau type de groupe.
     * @return Renvoie VRAI si l'ajout a r�ussi. FAUX sinon.
     */
    public boolean ajouterTypeEnseignement(String libelle) {
        Boolean ok;
        TypeGroupe typeGpe = new TypeGroupe(libelle);
        ok = typeGroupeDAO.create(typeGpe);
        if (ok) {
            listeTypeGroupe.add(typeGpe);
        }
        return ok;
    }

    /** M�thode permmettant de modifier un type de groupe.
     * @param libelle Le libell� modifi� du type.
     * @param typeGpe Le type de groupe concern� par la modification.
     * @return Renvoie VRAI si la modification a r�ussi. FAUX sinon.
     */
    public boolean modifierTypeGroupe(String libelle, TypeGroupe ancien) {
        Boolean ok;
        int index;
        TypeGroupe nouveau = new TypeGroupe(libelle);
        ok = typeGroupeDAO.update(ancien, nouveau);
        if (ok) {
            index = listeTypeGroupe.indexOf(ancien);
            listeTypeGroupe.remove(ancien);
            listeTypeGroupe.add(index, nouveau);
        }
        return ok;
    }
}
