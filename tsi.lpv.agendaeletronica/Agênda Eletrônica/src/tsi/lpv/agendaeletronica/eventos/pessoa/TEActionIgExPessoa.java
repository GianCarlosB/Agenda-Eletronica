package tsi.lpv.agendaeletronica.eventos.pessoa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.gui.pessoa.IgExcluirPessoa;

public class TEActionIgExPessoa implements ActionListener {

	private IgExcluirPessoa ig;

	public TEActionIgExPessoa(IgExcluirPessoa igExcluirPessoa) {
		super();
		this.ig = igExcluirPessoa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getExcluirButton()) {
			int itensSelecionados[] = ig.getPessoasList().getSelectedIndices();
			String pessoasExcluidas = "";
			
			for(int i = 0; i < itensSelecionados.length; i++) {
				if(ig.getArrayListPessoas().get(itensSelecionados[i]).excluir())
					pessoasExcluidas += "\n---- " + ig.getArrayListPessoas().get(itensSelecionados[i]).getNome();
				else
					JOptionPane.showMessageDialog(ig, "Falha na exclusão da pessoa de nome '" + ig.getArrayListPessoas()
							.get(itensSelecionados[i]).getNome() + "' !", ig.NOME_MODULO, 0);
			}
			
			if(!pessoasExcluidas.equals(""))
				JOptionPane.showMessageDialog(ig, "Exclusão das pessoas realizada com sucesso!"
						+ "\nPessoas excluídas:" + pessoasExcluidas, ig.NOME_MODULO, 1);
			else
				JOptionPane.showMessageDialog(ig, "Nenhuma pessoa foi selecionada!\nExclusão não realizada!", ig.NOME_MODULO, 2);
			
			ig.inserirPessoasLista();
		}
	}

} // class TEActionIgExPessoa
