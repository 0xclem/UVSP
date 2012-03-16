package persistance;

import java.sql.Connection;
import java.util.ArrayList;
import jdbc.ConnectionToOracle;

public abstract class DAO<T> {

	public Connection connect = ConnectionToOracle.getInstance();

    /**
     * M�thode abstraite qui fait apelle � la m�thode concr�te de la classe DAO de l'objet T permettant l'ajout de celui-ci en base de donn�es
     * @param obj Objet T qui doit �tre mapp� dans la base
     */
	public abstract boolean create(T obj);
	
    /**
     * M�thode abstraite qui fait apelle � la m�thode concr�te de la classe DAO de l'objet T permettant la modification de celui-ci en base de donn�es
     * @param obj Objet T qui doit �tre mapp� dans la base
     */
	public abstract boolean update(T obj);
	
    /**
     * M�thode abstraite qui fait apelle � la m�thode concr�te de la classe DAO de l'objet T permettant le remplacement de celui-ci en base de donn�es
     * @param ancien Objet T qui doit �tre remplac� dans la base
     * @param ancien Objet T qui doit remplacer l'ancien objet dans la base
     */
	public abstract boolean update(T ancien, T nouveau);
	
    /**
     * M�thode abstraite qui fait apelle � la m�thode concr�te de la classe DAO de l'objet T permettant de trouver celui-ci en base de donn�es
     * @param obj Objet T qui doit �tre mapp� dans la base
     */
	public abstract T find(T obj);
	
    /**
     * M�thode abstraite qui fait apelle � la m�thode concr�te de la classe DAO de l'objet T permettant la suppression de celui-ci en base de donn�es
     * @param obj Objet T qui doit �tre mapp� dans la base
     */
	public abstract boolean delete(T obj);
	
    /**
     * M�thode abstraite qui fait apelle � la m�thode concr�te de la classe DAO de l'objet T permettant de de cr�er une liste de ceux-ci � partir de la base de donn�es
     */
	public abstract ArrayList<T> getListe();
	
    /**
     * M�thode abstraite qui fait apelle � la m�thode concr�te de la classe DAO de l'objet T permettant de v�rifier la connexion de celui-ci en base de donn�es
     * @param obj Objet T qui doit �tre mapp� dans la base
     */
	public abstract boolean login(T obj);


}