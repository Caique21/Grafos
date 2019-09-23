/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis.Arvore;

import grafo.Uteis.Incidencia;
import grafo.Uteis.PontoArticulacao;
import java.util.LinkedList;
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

    public void inserir(int info, int N,PontoArticulacao p)
    {
        boolean flag = false;
        No aux, ant;
        int pos;
        if (raiz == null)
        {
            raiz = new No(info, N,p);
        } else
        {
            int i;
            aux = raiz;
            while(aux.getVLig(aux.getN() - 1) != null)
                aux = aux.getVLig(0);
            
            for ( i = 0; i < aux.getN() && aux.getVLig(i) != null; i++) 
            {}
            aux.setvLig(i, new No(info, N,p));
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
                pa.get(raiz.getinfo()).setLigAlte(String.valueOf(raiz.getVLig(1).getinfo()));
            else
                pa.get(raiz.getinfo()).setLigAlte("X");
            for (int i = 0; i < raiz.getN(); i++) 
                verificaLigAlt(raiz.getVLig(i), pa);
        }
    }

    public No find(No r, int parseInt) 
    {
        if(r != null)
        {
            if(r.getinfo() == parseInt)
                return r;
            else
            {
                for (int i = 0; i < r.getN(); i++) 
                    if(find(r.getVLig(i), parseInt) != null)
                        return find(r.getVLig(i), parseInt);
            }
        }
        return null;
    }

    public void inserir(No ret, int info, int N,PontoArticulacao p) 
    {
        boolean flag = false;
        No aux, ant;
        int pos;
        if (ret == null)
        {
            ret = new No(info, N,p);
        } 
        else
        {
            int i;
            aux = ret;
            while(aux.getVLig(aux.getN() - 1) != null)
                aux = aux.getVLig(0);
            
            for ( i = 0; i < aux.getN() && aux.getVLig(i) != null; i++) 
            {}
            aux.setvLig(i, new No(info, N,p));
        }
    }
    
    public void inserir(No ret, int info, int N,PontoArticulacao p,No filho) 
    {
        boolean flag = false;
        No aux, ant;
        int pos;
        if (ret == null)
        {
            ret = new No(info, N,p);
        } 
        else
        {
            int i;
            aux = ret;
            while(aux.getVLig(aux.getN() - 1) != null)
                aux = aux.getVLig(0);
            
            for ( i = 0; i < aux.getN() && aux.getVLig(i) != null; i++) 
            {}
            aux.setvLig(i, filho);
        }
    }

    public void refresh(No r, ObservableList<PontoArticulacao> pa) 
    {
        if(pa.get(r.getinfo()).getMenor() == 0)
            pa.get(r.getinfo()).setMenor(r.getMenor());
        pa.get(r.getinfo()).setMenorFilho(r.getMenorFilho());
        
        for (int i = 0; i < r.getN(); i++) 
            refresh(r.getVLig(i), pa);
    }

    public void insereLista(No r, List<No> nos) 
    {
        if(r != null)
        {
            if(r == raiz && r.getN() > 1)
                nos.add(r);
            else if(r != raiz)
            {
                if(r.getMenorFilho() != 0)
                    nos.add(r);
            }
            for (int i = 0; i < r.getN(); i++) 
                    insereLista(r.getVLig(i), nos);
        }
        
    }

    public boolean verificaLigacao(No r,String pai, String filho) 
    {
        if(r != null)
        {
            if(r.getinfo() == Integer.parseInt(pai))
                for (int i = 0; i < r.getN(); i++) 
                    if(r.getVLig(i).getinfo() == Integer.parseInt(filho))
                        return true;
                    else
                        return false;
            else if(r.getinfo() == Integer.parseInt(filho))
            {
                for (int i = 0; i < r.getN(); i++) 
                    if(r.getVLig(i).getinfo() == Integer.parseInt(pai))
                        return true;
                    return false;
            }
            else
            {
                for (int i = 0; i < r.getN(); i++) 
                    if(verificaLigacao(r.getVLig(i), pai, filho))
                        return verificaLigacao(r.getVLig(i), pai, filho);
            }   
        }
        return false;
    }

    public void inicializaCores(No r,ObservableList<Incidencia>cores) 
    {
        if(r != null)
        {
            if(r.getCores() == null)
                r.setCores(cores.get(r.getinfo()));
            
            for (int i = 0; i < r.getN(); i++) 
                inicializaCores(r.getVLig(i), cores);
        }
    }

    public void colorir(No ret, int[]vetorVisitado) 
    {
        int posCor = 0,cor = 1;
        
        List<No>ligacoes = new LinkedList<>();
        ligacoes.add(ret);
        do
        {
            ret = ligacoes.remove(0);
            ret.setCor();
            
            //ret.setCor(String.valueOf(cor), 0);
            vetorVisitado[ret.getinfo()] = 1;
        
            No aux;

            if(this.findPai(ret,this.raiz) != null)
            {
                aux = this.findPai(ret,this.raiz);
                aux.setCor("-", ret.getPosCor() - 1);
                if(vetorVisitado[this.findPai(ret,this.raiz).getinfo()] == 0 && !ligacoes.contains(aux))
                    ligacoes.add(aux);
            }
            for (int i = 0; i < ret.getN(); i++) 
            {
                aux = ret.getVLig(i);
                aux.setCor("-", ret.getPosCor() - 1);
                if(vetorVisitado[ret.getVLig(i).getinfo()] == 0 && !ligacoes.contains(aux))
                    ligacoes.add(ret.getVLig(i));
            }
            
            posCor++;
            cor++;
        }while(!ligacoes.isEmpty());
    }
    
    public No findPai(No busca,No r)
    {
        if(r == null || busca == r)
            return null;
        else
        {
            for (int i = 0; i < r.getN(); i++) 
                   if(r.getVLig(i).getinfo() == busca.getinfo())
                       return r;
            for (int i = 0; i < r.getN(); i++) 
                if(findPai(busca,r.getVLig(i)) != null)
                    return findPai(busca,r.getVLig(i));
        }
        return null;
    }

    public void limpaZeros(No r, int n)
    {
        if(raiz != null)
        {
            if(n <= 1)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
            if(n <= 2)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
                if(r.getCores().getParametro2() == "0")
                    r.setCor("-", 1);
            if(n <= 3)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
                if(r.getCores().getParametro2() == "0")
                    r.setCor("-", 1);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 2);
            if(n <= 4)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
                if(r.getCores().getParametro2() == "0")
                    r.setCor("-", 1);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 2);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 3);
            if(n <= 5)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
                if(r.getCores().getParametro2() == "0")
                    r.setCor("-", 1);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 2);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 3);
                if(r.getCores().getParametro4() == "0")
                    r.setCor("-", 4);
                if(n <= 1)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
            if(n <= 6)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
                if(r.getCores().getParametro2() == "0")
                    r.setCor("-", 1);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 2);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 3);
                if(r.getCores().getParametro4() == "0")
                    r.setCor("-", 4);
                if(r.getCores().getParametro5() == "0")
                    r.setCor("-", 5);
            if(n <= 7)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
                if(r.getCores().getParametro2() == "0")
                    r.setCor("-", 1);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 2);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 3);
                if(r.getCores().getParametro4() == "0")
                    r.setCor("-", 4);
                if(r.getCores().getParametro5() == "0")
                    r.setCor("-", 5);
                if(r.getCores().getParametro6() == "0")
                    r.setCor("-", 6);
            if(n <= 8)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
                if(r.getCores().getParametro2() == "0")
                    r.setCor("-", 1);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 2);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 3);
                if(r.getCores().getParametro4() == "0")
                    r.setCor("-", 4);
                if(r.getCores().getParametro5() == "0")
                    r.setCor("-", 5);
                if(r.getCores().getParametro6() == "0")
                    r.setCor("-", 6);
                if(r.getCores().getParametro7() == "0")
                    r.setCor("-", 7);
            if(n <= 9)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
                if(r.getCores().getParametro2() == "0")
                    r.setCor("-", 1);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 2);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 3);
                if(r.getCores().getParametro4() == "0")
                    r.setCor("-", 4);
                if(r.getCores().getParametro5() == "0")
                    r.setCor("-", 5);
                if(r.getCores().getParametro6() == "0")
                    r.setCor("-", 6);
                if(r.getCores().getParametro7() == "0")
                    r.setCor("-", 7);
                if(r.getCores().getParametro8() == "0")
                    r.setCor("-", 8);
            if(n <= 10)
                if(r.getCores().getParametro1() == "0")
                    r.setCor("-", 0);
                if(r.getCores().getParametro2() == "0")
                    r.setCor("-", 1);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 2);
                if(r.getCores().getParametro3() == "0")
                    r.setCor("-", 3);
                if(r.getCores().getParametro4() == "0")
                    r.setCor("-", 4);
                if(r.getCores().getParametro5() == "0")
                    r.setCor("-", 5);
                if(r.getCores().getParametro6() == "0")
                    r.setCor("-", 6);
                if(r.getCores().getParametro7() == "0")
                    r.setCor("-", 7);
                if(r.getCores().getParametro8() == "0")
                    r.setCor("-", 8);
                if(r.getCores().getParametro9() == "0")
                    r.setCor("-", 9);
             
            for (int i = 0; i < r.getN(); i++) 
                limpaZeros(r.getVLig(i), n);
        }
    }
}
