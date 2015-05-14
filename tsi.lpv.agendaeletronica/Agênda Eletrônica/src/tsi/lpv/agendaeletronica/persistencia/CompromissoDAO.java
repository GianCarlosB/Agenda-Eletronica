package tsi.lpv.agendaeletronica.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.tarefa.Compromisso;

public abstract class CompromissoDAO {
	
	protected boolean inserir(Compromisso compromisso) {
		final String SQL = "INSERT INTO compromisso VALUES (?, ?)";

		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, compromisso.getCodigoPessoa());
			BDAgenda.getInstance().getStmt().setInt(2, compromisso.getCodigoTarefa());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Compromisso inserido");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	protected ArrayList<Compromisso> pesquisarTarefa(Compromisso compromisso) {
		ArrayList<Compromisso> arrayList = new ArrayList<>();
		final String SQL = "SELECT * FROM compromisso WHERE codigotarefa = ?";
		
		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, compromisso.getCodigoTarefa());
			ResultSet rSet = BDAgenda.getInstance().obterResultSet();
			
			while(rSet.next())
				arrayList.add(new Compromisso(rSet.getInt(1), rSet.getInt(2)));
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
		}
		
		return arrayList;
	}
	
	protected ArrayList<Compromisso> pesquisarPessoa(Compromisso compromisso) {
		ArrayList<Compromisso> arrayList = new ArrayList<>();
		final String SQL = "SELECT * FROM compromisso WHERE codigopessoa = ?";

		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, compromisso.getCodigoPessoa());
			ResultSet rSet = BDAgenda.getInstance().obterResultSet();
			
			while(rSet.next())
				arrayList.add(new Compromisso(rSet.getInt(1), rSet.getInt(2)));
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
		}
		
		return arrayList;
	}
	
	protected boolean excluir(Compromisso compromisso) {
		final String SQL = "DELETE FROM compromisso WHERE codigopessoa = ?";
		
		try{
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, compromisso.getCodigoPessoa());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Compromisso excluído");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
} // class CompromissoDAO
