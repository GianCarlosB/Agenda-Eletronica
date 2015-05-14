package tsi.lpv.agendaeletronica.eventos.contato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.gui.contato.IgExcluirEmail;

public class TEActionIgExEmail implements ActionListener {

	private IgExcluirEmail ig;

	public TEActionIgExEmail(IgExcluirEmail igExcluirEmail) {
		super();
		this.ig = igExcluirEmail;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getExcluirButton()) {
			int itensSelecionados[] = ig.getEmailsList().getSelectedIndices();
			String emailsExcluidas = "";
			
			for(int i = 0; i < itensSelecionados.length; i++) {
				if(ig.getArrayListEmails().get(itensSelecionados[i]).excluir())
					emailsExcluidas += "\n---- " + ig.getArrayListEmails().get(itensSelecionados[i]).getEmail();
				else
					JOptionPane.showMessageDialog(ig, "Falha na exclusão do e-mail: " + ig.getArrayListEmails()
							.get(itensSelecionados[i]).getEmail() + " !", ig.NOME_MODULO, 0);
			}
			
			if(!emailsExcluidas.equals(""))
				JOptionPane.showMessageDialog(ig, "Exclusão dos e-mails realizada com sucesso!"
						+ "\nE-Mails excluídos:" + emailsExcluidas, ig.NOME_MODULO, 1);
			else
				JOptionPane.showMessageDialog(ig, "Nenhum e-mail foi selecionado!\nExclusão não realizada!", ig.NOME_MODULO, 2);
			
			ig.inserirEmailsLista();
		}
	}

} // class TEActionIgExEmail
