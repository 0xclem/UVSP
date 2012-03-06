package persistance;

import metier.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant de mapper les objets Cours vers la table COURS de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class CoursDAO extends DAO<Cours> {
    
    private static final CoursDAO instance = new CoursDAO();
    
    /**
     * M�thode permettant de r�cup�rer l'objet unique de type CoursDAO
     * @return CoursDAO - Instance unique de l'objet CoursDAO
     */
    public final static CoursDAO getInstance() {
        return instance;
    }

    /**
     * M�thode qui ex�cute une requ�te d'ajout d'une nouvelle mati�re dans la base de donn�es.
     * Cette m�thode red�finit la m�thode create(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Mati�re qui doit �tre mapp� dans la base
     * @return Boolean - Vrai si l'insertion s'est d�roul�e correctement, Faux sinon
     */
    public boolean create(Cours c) {
        boolean ok = true;
        try {
        	PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO COURS VALUES(?, ?, ?, ?)");
        	prepare.setString(1, "seqCours.nextval");
            prepare.setInt(2, c.getMatiere().getIdMat());
            prepare.setInt(3, c.getTypeCours().getIdTypeCours());
            prepare.setString(4, c.getLibelle());
            prepare.executeUpdate();
        }
        catch (SQLException e) {
            ok = false;
        }
        return ok;
    }

    /**
     * M�thode qui ex�cute une requ�te de mise � jour d'un enregistrement de la table 'COURS' dans la base de donn�es.
     * Cette m�thode red�fint la m�thode update(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Cours qui doit �tre mapp� pour mettre � jour la ligne correspondante dans la base
     * @return Boolean - Vrai si la mise � jour s'est d�roul�e correctement, Faux sinon
     */
    public boolean update(Cours c) {
        boolean ok = true;
        try {
            this.connect.createStatement().executeUpdate(
                            "UPDATE COURS " +
                            "SET ID_MATIERE='" + c.getMatiere().getIdMat() + "' " +
                            "WHERE ID_COURS=" + c.getIdCours());
            
            this.connect.createStatement().executeUpdate(
                            "UPDATE COURS " +
                            "SET ID_TYPE_DE_COURS='" + c.getTypeCours().getIdTypeCours() + "' " +
                            "WHERE ID_COURS=" + c.getIdCours());

            this.connect.createStatement().executeUpdate(
                            "UPDATE COURS " +
                            "SET LIBELLE_COURS='" + c.getLibelle() + "' " +
                            "WHERE ID_COURS=" + c.getIdCours());
        } catch (SQLException e) {
            ok = false;
        }
        return ok;
    }

    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * � la cours m en param�tre et retourne les r�sultats sous forme d'un objet Cours.
     * @param m Objet Cours � rechercher dans la base de donn�es
     * @return Cours - Objet Cours cr�� � partir des r�sultats trouv�s dans la base
     */
    public Cours find(Cours c) {
        Cours cou = new Cours(c.getIdCours());
        try {
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM COURS " + "WHERE ID_COURS = '" + c.getIdCours() + "'");
            if (result.first()) {
            	MatiereDAO matDAO = new MatiereDAO();
                Matiere mat = new Matiere(result.getInt("ID_MATIERE"));
                mat = matDAO.find(mat);
                
                TypeCoursDAO typeDAO = new TypeCoursDAO();
                TypeCours type = new TypeCours(result.getInt("ID_TYPE_DE_COURS"));
                type = typeDAO.find(type);
                
                cou.setLibelle(result.getString("LIBELLE_MATIERE"));
                cou.setMatiere(mat);
                cou.setTypeCours(type);
            }
        }
        catch (SQLException ex) {
            cou = c;
        }
        return cou;
    }

    /**
     * M�thode qui ex�cute une requ�te de suppression d'une cours dans la base de donn�es.
     * Cette m�thode red�finit la m�thode find(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Cours dont l'enregistrement correspondant dans la base doit �tre supprim�
     * @return Boolean - Vrai si la suppression s'est bien d�roul�e, Faux sinon
     */
    public boolean delete(Cours c) {
        boolean ok = true;
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM COURS " + "WHERE ID_COURS ='" + c.getIdCours() + "'");
        }
        catch (SQLException e) {
            ok = false;
        }
        return ok;
    }

    /**
     * M�thode qui :
     *      1) r�cup�re l'ensemble des mati�res de la table correspondante dans la base
     *      2) les mappe en objet java Matiere
     *      3) les stocke dans une liste d'ojbets Matiere
     * @exception SQLException
     * @return ArrayList<Matiere> - Liste des mati�res stock�es dans la base
     */
    public ArrayList<Cours> getListe() {
        ArrayList<Cours> list = new ArrayList<Cours>();
        try {
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM COURS");
            while (result.next()) {
            	MatiereDAO matDAO = new MatiereDAO();
                Matiere mat = new Matiere(result.getInt("ID_MATIERE"));
                mat = matDAO.find(mat);
                
                TypeCoursDAO typeDAO = new TypeCoursDAO();
                TypeCours type = new TypeCours(result.getInt("ID_TYPE_DE_COURS"));
                type = typeDAO.find(type);
                
                Cours c = new Cours(result.getInt("ID_COURS"), result.getString("LIBELLE_COURS"), mat, type);
                list.add(c);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * M�thode qui permet de retourner la liste des cours associ�es � la mati�re
     * dont le code est en param�tre. Les objets Cours de la liste sont renseign�s
     * par tous leurs attributs.
     * @param mat ID de la mati�re pour laquelle on veut les cours associ�es
     * @return ArrayList<Cours> - Liste des objets Cours associ�s � la mati�re en param�tre
     */
    public ArrayList<Cours> getCoursByMatiere (Integer mat) {
        ArrayList<Cours> list = new ArrayList<Cours>();
        try {
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM COURS WHERE ID_MATIERE = '" + mat + "'");
            while (result.next()) {
            	MatiereDAO matDAO = new MatiereDAO();
                Matiere m = new Matiere(result.getInt("ID_MATIERE"));
                m = matDAO.find(m);
                
                TypeCoursDAO typeDAO = new TypeCoursDAO();
                TypeCours type = new TypeCours(result.getInt("ID_TYPE_DE_COURS"));
                type = typeDAO.find(type);
                
                Cours c = new Cours(result.getInt("ID_COURS"), result.getString("LIBELLE_COURS"), m, type);
                list.add(c);
            }
        }
        catch (SQLException e) {
        	list.clear();
        }
        return list;
    }

    public boolean update(Cours ancien, Cours nouveau) {
        return false;
    }
}
