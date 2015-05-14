package tsi.lpv.agendaeletronica.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

/** Classe que define a GUI de apresentação da Agênda Eletrônica
 * 
 * @author Gian Carlos Barros Honório
 * 
 * @see JFrame
 */
public class IgApresentacao extends JFrame {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 3529660466821184952L;
	
	private String textoCarregando = "  Carregando a Agênda Eletrônica.";
	private JPanel contentPane;
	private JLabel lblCarregando = new JLabel(textoCarregando);;
	private JProgressBar progressBar = new JProgressBar();
	private JLabel lblBanner = new JLabel("");
	
	private Carregamento carregamento = new Carregamento();
	private Thread threadCarregamento = new Thread(carregamento);

	/** Cria uma instância da janela de apresentação da Agênda Eletrônica
	 */
	public IgApresentacao() {
		setTitle("Ag\u00EAnda Eletr\u00F4nica - Carregando");
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgApresentacao.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Phone-Book-256.png")));
		threadCarregamento.start();
		setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(463, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBanner.setIcon(new ImageIcon(IgApresentacao.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Banner.png")));
		lblBanner.setBounds(10, 11, 128, 128);
		contentPane.add(lblBanner);
		progressBar.setIndeterminate(true);
		progressBar.setName("");
		progressBar.setForeground(Color.BLACK);
		
		progressBar.setBounds(10, 269, 443, 30);
		contentPane.add(progressBar);
		lblCarregando.setBorder(null);
		
		lblCarregando.setForeground(Color.BLACK);
		lblCarregando.setFont(new Font("Segoe Condensed", Font.BOLD, 24));
		lblCarregando.setLabelFor(progressBar);
		lblCarregando.setBounds(52, 188, 390, 30);
		contentPane.add(lblCarregando);
		
		JEditorPane dtrpnCriado = new JEditorPane();
		dtrpnCriado.setEditable(false);
		dtrpnCriado.setFont(new Font("Tahoma", Font.PLAIN, 9));
		dtrpnCriado.setText("Copyright \u00A9 2013-2014, Ag\u00EAnda Eletr\u00F4nica vers\u00E3o 1.00. Software criado por Gian Carlos & Diego\r\nOliveira para avalia\u00E7\u00E3o na disciplina de Linguagem de Programa\u00E7\u00E3o Visual do Curso Superior de\r\nTecnologia em Sistemas para internet do IF Sudeste de Minas Gerais C\u00E2mpus Barbacena. Prof. M\u00E1rlon.");
		dtrpnCriado.setForeground(Color.BLACK);
		dtrpnCriado.setBackground(Color.WHITE);
		dtrpnCriado.setBounds(10, 229, 443, 39);
		contentPane.add(dtrpnCriado);
		
		JLabel lblSeta = new JLabel("seta");
		lblSeta.setIcon(new ImageIcon(IgApresentacao.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Arrowhead-Right-32 (verde).png")));
		lblSeta.setBounds(10, 186, 32, 32);
		contentPane.add(lblSeta);
		
		JLabel lblTitulo = new JLabel(" Ag\u00EAnda Eletr\u00F4nica");
		lblTitulo.setFont(new Font("Segoe Condensed", Font.BOLD, 40));
		lblTitulo.setBounds(148, 11, 305, 128);
		contentPane.add(lblTitulo);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	private class Carregamento implements Runnable {
		private volatile boolean isRunning = true;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int carregar = 0; isRunning; carregar += 25) {
				if(carregar > 100) carregar = 0;
				if(carregar == 0) lblCarregando.setText(textoCarregando);
				else if(carregar == 25) lblCarregando.setText(textoCarregando + ".");
				else if(carregar == 50) lblCarregando.setText(textoCarregando + "..");
				else if(carregar == 75) lblCarregando.setText(textoCarregando + "...");
				else if(carregar == 100) lblCarregando.setText(textoCarregando + "....");
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		/** Muda o estado de execução da thread
		 *  @param isRunning <code>boolean</code> com <code>true</code> para a thread continuar com seu loop de execução, 
		 *  e <code>false</code> para ser finalizada
		 */
		public void setRunning(boolean isRunning) {
			this.isRunning = isRunning;
		}
	}
	
	/** Finaliza a thread de carregamento
	 */
	public void finalizarThread() {
		carregamento.setRunning(false);
	}
} // class IgApresentacao
