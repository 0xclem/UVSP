package persistance;
import metier.*;

import java.sql.*;
import java.util.ArrayList;


public class AnneeEtudeDAO extends DAO<AnneeEtude>{

	private static final AnneeEtudeDAO instance = new AnneeEtudeDAO();
	
	public final static AnneeEtudeDAO getInstance()
	{
		return instance;
	}
	
	
	@Override
	public boolean create(AnneeEtude ae) {
		 boolean ok = true; // Vaut True si l'insertion s'est d�roul�e correctement, False sinon
	        try {
	                // Cr�ation et ex�cution de la requ�te d'ajout d'une nouvelle ann�e d'�tude
	                PreparedStatement prepare = this.connect.prepareStatement(
	                    "INSERT INTO annee_etude VALUES(?, ?, ?, ?, ?)");
	            prepare.setString(1, ae.getNomAnnee());
	            prepare.setString(3, ae.getDescription());
	            prepare.setInt(4, ae.getDureeSeance());
	            prepare.setInt(5, ae.getNbSeanceAM());
	            prepare.setInt(6, ae.getNbSeancePM());

	            prepare.executeUpdate();
	            }
	        catch (SQLException e) {
	            // Si exception, on indique que l'insertion ne s'est pas d�roul�e correctement
	            ok = false;
	        }
	        // L'ajout s'est bien d�roul� si 1 ligne a �t� cr��e dans la base
	        return ok;

	}

	@Override
	public boolean update(AnneeEtude ae) {
		boolean ok = true; // Vaut True si la mise � jour s'est correctement d�roul�e, False sinon
        try {
           
            // Exc�cution de la requ�te de mise � jour de la description de l'ann�e d'�tude ae
            this.connect.createStatement().executeUpdate(
                            "UPDATE annee_etude " +
                            "SET description='" + ae.getDescription() + "' " +
                            "WHERE nom_annee_etude='" + ae.getNomAnnee() + "'");

            // Exc�cution de la requ�te de mise � jour de la dur�e des s�ances de l'ann�e d'�tude ae
            this.connect.createStatement().executeUpdate(
                            "UPDATE annee_etude " +
                            "SET duree_seance=" + ae.getDureeSeance() + " " +
                            "WHERE nom_annee_etude='" + ae.getNomAnnee() + "'");

            // Exc�cution de la requ�te de mise � jour du nombre de s�ances le matin de l'ann�e d'�tude ae
            this.connect.createStatement().executeUpdate(
                            "UPDATE annee_etude " +
                            "SET nb_seances_am=" + ae.getNbSeanceAM() + " " +
                            "WHERE nom_annee_etude='" + ae.getNomAnnee() + "'");

            // Exc�cution de la requ�te de mise � jour du nombre de s�ances l'apr�s midi de l'ann�e d'�tude ae
            this.connect.createStatement().executeUpdate(
                            "UPDATE annee_etude " +
                            "SET nb_seances_pm=" + ae.getNbSeancePM() + " " +
                            "WHERE nom_annee_etude='" + ae.getNomAnnee() + "'");
        } catch (SQLException e) {
            // Si exception, on indique que la mise � jour ne s'est pas d�roul�e correctement
            ok = false;
        }

        return ok;

	}

	@Override
	public boolean update(AnneeEtude ancien, AnneeEtude nouveau) {
		return false;
	}

	@Override
	public AnneeEtude find(AnneeEtude ae) {
		
		ArrayList<AnneeUniversitaire> list = new ArrayList<AnneeUniversitaire>(); // Liste des ann�es universitaires
        AnneeEtude annee = null;
        ResultSet result; // R�sultat des requ�tes

        try {
            // Requ�te de s�lection des informations dans la table annee_etude
            result = this.connect.createStatement().executeQuery(
                        "SELECT * FROM annee_etude " +
                        "WHERE nom_annee_etude = '" + ae.getNomAnnee() + "'");  

            if (result.first()) {

                // Cr�ation de l'objet AnneeEtude avec les informations trouv�es dans la base
               
                annee = new AnneeEtude (result.getString("nom_annee_etude"),
                                        result.getString("description"),
                                        result.getInt("duree_seance"),
                                        result.getInt("nb_seances_am"),
                                        result.getInt("nb_seances_pm"));
                
                // Requ�te de s�lection des ann�es universitaires associ�es � l'ann�e d'�tude
                result = this.connect.createStatement().executeQuery(
                            "SELECT nom_annee_univ FROM conge " +
                            "WHERE nom_annee_etude = '" + ae.getNomAnnee() + "' " +
                            "GROUP BY nom_annee_univ");

                // Stockage des enregistrements dans une liste d'ann�es universitaires
                while (result.next()) {
                    list.add(new AnneeUniversitaire (result.getString("nom_annee_univ")));
                }

                // Mise � jour de l'ann�e d'�tude avec la liste des ann�es universitaires
                annee.setAnnneUniv(list);

                // Requ�te de s�lection des semestres associ�s � une ann�e d'�tude
                result = this.connect.createStatement().executeQuery(
                            "SELECT * FROM semestre " +
                            "WHERE nom_annee_etude = '" + ae.getNomAnnee() + "'");

                // Cr�ation des objets Semestre
                Semestre s1 = null;
                Semestre s2 = null;
                if (result.first()) {
                    // Cr�ation de l'objet correspondant au 1er semestre
                    s1 = new Semestre (result.getString("nom_semestre"),
                                       annee,
                                       result.getInt("niveau"));
                    if (result.next()) {
                        // Cr�ation de l'objet correspondant au 2nd semestre
                        s2 = new Semestre (result.getString("nom_semestre"),
                                           annee,
                                           result.getInt("niveau"));
                    }
                }

                // Mise � jour de l'ann�e d'�tude avec les semstres
                annee.setS1(s1);
                annee.setS2(s2);
            }
        } catch (SQLException ex) {
            // Si exception, la m�thode retourne l'objet Matiere initial
            annee = ae;
        }
        return annee;

	}

