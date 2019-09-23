/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis.Buscas;

import grafo.Uteis.PontoArticulacao;
import grafo.Uteis.Arvore.No;
import java.util.Stack;
import javafx.collections.ObservableList;

/**
 *
 * @author Aluno
 */
public class Profundidade 
{
    private int[] vetorVisitado;
    private int indice;
    private Stack pilha;

    public Profundidade(int size) 
    {
        vetorVisitado = new int[size];
        for (int i = 0; i < vetorVisitado.length; i++) 
            vetorVisitado[i] = 0;
        indice = 1;
        pilha = new Stack();
    }
    
    public void busca(ObservableList<PontoArticulacao>p, No tree, int pos)
    {
        pilha.push(tree);
        vetorVisitado[pos] = 1;
        p.get(pos).setPrenum(indice);
        tree.setPrenum(indice);

        for (int i = 0; i < tree.getN(); i++) 
        {
            if(vetorVisitado[tree.getVLig(i).getinfo()] == 0)
            {
                indice++;
                busca(p, tree.getVLig(i),tree.getVLig(i).getinfo());            
            }
        }
    }
}
