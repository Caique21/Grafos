/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;

/**
 *
 * @author carlo
 */
public class Aresta
{
    private String caracter;
    private Arrow linha;
    private Label label;

    public Aresta()
    {
    }

    public Aresta(String caracter, Arrow linha, Label label)
    {
        this.caracter = caracter;
        this.linha = linha;
        this.label = label;
    }
    public String getCaracter()
    {
        return caracter;
    }

    public void setCaracter(String caracter)
    {
        this.caracter = caracter;
    }

    public Arrow getLinha()
    {
        return linha;
    }

    public void setLinha(Arrow linha)
    {
        this.linha = linha;
    }

    public Label getLabel()
    {
        return label;
    }

    public void setLabel(Label label)
    {
        this.label = label;
    }
    
    
}
