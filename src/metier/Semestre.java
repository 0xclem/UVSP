package metier;

import java.util.ArrayList;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets Semestre
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Semestre {

    // Attributs
    private String nomSemestre;
    private Integer niveau;

    // Attributs associations
    private ArrayList<UE> ue;
    private AnneeEtude anneeEtude;
    
    /**
     * Constructeur d'un objet Semestre prenant en compte uniquement son nom
     * @param nomSemestre Nom du semestre
     * @param nomAnneeEtude Nom de l'ann�e d'�tude
     */
    public Semestre(String nomSemestre, AnneeEtude anneeEtude) {
        this.nomSemestre = nomSemestre;
        this.anneeEtude = anneeEtude;
    }

    /** Constructeur d'un objet Semestre prenant en compte son nom, son ann�e d'�tude et son niveau
     * @param nomSemestre Nom du semestre
     * @param nomAnneeEtude Nom de l'ann�e d'�tude
     * @param niveau Niveau du semestre
     */
    public Semestre(String nomSemestre, AnneeEtude anneeEtude, Integer niveau) {
        this.nomSemestre = nomSemestre;
        this.anneeEtude = anneeEtude;
        this.niveau = niveau;
        this.ue = new ArrayList<UE>();
    }

    /**
     * Constructeur d'un objet Semestre prenant en compte ses 3 attributs
     * @param nomSemestre Nom du semestre
     * @param nomAnneeEtude Nom de l'ann�e d'�tude associ�e au semestre
     * @param niveau Niveau du semestre dans l'ann�e d'�tude
     */
    public Semestre(String nomSemestre, AnneeEtude anneeEtude, Integer niveau, ArrayList<UE> ue) {
        this.nomSemestre = nomSemestre;
        this.anneeEtude = anneeEtude;
        this.niveau = niveau;
        this.ue = ue;
    }

    /**
     * Accesseur du nom d'un objet Semestre
     * @return String - Nom du semestre
     */
    public String getNomSemestre() {
        return nomSemestre;
    }

    /**
     * Modifieur du nom d'un objet Semestre
     * @param nomSemestre - Cha�ne de caract�re d�finissant le nouveau nom du semestre
     */
    public void setNomSemestre(String nomSemestre) {
        this.nomSemestre = nomSemestre;
    }

    /**
     * Accesseur du nom de l'ann�e d'�tude d'un objet Semestre
     * @return AnneeEtude L'ann�e d'�tude du semestre
     */
    public AnneeEtude getAnneeEtude() {
        return anneeEtude;
    }

    /**
     * Modifieur du nom de l'ann�e d'�tude d'un objet Semestre
     * @param anneeEtude L'ann�e d'�tude du semestre
     */
    public void setAnneeEtud(AnneeEtude nomAnneeEtude) {
        this.anneeEtude = nomAnneeEtude;
    }

    /**
     * Accesseur du niveau d'un objet Semestre
     * @return Integer - Niveau du semestre
     */
    public Integer getNiveau() {
        return niveau;
    }

    /**
     * Modifieur du niveau d'un objet Semestre
     * @param niveau - Entier d�finissant le nouveau niveau du semestre
     */
    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    /**
     * Accesseur de la liste des UEs associ�es � un objet Semestre
     * @return ArrayList<UE> - Liste des UEs du semestre
     */
    public ArrayList<UE> getUe() {
        return ue;
    }

    /**
     * Modifieur de la liste des UEs associ�es � un objet Semestre
     * @param ue Liste des UEs du semestre
     */
    public void setUe(ArrayList<UE> ue) {
        this.ue = ue;
    }
}
