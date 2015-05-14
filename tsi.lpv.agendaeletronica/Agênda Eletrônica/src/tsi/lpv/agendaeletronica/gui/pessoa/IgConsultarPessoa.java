package tsi.lpv.agendaeletronica.gui.pessoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
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
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.contato.Email;
import tsi.lpv.agendaeletronica.entidades.contato.Telefone;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgConsultarPessoa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -7228357994503672768L;

	public final String NOME_MODULO;
	
	private ArrayList<Pessoa> arrayListPessoas;
	private Vector<String> vectorPessoas;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField dataAniversarioTextField;
	private JComboBox<String> nomesComboBox;
	private JList<String> telefonesList;
	private JList<String> emailList;

	/**
	 * Create the dialog.
	 */
	public IgConsultarPessoa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Consultar Pessoa";
		
		Color wetAsphalt = new Color(52, 73, 94);
		setTitle(NOME_MODULO);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 400);
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
		labelImg.setIcon(new ImageIcon(IgConsultarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/User-Profile-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Selecione o nome da pessoa que deseja consultar:");
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
			public void itemStateChanged(ItemEvent arg0) {
				inserirInformacoesConsulta();
			}
		});
		lblNome.setLabelFor(nomesComboBox);
		dadosPanel.add(nomesComboBox, "cell 1 0,growx");
		
		JPanel buscaPanel = new JPanel();
		dadosPanel.add(buscaPanel, "cell 0 1 2 1,grow");
		buscaPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorNome = new JSeparator();
		buscaPanel.add(separatorNome, BorderLayout.NORTH);
		
		JPanel dadosBuscaPanel = new JPanel();
		buscaPanel.add(dadosBuscaPanel, BorderLayout.CENTER);
		dadosBuscaPanel.setLayout(new MigLayout("", "[][grow]", "[][][grow]"));
		
		JLabel lblDataDeAniversario = new JLabel("Data De Anivers\u00E1rio:");
		lblDataDeAniversario.setDisplayedMnemonic(KeyEvent.VK_D);
		dadosBuscaPanel.add(lblDataDeAniversario, "cell 0 0");
		lblDataDeAniversario.setLabelFor(dataAniversarioTextField);
		
		dataAniversarioTextField = new JTextField();
		dataAniversarioTextField.setEditable(false);
		dadosBuscaPanel.add(dataAniversarioTextField, "cell 1 0,growx");
		dataAniversarioTextField.setColumns(10);
		
		JLabel lblTelefones = new JLabel("Telefone(s):");
		dadosBuscaPanel.add(lblTelefones, "cell 0 1");
		
		JScrollPane telScrollPane = new JScrollPane();
		dadosBuscaPanel.add(telScrollPane, "cell 1 1,grow");
		
		telefonesList = new JList<String>();

		telScrollPane.setViewportView(telefonesList);
		
		JLabel lblEmails = new JLabel("E-Mail(s):");
		dadosBuscaPanel.add(lblEmails, "cell 0 2");
		
		JScrollPane emailScrollPane = new JScrollPane();
		dadosBuscaPanel.add(emailScrollPane, "cell 1 2,grow");
		
		emailList = new JList<String>();
		emailScrollPane.setViewportView(emailList);
		
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
				JButton fecharButton = new JButton("Fechar");
				fecharButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgConsultarPessoa.this.dispose();
					}
				});
				fecharButton.setIcon(new ImageIcon(IgCadastrarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				fecharButton.setMnemonic(KeyEvent.VK_F);
				fecharButton.setActionCommand("Cancel");
				buttonPane.add(fecharButton);
			}
		}
		
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor

	private void inserirInformacoesConsulta() {
		inserirModelosListas(null, null);
		
		if(ValidarDados.validarVazio(nomesComboBox.getSelectedItem().toString())) {
			int index = nomesComboBox.getSelectedIndex() - 1;
			
			DefaultListModel<String> modeloTel = new DefaultListModel<String>();
			DefaultListModel<String> modeloEmail = new DefaultListModel<String>();
			
			ArrayList<Telefone> arrayListTel = new Telefone(arrayListPessoas.get(index).getCodigoPessoa()).pesquisar();
			ArrayList<Email> arrayListEmail = new Email(arrayListPessoas.get(index).getCodigoPessoa()).pesquisar();
			
			// Preenche o JList com os telefones do contato.
			for(Telefone t : arrayListTel) modeloTel.addElement(t.getNumero() + "  -  " + t.getTipo().getDescricao());
			
			// Preenche o JList com os e-mails do contato.
			for(Email e : arrayListEmail) modeloEmail.addElement(e.getEmail());
			
			dataAniversarioTextField.setText( new SimpleDateFormat("dd/MM").format(arrayListPessoas.get(index).getDataDeAniversario()));
			
			inserirModelosListas(modeloTel, modeloEmail);
		}
		else
			dataAniversarioTextField.setText("");
	}
	
	private void inserirModelosListas(DefaultListModel<String> modeloTel, DefaultListModel<String> modeloEmail) {
		if(modeloTel != null)
			telefonesList.setModel(modeloTel);
		else
			telefonesList.setModel(new DefaultListModel<String>());
		
		if(modeloEmail != null)
			emailList.setModel(modeloEmail);
		else
			emailList.setModel(new DefaultListModel<String>());
	}
	
} // class IgConsultarPessoa
