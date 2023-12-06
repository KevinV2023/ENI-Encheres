package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	
	private static final String SQL_INSERT ="INSERT INTO Utilisateur (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECTBY_ID ="SELECT no_Utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur \"\r\n"
												+ "	+\"	FROM Utilisateur WHERE noUtilisateur = ?";
	private static final String SQL_SELECT_ALL ="SELECT noUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur \"\r\n"
												+ " +\" FROM Utilisateur";
	private static final String SQL_UPDATE ="UPDATE Utilisateur SET pseudo=?, nom=?, prenom=?, email=?, rue=?, code_postal=?, ville=? WHERE noUtilisateur=?";
	private static final String SQL_DELETE ="DELETE FROM Utilisateur WHERE noUtilisateur=?";
	

	@Override
	public void insert(Utilisateur u) {
		Connection cnx = null;
		PreparedStatement rqt;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(SQL_INSERT);
			rqt.setString(1, u.getPseudo());
			rqt.setString(2, u.getNom());
			rqt.setString(3, u.getPrenom());
			rqt.setString(4, u.getEmail());
			rqt.setString(5, u.getTelephone());
			rqt.setString(6, u.getRue());
			rqt.setString(7, u.getCodePostal());
			rqt.setString(8, u.getVille());
			rqt.setString(9, u.getMotDePasse());
			rqt.setInt(10, u.getCredit());
			rqt.setBoolean(11, u.getAdministrateur());
		}catch(SQLException e) {
				e.printStackTrace();
		}finally {
				if(cnx!=null) {
					try {
						JdbcTools.closeConnection(cnx);
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}

	@Override
	public Utilisateur selectBy(int id) {
		Connection cnx = null;
		Utilisateur u = null;
		PreparedStatement rqt;
		try {
			cnx=JdbcTools.getConnection();
			rqt=cnx.prepareStatement(SQL_SELECTBY_ID);
			rqt.setInt(1, id);
			rqt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return u;
	}

	@Override
	public List<Utilisateur> selectAll() {
		Connection cnx = null;
		Utilisateur u = null;
		List<Utilisateur> utilisateur = new ArrayList<>();
		ResultSet rs;
		Statement rqt;
		try {
			cnx=JdbcTools.getConnection();
			rqt=cnx.createStatement();
			rs=rqt.executeQuery(SQL_SELECT_ALL);
			while(rs.next()) {
				utilisateur.add(u);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(cnx!=null) {
				try {
				JdbcTools.closeConnection(cnx);
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return utilisateur;
	}

	@Override
	public void update(Utilisateur u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
