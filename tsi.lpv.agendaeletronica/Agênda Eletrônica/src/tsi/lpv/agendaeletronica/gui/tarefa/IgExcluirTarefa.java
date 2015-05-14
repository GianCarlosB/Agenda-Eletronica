package tsi.lpv.agendaeletronica.gui.tarefa;

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
import tsi.lpv.agendaeletronica.entidades.tarefa.Tarefa;
import tsi.lpv.agendaeletronica.eventos.tarefa.TEActionIgExTarefa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgExcluirTarefa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 5010114376299507306L;
	
	public final String NOME_MODULO;
	
	private ArrayList<Tarefa> arrayListTarefas;
	
	private final JPanel contentPanel = new JPanel();
	private JButton excluirButton;
	private JList<String> tarefasList;

	/**
	 * Create the dialog.
	 */
	public IgExcluirTarefa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Excluir Tarefa";
		
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
		labelImg.setIcon(new ImageIcon(IgExcluirTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Task-01-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Selecione as tarefas que deseja exclu\u00EDr:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JPanel dadosPanel = new JPanel();
		contentPanel.add(dadosPanel, BorderLayout.CENTER);
		
		dadosPanel.setLayout(new MigLayout("", "[116px][202px,grow]", "[28px,grow]"));
		
		JLabel lblTarefas = new JLabel("Tarefas:");
		lblTarefas.setDisplayedMnemonic(KeyEvent.VK_T);
		dadosPanel.add(lblTarefas, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		dadosPanel.add(scrollPane, "cell 1 0,grow");
		
		tarefasList = new JList<String>();
		lblTarefas.setLabelFor(tarefasList);
		scrollPane.setViewportView(tarefasList);
		
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
				excluirButton.addActionListener(new TEActionIgExTarefa(this));
				excluirButton.setIcon(new ImageIcon(IgExcluirTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Report-Delete-16.png")));
				excluirButton.setMnemonic(KeyEvent.VK_E);
				excluirButton.setActionCommand("OK");
				buttonPane.add(excluirButton);
				getRootPane().setDefaultButton(excluirButton);
			}
			{
				JButton fecharButton = new JButton("Fechar");
				fecharButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgExcluirTarefa.this.dispose();
					}
				});
				fecharButton.setIcon(new ImageIcon(IgExcluirTarefa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				fecharButton.setMnemonic(KeyEvent.VK_F);
				fecharButton.setActionCommand("Cancel");
				buttonPane.add(fecharButton);
			}
		}
		
		inserirTarefasLista();
		setLocationRelativeTo(igMenuPrincipal);
		setVisible(true);
	} // construtor
	
	public void inserirTarefasLista() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		
		arrayListTarefas = new Tarefa().pesquisar();
		
		Collections.sort(arrayListTarefas, new Tarefa());
		
		// Preenche a JList com os dados das tarefas.
		for(Tarefa t : arrayListTarefas) modelo.addElement(new SimpleDateFormat("dd/MM/yyyy").format(t.getDataHora()) + " - " + t.getDescricao());
		
		tarefasList.setModel(modelo);
	}

	public ArrayList<Tarefa> getArrayListTarefas() {
		return arrayListTarefas;
	}

	public JButton getExcluirButton() {
		return excluirButton;
	}

	public JList<String> getTarefasList() {
		return tarefasList;
	}

} // class IgExcluirTarefa
