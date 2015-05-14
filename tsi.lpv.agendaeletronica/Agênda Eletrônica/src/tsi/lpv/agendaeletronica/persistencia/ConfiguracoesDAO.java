package tsi.lpv.agendaeletronica.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.configuracoes.Configuracoes;

public abstract class ConfiguracoesDAO {

	protected boolean alterar(Configuracoes configuracoes) {
		final String SQL= "UPDATE configuracoes SET lookandfeel = ?, perspectivapadrao = ?, papeldeparede = ? WHERE idconfiguracao = ?";

		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, configuracoes.getLookAndFeel());
			BDAgenda.getInstance().getStmt().setBoolean(2, configuracoes.isPerspectivaPadrao());
			BDAgenda.getInstance().getStmt().setInt(3, configuracoes.getPapelDeParede());
			BDAgenda.getInstance().getStmt().setInt(4, configuracoes.getIdConfiguracao());
			BDAgenda.getInstance().getStmt().executeUpdate();
			
			System.out.println("Configurações inseridas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	protected Configuracoes consultar() {
		Configuracoes configuracoes = null;
		final String SQL = "SELECT * FROM configuracoes WHERE idconfiguracao = ?";
		
		try {
			BDAgenda.getInstance().obterPreparedStatement(SQL);
			BDAgenda.getInstance().getStmt().setInt(1, Configuracoes.ID_CONF_PADRAO);
			ResultSet rSet = BDAgenda.getInstance().obterResultSet();
			
			if(!rSet.next()) return configuracoes;
			
			configuracoes = new Configuracoes(rSet.getInt(2), rSet.getBoolean(3), rSet.getInt(4));
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
		}
		
		return configuracoes;
	}
	
} // class ConfiguracoesDAO
