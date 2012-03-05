package metier;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'AnneeUniveristaire'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class AnneeUniversitaire {
    
    // Attributs
    private String nom_annee_univ;
    
    /**
     * Constructeur d'un objet AnneeUniversitaire prenant en compte un attribut
     * @param nom d'un objet ann�e universitaire
     */
    public AnneeUniversitaire (String nom_annee_univ)
    {
        this.nom_annee_univ = nom_annee_univ;
    }

    /**
     * Accesseur du code d'un objet Ann�e Universitaire
     * @return String - Nom ann�e universitaire
     */
    public String getNomAnneeUniversitaire() {
        return nom_annee_univ;
    }

    /**
     * Modifieur du code d'un objet Ann�eUniversitaire
     * @param nom de l'objet Ann�eUniversitaire
     */
    public void setAnneeUniversitaire(String nom) {
        this.nom_annee_univ = nom ;
    }
}