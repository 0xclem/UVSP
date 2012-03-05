package metier;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'Salle'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Batiment{

    //Attributs
	private String code;
    private String lib;
    
    /**
     * Constructeur d'un objet Batiment prenant en compte ses 2 attributs
     * @param lib Libell� de la salle
     * @param code Code de la salle
     */
    public Batiment(String lib, String code) {
        this.lib = lib;
        this.code = code;
    }

    /**
     * Constructeur d'un objet Batiment ne prenant en compte que son attribut code
     * @param code Code du batiment
     */
    public Batiment(String code) {
        this.code = code;
    }

    /**
     * Accesseur du libell� d'un objet Salle
     * @return String - Libell� de la salle
     */
    public String getLibelle() {
        return lib;
    }

    /**
     * Accesseur du code d'un objet Salle
     * @return String - Code de la salle
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Modifieur du libell� d'un objet Salle
     * @param lib - Cha�ne de caract�res d�finissant le nouveau nom de l'objet Salle
     */
    public void setLibelle(String lib) {
        this.lib = lib;
    }

    /**
     * Modifieur de la capacit� d'un objet Salle
     * @param code Code de la salle Salle
     */
    public void setCode(String code) {
        this.code = code;
    }
}