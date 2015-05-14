package tsi.lpv.agendaeletronica.gui.contato;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.contato.Email;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.eventos.contato.TEActionIgExEmail;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgExcluirEmail extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -8171758211259055984L;

	public final String NOME_MODULO;
	
	private ArrayList<Pessoa> arrayListPessoas;
	private ArrayList<Email> arrayListEmails;
	private Vector<String> vectorPessoas;
	
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> nomesComboBox;
	private JButton excluirButton;
	private JPanel dadosBuscaPanel;
	private JList<String> emailsList;

	/**
	 * Create the dialog.
	 */
	public IgExcluirEmail(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Excluir E-Mail";
		
		Color wetAsphalt = new Color(52, 73, 94);
		setTitle(NOME_MODULO);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 250);
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
		labelImg.setIcon(new ImageIcon(IgExcluirEmail.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Mail-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Selecione os e-mails que deseja excluir:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JPanel dadosPanel = new JPanel();
		contentPanel.add(dadosPanel, BorderLayout.CENTER);

		dadosPanel.setLayout(new MigLayout("", "[116px,grow][202px,grow]", "[28px][28px,grow]"));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setDisplayedMnemonic(KeyEvent.VK_N);
		dadosPanel.add(lblNome, "cell 0 0,alignx trailing");
		
		arrayListPessoas = new Pessoa().pesquisar();
		vectorPessoas = new Vector<String>();
		
		Collections.sort(arrayListPessoas, new Pessoa());
		
		vectorPessoas.add("");
		
		// Preenche o vector com os nomes dos contatos.
		for(Pessoa p : arrayListPessoas) vectorPessoas.add(p.getNome());
		
		nomesComboBox = new JComboBox<String>(new DefaultComboBoxModel<String>(vectorPessoas)); 
		nomesComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				inserirEmailsLista();
			}
		});
		lblNome.setLabelFor(nomesComboBox);
		dadosPanel.add(nomesComboBox, "cell 1 0,growx");
		
		JPanel buscaPanel = new JPanel();
		dadosPanel.add(buscaPanel, "cell 0 1 2 1,grow");
		buscaPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorNome = new JSeparator();
		buscaPanel.add(separatorNome, BorderLayout.NORTH);
		
		dadosBuscaPanel = new JPanel();
		buscaPanel.add(dadosBuscaPanel, BorderLayout.CENTER);
		dadosBuscaPanel.setLayout(new MigLayout("", "[][grow]", "[grow]"));
		
		JLabel lblEmails = new JLabel("E-Mails:");
		lblEmails.setDisplayedMnemonic(KeyEvent.VK_E);
		dadosBuscaPanel.add(lblEmails, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		dadosBuscaPanel.add(scrollPane, "cell 1 0,grow");
		
		emailsList = new JList<String>();
		lblEmails.setLabelFor(emailsList);
		scrollPane.setViewportView(emailsList);
		
		JPanel msgErroPanel = new JPanel();
		contentPanel.add(msgErroPanel, BorderLayout.SOUTH);
		msgErroPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorBtn = new JSeparator();
		msgErroPanel.add(separatorBtn, BorderLayout.SOUTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			excluirButton = new JButton("Excluir");
			excluirButton.setEnabled(false);
			excluirButton.addActionListener(new TEActionIgExEmail(this));
			excluirButton.setIcon(new ImageIcon(IgExcluirEmail.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Report-Delete-16.png")));
			excluirButton.setMnemonic(KeyEvent.VK_E);
			excluirButton.setActionCommand("OK");
			buttonPane.add(excluirButton);
			{
				JButton fecharButton = new JButton("Fechar");
				fecharButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgExcluirEmail.this.dispose();
					}
				});
				fecharButton.setIcon(new ImageIcon(IgExcluirEmail.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				fecharButton.setMnemonic(KeyEvent.VK_F);
				fecharButton.setActionCommand("Cancel");
				buttonPane.add(fecharButton);
			}
		}
		
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor
	
	public void inserirEmailsLista() {
		inserirModelosListas(null);
		verificarItemSelecionado();
		
		if(ValidarDados.validarVazio(nomesComboBox.getSelectedItem().toString())) {
			int index = nomesComboBox.getSelectedIndex() - 1;
			
			DefaultListModel<String> modelo = new DefaultListModel<String>();
			
			arrayListEmails = new Email(arrayListPessoas.get(index).getCodigoPessoa()).pesquisar();
			
			// Preenche a JList com os e-mails.
			for(Email e : arrayListEmails) modelo.addElement(e.getEmail());
			
			inserirModelosListas(modelo);
		}
	}
	
	private void inserirModelosListas(DefaultListModel<String> modelo) {
		if(modelo != null)
			emailsList.setModel(modelo);
		else
			emailsList.setModel(new DefaultListModel<String>());
	}
	
	private void verificarItemSelecionado() {
		if(ValidarDados.validarVazio(nomesComboBox.getSelectedItem().toString()))
			excluirButton.setEnabled(true);
		else
			excluirButton.setEnabled(false);
	}

	public JButton getExcluirButton() {
		return excluirButton;
	}

	public JList<String> getEmailsList() {
		return emailsList;
	}

	public ArrayList<Email> getArrayListEmails() {
		return arrayListEmails;
	}
	
} // class IgExcluirEmail
