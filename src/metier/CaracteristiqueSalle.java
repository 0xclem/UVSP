package metier;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'CaracteristiqueSalle'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class CaracteristiqueSalle {

    // Attributs
    private Salle sal;
    private Caracteristique caract;
    
    /**
     * Constructeur d'un objet CaracteristiqueSalle prenant en compte la salle et la caract�ristique
     * @param sal Id de la salle
     * @param caract Id de la caract�ristique
     */
    public CaracteristiqueSalle(Salle sal, Caracteristique caract) {
        this.sal = sal;
        this.caract = caract;
    }
    
    /**
     * Accesseur du id de la salle
     * @return id de la salle
     */
    public Salle getSalle() {
        return sal;
    }

    /**
     * Modifieur de la salle
     * @param res Cha�ne de caract�res d�finissant la nouvelle r�servation de la salle
     */
    public void setSalle(Salle sal) {
        this.sal = sal;
    }
    
    /**
     * Accesseur de la caract�ristique
     * @return caract�ristique de la r�servation
     */
    public Caracteristique getCaract() {
        return caract;
    }

    /**
     * Modifieur du id de la caract�ristique
     * @param id Cha�ne de caract�res d�finissant le nouveau id de la caract�ristique
     */
    public void setCaract(Caracteristique caract) {
        this.caract = caract;
    }
}