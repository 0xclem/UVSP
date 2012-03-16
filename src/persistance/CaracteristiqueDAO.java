package persistance;
import metier.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe permettant de mapper les objets Caracteristique vers la table CARACTERISTIQUE de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class CaracteristiqueDAO extends DAO<Caracteristique> {

	private static final CaracteristiqueDAO instance = new CaracteristiqueDAO();
	
    /**
     * M�thode permettant de r�cup�rer l'objet unique de type CaracteristiqueDAO
     * @return CaracteristiqueDAO - Instance unique de l'objet CaracteristiqueDAO
     */
	public final static CaracteristiqueDAO getInstance() {
		return instance;
	}
	
    /**
     * M�thode qui ex�cute une requ�te d'ajout d'une nouvelle caract�ristique dans la base de donn�es.
     * Cette m�thode red�finit la m�thode create(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param ca Objet Caracteristique qui doit �tre mapp� dans la base
     * @return Boolean - Vrai si l'insertion s'est d�roul�e correctement, Faux sinon
     */
	public boolean create(Caracteristique ca) {
		 boolean ok = true;
         try {
        	 
             this.connect.createStatement().executeUpdate(
                         "INSERT INTO Caracteristique " +
                         "(id_caracteristique, libelle_caracteristique) " +
                         "VALUES (seqCaracteristique.nextval, '"+ ca.getLibelle()+"')");
         }
         catch (SQLException e) 
         {
        	 ok = false;
         }
         return ok;
	}

    /**
     * M�thode qui ex�cute une requ�te de mise � jour d'un enregistrement de la table 'CARACTERISTIQUE' dans la base de donn�es.
     * Cette m�thode red�fint la m�thode update(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param ca Objet Caracteristique qui doit �tre mapp� pour mettre � jour la ligne correspondante dans la base
     * @return Boolean - Vrai si la mise � jour s'est d�roul�e correctement, Faux sinon
     */
	public boolean update(Caracteristique ca) {
		boolean ok = true; 
        try {
            this.connect.createStatement().executeUpdate(
                            "UPDATE caracteristique " +
                            "SET libelle_caracteristique='" +ca.getLibelle() + "'"+
                            "WHERE id_caracteristique=" + ca.getIdCar());
            }
        catch (SQLException e) {
            ok = false;
        }
        return ok;
	}

	@Override
	public boolean update(Caracteristique ancien, Caracteristique nouveau) {
		return false;
	}

    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * � la caract�ristique ca en param�tre et retourne les r�sultats sous forme d'un objet Batiment.
     * @param ca Objet Caracteristique � rechercher dans la base de donn�es
     * @return Caracteristique - Objet Caracteristique cr�� � partir des r�sultats trouv�s dans la base
     */
	public Caracteristique find(Caracteristique ca) {
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery("select * from CARACTERISTIQUE where ID_CARACTERISTIQUE = " + ca.getIdCar() );     
			if(result.first()) {
				ca.setLibelle(result.getString("LIBELLE_CARACTERISTIQUE"));
			}
			result.getStatement().close();
        }
		catch (SQLException ex) {
			ex.printStackTrace();
        }
        return ca;
	}

    /**
     * M�thode qui ex�cute une requ�te de suppression d'une caract�ristique dans la base de donn�es.
     * Cette m�thode red�finit la m�thode find(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param ca Objet Caract�ristique dont l'enregistrement correspondant dans la base doit �tre supprim�
     * @return Boolean - Vrai si la suppression s'est bien d�roul�e, Faux sinon
     */
	public boolean delete(Caracteristique ca) {
		boolean ok = true; 
        try {
            this.connect.createStatement().executeUpdate(
                            "DELETE FROM CARACTERISTIQUE " +
                            "WHERE id_caracteristique =" + ca.getIdCar());
        }
        catch (SQLException e) {
            ok = false;
        }
        return ok;
	}

    /**
     * M�thode qui :
     *      1) r�cup�re l'ensemble des caract�ristiques de la table correspondante dans la base
     *      2) les mappe en objet java Caracteristique
     *      3) les stocke dans une liste d'objets Caracteristique
     * @exception SQLException
     * @return ArrayList<Caracteristique> - Liste des caracteristiques stock�es dans la base
     */
	public ArrayList<Caracteristique> getListe() {
		Caracteristique c;
		CaracteristiqueDAO cDAO;
		ArrayList<Caracteristique> list = new ArrayList<Caracteristique>();
		try {
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM Caracteristique");
            while (result.next())
            {
            	c = new Caracteristique(result.getInt("id_caracteristique"));
            	cDAO = new CaracteristiqueDAO();
            	c = cDAO.find(c);
            	
            	list.add(c);	
            }
            result.getStatement().close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;	
	}

	@Override
	public boolean login(Caracteristique obj) {
		// TODO Auto-generated method stub
		return false;
	}	
}