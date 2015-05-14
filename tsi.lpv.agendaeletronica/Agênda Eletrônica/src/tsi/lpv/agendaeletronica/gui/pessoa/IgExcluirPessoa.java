package tsi.lpv.agendaeletronica.gui.pessoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.eventos.pessoa.TEActionIgExPessoa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgExcluirPessoa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 5010114376299507306L;
	
	public final String NOME_MODULO;
	
	private ArrayList<Pessoa> arrayListPessoas;
	
	private final JPanel contentPanel = new JPanel();
	private JButton excluirButton;
	private JList<String> pessoasList;

	/**
	 * Create the dialog.
	 */
	public IgExcluirPessoa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Excluir Pessoa";
		
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
		labelImg.setIcon(new ImageIcon(IgExcluirPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/User-Profile-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Selecione as pessoas que deseja excluir:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JPanel dadosPanel = new JPanel();
		contentPanel.add(dadosPanel, BorderLayout.CENTER);
		
		dadosPanel.setLayout(new MigLayout("", "[116px][202px,grow]", "[28px,grow]"));
		
		JLabel lblPessoas = new JLabel("Pessoas:");
		lblPessoas.setDisplayedMnemonic(KeyEvent.VK_P);
		dadosPanel.add(lblPessoas, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		dadosPanel.add(scrollPane, "cell 1 0,grow");
		
		pessoasList = new JList<String>();
		lblPessoas.setLabelFor(pessoasList);
		scrollPane.setViewportView(pessoasList);
		
		JPanel msgErroPanel = new JPanel();
		contentPanel.add(msgErroPanel, BorderLayout.SOUTH);
		msgErroPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorBtn = new JSeparator();
		msgErroPanel.add(separatorBtn, BorderLayout.SOUTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				excluirButton = new JButton("Excluir");
				excluirButton.addActionListener(new TEActionIgExPessoa(this));
				excluirButton.setIcon(new ImageIcon(IgExcluirPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Report-Delete-16.png")));
				excluirButton.setMnemonic(KeyEvent.VK_E);
				excluirButton.setActionCommand("OK");
				buttonPane.add(excluirButton);
				getRootPane().setDefaultButton(excluirButton);
			}
			{
				JButton fecharButton = new JButton("Fechar");
				fecharButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgExcluirPessoa.this.dispose();
					}
				});
				fecharButton.setIcon(new ImageIcon(IgExcluirPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				fecharButton.setMnemonic(KeyEvent.VK_F);
				fecharButton.setActionCommand("Cancel");
				buttonPane.add(fecharButton);
			}
		}
		
		inserirPessoasLista();
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor
	
	public void inserirPessoasLista() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		
		arrayListPessoas = new Pessoa().pesquisar();
		
		Collections.sort(arrayListPessoas, new Pessoa());
		
		// Preenche a JList com os dados das pessoas.
		for(Pessoa p : arrayListPessoas) modelo.addElement(p.getNome());
		
		pessoasList.setModel(modelo);
	}

	public ArrayList<Pessoa> getArrayListPessoas() {
		return arrayListPessoas;
	}

	public JButton getExcluirButton() {
		return excluirButton;
	}

	public JList<String> getPessoasList() {
		return pessoasList;
	}
	
} // class IgExcluirPessoa
