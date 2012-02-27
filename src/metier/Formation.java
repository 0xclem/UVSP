package metier;

import java.util.ArrayList;

/**
 * Classe m�tier permettant la d�finition et la gestion des objets 'Formation'
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Formation {

    // Attributs
    private String nom;
    private String descriptifObjectifs;
    
    // Attributs association
    private ArrayList<AnneeEtude> anneeEtude;

    /**
     * Constructeur public d'un objet Formation prenant en compte son nom uniquement
     * @param name Nom formation
     */
    public Formation(String name)
    {
        this.nom = name ;
        this.descriptifObjectifs = "";
        this.anneeEtude = new ArrayList();
    }

    /**
     * Constructeur public d'un objet Formation prenant en compte son nom et sa description
     * @param nom
     * @param descriptifObjectifs
     */
    public Formation(String nom, String descriptifObjectifs) {
        this.nom = nom;
        this.descriptifObjectifs = descriptifObjectifs;
    }

    /**
     * Constructeur public d'un objet Formation prenant en compte ses 3 param�tres
     * @param name Nom formation
     * @param descriptif Description objectifs formation
     * @param ArrayList<AnneeEtude> Liste des ann�es d'�tudes de la formation
     */
    public Formation(String name, String descriptif, ArrayList<AnneeEtude> annee)
    {
        nom = name ;
        descriptifObjectifs = descriptif;
        anneeEtude = annee;
    }

    /**
     * Methode qui retourne le nom de la formation
     * @return le nom de l'objet formation
     */
    public String getNom(){
        return nom ;
    }

    /**
     * Methode qui affecte un nom � la formation cr��e
     * @param nom Nouveau nom de la formation
     */
    public void setNom(String nom){
        this.nom = nom ;
    }

    /**
     * Methode qui retourne la description des objectifs de la formation
     * @return description objectifs de l'objet formation
     */
    public String getDescriptifObjectifs(){
        return descriptifObjectifs ;
    }

    /**
     * Methode qui affecte un num�ro id � une formation cr��e
     * @param desc Description des objectifs de formation
     */
    public void setDescriptifObjectifs(String desc){
        this.descriptifObjectifs = desc ;
    }

    /**
     * Accesseur de la liste des ann�es d'�tudes d'un objet Formation
     * @return ArrayList<AnneeEtude> - Liste des années d'études de la formation
     */
    public ArrayList<AnneeEtude> getAnneeEtude() {
        return anneeEtude;
    }

    /**
     * Modifieur de la liste des ann�es d'�tudes d'un objet Formation
     * @param anneeEtude Liste des ann�es d'�tude de la formation
     */
    public void setAnneeEtude(ArrayList<AnneeEtude> anneeEtude) {
        this.anneeEtude = anneeEtude;
    }
}