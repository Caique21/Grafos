/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis.Arvore;

import grafo.Uteis.PontoArticulacao;
import java.util.List;
import javafx.collections.ObservableList;


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
            aux.setMenorFilho(menor);
            if(aux.getLigAlte() != null && aux.getLigAlte() != "X")
                aux.setMenor(aux.getPrenum() < Integer.parseInt(aux.getLigAlte()) ? aux.getPrenum() : Integer.parseInt(aux.getLigAlte()));
            else
                aux.setMenor(aux.getPrenum());
            
            if(aux.getMenor() > aux.getMenorFilho())
                aux.setMenor(aux.getMenorFilho());
        }
    }

    public void verificaLigAlt(No raiz, ObservableList<PontoArticulacao> pa) 
    {
        if(raiz != null)
        {
            if(raiz.getN() > 1)
                pa.get(raiz.getVinfo()).setLigAlte(String.valueOf(raiz.getVLig(1).getVinfo()));
            else
                pa.get(raiz.getVinfo()).setLigAlte("X");
            for (int i = 0; i < raiz.getN(); i++) 
                verificaLigAlt(raiz.getVLig(i), pa);
        }
    }

    public No find(No r, int parseInt) 
    {
        if(r != null)
        {
            if(r.getVinfo() == parseInt)
                return r;
            else
            {
                for (int i = 0; i < r.getN(); i++) 
                    if(find(r.getVLig(i), parseInt) != null)
                        return r.getVLig(i);
            }
        }
        return null;
    }

    public void inserir(No ret, int info, int N) 
    {
        boolean flag = false;
        No aux, ant;
        int pos;
        if (ret == null)
        {
            ret = new No(info, N);
        } else
        {
            int i;
            aux = ret;
            while(aux.getVLig(aux.getN() - 1) != null)
                aux = aux.getVLig(0);
            
            for ( i = 0; i < aux.getN() && aux.getVLig(i) != null; i++) 
            {}
            aux.setvLig(i, new No(info, N));
        }
    }

    public void refresh(No r, ObservableList<PontoArticulacao> pa) 
    {
        if(pa.get(r.getVinfo()).getMenor() == 0)
            pa.get(r.getVinfo()).setMenor(r.getMenor());
        pa.get(r.getVinfo()).setMenorFilho(r.getMenorFilho());
        
        for (int i = 0; i < r.getN(); i++) 
            refresh(r.getVLig(i), pa);
    }

    public void insereLista(No r, List<No> nos) 
    {
        if(r != null)
        {
            if(r == raiz && r.getN() > 1)
                nos.add(r);
            else
            {
                if(r.getMenorFilho() != 0)
                    nos.add(r);
            }
            for (int i = 0; i < r.getN(); i++) 
                    insereLista(r.getVLig(i), nos);
        }
        
    }
}
