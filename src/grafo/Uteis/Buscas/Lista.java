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
public class Lista 
{
    private No cabeca;

    public Lista(No cabeca) 
    {
        this.cabeca = cabeca;
    }
    
    public Lista() 
    {
        this.cabeca = null;
    }

    public No getCabeca() 
    {
        return cabeca;
    }

    public void setCabeca(No cabeca) 
    {
        this.cabeca = cabeca;
    }
    
    public void insere(int indice)
    {
        if(cabeca == null)
            cabeca = new No(indice);
        else
        {
            No aux = new No(indice);
            if(indice < cabeca.getIndice())
            {
                aux.setProx(cabeca);
                cabeca = aux;
            }
            else
            {
                No novo = aux;
                aux = cabeca;
                while(aux.getProx() != null && indice > aux.getIndice())
                    aux = aux.getProx();
                
                if(aux.getProx() == null || indice != aux.getIndice())
                    aux.setProx(new No(indice));
            }
        }
    }
    
    public No get(int indice)
    {
        No aux = cabeca;
        while(indice > 0)
        {
            aux = aux.getProx();
            indice--;
        }
        return aux;
    }
    
    public int size()
    {
        No aux = cabeca;
        int i = 0;
        while(aux != null)
        {
            i++;
            aux = aux.getProx();
        }
        return i;
    }
}
