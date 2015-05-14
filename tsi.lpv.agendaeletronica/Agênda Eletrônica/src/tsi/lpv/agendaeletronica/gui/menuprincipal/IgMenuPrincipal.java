package tsi.lpv.agendaeletronica.gui.menuprincipal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import tsi.lpv.agendaeletronica.entidades.configuracoes.Configuracoes;
import tsi.lpv.agendaeletronica.gui.IgSobre;
import tsi.lpv.agendaeletronica.gui.contato.IgCadastrarEmail;
import tsi.lpv.agendaeletronica.gui.contato.IgCadastrarTel;
import tsi.lpv.agendaeletronica.gui.contato.IgExcluirEmail;
import tsi.lpv.agendaeletronica.gui.contato.IgExcluirTel;
import tsi.lpv.agendaeletronica.gui.pessoa.IgAlterarPessoa;
import tsi.lpv.agendaeletronica.gui.pessoa.IgCadastrarPessoa;
import tsi.lpv.agendaeletronica.gui.pessoa.IgConsultarPessoa;
import tsi.lpv.agendaeletronica.gui.pessoa.IgExcluirPessoa;
import tsi.lpv.agendaeletronica.gui.pessoa.IgRelacionarPessoa;
import tsi.lpv.agendaeletronica.gui.tarefa.IgAlterarTarefa;
import tsi.lpv.agendaeletronica.gui.tarefa.IgCadastrarTarefa;
import tsi.lpv.agendaeletronica.gui.tarefa.IgConsultarTarefa;
import tsi.lpv.agendaeletronica.gui.tarefa.IgExcluirTarefa;
import tsi.lpv.agendaeletronica.gui.tarefa.IgRelacionarTarefa;
import tsi.lpv.agendaeletronica.persistencia.BDAgenda;

public class IgMenuPrincipal extends JFrame {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 171223900197999306L;
	
	public final String NOME_PROGRAMA = "Agenda Eletr\u00F4nica";
	
	private Configuracoes configuracoes;
	
	private final String ROTULO_LF_METAL = "Metal";
	private final String ROTULO_LF_NIMBUS = "Nimbus";
	private final String ROTULO_LF_MOTIF = "CDE/Motif";
	private final String ROTULO_LF_WINDOWS = "Windows";
	private final String ROTULO_LF_WINDOWS_CLASSIC = "Windows Classic";
	
	private final String ROTULO_WP_THREE_D_INSPIRATION = "3D Inspiration";
	private final String ROTULO_WP_BLUE_BUBBLE_WAVES = "Blue Bubble Waves";
	private final String ROTULO_WP_CRYSTAL_LIGHT = "Crystal Light";
	private final String ROTULO_WP_DARK_AURORA = "Dark Aurora";
	private final String ROTULO_WP_FLOW_OF_GLOW = "Flow of Glow";
	
	private UIManager.LookAndFeelInfo looksAndFell[];
	private final String LOOK_AND_FEEL_NAMES[] = {ROTULO_LF_METAL, ROTULO_LF_NIMBUS, ROTULO_LF_MOTIF,
			ROTULO_LF_WINDOWS, ROTULO_LF_WINDOWS_CLASSIC};
	
