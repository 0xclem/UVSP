package metier;

/**
 * Classe m�tier permettant de g�rer les enseignements. Un enseignement est d�fini par
 * le code de sa mati�re, le nom de son type d'enseignement, le type de groupe
 * auquel il appartient, et son nombre d'heures r�el.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class Enseignement {

    //Attributs
    private Double nbHeureReel;

    //Attributs d'association
    private Cours cours;
    private Groupe groupe;
    private Enseignant enseignant;

    /** M�thode permettant d'instancier un objet de la classe "Enseignement".
     * @param groupe Groupe de l'enseignement.
     * @param enseignant L'enseignant de cet enseignement.
     * @param Inbheures Le nombre d'heures r�el de l'enseignement.
     */
    public Enseignement(Groupe groupe, Double Inbheures, Enseignant enseignant, Cours cours) {
        this.groupe = groupe;
        this.enseignant = enseignant;
        this.nbHeureReel = Inbheures;
        this.cours = cours;
    }

    /** M�thode permettant d'acc�der � un objet de la classe "Enseignement".
     * @return Renvoie un nombre r�el repr�sentant le nombre d'heures r�el de l'enseignenemt.
     */
    public Double getNbHeureReel() {
        return this.nbHeureReel;
    }

    /** M�thode permettant d'acc�der � un objet de la classe "Enseignement".
     * @return Renvoie un objet repr�sentant le groupe de l'enseignement.
     */
    public Groupe getGroupe() {
        return this.groupe;
    }

    /** M�thode permettant d'acc�der � un objet de la classe "Enseignement".
     * @return Renvoie l'objet enseignant de l'enseignement.
     */
    public Enseignant getEnseignant() {
        return this.enseignant;
    }
    
    /** M�thode permettant d'acc�der � un objet de la classe "Enseignement".
     * @return Renvoie l'objet cours de l'enseignement.
     */
    public Cours getCours() {
        return this.cours;
    }

    /** M�thode permettant de modifier la valeur du champ "nbHeureReel" d'un
     *  objet de la classe "Enseignement".
     * @param InbHeure le nouveau nombre d'heures réel.
     */
    public void setNbHeureReel(Double InbHeure) {
        this.nbHeureReel = InbHeure;
    }

    /** M�thode permettant de modifier la valeur du champ "groupe" d'un
     *  objet de la classe "Enseignement".
     * @param groupe Le nouveau groupe de l'enseignement.
     */
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    /** M�thode permettant de modifier la valeur du champ "enseignant" d'un
     * objet de la classe "Enseignement".
     * @param enseignant Le nouveau enseignant.
     */
    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
    
    /** M�thode permettant de modifier la valeur du champ "cours" d'un
     * objet de la classe "Enseignement".
     * @param cours Le nouveau cours.
     */
    public void setCours(Cours cours) {
        this.cours = cours;
    }
}