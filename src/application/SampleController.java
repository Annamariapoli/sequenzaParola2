package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModel(Model m){
		this.m=m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLunghezza;

    @FXML
    private Button btnCarica;

    @FXML
    private TextField txtIniziale;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCarica(ActionEvent event) {
    	txtResult.clear();
    	try{
    		int lung = Integer.parseInt(txtLunghezza.getText());
    		if(lung<2){
    			txtResult.appendText("Lunghezza errata!\n");
    			return;
    		}
    		int numero = m.conta(lung);
    		m.buidGraph(lung);
    		txtResult.appendText("Numero parole : "+numero);
    		txtResult.appendText("Numero totale di collegamenti: " +m.getGraph().edgeSet().size());
    		
    		
    		
    	}catch(Exception e ){
    	   txtResult.appendText("Errore nel formato!\n");
    	   return;
    	}
    	
    	

    }

    @FXML
    void doCerca(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtLunghezza != null : "fx:id=\"txtLunghezza\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCarica != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtIniziale != null : "fx:id=\"txtIniziale\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

    }
}
