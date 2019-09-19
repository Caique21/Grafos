/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis.Arvore;


public class NArea
{

    private No raiz;

    public No getRaiz()
    {
        return raiz;
    }

    public void setRaiz(No raiz)
    {
        this.raiz = raiz;
    }

    public void inserir(int info, int N)
    {
        boolean flag = false;
        No aux, ant;
        int pos;
        if (raiz == null)
        {
            raiz = new No(info, N);
        } else
        {
            int i;
            aux = raiz;
            while(aux.getVLig(aux.getN() - 1) != null)
                aux = aux.getVLig(0);
            
            for ( i = 0; i < aux.getN() && aux.getVLig(i) != null; i++) 
            {}
            aux.setvLig(i, new No(info, N));
        }
    }
    
    public void verificaRaiz()
    {
        if(raiz.getN() > 1)
            raiz.setLigAlte("X");
    }
    
    public void in_ordem(No aux)
    {
        for (int i = 0; i < aux.getN(); i++) 
        {
            in_ordem(aux.getVLig(i));
        }
        
        if(aux.getN() == 0)
        {    
            aux.setMenorFilho(0);
            if(aux.getLigAlte() != null && aux.getLigAlte() != "X")
                aux.setMenor(aux.getPrenum() < Integer.parseInt(aux.getLigAlte()) ? aux.getPrenum() : Integer.parseInt(aux.getLigAlte()));
            else
                aux.setMenor(aux.getPrenum());
        }
        else
        {
            int menor =9999999;
            for (int i = 0; i < aux.getN(); i++) 
                if(aux.getVLig(i).getMenor() < menor && aux.getVLig(i).getMenor() != 0)
                    menor = aux.getVLig(i).getMenor();
            if(aux.getLigAlte() != null && aux.getLigAlte() != "X")
                aux.setMenor(aux.getPrenum() < Integer.parseInt(aux.getLigAlte()) ? aux.getPrenum() : Integer.parseInt(aux.getLigAlte()));
            else
                aux.setMenor(aux.getPrenum());
            
            if(aux.getMenor() > aux.getMenorFilho())
                aux.setMenor(aux.getMenorFilho());
        }
    }
}
