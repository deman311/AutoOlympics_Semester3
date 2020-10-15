package Controller;

import Model.NationalTeam;
import View.VisualConstructor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class tableButtonHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent ae) {
		if(ae.getSource().toString().contains("View Athletes")) {
			VisualConstructor.fillAthleteTable(VisualConstructor.getCurrentSelectedTeam().getMembers());
			VisualConstructor.setScene("athlete window");
		}
		
		else if(ae.getSource().toString().contains("View Competition")) 
			VisualConstructor.setScene("competition window");
		
		else if(ae.getSource().toString().contains("Delete")) {
			switch(VisualConstructor.getCurrentScene()) {
			case "athlete window" :
				VisualConstructor.getCurrentSelectedTeam().removeMember(VisualConstructor.getCurrentSelectedAthlete());
				VisualConstructor.fillAthleteTable(VisualConstructor.getCurrentSelectedTeam().getMembers());
				break;
			case "main window" :
				if(ae.getSource().toString().contains("Country"))
					ProgramRunner.getCurretOlympic().removeCountry(VisualConstructor.getCurrentSelectedTeam());
				else
					ProgramRunner.getCurretOlympic().removeCompetition(VisualConstructor.getCurrentSelectedCompetition());
				break;
			}
			VisualConstructor.fillMainTables(ProgramRunner.getCurretOlympic().getCountries(), ProgramRunner.getCurretOlympic().getCompetitions());
		}
		
		else if(ae.getSource().toString().contains("Add Athlete")) 
			VisualConstructor.setScene("Human Submit Window");
			
		else if( ae.getSource().toString().contains("Add Country")) 
			VisualConstructor.setScene("Country Submit Window");
	}
}
