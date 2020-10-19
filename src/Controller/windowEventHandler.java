package Controller;

import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

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
		}
		
		if(ae.getSource().toString().contains("Manually")) {
	    	ProgramRunner.getCurretOlympic().genCompetitions(false);
			VisualConstructor.fillMainTables(ProgramRunner.getCurretOlympic().getCountries(), ProgramRunner.getCurretOlympic().getCompetitions());
		}

		else if (ae.getSource().toString().contains("Back")) {
			if(VisualConstructor.getCurrentScene().contains("competition window") || VisualConstructor.getCurrentScene().contains("athlete window") || VisualConstructor.getCurrentScene().contains("competitors window"))
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
				boolean isOk = false;
				if(!VisualConstructor.getTfName().getText().isEmpty() && isOkDate(VisualConstructor.getTfSDate().getText() , VisualConstructor.getTfEDate().getText())) {
					ProgramRunner.setCurrentOlympic(VisualConstructor.getTfName().getText(),VisualConstructor.getTfSDate().getText(), VisualConstructor.getTfEDate().getText());
					isOk = true;
				}
				else if(VisualConstructor.getTfName().getText().isEmpty() && VisualConstructor.getTfSDate().getText().isEmpty() && VisualConstructor.getTfEDate().getText().isEmpty()) {
					ProgramRunner.setCurrentOlympic(VisualConstructor.getTfName().getText(),VisualConstructor.getTfSDate().getText(), VisualConstructor.getTfEDate().getText());
					isOk = true;
				}
				else if(VisualConstructor.getTfName().getText().isEmpty())
					JOptionPane.showMessageDialog(null, "No Name Given.", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
				if(isOk)
					VisualConstructor.setScene("start window");
				break;
			case "Human Submit Window":
				try {
					if(VisualConstructor.getTfName().getText().isEmpty())
						throw new NullPointerException();
					if(VisualConstructor.getLastScene().contains("competition window"))
						VisualConstructor.getCurrentSelectedCompetition().setReferee(new Referee(VisualConstructor.getTfName().getText(), VisualConstructor.getSelectedCountry(), VisualConstructor.getCurrentSelectedCompetition().getField()));
					else if(VisualConstructor.getLastScene().contains("athlete window")) {
						if(VisualConstructor.getSelectedField() == null)
							VisualConstructor.getCurrentSelectedTeam().addMember(new RunnerJumper(VisualConstructor.getTfName().getText(),VisualConstructor.getCurrentSelectedTeam()));
						else if(VisualConstructor.getSelectedField().name().contains("RUNNING"))
							VisualConstructor.getCurrentSelectedTeam().addMember(new Runner(VisualConstructor.getTfName().getText(),VisualConstructor.getCurrentSelectedTeam()));
						else if(VisualConstructor.getSelectedField().name().contains("HIGHJUMPING"))
							VisualConstructor.getCurrentSelectedTeam().addMember(new HighJumper(VisualConstructor.getTfName().getText(),VisualConstructor.getCurrentSelectedTeam()));
						
						VisualConstructor.fillAthleteTable(VisualConstructor.getCurrentSelectedTeam().getMembers());
						ProgramRunner.getCurretOlympic().genCompetitions(false);
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Please fill all fields.", "Empty Fields!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ProgramRunner.getCurretOlympic().countMedals();
				VisualConstructor.setScene(VisualConstructor.getLastScene());
				break;
			case "Stadium Submit Window":
				try {
					if(!isNumeric(VisualConstructor.getTfNumOfSeats().getText()))
						throw new InputMismatchException();
					if(VisualConstructor.getTfName().getText().isEmpty() || VisualConstructor.getTfNumOfSeats().getText().isEmpty() || VisualConstructor.getTfLocation().getText().isEmpty())
						throw new NullPointerException();
					if(Integer.parseInt(VisualConstructor.getTfNumOfSeats().getText())<1 || Integer.parseInt(VisualConstructor.getTfNumOfSeats().getText())>100000)
						throw new InputMismatchException();
					VisualConstructor.getCurrentSelectedCompetition().setStadium(new Stadium(VisualConstructor.getTfName().getText(), VisualConstructor.getTfLocation().getText(), Integer.parseInt(VisualConstructor.getTfNumOfSeats().getText())));
					VisualConstructor.setScene(VisualConstructor.getLastScene());
				break;
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Please fill all fields.", "Empty Fields!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				catch (InputMismatchException e) {
					JOptionPane.showMessageDialog(null, "Invalid number of seats.", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Can't have seats in pieces!", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
					return;
				}
			case "Country Submit Window":
				try {
					if(VisualConstructor.getTfName().getText().isEmpty())
						throw new NullPointerException();
					ProgramRunner.getCurretOlympic().addCountry(new NationalTeam(VisualConstructor.getTfName().getText()));
					VisualConstructor.setScene(VisualConstructor.getLastScene());
					break;
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Please fill all fields.", "Empty Fields!", JOptionPane.ERROR_MESSAGE);
					return;
				}
			case "Score Submit Window":
				try {
					Double scoreRun=0d,scoreJump=0d;
					if(VisualConstructor.getCurrentSelectedCompetition().getType().contains("PERSONAL")) {
						if(VisualConstructor.getCurrentSelectedCompetition().getFieldName().contains("RUNNING")) {
							scoreRun = Double.parseDouble(VisualConstructor.getTfName().getText());
							VisualConstructor.getCurrentSelectedAthlete().setRun(""+scoreRun);
							if(scoreRun < 10 || scoreRun > 50)
								throw new NumberFormatException();
						}
						else {
							scoreJump = Double.parseDouble(VisualConstructor.getTfName().getText());	
							VisualConstructor.getCurrentSelectedAthlete().setJump(""+scoreJump);
							if(scoreJump < 0 || scoreJump > 3)
								throw new NumberFormatException();
						}
					}
					else {
						if(VisualConstructor.getCurrentSelectedCompetition().getFieldName().contains("RUNNING")) {
							scoreRun = Double.parseDouble(VisualConstructor.getTfName().getText());
							VisualConstructor.getCurrentSelectedTeam().setRun(""+scoreRun);
							if(scoreRun < 10 || scoreRun > 50)
								throw new NumberFormatException();
						}
						else {
							scoreJump = Double.parseDouble(VisualConstructor.getTfName().getText());
							VisualConstructor.getCurrentSelectedTeam().setJump(""+scoreJump);	
							if(scoreJump < 0 || scoreJump > 3)
								throw new NumberFormatException();
						}
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Please fill all fields.", "Empty Fields!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "INVALID SCORE!" , "Invalid Input!" , JOptionPane.ERROR_MESSAGE);
					return;
				}
				VisualConstructor.setScene(VisualConstructor.getLastScene());
				ProgramRunner.getCurretOlympic().genCompetitions(false);
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
		
		else if(ae.getSource().toString().contains("End Olympics")) {
			ProgramRunner.getCurretOlympic().awardVictors();
			ProgramRunner.getCurretOlympic().countMedals();
			VisualConstructor.setFinished(true);
			VisualConstructor.setScene("Final Window");
			return;
		}
		
		else if(ae.getSource().toString().contains("View Results")) {
			VisualConstructor.setScene("Final Window");
			return;
		}
		
		VisualConstructor.setScene("main window");
	}

	public boolean isOkDate(String sDate,String eDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		try {
			if(dateFormat.parse(sDate.trim()).before(dateFormat.parse(eDate.trim())))
				return true;
			else
				JOptionPane.showMessageDialog(null, "'Start Date' must be before 'End Date'.", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
		} catch (ParseException pe) {
			JOptionPane.showMessageDialog(null, "INVALID DATE!", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
			return false;
        } catch (java.text.ParseException e) {
        	JOptionPane.showMessageDialog(null, "INVALID DATE!", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
        	return false;
        }
		return false;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        @SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}