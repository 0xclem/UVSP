package metier;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'CaracteristiqueReservation'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class CaracteristiqueReservation {

    // Attributs
    private Reservation res;
    private Caracteristique caract;
    
    /**
     * Constructeur d'un objet CaracteristiqueReservation prenant en compte la reservation et la caract�ristique
     * @param idSalle Id de la salle
     * @param idCaract Id de la caract�ristique
     */
    public CaracteristiqueReservation(Reservation res, Caracteristique caract) {
        this.res = res;
        this.caract = caract;
    }
    
    /**
     * Accesseur du id de la reservation
     * @return id de la reservation
     */
    public Reservation getReservation() {
        return res;
    }

    /**
     * Modifieur de la reservation
     * @param res Cha�ne de caract�res d�finissant la nouvelle r�servation
     */
    public void setReservation(Reservation res) {
        this.res = res;
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
