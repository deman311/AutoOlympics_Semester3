package Controller;

import View.VisualConstructor;
import javafx.event.EventHandler;

public class tableMouseHandler implements EventHandler<javafx.scene.input.MouseEvent> {

	public void handle(javafx.scene.input.MouseEvent event) {
		if(!VisualConstructor.getCountryTable().getSelectionModel().getSelectedCells().isEmpty())
			VisualConstructor.setCurrentSelectedTeam(VisualConstructor.getCountryTable().getSelectionModel().getSelectedItem());

		if(!VisualConstructor.getCompetitionTable().getSelectionModel().getSelectedCells().isEmpty())
			VisualConstructor.setCurrentSelectedCompetition(VisualConstructor.getCompetitionTable().getSelectionModel().getSelectedItem());
		
		if(!VisualConstructor.getAthleteTable().getSelectionModel().getSelectedCells().isEmpty()) 
			VisualConstructor.setCurrentSelectedAthlete(VisualConstructor.getAthleteTable().getSelectionModel().getSelectedItem());
		
		
	}
}