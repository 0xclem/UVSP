package metier;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'Salle'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Salle implements java.io.Serializable {

    //Attributs
    private String nom;
    private Integer capacite;

    /**
     * Constructeur d'un objet Salle prenant en compte ses 2 attributs
     * @param nom Nom de la salle
     * @param capacite Nombre d'�l�ves maximum dans la salle
     */
    
    public Salle(String n, Integer c) {
        nom = n;
        capacite = c;
    }

    /**
     * Constructeur d'un objet Salle ne prenant en compte que son attribut nom
     * @param nom Nom de la salle
     */

    public Salle(String n) {
        nom = n;
    }

    /**
     * Accesseur du nom d'un objet Salle
     * @return String - Nom de la salle
     */

    public String getNom() {
        return nom;
    }

    /**
     * Accesseur de la capacit� d'un objet Salle
     * @return Integer - Capacit� de la salle
     */

    public Integer getCapacite() {
        return capacite;
    }

    /**
     * Modifieur du nom d'un objet Salle
     * @param nomSalle Cha�ne de caract�res d�finissant le nouveau nom de l'objet Salle
     */

    public void setNom(String nomSalle) {
        nom = nomSalle;
    }

    /**
     * Modifieur de la capacit� d'un objet Salle
     * @param capSalle Entier d�finissant la nuvelle capacit� de l'objet Salle
     */

    public void setCapacite(Integer capSalle) {
        capacite = capSalle;
    }
}

