/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import grafo.Uteis.Aresta;
import grafo.Uteis.Arrow;
import grafo.Uteis.Arvore.NArea;
import grafo.Uteis.Arvore.No;
import grafo.Uteis.Buscas.Lista;
import grafo.Uteis.Buscas.Profundidade;
import grafo.Uteis.Incidencia;
import grafo.Uteis.PontoArticulacao;
import grafo.Uteis.Tabela;
import grafo.Uteis.Vertice;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
    ArrayList<Label> list_label;
    private NArea tree;
    private List<No> nos;
    
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
    private TableView<Incidencia> tv_mi;
    @FXML
    private VBox vb_vertices;
    @FXML
    private Label lb_vertices;
    @FXML
    private VBox paneLista;
    @FXML
    private JFXTextField tfCusto;
    @FXML
    private Label lb_ma;
    @FXML
    private Label lb_mi;
    @FXML
    private Label lb_lista;
    @FXML
    private Label lbSimplesMA;
    @FXML
    private Label lbRegularMA;
    @FXML
    private Label lbCompletoMA;
    @FXML
    private Label lbSimplesLista;
    @FXML
    private Label lbRegularLista;
    @FXML
    private Label lbCompletoLista;
    @FXML
    private TableView<PontoArticulacao> tvVerticeCorte;
    @FXML
    private TableColumn<String, String> tcVertice;
    @FXML
    private TableColumn<Integer, Integer> tcPreNum;
    @FXML
    private TableColumn<String, String> tcLigalter;
    @FXML
    private TableColumn<Integer, Integer> tcMenorFilho;
    @FXML
    private TableColumn<Integer, Integer> tcMenor;
    @FXML
    private Label lb_lista1;
    @FXML
    private VBox paneListaCores;
    @FXML
    private TableView<Tabela> tv_Cores;
    @FXML
    private JFXButton btColorir;
    @FXML
    private Label lbCorte;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
        lista = new ArrayList<>();
        
        lbSimplesMA.setText("");
        lbRegularMA.setText("");
        lbCompletoMA.setText("");
        
        lbSimplesLista.setText("");
        lbRegularLista.setText("");
        lbCompletoLista.setText("");
        
        TableColumn col = new TableColumn("Arestas");
        tv_mi.getColumns().add(col);
        list_label = new ArrayList<>();
        
        tcVertice.setCellValueFactory(new PropertyValueFactory("vertice"));
        tcPreNum.setCellValueFactory(new PropertyValueFactory("prenum"));
        tcLigalter.setCellValueFactory(new PropertyValueFactory("ligAlte"));
        tcMenorFilho.setCellValueFactory(new PropertyValueFactory("menorFilho"));
        tcMenor.setCellValueFactory(new PropertyValueFactory("menor"));
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
            //quando cria novo vertice, gera nova coluna na matriz adjascente
            TableColumn c = new TableColumn(aux.getText());
            c.setMaxWidth(35);
            c.setCellValueFactory(new PropertyValueFactory("parametro" + aux.getText()));
            tv_ma.getColumns().add(c);
            tv_ma.getItems().add(new Tabela("-"));
            
            inicializaCelulas(aux);
            
            addLabel(aux);
                    
            vertices.add(new Vertice(aux));
            inicializa_colunas_incidencia(aux);
            atualiza_tela(true);
            
            insereVerticePontoArticulacao(aux.getText());
        }
    }
    
    private void inicializaCelulas(Button aux)
    {//meio cachorrada, mas funciona
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
    
    private void inicializa_colunas_incidencia(Button aux)
    {
        //a matriz de incidencia fiz de outra forma. as linhas sao as arestas criadas, e as colunas são os vertices.
        //dessa forma nao precisei fazer uma classe com 45 parametros que representam todas as possiveis arestas criadas
        //inverti linha por coluna.
        tv_mi.getColumns().clear();
        TableColumn col = new TableColumn("Arestas");
        col.setStyle("-fx-alignment: CENTER;");
        col.setCellValueFactory(new PropertyValueFactory("parametro0"));
        tv_mi.getColumns().add(col);
        for (int i = 0; i < vertices.size(); i++)
        {
            TableColumn c = new TableColumn(vertices.get(i).getButton().getText());
            int pos = Integer.parseInt(aux.getText())+1;
            c.setCellValueFactory(new PropertyValueFactory("parametro" + pos));
            c.setMaxWidth(42);
            tv_mi.getColumns().add(c);
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
    
    private void addLabel(Button aux)
    {//sao como os vertices sao exibidos no lado esquedo da tela
        Label l = new Label(aux.getText());
        l.setMaxHeight(40);
        vb_vertices.getChildren().add(l);
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
                    atualiza_tela(true);
                }
            }
        });
    }
    
    private void atualiza_tela(boolean flag)
    {//imprimi os vertices (do grafico e da lista) e as arestas
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
        
        
        for (int i = 0; i < list_label.size(); i++) {
            paneGrafico.getChildren().add(list_label.get(i));
        }
        //a lista funciona como um hbox dentro de um vbox, assim toda vez que um vertice é criado
        //um novo hbox é criado pra ele. qnd uma aresta é criada, os componentes (Arrow e Button) sao inseridos 
        //no hbox. Era pra estar funcionando, mas nao ta
        if(flag)
        {
            paneLista.getChildren().clear();
            lista.clear();
            for (int i = 0; i < vertices.size(); i++)
            {
                HBox pane = new HBox();
                pane.setPrefWidth(350);
                pane.setAlignment(Pos.CENTER_LEFT);
                pane.setId(String.valueOf(i));
                pane.getChildren().add(new Button(vertices.get(i).getButton().getText()));
                paneLista.getChildren().add(pane);
                lista.add(pane);
            }
            
            paneListaCores.getChildren().clear();
            //paneListaCores.getChildren().addAll(paneLista.getChildren());
            for (int i = 0; i < vertices.size(); i++)
            {
                HBox pane = new HBox();
                pane.setPrefWidth(350);
                pane.setAlignment(Pos.CENTER_LEFT);
                pane.setId(String.valueOf(i));
                pane.getChildren().add(new Button(vertices.get(i).getButton().getText()));
                paneListaCores.getChildren().add(pane);
            }
        }
    }

    @FXML
    private void clickCriar(ActionEvent event)
    {
        if(cbVertice1.getSelectionModel().getSelectedIndex() >=0 && cbVertice2.getSelectionModel().getSelectedIndex() >=0)
        {
            if(tfCusto.getText().equals(""))
                tfCusto.setText("1");
            Button b1 = new Button(""),b2 = new Button("");
            double x1 = 0;
            double y1 = 0;
            double x2 = 0;
            double y2 = 0;

            int octante;
            int deltaX,deltaY;

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
            //Esse trecho é pra deixa as arestar menos tortas, esta quase bom
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
            
            //verifica se uma aresta que esta pra ser criada nao existe no sentido contrario, substituindo a cabeça da
            //flecha. Se for criado (1,2), ele cria com flecha. Se dps foi criado (2,1) ele substitui a flecha por uma reta
            if(verifica_aresta_duplicada(b1,b2))
                new Alert(Alert.AlertType.ERROR, "Arestas Duplicada", ButtonType.OK).showAndWait();
            else
            {
                if(verifica_sentido(b1,b2))
                    seta = new Arrow(x1, y1, x2, y2,b1.getText(),b2.getText());
                else
                {
                    if(verifica_custo(b1, b2))
                    {
                        remove_seta(b2,b1);
                        seta = new Arrow(x1, y1, x2, y2,0,b1.getText(),b2.getText());
                        seta.setSeta(Boolean.FALSE);
                    }
                    else
                    {
                        new Alert(Alert.AlertType.ERROR, "Custo incorreto", ButtonType.OK).showAndWait();
                        return;
                    }
                        
                }
                Label lbl = new Label(tfCusto.getText());
                double posx = (b1.getLayoutX() + b2.getLayoutX()) / 2 - 5;
                double posy = (b1.getLayoutY() + b2.getLayoutY()) / 2 + 5;
                lbl.setLayoutX(posx);
                lbl.setLayoutY(posy);
                list_label.add(lbl);
                arestas.add(new Aresta(tfCusto.getText(), seta, lbl));

                alteraCelula(b1,b2,seta);

                atualiza_matriz_incidencia(seta);

                atualiza_tela(false);

                atualiza_lista(seta);
                tfCusto.clear();
                validar();
            }
        }
        else
            new Alert(Alert.AlertType.ERROR, "Escolha um vertice", ButtonType.NO).showAndWait();
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
    }

    private void alteraCelula(Button b1,Button b2,Arrow seta)
    {//outra cachorrada
        ObservableList<Tabela>celulas = tv_ma.getItems();
        Tabela t = celulas.get(Integer.parseInt(b1.getText()));
        
        switch(Integer.parseInt(b2.getText()))
        {
            case 0:
                t.setParametro0(tfCusto.getText());
                break;
            case 1:
                t.setParametro1(tfCusto.getText());
                break;
            case 2:
                t.setParametro2(tfCusto.getText());
                break;
            case 3:
                t.setParametro3(tfCusto.getText());
                break;
            case 4:
                t.setParametro4(tfCusto.getText());
                break;
            case 5:
                t.setParametro5(tfCusto.getText());
                break;
            case 6:
                t.setParametro6(tfCusto.getText());
                break;
            case 7:
                t.setParametro7(tfCusto.getText());
                break;
            case 8:
                t.setParametro8(tfCusto.getText());
                break;
            case 9:
                t.setParametro9(tfCusto.getText());
                break;
        }
        
        if(seta.getDefaultArrowHeadSize() == 0)
        {
            t = celulas.get(Integer.parseInt(b2.getText()));
        
            switch(Integer.parseInt(b1.getText()))
            {
                case 0:
                    t.setParametro0(tfCusto.getText());
                    break;
                case 1:
                    t.setParametro1(tfCusto.getText());
                    break;
                case 2:
                    t.setParametro2(tfCusto.getText());
                    break;
                case 3:
                    t.setParametro3(tfCusto.getText());
                    break;
                case 4:
                    t.setParametro4(tfCusto.getText());
                    break;
                case 5:
                    t.setParametro5(tfCusto.getText());
                    break;
                case 6:
                    t.setParametro6(tfCusto.getText());
                    break;
                case 7:
                    t.setParametro7(tfCusto.getText());
                    break;
                case 8:
                    t.setParametro8(tfCusto.getText());
                    break;
                case 9:
                    t.setParametro9(tfCusto.getText());
                    break;
            }
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
    
    private boolean verifica_custo(Button b1,Button b2)
    {
        for (int i = 0; i < arestas.size(); i++)
            if(arestas.get(i).getLinha().getOrigem() == b2.getText() && 
                    arestas.get(i).getLinha().getDestino()== b1.getText())
                if(arestas.get(i).getCusto().equals(tfCusto.getText()))
                    return true;
        return false;
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

    
    private void atualiza_matriz_incidencia(Arrow seta)
    {
        Incidencia i = new Incidencia("(" + seta.getOrigem() + "," + seta.getDestino() + ")");
        altera_celula_incidencia(i, seta);
        ObservableList<Incidencia> aux = tv_mi.getItems();
        aux.add(i);
        tv_mi.setItems(aux);
    }
    
    private void altera_celula_incidencia(Incidencia incidencia, Arrow seta)
    {
        if(tfCusto.getText().equals(""))
            tfCusto.setText("1");
        switch(Integer.parseInt(seta.getOrigem()))
        {
            case 0:
                incidencia.setParametro1("-" + tfCusto.getText());
                break;
            case 1:
                incidencia.setParametro2("-" + tfCusto.getText());
                break;
            case 2:
                incidencia.setParametro3("-" + tfCusto.getText());
                break;
            case 3:
                incidencia.setParametro4("-" + tfCusto.getText());
                break;
            case 4:
                incidencia.setParametro5("-" + tfCusto.getText());
                break;
            case 5:
                incidencia.setParametro6("-" + tfCusto.getText());
                break;
            case 6:
                incidencia.setParametro7("-" + tfCusto.getText());
                break;
            case 7:
                incidencia.setParametro8("-" + tfCusto.getText());
                break;
            case 8:
                incidencia.setParametro9("-" + tfCusto.getText());
                break;
            case 9:
                incidencia.setParametro10("-" + tfCusto.getText());
                break;
        }
        
        switch(Integer.parseInt(seta.getDestino()))
        {
            case 0:
                incidencia.setParametro1(tfCusto.getText());
                break;
            case 1:
                incidencia.setParametro2(tfCusto.getText());
                break;
            case 2:
                incidencia.setParametro3(tfCusto.getText());
                break;
            case 3:
                incidencia.setParametro4(tfCusto.getText());
                break;
            case 4:
                incidencia.setParametro5(tfCusto.getText());
                break;
            case 5:
                incidencia.setParametro6(tfCusto.getText());
                break;
            case 6:
                incidencia.setParametro7(tfCusto.getText());
                break;
            case 7:
                incidencia.setParametro8(tfCusto.getText());
                break;
            case 8:
                incidencia.setParametro9(tfCusto.getText());
                break;
            case 9:
                incidencia.setParametro10(tfCusto.getText());
                break;
        }
    }

    private void atualiza_lista(Arrow seta)
    {
        HBox pane = (HBox)lista.get(Integer.parseInt(seta.getOrigem()));
        pane.getChildren().add(new Arrow(0, 0, 30, 0, null, null));
        pane.getChildren().add(new Button(seta.getDestino()));
        
        HBox pane2 = (HBox)paneListaCores.getChildren().get(Integer.parseInt(seta.getOrigem()));
        pane2.getChildren().add(new Arrow(0, 0, 30, 0, null, null));
        pane2.getChildren().add(new Button(seta.getDestino()));
    }

    private boolean verifica_aresta_duplicada(Button b1, Button b2) 
    {
        for (int i = 0; i < arestas.size(); i++) 
            if(arestas.get(i).getLinha().getOrigem() == b1.getText()
                    && arestas.get(i).getLinha().getDestino()== b2.getText())
                return true;
        for (int i = 0; i < arestas.size(); i++) 
            if(!arestas.get(i).getLinha().isSeta())
                if(arestas.get(i).getLinha().getDestino()== b1.getText()
                    && arestas.get(i).getLinha().getOrigem()== b2.getText()
                    || arestas.get(i).getLinha().getOrigem() == b1.getText()
                    && arestas.get(i).getLinha().getDestino()== b2.getText())
                    return true;
        return false;
    }
    
    
    public void validar()
    {
        if(validacaoSimplesMA())
        {
            if(!lbSimplesMA.getText().contains("simples"))
                lbSimplesMA.setText(lbSimplesMA.getText() + "- simples");
        } 
        else
            lbSimplesMA.setText("");
        
        String grau = "";
        if(validacaoRegularMA(grau))
        {
            if(!lbRegularMA.getText().contains("regular"))
                lbRegularMA.setText("- regular: " + grau);
        }
        else
            lbRegularMA.setText("");
        
        if(validacaoCompletoMA())
        {
            if(!lbCompletoMA.getText().contains("completo"))
            {
                lbCompletoMA.setText(lbCompletoMA.getText() + "- completo: k" + tv_ma.getItems().size());
            }
        } 
        else
            lbCompletoMA.setText("");
        
        
        if(validacaoSimplesLista())
        {
            if(!lbSimplesLista.getText().contains("simples"))
                lbSimplesLista.setText(lbSimplesLista.getText() + "- simples");
        } 
        else
            lbSimplesLista.setText("");
        
        grau = "";
        if(validacaoRegularLista(grau))
        {
            if(!lbRegularLista.getText().contains("regular"))
                lbRegularLista.setText(lbRegularLista.getText() + "- regular: " + grau);
        } 
        else
            lbRegularLista.setText("");
        
        if(validacaoCompletoLista())
        {
            if(!lbCompletoLista.getText().contains("completo"))
                lbCompletoLista.setText(lbCompletoLista.getText() + "- completo: k" + paneLista.getChildren().size());
        } 
        else
            lbCompletoLista.setText("");
    }
    
    ///////////////////////////////////// VALIDAÇÕES MATRIZ ADJACENTE /////////////////////////////////////
    private boolean validacaoSimplesMA()
    {
        for (int i = 0; i < tv_ma.getColumns().size() - 1; i++)
            for (int j = i + 1; j < tv_ma.getColumns().size(); j++)
                if(tv_ma.getColumns().get(i).getText().equals(tv_ma.getColumns().get(j).getText()))
                    return false;
        ObservableList<Tabela>rows = tv_ma.getItems();
        for (int i = 0; i < tv_ma.getColumns().size(); i++)
            switch(i)
            {
                case 0:
                    if(!rows.get(i).getParametro0().equals("-"))
                        return false;
                    break;
                case 1:
                    if(!rows.get(i).getParametro1().equals("-"))
                        return false;
                    break;
                case 2:
                    if(!rows.get(i).getParametro2().equals("-"))
                        return false;
                    break;
                case 3:
                    if(!rows.get(i).getParametro3().equals("-"))
                        return false;
                    break;
                case 4:
                    if(!rows.get(i).getParametro4().equals("-"))
                        return false;
                    break;
                case 5:
                    if(!rows.get(i).getParametro5().equals("-"))
                        return false;
                    break;
                case 6:
                    if(!rows.get(i).getParametro6().equals("-"))
                        return false;
                    break;
                case 7:
                    if(!rows.get(i).getParametro7().equals("-"))
                        return false;
                    break;
                case 8:
                    if(!rows.get(i).getParametro8().equals("-"))
                        return false;
                    break;
                case 9:
                    if(!rows.get(i).getParametro9().equals("-"))
                        return false;
                    break;
            }
        return true;
    }
    
    private boolean validacaoRegularMA(String grau_retorno)
    {
        int grau = 0,aux;
        
        if(tv_ma.getItems().get(0).getParametro0() != null && !tv_ma.getItems().get(0).getParametro0().equals("-"))
            grau++;
        if(tv_ma.getItems().get(0).getParametro1() != null && !tv_ma.getItems().get(0).getParametro1().equals("-"))
            grau++;
        if(tv_ma.getItems().get(0).getParametro2() != null && !tv_ma.getItems().get(0).getParametro2().equals("-"))
            grau++;
        if(tv_ma.getItems().get(0).getParametro3() != null && !tv_ma.getItems().get(0).getParametro3().equals("-"))
            grau++;
        if(tv_ma.getItems().get(0).getParametro4() != null && !tv_ma.getItems().get(0).getParametro4().equals("-"))
            grau++;
        if(tv_ma.getItems().get(0).getParametro5() != null && !tv_ma.getItems().get(0).getParametro5().equals("-"))
            grau++;
        if(tv_ma.getItems().get(0).getParametro6() != null && !tv_ma.getItems().get(0).getParametro6().equals("-"))
            grau++;
        if(tv_ma.getItems().get(0).getParametro7() != null && !tv_ma.getItems().get(0).getParametro7().equals("-"))
            grau++;
        if(tv_ma.getItems().get(0).getParametro8() != null && !tv_ma.getItems().get(0).getParametro8().equals("-"))
            grau++;
        if(tv_ma.getItems().get(0).getParametro9() != null && !tv_ma.getItems().get(0).getParametro9().equals("-"))
            grau++;
        
        for (int i = 1; i < tv_ma.getItems().size(); i++)
        {
            aux = 0;
            if(tv_ma.getItems().get(i).getParametro0() != null && !tv_ma.getItems().get(i).getParametro0().equals("-"))
                aux++;
            if(tv_ma.getItems().get(i).getParametro1() != null && !tv_ma.getItems().get(i).getParametro1().equals("-"))
                aux++;
            if(tv_ma.getItems().get(i).getParametro2() != null && !tv_ma.getItems().get(i).getParametro2().equals("-"))
                aux++;
            if(tv_ma.getItems().get(i).getParametro3() != null && !tv_ma.getItems().get(i).getParametro3().equals("-"))
                aux++;
            if(tv_ma.getItems().get(i).getParametro4() != null && !tv_ma.getItems().get(i).getParametro4().equals("-"))
                aux++;
            if(tv_ma.getItems().get(i).getParametro5() != null && !tv_ma.getItems().get(i).getParametro5().equals("-"))
                aux++;
            if(tv_ma.getItems().get(i).getParametro6() != null && !tv_ma.getItems().get(i).getParametro6().equals("-"))
                aux++;
            if(tv_ma.getItems().get(i).getParametro7() != null && !tv_ma.getItems().get(i).getParametro7().equals("-"))
                aux++;
            if(tv_ma.getItems().get(i).getParametro8() != null && !tv_ma.getItems().get(i).getParametro8().equals("-"))
                aux++;
            if(tv_ma.getItems().get(i).getParametro9() != null && !tv_ma.getItems().get(i).getParametro9().equals("-"))
                aux++;
            
            if(aux != grau)
                return false;
        }
        grau_retorno = String.valueOf(grau);
        return true;
    }
    
    public boolean validacaoCompletoMA()
    {
        for (int i = 0; i < tv_ma.getItems().size(); i++)
        {
            for (int j = 0; j < tv_ma.getColumns().size(); j++)
            {
                if(j != i)
                    if(!tv_ma.getItems().get(i).valida(j))
                        return false;
                if(j == i)
                    if(tv_ma.getItems().get(i).valida(j))
                        return false;
            }
        }
        return true;
    }
    ///////////////////////////////////// VALIDAÇÕES MATRIZ ADJACENTE /////////////////////////////////////
    
    ///////////////////////////////////// LISTA /////////////////////////////////////
    private boolean validacaoSimplesLista()
    {
        for (int i = 0; i < paneLista.getChildren().size(); i++)
        {
            HBox pane = (HBox)paneLista.getChildren().get(i);
            Button vertice = (Button)pane.getChildren().get(0);
            for (int j = 2; j < pane.getChildren().size(); j+=2)
            {
                Button aux = (Button)pane.getChildren().get(j);
                if(aux.getText().equals(vertice.getText()))
                    return false;
            }
        }
        return true;
    }
    
    private boolean validacaoRegularLista(String grau_retorno)
    {
        int grau = 0,aux = 0;
        HBox pane = (HBox)paneLista.getChildren().get(0);
        for (int i = 2; i < pane.getChildren().size(); i+=2)
            grau++;
        
        for (int i = 1; i < paneLista.getChildren().size(); i++)
        {
            aux = 0;
            pane = (HBox)paneLista.getChildren().get(i);
            for (int j = 2; j < pane.getChildren().size(); j+=2)
                aux++;
            if(aux != grau)
                return false;
        }
        grau_retorno = String.valueOf(grau);
        return true;
    }
    
    public boolean validacaoCompletoLista()
    {
        int contador = 0;
        for (int i = 0; i < paneLista.getChildren().size(); i++)
        {
            contador = 0;
            HBox pane = (HBox)paneLista.getChildren().get(i);
            Button vertice = (Button)pane.getChildren().get(0);
            for (int j = 2; j < pane.getChildren().size(); j+=2)
            {
                if(((Button)pane.getChildren().get(j)).getText() != vertice.getText())
                    contador++;
                else
                    return false;
            }
            if(contador != paneLista.getChildren().size()-1)
                return false;
        }
        return true;
    }
    
    ///////////////////////////////////// LISTA /////////////////////////////////////

    @FXML
    private void clickColorir(ActionEvent event) 
    {
        tree = new NArea();
        nos = new LinkedList<>();
        ObservableList<PontoArticulacao> pa = tvVerticeCorte.getItems();
        Profundidade p = new Profundidade(vertices.size());
        tree.inserir(Integer.parseInt(vertices.get(0).getButton().getText()), 0);
        criaArvore();
        p.busca(pa, tree.getRaiz(), 0);
        tree.verificaLigAlt(tree.getRaiz(),pa);
        tvVerticeCorte.setItems(pa);
        
        tree.in_ordem(tree.getRaiz());
        tree.refresh(tree.getRaiz(),pa);
        tvVerticeCorte.refresh();
        tree.insereLista(tree.getRaiz(),nos);
        
        for (int i = 0; i < nos.size(); i++) 
        {
            if(pa.get(nos.get(i).getVinfo()).getPrenum() <= pa.get(nos.get(i).getVinfo()).getMenorFilho())
                lbCorte.setText(lbCorte.getText() + nos.get(i).getVinfo() + ",");
        }
        pa.get(0);
    }

    private void insereVerticePontoArticulacao(String vertice) 
    {
        PontoArticulacao p = new PontoArticulacao(vertice);
        
        tvVerticeCorte.getItems().add(p);
    }
    
    public void criaArvore()
    {
        for (int i = 0; i < vertices.size(); i++) 
        {
            ArrayList<Aresta> aux = new ArrayList<>();
            for (int j = 0; j < arestas.size(); j++) 
            {
                if(arestas.get(j).getLinha().getOrigem() == vertices.get(i).getButton().getText())
                    aux.add(arestas.get(j));
                if(!arestas.get(j).getLinha().isSeta() && arestas.get(j).getLinha().getDestino() == vertices.get(i).getButton().getText())
                {
                    Aresta a1 = new Aresta();
                    Arrow a2 = new Arrow(j, j, j, j, arestas.get(j).getLinha().getDestino(), arestas.get(j).getLinha().getOrigem());
                    a1.setLinha(a2);
                    boolean flag = true;
                    for (int k = 0; k < aux.size() && flag; k++) 
                    {
                        if(aux.get(k).getLinha().getOrigem() == a2.getOrigem())
                            flag = false;
                    }
                    if(flag)
                        aux.add(a1);
                }
            }
            
            No ret = tree.find(tree.getRaiz(),Integer.parseInt(vertices.get(i).getButton().getText()));
            if(aux.size() > 0)
                ret.setN(aux.size());
            
            for (int j = 0; j < aux.size(); j++) 
                tree.inserir(ret,Integer.parseInt(aux.get(j).getLinha().getDestino()), 0);
            
        }
    }
}
