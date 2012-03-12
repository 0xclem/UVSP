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
    ArrayList<Matiere> listeMatieres;
    private static final GestionnaireMatiere instance = new GestionnaireMatiere();

    /**
     * Constructeur d'un objet GestionnaireMatiere.
     * Son acc�s est priv� afin de contr�ler l'instanciation d'un objet unique (application du design pattern Singleton).
     */
    private GestionnaireMatiere() {
        matiereDao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getMatiereDAO();
        listeMatieres = matiereDao.getListe();
    }

    /**
     * M�thode permettant de renvoyer un objet unique de type GestionnaireMatiere
     * @return GestionnaireMatiere - Instance unique de l'objet GestionnaireMatiere
     */
    public final static GestionnaireMatiere getInstance() {
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
        return listeMatieres;
    }
    
    /**
     * M�thode qui permet l'ajout d'une mati�re
     * @param nom Nom de la mati�re
     * @param ue UE correspondant � la mati�re
     * @param resp Enseignant responsable de la mati�re
     */
    public void addMatiere(String nom, UE ue, Enseignant resp) {
        Matiere matiere = new Matiere(nom, ue, resp);
        Boolean ok= matiereDao.create(matiere);
        if ( ok )
            listeMatieres.add(matiere);
    }
    
    /**
     * M�thode qui permet la modification d'une mati�re
     * @param nom Nom de la mati�re
     * @param ue UE correspondant � la mati�re
     * @param resp Enseignant responsable de la mati�re
     */
    public void updateMatiere(Matiere matiere, String nom, UE ue, Enseignant resp) {
        listeMatieres.remove(matiere);
        matiere.setNomMat(nom);
        matiere.setUEMat(ue);
        matiere.setResponsable(resp);

        Boolean ok= matiereDao.update(matiere);
        if ( ok )
            listeMatieres.add(matiere);
    }

    /**
     * M�thode qui permet de supprimer une matiere
     * @param m Matiere � supprimer
     */
    public void deleteMatiere(Matiere m) {
        Boolean ok = matiereDao.delete(m);
        if (ok) {
            listeMatieres.remove(m);
        }
    }

}
