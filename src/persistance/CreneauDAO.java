package persistance;

import metier.Creneau;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe permettant de mapper les objets Creneau vers la table CRENAUX de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class CreneauDAO extends DAO<Creneau> {

    private static final CreneauDAO instance = new CreneauDAO();

    /**
     * M�thode permettant de r�cup�rer l'objet unique de type CreneauDAO
     * @return CreneauDAO - Instance unique de l'objet CreneauDAO
     */
    public final static CreneauDAO getInstance() {
        return instance;
    }

    /**
     * M�thode permettant de cr�er un cr�neau en base de donn�e
     */
    public boolean create(Creneau instance) {
    	boolean ok = false;
    	try {
    		PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO CRENAUX(ID_CRENAU, HEURE_DEBUT, HEURE_FIN) VALUES(?,?,?)");
    		prepare.setString(1, "seqCreneau.nextval");
    		prepare.setString(2, instance.getHeureDeb());
    		prepare.setString(3, instance.getHeureFin());
    		prepare.executeUpdate();
    		ok = true;
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
	    }
            return ok;
    }

    /**
     * M�thode qui ex�cute une requ�te de mise � jour d'un enregistrement de la table 'CRENEAU' dans la base de donn�es.
     * Cette m�thode red�fint la m�thode update(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param creneau Objet Creneau qui doit �tre mapp� pour mettre � jour la ligne correspondante dans la base
     * @return Boolean - Vrai si la mise � jour s'est d�roul�e correctement, Faux sinon
     */
	public boolean update(Creneau creneau) {
		boolean resultat = false;
		try {
            this.connect.createStatement().executeUpdate(
                    "UPDATE CRENAUX " +
                    "SET HEURE_DEBUT='" + creneau.getHeureDeb() + "' " +
                    "WHERE ID_CRENAU=" + creneau.getIdCreneau());
    
            this.connect.createStatement().executeUpdate(
                    "UPDATE CRENAUX " +
                    "SET HEURE_FIN='" + creneau.getHeureFin() + "' " +
                    "WHERE ID_CRENAU=" + creneau.getIdCreneau());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * au cr�neau bat en param�tre et retourne les r�sultats sous forme d'un objet Creneau.
     * @param c Objet Creneau � rechercher dans la base de donn�es
     * @return Creneau - Objet Creneau cr�� � partir des r�sultats trouv�s dans la base
     */
	public Creneau find(Creneau c) {
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery("select * from CRENAUX where ID_CRENEAU = " + c.getIdCreneau() );
			if(result.first())
			{
				c.setHeureDeb(result.getString("HEURE_DEBUT"));
				c.setHeureFin(result.getString("HEURE_FIN"));
			}
			result.getStatement().close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

    /**
     * M�thode qui ex�cute une requ�te de suppression d'un cr�neau dans la base de donn�es.
     * Cette m�thode red�finit la m�thode find(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param creneau Objet Creneau dont l'enregistrement correspondant dans la base doit �tre supprim�
     * @return Boolean - Vrai si la suppression s'est bien d�roul�e, Faux sinon
     */
	public boolean delete(Creneau creneau) {
		boolean resultat=false;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("DELETE FROM CRENAUX where ID_CRENAU=?");
			prepare.setInt(1, creneau.getIdCreneau());
			prepare.executeUpdate();
			resultat=true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

    /**
     * M�thode qui :
     *      1) r�cup�re l'ensemble des cr�neaux de la table correspondante dans la base
     *      2) les mappe en objet java Creneau
     *      3) les stocke dans une liste d'objets Creneau
     * @exception SQLException
     * @return ArrayList<Creneau> - Liste des cr�neaux stock�es dans la base
     */
	public ArrayList<Creneau> getListe() {
		ArrayList<Creneau> list = new ArrayList<Creneau>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery("select * from CRENAUX");
			while (result.next())
			{
				Creneau creneau = new Creneau(result.getInt("ID_CRENAU"), result.getString("HEURE_DEBUT"), result.getString("HEURE_FIN"));
				list.add(creneau);
			}
			result.getStatement().close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean update(Creneau ancien, Creneau nouveau) {
		return false;
	}

	@Override
	public boolean login(Creneau obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
