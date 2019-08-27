/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo.Uteis;

/**
 *
 * @author carlo
 */
public class Tabela
{
    private String parametro0;
    private String parametro1;
    private String parametro2;
    private String parametro3;
    private String parametro4;
    private String parametro5;
    private String parametro6;
    private String parametro7;
    private String parametro8;
    private String parametro9;

    public Tabela()
    {
    }

    public Tabela(String parametro0)
    {
        this.parametro0 = parametro0;
    }
    
    public String getParametro0()
    {
        return parametro0;
    }

    public void setParametro0(String parametro0)
    {
        this.parametro0 = parametro0;
    }

    public String getParametro1()
    {
        return parametro1;
    }

    public void setParametro1(String parametro1)
    {
        this.parametro1 = parametro1;
    }

    public String getParametro2()
    {
        return parametro2;
    }

    public void setParametro2(String parametro2)
    {
        this.parametro2 = parametro2;
    }

    public String getParametro3()
    {
        return parametro3;
    }

    public void setParametro3(String parametro3)
    {
        this.parametro3 = parametro3;
    }

    public String getParametro4()
    {
        return parametro4;
    }

    public void setParametro4(String parametro4)
    {
        this.parametro4 = parametro4;
    }

    public String getParametro5()
    {
        return parametro5;
    }

    public void setParametro5(String parametro5)
    {
        this.parametro5 = parametro5;
    }

    public String getParametro6()
    {
        return parametro6;
    }

    public void setParametro6(String parametro6)
    {
        this.parametro6 = parametro6;
    }

    public String getParametro7()
    {
        return parametro7;
    }

    public void setParametro7(String parametro7)
    {
        this.parametro7 = parametro7;
    }

    public String getParametro8()
    {
        return parametro8;
    }

    public void setParametro8(String parametro8)
    {
        this.parametro8 = parametro8;
    }

    public String getParametro9()
    {
        return parametro9;
    }

    public void setParametro9(String parametro9)
    {
        this.parametro9 = parametro9;
    }
    
    public boolean valida(int i)
    {
        switch(i)
        {
            case 0:
                if(parametro0 != null && parametro0.equals("-"))
                    return false;
                break;
            case 1:
                if(parametro1 != null && parametro1.equals("-"))
                    return false;
                break;
            case 2:
                if(parametro2 != null && parametro2.equals("-"))
                    return false;
                break;
            case 3:
                if(parametro3 != null && parametro3.equals("-"))
                    return false;
                break;
            case 4:
                if(parametro4 != null && parametro4.equals("-"))
                    return false;
                break;
            case 5:
                if(parametro5 != null && parametro5.equals("-"))
                    return false;
                break;
            case 6:
                if(parametro6 != null && parametro6.equals("-"))
                    return false;
                break;
            case 7:
                if(parametro7 != null && parametro7.equals("-"))
                    return false;
                break;
            case 8:
                if(parametro8 != null && parametro8.equals("-"))
                    return false;
                break;
            case 9:
                if(parametro9 != null && parametro9.equals("-"))
                    return false;
                break;
        }
        return true;
    }
}