	private final ImageIcon THREE_D_INSPIRATION = new ImageIcon(IgMenuPrincipal.class
			.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/3D Inspiration.jpg"));
	private final ImageIcon BLUE_BUBBLE_WAVES = new ImageIcon(IgMenuPrincipal.class
			.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Blue Bubble Waves.jpg"));
	private final ImageIcon CRYSTAL_LIGHT = new ImageIcon(IgMenuPrincipal.class
			.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Crystal Light.jpg"));
	private final ImageIcon DARK_AURORA = new ImageIcon(IgMenuPrincipal.class
			.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Dark Aurora.jpg"));
	private final ImageIcon FLOW_OF_GLOW = new ImageIcon(IgMenuPrincipal.class
			.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Flow of Glow.jpg"));
	private final ImageIcon PHONE = new ImageIcon(IgMenuPrincipal.class
			.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/phone50.png"));
	
	private ImageIcon wallPappers[] = {THREE_D_INSPIRATION, BLUE_BUBBLE_WAVES, CRYSTAL_LIGHT,
			DARK_AURORA, FLOW_OF_GLOW};
	private final String WALLPAPPER_NAMES[] = {ROTULO_WP_THREE_D_INSPIRATION, ROTULO_WP_BLUE_BUBBLE_WAVES, ROTULO_WP_CRYSTAL_LIGHT,
			ROTULO_WP_DARK_AURORA, ROTULO_WP_FLOW_OF_GLOW};
	
	private int numWallpapper;
	
	private JPanel contentPane;
	private JRadioButtonMenuItem looksAndFellRadioButtonMenuItem[];
	private JRadioButtonMenuItem wallpapperRadioButtonMenuItem[];
	private final ButtonGroup perspectivaButtonGroup = new ButtonGroup();
	private JRadioButtonMenuItem rdbtnmntmMinima;
	private JRadioButtonMenuItem rdbtnmntmPadrao;
	private JLabel wallPapperLabel;
	private ButtonGroup lookAndFeelButtonGroup;
	private ButtonGroup wallPapperButtonGroup;
	private JMenu mnWallPapper;
	private JMenuItem mntmProxWallpapper;
	private JMenuItem mntmAntWallpapper;

	/**
	 * Create the frame.
	 */
	public IgMenuPrincipal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				terminarPrograma();
			}
		});
		
		configuracoes = new Configuracoes().consultar();
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Phone-Book-256.png")));
		setTitle(".: " + NOME_PROGRAMA + " :.");
		this.setBounds(100, 100, 806, 554);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu(" Arquivo");
		mnArquivo.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Files-24.png")));
		mnArquivo.setMnemonic(KeyEvent.VK_A);
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminarPrograma();
			}
		});
		
