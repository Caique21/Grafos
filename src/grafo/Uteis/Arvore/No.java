/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis.Arvore;

import grafo.Uteis.Incidencia;
import grafo.Uteis.PontoArticulacao;

public class No
{

    private int info;
    private No[] vLig;
    private int N;
    private PontoArticulacao p;
    private Incidencia cores;

    public No(int info, int N, PontoArticulacao p)
    {
        this.info = info;
        this.vLig = new No[N];
        this.N = N;
        this.p = p;
        this.cores = null;
    }

    public int getinfo()
    {
        return info;
    }

    public void setInfo(int info)
    {
        this.info = info;
    }

    public int getN() 
    {
        return N;
    }
    
    public No getVLig(int pos)
    {
        return vLig[pos];
    }

    public void setvLig(int pos, No Lig)
    {
        this.vLig[pos] = Lig;
    }

    public int getPrenum() 
    {
        return p.getPrenum();
    }

    public void setPrenum(int prenum) 
    {
        this.p.setPrenum(prenum);
    }

    public String getLigAlte() 
    {
        return p.getLigAlte();
    }

    public void setLigAlte(String ligAlte) 
    {
        this.p.setLigAlte(ligAlte);
    }

    public int getMenorFilho() 
    {
        return p.getMenorFilho();
    }

    public void setMenorFilho(int menorFilho) 
    {
        this.p.setMenorFilho(menorFilho);
    }

    public int getMenor() 
    {
        return p.getMenor();
    }

    public void setMenor(int menor) 
    {
        this.p.setMenor(menor);
    }

    public PontoArticulacao getP() 
    {
        return p;
    }

    public void setN(int N) 
    {
        this.N = N;
        
        No[] auxLig = vLig;
        vLig = new No[N];
        for (int i = 0; i < this.N; i++) 
            this.setvLig(i, null);
        
        for (int i = 0; i < auxLig.length; i++) 
            this.setvLig(i, new No(auxLig[i].getinfo(), 0,null));
    }

    public Incidencia getCores() 
    {
        return cores;
    }

    public void setCores(Incidencia cores) 
    {
        this.cores = cores;
    }

    public void setCor(String cor, int pos)
    {
        switch(pos)
        {
            case 0:
                this.cores.setParametro1(cor);
                break;
            case 1:
                this.cores.setParametro2(cor);
                break;
            case 2:
                this.cores.setParametro3(cor);
                break;
            case 3:
                this.cores.setParametro4(cor);
                break;
            case 4:
                this.cores.setParametro5(cor);
                break;
            case 5:
                this.cores.setParametro6(cor);
                break;
            case 6:
                this.cores.setParametro7(cor);
                break;
            case 7:
                this.cores.setParametro8(cor);
                break;
            case 8:
                this.cores.setParametro9(cor);
                break;
            case 9:
                this.cores.setParametro10(cor);
                break;
        }
    }

    void setCor() 
    {
        if(this.cores.getParametro1() == "0")
            this.cores.setParametro1("1");
        else if(this.cores.getParametro2() == "0")
            this.cores.setParametro2("2");
        else if(this.cores.getParametro3() == "0")
            this.cores.setParametro3("3");
        else if(this.cores.getParametro4() == "0")
            this.cores.setParametro4("4");
        else if(this.cores.getParametro5() == "0")
            this.cores.setParametro5("5");
        else if(this.cores.getParametro6() == "0")
            this.cores.setParametro6("6");
        else if(this.cores.getParametro7() == "0")
            this.cores.setParametro7("7");
        else if(this.cores.getParametro8() == "0")
            this.cores.setParametro8("8");
        else if(this.cores.getParametro9() == "0")
            this.cores.setParametro9("9");
        else if(this.cores.getParametro10() == "0")
            this.cores.setParametro10("10");
    }
    
    int getPosCor()
    {
        if(this.cores.getParametro1() != "-" && this.cores.getParametro1() != "0")
            return 1;
        if(this.cores.getParametro2() != "-" && this.cores.getParametro2() != "0")
            return 2;
        if(this.cores.getParametro3() != "-" && this.cores.getParametro3() != "0")
            return 3;
        if(this.cores.getParametro4() != "-" && this.cores.getParametro4() != "0")
            return 4;
        if(this.cores.getParametro5() != "-" && this.cores.getParametro5() != "0")
            return 5;
        if(this.cores.getParametro6() != "-" && this.cores.getParametro6() != "0")
            return 6;
        if(this.cores.getParametro7() != "-" && this.cores.getParametro7() != "0")
            return 7;
        if(this.cores.getParametro8() != "-" && this.cores.getParametro8() != "0")
            return 8;
        if(this.cores.getParametro9() != "-" && this.cores.getParametro9() != "0")
            return 9;
        if(this.cores.getParametro10() != "-" && this.cores.getParametro10() != "0")
            return 10;
        return -1;
    }
}
