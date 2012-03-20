package metier;

import persistance.*;
import java.util.ArrayList;

/**
 * Classe servant de fa�ade entre le package "vue" et les classe m�tiers du package "persistance".
 * Cette classe contient une liste des Ues pr�sentes dans la base de donn�es,
 * et elle impl�mente le design pattern Singleton afin de n'�tre instanci�e qu'une seule fois.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */

public class GestionnaireUE {

	//Attributs
    DAO<UE> ueDAO;

    private ArrayList<UE> listeUEs;
    private static GestionnaireUE gestUE;

    /**
     * Constructeur d'un objet GestionnaireUE.
     * Son acc�s est priv� afin de contr�ler l'instanciation d'un objet unique (application du design pattern Singleton).
     */
    private GestionnaireUE() {
        ueDAO = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getUEDAO();
        listeUEs = new ArrayList<UE>();
    }

    /**
     * M�thode permettant de renvoyer un objet unique de type GestionnaireUE
     * @return GestionnaireUE - Instance unique de l'objet GestionnaireUE
     */
    public static GestionnaireUE getInstance()
    {
        if (gestUE == null)
        {
                gestUE = new GestionnaireUE();
        }
        return (gestUE);
    }

    /**
     * Accesseur de la liste des UEs du gestionnaire
     * @return ArrayList<UE> - Liste des UEs du gestionnaire
     */
    public ArrayList<UE> getListeUE(){
        return ueDAO.getListe();
    }

    /**
     * M�thode qui permet de supprimer une UE
     * @param u L' UE � supprimer
     */
    public void deleteUE(UE u) {
        Boolean ok= ueDAO.delete(u);
        if ( ok )
            listeUEs.remove(u);
    }

    /**
     * M�thode qui permet de mettre � jour l'UE
     * @param code Code de l'UE
     * @param nomSem Nomde du semestre de l'UE
     * @param nomAE Nom de l'ann�e d'etude � laquelle l'ue appartient
     * @param nomUE Nom de l'UE
     * @param d Description de l'UE
     * @param nbE Nombre d'ECTS pour l'UE
     * @param noteM Note minimum pour l'UE
     * @param s Semestre auquel appartient l'UE
     */
    public void updateUE(UE ue, String nom, Enseignant ens) {
        listeUEs.remove(ue);
        ue.setNomUE(nom);
        ue.setEnseignant(ens);

        Boolean ok= ueDAO.update(ue);
        if ( ok )
            listeUEs.add(ue);
    }

    /**
     * M�thode qui permet l'ajout de l'UE
     * @param code Code de l'UE
     * @param nomSem Nomde du semestre de l'UE
     * @param nomAE Nom de l'ann�e d'etude � laquelle l'ue appartient
     * @param nomUE Nom de l'UE
     * @param d Description de l'UE
     * @param nbE Nombre d'ECTS pour l'UE
     * @param noteM Note minimum pour l'UE
     * @param s Semestre auquel appartient l'UE
     */
    public void addUE(String nom, Enseignant ens) {
        UE u = new UE(nom, ens);
        Boolean ok= ueDAO.create(u);
        if ( ok )
            listeUEs.add(u);
    }

    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}