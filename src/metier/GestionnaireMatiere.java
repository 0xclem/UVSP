package metier;

import persistance.*;
import java.util.*;

/**
 * Classe servant de fa�ade entre le package "vue" et les classe m�tiers du package "persistance".
 * Cette classe contient une liste des mati�res pr�sentes dans la base de donn�es,
 * et elle impl�mente le design pattern Singleton afin de n'�tre instanci�e qu'une seule fois.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class GestionnaireMatiere {

	//Attributs
    DAO<Matiere> matiereDao;
    ArrayList<Matiere> listeMat;
    ArrayList<Matiere> listeMatAE;
    private static final GestionnaireMatiere instance = new GestionnaireMatiere();

    /**
     * Constructeur d'un objet GestionnaireMatiere.
     * Son acc�s est priv� afin de contr�ler l'instanciation d'un objet unique (application du design pattern Singleton).
     */
    private GestionnaireMatiere() {
        matiereDao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getMatiereDAO();
        listeMat = matiereDao.getListe();
    }

    /**
     * M�thode permettant de renvoyer un objet unique de type GestionnaireMatiere
     * @return GestionnaireMatiere - Instance unique de l'objet GestionnaireMatiere
     */
    public final static GestionnaireMatiere getGestionnaireMatiere() {
        return instance;
    }

    /**
     * M�thode qui renvoie l'objet Matiere correspondant au nom en param�tre
     * @param nom Nom de la mati�re recherch�e
     * @return Matiere - L'objet Matiere recherch�
     */
    public Matiere getMatiere(String nom) {
        return this.matiereDao.find(new Matiere(nom));
    }

    /**
     * M�thode qui renvoie la liste de mati�res
     * @return listeMat - Liste de mati�res
     */
    public ArrayList<Matiere> getListeMatiere() {
        return listeMat;
    }

    /**
     * Accesseur de la liste des ann�es d'�tude du gestionnaire
     * @return ArrayList<AnneeEtude> - Liste des ann�es d'�tudes du gestionnaire
     */
    public ArrayList<Matiere> getListeMatiere(AnneeEtude a) {
        listeMatAE = listeMat; // copie partielle ou pas?
        for (int i=0; i<listeMatAE.size(); i++)
            if (! listeMatAE.get(i).getUE().getSem().getAnneeEtude().getNomAnnee()
                .equalsIgnoreCase(a.getNomAnnee()))
                listeMatAE.remove(i);
        return listeMatAE;
    }

    /**
     * M�thode qui permet de supprimer une matiere
     * @param m Matiere à supprimer
     */
    public void deleteMatiere(Matiere m) {
        Boolean ok = matiereDao.delete(m);
        if (ok) {
            listeMat.remove(m);
        }
    }

    /**
     * M�thode qui permet de mettre � jour l'annee d'etude
     * @param nom Nom de la mati�re
     * @param descr Description de la mati�re
     * @param coeff Coefficient de la mati�re dans l'UE
     * @param nomUE Nom de l'UE associ� � la matiere
     */
    public void updateAnneeEtude(String code, String nom, String descr, Integer coeff, String nomUE) {
        UE u = new UE(nomUE);
        Matiere m = new Matiere(code, nom, descr, coeff, u);
        Boolean ok = matiereDao.update(m);
        if (ok) {
            listeMat.add(m);
        }
    }

    /**
     * M�thode qui permet l'ajout de l'anne d'etude
     * @param descr Description de la mati�re
     * @param coeff Coefficient de la mati�re dans l'UE
     * @param nomUE Nom de l'UE associ� � la matiere
     */
    public void ajouterAnneeEtude(String code, String lib, String nomUE, Enseignant resp) {
        UE u = new UE(nomUE);
        Matiere m = new Matiere(code, lib, u, resp);
        Boolean ok = matiereDao.create(m);
        if (ok) {
            listeMat.add(m);
        }
    }
}
