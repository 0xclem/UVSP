package metier;

import persistance.*;
import java.util.*;

/**
 * Classe servant de fa�ade entre le package "vue" et les classe m�tiers du package "persistance".
 * Cette classe contient une liste des salles pr�sentes dans la base de donn�es,
 * et elle impl�mente le design pattern Singleton afin de n'�tre instanci�e qu'une seule fois.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */

public class GestionnaireSalle {
	
    //Attributs
    DAO<Salle> salleDAO;
    ArrayList<Salle> listeSalles;
    private static final GestionnaireSalle instance = new GestionnaireSalle();

    /**
     * Constructeur d'un objet GestionnaireSalle.
     * Son acc�s est priv� afin de contr�ler l'instanciation d'un objet unique (application du design pattern Singleton).
     */
    private GestionnaireSalle () {
        salleDAO = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getSalleDAO();
       listeSalles = salleDAO.getListe();
    }


    /**
     * M�thode permettant de renvoyer un objet unique de type GestionnaireSalle
     * @return GestionnaireSalle - Instance unique de l'objet GestionnaireSalle
     */
    public final static GestionnaireSalle getGestionnaireSalle () {
        return instance;
    }

    /**
     * M�thode qui enregistre les informations d'un nouvel objet salle
     * et rajoute � la liste des salles
     * @param nom le nom de la salle
     * @param capacite la capacit� de la salle
     * @return l'objet salle cr��
     */
    public Salle addSalle( String nom, Integer capacite) {
        Salle salle = new Salle(nom, capacite);
        salleDAO.create(salle);
        listeSalles.add(salle);
        return salle;
    }


    /**
     * M�thode qui supprime l'objet salle de la liste des salles
     * @param salle: l'objet � supprimer de la liste
     */
    public void deleteSalle(Salle salle)
    {
        int i = 0;
        while (!salle.getNom().equals((listeSalles.get(i)).getNom()))
        {
                i++;
        }
        listeSalles.remove(i) ;
        salleDAO.delete(salle);
    }


    /**
     * M�thode qui met � jour l'objet salle
     * @param nom le nom de la salle
     * @param capacite la capacit� de la salle
     */
    public void updateSalle( String nom, Integer capacite)
    {
        Salle salle = new Salle( nom, capacite);
        salleDAO.update(salle);
        listeSalles = salleDAO.getListe();
    }
}