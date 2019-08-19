/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis;


import java.util.ArrayList;
import javafx.scene.control.Button;

/**
 *
 * @author carlo
 */
public class Vertice
{
    private Button button;
    private ArrayList<Aresta>ligacoes;

    public Vertice()
    {
    }

    public Vertice(Button button, ArrayList<Aresta> ligacoes)
    {
        this.button = button;
        this.ligacoes = ligacoes;
    }

    public Vertice(Button button)
    {
        this.button = button;
    }

    public Button getButton()
    {
        return button;
    }

    public void setButton(Button button)
    {
        this.button = button;
    }

    public ArrayList<Aresta> getLigacoes()
    {
        return ligacoes;
    }

    public void setLigacoes(ArrayList<Aresta> ligacoes)
    {
        this.ligacoes = ligacoes;
    }
    
    public void addLigacao(Aresta a)
    {
        this.ligacoes.add(a);
    }
}
