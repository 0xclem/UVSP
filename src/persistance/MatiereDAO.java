package persistance;

import metier.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant de mapper les objets Matiere vers la table mati�re de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class MatiereDAO extends DAO<Matiere> {
    
    private static final MatiereDAO instance = new MatiereDAO();
    
    /**
     * M�thode permettant de r�cup�rer l'objet unique de type MatiereDAO
     * @return MatiereDAO - Instance unique de l'objet MatiereDAO
     */
    public final static MatiereDAO getInstance() {
        return instance;
    }

    /**
     * M�thode qui ex�cute une requ�te d'ajout d'une nouvelle mati�re dans la base de donn�es.
     * Cette m�thode red�finit la m�thode create(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Mati�re qui doit �tre mapp� dans la base
     * @return Boolean - Vrai si l'insertion s'est d�roul�e correctement, Faux sinon
     */
    public boolean create(Matiere m) {
        boolean ok = true;
        try {
        	PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO MATIERE VALUES(?, ?, ?, ?)");
            prepare.setInt(1, m.getIdMat());
            prepare.setInt(2, m.getUEMat().getIdUE());
            prepare.setInt(3, m.getResponsable().getIdEns());
            prepare.setString(4, m.getNomMat());
            prepare.executeUpdate();
        }
        catch (SQLException e) {
            ok = false;
        }
        return ok;
    }

    /**
     * M�thode qui ex�cute une requ�te de mise � jour d'un enregistrement de la table 'matiere' dans la base de donn�es.
     * Cette m�thode red�fint la m�thode update(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Matiere qui doit �tre mapp� pour mettre � jour la ligne correspondante dans la base
     * @return Boolean - Vrai si la mise � jour s'est d�roul�e correctement, Faux sinon
     */
    public boolean update(Matiere m) {
        boolean ok = true;
        try {
            this.connect.createStatement().executeUpdate(
                            "UPDATE matiere " +
                            "SET ID_UE='" + m.getUEMat().getIdUE() + "' " +
                            "WHERE ID_MATIERE=" + m.getIdMat());
            
            this.connect.createStatement().executeUpdate(
                            "UPDATE matiere " +
                            "SET LIBELLE_MATIERE='" + m.getNomMat() + "' " +
                            "WHERE ID_MATIERE=" + m.getIdMat());

            this.connect.createStatement().executeUpdate(
                            "UPDATE matiere " +
                            "SET ID_ENSEIGNANT='" + m.getResponsable().getIdEns() + "' " +
                            "WHERE ID_MATIERE=" + m.getIdMat());
        } catch (SQLException e) {
            ok = false;
        }
        return ok;
    }

    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * � la mati�re m en param�tre et retourne les r�sultats sous forme d'un objet Matiere.
     * @param m Objet Matiere � rechercher dans la base de donn�es
     * @return Matiere - Objet Matiere cr�� � partir des r�sultats trouv�s dans la base
     */
    public Matiere find(Matiere m) {
        Matiere mat = new Matiere(m.getIdMat());
        try {
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM MATIERE " + "WHERE ID_MATIERE = '" + m.getIdMat() + "'");
            if (result.first()) {
                UE u = new UE(result.getInt("ID_UE"));
                Enseignant ens = new Enseignant(result.getInt("ID_ENSEIGNANT"));
                m.setNomMat(result.getString("LIBELLE_MATIERE"));
                m.setUEMat(u);
                m.setResponsable(ens);
            }
        }
        catch (SQLException ex) {
            mat = m;
        }
        return mat;
    }

    /**
     * M�thode qui ex�cute une requ�te de suppression d'une mati�re dans la base de donn�es.
     * Cette m�thode red�finit la m�thode find(T obj) de la superclasse DAO.
     * @exception SQLException
     * @param m Objet Matiere dont l'enregistrement correspondant dans la base doit �tre supprim�
     * @return Boolean - Vrai si la suppression s'est bien d�roul�e, Faux sinon
     */
    public boolean delete(Matiere m) {
        boolean ok = true;
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM MATIERE " + "WHERE ID_MATIERE ='" + m.getIdMat() + "'");
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
    public ArrayList<Matiere> getListe() {
        ArrayList<Matiere> list = new ArrayList<Matiere>();
        try {
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM MATIERE");
            while (result.next()) {
                UE u = new UE(result.getString("ID_UE"));
                Enseignant ens = new Enseignant(result.getInt("ID_ENSEIGNANT"));
                Matiere m = new Matiere(result.getInt("ID_MATIERE"), result.getString("LIBELLE_MATIERE"), u, ens);
                list.add(m);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * M�thode qui permet de retourner la liste des mati�res associ�es � l'UE
     * dont le code est en param�tre. Les objets Matiere de la liste sont renseign�s
     * par tous leurs attributs.
     * @param ue Code de l'UE pour laquelle on veut les mati�res associ�es
     * @return ArrayList<Matiere> - Liste des objets Matiere associ�s � l'UE en param�tre
     */
    public ArrayList<Matiere> getMatiereByUE (Integer ue) {
        ArrayList<Matiere> list = new ArrayList<Matiere>();
        try {
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM MATIERE WHERE ID_UE = '" + ue + "'");
            while (result.next()) {
                UE u = new UE(result.getString("ID_UE"));
                Enseignant ens = new Enseignant(result.getInt("ID_ENSEIGNANT"));
                Matiere m = new Matiere(result.getInt("ID_MATIERE"), result.getString("LIBELLE_MATIERE"), u, ens);
                list.add(m);
            }
        }
        catch (SQLException e) {
        	list.clear();
        }
        return list;
    }

    public boolean update(Matiere ancien, Matiere nouveau) {
        return false;
    }
}
