package tsi.lpv.agendaeletronica.gui.contato;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import tsi.lpv.agendaeletronica.entidades.Mascara;
import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.contato.TipoTel;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.eventos.contato.TEActionIgCadTel;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class IgCadastrarTel extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -7228357994503672768L;
	
	private Color corPadraoLblTel;

	public final String NOME_MODULO;
	
	private ArrayList<Pessoa> arrayListPessoas;
	private Vector<String> vectorPessoas;
	
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> nomesComboBox;
	private JButton btnInserir;
	private JFormattedTextField telFormattedTextField;
	private JLabel lblCampoErrado;
	private JLabel lblTelefone;
	private final ButtonGroup tipoButtonGroup = new ButtonGroup();
	private JRadioButton rdbtnFixo;
	private JRadioButton rdbtnMovel;
	private JPanel dadosBuscaPanel;

	/**
	 * Create the dialog.
	 */
	public IgCadastrarTel(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Cadastrar Telefone";
		
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
		labelImg.setIcon(new ImageIcon(IgCadastrarTel.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Telephone-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Selecione o nome da pessoa e insira o telefone:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JPanel dadosPanel = new JPanel();
		contentPanel.add(dadosPanel, BorderLayout.CENTER);

		dadosPanel.setLayout(new MigLayout("", "[116px,grow][202px,grow]", "[28px][28px,grow][]"));
		
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
				verificarItemSelecionado();
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
		dadosBuscaPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setDisplayedMnemonic(KeyEvent.VK_T);
		corPadraoLblTel = lblTelefone.getForeground();
		dadosBuscaPanel.add(lblTelefone, "cell 0 0,alignx trailing");
		
		telFormattedTextField = new JFormattedTextField(new Mascara("(##)####-####"));
		lblTelefone.setLabelFor(telFormattedTextField);
		dadosBuscaPanel.add(telFormattedTextField, "cell 1 0,growx");
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setDisplayedMnemonic(KeyEvent.VK_I);
		dadosBuscaPanel.add(lblTipo, "cell 0 1");
		
		rdbtnFixo = new JRadioButton(TipoTel.FIXO.getDescricao());
		lblTipo.setLabelFor(rdbtnFixo);
		rdbtnFixo.setMnemonic(KeyEvent.VK_F);
		rdbtnFixo.setSelected(true);
		tipoButtonGroup.add(rdbtnFixo);
		dadosBuscaPanel.add(rdbtnFixo, "flowx,cell 1 1");
		
		rdbtnMovel = new JRadioButton(TipoTel.MOVEL.getDescricao());
		rdbtnMovel.setMnemonic(KeyEvent.VK_M);
		tipoButtonGroup.add(rdbtnMovel);
		dadosBuscaPanel.add(rdbtnMovel, "cell 1 1,alignx center");
		
		JPanel msgErroPanel = new JPanel();
		contentPanel.add(msgErroPanel, BorderLayout.SOUTH);
		msgErroPanel.setLayout(new BorderLayout(0, 0));
		
		lblCampoErrado = new JLabel("");
		lblCampoErrado.setVisible(false);
		lblCampoErrado.setForeground(Color.RED);
		lblCampoErrado.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblCampoErrado.setIcon(new ImageIcon(IgCadastrarTel.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Warning-Message-24.png")));
		msgErroPanel.add(lblCampoErrado, BorderLayout.NORTH);
		
		JSeparator separatorBtn = new JSeparator();
		msgErroPanel.add(separatorBtn, BorderLayout.SOUTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnInserir = new JButton("Inserir");
			btnInserir.setEnabled(false);
			btnInserir.addActionListener(new TEActionIgCadTel(IgCadastrarTel.this));
			btnInserir.setIcon(new ImageIcon(IgCadastrarTel.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Add-New-16.png")));
			btnInserir.setMnemonic(KeyEvent.VK_I);
			btnInserir.setActionCommand("OK");
			buttonPane.add(btnInserir);
			{
				JButton fecharButton = new JButton("Fechar");
				fecharButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgCadastrarTel.this.dispose();
					}
				});
				fecharButton.setIcon(new ImageIcon(IgCadastrarTel.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				fecharButton.setMnemonic(KeyEvent.VK_F);
				fecharButton.setActionCommand("Cancel");
				buttonPane.add(fecharButton);
			}
		}
		
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor
	
	public void exibirMsgCamposErrados(String msg, JLabel label) {
		label.setForeground(Color.RED);
		lblCampoErrado.setText(msg);
		lblCampoErrado.setVisible(true);
		
		// Coloca o foco no componente que não foi preenchido corretamente.
		if(label.equals(lblTelefone))
			telFormattedTextField.requestFocus();
	}
	
	public void esconderMsgCamposErrados() {
		lblTelefone.setForeground(corPadraoLblTel);
		lblCampoErrado.setVisible(false);
	}
	
	public String obterRadioBtnSelecionado() {
		String radioBtnTxt = null;
		
		if(rdbtnFixo.isSelected())
			radioBtnTxt = rdbtnFixo.getText();
		else if(rdbtnMovel.isSelected())
			radioBtnTxt = rdbtnMovel.getText();
		
		return radioBtnTxt;
	}
	
	private void verificarItemSelecionado() {
		if(ValidarDados.validarVazio(nomesComboBox.getSelectedItem().toString()))
			btnInserir.setEnabled(true);
		else
			btnInserir.setEnabled(false);
			
	}

	public JButton getBtnInserir() {
		return btnInserir;
	}

	public JLabel getLblTelefone() {
		return lblTelefone;
	}

	public JComboBox<String> getNomesComboBox() {
		return nomesComboBox;
	}

	public JFormattedTextField getTelFormattedTextField() {
		return telFormattedTextField;
	}

	public ArrayList<Pessoa> getArrayListPessoas() {
		return arrayListPessoas;
	}
	
} // class IgConsultarPessoa