	@Override
	public boolean delete(AnneeEtude ae) {
		boolean ok = true; // Vaut True si la suppression s'est d�roul�e correctement, False sinon
        try {
            // Cr�ation et ex�cution de la requ�te de suppression
            this.connect.createStatement().executeUpdate(
                            "DELETE FROM annee_etude " +
                            "WHERE nom_annee_etude = '" + ae.getNomAnnee() + "'");
        }
        catch (SQLException e) {
            // Si exception, on indique que la suppression ne s'est pas d�roul�e correctement
            ok = false;
        }

        // La suppression s'est bien d�roul�e lorsqu'1 seule ligne a �t� supprim�e de la base
        return ok;

	}

	@Override
	public ArrayList<AnneeEtude> getListe() {
		ArrayList<AnneeEtude> listAE = new ArrayList<AnneeEtude>();
        //ArrayList<AnneeUniversitaire> listAU = new ArrayList<AnneeUniversitaire>();
        ResultSet anneeEt, semestre; // R�sultats de requ�tes de s�lection
        Semestre s1 = null; // Objet correspondant au 1er semestre
        Semestre s2 = null; // Objet correspondant au 2nd semestre

        try {
            // Requ�te de s�lection de l'ensemble des ann�es d'�tude de la base
            anneeEt = this.connect.createStatement().executeQuery("SELECT * FROM annee_etude");

            // Ajout des ann�es d'�tude dans la liste
            while (anneeEt.next()) {

                /*// Requ�te de s�lection des ann�es universitaires associ�es � l'ann�e d'�tude
                anneeUniv = this.connect.createStatement().executeQuery(
                        "SELECT nom_annee_univ FROM conge " +
                        "WHERE nom_annee_etude = '" + anneeEt.getString("nom_annee_etude") + "' " +
                        "GROUP BY nom_annee_univ");
                // Stockage des enregistrements dans une liste d'ann�es universitaires
                while (anneeUniv.next()) {
                    listAU.add(new AnneeUniversitaire (anneeUniv.getString("nom_annee_univ")));
                }*/

               
                AnneeEtude a = new AnneeEtude(anneeEt.getString("nom_annee_etude"),
                                           anneeEt.getString("description"),
                                           anneeEt.getInt("duree_seance"),
                                           anneeEt.getInt("nb_seances_am"),
                                           anneeEt.getInt("nb_seances_pm"));
                // Requ�te de s�lection des semestres associ�s � une ann�e d'�tude
                semestre = this.connect.createStatement().executeQuery(
                            "SELECT * FROM semestre " +
                            "WHERE nom_annee_etude = '" + anneeEt.getString("nom_annee_etude") + "'");
                // Cr�ation des objets Semestre
                if (semestre.first()) {
                    // Cr�ation de l'objet correspondant au 1er semestre
                    s1 = new Semestre (semestre.getString("nom_semestre"),a,
                                       semestre.getInt("niveau"));
                    a.setS1(s1);
                    if (semestre.next()) {
                        // Cr�ation de l'objet correspondant au 2nd semestre
                        s2 = new Semestre (semestre.getString("nom_semestre"),a,
                                           semestre.getInt("niveau"));
                        a.setS2(s2);
                    }
                }

                // Ajout de la nouvelle ann�e d'�tude � la liste
                listAE.add(a);
            }
        }
        catch (SQLException e) {
            // Si exception, on renvoie la liste vide
            listAE.clear();
        }
        return listAE;

	}

	
	
	
	
	
	
	
	
	
	
	
	
}
