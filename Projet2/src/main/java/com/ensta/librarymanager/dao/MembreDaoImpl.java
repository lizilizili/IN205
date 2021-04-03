package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.modele.Membre.Abonnement;
import com.ensta.librarymanager.utils.EstablishConnection;

public class MembreDaoImpl implements MembreDao{
	
	// Une implementation possible du design pattern Singleton dite "lazy instanciation"
	
		private static MembreDaoImpl instance;
		private MembreDaoImpl() { }	
		public static MembreDao getInstance() {
			if(instance == null) {
				instance = new MembreDaoImpl();
			}
			return instance;
		}
		
		
		private static final String CREATE_QUERY = "INSERT INTO  membre(nom, prenom, adresse, email, telephone, abonnement)  VALUES ( ?, ?, ?, ?， ?, ?);";
		private static final String SELECT_ID_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement "
				+ "FROM membre WHERE id=?;";
		private static final String SELECT_ALL_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement "
				+ "FROM membre ORDER BY nom, prenom;";
		private static final String UPDATE_QUERY = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ? abonnement = ? WHERE id = ?;";
		private static final String DELETE_QUERY = "DELETE FROM membre  WHERE id=?;";
		private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM membre;";
		
		@Override
		public List<Membre> getList() throws DaoException {
			List<Membre> membres = new ArrayList<>();
			ResultSet res = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = EstablishConnection.getConnection();
				preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
				res = preparedStatement.executeQuery();
				while(res.next()) {
					Membre l = new Membre(res.getInt("id"), res.getString("nom"), res.getString("prenom"),res.getString("adresse"),res.getString("email"),res.getString("telephone"),Abonnement.valueOf(res.getString("abonnement")));
					membres.add(l);
				}
				System.out.println("GET: " + membres);
			} catch (SQLException e) {
				throw new DaoException("Problème lors de la récupération de la liste des membres", e);
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
			return membres;
		}
		
		@Override
		public Membre getById(int id) throws DaoException {
			Membre membre = new Membre();
			ResultSet res = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = EstablishConnection.getConnection();
				preparedStatement = connection.prepareStatement(SELECT_ID_QUERY);
				preparedStatement.setInt(1, id);
				res = preparedStatement.executeQuery();
				if(res.next()) {
					membre.setId(res.getInt("id"));
					membre.setNom(res.getString("nom"));
					membre.setPrenom(res.getString("prenom"));
					membre.setAdresse(res.getString("adresse"));
					membre.setEmail(res.getString("email"));
					membre.setTel(res.getString("telephone"));
					membre.setAbonnement(Abonnement.valueOf(res.getString("abonnement")));
				}
				
				System.out.println("GET: " + membre);
			} catch (SQLException e) {
				throw new DaoException("Problème lors de la récupération du livre: id=" + id, e);
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
			return membre;
		}
		
		@Override
		public int create(String nom, String prenom, String adresse, String email, String telephone)
				throws DaoException {
			ResultSet res = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			int id = -1;
			Membre membre=new Membre();
			try {
				connection = EstablishConnection.getConnection();
				preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, nom);
				preparedStatement.setString(2, prenom);
				preparedStatement.setString(3, adresse);
				preparedStatement.setString(4, email);
				preparedStatement.setString(5, telephone);
				preparedStatement.setString(6, Abonnement.BASIC.toString());
				preparedStatement.executeUpdate();
				res = preparedStatement.getGeneratedKeys();
				if(res.next()){
					id = res.getInt(1);				
				}
				membre.setId(id);
				membre.setNom(res.getString("nom"));
				membre.setPrenom(res.getString("prenom"));
				membre.setAdresse(res.getString("adresse"));
				membre.setEmail(res.getString("email"));
				membre.setTel(res.getString("telephone"));
				membre.setAbonnement(Abonnement.BASIC);
				
				System.out.println("CREATE: " + membre);
			} catch (SQLException e) {
				throw new DaoException("Problème lors de la création du membre: " + membre, e);
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
			return id;
		}
		@Override
		public void update(Membre membre) throws DaoException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = EstablishConnection.getConnection();
				preparedStatement = connection.prepareStatement(UPDATE_QUERY);
				preparedStatement.setString(1, membre.getNom());
				preparedStatement.setString(2, membre.getPrenom());
				preparedStatement.setString(3, membre.getAdresse());
				preparedStatement.setString(4, membre.getEmail());
				preparedStatement.setString(5, membre.getTel());
				preparedStatement.setString(6, membre.getAbonnement().toString());
				preparedStatement.setInt(7, membre.getId());
				preparedStatement.executeUpdate();

				System.out.println("UPDATE: " + membre);
			} catch (SQLException e) {
				throw new DaoException("Problème lors de la mise à jour du membre: " + membre, e);
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
		public void delete(int id) throws DaoException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = EstablishConnection.getConnection();
				preparedStatement = connection.prepareStatement(DELETE_QUERY);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				connection.close();
				System.out.println("DELETE: " + id);
			} catch (SQLException e) {
				throw new DaoException("Problème lors de la suppression du membre: " + id, e);
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
				throw new DaoException("Problème lors de le count des membres " , e);
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
