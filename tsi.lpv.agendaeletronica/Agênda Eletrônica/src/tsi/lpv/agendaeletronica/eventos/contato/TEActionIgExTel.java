package tsi.lpv.agendaeletronica.eventos.contato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.gui.contato.IgExcluirTel;

public class TEActionIgExTel implements ActionListener {

	private IgExcluirTel ig;

	public TEActionIgExTel(IgExcluirTel igExcluirTel) {
		super();
		this.ig = igExcluirTel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getExcluirButton()) {
			int itensSelecionados[] = ig.getTelefonesList().getSelectedIndices();
			String telefonesExcluidas = "";
			
			for(int i = 0; i < itensSelecionados.length; i++) {
				if(ig.getArrayListTelefones().get(itensSelecionados[i]).excluir())
					telefonesExcluidas += "\n---- " + ig.getArrayListTelefones().get(itensSelecionados[i]).getNumero();
				else
					JOptionPane.showMessageDialog(ig, "Falha na exclus�o do n�mero de telefone: " + ig.getArrayListTelefones()
							.get(itensSelecionados[i]).getNumero() + " !", ig.NOME_MODULO, 0);
			}
			
			if(!telefonesExcluidas.equals(""))
				JOptionPane.showMessageDialog(ig, "Exclus�o dos n�meros de telefone realizada com sucesso!"
						+ "\nN�meros de telefone exclu�dos:" + telefonesExcluidas, ig.NOME_MODULO, 1);
			else
				JOptionPane.showMessageDialog(ig, "Nenhum n�mero de telefone foi selecionado!\nExclus�o n�o realizada!", ig.NOME_MODULO, 2);
			
			ig.inserirTelefonesLista();
		}
	}

} // class TEActionIgExTel
