/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis.Arvore;

/**
 *
 * @author Aluno
 */
import java.util.Arrays;

/**
 *
 * @author luish
 */
public class No
{

    private int vinfo;
    private No[] vLig;
    private int TL;
    private int N;
    private int prenum;
    private String ligAlte;
    private int menorFilho;
    private int menor;

    public No(int info, int N)
    {
        this.vinfo = info;
        this.vLig = new No[N];
        this.TL = 1;
        this.N = N;
    }

    public int getVinfo()
    {
        return vinfo;
    }

    public void setInfo(int pos, int info)
    {
        this.vinfo = info;
    }

    public int getN() {
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

    public int getTL()
    {
        return TL;
    }

    public void setTL(int TL)
    {
        this.TL = TL;
    }

    public int getPrenum() {
        return prenum;
    }

    public void setPrenum(int prenum) {
        this.prenum = prenum;
    }

    public String getLigAlte() {
        return ligAlte;
    }

    public void setLigAlte(String ligAlte) {
        this.ligAlte = ligAlte;
    }

    public int getMenorFilho() {
        return menorFilho;
    }

    public void setMenorFilho(int menorFilho) {
        this.menorFilho = menorFilho;
    }

    public int getMenor() {
        return menor;
    }

    public void setMenor(int menor) {
        this.menor = menor;
    }

    public void setN(int N) 
    {
        this.N = N;
        
        No[] auxLig = vLig;
        vLig = new No[N];
        for (int i = 0; i < this.N; i++) 
            this.setvLig(i, null);
        
        for (int i = 0; i < auxLig.length; i++) 
            this.setvLig(i, new No(auxLig[i].getVinfo(), 0));
    }

    
}
