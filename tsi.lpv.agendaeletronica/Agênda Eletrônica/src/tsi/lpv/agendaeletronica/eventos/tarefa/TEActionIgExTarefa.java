package tsi.lpv.agendaeletronica.eventos.tarefa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.gui.tarefa.IgExcluirTarefa;

public class TEActionIgExTarefa implements ActionListener {

	private IgExcluirTarefa ig;

	public TEActionIgExTarefa(IgExcluirTarefa igExcluirPessoa) {
		super();
		this.ig = igExcluirPessoa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getExcluirButton()) {
			int itensSelecionados[] = ig.getTarefasList().getSelectedIndices();
			String tarefasExcluidas = "";
			
			for(int i = 0; i < itensSelecionados.length; i++) {
				if(ig.getArrayListTarefas().get(itensSelecionados[i]).excluir())
					tarefasExcluidas += "\n---- " + new SimpleDateFormat("dd/MM/yyyy").format(ig.getArrayListTarefas().get(itensSelecionados[i])
							.getDataHora()) + " - " + ig.getArrayListTarefas().get(itensSelecionados[i]).getDescricao();
				else
					JOptionPane.showMessageDialog(ig, "Falha na exclusão da tarefa de data: " + new SimpleDateFormat("dd/MM/yyyy")
							.format(ig.getArrayListTarefas().get(itensSelecionados[i]).getDataHora()) + " !", ig.NOME_MODULO, 0);
			}
			
			if(!tarefasExcluidas.equals(""))
				JOptionPane.showMessageDialog(ig, "Exclusão das tarefas realizada com sucesso!"
						+ "\nTarefas excluídas:" + tarefasExcluidas, ig.NOME_MODULO, 1);
			else
				JOptionPane.showMessageDialog(ig, "Nenhuma tarefa foi selecionada!\nExclusão não realizada!", ig.NOME_MODULO, 2);
			
			ig.inserirTarefasLista();
		}
	}

} // class IgExcluirTarefa
