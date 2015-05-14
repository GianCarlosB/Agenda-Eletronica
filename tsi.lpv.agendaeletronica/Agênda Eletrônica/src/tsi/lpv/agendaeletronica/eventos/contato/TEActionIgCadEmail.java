package tsi.lpv.agendaeletronica.eventos.contato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.contato.Email;
import tsi.lpv.agendaeletronica.gui.contato.IgCadastrarEmail;

public class TEActionIgCadEmail implements ActionListener {
	
	private final String MSG_ERRO_EMAIL = " O campo e-mail não foi preenchido corretamente!";
	
	private IgCadastrarEmail ig;

	public TEActionIgCadEmail(IgCadastrarEmail igCadastrarEmail) {
		super();
		this.ig = igCadastrarEmail;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getBtnInserir()) {
			if(ValidarDados.verificarCadeiaDeCaracteres(ig.getEmailTextField().getText(), ValidarDados.EX_REG_EMAIL)) {
				String email = ig.getEmailTextField().getText();
				int index = ig.getNomesComboBox().getSelectedIndex() - 1;
				
				ig.esconderMsgCamposErrados();
				
				// Verifica se foi possível inserir o e-mail no banco de dados.
				if(new Email(email, ig.getArrayListPessoas().get(index).getCodigoPessoa()).inserir())
					JOptionPane.showMessageDialog(ig, "Inserção do e-mail: " + email + " , realizada com sucesso!",
							ig.NOME_MODULO, 1);
				else
					JOptionPane.showMessageDialog(ig, "Falha na inserção do e-mail: " + email + " !",
							ig.NOME_MODULO, 0);
				
				// Libera a janela.
				ig.dispose();
			}
			else ig.exibirMsgCamposErrados(MSG_ERRO_EMAIL, ig.getLblEmail());
		}
	}

} // class TEActionIgCadEmail
