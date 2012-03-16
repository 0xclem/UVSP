package persistance;
import metier.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe permettant de mapper les objets Batiment vers la table BATIMENT de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class BatimentDAO extends DAO<Batiment>{

	private static final BatimentDAO instance = new BatimentDAO();
	
    /**
     * M�thode permettant de r�cup�rer l'objet unique de type BatimentDAO
     * @return BatimentDAO - Instance unique de l'objet BatimentDAO
     */
	public final static BatimentDAO getInstance()
	{
		return instance;
	}
	
    /**
     * M�thode qui ex�cute une requ�te d'ajout d'une nouvelle mati�re dans la base de donn�es.
     * Cette m�thode red�finit la m�thode create(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param bat Objet Batiment qui doit �tre mapp� dans la base
     * @return Boolean - Vrai si l'insertion s'est d�roul�e correctement, Faux sinon
     */
	public boolean create(Batiment bat) 
	{
		boolean ok = true;
        try {
       	 
            this.connect.createStatement().executeUpdate(
                        "INSERT INTO batiment " +
                        "(id_batiment, libelle_batiment) " +
                        "VALUES (seqBatiment.nextval, '"+ bat.getLibelle()+"')");
        }
        catch (SQLException e) 
        {
       	 ok = false;
        }
        return ok;
	}

    /**
     * M�thode qui ex�cute une requ�te de mise � jour d'un enregistrement de la table 'BATIMENT' dans la base de donn�es.
     * Cette m�thode red�fint la m�thode update(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param bat Objet Batiment qui doit �tre mapp� pour mettre � jour la ligne correspondante dans la base
     * @return Boolean - Vrai si la mise � jour s'est d�roul�e correctement, Faux sinon
     */
	public boolean update(Batiment bat) 
	{
		boolean ok = true; 
        try {
        
            this.connect.createStatement().executeUpdate(
                            "UPDATE batiment " +
                            "SET libelle_batiment='" + bat.getLibelle() + "' " +
                            "WHERE id_batiment=" + bat.getIdBat());
            }
        catch (SQLException e) {
            ok = false;
        }
        return ok;
	}

	@Override
	public boolean update(Batiment ancien, Batiment nouveau) 
	{
		return false;
	}

    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * au batiment bat en param�tre et retourne les r�sultats sous forme d'un objet Batiment.
     * @param bat Objet Batiment � rechercher dans la base de donn�es
     * @return Batiment - Objet Batiment cr�� � partir des r�sultats trouv�s dans la base
     */
	public Batiment find(Batiment bat) 
	{
		try
		{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery("select * from BATIMENT where ID_BATIMENT = " + bat.getIdBat() );
			if(result.first())
			{
				bat.setLibelle(result.getString("LIBELLE_BATIMENT"));
			}
			result.getStatement().close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return bat;
	}

    /**
     * M�thode qui ex�cute une requ�te de suppression d'un batiment dans la base de donn�es.
     * Cette m�thode red�finit la m�thode find(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param bat Objet Batiment dont l'enregistrement correspondant dans la base doit �tre supprim�
     * @return Boolean - Vrai si la suppression s'est bien d�roul�e, Faux sinon
     */
	public boolean delete(Batiment bat) 
	{
		boolean ok = true; 
        try
        {   
            this.connect.createStatement().executeUpdate(
                            "DELETE FROM BATIMENT " +
                            "WHERE id_batiment =" + bat.getIdBat());
        }
        catch (SQLException e) {
            
            ok = false;
        }
        return ok;
	}

    /**
     * M�thode qui :
     *      1) r�cup�re l'ensemble des batiments de la table correspondante dans la base
     *      2) les mappe en objet java Batiment
     *      3) les stocke dans une liste d'ojbets Batiment
     * @exception SQLException
     * @return ArrayList<Batiment> - Liste des batiments stock�es dans la base
     */
	public ArrayList<Batiment> getListe() 
	{
		Batiment bat;
		BatimentDAO batDAO;
		ArrayList<Batiment> list = new ArrayList<Batiment>();
		        try {
		            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM BATIMENT");
		            while (result.next())
		            {
		            	  bat = new Batiment(result.getInt("id_batiment"));
		            	  batDAO = new BatimentDAO();
		            	  bat = batDAO.find(bat);
		            	  list.add(bat);
		            }
		            result.getStatement().close();
		        }
		        catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return list;
	}

	@Override
	public boolean login(Batiment obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
