package metier;

import java.util.ArrayList;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'Matiere'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Matiere {

    // Attributs
    private String code;
    private String nom;
    private String description;
    private Integer coeff;

    // Attributs d'association
    private UE u;
    private ArrayList<TypeEnseignement> typeEns;

    /**
     * Constructeur d'un objet Matiere prenant en compte le nom uniquement
     * @param nom Nom de la mati�re
     */
    public Matiere(String nom) {
        this.nom = nom;
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte son code et son nom
     * @param code Code de la mati�re
     * @param nom Nom de la mati�re
     */
    public Matiere(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte son code et son nom
     * @param code Code de la mati�re
     * @param nom Nom de la mati�re
     * @param u L'unit� d'enseignement associ� � la mati�re
     */
    public Matiere(String code, String nom, UE u) {
        this.code = code;
        this.nom = nom;
        this.u = u;
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte son nom, sa description
     * et son coefficient
     * @param code Code de la mati�re
     * @param nom Nom de la mati�re
     * @param descr Description de la mati�re
     * @param coeff Coefficient de la mati�re dans l'UE
     * @param u UE associ� � la matiere
     */
    public Matiere (String code, String nom, String descr, Integer coeff, UE u)
    {
        this.code = code;
        this.nom = nom;
        this.description = descr;
        this.coeff = coeff;
        this.u = u;
        this.typeEns = new ArrayList();
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte ses 4 attributs
     * @param code Code de la mati�re
     * @param nom Nom de la mati�re
     * @param descr Description de la mati�re
     * @param coeff Coefficient de la mati�re dans l'UE
     * @param u UE associ� � la mati�re
     * @param typeEns Liste des types d'enseignement qui lui sont associ�s
     */
    public Matiere(String code, String nom, String description, Integer coeff,UE u, ArrayList<TypeEnseignement> typeEns) {
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.coeff = coeff;
        this.u = u;
        this.typeEns = typeEns;
    }

    public Matiere(String code, int db) {
        this.code = code;
    }

    /**
     * Accesseur du code de m'objet Matiere
     * @return code de la mati�re
     */
    public String getCode() {
        return code;
    }

    /**
     * Modifieur du code de la mati�re
     * @param code Chaîne de caract�res d�finissant le nouveau code de la mati�re
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Accesseur du coefficient d'un objet Matiere
     * @return Integer - Coefficient de l'objet Mati�re
     */
    public Integer getCoeff() {
        return coeff;
    }

    /**
     * Modifieur du coefficient d'un objet Matiere
     * @param coeff Entier d�finissant le nouveau coefficient de l'objet Matiere
     */
    public void setCoeff(Integer coeff) {
        this.coeff = coeff;
    }

    /**
     * Accesseur de la description d'un objet Matiere
     * @return String - Coefficient de l'objet Matiere
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifieur de la description d'un objet Matiere
     * @param description Cha�ne de caract�res d�finissant la nouvelle description d'un objet Matiere
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Accesseur du nom d'un objet Matiere
     * @return String - Nom de l'objet Mati�re
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifieur du nom d'un objet Matiere
     * @param nom Cha�ne de caract�res d�finissant le nouveau nom de l'objet Matiere
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Accesseur sur l'UE associ�es � un objet Matiere
     * @author Ophélie Mak
     * @return UE L'UE associ�s � une mati�re
     */
    public UE getUE() {
        return u;
    }

    /**
     * Modificateur de l'UE associ�e � un objet Matiere
     * @param u L'UE associ�e � une mati�re
     */
    public void setUE(UE u) {
        this.u = u;
    }

    /**
     * Accesseur de la liste des types denseignements d'un objet Matiere
     * @return ArrayList<TypeEnseignement> - Liste des types d'enseignements associ�s � une mati�re
     */
    public ArrayList<TypeEnseignement> getTypeEns() {
        return typeEns;
    }

    /**
     * Modifieur de la liste des types d'enseignement d'un objet Matiere
     * @param typeEns Liste es types d'enseignements associ�s � une mati�re	
     */
    public void setTypeEns(ArrayList<TypeEnseignement> typeEns) {
        this.typeEns = typeEns;
    }
}