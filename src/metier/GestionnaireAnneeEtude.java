package metier;

import java.util.*;
import persistance.*;

/**
 * Classe servant de fa�ade entre le package "vue" et les classe m�tiers du package "persistance".
 * Cette classe contient une liste des ann�es d'�tudes pr�sentes dans la base de donn�es,
 * et elle impl�mente le design pattern Singleton afin de n'�tre instanci�e qu'une seule fois.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class GestionnaireAnneeEtude {

    // Objet de type "DAO" AnneeEtude assurant la correspondance avec la base de donn�es
    DAO<AnneeEtude> anneeDao;
    // Objet de type "DAO" Semestre assurant la correspondance avec la base de donn�es
    DAO<Semestre> semestreDao;
    // Liste des mati�res contenues dans la base
    ArrayList<AnneeEtude> listeAnnee;
    // Liste des semestres contenus dans la base
    ArrayList<Semestre> listeSemestre;
    // Instance d'un objet GestionnaireMatiere
    private static final GestionnaireAnneeEtude instance = new GestionnaireAnneeEtude();

    /**
     * Constructeur d'un objet GestionnaireAnneeEtude.
     * Son acc�s est priv� afin de contr�ler l'instanciation d'un objet unique (application du design pattern Singleton).
     */
    private GestionnaireAnneeEtude () {
        // Initialisation du DAO de AnneeEtude (une seule et unique instance)
        this.anneeDao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getAnneeEtudeDAO();
        // Initialisation de la liste des années d'étude
        this.listeAnnee = new ArrayList();
        // Initialisation du DAO de Semestre (une seule et unique instance)
        this.semestreDao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getSemestreDAO();
        // Initialisation de la liste des semestres
        this.listeSemestre = new ArrayList();
    }

    /**
     * M�thode permettant de renvoyer un objet unique de type GestionnaireAnneeEtude
     * @return GestionnaireAnneeEtude - Instance unique de l'objet GestionnaireAnneeEtude
     */
    public final static GestionnaireAnneeEtude getGestionnaireAnneeEtude () {
        return instance;
    }

    /**
     * Accesseur de la liste des ann�es d'�tude du gestionnaire
     * @return ArrayList<AnneeEtude> - Liste des ann�es d'�tudes du gestionnaire
     */
    public ArrayList<AnneeEtude> getListeAnnee() {
        return listeAnnee;
    }

    /**
     * Modifieur de la liste des ann�es d'�tude du gestionnaire
     * @param listeAnnee Nouvelle liste d'objets AnneeEtude
     */
    public void setListeAnnee(ArrayList<AnneeEtude> listeAnnee) {
        this.listeAnnee = listeAnnee;
    }

    /**
     * Accesseur de la liste des semestres du gestionnaire
     * @return ArrayList<Semestre> - Liste des semestres du gestionnairs
     */
    public ArrayList<Semestre> getListeSemestre() {
        return listeSemestre;
    }

    /**
     * Modifieur de la liste des semestres du gestionnaire
     * @param listeSemestre Nouvelle liste d'objets Semestre
     */
    public void setListeSemestre(ArrayList<Semestre> listeSemestre) {
        this.listeSemestre = listeSemestre;
    }

    /**
     * M�thode qui renvoie l'objet AnneeEtude correspondant au nom de l'ann�e pass�e en param�tre
     * @param nom Nom de l'ann�e d'�tude recherch�e
     * @return AnneeEtude - L'objet AnneeEtude recherch�
     */
    public AnneeEtude getAnneeEtude (String nom) {
        return this.anneeDao.find(new AnneeEtude (nom));
    }

    /**
     * M�thode qui renvoie l'objet Semestre correspondant au nom du semestre et
     * de l'ann�e d'�tude pass�s en param�tre
     * @param nomSem Nom du semestre recherch�
     * @param nom Nom de l'ann�e d'�tude correspondant au semestre recherch�
     * @return Semestre - L'objet Semestre recherch�
     */
    public Semestre getSemestre (String nomSem, AnneeEtude annee) {
        return this.semestreDao.find(new Semestre (nomSem, annee));
    }
    /**
     * M�thode qui retourne la liste des semestres correspondant � l'ann�e d'�tude
     * dont le nom est pass� en param�tre
     * @param annee Nom de l'ann�e d'�tude pour laquelle on souhaite la liste des semestres associ�es
     * @return ArrayList<Semestre> - Liste des semestres associ�es � l'ann�e d'�tude en param�tre
     */
    public ArrayList<Semestre> getSemestre (String annee) {
        return ((SemestreDAO)this.semestreDao).getSemestreByAnnee(annee);
    }

    /**
     * M�thode qui permet de supprimer une annee d'�tude
     * @param a Annne d'etude � supprimer
     */
    public void deleteAnneeEtude(AnneeEtude a) {
        Boolean ok= anneeDao.delete(a);
        if ( ok )
            listeAnnee.remove(a);
    }

    /**
     * M�thode qui permet de supprimer un semestre
     * @param s Semestre d'etude � supprimer
     */
    public void deleteSemestre(Semestre s) {
        Boolean ok= semestreDao.delete(s);
        if ( ok )
            listeSemestre.remove(s);
    }

    /**
     * M�thode qui permet de mettre � jour de l'annee d'etude
     * @param nomAnnee Nom de l'ann�e d'�tude
     * @param description Description de l'ann�e d'�tude
     * @param dureeSeance Dur�e d'une s�ance de cours
     * @param nbSeanceAM Nombre de s�ances de cours le matin
     * @param nbSeancePM Nombre de s�ances de cours le soir
     * @param nomFormation Nom de la formation � laquelle appartient l'annee d'etude
     */
    public void updateAnneeEtude(String nomAnnee,String description,
            Integer dureeSeance,Integer nbSeanceAM,Integer nbSeancePM,String nomFormation) {
        Formation f = new Formation(nomFormation);
        AnneeEtude a = new AnneeEtude(nomAnnee,description,dureeSeance,nbSeanceAM,nbSeancePM,f);
        Boolean ok= anneeDao.update(a);
        if ( ok )
            listeAnnee.add(a);
    }

    /**
     * M�thode qui permet de mettre � jour le semestre
     * @param nomSemestre Nom du semestre
     * @param nomAnneeEtude Nom de l'ann�e d'�tude
     * @param niveau Niveau du semestre
     */
    public void updateSemestre(String nomSemestre, String nomAnneeEtude, Integer niveau) {
        AnneeEtude a = new AnneeEtude(nomAnneeEtude);
        Semestre s = new Semestre(nomSemestre,a,niveau);
        Boolean ok= semestreDao.update(s);
        if ( ok )
            listeSemestre.add(s);
    }

    /**
     * M�thode qui permet l'ajout de l'anne d'etude
     * @param nomAnnee Nom de l'ann�e d'�tude
     * @param description Description de l'ann�e d'�tude
     * @param dureeSeance Dur�e d'une s�ance de cours
     * @param nbSeanceAM Nombre de s�ances de cours le matin
     * @param nbSeancePM Nombre de s�ances de cours le soir
     * @param nomFormation Nom de la formation � laquelle appartient l'annee d'etude
     */
    public void ajouterAnneeEtude(String nomAnnee,String description,
            Integer dureeSeance,Integer nbSeanceAM,Integer nbSeancePM,String nomFormation) {
        Formation f = new Formation(nomFormation);
        AnneeEtude a = new AnneeEtude(nomAnnee,description,dureeSeance,nbSeanceAM,nbSeancePM,f);
        Boolean ok= anneeDao.create(a);
        if ( ok )
            listeAnnee.add(a);
    }

    /**
     * M�thode qui permet de mettre � jour le semestre
     * @param nomSemestre Nom du semestre
     * @param nomAnneeEtude Nom de l'ann�e d'�tude
     * @param niveau Niveau du semestre
     */
    public void ajouterSemestre(String nomSemestre, String nomAnneeEtude, Integer niveau) {
        AnneeEtude a = new AnneeEtude(nomAnneeEtude);
        Semestre s = new Semestre(nomSemestre,a,niveau);
        Boolean ok= semestreDao.create(s);
        if ( ok )
            listeSemestre.add(s);
    }
}
