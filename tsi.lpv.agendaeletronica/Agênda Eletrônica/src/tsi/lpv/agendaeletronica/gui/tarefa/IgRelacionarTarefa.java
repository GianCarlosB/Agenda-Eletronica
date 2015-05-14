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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.entidades.tarefa.Compromisso;
import tsi.lpv.agendaeletronica.entidades.tarefa.Tarefa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgRelacionarTarefa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 3501991275797970760L;

	public final String NOME_MODULO;
	
	private final int NUMERO_COLUNAS_TABELA = 3; // Número de colunas da tabela.
	private int num_linhas; // Número de linhas da tabela.
	
	private final String COLUNA_DESCRICAO = "Descrição";
	private final String COLUNA_DATA = "Data";
	private final String COLUNA_HORA = "Hora";
	private final String[] COLUNAS_TAREFA = {COLUNA_DESCRICAO, COLUNA_DATA, COLUNA_HORA};
	private String[][] linhasTabela = new String[0][NUMERO_COLUNAS_TABELA];
	
	private ArrayList<Pessoa> arrayListPessoas;
	private Vector<String> vectorPessoas;
	
	private final JPanel contentPanel = new JPanel();
	private JTable tabelaPessoas;
	private JComboBox<String> nomesComboBox;

	/**
	 * Create the dialog.
	 */
	public IgRelacionarTarefa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Relacionar Tarefa";
		
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
		labelImg.setIcon(new ImageIcon(IgRelacionarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/User-Profile-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Selecione o nome da pessoa que deseja relacionar:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JPanel dadosPanel = new JPanel();
		contentPanel.add(dadosPanel, BorderLayout.CENTER);

		dadosPanel.setLayout(new MigLayout("", "[116,grow][202px,grow]", "[28][][28px,grow]"));
		
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
				addLinhasTabela();
			}
		});
		dadosPanel.add(nomesComboBox, "cell 1 0,growx");
		
		JPanel divisorPanel = new JPanel();
		dadosPanel.add(divisorPanel, "cell 0 1 2 1,grow");
		divisorPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		divisorPanel.add(separator, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dadosPanel.add(scrollPane, "cell 0 2 2 1,grow");
		
		tabelaPessoas = new JTable();
		tabelaPessoas.setFont(new Font("SansSerif", Font.BOLD, 14));
		tabelaPessoas.setRowHeight(50);
		tabelaPessoas.setModel(new DefaultTableModel(linhasTabela, COLUNAS_TAREFA) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    });
		tabelaPessoas.getColumnModel().getColumn(1).setPreferredWidth(2);
		tabelaPessoas.getColumnModel().getColumn(2).setPreferredWidth(2);
		scrollPane.setViewportView(tabelaPessoas);
		
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
						IgRelacionarTarefa.this.dispose();
					}
				});
				fecharButton.setIcon(new ImageIcon(IgRelacionarTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				fecharButton.setMnemonic(KeyEvent.VK_F);
				fecharButton.setActionCommand("Cancel");
				buttonPane.add(fecharButton);
			}
		}
		
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor
	
	private void addLinhasTabela() {
		DefaultTableModel modelo = limpaTabela();
		
		if(ValidarDados.validarVazio(nomesComboBox.getSelectedItem().toString())) {
			int index = nomesComboBox.getSelectedIndex() - 1;
			
			Compromisso compromisso = new Compromisso();
			compromisso.setCodigoPessoa(arrayListPessoas.get(index).getCodigoPessoa());
			ArrayList<Tarefa> arrayListTarefas = new Tarefa().pesquisar();
			ArrayList<Compromisso> arrayListCompromissos = compromisso.pesquisarPessoa();
			
			Collections.sort(arrayListTarefas, new Tarefa());
			
			Object[] linha = new Object[NUMERO_COLUNAS_TABELA];

			for(int i = 0; i < arrayListCompromissos.size(); i++) {
				for(Tarefa t : arrayListTarefas) {
					if(arrayListCompromissos.get(i).getCodigoTarefa() == t.getCodigoTarefa()) {
						linha[0] = t.getDescricao();
						linha[1] = new SimpleDateFormat("dd/MM/yyyy").format(t.getDataHora());
						linha[2] = new SimpleDateFormat("HH:mm").format(t.getDataHora());
						modelo.addRow(linha);
						break;
					}
				}
			}
		}
	}
	
	public DefaultTableModel limpaTabela() {
		DefaultTableModel model = ((DefaultTableModel)(tabelaPessoas.getModel()));
		num_linhas = 0;
		model.setNumRows(num_linhas);
		
		return model;
	}
	
} // class IgRelacionarTarefa
