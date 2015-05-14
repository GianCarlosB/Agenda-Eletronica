package tsi.lpv.agendaeletronica.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;

public abstract class PessoaDAO {
	
	protected boolean inserir(Pessoa pessoa) {
		final String SQL = "INSERT INTO pessoa VALUES (nextval('seq_pessoa'), ?, ?)";

		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setString(1, pessoa.getNome());
			BDAgenda.getInstance().getStmt().setDate(2, new java.sql.Date(pessoa.getDataDeAniversario().getTime()));
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Pessoa inserida");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	protected ArrayList<Pessoa> pesquisar() {
		ArrayList<Pessoa> arrayList = new ArrayList<>();
		final String SQL = "SELECT * FROM pessoa";
		
		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			ResultSet rSet = BDAgenda.getInstance().obterResultSet();
			
			while(rSet.next())
				arrayList.add(new Pessoa(rSet.getInt(1), rSet.getString(2), rSet.getDate(3)));
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
		}
		
		return arrayList;
	}
	
	protected boolean alterar(Pessoa pessoa) {
		final String SQL = "UPDATE pessoa SET nome = ?, datadeaniversario = ? WHERE codigopessoa = ?";
		
		try{
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setString(1, pessoa.getNome());
			BDAgenda.getInstance().getStmt().setDate(2, new java.sql.Date(pessoa.getDataDeAniversario().getTime()));
			BDAgenda.getInstance().getStmt().setInt(3, pessoa.getCodigoPessoa());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Pessoa atualizada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	protected boolean excluir(Pessoa pessoa) {
		final String SQL = "DELETE FROM pessoa WHERE codigopessoa = ?";
		
		try{
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, pessoa.getCodigoPessoa());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Pessoa excluída");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
} // class PessoaDAO
