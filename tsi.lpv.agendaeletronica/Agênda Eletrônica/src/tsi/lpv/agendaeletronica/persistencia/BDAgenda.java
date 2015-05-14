package tsi.lpv.agendaeletronica.persistencia;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/** Classe para manipular o banco de dados do sistema
 * 
 * @author Gian Carlos Barros Honório
 * 
 * @see BancoDeDadosDAO
 */
public final class BDAgenda extends BancoDeDadosDAO implements Runnable {
	
	private final static String NOME = "agenda"; // Nome ao banco de dados.
	
	private final static String LOGIN = "aluno"; // login de accesso ao banco de dados.
	
	private final static String SENHA = "aluno"; // senha de accesso ao banco de dados.
	
	private final static String ARQ_SQL_CRIAR_TABELAS = "src\\tsi\\lpv\\agendaeletronica\\recursos\\scripts\\CriarTabelas.sql";
	
	private final static String ARQ_SQL_DELETAR_TABELAS = "src\\tsi\\lpv\\agendaeletronica\\recursos\\scripts\\DeletarTabelas.sql";
	
	private final static String ARQ_SQL_INSERIR_CONFIGS_PADRAO = "src\\tsi\\lpv\\agendaeletronica\\recursos\\scripts\\InserirConfigsPadrao.sql";
	
	private static final String TIPO = "jdbc:postgresql:";
	
	/** Caminho do banco de dados */
	private final static String URL = TIPO + NOME; // URL de acesso ao banco de dados.
	
	/** <code>BDAgenda</code> com a instância do banco de dados */
	protected static BDAgenda BD_AGENDA = new BDAgenda();
	
	/** Cria uma instância do banco de dados do sistema
	 * 
	 */
	protected BDAgenda() {
		super(URL, LOGIN, SENHA);
	}
	
	/** Retorna a instâcia do banco de dados do sistema
	 * @return <code>BDAgenda</code> com a instância da classe
	 */
	public static BDAgenda getInstance() {
		return BD_AGENDA;
	}
	
	/** Reinicia o banco de dados, apaga todas as tabelas e as recria
	 * @return <code>boolean</code> com <code>true</code> caso tenha reiniciado com sucesso, e <code>false</code> 
	 * caso tenha ocorrido algum problema
	 */
	public static boolean resetarDadosBD() {
		try {
			BD_AGENDA.abrirArquivoSQL(ARQ_SQL_DELETAR_TABELAS);
			BD_AGENDA.abrirArquivoSQL(ARQ_SQL_CRIAR_TABELAS);
			BD_AGENDA.abrirArquivoSQL(ARQ_SQL_INSERIR_CONFIGS_PADRAO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(), "Agenda Eletrônica - ERRO", 0);
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/** Encerra a conexão com o banco de dados e fecha seus recursos
	 * @return <code>boolean</code> com <code>true</code> caso tenha encerrado com sucesso, e <code>false</code> 
	 * caso tenha ocorrido algum problema
	 */
	public static boolean encerrarBD() {
		// Fecha o PreparedStatement.
		try {
			if(BDAgenda.getInstance().getStmt() != null) BDAgenda.getInstance().fecharPreparedStatement();
		} catch (SQLException eStmt) {
			// TODO Auto-generated catch block
			eStmt.printStackTrace();
		} finally {
			// Fecha a conexão com o banco de dados e finaliza a aplicação.
			try {
				BDAgenda.getInstance().fecharConexao();
				System.out.println("Conexão com o Banco de dados finalizada: " +
				                   new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss").format(new Date()));
			} catch (SQLException | NullPointerException e) {
				// TODO Auto-generated catch block
				System.err.println("Conexão com o Banco de dados NÃO finalizada: " +
				                   new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss").format(new Date()));
				return false;
			}
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				this.abrirConexao();
				System.out.println("Banco de dados iniciado: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss").format( new Date()) +
				                   "\nURL da conexão: " + URL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Banco de dados NÃO iniciado: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss").format( new Date()) +
		                           "\nURL da conexão: " + URL);
				BD_AGENDA.setConn(null);
			}
		}
	}

} // class BancoDeDadosBVB
