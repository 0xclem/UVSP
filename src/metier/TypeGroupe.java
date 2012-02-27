package metier;

/**
 * Classe m�tier permettant de g�rer les types de groupe d'�tudiants.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class TypeGroupe {

    //Attributs
    private String nomTypeGroupe;

    /** M�thode permettant d'instancier un objet de la classe "TypeGroupe".
     * @param nomTypeGroupe Le nom du type de groupe.
     */
    public TypeGroupe(String nomTypeGroupe) {
        this.nomTypeGroupe = nomTypeGroupe;
    }

    public TypeGroupe() {}

    /** M�thode permettant d'acc�der � la valeur du champ "nomTypeGroupe" d'un
     *  objet de la classe "TypeGroupe".
     * @return Renvoie une chaine de caract�res repr�sentant le nom du type
     *  de groupe.
     */
    public String getNomTypeGroupe() {
        return this.nomTypeGroupe;
    }

    /** M�thode permettant de modifier la valeur du champ "nomTypeGroupe" d'un
     *  objet de la classe "TypeGroupe".
     * @param nomTypeGroupe Le nouveau nom du type de groupe.
     */
    public void setNomTypeGroupe(String nomTypeGroupe) {
        this.nomTypeGroupe = nomTypeGroupe;
    }
}
