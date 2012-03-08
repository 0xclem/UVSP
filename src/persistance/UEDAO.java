package persistance;

/**
 * Classe permettant de mapper les objets UE vers la table 'ue' de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
import metier.UE;
import metier.Enseignant;
import persistance.EnseignantDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UEDAO extends DAO<UE> {

    private static final UEDAO instance = new UEDAO();

    /**
     * M�thode permettant de r�cup�rer l'objet unique de type UEDAO
     * @return UEDAO - Instance unique de l'objet UEDAO
     */
    public final static UEDAO getInstance() {
        return instance;
    }

    /**
     * M�thode qui ex�cute une requ�te d'ajout d'une nouvelle UE dans la base de donn�es.
     * Cette m�thode red�finit la m�thode create(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param u Objet UE qui doit �tre mapp� dans la base
     * @return Boolean - Vrai si l'insertion s'est d�roul�e correctement, Faux sinon
     */
    public boolean create(UE u) {
        boolean ok;
        try {
            PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO UE VALUES(?, ?, ?)");
            prepare.setString(1, "seqUE.nextval");
            prepare.setString(2, u.getNomUE());
            prepare.setInt(3, u.getEnseignant().getIdEns());
            prepare.executeUpdate();
            ok = true;
        } catch (SQLException e) {
            ok = false;
        }
        return ok;
    }

    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * � l'UE u en param�tre et retourne les r�sultats sous forme d'un objet UE.
     * @param u Objet UE � rechercher dans la base de donn�es
     * @return UE - Objet UE cr�� � partir des r�sultats trouv�s dans la base
     */
    public UE find(UE u) {
        try {
        	ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery("select * from UE where ID_UE = " + u.getIdUE() );
        	if (result.first()) {
        		EnseignantDAO ensDAO = new EnseignantDAO();
        		Enseignant ens = new Enseignant(result.getInt("ID_ENSEIGNANT"));
        		ens = ensDAO.find(ens);
                u.setNomUE(result.getString("LIBELLE_UE"));
                u.setEnseignant(ens);
        	}
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return u;
    }

    /**
     * M�thode qui ex�cute une requ�te de mise � jour d'un enregistrement de la table 'ue' dans la base de donn�es.
     * Cette m�thode red�fint la m�thode update(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param u Objet UE qui doit �tre mapp� pour mettre � jour la ligne correspondante dans la base
     * @return Boolean - Vrai si la mise � jour s'est d�roul�e correctement, Faux sinon
     */
    public boolean update(UE u) {
        boolean ok = true;
        try {
            this.connect.createStatement().executeUpdate(
                            "UPDATE ue " +
                            "SET LIBELLE_UE='" + u.getNomUE() + "' " +
                            "WHERE ID_UE='" + u.getIdUE() + "'");
            
            this.connect.createStatement().executeUpdate(
                            "UPDATE ue " +
                            "SET ID_ENSEIGNANT='" + u.getEnseignant().getIdEns() + "' " +
                            "WHERE ID_UE='" + u.getIdUE() + "'");
        } catch (SQLException e) {
            ok = false;
        }
        return ok;
    }

    public boolean update(UE u1, UE u2) {
    	return false;
    }

    /**
     * M�thode qui ex�cute une requ�te de suppression d'une UE dans la base de donn�es.
     * Cette m�thode red�finit la m�thode find(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param u Objet UE dont l'enregistrement correspondant dans la base doit �tre supprim�
     * @return Boolean - Vrai si la suppression s'est bien d�roul�e, Faux sinon
     */
    public boolean delete(UE u) {
        boolean resultat;
        try {
            PreparedStatement prepare = this.connect.prepareStatement("DELETE FROM UE where ID_UE=?");
            prepare.setInt(1, u.getIdUE());
            prepare.executeUpdate();
            resultat = true;
        } catch (SQLException e) {
            resultat = false;
        }
        return resultat;
    }

    /**
     * M�thode qui permet de retourner la liste des UEs stock�es dans la base
     * @exception SQLException
     * @return ArrayList<UE> - Liste des UEs stock�es dans la base
     */
    public ArrayList<UE> getListe() {
        ArrayList<UE> list = new ArrayList<UE>();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("select * from UE");
            while (result.next()) {
            	Enseignant ens =  new Enseignant(result.getInt("ID_ENSEIGNANT"));
            	UE u = new UE(
                        result.getInt("ID_UE"),
                        result.getString("LIBELLE_UE"),
                		ens);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}