package tsi.lpv.agendaeletronica.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.contato.Telefone;
import tsi.lpv.agendaeletronica.entidades.contato.TipoTel;

public abstract class TelefoneDAO {
	
	protected boolean inserir(Telefone telefone) {
		final String SQL = "INSERT INTO telefone VALUES (?, ?, ?)";

		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setString(1, telefone.getNumero());
			BDAgenda.getInstance().getStmt().setString(2, Character.toString(telefone.getTipo().getTipo()));
			BDAgenda.getInstance().getStmt().setInt(3, telefone.getCodigoPessoa());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Telefone inserido");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	protected ArrayList<Telefone> pesquisar(Telefone telefone) {
		ArrayList<Telefone> arrayList = new ArrayList<>();
		final String SQL = "SELECT * FROM telefone WHERE codigopessoa = ?";
		
		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, telefone.getCodigoPessoa());
			ResultSet rSet = BDAgenda.getInstance().obterResultSet();
			
			while(rSet.next())
				arrayList.add(new Telefone(rSet.getString(1), TipoTel.obterTipo(rSet.getString(2).charAt(0)), rSet.getInt(3)));
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
		}
		
		return arrayList;
	}
	
	protected boolean alterar(Telefone telefone, String chave) {
		final String SQL = "UPDATE telefone SET numerotelefone = ?, tipo = ? WHERE numerotelefone = ?";
		
		try{
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setString(1, telefone.getNumero());
			BDAgenda.getInstance().getStmt().setString(2, Character.toString(telefone.getTipo().getTipo()));
			BDAgenda.getInstance().getStmt().setString(3, chave);
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Telefone atualizado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	protected boolean excluir(Telefone telefone) {
		final String SQL = "DELETE FROM telefone WHERE numerotelefone = ?";
		
		try{
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setString(1, telefone.getNumero());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Telefone excluído");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
} // class TelefoneDAO
