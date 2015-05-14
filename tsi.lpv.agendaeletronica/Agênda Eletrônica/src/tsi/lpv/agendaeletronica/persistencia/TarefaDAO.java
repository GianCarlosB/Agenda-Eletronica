package tsi.lpv.agendaeletronica.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.tarefa.Tarefa;

public abstract class TarefaDAO {
	
	protected boolean inserir(Tarefa tarefa) {
		final String SQL = "INSERT INTO tarefa VALUES (?, ?, ?, ?)";

		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, tarefa.getCodigoTarefa());
			BDAgenda.getInstance().getStmt().setString(2, tarefa.getDescricao());
			BDAgenda.getInstance().getStmt().setDate(3, new java.sql.Date(tarefa.getDataHora().getTime()));
			BDAgenda.getInstance().getStmt().setTime(4, new Time(tarefa.getDataHora().getTime()));
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Tarefa inserida");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public int proximoValorSequencia() {
		final String SQL = "SELECT nextval('seq_tarefa')";
		int proximoValor;
		
		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			ResultSet rSet = BDAgenda.getInstance().obterResultSet();
			
			rSet.next();
			
			proximoValor = rSet.getInt(1);
			System.out.println("Próxima chave tarefa: " + proximoValor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return -1;
		}
		
		return proximoValor;
	}
	
	@SuppressWarnings("deprecation")
	protected ArrayList<Tarefa> pesquisar() {
		ArrayList<Tarefa> arrayList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		final String SQL = "SELECT * FROM tarefa";
		
		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			ResultSet rSet = BDAgenda.getInstance().obterResultSet();
			
			while(rSet.next()) {
				Date data = rSet.getDate(3);
				Time time = rSet.getTime(4);
				
				cal.setTime(data);
				
				cal.set(Calendar.HOUR_OF_DAY, time.getHours());
				cal.set(Calendar.MINUTE, time.getMinutes());
				cal.set(Calendar.SECOND, time.getSeconds());
				cal.set(Calendar.MILLISECOND, 00);
				
				arrayList.add(new Tarefa(rSet.getInt(1), rSet.getString(2), cal.getTime()));
			}
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
		}
		
		return arrayList;
	}
	
	protected boolean alterar(Tarefa tarefa) {
		final String SQL = "UPDATE tarefa SET descricao = ?, data = ?, hora = ? WHERE codigotarefa = ?";
		
		try{
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setString(1, tarefa.getDescricao());
			BDAgenda.getInstance().getStmt().setDate(2, new java.sql.Date(tarefa.getDataHora().getTime()));
			BDAgenda.getInstance().getStmt().setTime(3, new Time(tarefa.getDataHora().getTime()));
			BDAgenda.getInstance().getStmt().setInt(4, tarefa.getCodigoTarefa());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Tarefa atualizada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	protected boolean excluir(Tarefa tarefa) {
		final String SQL = "DELETE FROM tarefa WHERE codigotarefa = ?";
		
		try{
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, tarefa.getCodigoTarefa());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Tarefa excluída");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
} // class TarefaDAO
