

package metier;

/** Classe m�tier permettant de g�rer les types d'enseignement. 
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class TypeEnseignement {
    //Attributs
    private String nom;                     //nom du type de l'enseignement
    private Double heureTypeEnseignement;   //nombre d'heure du type d'enseignement
    private Double heureEquivalentTD;       //nombre d'heure de TD equivalent

    /** Cette m�thode permet l'instanciation d'un objet
     *  de la classe "TypeEnseignement". Il n'a qu'un seul param�tre.
     *  @param SNomTypeEns Le nom du nouveau type d'enseignement.
     */
    public TypeEnseignement(String SNomTypeEns) {
        this.nom = SNomTypeEns;
    }

    /** Cette m�thode permet l'instanciation d'un objet de la classe
     *  "TypeEnseignement". Il a 2 param�tres.
     *  @param SNomTypeEns Le nom du nouveau type d'enseignement.
     *  @param DHeureTypeEns Le nombre d'heure du type d'enseignement.
     *  @param DHeureEqui Le nombre d'heure de TD �quivalent.
     */
     public TypeEnseignement(String SNomTypeEns, Double DHeureTypeEns,
             Double DHeureEqui) {
         this.nom = SNomTypeEns;
         this.heureTypeEnseignement = DHeureTypeEns;
         this.heureEquivalentTD = DHeureEqui;
     }

     /** Cette m�thode permet d'acc�der � la valeur du champ
      *  "nom" d'un objet de la classe "TypeEnseignement".
      *  @return Renvoie une cha�ne de caract�res repr�sentant le nom du type
      *  d'enseignement.
      */
      public String getNom() {
          return this.nom;
      }

      /** Cette m�thode permet d'acc�der � la valeur du champ
       *  "heureTypeEnseignement" d'un objet de la classe "TypeEnseignement".
       *  @return Renvoie un nombre r�el repr�sentant le nombre d'heure du type
       *  de l'enseignement.
       */
      public Double getheureTypeEnseignement() {
          return this.heureTypeEnseignement;
      }

      /** Cette m�thode permet d'acc�der � la valeur du champ "heureEquivalentTD
       *  d'un objet de la classe "TypeEnseignement".
       *  @return Renvoie le nombre d'heure de TD �quivalent (nombre r�el).
       */
      public Double getHeureEquiTD() {
          return this.heureEquivalentTD;
      }

      /** Cette m�thode permet de modifier la valeur du champ "nom"
       *  d'un objet de la classe "TypeEnseignement".
       *  @param SNomTypeEns Le nouveau nom de type d'enseignement � modifier.
       */
      public void setNom(String SNomTypeEns) {
          this.nom = SNomTypeEns;
      }

      /** Cette m�thode permet de modifier la valeur du champ
       *  "heureTypeEnseignement" d'un objet de la classe "TypeEnseignement".
       *  @param DHeureTypeEns Le nouveau nombre d'heures du type d'enseignement
       */
      public void setHeureTypeEnseignement(Double DHeureTypeEns) {
          this.heureTypeEnseignement = DHeureTypeEns;
      }

      /** Cette m�thode permet de modifier la valeur du champ "heureEquivalentTD"
       *  d'un objet de la classe "TypeEnseignement".
       *  @param IHeureEquiTD Le nouveau nombre d'heure de TD �quivalent.
       */
      public void setHeureEquiTD(Double IHeureEquiTD) {
          this.heureEquivalentTD = IHeureEquiTD;
      }

}