		JMenuItem mntmLimparBancoDe = new JMenuItem("Limpar Banco de Dados");
		mntmLimparBancoDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmLimparBancoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparBD();
			}
		});
		mntmLimparBancoDe.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Data-Delete-24.png")));
		mnArquivo.add(mntmLimparBancoDe);
		
		JSeparator separatorArquivo = new JSeparator();
		mnArquivo.add(separatorArquivo);
		mntmSair.setMnemonic(KeyEvent.VK_S);
		mntmSair.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Fire-Exit-24.png")));
		mnArquivo.add(mntmSair);
		
		JMenu mnPessoa = new JMenu(" Pessoa");
		mnPessoa.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/User-Profile-24.png")));
		mnPessoa.setMnemonic(KeyEvent.VK_P);
		menuBar.add(mnPessoa);
		
		JMenuItem mntmCadPessoa = new JMenuItem("Cadastrar");
		mntmCadPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastrarPessoa(IgMenuPrincipal.this);
			}
		});
		mntmCadPessoa.setMnemonic(KeyEvent.VK_C);
		mnPessoa.add(mntmCadPessoa);
		
		JMenuItem mntmConsPessoa = new JMenuItem("Consultar");
		mntmConsPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgConsultarPessoa(IgMenuPrincipal.this);
			}
		});
		mntmConsPessoa.setMnemonic(KeyEvent.VK_O);
		mnPessoa.add(mntmConsPessoa);
		
		JMenuItem mntmAltPessoa = new JMenuItem("Alterar");
		mntmAltPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgAlterarPessoa(IgMenuPrincipal.this);
			}
		});
		mntmAltPessoa.setMnemonic(KeyEvent.VK_A);
		mnPessoa.add(mntmAltPessoa);
		
		JMenuItem mntmExPessoa = new JMenuItem("Excluir");
		mntmExPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgExcluirPessoa(IgMenuPrincipal.this);
			}
		});
		mntmExPessoa.setMnemonic(KeyEvent.VK_E);
		mnPessoa.add(mntmExPessoa);
		
		JMenuItem mntmRelPessoa = new JMenuItem("Relacionar");
		mntmRelPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgRelacionarPessoa(IgMenuPrincipal.this);
			}
		});
		mntmRelPessoa.setMnemonic(KeyEvent.VK_R);
		mnPessoa.add(mntmRelPessoa);
		
		JSeparator separatorPessoa = new JSeparator();
		mnPessoa.add(separatorPessoa);
		
		JMenuItem mntmAniversariantes = new JMenuItem("Aniversariantes");
		mntmAniversariantes.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cake-02-24.png")));
		mntmAniversariantes.setMnemonic(KeyEvent.VK_N);
		mnPessoa.add(mntmAniversariantes);
		
		JMenu mnContato = new JMenu(" Contato");
		mnContato.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Phone-24.png")));
		mnContato.setMnemonic(KeyEvent.VK_C);
		menuBar.add(mnContato);
		
		JMenu mnEmail = new JMenu("E-Mail");
		mnEmail.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Message-Mail-24.png")));
		mnEmail.setMnemonic(KeyEvent.VK_E);
		mnContato.add(mnEmail);
		
		JMenuItem mntmInserirEmail = new JMenuItem("Inserir");
		mntmInserirEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IgCadastrarEmail(IgMenuPrincipal.this);
			}
		});
		mntmInserirEmail.setMnemonic(KeyEvent.VK_I);
		mnEmail.add(mntmInserirEmail);
		
		JMenuItem mntmRemoverEmail = new JMenuItem("Remover");
		mntmRemoverEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IgExcluirEmail(IgMenuPrincipal.this);
			}
		});
		mntmRemoverEmail.setMnemonic(KeyEvent.VK_R);
		mnEmail.add(mntmRemoverEmail);
		
		JMenu mnTelefone = new JMenu("Telefone");
		mnTelefone.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Mobile-Phone-24.png")));
		mnTelefone.setMnemonic(KeyEvent.VK_T);
		mnContato.add(mnTelefone);
		
		JMenuItem mntmInserirTel = new JMenuItem("Inserir");
		mntmInserirTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastrarTel(IgMenuPrincipal.this);
			}
		});
		mntmInserirTel.setMnemonic(KeyEvent.VK_I);
		mnTelefone.add(mntmInserirTel);
		
		JMenuItem mntmRemoverTel = new JMenuItem("Remover");
		mntmRemoverTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgExcluirTel(IgMenuPrincipal.this);
			}
		});
		mntmRemoverTel.setMnemonic(KeyEvent.VK_R);
		mnTelefone.add(mntmRemoverTel);
		
		JMenu mntmCadTarefa = new JMenu(" Tarefa");
		mntmCadTarefa.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Task-03-24.png")));
		mntmCadTarefa.setMnemonic(KeyEvent.VK_T);
		menuBar.add(mntmCadTarefa);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastrarTarefa(IgMenuPrincipal.this);
			}
		});
		mntmCadastrar.setMnemonic(KeyEvent.VK_C);
		mntmCadTarefa.add(mntmCadastrar);
		
		JMenuItem mntmConsTarefa = new JMenuItem("Consultar");
		mntmConsTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgConsultarTarefa(IgMenuPrincipal.this);
			}
		});
		mntmConsTarefa.setMnemonic(KeyEvent.VK_O);
		mntmCadTarefa.add(mntmConsTarefa);
		
		JMenuItem mntmAltTarefa = new JMenuItem("Alterar");
		mntmAltTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgAlterarTarefa(IgMenuPrincipal.this);
			}
		});
		mntmAltTarefa.setMnemonic(KeyEvent.VK_A);
		mntmCadTarefa.add(mntmAltTarefa);
		
		JMenuItem mntmExTarefa = new JMenuItem("Excluir");
		mntmExTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgExcluirTarefa(IgMenuPrincipal.this);
			}
		});
		mntmExTarefa.setMnemonic(KeyEvent.VK_E);
		mntmCadTarefa.add(mntmExTarefa);
		
		JMenuItem mntmRelTarefa = new JMenuItem("Relacionar");
		mntmRelTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgRelacionarTarefa(IgMenuPrincipal.this);
			}
		});
		mntmRelTarefa.setMnemonic(KeyEvent.VK_R);
		mntmCadTarefa.add(mntmRelTarefa);
		
		JMenu mnAparencia = new JMenu(" Apar\u00EAncia");
		mnAparencia.setMnemonic(KeyEvent.VK_R);
		mnAparencia.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Stationery-02-24.png")));
		menuBar.add(mnAparencia);
		
		JMenu mnTemas = new JMenu("Temas");
		mnTemas.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Projector-Screen-24.png")));
		mnTemas.setMnemonic(KeyEvent.VK_T);
		mnAparencia.add(mnTemas);
		
		JMenu mnPerspectivas = new JMenu("Perspectivas");
		mnPerspectivas.setMnemonic(KeyEvent.VK_P);
		mnPerspectivas.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Windows-24.png")));
		mnAparencia.add(mnPerspectivas);
		
		rdbtnmntmMinima = new JRadioButtonMenuItem("M\u00EDnima");
		rdbtnmntmMinima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarPerspectiva(false);
			}
		});
		perspectivaButtonGroup.add(rdbtnmntmMinima);
		rdbtnmntmMinima.setMnemonic(KeyEvent.VK_M);
		mnPerspectivas.add(rdbtnmntmMinima);
		
		rdbtnmntmPadrao = new JRadioButtonMenuItem("Padr\u00E3o");
		rdbtnmntmPadrao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarPerspectiva(true);
			}
		});
		perspectivaButtonGroup.add(rdbtnmntmPadrao);
		rdbtnmntmPadrao.setMnemonic(KeyEvent.VK_P);
		mnPerspectivas.add(rdbtnmntmPadrao);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Help-24.png")));
		mnAjuda.setMnemonic(KeyEvent.VK_A);
		menuBar.add(mnAjuda);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre a Agenda Eletr\u00F4nica");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgSobre(IgMenuPrincipal.this);
			}
		});
		mntmSobre.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Message-Information-24.png")));
		mntmSobre.setMnemonic(KeyEvent.VK_S);
		mnAjuda.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		wallPapperLabel = new JLabel("");
		wallPapperLabel.setBounds(100, 100, 800, 500);
		contentPane.add(wallPapperLabel, BorderLayout.CENTER);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(wallPapperLabel, popupMenu);
		
		mntmProxWallpapper = new JMenuItem("Pr\u00F3ximo Papel de Parede");
		mntmProxWallpapper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmProxWallpapper.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Next-24.png")));
		mntmProxWallpapper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarWallPapper(true);
			}
		});
		mntmProxWallpapper.setMnemonic(KeyEvent.VK_P);
		popupMenu.add(mntmProxWallpapper);
		
		mntmAntWallpapper = new JMenuItem("Papel de Parede Anterior");
		mntmAntWallpapper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmAntWallpapper.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Previous-24.png")));
		mntmAntWallpapper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarWallPapper(false);
			}
		});
		mntmAntWallpapper.setMnemonic(KeyEvent.VK_A);
		popupMenu.add(mntmAntWallpapper);
		
		// Define as opções para configurar o look-and-feel. 
		looksAndFellRadioButtonMenuItem = new JRadioButtonMenuItem[LOOK_AND_FEEL_NAMES.length];
		
		// Obtém informações sobre os look-and-feel instalados pelo JDK.
		looksAndFell = UIManager.getInstalledLookAndFeels();
		
		looksAndFellRadioButtonMenuItem[0] = new JRadioButtonMenuItem(ROTULO_LF_METAL);
		looksAndFellRadioButtonMenuItem[0].setSelected(true);
		looksAndFellRadioButtonMenuItem[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[0], 0);
			}
		});
		looksAndFellRadioButtonMenuItem[0].setMnemonic(KeyEvent.VK_M);
		mnTemas.add(looksAndFellRadioButtonMenuItem[0]);
		
		looksAndFellRadioButtonMenuItem[1] = new JRadioButtonMenuItem(ROTULO_LF_NIMBUS);
		looksAndFellRadioButtonMenuItem[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[1], 1);
			}
		});
		looksAndFellRadioButtonMenuItem[1].setMnemonic(KeyEvent.VK_N);
		mnTemas.add(looksAndFellRadioButtonMenuItem[1]);
		
		looksAndFellRadioButtonMenuItem[2] = new JRadioButtonMenuItem(ROTULO_LF_MOTIF);
		looksAndFellRadioButtonMenuItem[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[2], 2);
			}
		});
		looksAndFellRadioButtonMenuItem[2].setMnemonic(KeyEvent.VK_C);
		mnTemas.add(looksAndFellRadioButtonMenuItem[2]);
		
		looksAndFellRadioButtonMenuItem[3] = new JRadioButtonMenuItem(ROTULO_LF_WINDOWS);
		looksAndFellRadioButtonMenuItem[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[3], 3);
			}
		});
		looksAndFellRadioButtonMenuItem[3].setMnemonic(KeyEvent.VK_W);
		mnTemas.add(looksAndFellRadioButtonMenuItem[3]);
		
		looksAndFellRadioButtonMenuItem[4] = new JRadioButtonMenuItem(ROTULO_LF_WINDOWS_CLASSIC);
		looksAndFellRadioButtonMenuItem[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[4], 4);
			}
		});
		looksAndFellRadioButtonMenuItem[4].setMnemonic(KeyEvent.VK_I);
		mnTemas.add(looksAndFellRadioButtonMenuItem[4]);
		
		mnWallPapper = new JMenu("Papel de Parede");
		mnWallPapper.setIcon(new ImageIcon(IgMenuPrincipal.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Images-24.png")));
		mnWallPapper.setMnemonic(KeyEvent.VK_P);
		mnAparencia.add(mnWallPapper);
		
		// Define as opções para configurar o papel de parede. 
		wallpapperRadioButtonMenuItem = new JRadioButtonMenuItem[WALLPAPPER_NAMES.length];
		
		wallpapperRadioButtonMenuItem[0] = new JRadioButtonMenuItem(ROTULO_WP_THREE_D_INSPIRATION);
		wallpapperRadioButtonMenuItem[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarWallPapper(WALLPAPPER_NAMES[0], 0);
			}
		});
		wallpapperRadioButtonMenuItem[0].setMnemonic(KeyEvent.VK_3);
		mnWallPapper.add(wallpapperRadioButtonMenuItem[0]);
		
		wallpapperRadioButtonMenuItem[1] = new JRadioButtonMenuItem(ROTULO_WP_BLUE_BUBBLE_WAVES);
		wallpapperRadioButtonMenuItem[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarWallPapper(WALLPAPPER_NAMES[1], 1);
			}
		});
		wallpapperRadioButtonMenuItem[1].setMnemonic(KeyEvent.VK_B);
		mnWallPapper.add(wallpapperRadioButtonMenuItem[1]);
		
		wallpapperRadioButtonMenuItem[2] = new JRadioButtonMenuItem(ROTULO_WP_CRYSTAL_LIGHT);
		wallpapperRadioButtonMenuItem[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarWallPapper(WALLPAPPER_NAMES[2], 2);
			}
		});
		wallpapperRadioButtonMenuItem[2].setMnemonic(KeyEvent.VK_C);
		mnWallPapper.add(wallpapperRadioButtonMenuItem[2]);
		
		wallpapperRadioButtonMenuItem[3] = new JRadioButtonMenuItem(ROTULO_WP_DARK_AURORA);
		wallpapperRadioButtonMenuItem[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarWallPapper(WALLPAPPER_NAMES[3], 3);
			}
		});
		wallpapperRadioButtonMenuItem[3].setMnemonic(KeyEvent.VK_D);
		mnWallPapper.add(wallpapperRadioButtonMenuItem[3]);
		
		wallpapperRadioButtonMenuItem[4] = new JRadioButtonMenuItem(ROTULO_WP_FLOW_OF_GLOW);
		wallpapperRadioButtonMenuItem[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarWallPapper(WALLPAPPER_NAMES[4], 4);
			}
		});
		wallpapperRadioButtonMenuItem[4].setMnemonic(KeyEvent.VK_F);
		mnWallPapper.add(wallpapperRadioButtonMenuItem[4]);
		
		JSeparator separatorAparencia = new JSeparator();
		mnAparencia.add(separatorAparencia);
		
		JMenuItem mntmRestaurarPadrao = new JMenuItem("Restaurar Padr\u00E3o");
		mntmRestaurarPadrao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmRestaurarPadrao.setMnemonic(KeyEvent.VK_R);
		mntmRestaurarPadrao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restaurarVisualPadrao();
			}
		});
		mnAparencia.add(mntmRestaurarPadrao);

		lookAndFeelButtonGroup = new ButtonGroup();
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[0]);
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[1]);
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[2]);
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[3]);
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[4]);
		
		wallPapperButtonGroup = new ButtonGroup();
		wallPapperButtonGroup.add(wallpapperRadioButtonMenuItem[0]);
		wallPapperButtonGroup.add(wallpapperRadioButtonMenuItem[1]);
		wallPapperButtonGroup.add(wallpapperRadioButtonMenuItem[2]);
		wallPapperButtonGroup.add(wallpapperRadioButtonMenuItem[3]);
		wallPapperButtonGroup.add(wallpapperRadioButtonMenuItem[4]);
		
		// Altera as configurações da GUI, para os valores armazenados no banco de dados.
		alterarLookAndFell(LOOK_AND_FEEL_NAMES[configuracoes.getLookAndFeel()], configuracoes.getLookAndFeel());
		alterarWallPapper(WALLPAPPER_NAMES[configuracoes.getPapelDeParede()], configuracoes.getPapelDeParede());
		alterarPerspectiva(configuracoes.isPerspectivaPadrao());
		
		setLocationRelativeTo(null);
		setVisible(true);
	} // construtor
	
	/**
	 * Altera a aparência e o comportamento (look-and-feel) da interface gráfica usando o nome do 
	 * lookAndFell. Ativa o item de menu correspondente ao look-and-feel.
	 */
	private void alterarLookAndFell(String lookAndFell, int lookAndFellItemMenu) {
		try {
			 for (int lf = 0; lf < looksAndFell.length; lf++)
				if (lookAndFell.equalsIgnoreCase(looksAndFell[lf].getName())) 
				{
					// Configura o novo look-and-feel carregando a classe de aparência e comportamento através do método getClassName.
					UIManager.setLookAndFeel(looksAndFell[lf].getClassName());
					
					// Seleciona o item de menu correspondente ao look-and-feel.
					looksAndFellRadioButtonMenuItem[lookAndFellItemMenu].setSelected(true);
					break;
				}
			/*
			 * Altera a aparência e o comportamento de cada componente anexado ao argumento do método 
			 * updateComponentTreeUI da classe SwingUtilities para a nova aparência e o novo comportamento 
			 * escolhido pelo usuário. 
			 */
			SwingUtilities.updateComponentTreeUI(this);
		} 
		// A partir da versão Java 7 (1.7) é possível usar um único catch para tratar várias exceções. 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Agenda Eletrônica - ERRO", 0);
			ex.printStackTrace();
		}
	} // alterarLookAndFell()
	
	private void alterarPerspectiva(boolean padrao) {
		if(padrao) {
			for(int wp = 0; wp < wallPappers.length; wp++)
				if(wallpapperRadioButtonMenuItem[wp].isSelected()) {
					alterarWallPapper(WALLPAPPER_NAMES[wp], wp);
					numWallpapper = wp;
					break;
				}
			
			mnWallPapper.setEnabled(true);
			mntmProxWallpapper.setSelected(true);
			mntmAntWallpapper.setSelected(true);
			
			this.setBounds(100, 100, 806, 554);
			setTitle(".: " + NOME_PROGRAMA + " :.");
			rdbtnmntmPadrao.setSelected(true);
		}
		else {
			wallPapperLabel.setIcon(PHONE);
			
			mnWallPapper.setEnabled(false);
			mntmProxWallpapper.setSelected(false);
			mntmAntWallpapper.setSelected(false);
			
			this.setBounds(100, 100, 190, 225);
			this.setTitle("Agenda");
			rdbtnmntmMinima.setSelected(true);
		}
		
		setLocationRelativeTo(null);
	}
	
	private void alterarWallPapper(String wallPapper, int wallPapperItemMenu) {
		 for (int wp = 0; wp < wallPappers.length; wp++)
			if (wallPapper.equalsIgnoreCase(WALLPAPPER_NAMES[wp])) 
			{
				// Configura o novo papel de parede.
				wallPapperLabel.setIcon(wallPappers[wp]);
				
				// Seleciona o item de menu correspondente ao novo papel de parede.
				wallpapperRadioButtonMenuItem[wallPapperItemMenu].setSelected(true);
				break;
			}
	}
	
	private void alterarWallPapper(boolean proximo) {
		if(proximo) {
			if(numWallpapper == 4) numWallpapper = 0;
			else numWallpapper++;
		}
		else {
			if(numWallpapper == 0) numWallpapper = 4;
			else numWallpapper--;
		}
		
		alterarWallPapper(WALLPAPPER_NAMES[numWallpapper], numWallpapper);
	}
	
	private void restaurarVisualPadrao() {
		alterarLookAndFell(LOOK_AND_FEEL_NAMES[Configuracoes.LOOK_AND_FEEL_PADRAO], 1);
		alterarPerspectiva(Configuracoes.PERSPECTIVA_PADRAO);
		alterarWallPapper(WALLPAPPER_NAMES[Configuracoes.PAPEL_DE_PAREDE_PADRAO], 4);
	}
	
	private void salvarConfiguracoes() {
		int lookAndFeel = Configuracoes.LOOK_AND_FEEL_PADRAO;
		boolean perspectivaPadrao = Configuracoes.PERSPECTIVA_PADRAO;
		int papelDeParede = Configuracoes.PAPEL_DE_PAREDE_PADRAO;
		
		for(int lf = 0; lf < looksAndFellRadioButtonMenuItem.length; lf++)
			if(looksAndFellRadioButtonMenuItem[lf].isSelected()) {
				lookAndFeel = lf;
				break;
			}

		for(int wp = 0; wp < wallpapperRadioButtonMenuItem.length; wp++)
			if(wallpapperRadioButtonMenuItem[wp].isSelected()) {
				papelDeParede = wp;
				break;
			}
		
		if(rdbtnmntmMinima.isSelected())
			perspectivaPadrao = false;
		else if(rdbtnmntmPadrao.isSelected())
			perspectivaPadrao = true;
		
		configuracoes.setLookAndFeel(lookAndFeel);
		configuracoes.setPerspectivaPadrao(perspectivaPadrao);
		configuracoes.setPapelDeParede(papelDeParede);
		configuracoes.alterar();
	}
	
	private void limparBD() {
		String NOME_MODULO = NOME_PROGRAMA + " - Limpar Banco de Dados";
		
		int opc = JOptionPane.showConfirmDialog(this, " Esta operação irá excluir permanentemente TODOS os registros do banco de dados!"
				+ "\n\n Deseja continuar assim mesmo?", NOME_MODULO, JOptionPane.YES_NO_CANCEL_OPTION, 3);
		if(opc == JOptionPane.YES_OPTION) {
			if(BDAgenda.resetarDadosBD())
				JOptionPane.showMessageDialog(this, "O banco de dados foi limpo com sucesso!", NOME_MODULO, 1);
			else
				JOptionPane.showMessageDialog(this, "Falha na limpeza do banco de dados!", NOME_MODULO, 0);
		}
	}
	
	private void terminarPrograma() {
		// Salva as configurações da GUI.
		salvarConfiguracoes();
		
		// Encerra o banco de dados.
		BDAgenda.encerrarBD();
		
		// Libera os recursos da janela.
		IgMenuPrincipal.this.dispose();
		System.exit(0);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
} // class IgMenuPrincipal
