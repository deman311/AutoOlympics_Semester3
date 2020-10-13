package Controller;

import Model.Olympic;
import View.VisualConstructor;

public class ProgramRunner {

	private static Olympic currentOlympic;
	
    public static void main(String args[]) {
    	VisualConstructor.mainLaunch(args);
    }
    
    public static Olympic getCurretOlympic() {
    	return currentOlympic;
    }
    
    public static void setCurrentOlympic(String name, String startDate, String endDate) {
    	if(name.isEmpty()&&startDate.isEmpty()&&endDate.isEmpty())
    		currentOlympic = new Olympic("Bobolympics","10/10/2010","12/11/2010");
    	else
    		currentOlympic = new Olympic(name, startDate, endDate);
    }
}