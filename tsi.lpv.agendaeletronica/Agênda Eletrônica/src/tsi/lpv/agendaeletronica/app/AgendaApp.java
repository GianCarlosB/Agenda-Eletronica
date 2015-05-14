package tsi.lpv.agendaeletronica.app;

import tsi.lpv.agendaeletronica.gui.IgApresentacao;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;
import tsi.lpv.agendaeletronica.persistencia.BDAgenda;

public class AgendaApp {

	public static void main(String[] args) {
		// Inicia a janela de apresentação (carregamento).
		IgApresentacao igApresentacao = new IgApresentacao();
		
		// Inicia a thread que faz a conexão com o banco de dados.
		Thread threadBancoDeDados = new Thread(BDAgenda.getInstance());
		threadBancoDeDados.start();
		
		try { 
			threadBancoDeDados.join(); // Faz o programa só iniciar após o banco de dados iniciar.
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// Finaliza a Thread da janela de apresentação.
		igApresentacao.finalizarThread();
		
		// Finaliza a janela de apresentação.
		igApresentacao.dispose();

		// Cria a janela que contém o menu principal da agenda eletrônica.
		new IgMenuPrincipal();
	} // main()

} // class AgendaApp
