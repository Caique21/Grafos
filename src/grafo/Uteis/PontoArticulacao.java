/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis;

/**
 *
 * @author Aluno
 */
public class PontoArticulacao 
{
    private String vertice;
    private int prenum;
    private String ligAlte;
    private int menorFilho;
    private int menor;

    public PontoArticulacao() 
    {
    }

    public PontoArticulacao(String vertice) 
    {
        this.vertice = vertice;
    }

    public PontoArticulacao(String vertice, int prenum) 
    {
        this.vertice = vertice;
        this.prenum = prenum;
    }

    public String getVertice() 
    {
        return vertice;
    }

    public void setVertice(String vertice) 
    {
        this.vertice = vertice;
    }

    public int getPrenum() 
    {
        return prenum;
    }

    public void setPrenum(int prenum) 
    {
        this.prenum = prenum;
    }

    public String getLigAlte() 
    {
        return ligAlte;
    }

    public void setLigAlte(String ligAlte) 
    {
        this.ligAlte = ligAlte;
    }

    public int getMenorFilho() 
    {
        return menorFilho;
    }

    public void setMenorFilho(int menorFilho) 
    {
        this.menorFilho = menorFilho;
    }

    public int getMenor() 
    {
        return menor;
    }

    public void setMenor(int menor) 
    {
        this.menor = menor;
    }
}
