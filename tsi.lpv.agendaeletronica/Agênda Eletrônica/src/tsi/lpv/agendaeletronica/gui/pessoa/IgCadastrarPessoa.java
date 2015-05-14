package tsi.lpv.agendaeletronica.gui.pessoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tsi.lpv.agendaeletronica.eventos.pessoa.TEActionIgCadPessoa;
import tsi.lpv.agendaeletronica.gui.menuprincipal.IgMenuPrincipal;

public class IgCadastrarPessoa extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 5010114376299507306L;
	
	public final String NOME_MODULO;
	
	private Color corPadraoLblNome;
	private Color corPadraoLblDataDeNascimento;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField nomeTextField;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JButton cadastrarButton;
	private JLabel lblCampoErrado;
	private JLabel lblNome;
	private JLabel lblDataDeNascimento;

	/**
	 * Create the dialog.
	 */
	public IgCadastrarPessoa(IgMenuPrincipal igMenuPrincipal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igMenuPrincipal.NOME_PROGRAMA + " - Cadastrar Pessoa";
		
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
		labelImg.setIcon(new ImageIcon(IgCadastrarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/User-Profile-32.png")));
		tituloPanel.add(labelImg, BorderLayout.EAST);
		
		JLabel lblTitulo = new JLabel("  Insira os dados da pessoa:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JPanel dadosPanel = new JPanel();
		contentPanel.add(dadosPanel, BorderLayout.CENTER);
		
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		
		nomeTextField = new JTextField();
		nomeTextField.setColumns(10);
		datePicker = new JDatePickerImpl(datePanel);
		dadosPanel.setLayout(new MigLayout("", "[116px][202px,grow]", "[28px][28px]"));
		
		lblNome = new JLabel("Nome:");
		lblNome.setLabelFor(nomeTextField);
		lblNome.setDisplayedMnemonic(KeyEvent.VK_N);
		corPadraoLblNome = lblNome.getForeground();
		dadosPanel.add(lblNome, "cell 0 0");
		dadosPanel.add(nomeTextField, "cell 1 0,growx,aligny top");
		
		lblDataDeNascimento = new JLabel("Data De Nascimento:");
		lblDataDeNascimento.setLabelFor(datePicker.getJFormattedTextField());
		lblDataDeNascimento.setDisplayedMnemonic(KeyEvent.VK_D);
		corPadraoLblDataDeNascimento = lblDataDeNascimento.getForeground();
		dadosPanel.add(lblDataDeNascimento, "cell 0 1");
		dadosPanel.add(datePicker, "cell 1 1,growx,aligny top");
		
		JPanel msgErroPanel = new JPanel();
		contentPanel.add(msgErroPanel, BorderLayout.SOUTH);
		msgErroPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorBtn = new JSeparator();
		msgErroPanel.add(separatorBtn, BorderLayout.SOUTH);
		
		lblCampoErrado = new JLabel("");
		lblCampoErrado.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblCampoErrado.setForeground(Color.RED);
		lblCampoErrado.setVisible(false);
		lblCampoErrado.setIcon(new ImageIcon(IgCadastrarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Warning-Message-24.png")));
		msgErroPanel.add(lblCampoErrado, BorderLayout.NORTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cadastrarButton = new JButton("Cadastrar");
				cadastrarButton.addActionListener(new TEActionIgCadPessoa(this));
				cadastrarButton.setIcon(new ImageIcon(IgCadastrarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Add-New-16.png")));
				cadastrarButton.setMnemonic(KeyEvent.VK_C);
				cadastrarButton.setActionCommand("OK");
				buttonPane.add(cadastrarButton);
				getRootPane().setDefaultButton(cadastrarButton);
			}
			{
				JButton cancelarButton = new JButton("Cancelar");
				cancelarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IgCadastrarPessoa.this.dispose();
					}
				});
				cancelarButton.setIcon(new ImageIcon(IgCadastrarPessoa.class.getResource("/tsi/lpv/agendaeletronica/recursos/imagens/Cancel-16.png")));
				cancelarButton.setMnemonic(KeyEvent.VK_A);
				cancelarButton.setActionCommand("Cancel");
				buttonPane.add(cancelarButton);
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
		if(label.equals(lblNome)) {
			nomeTextField.requestFocus();
			lblDataDeNascimento.setForeground(corPadraoLblDataDeNascimento);
		}
		else if(label.equals(lblDataDeNascimento)) {
			datePicker.requestFocus();
			lblNome.setForeground(corPadraoLblNome);
		}
	}
	
	public void esconderMsgCamposErrados() {
		lblNome.setForeground(corPadraoLblNome);
		lblDataDeNascimento.setForeground(corPadraoLblDataDeNascimento);
		lblCampoErrado.setVisible(false);
	}

	public JTextField getNomeTextField() {
		return nomeTextField;
	}

	public Date getValorDatePicker() {
		return ((Date) datePicker.getModel().getValue());
	}

	public JLabel getLblNome() {
		return lblNome;
	}

	public JLabel getLblDataDeNascimento() {
		return lblDataDeNascimento;
	}

	public JButton getCadastrarButton() {
		return cadastrarButton;
	}
	
} // class IgCadastrarPessoa
