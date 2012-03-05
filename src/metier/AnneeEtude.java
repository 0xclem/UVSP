package metier;

import java.util.ArrayList;

/**
 * Classe qui d�finit les attributs et les m�thodes d'une ann�e d'�tude
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class AnneeEtude {
	
    // Attributs classe
    private String nomAnnee;
    private String description;
    private Integer dureeSeance;
    private Integer nbSeanceAM;
    private Integer nbSeancePM;

    // Attributs Association
    private Semestre s1;
    private Semestre s2;
    private ArrayList<AnneeUniversitaire> annneUniv;

    /**
     * Constructeur de la classe AnneeEtude ne prenant en compte que le nom de l'ann�e
     * @param nomAnnee Nom de l'ann�e d'�tude
     */
    public AnneeEtude(String nomAnnee) {
        this.nomAnnee = nomAnnee;
    }

    /**
     * Constructeur de la classe AnneeEtude prenant en compte son nom, sa description,
     * la dur�e d'une s�ance de cours, le nombre de s�ances le matin et l'apr�s-midi.
     * Les deux semestres et les ann�es universitaires sont nulls par d�faut.
     * @param nomAnnee Nom de l'ann�e d'�tude
     * @param description Description de l'ann�e d'�tude
     * @param dureeSeance Dur�e d'une s�ance de cours
     * @param nbSeanceAM Nombre de s�ances de cours le matin
     * @param nbSeancePM Nombre de s�ances de cours le soir
     * @param formation Formation � laquelle appartient l'annee d'etude
     */
    public AnneeEtude(String nomAnnee,
                      String description,
                      Integer dureeSeance,
                      Integer nbSeanceAM,
                      Integer nbSeancePM)
    {
        this.nomAnnee = nomAnnee;
        this.description = description;
        this.dureeSeance = dureeSeance;
        this.nbSeanceAM = nbSeanceAM;
        this.nbSeancePM = nbSeancePM;
        this.s1 = null;
        this.s2 = null;
        this.annneUniv = new ArrayList<AnneeUniversitaire>();
    }

    /**
     * Constructeur de la classe AnneeEtude prenant en compte ses 6 attributs
     * @param nomAnnee Nom de l'ann�e d'�tude
     * @param description Description de l'ann�e d'�tude
     * @param dureeSeance Dur�e d'une s�ance de cours
     * @param nbSeanceAM Nombre de s�ances de cours le matin
     * @param nbSeancePM Nombre de s�ances de cours le soir
     * @param formation Formation � laquelle appartient l'annee d'etude
     * @param s1 Premier semestre de l'ann�e d'�tude
     * @param s2 Second semestre de l'ann�e d'�tude
     * @param anneeUniv Liste des ann�es universitaires associ�es � l'ann�e d'�tude
     */
    public AnneeEtude(String nomAnnee,
                      String description,
                      Integer dureeSeance,
                      Integer nbSeanceAM,
                      Integer nbSeancePM,
                      Semestre s1,
                      Semestre s2,
                      ArrayList<AnneeUniversitaire> anneeUniv)
    {
        this.nomAnnee = nomAnnee;
        this.description = description;
        this.dureeSeance = dureeSeance;
        this.nbSeanceAM = nbSeanceAM;
        this.nbSeancePM = nbSeancePM;
        this.s1 = s1;
        this.s2 = s2;
        this.annneUniv = anneeUniv;
    }
    
    /**
     * Accesseur du nom d'un objet AnneeEtude
     * @return String - Nom de l'ann�e d'�tude
     */
    public String getNomAnnee() {
        return nomAnnee;
    }

    /**
     * Modifieur du nom d'un objet AnneeEtude
     * @param nomAnnee Cha�ne de caract�res d�finissant le nouveau nom de l'ann�e d'�tude
     */
    public void setNomAnnee(String nomAnnee) {
        this.nomAnnee = nomAnnee;
    }

    /**
     * Accesseur de la description d'un objet AnneeEtude
     * @return String - Description de l'ann�e d'�tude
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifieur de la description d'un objet AnneeEtude
     * @param description Cha�ne de caract�res d�finissant la nouvelle description de l'ann�e d'�tude
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Accesseur de la dur�e d'une s�ance d'un objet AnneeEtude
     * @return Integer - Dur�e des s�ance d'une ann�e d'�tude
     */
    public Integer getDureeSeance() {
        return dureeSeance;
    }

    /**
     * Modifieur de la dur�e d'une s�ance d'un objet AnneeEtude
     * @param dureeSeance Entier d�finissant la nouvelle dur�e des s�ances de l'ann�e d'�tude
     */
    public void setDureeSeance(Integer dureeSeance) {
        this.dureeSeance = dureeSeance;
    }

    /**
     * Accesseur du nombre de s�ances le matin d'un objet AnneeEtude
     * @return Integer - Nombre de s�ances pr�vues le matin pour une ann�e d'�tude
     */
    public Integer getNbSeanceAM() {
        return nbSeanceAM;
    }

    /**
     * Modifieur du nombre de s�ance le matin d'un objet AnneeEtude
     * @param nbSeanceAM Entier d�finissant le nouveau nombre de s�ances le matin de l'ann�e d'�tude
     */
    public void setNbSeanceAM(Integer nbSeanceAM) {
        this.nbSeanceAM = nbSeanceAM;
    }

    /**
     * Accesseur du nombre de s�ances l'apr�s midi d'un objet AnneeEtude
     * @return Integer - Nombre de s�ances pr�vues l'apr�s midi pour une ann�e d'�tude
     */
    public Integer getNbSeancePM() {
        return nbSeancePM;
    }

    /**
     * Modifieur du nombre de s�ance l'apr�s midi d'un objet AnneeEtude
     * @param nbSeancePM Entier d�finissant le nouveau nombre de s�ances l'apr�s midi de l'ann�e d'�tude
     */
    public void setNbSeancePM(Integer nbSeancePM) {
        this.nbSeancePM = nbSeancePM;
    }

    /**
     * Accesseur de la liste des ann�es universitaires associ�es � un objet AnneeEtude
     * @return ArrayList<AnneeUniversitaire> - Liste des ann�es universitaires d'une ann�e d'�tude
     */
    public ArrayList<AnneeUniversitaire> getAnnneUniv() {
        return annneUniv;
    }

    /**
     * Modifieur de la liste des ann�es universitaires associ�es � un objet AnneeEtude
     * @param annneUniv Liste des années universitaires associ�es � l'ann�e d'�tude
     */
    public void setAnnneUniv(ArrayList<AnneeUniversitaire> annneUniv) {
        this.annneUniv = annneUniv;
    }

    /**
     * Accesseur du premier semestre associ� � un objet AnneeEtude
     * @return Semestre - Objet Semestre correspondant au 1er semestre associ� � une ann�e d'�tude
     */
    public Semestre getS1() {
        return s1;
    }

    /**
     * Modifieur du premier semestre associ� � un objet AnneeEtude
     * @param s1 Objet Semestre correspondant au 1er semestre associ� � une ann�e d'�tude
     */
    public void setS1(Semestre s1) {
        this.s1 = s1;
    }

    /**
     * Accesseur du second semestre associ� � un objet AnneeEtude
     * @return Semestre - Objet Semestre correspondant au 2nd semestre associ� � une ann�e d'�tude
     */
    public Semestre getS2() {
        return s2;
    }

    /**
     * Modifieur du second semestre associ� � un objet AnneeEtude
     * @param s2 Objet Semestre correspondant au 2nd semestre associ� � une ann�e d'�tude
     */
    public void setS2(Semestre s2) {
        this.s2 = s2;
    }
}
