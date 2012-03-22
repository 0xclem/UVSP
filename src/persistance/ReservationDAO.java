package persistance;
import metier.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe permettant de mapper les objets Reservation vers la table Reservation de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class ReservationDAO extends DAO<Reservation>
{

	private static final ReservationDAO instance = new ReservationDAO();

    /**
     * M�thode permettant de r�cup�rer l'objet unique de type ReservationDAO
     * @return ReservationDAO - Instance unique de l'objet ReservationDAO
     */
	public final static ReservationDAO getInstance()
	{
		return instance;
	}

    /**
     * M�thode qui ex�cute une requ�te d'ajout d'une nouvelle Reservation dans la base de donn�es.
     * Cette m�thode red�finit la m�thode create(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Reservation qui doit �tre mapp� dans la base
     * @return Boolean - Vrai si l'insertion s'est d�roul�e correctement, Faux sinon
     */
	public boolean create(Reservation res)
	{
		boolean ok = true;
        try {
        	SimpleDateFormat formatter = new SimpleDateFormat ("dd-MM-yyyy" ); 
    		String dateString = formatter.format(res.getDateResa());
        	
            this.connect.createStatement().executeUpdate("INSERT INTO Reservation (id_reservation, id_salle, id_creneau, id_enseignement, date_reservation) VALUES (seqReservation.nextval, null, " + res.getCreneau().getIdCreneau()+","+res.getEns().getIdEnseignement()+",to_date('" + dateString + "', 'dd-mm-yyyy'))");
        }
        catch (SQLException e) 
        {
       	 ok = false;
        }
        return ok;
	}

    /**
     * M�thode qui ex�cute une requ�te de mise � jour d'un enregistrement de la table 'Reservation' dans la base de donn�es.
     * Cette m�thode red�fint la m�thode update(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Reservation qui doit �tre mapp� pour mettre � jour la ligne correspondante dans la base
     * @return Boolean - Vrai si la mise � jour s'est d�roul�e correctement, Faux sinon
     */
	public boolean update(Reservation res)
	{
		boolean ok = true; 
        try {
        
        					this.connect.createStatement().executeUpdate(
                            "UPDATE Reservation " +
                            "SET id_salle=" + res.getSalle().getIdSalle()+
                            "WHERE id_reservation=" + res.getIdResa());
            
            this.connect.createStatement().executeUpdate(
                    "UPDATE Reservation " +
                    "SET id_creneau=" + res.getCreneau().getIdCreneau()+
                    "WHERE id_reservation=" + res.getIdResa());
            
            this.connect.createStatement().executeUpdate(
                    "UPDATE Reservation " +
                    "SET id_enseignement=" + res.getEns().getIdEnseignement()+
                    "WHERE id_reservation=" + res.getIdResa());
            
            this.connect.createStatement().executeUpdate(
                    "UPDATE Reservation " +
                    "SET date_reservation='" + res.getDateResa()+"'"+
                    "WHERE id_reservation=" + res.getIdResa());
         
            }
        catch (SQLException e) {
            ok = false;
        }

        return ok;
	}

	@Override
	public boolean update(Reservation ancien, Reservation nouveau) {

		return false;
	}

    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * � la Reservation m en param�tre et retourne les r�sultats sous forme d'un objet Reservation.
     * @param m Objet Reservation � rechercher dans la base de donn�es
     * @return Reservation - Objet Reservation cr�� � partir des r�sultats trouv�s dans la base
     */
	public Reservation find(Reservation res) 
	{
		Salle s;
		SalleDAO sDAO;
		Creneau c;
		CreneauDAO cDAO;
		Enseignement ens;
		EnseignementDAO ensDAO;
		Date d;
		ArrayList<Caracteristique> car;

		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
            		"SELECT * FROM Reservation WHERE id_reservation =" +res.getIdResa());
            	
      
			if(result.first())
			{
				s = new Salle(result.getInt("id_salle"));
	            sDAO = new SalleDAO();
	            s = sDAO.find(s);

	            c = new Creneau(result.getInt("id_creneau"));
	            cDAO = new CreneauDAO();
	            c = cDAO.find(c);

	            ens = new Enseignement(result.getInt("id_enseignement"));
	            ensDAO = new EnseignementDAO();
	            ens = ensDAO.find(ens);

	            d = (result.getDate("date_reservation"));

	            res.setSalle(s);
	            res.setCreneau(c);
	            res.setEns(ens);
	            res.setDateResa(d);
	            result.getStatement().close();
	           result.close(); 
			} 


			 result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"select rc.id_caracteristique from Reservation r, reservation_caracteristique rc where r.id_reservation ="+ res.getIdResa()+" and r.id_reservation = rc.id_reservation");
            	
			 car = new ArrayList<Caracteristique>();

	            while(result.next())
	            {
	            	CaracteristiqueDAO caDAO= new CaracteristiqueDAO();
	            	Caracteristique ca = new Caracteristique(result.getInt("id_caracteristique"));
	            	ca = caDAO.find(ca);
	            	car.add(ca);	
	            }

	          res.setCarResa(car);

	          result.getStatement().close();
	          result.close();
             
        } catch (SQLException ex) 
        {
            
      
        }
        return res;

	}

    /**
     * M�thode qui ex�cute une requ�te de suppression d'une Reservation dans la base de donn�es.
     * Cette m�thode red�finit la m�thode find(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param res Objet Reservation dont l'enregistrement correspondant dans la base doit �tre supprim�
     * @return Boolean - Vrai si la suppression s'est bien d�roul�e, Faux sinon
     */
	public boolean delete(Reservation res)
	{
		 boolean ok = true; 
	        try
	        {
	            this.connect.createStatement().executeUpdate(
	                            "DELETE FROM RESERVATION " +
	                            "WHERE id_reservation =" + res.getIdResa());
	        }
	        catch (SQLException e) {

	            ok = false;
	        }

	        return ok;
	}

    /**
     * M�thode qui :
     *      1) r�cup�re l'ensemble des Reservation de la table correspondante dans la base
     *      2) les mappe en objet java Reservation
     *      3) les stocke dans une liste d'objets Reservation
     * @exception SQLException
     * @return ArrayList<Reservation> - Liste des Reservation stock�es dans la base
     */
	public ArrayList<Reservation> getListe() 
	{
		Reservation res;
		ReservationDAO resDAO;
		 ArrayList<Reservation> list = new ArrayList<Reservation>();

	        try {

	            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM Reservation");

	            while (result.next())
	            {
	            	  res = new Reservation(result.getInt("id_reservation"));
	            	  resDAO = new ReservationDAO();
	            	  res = resDAO.find(res);
	            	  list.add(res);
	            }
	            result.getStatement().close();
		          result.close();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;


	}

	@Override
	public boolean login(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}	
}