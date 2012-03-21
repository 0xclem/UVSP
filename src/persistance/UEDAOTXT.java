package persistance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import metier.Enseignant;
import metier.UE;

public class UEDAOTXT extends DAO<UE>{
	private static final UEDAOTXT instance = new UEDAOTXT();

    /**
     * M�thode permettant de r�cup�rer l'objet unique de type UEDAO
     * @return UEDAO - Instance unique de l'objet UEDAO
     */
    public final static UEDAOTXT getInstance() {
        return instance;
    }


    /**
     * M�thode qui recherche dans la base de donn�es l'enregistrement correspondant
     * � l'UE u en param�tre et retourne les r�sultats sous forme d'un objet UE.
     * @param u Objet UE � rechercher dans la base de donn�es
     * @return UE - Objet UE cr�� � partir des r�sultats trouv�s dans la base
     */
    public UE find(UE u) {
        
    	String file ="BDTXT/UE.txt";
		String delimiter = "|";
		String ligne = null;
		StringTokenizer strToken = null;
		BufferedReader bufferReader;
		try {
			bufferReader = new BufferedReader(new FileReader(file));						
			try {
				while ((ligne = bufferReader.readLine())!=null)
				{
					strToken = new StringTokenizer(ligne,delimiter);
					if (u.getIdUE() == Integer.parseInt(strToken.nextToken())){
					while (strToken.hasMoreTokens()){
						EnseignantDAOTXT ensDAO = new EnseignantDAOTXT();
		        		Enseignant ens = new Enseignant(Integer.parseInt(strToken.nextToken()));
		        		ens = ensDAO.find(ens);
		        		u.setNomUE(strToken.nextToken());
		                u.setEnseignant(ens);
					}}	
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
        return u;
    }

 

    public boolean update(UE u1, UE u2) {
    	return false;
    }


    /**
     * M�thode qui permet de retourner la liste des UEs stock�es dans la base
     * @exception SQLException
     * @return ArrayList<UE> - Liste des UEs stock�es dans la base
     */
    public ArrayList<UE> getListe() {
    	ArrayList<UE> list = new ArrayList<UE>();
		String file ="BDTXT/UE.txt";
		String delimiter = "|";
		String ligne = null;
		StringTokenizer strToken = null;
		BufferedReader bufferReader;
		int id;
		try {
			bufferReader = new BufferedReader(new FileReader(file));						
			try {
				while ((ligne = bufferReader.readLine())!=null)
				{
					strToken = new StringTokenizer(ligne,delimiter);
					while (strToken.hasMoreTokens()){
						id = Integer.parseInt(strToken.nextToken());
						EnseignantDAOTXT ensDAO = new EnseignantDAOTXT();
		        		Enseignant ens = new Enseignant(Integer.parseInt(strToken.nextToken()));
		        		ens = ensDAO.find(ens);
		        		UE u = new UE(id,strToken.nextToken(),ens);
		        		list.add(u);
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

	public boolean login(UE obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean create(UE obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean update(UE obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(UE obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
