package metier;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'Salle'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Batiment{

    //Attributs
	private int idBat;
    private String lib;
    
    public Batiment(int id) {
        this.idBat = id;
    }
    
    public Batiment(int id, String lib) {
        this.idBat = id;
        this.lib = lib;
    }

  
    public Batiment(String lib) {
        this.lib = lib;
    }

 
    public String getLibelle() {
        return lib;
    }

  
   public int getIdBat()
   {
	   return this.idBat;
   }
    
   public void setIdBat(int id)
   {
	   this.idBat = id;
   }
    
    
    public void setLibelle(String lib) {
        this.lib = lib;
    }

   
}