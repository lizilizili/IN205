package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.modele.Membre.Abonnement;
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
		private static final String SELECT_ID_QUERY =  "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
				+ "FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre "
				+ "INNER JOIN livre ON livre.id = e.idLivre "
				+ "WHERE e.id = ?;";
		private static final String SELECT_CURRENT_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
				+ "FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre "
				+ "INNER JOIN livre ON livre.id = e.idLivre "
				+ "WHERE dateRetour IS NULL;";
		private static final String SELECT_CURRENT_MEMBRE_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
				+ "FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre "
				+ "INNER JOIN livre ON livre.id = e.idLivre "
				+ "WHERE dateRetour IS NULL AND membre.id = ?;";
		private static final String SELECT_CURRENT_LIVRE_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
				+ "FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre "
				+ "INNER JOIN livre ON livre.id = e.idLivre "
				+ "WHERE dateRetour IS NULL AND livre.id = ?;";
		private static final String SELECT_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre,"
				+"titre, auteur, isbn, dateEmprunt, dateRetour "
				+ "FROM emprunt AS e "
				+ "INNER JOIN membre ON membre.id = e.idMembre "
				+ "INNER JOIN livre ON livre.id = e.idLivre "
				+ "ORDER BY dateRetour DESC;";
		private static final String UPDATE_QUERY = "UPDATE Emprunt SET membre=?, livre=? ,dateEmprunt=?,dateRetour=?  WHERE id=?;";
		private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt;";
		
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
			while(res.next()) {
				LivreDao lDao=LivreDaoImpl.getInstance();
				Livre l=lDao.getById(res.getInt("idLivre"));
				MembreDao mDao=MembreDaoImpl.getInstance();
				Membre m=mDao.getById(res.getInt("idMembre"));
				LocalDate dateEmprunt= res.getDate("dateEmprunt").toLocalDate();
				LocalDate dateRetour = null;
				if (res.getDate("dateRetour") !=null) {
					dateRetour= res.getDate("dateRetour").toLocalDate();
				}
						
				Emprunt e = new Emprunt(res.getInt("id"), m,l, dateEmprunt,dateRetour);
				emprunts.add(e);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
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
		List<Emprunt> emprunts = new ArrayList<>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = EstablishConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_CURRENT_QUERY);
			res = preparedStatement.executeQuery();
			while(res.next()) {
				LivreDao lDao=LivreDaoImpl.getInstance();
				Livre l=lDao.getById(res.getInt("idLivre"));
				MembreDao mDao=MembreDaoImpl.getInstance();
				Membre m=mDao.getById(res.getInt("idMembre"));
				LocalDate dateEmprunt= res.getDate("dateEmprunt").toLocalDate();
				LocalDate dateRetour = null;
				if (res.getDate("dateRetour") !=null) {
					dateRetour= res.getDate("dateRetour").toLocalDate();
				}
						
				Emprunt e = new Emprunt(res.getInt("id"), m,l, dateEmprunt,dateRetour);
				emprunts.add(e);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
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
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		List<Emprunt> emprunts = new ArrayList<>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = EstablishConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_CURRENT_MEMBRE_QUERY);
			preparedStatement.setInt(1, idMembre);
			res = preparedStatement.executeQuery();
			MembreDao mDao=MembreDaoImpl.getInstance();
			Membre m=mDao.getById(idMembre);
			while(res.next()) {
				LivreDao lDao=LivreDaoImpl.getInstance();
				Livre l=lDao.getById(res.getInt("idLivre"));
				LocalDate dateEmprunt= res.getDate("dateEmprunt").toLocalDate();
				LocalDate dateRetour = null;
				if (res.getDate("dateRetour") !=null) {
					dateRetour= res.getDate("dateRetour").toLocalDate();
				}
						
				Emprunt e = new Emprunt(res.getInt("id"), m,l, dateEmprunt,dateRetour);
				emprunts.add(e);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
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
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		List<Emprunt> emprunts = new ArrayList<>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = EstablishConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_CURRENT_LIVRE_QUERY);
			preparedStatement.setInt(1, idLivre);
			LivreDao lDao=LivreDaoImpl.getInstance();
			Livre l=lDao.getById(idLivre);
			res = preparedStatement.executeQuery();
			while(res.next()) {
				
				MembreDao mDao=MembreDaoImpl.getInstance();
				Membre m=mDao.getById(res.getInt("idMembre"));
				LocalDate dateEmprunt= res.getDate("dateEmprunt").toLocalDate();
				LocalDate dateRetour = null;
				if (res.getDate("dateRetour") !=null) {
					dateRetour= res.getDate("dateRetour").toLocalDate();
				}
						
				Emprunt e = new Emprunt(res.getInt("id"), m,l, dateEmprunt,dateRetour);
				emprunts.add(e);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
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
	public Emprunt getById(int id) throws DaoException {
		Emprunt emprunt = new Emprunt();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = EstablishConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_CURRENT_LIVRE_QUERY);
			preparedStatement.setInt(1, id);	
			res = preparedStatement.executeQuery();
			if(res.next()) {
				LivreDao lDao=LivreDaoImpl.getInstance();
				Livre l=lDao.getById(res.getInt("idLivre"));
				MembreDao mDao=MembreDaoImpl.getInstance();
				Membre m=mDao.getById(res.getInt("idMembre"));

				emprunt.setId(id);
				emprunt.setLivre(l);
				emprunt.setMembre(m);
				emprunt.setDateEmprunt(res.getDate("dateEmprunt").toLocalDate());
				LocalDate dateRetour = null;
				if (res.getDate("dateRetour") !=null) {
					dateRetour= res.getDate("dateRetour").toLocalDate();
				} 
				emprunt.setDateRetour(dateRetour);
			}
			System.out.println("GET: " + emprunt);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste du emprunt: id=" + id, e);
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
		return emprunt;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = -1;
		LivreDao lDao=LivreDaoImpl.getInstance();
		Livre l=lDao.getById(idLivre);
		MembreDao mDao=MembreDaoImpl.getInstance();
		Membre m=mDao.getById(idMembre);
		
		Emprunt emprunt = new Emprunt();
		try {
			connection = EstablishConnection.getConnection();
			preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idMembre);
			preparedStatement.setInt(2, idLivre);
			preparedStatement.setString(3, dateEmprunt.toString());
			preparedStatement.setString(4, null);
			preparedStatement.executeUpdate();
			res = preparedStatement.getGeneratedKeys();
			if(res.next()){
				id = res.getInt(1);				
			}
			emprunt.setId(id);
			emprunt.setLivre(l);
			emprunt.setMembre(m);
			emprunt.setDateEmprunt(LocalDate.parse(res.getString("dateEmprunt")));
			emprunt.setDateRetour(LocalDate.parse(res.getString("dateRetour")));
			
			System.out.println("CREATE: " + emprunt);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de la création du emprunt: " + emprunt, e);
		} finally {
			// Ici pour bien faire les choses on doit fermer les objets utilisés dans
			// des blocs séparés afin que les exceptions levées n'empèchent pas la fermeture des autres !
			// la logique est la même pour les autres méthodes. Pour rappel, le bloc finally sera toujours exécuté !
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
		
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = EstablishConnection.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setInt(1, emprunt.getMembre().getId());
			preparedStatement.setInt(2, emprunt.getLivre().getId());
			preparedStatement.setString(3, emprunt.getDateEmprunt().toString());
			preparedStatement.setString(4, emprunt.getDateRetour().toString());
			preparedStatement.setInt(5, emprunt.getId());
			preparedStatement.executeUpdate();

			System.out.println("UPDATE: " + emprunt);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de la mise à jour du emprunt: " + emprunt, e);
		} finally {
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
		
	}

	@Override
	public int count() throws DaoException {
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count=-1;
		try {
			connection = EstablishConnection.getConnection();
			preparedStatement = connection.prepareStatement(COUNT_QUERY);
			res = preparedStatement.executeQuery();
			if(res.next()){
				count = res.getInt(1);				
			}
			System.out.println("Count: " + count);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de le count des emprunts " , e);
		}  finally {
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
		return count;
	}
	

}
