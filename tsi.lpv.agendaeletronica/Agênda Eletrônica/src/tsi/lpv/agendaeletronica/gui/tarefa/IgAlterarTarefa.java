package tsi.lpv.agendaeletronica.gui.tarefa;

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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.entidades.tarefa.Compromisso;
import tsi.lpv.agendaeletronica.entidades.tarefa.Tarefa;
import tsi.lpv.agendaeletronica.eventos.tarefa.TEActionIgAltTarefa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;
import tsi.lpv.agendaeletronica.gui.pessoa.IgCadastrarPessoa;

public class IgAlterarTarefa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 6742046009340636824L;

	public final String NOME_MODULO;
	
	private Color corPadraoLblNovaDescricao;
	private Color corPadraoLblNovaData;
	private Color corPadraoLblNovaHora;
	
	private ArrayList<Tarefa> arrayListTarefas;
	private ArrayList<Pessoa> arrayListPessoasIn;
	private ArrayList<Pessoa> arrayListPessoasRe;
	private Vector<String> vectorTarefas;
	
	private Date dataHoraAntiga;
	private String descricaoAntiga;
	
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> tarefasComboBox;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JLabel lblData;
	private JLabel lblRemoverPessoas;
	private JTextField descricaoTextField;
	private JLabel lblDescricao;
	private JButton btnAlterar;
	private JLabel lblCampoErrado;
	private JLabel lblHora;
	private JList<String> inserirPessoasList;
	private JList<String> removerPessoasList;
	private JFormattedTextField horaFormattedTextField;

	/**
	 * Create the dialog.
	 */
	public IgAlterarTarefa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Alterar Tarefa";
		
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
		labelImg.setIcon(new ImageIcon(IgAlterarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Task-01-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Selecione a data da tarefa que deseja alterar:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		
		JPanel dadosPanel = new JPanel();
		
		contentPanel.add(dadosPanel, BorderLayout.CENTER);

		dadosPanel.setLayout(new MigLayout("", "[116px,grow][202px,grow]", "[28px][28px,grow]"));
		
		JLabel lblTarefa = new JLabel("Tarefa:");
		lblTarefa.setDisplayedMnemonic(KeyEvent.VK_T);
		dadosPanel.add(lblTarefa, "cell 0 0,alignx trailing");
		
		arrayListTarefas = new Tarefa().pesquisar();
		vectorTarefas = new Vector<String>();
		
		Collections.sort(arrayListTarefas, new Tarefa());
		
		vectorTarefas.add("");
		
		// Preenche o vector com as tarefas.
		for(Tarefa t : arrayListTarefas) vectorTarefas.add(new SimpleDateFormat("dd/MM/yyyy").format(t.getDataHora()) + " - " + t.getDescricao());
		
		tarefasComboBox = new JComboBox<String>(new DefaultComboBoxModel<String>(vectorTarefas)); 
		tarefasComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				inserirInformacoesConsulta();
			}
		});
		lblTarefa.setLabelFor(tarefasComboBox);
		dadosPanel.add(tarefasComboBox, "cell 1 0,growx");
		
		JPanel buscaPanel = new JPanel();
		dadosPanel.add(buscaPanel, "cell 0 1 2 1,grow");
		buscaPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorNome = new JSeparator();
		buscaPanel.add(separatorNome, BorderLayout.NORTH);
		
		JPanel dadosBuscaPanel = new JPanel();
		buscaPanel.add(dadosBuscaPanel, BorderLayout.CENTER);
		dadosBuscaPanel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][grow][grow]"));
		
		lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setDisplayedMnemonic(KeyEvent.VK_D);
		corPadraoLblNovaDescricao = lblDescricao.getForeground();
		dadosBuscaPanel.add(lblDescricao, "cell 0 0");
		
		descricaoTextField = new JTextField();
		lblDescricao.setLabelFor(descricaoTextField);
		dadosBuscaPanel.add(descricaoTextField, "cell 1 0,growx");
		descricaoTextField.setColumns(10);
		
		lblData = new JLabel("Data:");
		lblData.setDisplayedMnemonic(KeyEvent.VK_A);
		corPadraoLblNovaData = lblData.getForeground();
		dadosBuscaPanel.add(lblData, "cell 0 1");
		datePicker = new JDatePickerImpl(datePanel);
		lblData.setLabelFor(datePicker.getJFormattedTextField());
		dadosBuscaPanel.add(datePicker, "cell 1 1");
		
		lblHora = new JLabel("Hora:");
		lblHora.setDisplayedMnemonic(KeyEvent.VK_H);
		corPadraoLblNovaHora = lblHora.getForeground();
		dadosBuscaPanel.add(lblHora, "cell 0 2");
		
		horaFormattedTextField = new JFormattedTextField(new Mascara("##:##"));
		lblHora.setLabelFor(horaFormattedTextField);
		dadosBuscaPanel.add(horaFormattedTextField, "cell 1 2,growx");
		
		JPanel divisorPanel = new JPanel();
		dadosBuscaPanel.add(divisorPanel, "cell 0 3 2 1,grow");
		divisorPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		divisorPanel.add(separator, BorderLayout.CENTER);
		
		JLabel lblInserirPessoas = new JLabel("Inserir Pessoa(s):");
		lblInserirPessoas.setDisplayedMnemonic(KeyEvent.VK_I);
		dadosBuscaPanel.add(lblInserirPessoas, "cell 0 4");
		
		lblRemoverPessoas = new JLabel("Remover Pessoa(s):");
		lblRemoverPessoas.setDisplayedMnemonic(KeyEvent.VK_R);
		
		JScrollPane removerScrollPane = new JScrollPane();
		dadosBuscaPanel.add(removerScrollPane, "cell 1 4,grow");
		
		inserirPessoasList = new JList<String>();
		lblInserirPessoas.setLabelFor(inserirPessoasList);
		removerScrollPane.setViewportView(inserirPessoasList);
		dadosBuscaPanel.add(lblRemoverPessoas, "cell 0 5");
		
		JScrollPane inserirScrollPane = new JScrollPane();
		dadosBuscaPanel.add(inserirScrollPane, "cell 1 5,grow");
		
		removerPessoasList = new JList<String>();
		lblRemoverPessoas.setLabelFor(removerPessoasList);
		inserirScrollPane.setViewportView(removerPessoasList);
		
		JPanel msgErroPanel = new JPanel();
		contentPanel.add(msgErroPanel, BorderLayout.SOUTH);
		msgErroPanel.setLayout(new BorderLayout(0, 0));
		
		lblCampoErrado = new JLabel("");
		lblCampoErrado.setForeground(Color.RED);
		lblCampoErrado.setVisible(false);
		lblCampoErrado.setIcon(new ImageIcon(IgAlterarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Warning-Message-24.png")));
		msgErroPanel.add(lblCampoErrado, BorderLayout.NORTH);
		
		JSeparator separatorBtn = new JSeparator();
		msgErroPanel.add(separatorBtn, BorderLayout.SOUTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnAlterar = new JButton("Alterar");
			btnAlterar.addActionListener(new TEActionIgAltTarefa(this));
			btnAlterar.setEnabled(false);
			btnAlterar.setIcon(new ImageIcon(IgAlterarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Reload-16.png")));
			btnAlterar.setMnemonic(KeyEvent.VK_A);
			btnAlterar.setActionCommand("OK");
			buttonPane.add(btnAlterar);
			{
				JButton cancelarButton = new JButton("Cancelar");
				cancelarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgAlterarTarefa.this.dispose();
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
		inserirModelosListas(null, null);
		
		if(ValidarDados.validarVazio(tarefasComboBox.getSelectedItem().toString())) {
			int index = tarefasComboBox.getSelectedIndex() - 1;
			Calendar cal = Calendar.getInstance();
			
			DefaultListModel<String> modeloInserir = new DefaultListModel<String>();
			DefaultListModel<String> modeloRemover = new DefaultListModel<String>();
			
			Compromisso compromisso = new Compromisso();
			compromisso.setCodigoTarefa(arrayListTarefas.get(index).getCodigoTarefa());
			ArrayList<Pessoa> arrayListPessoas = new Pessoa().pesquisar();
			ArrayList<Compromisso> arrayListCompromissos = compromisso.pesquisarTarefa();
			
			Collections.sort(arrayListPessoas, new Pessoa());
			
			arrayListPessoasRe = new ArrayList<Pessoa>();
			arrayListPessoasIn = new ArrayList<Pessoa>();
			
			// Preenche o JList inserir com os nomes das pessoas.
			for(int i = 0; i < arrayListCompromissos.size(); i++) {
				for(int j = 0; j < arrayListPessoas.size(); j++)
					if(arrayListCompromissos.get(i).getCodigoPessoa() == arrayListPessoas.get(j).getCodigoPessoa()) {
						modeloRemover.addElement(arrayListPessoas.get(j).getNome());
						arrayListPessoasRe.add(arrayListPessoas.get(j));
						arrayListPessoas.remove(j);
						break;
					}
			}
			
			// Preenche o JList remover com os nomes das pessoas.
			for(Pessoa p : arrayListPessoas) {
				modeloInserir.addElement(p.getNome());
				arrayListPessoasIn.add(p);
			}
				
			// Preenche o campo com a data.
			cal.setTime(arrayListTarefas.get(index).getDataHora());
			model.setValue(cal.getTime());
			dataHoraAntiga = cal.getTime();
			
			// Preenche o campo com a hora.
			horaFormattedTextField.setText(new SimpleDateFormat("HH:mm").format(cal.getTime()));
			
			// Preeche o campo com a descrição da tarefa.
			descricaoTextField.setText(arrayListTarefas.get(index).getDescricao());
			descricaoAntiga = descricaoTextField.getText();
			
			inserirModelosListas(modeloInserir, modeloRemover);
		}
	}
	
	private void inserirModelosListas(DefaultListModel<String> modeloInserir, DefaultListModel<String> modeloRemover) {
		if(modeloInserir != null)
			inserirPessoasList.setModel(modeloInserir);
		else
			inserirPessoasList.setModel(new DefaultComboBoxModel<String>());
		
		if(modeloRemover != null)
			removerPessoasList.setModel(modeloRemover);
		else
			removerPessoasList.setModel(new DefaultComboBoxModel<String>());
	}
	
	public void exibirMsgCamposErrados(String msg, JLabel label) {
		label.setForeground(Color.RED);
		lblCampoErrado.setText(msg);
		lblCampoErrado.setVisible(true);
		
		// Coloca o foco no componente que não foi preenchido corretamente.
		if(label.equals(lblDescricao)) {
			descricaoTextField.requestFocus();
			lblData.setForeground(corPadraoLblNovaData);
			lblHora.setForeground(corPadraoLblNovaHora);
		}
		else if(label.equals(lblData)) {
			datePicker.requestFocus();
			lblDescricao.setForeground(corPadraoLblNovaDescricao);
			lblHora.setForeground(corPadraoLblNovaHora);
		}
		else if(label.equals(lblHora)) {
			datePicker.requestFocus();
			lblDescricao.setForeground(corPadraoLblNovaDescricao);
			lblData.setForeground(corPadraoLblNovaData);
		}
	}
	
	public void esconderMsgCamposErrados() {
		lblDescricao.setForeground(corPadraoLblNovaDescricao);
		lblData.setForeground(corPadraoLblNovaData);
		lblHora.setForeground(corPadraoLblNovaHora);
		lblCampoErrado.setVisible(false);
	}
	
	private void verificarItemSelecionado() {
		if(ValidarDados.validarVazio(tarefasComboBox.getSelectedItem().toString()))
			btnAlterar.setEnabled(true);
		else {
			btnAlterar.setEnabled(false);
			descricaoTextField.setText("");
			model.setValue(null);
			horaFormattedTextField.setText("");
		}
	}

	public JLabel getLblData() {
		return lblData;
	}

	public JLabel getLblDescricao() {
		return lblDescricao;
	}

	public JLabel getLblHora() {
		return lblHora;
	}
	
	public JTextField getDescricaoTextField() {
		return descricaoTextField;
	}

	public Date getValorDatePicker() {
		return ((Date) datePicker.getModel().getValue());
	}

	public JFormattedTextField getHoraFormattedTextField() {
		return horaFormattedTextField;
	}

	public ArrayList<Tarefa> getArrayListTarefas() {
		return arrayListTarefas;
	}

	public ArrayList<Pessoa> getArrayListPessoasIn() {
		return arrayListPessoasIn;
	}

	public ArrayList<Pessoa> getArrayListPessoasRe() {
		return arrayListPessoasRe;
	}

	public JList<String> getInserirPessoasList() {
		return inserirPessoasList;
	}

	public JList<String> getRemoverPessoasList() {
		return removerPessoasList;
	}

	public JComboBox<String> getTarefasComboBox() {
		return tarefasComboBox;
	}

	public Date getDataHoraAntiga() {
		return dataHoraAntiga;
	}

	public String getDescricaoAntiga() {
		return descricaoAntiga;
	}

	public JButton getBtnAlterar() {
		return btnAlterar;
	}
	
} // class IgAlterarTarefa
