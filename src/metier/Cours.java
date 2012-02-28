package metier;

public class Cours {

    // Attributs
    private String code;
    private String lib;

    // Attributs d'association
    private Matiere mat;
    private TypeCours type;

    /**
     * Constructeur d'un objet Matiere prenant en compte le nom uniquement
     * @param lib Libell� de la mati�re
     */
    public Cours(String lib) {
        this.lib = lib;
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte son code et son nom
     * @param code Code de la mati�re
     * @param lib Libell� de la mati�re
     */
    public Cours(String code, String lib) {
        this.code = code;
        this.lib = lib;
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte son code et son nom
     * @param code Code de la mati�re
     * @param lib Libell� de la mati�re
     * @param u L'unit� d'enseignement associ� � la mati�re
     */
    public Cours(String code, String lib, Matiere mat) {
        this.code = code;
        this.lib = lib;
        this.mat = mat;
    }

    /**
     * Constructeur d'un objet Matiere prenant en compte son nom, sa description
     * et son coefficient
     * @param code Code de la mati�re
     * @param lib Libell� de la mati�re
     * @param u UE associ� � la matiere
     * @param resp Responsable de la matiere
     */
    public Cours(String code, String lib, Matiere mat, TypeCours type)
    {
        this.code = code;
        this.lib = lib;
        this.mat = mat;
        this.type = type;
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
     * Accesseur de la mati�re d'un objet Cours
     * @return Matiere - Matiere de l'objet Cours
     */
    public Matiere getMatiere() {
        return mat;
    }

    /**
     * Modifieur du coefficient d'un objet Matiere
     * @param coeff Entier d�finissant le nouveau coefficient de l'objet Matiere
     */
    public void setMatiere(Matiere mat) {
        this.mat = mat;
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
     * @author Ophélie Mak
     * @return UE L'UE associ�s � une mati�re
     */
    public TypeCours getTypeCours() {
        return type;
    }

    /**
     * Modificateur de l'UE associ�e � un objet Matiere
     * @param u L'UE associ�e � une mati�re
     */
    public void setTypeCours(UE u) {
        this.type = type;
    }
}
