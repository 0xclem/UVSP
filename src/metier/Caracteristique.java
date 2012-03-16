package metier;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'CaracteristiqueReservation'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */

public class Caracteristique {

	// Attributs
	private int idCar;
	private String lib;
	
	public Caracteristique(int id)
	{
		this.idCar=id;
	}
	
	public Caracteristique(int id, String libelle)
	{
		this.idCar=id;
		this.lib= libelle;
	}
	
	public Caracteristique(String libelle)
	{
		this.lib = libelle;
	}
	
	public String toString() {
		return lib;
	}
	
	public int getIdCar()
	{
		return this.idCar;
	}
	
	public void setIdCar(int id)
	{
		this.idCar = id;
	}
	
	
	public String getLibelle() {
		return this.lib;
	}
	
	
	public void setLibelle(String libelle) {
		this.lib = libelle;
	}
	
}
