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
    DAO<UE> UEDao;
    DAO<Semestre> semestreDao;
    DAO<AnneeEtude> anneeEtudeDao;
    DAO<Matiere> matiereDao;

    private ArrayList<UE> listeUEs;
    private ArrayList<Semestre> listeSemestre;
    private ArrayList<AnneeEtude> listeAnneeEtude;
    private static GestionnaireUE gestUE;

    /**
     * Constructeur d'un objet GestionnaireUE.
     * Son acc�s est priv� afin de contr�ler l'instanciation d'un objet unique (application du design pattern Singleton).
     */
    private GestionnaireUE() {
        UEDao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getUEDAO();
        listeUEs = new ArrayList();
        semestreDao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getSemestreDAO();
        listeSemestre = semestreDao.getListe();
        anneeEtudeDao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getAnneeEtudeDAO();
        listeAnneeEtude = anneeEtudeDao.getListe();
    }

    /**
     * M�thode permettant de renvoyer un objet unique de type GestionnaireUE
     * @return GestionnaireUE - Instance unique de l'objet GestionnaireUE
     */
    public static GestionnaireUE getGestionnaireUE()
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
        return UEDao.getListe();
    }

    /**
     * Modifieur de la liste des UEs du gestionnaire
     * @param listeUEs - Liste des UEs du gestionnaire
     */
    public void setListeUEs(ArrayList<UE> listeUEs) {
        this.listeUEs = listeUEs;
    }

    /**
     * M�thode qui retourne la liste des UEs correspondant au semestre dont le nom est pass� en param�tre
     * @param semestre Nom du semestre pour laquelle on souhaite la liste des UEs associ�es
     * @return ArrayList<UE> - Liste des UEs associ�es au semestre en param�tre
     */
    public ArrayList<UE> getUEs (String semestre) {
        return ((UEDAO)this.UEDao).getUEBySemestre(semestre);
    }

    /**
     * M�thode qui retourne la liste des matieres correspondant � l'UE dont le nom est pass� en param�tre
     * @param annee Nom de l'UE pour laquelle on souhaite la liste des matieres associées
     * @return ArrayList<Matiere> - Liste des matieres associ�es � l'UE en param�tre
     */
    public ArrayList<Matiere> getMatiere (String u) {
        return ((MatiereDAO)this.matiereDao).getMatiereByUE(u);
    }

    /**
     * M�thode qui retourne la liste des UEs correspondant � l'ann�e d'�tude et
     * au semestre dont les noms sont pass�s en param�tre
     * @param annee Nom de l'ann�e d'�tude pour laquelle on souhaite la liste des UEs associ�es
     * @param semestre Nom du semestre pour lequel on souhaite la liste des UEs asoci�es
     * @return ArrayList<UE> - Liste des UEs associ�es � l'ann�e d'�tude et au semestre en param�tre
     */
    public ArrayList<UE> getUE (String annee, String semestre) {
        return ((UEDAO)this.UEDao).getUEByAnneeSemestre(annee, semestre);
    }

    /**
     * M�thode qui retourne la liste des semestres correspondant � l'ann�e d'�tude et
     * au semestre dont les noms sont pass�s en param�tre
     * @return ArrayList<Semestre> - Liste des semestre associ�es � l'ann�e d'�tude
     */
    public ArrayList<Semestre> getListeSemestre() {
        return listeSemestre;
    }

    /**
     * M�thode qui retourne la liste des l'ann�es d'�tudes
     * @return ArrayList<AnneeEtude> - Liste des ann�es d'�tudes
     */    
    public Iterable<AnneeEtude> getListeAnneeEtude() {
        return listeAnneeEtude;
    }

    /**
     * M�thode qui permet de supprimer une UE
     * @param u L' UE � supprimer
     */
    public void deleteUE(UE u) {
        Boolean ok= UEDao.delete(u);
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
    public void updateUE(String code,String nomSem, String nomAE, String nomUE, String description, int nbEcts, int noteM) {
        AnneeEtude a = new AnneeEtude(nomAE);
        Semestre s = new Semestre(nomSem,a);
        UE u = new UE(code,nomUE,description,nbEcts,noteM,s);
        Boolean ok= UEDao.update(u);
        if ( ok )
            listeUEs.add(u);
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
    public void ajouterUE(String code,String nomSem, String nomAE, String nomUE, String description, int nbEcts, int noteM) {
        AnneeEtude a = new AnneeEtude(nomAE);
        Semestre s = new Semestre(nomSem,a);
        UE u = new UE(code,nomUE,description,nbEcts,noteM,s);
        Boolean ok= UEDao.create(u);
        if ( ok )
            listeUEs.add(u);
    }
}