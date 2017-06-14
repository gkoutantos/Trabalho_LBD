package controller.aluno;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class SimuladoController {
	
	@FXML RadioButton rdbA;
	@FXML RadioButton rdbB;
	@FXML RadioButton rdbC;
	@FXML RadioButton rdbD;
	@FXML RadioButton rdbE;
	
	public void initialize(){
		 final ToggleGroup group = new ToggleGroup();
		 rdbA.setToggleGroup(group);
		 rdbB.setToggleGroup(group);
		 rdbC.setToggleGroup(group);
		 rdbD.setToggleGroup(group);
		 rdbE.setToggleGroup(group);
	}
}
