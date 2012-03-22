package persistance;

import metier.TypeCours;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe permettant de mapper les objets TypeCours vers la table TYPECOURS de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class TypeCoursDAO extends DAO<TypeCours> {

    private static final TypeCoursDAO instance = new TypeCoursDAO();

    /**
     * M�thode permettant de r�cup�rer l'objet unique de type TypeCoursDAO
     * @return TypeCoursDAO - Instance unique de l'objet TypeCoursDAO
     */
    public final static TypeCoursDAO getInstance() {
        return instance;
    }

    /**
     * M�thode qui ex�cute une requ�te d'ajout d'une nouvelle TypeCours dans la base de donn�es.
     * Cette m�thode red�finit la m�thode create(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param instance Objet TypeCours qui doit �tre mapp� dans la base
     * @return Boolean - Vrai si l'insertion s'est d�roul�e correctement, Faux sinon
     */
    public boolean create(TypeCours instance) {
    	boolean ok = false;
    	try {
    		PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO TYPECOURS(ID_TYPE_DE_COURS, LIBELLE_TYPE_DE_COURS) VALUES(?,?)");
    		prepare.setString(1, "seqEnseignant.nextval");
    		prepare.setString(2, instance.getNomTypeCours());
    		prepare.executeUpdate();
    		ok = true;
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
	    }
            return ok;
    }

    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * � la TypeCours instance en param�tre et retourne les r�sultats sous forme d'un objet TypeCours.
     * @param instance Objet TypeCours � rechercher dans la base de donn�es
     * @return TypeCours - Objet TypeCours cr�� � partir des r�sultats trouv�s dans la base
     */
	public TypeCours find(TypeCours instance) {
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery("select * from TYPECOURS where ID_TYPE_DE_COURS = " + instance.getIdTypeCours() );
			if(result.first())
			{
				instance.setNomTypeCours(result.getString("LIBELLE_TYPE_DE_COURS"));
			}
			result.getStatement().close();
	        result.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return instance;
	}

    /**
     * M�thode qui ex�cute une requ�te de mise � jour d'un enregistrement de la table 'TypeCours' dans la base de donn�es.
     * Cette m�thode red�fint la m�thode update(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param type Objet TypeCours qui doit �tre mapp� pour mettre � jour la ligne correspondante dans la base
     * @return Boolean - Vrai si la mise � jour s'est d�roul�e correctement, Faux sinon
     */
	public boolean update(TypeCours type) {
		boolean resultat = false;
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("update TYPECOURS set LIBELLE_TYPE_DE_COURS=? where ID_TYPE_DE_COURS=?");
                        prepare.setString(1, type.getNomTypeCours());
                        prepare.setInt(7, type.getIdTypeCours());
                        prepare.executeUpdate();
                        resultat=true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

    /**
     * M�thode qui ex�cute une requ�te de suppression d'une TypeCours dans la base de donn�es.
     * Cette m�thode red�finit la m�thode find(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param type Objet TypeCours dont l'enregistrement correspondant dans la base doit �tre supprim�
     * @return Boolean - Vrai si la suppression s'est bien d�roul�e, Faux sinon
     */
	public boolean delete(TypeCours type) {
		boolean resultat=false;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("DELETE FROM TYPECOURS where ID_TYPE_DE_COURS=?");
			prepare.setInt(1, type.getIdTypeCours());
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
     *      1) r�cup�re l'ensemble des TypeCours de la table correspondante dans la base
     *      2) les mappe en objet java TypeCours
     *      3) les stocke dans une liste d'objets TypeCours
     * @exception SQLException
     * @return ArrayList<TypeCours> - Liste des TypeCours stock�es dans la base
     */
	public ArrayList<TypeCours> getListe() {
		ArrayList<TypeCours> list = new ArrayList<TypeCours>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
					.executeQuery("select * from TYPECOURS");
			while (result.next())
			{
				TypeCours type = new TypeCours(result.getInt("ID_TYPE_DE_COURS"),result.getString("LIBELLE_TYPE_DE_COURS"));
				list.add(type);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean update(TypeCours ancien, TypeCours nouveau) {
		return false;
	}

	@Override
	public boolean login(TypeCours obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
