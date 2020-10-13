package Controller;

import View.VisualConstructor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class tableButtonHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent ae) {
		if(ae.getSource().toString().contains("View Athletes")) {
			VisualConstructor.fillAthleteTable(VisualConstructor.getCurrentSelectedTeam().getMembers());
			VisualConstructor.setScene("athlete window");
		}
		
//		if(ae.getSource().toString().contains("View Competition")) {
//			VisualConstructor.fillCompetitionTable
//			
//		}
	}

}
