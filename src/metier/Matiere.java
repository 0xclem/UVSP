package metier;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'Matiere'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Matiere {

    // Attributs
    private String code;
    private String lib;

    // Attributs d'association
    private UE u;
    private Enseignant resp;
    
    /**
     * Constructeur d'un objet Matiere prenant en compte le nom uniquement
     * @param lib Libell� de la mati�re
     */
    public Matiere(String lib) {
        this.lib = lib;
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte son code et son nom
     * @param code Code de la mati�re
     * @param lib Libell� de la mati�re
     */
    public Matiere(String code, String lib) {
        this.code = code;
        this.lib = lib;
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte son code et son nom
     * @param code Code de la mati�re
     * @param lib Libell� de la mati�re
     * @param u L'unit� d'enseignement associ� � la mati�re
     */
    public Matiere(String code, String lib, UE u) {
        this.code = code;
        this.lib = lib;
        this.u = u;
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte son nom, sa description
     * et son coefficient
     * @param code Code de la mati�re
     * @param lib Libell� de la mati�re
     * @param u UE associ� � la matiere
     * @param resp Responsable de la matiere
     */
    public Matiere (String code, String lib, UE u, Enseignant resp)
    {
        this.code = code;
        this.lib = lib;
        this.u = u;
        this.resp = resp;
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
     * @param code Cha�ne de caract�res d�finissant le nouveau code de la mati�re
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Accesseur du coefficient d'un objet Matiere
     * @return Integer - Coefficient de l'objet Mati�re
     */
    public Enseignant getResponsable() {
        return resp;
    }

    /**
     * Modifieur du coefficient d'un objet Matiere
     * @param coeff Entier d�finissant le nouveau coefficient de l'objet Matiere
     */
    public void setResponsable(Enseignant resp) {
        this.resp = resp;
    }

    /**
     * Accesseur du nom d'un objet Matiere
     * @return String - Nom de l'objet Mati�re
     */
    public String getLibelle() {
        return lib;
    }

    /**
     * Modifieur du nom d'un objet Matiere
     * @param nom Cha�ne de caract�res d�finissant le nouveau nom de l'objet Matiere
     */
    public void setLibelle(String lib) {
        this.lib = lib;
    }

    /**
     * Accesseur sur l'UE associ�es � un objet Matiere
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
}