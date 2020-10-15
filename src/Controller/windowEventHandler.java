package Controller;

import javax.swing.JOptionPane;

import Model.HighJumper;
import Model.NationalTeam;
import Model.Olympic;
import Model.Referee;
import Model.Runner;
import Model.RunnerJumper;
import Model.Stadium;
import View.VisualConstructor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class windowEventHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent ae) {
		Olympic olympic = ProgramRunner.getCurretOlympic();

		if (ae.getSource().toString().contains("Automatic")) {
			olympic.autoGenerate();
			VisualConstructor.fillMainTables(olympic.getCountries(), olympic.getCompetitions());
			JOptionPane.showMessageDialog(null,
					"Welcome user!\n\nHere you will be presented with information about an Automatically Generated Olympic: "
							+ ProgramRunner.getCurretOlympic().getName()
							+ ".\nUse the tables to navigate through the Database,\nWhen ready to start the event - press \"Play The Olympics\".",
					"Tip!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(ae.getSource().toString().contains("Manually")) {
	    	ProgramRunner.getCurretOlympic().genCompetitions();
			VisualConstructor.fillMainTables(ProgramRunner.getCurretOlympic().getCountries(), ProgramRunner.getCurretOlympic().getCompetitions());
		}

		else if (ae.getSource().toString().contains("Back")) {
			if(VisualConstructor.getCurrentScene().contains("competition window") || VisualConstructor.getCurrentScene().contains("athlete window"))
				VisualConstructor.setScene("main window");
			else
				VisualConstructor.setScene(VisualConstructor.getLastScene());
			return;
		}

		else if (ae.getSource().toString().contains("Exit")) {
			VisualConstructor.exitProgram();
			return;
		}

		else if (ae.getSource().toString().contains("Submit")) {
			switch(VisualConstructor.getCurrentScene()) {
			case "details form":
				ProgramRunner.setCurrentOlympic(VisualConstructor.getTfName().getText(),
						VisualConstructor.getTfSDate().getText(), VisualConstructor.getTfEdate().getText());
				VisualConstructor.setScene("start window");
				break;
			case "Human Submit Window":
				if(VisualConstructor.getLastScene().contains("competition window"))
					VisualConstructor.getCurrentSelectedCompetition().setReferee(new Referee(VisualConstructor.getTfName().getText(), VisualConstructor.getSelectedCountry(), VisualConstructor.getCurrentSelectedCompetition().getField()));
				else if(VisualConstructor.getLastScene().contains("athlete window")) {
					if(VisualConstructor.getSelectedField() == null)
						VisualConstructor.getCurrentSelectedTeam().addMember(new RunnerJumper(VisualConstructor.getTfName().getText(),VisualConstructor.getSelectedCountry()));
					else if(VisualConstructor.getSelectedField().name().contains("RUNNING"))
						VisualConstructor.getCurrentSelectedTeam().addMember(new Runner(VisualConstructor.getTfName().getText(),VisualConstructor.getSelectedCountry()));
					else if(VisualConstructor.getSelectedField().name().contains("HIGHJUMPING"))
						VisualConstructor.getCurrentSelectedTeam().addMember(new HighJumper(VisualConstructor.getTfName().getText(),VisualConstructor.getSelectedCountry()));
					
					VisualConstructor.fillAthleteTable(VisualConstructor.getCurrentSelectedTeam().getMembers());
				}
					
				VisualConstructor.setScene(VisualConstructor.getLastScene());
				break;
			case "Stadium Submit Window":
				VisualConstructor.getCurrentSelectedCompetition().setStadium(new Stadium(VisualConstructor.getTfName().getText(), VisualConstructor.getTfLocation().getText(), Integer.parseInt(VisualConstructor.getTfNumOfSeats().getText())));
				VisualConstructor.setScene(VisualConstructor.getLastScene());
				break;
			case "Country Submit Window":
				ProgramRunner.getCurretOlympic().addCountry(new NationalTeam(VisualConstructor.getTfName().getText()));
				VisualConstructor.setScene(VisualConstructor.getLastScene());
				VisualConstructor.fillMainTables(ProgramRunner.getCurretOlympic().getCountries(), ProgramRunner.getCurretOlympic().getCompetitions());
				break;
			}	
			return;
		}
		
		else if(ae.getSource().toString().contains("Set Referee")) {
			VisualConstructor.setScene("Human Submit Window");
			return;
		}
		
		else if(ae.getSource().toString().contains("Set Stadium")) {
			VisualConstructor.setScene("Stadium Submit Window");
			return;
		}
		
		else if(ae.getSource().toString().contains("View All Referees")) {
			VisualConstructor.fillRefereesTable(ProgramRunner.getCurretOlympic().getCompetitions());
			VisualConstructor.setScene("referees window");
			return;
		}
		
		else if(ae.getSource().toString().contains("View All Stadiums")) {
			VisualConstructor.fillStadiumsTable(ProgramRunner.getCurretOlympic().getCompetitions());
			VisualConstructor.setScene("stadiums window");
			return;
		}
		
		else if(ae.getSource().toString().contains("View Competitors")) {
			VisualConstructor.resetCompetitorsTable();
			VisualConstructor.fillCompetitorsTable(VisualConstructor.getCurrentSelectedCompetition());
			VisualConstructor.setScene("competitors window");
			return;
		}
		
		VisualConstructor.setScene("main window");
	}
}