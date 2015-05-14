package tsi.lpv.agendaeletronica.gui.tarefa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tsi.lpv.agendaeletronica.entidades.Mascara;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.eventos.tarefa.TEActionIgCadTarefa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgCadastrarTarefa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 5010114376299507306L;
	
	public final String NOME_MODULO;
	
	private ArrayList<Pessoa> arrayListPessoas;
	
	private Color corPadraoLblDescricao;
	private Color corPadraoLblData;
	private Color corPadraoLblHora;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField descricaoTextField;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JButton cadastrarButton;
	private JLabel lblCampoErrado;
	private JLabel lblDescricao;
	private JLabel lblData;
	private JLabel lblHora;
	private JFormattedTextField horaFormattedTextField;
	private JList<String> pessoasList;

	/**
	 * Create the dialog.
	 */
	public IgCadastrarTarefa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Cadastrar Tarefa";
		
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
		labelImg.setIcon(new ImageIcon(IgCadastrarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Task-01-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Insira os dados da tarefa:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JPanel dadosPanel = new JPanel();
		contentPanel.add(dadosPanel, BorderLayout.CENTER);
		
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		
		descricaoTextField = new JTextField();
		descricaoTextField.setColumns(10);
		datePicker = new JDatePickerImpl(datePanel);
		dadosPanel.setLayout(new MigLayout("", "[116px,grow][202px,grow]", "[28px][28px][][][28px,grow]"));
		
		lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setLabelFor(descricaoTextField);
		lblDescricao.setDisplayedMnemonic(KeyEvent.VK_D);
		corPadraoLblDescricao = lblDescricao.getForeground();
		dadosPanel.add(lblDescricao, "cell 0 0");
		dadosPanel.add(descricaoTextField, "cell 1 0,growx,aligny top");
		
		lblData = new JLabel("Data:");
		lblData.setLabelFor(datePicker.getJFormattedTextField());
		lblData.setDisplayedMnemonic(KeyEvent.VK_T);
		corPadraoLblData = lblData.getForeground();
		dadosPanel.add(lblData, "cell 0 1");
		dadosPanel.add(datePicker, "cell 1 1,growx,aligny top");
		
		JPanel msgErroPanel = new JPanel();
		contentPanel.add(msgErroPanel, BorderLayout.SOUTH);
		msgErroPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorBtn = new JSeparator();
		msgErroPanel.add(separatorBtn, BorderLayout.SOUTH);
		
		lblCampoErrado = new JLabel("");
		lblCampoErrado.setVisible(false);
		lblCampoErrado.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblCampoErrado.setForeground(Color.RED);
		lblCampoErrado.setIcon(new ImageIcon(IgCadastrarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Warning-Message-24.png")));
		msgErroPanel.add(lblCampoErrado, BorderLayout.NORTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			lblHora = new JLabel("Hora:");
			lblHora.setDisplayedMnemonic(KeyEvent.VK_H);
			corPadraoLblHora = lblHora.getForeground();
			dadosPanel.add(lblHora, "cell 0 2");
			lblHora.setLabelFor(horaFormattedTextField);
			
			horaFormattedTextField = new JFormattedTextField(new Mascara("##:##"));
			dadosPanel.add(horaFormattedTextField, "cell 1 2,growx,aligny top");
			
			JPanel Divisorpanel = new JPanel();
			dadosPanel.add(Divisorpanel, "cell 0 3 2 1,grow");
			Divisorpanel.setLayout(new BorderLayout(0, 0));
			
			JSeparator separatorLista = new JSeparator();
			Divisorpanel.add(separatorLista, BorderLayout.NORTH);
			
			JLabel lblPessoas = new JLabel("Pessoas:");
			lblPessoas.setDisplayedMnemonic(KeyEvent.VK_P);
			dadosPanel.add(lblPessoas, "cell 0 4");
			
			JScrollPane scrollPane = new JScrollPane();
			dadosPanel.add(scrollPane, "cell 1 4,grow");
			
			pessoasList = new JList<String>();
			lblPessoas.setLabelFor(pessoasList);
			scrollPane.setViewportView(pessoasList);
			{
				cadastrarButton = new JButton("Cadastrar");
				cadastrarButton.addActionListener(new TEActionIgCadTarefa(this));
				cadastrarButton.setIcon(new ImageIcon(IgCadastrarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Add-New-16.png")));
				cadastrarButton.setMnemonic(KeyEvent.VK_C);
				cadastrarButton.setActionCommand("OK");
				buttonPane.add(cadastrarButton);
				getRootPane().setDefaultButton(cadastrarButton);
			}
			{
				JButton cancelarButton = new JButton("Cancelar");
				cancelarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgCadastrarTarefa.this.dispose();
					}
				});
				cancelarButton.setIcon(new ImageIcon(IgCadastrarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				cancelarButton.setMnemonic(KeyEvent.VK_A);
				cancelarButton.setActionCommand("Cancel");
				buttonPane.add(cancelarButton);
			}
		}
		
		inserirPessoasLista();
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor
	
	private void inserirPessoasLista() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		
		arrayListPessoas = new Pessoa().pesquisar();
		
		Collections.sort(arrayListPessoas, new Pessoa());
		
		// Preenche a JList com as pessoas.
		for(Pessoa p : arrayListPessoas) modelo.addElement(p.getNome());
		
		pessoasList.setModel(modelo);
	}
	
	public void exibirMsgCamposErrados(String msg, JLabel label) {
		label.setForeground(Color.RED);
		lblCampoErrado.setText(msg);
		lblCampoErrado.setVisible(true);
		
		// Coloca o foco no componente que não foi preenchido corretamente.
		if(label.equals(lblDescricao)) {
			descricaoTextField.requestFocus();
			lblData.setForeground(corPadraoLblData);
			lblHora.setForeground(corPadraoLblHora);
		}
		else if(label.equals(lblData)) {
			datePicker.requestFocus();
			lblDescricao.setForeground(corPadraoLblDescricao);
			lblHora.setForeground(corPadraoLblHora);
		}
		else if(label.equals(lblHora)) {
			horaFormattedTextField.requestFocus();
			lblDescricao.setForeground(corPadraoLblDescricao);
			lblData.setForeground(corPadraoLblData);
		}
	}
	
	public void esconderMsgCamposErrados() {
		lblDescricao.setForeground(corPadraoLblDescricao);
		lblData.setForeground(corPadraoLblData);
		lblHora.setForeground(corPadraoLblHora);
		lblCampoErrado.setVisible(false);
	}

	public ArrayList<Pessoa> getArrayListPessoas() {
		return arrayListPessoas;
	}

	public JTextField getDescricaoTextField() {
		return descricaoTextField;
	}

	public JFormattedTextField getHoraFormattedTextField() {
		return horaFormattedTextField;
	}

	public Date getValorDatePicker() {
		return ((Date) datePicker.getModel().getValue());
	}

	public JLabel getLblDescricao() {
		return lblDescricao;
	}

	public JLabel getLblData() {
		return lblData;
	}

	public JLabel getLblHora() {
		return lblHora;
	}

	public JList<String> getPessoasList() {
		return pessoasList;
	}

	public JButton getCadastrarButton() {
		return cadastrarButton;
	}
	
} // class IgCadastrarTarefa
