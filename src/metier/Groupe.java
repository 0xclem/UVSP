package metier;

/**
 * Classe metier de gestion des groupes
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Groupe {

	//Attributs
    private String code;
    public String lib;
    public Groupe pere;
    
    /**
     * Constructeur d'un groupe
     * @param code Code du groupe
     * @param lib Libell� du groupe
     * @param p�re Groupe p�re du groupe
     */
    public Groupe(String code, String lib, Groupe pere) {
        this.code = code;
        this.lib = lib;
        this.pere = pere;
    }

    /**
     * Constructeur d'un groupe qu'avec le code
     * @param code Code du groupe
     */
    public Groupe(String code) {
        this.code = code;
    }
    
    /**
     * Constructeur d'un groupe qu'avec le code
     * @param code Code du groupe
     */
    public Groupe(String code, String lib) {
        this.code = code;
        this.lib = lib;
    }

    /**
     * Recuperation du code du groupe
     * @return le code du groupe
     */
    public String getCode() {
        return code;
    }

    /**
     * Affectation du code du groupe
     * @param unNom le code du groupe
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Recuperation du libell� du groupe
     * @return Libell� du groupe
     */
    public String getLibelle() {
        return lib;
    }

    /**
     * Affectation du nombre d'�tudiants du groupe
     * @param nbEtudiants le nombre d'�tudiants du groupe
     */
    public void setLibelle(String lib) {
        this.lib = lib;
    }

    /**
     * Recuperation du p�re du groupe
     * @return P�re du groupe
     */
    public Groupe getPere() {
        return pere;
    }

    /**
     * Affectation p�re du groupe
     * @param p�re du groupe
     */
    public void setPere(Groupe pere) {
        this.pere = pere;
    }
}