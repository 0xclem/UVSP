package metier;

import java.util.* ;
//import persistance.DAO;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'Conge'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Conge extends TypeConge{

    // Attributs
    private String nomConge;
    private Date dateDebut;
    private Date dateFin ;

    //Attributs de l'association
    AnneeUniversitaire anneeAU;

    /**
     * Constructeur d'un objet Conge prenant en compte ses 5 attributs
     * @param nomC Nom du cong�
     * @param nomT Nom du type de cong�
     * @param anneeU Objet ann�e univerisitaire
     * @param dateD date d�but du cong�
     * @param dateF date fin du cong�
     */
    public Conge (String nomC, String nomT, AnneeUniversitaire anneeU, Date dateD, Date dateF)
    {
        super(nomT);
        this.nomConge = nomC;
        this.anneeAU = anneeU;
        this.dateDebut = dateD;
        this.dateFin = dateF;
    }

    /**
     * Accesseur du nom du cong�
     * @return String - nom du cong�
     */
    public String getNomConge() {
        return nomConge;
    }

    /**
     * Modifieur du nom du cong�
     * @param nomC Cha�ne de caract�res d�finissant le nouveau nom de l'objet Conge
     */
    public void setNomConge(String nomC) {
        this.nomConge = nomC;
    }

    /**
     * Accesseur � la date de d�but de cong�
     * @return Date - date de d�but de l'objet Conge
     */
    public Date getDateDebut() {
        return dateDebut ;
    }

    /**
     * Modifieur de la date d�but de l'objet conge
     * @param dateD date de d�but de l'objet Conge
     */
    public void setDateDebut(Date dateD) {
        this.dateDebut = dateD;
    }


    /**
     * Accesseur � l'objet Ann�eUniversitaire d'un objet Conge
     * @return  anneeAU -  Objet annéeUniversitaire de l'objet Conge
     */
    public AnneeUniversitaire getAnneeAU() {
        return anneeAU;
    }

     /**
     * Modifieur de l'objet Ann�eUniversitaire de l'objet conge
     * @param anneeAU Objet année universitaire correspondant à l'objet Conge
     */
    public void setAnneeAU(AnneeUniversitaire anneeAU) {
        this.anneeAU = anneeAU;
    }

      /**
     * Accesseur � la date de fin d'un objet Conge
     * @return Date - date de fin de l'objet Conge
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * Modifieur de la date fin du Conge
     * @param dateF -  date fin de l'objet Conge
     */
    public void setDateFin(Date dateF) {
        this.dateFin = dateF;
    }
}