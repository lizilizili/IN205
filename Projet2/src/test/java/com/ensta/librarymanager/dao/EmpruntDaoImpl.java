package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.utils.EstablishConnection;

public class EmpruntDaoImpl implements EmpruntDao{
	
		// Une implementation possible du design pattern Singleton dite "lazy instanciation"
	
		private static EmpruntDaoImpl instance;
		private EmpruntDaoImpl() { }	
		public static EmpruntDao getInstance() {
			if(instance == null) {
				instance = new EmpruntDaoImpl();
			}
			return instance;
		}
		
		private static final String CREATE_QUERY = "INSERT INTO Emprunt (idMembre, idLivre, dateEmprunt,dateRetour) VALUES (?, ?,?,?);";
		private static final String SELECT_ONE_QUERY = "SELECT * FROM Emprunt WHERE id=?;";
		private static final String SELECT_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre,"
				+"titre, auteur, isbn, dateEmprunt, dateRetour "
				+ "FROM emprunt AS e "
				+ "INNER JOIN membre ON membre.id = e.idMembre "
				+ "INNER JOIN livre ON livre.id = e.idLivre "
				+ "ORDER BY dateRetour DESC;";
		private static final String UPDATE_QUERY = "UPDATE Emprunt SET membre=?, livre=? ,dateEmprunt=?,dateRetour=?  WHERE id=?;";
		private static final String DELETE_QUERY = "DELETE FROM Emprunt WHERE id=?;";
		
	@Override
	public List<Emprunt> getList() throws DaoException {
		List<Emprunt> emprunts = new ArrayList<>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = EstablishConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			res = preparedStatement.executeQuery();
			/*while(res.next()) {
				Emprunt f = new Emprunt(res.getInt("id"), res.getString("titre"), res.getString("realisateur"));
				emprunts.add(f);
			}*/
			System.out.println("GET: " + emprunts);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des films", e);
		} finally {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return emprunts;
	}

	@Override
	public List<Emprunt> getListCurrent() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emprunt getById(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
