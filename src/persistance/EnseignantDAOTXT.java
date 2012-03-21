package persistance;
import metier.Enseignant;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.IOException; 


/**
 * Classe permettant de mapper les objets Enseignant vers la table ENSEIGNANT de la base de donn�es.
 * Cette classe h�rite de la classe DAO.
 * @authors Ahardane Abdeslam, Balestrat Cl�ment, Pravossoudovitch Yann
 * @version 1.0
 */
public class EnseignantDAOTXT extends DAO<Enseignant>{

    private static final EnseignantDAOTXT instance = new EnseignantDAOTXT();
    /**
     * M�thode permettant de r�cup�rer l'objet unique de type EnseignantDAO
     * @return EnseignantDAO - Instance unique de l'objet EnseignantDAO
     */
    public final static EnseignantDAOTXT getInstance() {
        return instance;
    }


	public Enseignant find(Enseignant instance) {
		
		String file ="BDTXT/Enseignant.txt";
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
					if (instance.getIdEns() == Integer.parseInt(strToken.nextToken())){
					while (strToken.hasMoreTokens()){
					instance.setNom(strToken.nextToken());
					instance.setPrenom(strToken.nextToken());
					instance.setMdp(strToken.nextToken());
					instance.setSu(Integer.parseInt(strToken.nextToken()));
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
		return instance;
	}

	

	public ArrayList<Enseignant> getListe() {
		ArrayList<Enseignant> list = new ArrayList<Enseignant>();
		String file ="BDTXT/Enseignant.txt";
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
					while (strToken.hasMoreTokens()){
					Enseignant ens = new Enseignant(Integer.parseInt(strToken.nextToken()),strToken.nextToken(),strToken.nextToken(),strToken.nextToken(),Integer.parseInt(strToken.nextToken()));
					list.add(ens);
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

	public boolean update(Enseignant ancien, Enseignant nouveau) {
		return false;
	}

	public boolean login(Enseignant ens) {
		boolean find = false;
			ArrayList<Enseignant> lens = new ArrayList<Enseignant>();
			EnseignantDAOTXT DAOens = new EnseignantDAOTXT();
			lens = DAOens.getListe();
			
			for(int i = 0; i<lens.size();i++){
				if ((ens.getNom().equals(lens.get(i).getNom())) && (ens.getMdp().equals(lens.get(i).getMdp()))){
					find = true;				
				}
			}
		
		return find;
		
	}


	@Override
	public boolean create(Enseignant obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean update(Enseignant obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(Enseignant obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
