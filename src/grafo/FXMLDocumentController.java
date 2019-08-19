/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.sun.javafx.collections.ElementObservableListDecorator;
import grafo.Uteis.Aresta;
import grafo.Uteis.Arrow;
import grafo.Uteis.Tabela;
import grafo.Uteis.Vertice;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.QuadCurve;

/**
 *
 * @author carlo
 */
public class FXMLDocumentController implements Initializable 
{
    private ArrayList<Vertice>vertices;
    private ArrayList<Aresta>arestas;
    private Arrow seta;
    private ArrayList<HBox>lista;
    @FXML
    private HBox paneTabelas;
    @FXML
    private AnchorPane paneGrafico;
    @FXML
    private JFXComboBox<String> cbVertice1;
    @FXML
    private JFXComboBox<String> cbVertice2;
    @FXML
    private JFXButton btCriar;
    @FXML
    private JFXButton btRemover;
    @FXML
    private TableView<Tabela> tv_ma;
    @FXML
    private TableView<Tabela> tv_mi;
    @FXML
    private VBox vb_vertices;
    @FXML
    private Label lb_vertices;
    @FXML
    private VBox paneLista;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
        lista = new ArrayList<>();
    }    

    @FXML
    private void evtMouseClicked(MouseEvent event)
    {
        double x = event.getX();
        double y = event.getY();
        int numero;
        
        Button aux = busca(x,y);
        if(vertices.size() < 10 && aux == null)
        {
            aux = new Button(vertices.size() == 0? "0" : String.valueOf(Integer.parseInt(vertices.get(vertices.size() - 1).getButton().getText())+1));
            numero = Integer.parseInt(aux.getText());
            aux.setLayoutX(event.getX());
            aux.setLayoutY(event.getY());
            aux.setStyle("-fx-background-radius:200px");
            aux.setOnMouseClicked(new EventHandler<MouseEvent>(){

                @Override
                public void handle(MouseEvent event)
                {
                    evtButtonClicked(numero);
                }
                
            });
            
            TableColumn c = new TableColumn(aux.getText());
            c.setMaxWidth(35);
            c.setCellValueFactory(new PropertyValueFactory("parametro" + aux.getText()));
            tv_ma.getColumns().add(c);
            tv_ma.getItems().add(new Tabela("-"));
            
            inicializaCelulas(aux);
            
            addLabel(aux);
                    
            vertices.add(new Vertice(aux));
            atualiza_tela();
        }
    }
    
    private Button busca(double x, double y)
    {
        for (int i = 0; i < vertices.size(); i++)
            if(vertices.get(i).getButton().getScaleX() == x && vertices.get(i).getButton().getScaleY()== y)
                return vertices.get(i).getButton();
        return null;
    }
    
    private Button busca(String nome)
    {
        for (int i = 0; i < vertices.size(); i++)
            if(vertices.get(i).getButton().getText() == nome)
            {
                return vertices.get(i).getButton();
            }
        return null;
    }
    
    private void evtButtonClicked(int numero)
    {
        Button b = vertices.get(numero).getButton();
        b.setOnKeyPressed(new EventHandler<KeyEvent>() 
        {
            @Override
            public void handle(KeyEvent event) 
            {
                if(event.getCode() == KeyCode.DELETE)
                {
                    vertices.remove(b);
                    atualiza_tela();
                }
            }
        });
    }
    
    private void atualiza_tela()
    {
        paneGrafico.getChildren().clear();
        for (int i = 0; i < vertices.size(); i++)
            paneGrafico.getChildren().add(vertices.get(i).getButton());
        
        cbVertice1.getItems().clear();
        cbVertice2.getItems().clear();
        for (int i = 0; i < vertices.size(); i++)
        {
            cbVertice1.getItems().add(vertices.get(i).getButton().getText());
            cbVertice2.getItems().add(vertices.get(i).getButton().getText());
        }
        
        for (int i = 0; i < arestas.size(); i++)
            paneGrafico.getChildren().add(arestas.get(i).getLinha());
        
        
        paneLista.getChildren().clear();
        lista.clear();
        for (int i = 0; i < vertices.size(); i++)
        {
            HBox pane = new HBox();
            pane.setPrefWidth(350);
            pane.setId(String.valueOf(i));
            pane.getChildren().add(new Button(vertices.get(i).getButton().getText()));
            paneLista.getChildren().add(pane);
            lista.add(pane);
        }
    }

    @FXML
    private void clickCriar(ActionEvent event)
    {
        if(cbVertice1.getSelectionModel().getSelectedIndex() >=0 && cbVertice2.getSelectionModel().getSelectedIndex() >=0)
        {
            Button b1 = new Button(""),b2 = new Button("");
            double x1 = 0;
            double y1 = 0;
            double x2 = 0;
            double y2 = 0;

            int octante;
            int deltaX,deltaY;

            QuadCurve linha;

            for (int i = 0; i < vertices.size(); i++)
            {
                if(cbVertice1.getSelectionModel().getSelectedItem() == vertices.get(i).getButton().getText())
                {
                    x1 = vertices.get(i).getButton().getLayoutX();
                    y1 = vertices.get(i).getButton().getLayoutY();
                    b1 = vertices.get(i).getButton();
                }

                if(cbVertice2.getSelectionModel().getSelectedItem() == vertices.get(i).getButton().getText())
                {
                    x2 = vertices.get(i).getButton().getLayoutX();
                    y2 = vertices.get(i).getButton().getLayoutY();
                    b2 = vertices.get(i).getButton();
                }
            }

            deltaX = (int)(x2 - x1);
            deltaY = (int)(y2 - y1);

            if(Math.abs(deltaX) > Math.abs(deltaY))
            {
                if(deltaX > 0)
                    x1 = x1 + vertices.get(0).getButton().getWidth();
                else if(deltaX < 0)
                    x2 = x2 + vertices.get(0).getButton().getWidth();

                y1 = y1 + vertices.get(0).getButton().getHeight()/2;
                if(y2 < y1)
                    y2 = y2 + vertices.get(0).getButton().getHeight()/2;
            }
            else
            {
                if(deltaY < 0)
                    y2 = y2 + vertices.get(0).getButton().getHeight();
                else if (deltaY > 0)
                    y1 = y1 + vertices.get(0).getButton().getHeight();

                x1 = x1 + vertices.get(0).getButton().getWidth()/2;
                if(x2 < x1)
                    x2 = x2 + vertices.get(0).getButton().getWidth()/2;
            }
            
            
            if(verifica_sentido(b1,b2))
                seta = new Arrow(x1, y1, x2, y2,b1.getText(),b2.getText());
            else
            {
                remove_seta(b2,b1);
                seta = new Arrow(x1, y1, x2, y2,0,b1.getText(),b2.getText());
            }
            arestas.add(new Aresta("", seta, new Label(b1.getText() + "-" + b2.getText())));

            alteraCelula(b1,b2);
            
            atualiza_matriz_incidencia();

            atualiza_tela();
            
            atualiza_lista(seta);
        }
        else
            new Alert(Alert.AlertType.ERROR, "Escolha um vertice", ButtonType.NO).showAndWait();
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
    }

    private void addLabel(Button aux)
    {
        Label l = new Label(aux.getText());
        l.setMaxHeight(40);
        vb_vertices.getChildren().add(l);
    }
    
    private void inicializaCelulas(Button aux)
    {
        ObservableList<Tabela>a = tv_ma.getItems();
        if(a.isEmpty())
            a.add(new Tabela("-"));
        else
            for (int i = 0; i < a.size(); i++)
            {
                if(tv_ma.getColumns().size() >= 1)
                    a.get(i).setParametro0("-");
                if(tv_ma.getColumns().size() >= 2)
                    a.get(i).setParametro1("-");
                if(tv_ma.getColumns().size() >= 3)
                    a.get(i).setParametro2("-");
                if(tv_ma.getColumns().size() >= 4)
                    a.get(i).setParametro3("-");
                if(tv_ma.getColumns().size() >= 5)
                    a.get(i).setParametro4("-");
                if(tv_ma.getColumns().size() >= 6)
                    a.get(i).setParametro5("-");
                if(tv_ma.getColumns().size() >= 7)
                    a.get(i).setParametro6("-");
                if(tv_ma.getColumns().size() >= 8)
                    a.get(i).setParametro7("-");
                if(tv_ma.getColumns().size() >= 9)
                    a.get(i).setParametro8("-");
                if(tv_ma.getColumns().size() >= 10)
                    a.get(i).setParametro9("-");
            }
        tv_ma.setItems(a);
    }
    
    private void alteraCelula(Button b1,Button b2)
    {
        ObservableList<Tabela>celulas = tv_ma.getItems();
        Tabela t = celulas.get(Integer.parseInt(b1.getText()));
        
        switch(Integer.parseInt(b2.getText()))
        {
            case 0:
                t.setParametro0("1");
                break;
            case 1:
                t.setParametro1("1");
                break;
            case 2:
                t.setParametro2("1");
                break;
            case 3:
                t.setParametro3("1");
                break;
            case 4:
                t.setParametro4("1");
                break;
            case 5:
                t.setParametro5("1");
                break;
            case 6:
                t.setParametro6("1");
                break;
            case 7:
                t.setParametro7("1");
                break;
            case 8:
                t.setParametro8("1");
                break;
            case 9:
                t.setParametro9("1");
                break;
        }
        
        t = celulas.get(Integer.parseInt(b2.getText()));
        
        switch(Integer.parseInt(b1.getText()))
        {
            case 0:
                t.setParametro0("1");
                break;
            case 1:
                t.setParametro1("1");
                break;
            case 2:
                t.setParametro2("1");
                break;
            case 3:
                t.setParametro3("1");
                break;
            case 4:
                t.setParametro4("1");
                break;
            case 5:
                t.setParametro5("1");
                break;
            case 6:
                t.setParametro6("1");
                break;
            case 7:
                t.setParametro7("1");
                break;
            case 8:
                t.setParametro8("1");
                break;
            case 9:
                t.setParametro9("1");
                break;
        }
        tv_ma.setItems(celulas);
        tv_ma.refresh();
    }

    private boolean verifica_sentido(Button b1,Button b2)
    {
        for (int i = 0; i < arestas.size(); i++)
            if(arestas.get(i).getLinha().getOrigem() == b2.getText() && 
                    arestas.get(i).getLinha().getDestino()== b1.getText())
                return false;
        return true;
    }

    private void remove_seta(Button b2, Button b1)
    {
        for (int i = 0; i < arestas.size(); i++)
        {
            if(arestas.get(i).getLinha().getOrigem() == b2.getText() 
                    && arestas.get(i).getLinha().getDestino() == b1.getText())
                arestas.remove(i);
        }
    }

    private void atualiza_matriz_incidencia()
    {
        tv_mi.getColumns().clear();
        tv_mi.getItems().clear();
        for (int i = 0; i < arestas.size(); i++)
        {  
            TableColumn c = new TableColumn("(" + arestas.get(i).getLinha().getOrigem() + "," + 
                    arestas.get(i).getLinha().getDestino() + ")");
            c.setMaxWidth(35);
            c.setCellValueFactory(new PropertyValueFactory("parametro0"));
            tv_mi.getColumns().add(c);
            tv_mi.getItems().add(new Tabela("-"));
        }
        
        
    }

    private void atualiza_lista(Arrow seta)
    {
        HBox pane = (HBox)lista.get(Integer.parseInt(seta.getOrigem()));
        pane.getChildren().add(new Arrow(0, 0, 30, 0, null, null));
        
    }
}
