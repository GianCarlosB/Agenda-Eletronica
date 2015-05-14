package tsi.lpv.agendaeletronica.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.contato.Email;

public abstract class EMailDAO {
	
	protected boolean inserir(Email email) {
		final String SQL = "INSERT INTO email VALUES (?, ?)";

		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setString(1, email.getEmail());
			BDAgenda.getInstance().getStmt().setInt(2, email.getCodigoPessoa());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("E-Mail inserido");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	protected ArrayList<Email> pesquisar(Email email) {
		ArrayList<Email> arrayList = new ArrayList<>();
		final String SQL = "SELECT * FROM email WHERE codigopessoa = ?";
		
		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, email.getCodigoPessoa());
			ResultSet rSet = BDAgenda.getInstance().obterResultSet();
			
			while(rSet.next())
				arrayList.add(new Email(rSet.getString(1), rSet.getInt(2)));
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
		}
		
		return arrayList;
	}
	
	protected boolean alterar(Email email, String chave) {
		final String SQL = "UPDATE email SET email = ? WHERE email = ?";
		
		try{
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setString(1, email.getEmail());
			BDAgenda.getInstance().getStmt().setString(2, chave);
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("E-Mail atualizado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	protected boolean excluir(Email email) {
		final String SQL = "DELETE FROM email WHERE email = ?";
		
		try{
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setString(1, email.getEmail());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("E-Mail excluída");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
} // class TelefoneDAO
