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
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.entidades.tarefa.Compromisso;
import tsi.lpv.agendaeletronica.entidades.tarefa.Tarefa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgConsultarTarefa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -5737591566333343412L;

	public final String NOME_MODULO;
	
	private ArrayList<Tarefa> arrayListTarefas;
	private Vector<String> vectorTarefas;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField dataTextField;
	private JComboBox<String> tarefasComboBox;
	private JList<String> pessoasList;
	private JTextField horaTextField;
	private JTextField descricaoTextField;

	/**
	 * Create the dialog.
	 */
	public IgConsultarTarefa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Consultar Tarefa";
		
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
		labelImg.setIcon(new ImageIcon(IgConsultarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Task-01-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Selecione a data da tarefa que deseja consultar:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
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
		dadosBuscaPanel.setLayout(new MigLayout("", "[][grow]", "[][][][grow]"));
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setDisplayedMnemonic(KeyEvent.VK_D);
		dadosBuscaPanel.add(lblDescrio, "cell 0 0,alignx trailing");
		
		descricaoTextField = new JTextField();
		descricaoTextField.setEditable(false);
		lblDescrio.setLabelFor(descricaoTextField);
		dadosBuscaPanel.add(descricaoTextField, "cell 1 0,growx");
		descricaoTextField.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setDisplayedMnemonic(KeyEvent.VK_A);
		dadosBuscaPanel.add(lblData, "cell 0 1");
		
		dataTextField = new JTextField();
		lblData.setLabelFor(dataTextField);
		dataTextField.setEditable(false);
		dadosBuscaPanel.add(dataTextField, "cell 1 1,growx");
		dataTextField.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setDisplayedMnemonic(KeyEvent.VK_H);
		dadosBuscaPanel.add(lblHora, "cell 0 2");
		
		horaTextField = new JTextField();
		horaTextField.setEditable(false);
		lblHora.setLabelFor(horaTextField);
		dadosBuscaPanel.add(horaTextField, "cell 1 2,growx");
		horaTextField.setColumns(10);
		
		JLabel lblPessoas = new JLabel("Pessoa(s):");
		lblPessoas.setDisplayedMnemonic(KeyEvent.VK_P);
		dadosBuscaPanel.add(lblPessoas, "cell 0 3");
		
		JScrollPane pessoaScrollPane = new JScrollPane();
		dadosBuscaPanel.add(pessoaScrollPane, "cell 1 3,grow");
		
		pessoasList = new JList<String>();
		lblPessoas.setLabelFor(pessoasList);
		pessoaScrollPane.setViewportView(pessoasList);
		
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
				fecharButton.setSelectedIcon(new ImageIcon(IgConsultarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				fecharButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgConsultarTarefa.this.dispose();
					}
				});
				fecharButton.setIcon(new ImageIcon(IgConsultarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				fecharButton.setMnemonic(KeyEvent.VK_F);
				fecharButton.setActionCommand("Cancel");
				buttonPane.add(fecharButton);
			}
		}
		
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor

	private void inserirInformacoesConsulta() {
		inserirModelosListas(null);
		
		if(ValidarDados.validarVazio(tarefasComboBox.getSelectedItem().toString())) {
			int index = tarefasComboBox.getSelectedIndex() - 1;
			
			DefaultListModel<String> modelo = new DefaultListModel<String>();
			
			Compromisso compromisso = new Compromisso();
			compromisso.setCodigoTarefa(arrayListTarefas.get(index).getCodigoTarefa());
			ArrayList<Pessoa> arrayListPessoas = new Pessoa().pesquisar();
			ArrayList<Compromisso> arrayListCompromissos = compromisso.pesquisarTarefa();
			
			Collections.sort(arrayListPessoas, new Pessoa());
			
			// Preenche o JList com os nomes das pessoas.
			for(Pessoa p : arrayListPessoas)
				for(Compromisso c : arrayListCompromissos) {
					if(p.getCodigoPessoa() == c.getCodigoPessoa()) {
						modelo.addElement(p.getNome());
						break;
					}
				}
			
			descricaoTextField.setText(arrayListTarefas.get(index).getDescricao());
			dataTextField.setText( new SimpleDateFormat("dd/MM/yyyy").format(arrayListTarefas.get(index).getDataHora()));
			horaTextField.setText( new SimpleDateFormat("HH:mm").format(arrayListTarefas.get(index).getDataHora()));
			
			inserirModelosListas(modelo);
		}
		else {
			descricaoTextField.setText("");
			dataTextField.setText("");
			horaTextField.setText("");
		}
	}
	
	private void inserirModelosListas(DefaultListModel<String> modelo) {
		if(modelo != null)
			pessoasList.setModel(modelo);
		else
			pessoasList.setModel(new DefaultListModel<String>());
	}
	
} // class IgConsultarTarefa
