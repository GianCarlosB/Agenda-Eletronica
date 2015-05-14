package tsi.lpv.agendaeletronica.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;

public class IgSobre extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 9175501819505379624L;

	public final String NOME_MODULO;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public IgSobre(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Sobre a Agênda Eletrônica";
		
		Color wetAsphalt = new Color(52, 73, 94);
		setTitle(NOME_MODULO);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel tituloPanel = new JPanel();
		tituloPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tituloPanel.setBackground(wetAsphalt);
		contentPanel.add(tituloPanel, BorderLayout.NORTH);
		tituloPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel labelImg = new JLabel("");
		labelImg.setIcon(new ImageIcon(IgSobre.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Certificate-Information-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Informa\u00E7\u00F5es do Software:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JTabbedPane dadosTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(dadosTabbedPane, BorderLayout.CENTER);
		
		JPanel sobrePanel = new JPanel();
		dadosTabbedPane.addTab("Sobre", null, sobrePanel, null);
		sobrePanel.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		JLabel labelImgSobre = new JLabel("");
		labelImgSobre.setIcon(new ImageIcon(IgSobre.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Phone-Book-48.png")));
		sobrePanel.add(labelImgSobre, "cell 0 0");
		
		JLabel lblAgndaEletrnica = new JLabel("    Ag\u00EAnda Eletr\u00F4nica");
		lblAgndaEletrnica.setFont(new Font("SansSerif", Font.BOLD, 18));
		sobrePanel.add(lblAgndaEletrnica, "cell 1 0");
		
		JLabel lblVersao = new JLabel("    Vers\u00E3o 1.0");
		sobrePanel.add(lblVersao, "cell 1 1");
		
		JLabel lblCopyright = new JLabel("    Copyright \u00A9 2013-2014, IFSUDMG TSI");
		lblCopyright.setFont(new Font("SansSerif", Font.PLAIN, 12));
		sobrePanel.add(lblCopyright, "cell 1 2");
		
		JPanel creditosPanel = new JPanel();
		dadosTabbedPane.addTab("Créditos", null, creditosPanel, null);
		creditosPanel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel labelImgIF = new JLabel("");
		labelImgIF.setIcon(new ImageIcon(IgSobre.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/IFET_logo.PNG")));
		creditosPanel.add(labelImgIF, "cell 0 0");
		
		JLabel lblAutores = new JLabel("    Autores:");
		lblAutores.setFont(new Font("SansSerif", Font.BOLD, 18));
		creditosPanel.add(lblAutores, "cell 1 0");
		
		JLabel lblNomes = new JLabel("    Gian Carlos Barros Hon\u00F3rio & Diego Oliveira");
		creditosPanel.add(lblNomes, "cell 1 1");
		
		JLabel lblDescricao = new JLabel("    Curso Superior de Tecnologia em Sistemas para Internet");
		lblDescricao.setFont(new Font("SansSerif", Font.PLAIN, 12));
		creditosPanel.add(lblDescricao, "cell 0 2 2 1");
		
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor
	
} // class IgSobre
