package br.com.poli.projetoXerox.Interface;

import java.io.IOException;

import br.com.poli.projetoXerox.exceptions.AlterarAtributoException;
import br.com.poli.projetoXerox.exceptions.CampoN�oInformadoException;
import br.com.poli.projetoXerox.repositorio.RepositorioAluno;
import br.com.poli.projetoXerox.sistema.SistemaAluno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AlterarLoginAlunoController {

    @FXML
    private Button bConfirmar;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtLogin;

    @FXML
    private Text texto;

    @FXML
    private Button bVoltar;

    @FXML
    private TextField txtNovoLogin;

    @FXML
    void cOK(ActionEvent event) throws IOException {
    	SistemaAluno sa = new SistemaAluno();
    	RepositorioAluno ra = new RepositorioAluno();
    	if(ra.disponibilidadeLoginAluno(txtNovoLogin.getText())==true){
    		try{
    			sa.alterarLoginAluno(txtLogin.getText(), txtSenha.getText(), txtNovoLogin.getText());
    			abrirAlert();
    			try {
    				new TelaSecundaria("MenuAluno.fxml").start(TelaInicial.stage);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		catch(AlterarAtributoException e){
    			abrirAlertErro();			
    		}
    		catch(CampoN�oInformadoException e){
    			abrirAlertCampos();	
    		}
    	}
    	else{
    		abrirAlertIndisponivel();
    	}
    }
    
    void abrirAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Login alterado com sucesso!");
		alert.setHeaderText("Voc� ser� redirecionado para a tela de menu");
		alert.setContentText("Seu login foi alterado com sucesso");
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file:Imagens/icon.png"));
		stage.show();
		
    }
    
    void abrirAlertCampos() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erro 404! Informa��es n�o encontradas");
		alert.setHeaderText("Preencha corretamente os campos.");
		alert.setContentText("Informa��es Inv�lidas! Digite novamente.");
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file:Imagens/icon.png"));
		stage.show();
		
    }
    
    void abrirAlertIndisponivel() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Login Solicitado Indisponivel");
		alert.setHeaderText("O login solicitado ja est� em uso");
		alert.setContentText("Informe um login que ainda n�o esteja em uso");
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file:Imagens/icon.png"));
		stage.show();
    }
    
    void abrirAlertErro() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erro 404! Usu�rio n�o encontrado");
		alert.setHeaderText("Erro ao alterar o atributo nome");
		alert.setContentText("O login e/ou senha informados s�o inv�lidos para o seu usu�rio");
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file:Imagens/icon.png"));
		stage.show();
		
    }

    @FXML
    void cRetornar(ActionEvent event) {
    	try{
    		new TelaSecundaria("MenuAluno.fxml").start(TelaInicial.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
