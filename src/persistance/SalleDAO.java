package persistance;
import metier.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant de mapper les objets Salle vers la table Salle de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class SalleDAO extends DAO<Salle>{

	
	private static final SalleDAO instance = new SalleDAO();
	
    /**
     * M�thode permettant de r�cup�rer l'objet unique de type SalleDAO
     * @return SalleDAO - Instance unique de l'objet SalleDAO
     */
	public final static SalleDAO getInstance()
	{
		return instance;
	}
	
	public boolean login(Salle sal){ return false;}

    /**
     * M�thode qui ex�cute une requ�te d'ajout d'une nouvelle Salle dans la base de donn�es.
     * Cette m�thode red�finit la m�thode create(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Salle qui doit �tre mapp� dans la base
     * @return Boolean - Vrai si l'insertion s'est d�roul�e correctement, Faux sinon
     */
	public boolean create(Salle sal)
	{
		 boolean ok = true;
         try {
        	 
             this.connect.createStatement().executeUpdate(
                         "INSERT INTO salle " +
                         "(id_salle, id_batiment, numero_salle) " +
                         "VALUES (seqSalle.nextval, "+ sal.getBatiment().getIdBat()+ ",'"
                                     + sal.getLibelle()+"')");
         }
         catch (SQLException e) 
         {
        	 ok = false;
         }
         return ok;
	}

    /**
     * M�thode qui ex�cute une requ�te de mise � jour d'un enregistrement de la table 'Salle' dans la base de donn�es.
     * Cette m�thode red�fint la m�thode update(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Salle qui doit �tre mapp� pour mettre � jour la ligne correspondante dans la base
     * @return Boolean - Vrai si la mise � jour s'est d�roul�e correctement, Faux sinon
     */
	public boolean update(Salle sal) {
		boolean ok = true; 
        try {
        
            this.connect.createStatement().executeUpdate(
                            "UPDATE salle " +
                            "SET numero_salle='" + sal.getLibelle() + "' " +
                            "WHERE id_salle=" + sal.getIdSalle());

            }
        catch (SQLException e) {
            ok = false;
        }

        return ok;
	}

	@Override
	public boolean update(Salle ancien, Salle nouveau) {
		
		return false;
	}

    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * � la Salle m en param�tre et retourne les r�sultats sous forme d'un objet Salle.
     * @param m Objet Salle � rechercher dans la base de donn�es
     * @return Salle - Objet Salle cr�� � partir des r�sultats trouv�s dans la base
     */
	public Salle find(Salle sal) {
		BatimentDAO batDAO;
		Batiment bat;
		ArrayList<Caracteristique> car;
		try {
            
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery("select * from SALLE where ID_SALLE = " + sal.getIdSalle());
            if (result.first()) {
            	batDAO = new BatimentDAO();
            	bat = new Batiment(result.getInt("ID_BATIMENT"));
            	bat = batDAO.find(bat);
            	sal.setIdSalle(result.getInt("ID_SALLE"));
            	sal.setLibelle(result.getString("NUMERO_SALLE"));
            }
            result.getStatement().close();
	          result.close();
            result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
    				.executeQuery("SELECT ID_CARACTERISTIQUE from salle s, caracteristique_salle cs WHERE s.id_salle = " + sal.getIdSalle() +" and s.id_salle = cs.id_salle");
            
            car = new ArrayList<Caracteristique>();
            if (result.first()) {
            	CaracteristiqueDAO cDAO= new CaracteristiqueDAO();
            	Caracteristique c = new Caracteristique(result.getInt("ID_CARACTERISTIQUE"));
            	c = cDAO.find(c);
            	
            	car.add(c);	
            	while(result.next())
                {
                	cDAO= new CaracteristiqueDAO();
                	c = new Caracteristique(result.getInt("ID_CARACTERISTIQUE"));
                	c = cDAO.find(c);
                	
                	car.add(c);	
                }
            }
            sal.setCarSalle(car);
            result.getStatement().close();
	          result.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return sal;
	}

    /**
     * M�thode qui ex�cute une requ�te de suppression d'une Salle dans la base de donn�es.
     * Cette m�thode red�finit la m�thode find(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param sal Objet Salle dont l'enregistrement correspondant dans la base doit �tre supprim�
     * @return Boolean - Vrai si la suppression s'est bien d�roul�e, Faux sinon
     */
	public boolean delete(Salle sal) {
		 boolean ok = true; 
	        try
	        {
	            
	            this.connect.createStatement().executeUpdate(
	                            "DELETE FROM salle " +
	                            "WHERE id_salle =" + sal.getIdSalle());
	        }
	        catch (SQLException e) {
	            
	            ok = false;
	        }

	        return ok;
	}

    /**
     * M�thode qui :
     *      1) r�cup�re l'ensemble des Salle de la table correspondante dans la base
     *      2) les mappe en objet java Salle
     *      3) les stocke dans une liste d'objets Salle
     * @exception SQLException
     * @return ArrayList<Salle> - Liste des Salle stock�es dans la base
     */
	public ArrayList<Salle> getListe() 
	{
		BatimentDAO batDAO;
		Batiment bat;
		ArrayList<Salle> list = new ArrayList<Salle>(); 
		ArrayList<Caracteristique> car;
		 
	        try {
	            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM SALLE ORDER BY NUMERO_SALLE ASC");
	            ResultSet result2;

	            while (result.next())
	            {
	            	result2 = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
	        				.executeQuery("SELECT ID_CARACTERISTIQUE from salle s, caracteristique_salle cs WHERE s.id_salle = " + result.getInt("ID_SALLE") +" and s.id_salle = cs.id_salle");
	                
	                car = new ArrayList<Caracteristique>();
	                if (result2.first()) {
	                	CaracteristiqueDAO cDAO= new CaracteristiqueDAO();
	                	Caracteristique c = new Caracteristique(result2.getInt("ID_CARACTERISTIQUE"));
	                	c = cDAO.find(c);
	                	
	                	car.add(c);	
	                	while(result2.next())
	                    {
	                    	cDAO= new CaracteristiqueDAO();
	                    	c = new Caracteristique(result2.getInt("ID_CARACTERISTIQUE"));
	                    	c = cDAO.find(c);
	                    	
	                    	car.add(c);	
	                    }
	                }
	            	batDAO = new BatimentDAO();
	            	bat = new Batiment(result.getInt("ID_BATIMENT"));
	            	bat = batDAO.find(bat);
	            	list.add(new Salle(result.getInt("ID_SALLE"), result.getString("NUMERO_SALLE"), bat, car));
	            }
	            result.getStatement().close();
		          result.close();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
		
	}	
}