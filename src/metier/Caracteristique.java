package metier;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'CaracteristiqueReservation'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */

public class Caracteristique {

	// Attributs
	private String id;
	private String libelle;
	
	/**
     * Constructeur d'un objet CaracteristiqueReservation prenant en compte la reservation et la caract�ristique
     * @param idSalle Id de la salle
     * @param idCaract Id de la caract�ristique
     */
	
	public Caracteristique(String id, String libelle){
		this.id=id;
		this.libelle= libelle;
	}
	
	
	/**
     * Accesseur du id de la caracteristique
     * @return id de la caracteristique
     */
	
	public String getId() {
		return id;
	}
	
    /**
     * Modifieur de la caracteristique
     * @param id Cha�ne de caract�res d�finissant le nouvel id de la caracteristique
     */
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
     * Accesseur du libelle de la caracteristique
     * @return libelle de la caracteristique
     */
	
	public String getLibelle() {
		return libelle;
	}
	
    /**
     * Modifieur le libelle de la caracteristique
     * @param libelle Cha�ne de caract�res d�finissant la nouvelle caracteristique
     */
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
