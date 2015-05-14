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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tsi.lpv.agendaeletronica.entidades.Mascara;
import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.contato.Email;
import tsi.lpv.agendaeletronica.entidades.contato.Telefone;
import tsi.lpv.agendaeletronica.entidades.contato.TipoTel;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.eventos.pessoa.TEActionIgAltPessoa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgAlterarPessoa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -7228357994503672768L;

	public final String NOME_MODULO;
	
	private Color corPadraoLblNovoNome;
	private Color corPadraoLblNovaDataDeNascimento;
	private Color corPadraoLblNovoNumero;
	private Color corPadraoLblNovoEmail;
	
	private ArrayList<Pessoa> arrayListPessoas;
	private Vector<String> vectorPessoas;
	private ArrayList<TipoTel> arrayListTipoTel;
	
	private Date dataAntiga;
	
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> nomesComboBox;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JTextField novoEmailTextField;
	private JComboBox<String> telefonesComboBox;
	private JComboBox<String> emailsComboBox;
	private JLabel lblNovaDataDeNascimento;
	private JLabel lblNovoNumero;
	private JLabel lblNovoEmail;
	private JTextField novoNomeTextField;
	private JLabel lblNovoNome;
	private JButton btnAlterar;
	private JLabel lblCampoErrado;
	private JFormattedTextField novoNumeroFormattedTextField;
	private final ButtonGroup tipoButtonGroup = new ButtonGroup();
	private JRadioButton rdbtnFixo;
	private JRadioButton rdbtnMovel;

	/**
	 * Create the dialog.
	 */
	public IgAlterarPessoa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Alterar Pessoa";
		
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
		labelImg.setIcon(new ImageIcon(IgAlterarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/User-Profile-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Selecione o nome da pessoa que deseja alterar:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		
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
		dadosBuscaPanel.setLayout(new MigLayout("", "[grow][grow]", "[][][grow][][][][grow][][]"));
		
		lblNovoNome = new JLabel("Nome:");
		lblNovoNome.setDisplayedMnemonic(KeyEvent.VK_O);
		corPadraoLblNovoNome = lblNovoNome.getForeground();
		dadosBuscaPanel.add(lblNovoNome, "cell 0 0");
		
		novoNomeTextField = new JTextField();
		lblNovoNome.setLabelFor(novoNomeTextField);
		dadosBuscaPanel.add(novoNomeTextField, "cell 1 0,growx");
		novoNomeTextField.setColumns(10);
		
		lblNovaDataDeNascimento = new JLabel("Data De Nascimento:");
		lblNovaDataDeNascimento.setDisplayedMnemonic(KeyEvent.VK_D);
		corPadraoLblNovaDataDeNascimento = lblNovaDataDeNascimento.getForeground();
		dadosBuscaPanel.add(lblNovaDataDeNascimento, "cell 0 1");
		datePicker = new JDatePickerImpl(datePanel);
		lblNovaDataDeNascimento.setLabelFor(datePicker.getJFormattedTextField());
		dadosBuscaPanel.add(datePicker, "cell 1 1");
		
		JPanel divisorPanel1 = new JPanel();
		dadosBuscaPanel.add(divisorPanel1, "cell 0 2 2 1,grow");
		divisorPanel1.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator1 = new JSeparator();
		divisorPanel1.add(separator1, BorderLayout.CENTER);
		
		JLabel lblTelefones = new JLabel("Telefones:");
		lblTelefones.setDisplayedMnemonic(KeyEvent.VK_T);
		dadosBuscaPanel.add(lblTelefones, "cell 0 3");
		
		telefonesComboBox = new JComboBox<String>();
		telefonesComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				inserirTipoTel();
			}
		});
		lblTelefones.setLabelFor(telefonesComboBox);
		dadosBuscaPanel.add(telefonesComboBox, "cell 1 3,growx");
		
		lblNovoNumero = new JLabel("Novo N\u00FAmero:");
		lblNovoNumero.setDisplayedMnemonic(KeyEvent.VK_V);
		corPadraoLblNovoNumero = lblNovoNumero.getForeground();
		dadosBuscaPanel.add(lblNovoNumero, "cell 0 4");
		
		novoNumeroFormattedTextField = new JFormattedTextField(new Mascara("(##)####-####"));
		lblNovoNumero.setLabelFor(novoNumeroFormattedTextField);
		dadosBuscaPanel.add(novoNumeroFormattedTextField, "cell 1 4,growx");
		
		JLabel lblNovoTipo = new JLabel("Novo Tipo:");
		lblNovoTipo.setDisplayedMnemonic(KeyEvent.VK_T);
		dadosBuscaPanel.add(lblNovoTipo, "cell 0 5");
		
		rdbtnFixo = new JRadioButton("Fixo");
		lblNovoTipo.setLabelFor(rdbtnFixo);
		tipoButtonGroup.add(rdbtnFixo);
		rdbtnFixo.setSelected(true);
		rdbtnFixo.setMnemonic(KeyEvent.VK_F);
		dadosBuscaPanel.add(rdbtnFixo, "flowx,cell 1 5");
		
		JPanel divisorPanel2 = new JPanel();
		dadosBuscaPanel.add(divisorPanel2, "cell 0 6 2 1,grow");
		divisorPanel2.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator2 = new JSeparator();
		divisorPanel2.add(separator2, BorderLayout.CENTER);
		
		JLabel lblEmails = new JLabel("E-Mails:");
		lblEmails.setDisplayedMnemonic(KeyEvent.VK_E);
		dadosBuscaPanel.add(lblEmails, "cell 0 7");
		
		emailsComboBox = new JComboBox<String>();
		lblEmails.setLabelFor(emailsComboBox);
		dadosBuscaPanel.add(emailsComboBox, "cell 1 7,growx");
		
		lblNovoEmail = new JLabel("Novo E-Mail:");
		lblNovoEmail.setDisplayedMnemonic(KeyEvent.VK_E);
		corPadraoLblNovoEmail = lblNovoEmail.getForeground();
		dadosBuscaPanel.add(lblNovoEmail, "cell 0 8");
		
		novoEmailTextField = new JTextField();
		lblNovoEmail.setLabelFor(novoEmailTextField);
		dadosBuscaPanel.add(novoEmailTextField, "cell 1 8,growx");
		novoEmailTextField.setColumns(10);
		
		rdbtnMovel = new JRadioButton("M\u00F3vel");
		tipoButtonGroup.add(rdbtnMovel);
		rdbtnMovel.setMnemonic(KeyEvent.VK_M);
		dadosBuscaPanel.add(rdbtnMovel, "cell 1 5");
		
		JPanel msgErroPanel = new JPanel();
		contentPanel.add(msgErroPanel, BorderLayout.SOUTH);
		msgErroPanel.setLayout(new BorderLayout(0, 0));
		
		lblCampoErrado = new JLabel("");
		lblCampoErrado.setForeground(Color.RED);
		lblCampoErrado.setVisible(false);
		lblCampoErrado.setIcon(new ImageIcon(IgAlterarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Warning-Message-24.png")));
		msgErroPanel.add(lblCampoErrado, BorderLayout.NORTH);
		
		JSeparator separatorBtn = new JSeparator();
		msgErroPanel.add(separatorBtn, BorderLayout.SOUTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnAlterar = new JButton("Alterar");
			btnAlterar.addActionListener(new TEActionIgAltPessoa(this));
			btnAlterar.setEnabled(false);
			btnAlterar.setIcon(new ImageIcon(IgAlterarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Reload-16.png")));
			btnAlterar.setMnemonic(KeyEvent.VK_A);
			btnAlterar.setActionCommand("OK");
			buttonPane.add(btnAlterar);
			{
				JButton cancelarButton = new JButton("Cancelar");
				cancelarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgAlterarPessoa.this.dispose();
					}
				});
				cancelarButton.setIcon(new ImageIcon(IgCadastrarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				cancelarButton.setMnemonic(KeyEvent.VK_C);
				cancelarButton.setActionCommand("Cancel");
				buttonPane.add(cancelarButton);
			}
		}
		
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor

	private void inserirInformacoesConsulta() {
		verificarItemSelecionado();
		inserirModelosComboBox(null, null);
		
		if(ValidarDados.validarVazio(nomesComboBox.getSelectedItem().toString())) {
			int index = nomesComboBox.getSelectedIndex() - 1;
			Calendar cal = Calendar.getInstance();
			
			DefaultComboBoxModel<String> modeloTel = new DefaultComboBoxModel<String>();
			DefaultComboBoxModel<String> modeloEmail = new DefaultComboBoxModel<String>();
			
			ArrayList<Telefone> arrayListTel = new Telefone(arrayListPessoas.get(index).getCodigoPessoa()).pesquisar();
			ArrayList<Email> arrayListEmail = new Email(arrayListPessoas.get(index).getCodigoPessoa()).pesquisar();
			
			arrayListTipoTel = new ArrayList<TipoTel>();
			
			// Preenche o Combobox com os telefones do contato.
			modeloTel.addElement("");
			for(Telefone t : arrayListTel) {
				modeloTel.addElement(t.getNumero());
				arrayListTipoTel.add(t.getTipo());
			}
			
			// Preenche o Combobox com os e-mails do contato.
			modeloEmail.addElement("");
			for(Email e : arrayListEmail) modeloEmail.addElement(e.getEmail());
			
			// Preenche o campo com a data de aniversário.
			cal.setTime(arrayListPessoas.get(index).getDataDeAniversario());
			model.setValue(cal.getTime());
			dataAntiga = model.getValue();
			
			// Preeche o campo com o nome da pessoa.
			novoNomeTextField.setText(arrayListPessoas.get(index).getNome());
			
			inserirModelosComboBox(modeloTel, modeloEmail);
		}
	}
	
	private void inserirModelosComboBox(DefaultComboBoxModel<String> modeloTel, DefaultComboBoxModel<String> modeloEmail) {
		if(modeloTel != null)
			telefonesComboBox.setModel(modeloTel);
		else
			telefonesComboBox.setModel(new DefaultComboBoxModel<String>());
		
		if(modeloEmail != null)
			emailsComboBox.setModel(modeloEmail);
		else
			emailsComboBox.setModel(new DefaultComboBoxModel<String>());
	}
	
	public void exibirMsgCamposErrados(String msg, JLabel label) {
		label.setForeground(Color.RED);
		lblCampoErrado.setText(msg);
		lblCampoErrado.setVisible(true);
		
		// Coloca o foco no componente que não foi preenchido corretamente.
		if(label.equals(lblNovoNome)) {
			novoNomeTextField.requestFocus();
			lblNovaDataDeNascimento.setForeground(corPadraoLblNovaDataDeNascimento);
			lblNovoNumero.setForeground(corPadraoLblNovoNumero);
			lblNovoEmail.setForeground(corPadraoLblNovoEmail);
		}
		else if(label.equals(lblNovaDataDeNascimento)) {
			datePicker.requestFocus();
			lblNovoNome.setForeground(corPadraoLblNovoNome);
			lblNovoNumero.setForeground(corPadraoLblNovoNumero);
			lblNovoEmail.setForeground(corPadraoLblNovoEmail);
		}
		else if(label.equals(lblNovoNumero)) {
			datePicker.requestFocus();
			lblNovoNome.setForeground(corPadraoLblNovoNome);
			lblNovaDataDeNascimento.setForeground(corPadraoLblNovoNumero);
			lblNovoEmail.setForeground(corPadraoLblNovoEmail);
		}
		else if(label.equals(lblNovoEmail)) {
			datePicker.requestFocus();
			lblNovoNome.setForeground(corPadraoLblNovoNome);
			lblNovaDataDeNascimento.setForeground(corPadraoLblNovoEmail);
			lblNovoNumero.setForeground(corPadraoLblNovoNumero);
		}
	}
	
	public void esconderMsgCamposErrados() {
		lblNovoNome.setForeground(corPadraoLblNovoNome);
		lblNovaDataDeNascimento.setForeground(corPadraoLblNovoEmail);
		lblNovoNumero.setForeground(corPadraoLblNovoNumero);
		lblNovoEmail.setForeground(corPadraoLblNovoEmail);
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
			btnAlterar.setEnabled(true);
		else {
			btnAlterar.setEnabled(false);
			model.setValue(null);
			novoNomeTextField.setText("");
		}
	}
	
	private void inserirTipoTel() {
		int index = telefonesComboBox.getSelectedIndex();
		
		if(index != 0 && arrayListTipoTel.get(telefonesComboBox.getSelectedIndex() - 1).getTipo() == TipoTel.FIXO.getTipo())
			rdbtnFixo.setSelected(true);
		else if(index != 0 && arrayListTipoTel.get(telefonesComboBox.getSelectedIndex() - 1).getTipo() == TipoTel.MOVEL.getTipo())
			rdbtnMovel.setSelected(true);
	}

	public JLabel getLblNovaDataDeNascimento() {
		return lblNovaDataDeNascimento;
	}

	public JLabel getLblNovoNumero() {
		return lblNovoNumero;
	}

	public JLabel getLblNovoEmail() {
		return lblNovoEmail;
	}

	public JLabel getLblNovoNome() {
		return lblNovoNome;
	}

	public JTextField getNovoNomeTextField() {
		return novoNomeTextField;
	}
	
	public Date getValorDatePicker() {
		return ((Date) datePicker.getModel().getValue());
	}

	public JFormattedTextField getNovoNumeroFormattedTextField() {
		return novoNumeroFormattedTextField;
	}

	public JTextField getNovoEmailTextField() {
		return novoEmailTextField;
	}

	public JComboBox<String> getNomesComboBox() {
		return nomesComboBox;
	}

	public JComboBox<String> getTelefonesComboBox() {
		return telefonesComboBox;
	}

	public JComboBox<String> getEmailsComboBox() {
		return emailsComboBox;
	}

	public ArrayList<Pessoa> getArrayListPessoas() {
		return arrayListPessoas;
	}

	public Date getDataAntiga() {
		return dataAntiga;
	}

	public JButton getBtnAlterar() {
		return btnAlterar;
	}
	
} // class IgAlterarPessoa
