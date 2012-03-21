package persistance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import metier.Enseignant;
import metier.Matiere;
import metier.UE;

public class MatiereDAOTXT extends DAO<Matiere>{
	 private static final MatiereDAOTXT instance = new MatiereDAOTXT();
	    
	    /**
	     * M�thode permettant de r�cup�rer l'objet unique de type MatiereDAO
	     * @return MatiereDAO - Instance unique de l'objet MatiereDAO
	     */
	    public final static MatiereDAOTXT getInstance() {
	        return instance;
	    }

	    /**
	     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
	     * � la mati�re m en param�tre et retourne les r�sultats sous forme d'un objet Matiere.
	     * @param m Objet Matiere � rechercher dans la base de donn�es
	     * @return Matiere - Objet Matiere cr�� � partir des r�sultats trouv�s dans la base
	     */
	    public Matiere find(Matiere m) {
	 
	    	String file ="BDTXT/Matiere.txt";
			String delimiter = "|";
			String ligne = null;
			StringTokenizer strToken = null;
			BufferedReader bufferReader;
			String s = null;
			try {
				bufferReader = new BufferedReader(new FileReader(file));						
				try {
					while ((ligne = bufferReader.readLine())!=null)
					{
						strToken = new StringTokenizer(ligne,delimiter);
						if (m.getIdMat() == Integer.parseInt(strToken.nextToken())){
							UEDAOTXT ueDAO = new UEDAOTXT();
			                UE ue = new UE(Integer.parseInt(strToken.nextToken()));
			                ue = ueDAO.find(ue);
			                
			                EnseignantDAOTXT ensDAO = new EnseignantDAOTXT();
			                Enseignant ens = new Enseignant(Integer.parseInt(strToken.nextToken()));
			                ens = ensDAO.find(ens);
			                s = strToken.nextToken();
			                m.setNomMat(s);
			                m.setUEMat(ue);
			                m.setResponsable(ens);
						}	
					}
				}	
				catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	        return m;
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
	        String file ="BDTXT/Matiere.txt";
			String delimiter = "|";
			String ligne = null;
			StringTokenizer strToken = null;
			BufferedReader bufferReader;
			int id;
			String lib;
			try {
				bufferReader = new BufferedReader(new FileReader(file));						
				try {
					while ((ligne = bufferReader.readLine())!=null)
					{
						strToken = new StringTokenizer(ligne,delimiter);
						while (strToken.hasMoreTokens()){
							id = Integer.parseInt(strToken.nextToken());
							UEDAOTXT ueDAO = new UEDAOTXT();
							UE ue = new UE(Integer.parseInt(strToken.nextToken()));
							ue = ueDAO.find(ue);
							
							EnseignantDAOTXT ensDAO = new EnseignantDAOTXT();
							Enseignant ens = new Enseignant(Integer.parseInt(strToken.nextToken()));
							ens = ensDAO.find(ens);
							lib = strToken.nextToken();
							Matiere m = new Matiere(id,lib, ue, ens);
							list.add(m);
						}
					}
					bufferReader.close();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return list;
	    }


	    public boolean update(Matiere ancien, Matiere nouveau) {
	        return false;
	    }

		public boolean login(Matiere obj) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean create(Matiere obj) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean update(Matiere obj) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean delete(Matiere obj) {
			// TODO Auto-generated method stub
			return false;
		}
}
