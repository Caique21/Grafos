/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis.Buscas;

/**
 *
 * @author Aluno
 */
public class No 
{
    private int indice;
    private No prox;

    public No(int indice) 
    {
        this.indice = indice;
        prox = null;
    }

    public No(int indice, No prox) 
    {
        this.indice = indice;
        this.prox = prox;
    }

    public int getIndice() 
    {
        return indice;
    }

    public void setIndice(int indice) 
    {
        this.indice = indice;
    }

    public No getProx() 
    {
        return prox;
    }

    public void setProx(No prox) 
    {
        this.prox = prox;
    }
    
    public int size()
    {
        No aux = this;
        int i = 0;
        while(aux != null)
        {
            i++;
            aux = aux.getProx();
        }
        return i;
    }
}
