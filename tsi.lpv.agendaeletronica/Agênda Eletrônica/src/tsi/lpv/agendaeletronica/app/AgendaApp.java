package tsi.lpv.agendaeletronica.app;

import tsi.lpv.agendaeletronica.gui.IgApresentacao;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;
import tsi.lpv.agendaeletronica.persistencia.BDAgenda;

public class AgendaApp {

	public static void main(String[] args) {
		// Inicia a janela de apresenta��o (carregamento).
		IgApresentacao igApresentacao = new IgApresentacao();
		
		// Inicia a thread que faz a conex�o com o banco de dados.
		Thread threadBancoDeDados = new Thread(BDAgenda.getInstance());
		threadBancoDeDados.start();
		
		try { 
			threadBancoDeDados.join(); // Faz o programa s� iniciar ap�s o banco de dados iniciar.
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// Finaliza a Thread da janela de apresenta��o.
		igApresentacao.finalizarThread();
		
		// Finaliza a janela de apresenta��o.
		igApresentacao.dispose();

		// Cria a janela que cont�m o menu principal da agenda eletr�nica.
		new IgMenuPrincipal();
	} // main()

} // class AgendaApp
