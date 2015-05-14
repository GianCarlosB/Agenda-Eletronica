package tsi.lpv.agendaeletronica.gui.pessoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgRelacionarPessoa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -7228357994503672768L;

	public final String NOME_MODULO;
	
	private final int NUMERO_COLUNAS_TABELA = 2; // Número de colunas da tabela.
	
	private final String COLUNA_NOME = "Nome";
	private final String COLUNA_DATA_DE_ANIVERSARIO = "Data de Aniversário";
	private final String[] COLUNAS_PESSOA = {COLUNA_NOME, COLUNA_DATA_DE_ANIVERSARIO};
	private String[][] linhasTabela = new String[0][NUMERO_COLUNAS_TABELA];
	
	private final JPanel contentPanel = new JPanel();
	private JTable tabelaPessoas;

	/**
	 * Create the dialog.
	 */
	public IgRelacionarPessoa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Relacionar Pessoa";
		
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
		labelImg.setIcon(new ImageIcon(IgRelacionarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/User-Profile-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Relat\u00F3rio de pessoas exibido em ordem alfab\u00E9tica:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JPanel dadosPanel = new JPanel();
		contentPanel.add(dadosPanel, BorderLayout.CENTER);

		dadosPanel.setLayout(new MigLayout("", "[202px,grow]", "[28px,grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dadosPanel.add(scrollPane, "cell 0 0,grow");
		
		tabelaPessoas = new JTable();
		tabelaPessoas.setFont(new Font("SansSerif", Font.BOLD, 16));
		tabelaPessoas.setRowHeight(50);
		tabelaPessoas.setModel(new DefaultTableModel(linhasTabela, COLUNAS_PESSOA) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    });
		tabelaPessoas.getColumnModel().getColumn(1).setPreferredWidth(2);
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
						IgRelacionarPessoa.this.dispose();
					}
				});
				fecharButton.setIcon(new ImageIcon(IgCadastrarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				fecharButton.setMnemonic(KeyEvent.VK_F);
				fecharButton.setActionCommand("Cancel");
				buttonPane.add(fecharButton);
			}
		}
		
		addLinhasTabela();
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor
	
	private void addLinhasTabela() {
		DefaultTableModel modelo = ((DefaultTableModel)(tabelaPessoas.getModel()));
		
		ArrayList<Pessoa> arrayListPessoas = new Pessoa().pesquisar();
		
		Collections.sort(arrayListPessoas, new Pessoa());
		
		Object[] linha = new Object[NUMERO_COLUNAS_TABELA];
			
		for(int i = 0; i < arrayListPessoas.size(); i++) {
			linha[0] = arrayListPessoas.get(i).getNome();
			linha[1] =  new SimpleDateFormat("dd/MM").format(arrayListPessoas.get(i).getDataDeAniversario());
			modelo.addRow(linha);
		}
	}
	
} // class IgRelacionarPessoa
