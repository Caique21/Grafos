/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis.Buscas;

import grafo.Uteis.Arvore.NArea;
import grafo.Uteis.Arvore.No;
import grafo.Uteis.Incidencia;
import java.util.LinkedList;
import java.util.Queue;
import javafx.collections.ObservableList;

/**
 *
 * @author carlo
 */
public class Largura 
{
    private int[] vetorVisitado;
    private int indice;
    private Queue fila;

    public Largura(int qtd) 
    {
        vetorVisitado = new int[qtd];
        for (int i = 0; i < vetorVisitado.length; i++) 
            vetorVisitado[i] = 0;
        indice = 1;
        fila = new LinkedList();
    }
    
    public void busca(int pos, No no, NArea tree)
    {
        no.setCor("1", pos);
        fila.add(no);
        
        
        /*while(!fila.isEmpty())
        {
            no = (No)fila.remove();
            
            No pai = tree.findPai(tree.getRaiz(),no);
            if(vetorVisitado[pai.getinfo()] == 0)
                fila.add(pai);
            for (int i = 0; i < no.getN(); i++) 
            {
                if(vetorVisitado[no.getVLig(i).getinfo()] == 0)
                    fila.add(no.getVLig(i));
            }
            
        }*/
    }
}
