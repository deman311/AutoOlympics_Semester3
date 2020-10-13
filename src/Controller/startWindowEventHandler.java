package Controller;

import javax.swing.JOptionPane;

import Model.Olympic;
import View.VisualConstructor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class startWindowEventHandler implements EventHandler<ActionEvent> {

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

		else if (ae.getSource().toString().contains("Go Back")) {
			VisualConstructor.setScene("main window");
			return;
		}

		else if (ae.getSource().toString().contains("Close Olympics")) {
			VisualConstructor.resetMainTables();
			VisualConstructor.setScene("start window");
			return;
		}

		else if (ae.getSource().toString().contains("Exit")) {
			VisualConstructor.exitProgram();
			return;
		}

		else if (ae.getSource().toString().contains("Submit")) {
			ProgramRunner.setCurrentOlympic(VisualConstructor.getTfName().getText(),
					VisualConstructor.getTfSDate().getText(), VisualConstructor.getTfEdate().getText());
			VisualConstructor.setScene("start window");
			return;
		}

		VisualConstructor.setScene("main window");
	}
}