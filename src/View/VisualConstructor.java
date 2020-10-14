package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import Controller.ProgramRunner;
import Controller.windowEventHandler;
import Controller.tableButtonHandler;
import Controller.tableMouseHandler;
import Model.Athlete;
import Model.Competition;
import Model.Competitors;
import Model.NationalTeam;
import Model.Olympic;
import Model.Olympic.eCompetition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VisualConstructor extends Application {

	private static Stage myStage;
	
	private static TextField tfName, tfSDate, tfEdate, tfNumOfSeats, tfLocation;
	private static TableView<NationalTeam> tvCountries, tvTeams;
	private static TableView<Athlete> tvAthletes;
	private static TableView<Competition> tvCompetition;
	private static NationalTeam currentSelectedTeam;
	private static Competition currentSelectedCompetition;
	private static String currentScene, lastScene;
	private static ComboBox<String> cbCountries, cbFields;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		myStage = primaryStage;
		primaryStage.setTitle("Olympic Games Managment System - OGMS");
		setScene("details form");
		primaryStage.setResizable(false);
		primaryStage.show();
		tvCountries = new TableView<NationalTeam>();
		tvTeams = new TableView<NationalTeam>();
		tvAthletes = new TableView<Athlete>();
		tvCompetition = new TableView<Competition>();
	}

	@SuppressWarnings("unchecked")
	public static void setScene(String scene) {
		setLastScene(currentScene);
		setCurrentScene(scene);
		
		tableButtonHandler tbHandler = new tableButtonHandler();
		tableMouseHandler tmHandler = new tableMouseHandler();
		windowEventHandler weHandler = new windowEventHandler();
		
		Button btnBack = new Button("Back");
		Button btnExit = new Button("Exit");
		Button btnSubmit = new Button("Submit");
		btnBack.setOnAction(weHandler);
		btnExit.setOnAction(weHandler);
		btnSubmit.setOnAction(weHandler);

		

		// START WINDOW ---------------------------------------------------------------
		if (scene.equalsIgnoreCase("start window")) {
			Label openingText = new Label();
			openingText.setText("Please choose your Olympic Game generation method:");
			openingText.setFont(new Font("Impact", 20));
			Button btnAutoGen = new Button();
			btnAutoGen.setText("Automatic");
			btnAutoGen.setPadding(new Insets(30));
			Button btnManualGen = new Button();
			btnManualGen.setPadding(new Insets(30));
			btnManualGen.setText("Manually");

			HBox mainBtnsHBox = new HBox();
			mainBtnsHBox.getChildren().addAll(btnAutoGen, btnManualGen);
			mainBtnsHBox.setSpacing(30);
			mainBtnsHBox.setAlignment(Pos.CENTER);
			VBox firstVBox = new VBox();
			firstVBox.getChildren().addAll(openingText, mainBtnsHBox);
			firstVBox.setAlignment(Pos.CENTER);
			firstVBox.setSpacing(30);

			Scene startWindow = new Scene(firstVBox, 500, 300);
			firstVBox.setBackground(
					new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));

			btnAutoGen.setOnAction(weHandler);
			btnManualGen.setOnAction(weHandler);
			myStage.setScene(startWindow);
		}

		// DETAILS FORM ---------------------------------------------------------------
		
		else if (scene.equalsIgnoreCase("details form")) {
			VBox mainVB = new VBox();
			HBox hbName = new HBox();
			HBox hbStartDate = new HBox();
			HBox hbEndDate = new HBox();
			HBox hbButtons = new HBox();

			Label lbTitle = new Label("Please Fill The Olympic Details:");
			Label lbName = new Label("Name The Olympic: ");
			Label lbSDate = new Label("Start Date: ");
			Label lbEDate = new Label("End Date: ");
			tfName = new TextField();
			tfSDate = new TextField();
			tfEdate = new TextField();

			hbButtons.setSpacing(20);
			hbButtons.setTranslateY(20);
			hbButtons.getChildren().addAll(btnSubmit, btnBack);
			hbButtons.setAlignment(Pos.BASELINE_CENTER);
			hbName.getChildren().addAll(lbName, tfName);
			hbStartDate.getChildren().addAll(lbSDate, tfSDate);
			hbEndDate.getChildren().addAll(lbEDate, tfEdate);
			lbTitle.setFont(new Font("Impact", 20));
			lbTitle.setAlignment(Pos.CENTER);
			lbTitle.setTranslateY(-20);
			lbName.setPadding(new Insets(5));
			lbSDate.setPadding(new Insets(5));
			lbEDate.setPadding(new Insets(5));
			hbName.setAlignment(Pos.CENTER);
			hbStartDate.setAlignment(Pos.CENTER);
			hbEndDate.setAlignment(Pos.CENTER);
			hbName.setPadding(new Insets(10));
			hbStartDate.setPadding(new Insets(10));
			hbEndDate.setPadding(new Insets(10));

			mainVB.getChildren().addAll(lbTitle, hbName, hbStartDate, hbEndDate, hbButtons);
			mainVB.setAlignment(Pos.CENTER);

			mainVB.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
			Scene detailsForm = new Scene(mainVB, 500, 300);
			myStage.setScene(detailsForm);
		}
		
		//MAIN WINDOW -----------------------------------------------------------	
		
		else if (scene.equalsIgnoreCase("main window")) {
			BorderPane secondWindowBP = new BorderPane();
			GridPane mainGP = new GridPane();
			Scene mainWindow = new Scene(secondWindowBP, 800, 800);
			mainGP.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));

			// COUTRIES TABLE
			Label lbCountries = new Label();
			VBox CoutriesVB = new VBox();
			Button btnViewAthlethes = new Button("View Athletes");
			Button btnAddCountry = new Button("Add Country");
			Button btnDeleteCountry = new Button("Delete Country");

			CoutriesVB.getChildren().addAll(btnViewAthlethes, btnAddCountry, btnDeleteCountry);
			CoutriesVB.setAlignment(Pos.CENTER);
			CoutriesVB.setSpacing(10);
			lbCountries.setPadding(new Insets(5));
			lbCountries.setText("Participating Countries:");
			lbCountries.setFont(new Font("Impact", 20));

			if (tvCountries.getColumns().isEmpty()) {
				TableColumn<NationalTeam, String> tcCountryName = new TableColumn<NationalTeam, String>("Country Name");
				tcCountryName.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("country"));
				tcCountryName.setPrefWidth(150);

				TableColumn<NationalTeam, String> tcCountryNumOfMedals = new TableColumn<NationalTeam, String>(
						"Achieved Medals");
				tcCountryNumOfMedals.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("numOfMedals"));
				tcCountryNumOfMedals.setPrefWidth(100);
				tcCountryNumOfMedals.setSortType(SortType.DESCENDING);
				
				tvCountries.getColumns().addAll(tcCountryName, tcCountryNumOfMedals);
				tvCountries.getSortOrder().add(tcCountryNumOfMedals);
			}

			// COMPETITIONS TABLE
			Label lbCompetitions = new Label();
			VBox competitionsVB = new VBox();
			Button btnViewCompetition = new Button("View Competition");
			Button btnAddCompetition = new Button("Add Competition");
			Button btnDeleteCompetition = new Button("Delete Competition");
			competitionsVB.getChildren().addAll(btnViewCompetition, btnAddCompetition, btnDeleteCompetition);
			competitionsVB.setAlignment(Pos.CENTER);
			competitionsVB.setSpacing(10);
			lbCompetitions.setText("Competitions Of The Olympic:");
			lbCompetitions.setPadding(new Insets(5));
			lbCompetitions.setFont(new Font("Impact", 20));

			if (tvCompetition.getColumns().isEmpty()) {
				TableColumn<Competition, String> tcCompetition = new TableColumn<Competition, String>("Type");
				tcCompetition.setCellValueFactory(new PropertyValueFactory<Competition, String>("type"));
				tcCompetition.setPrefWidth(100);

				TableColumn<Competition, String> tcField = new TableColumn<Competition, String>("Competition");
				tcField.setCellValueFactory(new PropertyValueFactory<Competition, String>("field"));
				tcField.setPrefWidth(150);

				tvCompetition.getColumns().addAll(tcField, tcCompetition);
			}

			btnViewAthlethes.disableProperty()
					.bind(Bindings.isEmpty(tvCountries.getSelectionModel().getSelectedItems()));
			btnDeleteCountry.disableProperty()
					.bind(Bindings.isEmpty(tvCountries.getSelectionModel().getSelectedItems()));
			btnViewCompetition.disableProperty()
					.bind(Bindings.isEmpty(tvCompetition.getSelectionModel().getSelectedItems()));
			btnDeleteCompetition.disableProperty()
					.bind(Bindings.isEmpty(tvCompetition.getSelectionModel().getSelectedItems()));

			tvCountries.setOnMouseClicked(tmHandler);
			tvCompetition.setOnMouseClicked(tmHandler);
			btnViewAthlethes.setOnAction(tbHandler);
			btnViewCompetition.setOnAction(tbHandler);

			// VISUAL TABLE
			mainGP.setAlignment(Pos.TOP_CENTER);
			mainGP.setHgap(50);
			mainGP.setVgap(20);
			mainGP.setPadding(new Insets(20));
			mainGP.add(lbCountries, 0, 0);
			mainGP.add(tvCountries, 0, 1);
			mainGP.add(CoutriesVB, 0, 2);
			mainGP.add(lbCompetitions, 1, 0);
			mainGP.add(tvCompetition, 1, 1);
			mainGP.add(competitionsVB, 1, 2);

			secondWindowBP.setCenter(mainGP);
			Button btnPlay = new Button("Play The Olympics!");
			HBox hbBot = new HBox();
			hbBot.getChildren().addAll(btnPlay,btnExit);
			hbBot.setAlignment(Pos.CENTER);
			hbBot.setSpacing(20);
			hbBot.setPadding(new Insets(10));
			
			VBox vbTitle = new VBox();
			HBox hbTitle = new HBox();
			Label lbTitleName = new Label(ProgramRunner.getCurretOlympic().getName());
			Label lbTitleDate = new Label("Starting " + ProgramRunner.getCurretOlympic().getStartDate() + " until "
					+ ProgramRunner.getCurretOlympic().getEndDate());
			lbTitleName.setFont(new Font("Impact", 50));
			lbTitleDate.setFont(new Font("Impact", 20));
			vbTitle.getChildren().addAll(lbTitleName, lbTitleDate);
			vbTitle.setAlignment(Pos.CENTER);
			try {
				ImageView logo1 = new ImageView(new Image(new FileInputStream("./ologo.jpg")));
				ImageView logo2 = new ImageView(new Image(new FileInputStream("./ologo.jpg")));
				logo1.setFitHeight(100);
				logo1.setFitWidth(100);
				logo2.setFitHeight(100);
				logo2.setFitWidth(100);
				hbTitle.getChildren().addAll(logo1,vbTitle,logo2);
				hbTitle.setSpacing(10);
				hbTitle.setAlignment(Pos.CENTER);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Could not find \'ologo.png\'!","ERROR - File not found!", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			secondWindowBP.setTop(hbTitle);
			secondWindowBP.setBottom(hbBot);
			secondWindowBP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			myStage.setScene(mainWindow);
		}

		// ATHLETE WINDOW --------------------------------------------------------------------------------------------------------
		else if (scene.equalsIgnoreCase("athlete window")) {
			BorderPane mainWindowBP = new BorderPane();
			Scene mainWindow = new Scene(mainWindowBP, 800, 800);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));

			// ATHLETE TABLE
			Label lbAthletes = new Label();
			VBox athleteVB = new VBox();
			Button btnAddAthlete = new Button("Add Athlete");
			Button btnDeleteAthlete = new Button("Delete Athlete");
			athleteVB.getChildren().addAll(btnAddAthlete, btnDeleteAthlete);
			athleteVB.setAlignment(Pos.CENTER);
			athleteVB.setSpacing(10);
			lbAthletes.setPadding(new Insets(10));
			lbAthletes.setText("Current Athletes Of " + currentSelectedTeam.getCountry() + ":");
			lbAthletes.setFont(new Font("Impact", 15));

			if (tvAthletes.getColumns().isEmpty()) {
				TableColumn<Athlete, String> tcAthleteName = new TableColumn<Athlete, String>("Athlete Name");
				tcAthleteName.setCellValueFactory(new PropertyValueFactory<Athlete, String>("athlete_name"));
				tcAthleteName.setPrefWidth(250);
				TableColumn<Athlete, String> tcAthleteField = new TableColumn<Athlete, String>("Competing Field");
				tcAthleteField.setCellValueFactory(new PropertyValueFactory<Athlete, String>("field_name"));
				tcAthleteField.setPrefWidth(250);
				TableColumn<Athlete, String> tcMedals = new TableColumn<Athlete, String>("Achieved Medals");
				tcMedals.setPrefWidth(250);
				TableColumn<Athlete, String> tcAthleteGold = new TableColumn<Athlete, String>("Gold");
				tcAthleteGold.setCellValueFactory(new PropertyValueFactory<Athlete, String>("gold_Medals"));
				TableColumn<Athlete, String> tcAthleteSilver = new TableColumn<Athlete, String>("Silver");
				tcAthleteSilver.setCellValueFactory(new PropertyValueFactory<Athlete, String>("silver_Medals"));
				TableColumn<Athlete, String> tcAthleteBronze = new TableColumn<Athlete, String>("Bronze");
				tcAthleteBronze.setCellValueFactory(new PropertyValueFactory<Athlete, String>("bronze_Medals"));
				Circle goldC = new Circle(5,Color.GOLD);
				Circle silverC = new Circle(5,Color.SILVER);
				Circle bronzeC = new Circle(5,Color.SADDLEBROWN);
				goldC.setFill(Color.GOLD);
				silverC.setFill(Color.SILVER);
				bronzeC.setFill(Color.SADDLEBROWN);
				tcAthleteGold.setGraphic(goldC);
				tcAthleteSilver.setGraphic(silverC);
				tcAthleteBronze.setGraphic(bronzeC);
				
				tcMedals.getColumns().addAll(tcAthleteGold,tcAthleteSilver,tcAthleteBronze);
				tvAthletes.getColumns().addAll(tcAthleteName, tcAthleteField, tcMedals);
			}

			HBox hbExit = new HBox();
			hbExit.getChildren().add(btnBack);
			hbExit.setAlignment(Pos.CENTER);
			hbExit.setPadding(new Insets(10));

			VBox vbTitle = new VBox();
			Label lbTitleName = new Label("Athletes Of " + currentSelectedTeam.getCountry());
			lbTitleName.setFont(new Font("Impact", 50));
			vbTitle.getChildren().add(lbTitleName);
			vbTitle.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(vbTitle);
			mainWindowBP.setCenter(tvAthletes);
			mainWindowBP.setBottom(hbExit);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			myStage.setScene(mainWindow);
		}
		
		
		//Competition Window -----------------------------------------------------------------------------------
		
		else if (scene.equalsIgnoreCase("competition window")) {
			BorderPane mainWindowBP = new BorderPane();
			Scene mainWindow = new Scene(mainWindowBP, 500, 500);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
			
			
			VBox competitionVB = new VBox();
			Button btnSetReferee = new Button("Set Referee");
			Button btnSetStadium = new Button("Set Stadium");
			Button btnViewCompetitiors = new Button("View Competitors");
			btnViewCompetitiors.setOnAction(tbHandler);
			btnSetReferee.setOnAction(weHandler);
			btnSetStadium.setOnAction(weHandler);
			
			Label lbStadium, lbReferee;
			if(currentSelectedCompetition.getStadium() == null)
				lbStadium = new Label("Stadium: NO STADIUM SELECTED!");
			else
				lbStadium = new Label("Stadium: " +currentSelectedCompetition.getStadium().getName() + "\nNum of seats: " + currentSelectedCompetition.getStadium().getNumOfSeats() +"\nLocation: "+currentSelectedCompetition.getStadium().getLocation());
			if(currentSelectedCompetition.getReferee() == null)
				lbReferee = new Label("Referee: NO REFEREE SELECTED!");
			else
				lbReferee = new Label("Referee: "+currentSelectedCompetition.getReferee().getName() +"\nCountry: ");
			lbStadium.setFont(new Font("Impact", 20));
			lbReferee.setFont(new Font("Impact", 20));
			
			competitionVB.getChildren().addAll(lbStadium,lbReferee, btnViewCompetitiors, btnSetStadium, btnSetReferee, btnBack);
			competitionVB.setAlignment(Pos.CENTER);
			competitionVB.setSpacing(10);
			


			VBox vbTitle = new VBox();
			Label lbTitleName = new Label(currentSelectedCompetition.getType() + " " + currentSelectedCompetition.getField());
			lbTitleName.setFont(new Font("Impact", 50));
			vbTitle.getChildren().add(lbTitleName);
			vbTitle.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(vbTitle);
			mainWindowBP.setCenter(competitionVB);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			myStage.setScene(mainWindow);		
		}
		
		else if(scene.equalsIgnoreCase("Human Submit Window")){
			
			BorderPane popUpBP = new BorderPane();
			Scene setWindow = new Scene(popUpBP, 500, 500);
			popUpBP.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
			
			VBox vbSet = new VBox();
			HBox hbField = new HBox();
			HBox hbCountry = new HBox();
			HBox hbName = new HBox();
			HBox hbBtns = new HBox();
			
			Label lbTitle = new Label("Referee Characteres");
			Label lbName = new Label("Name: ");
			Label lbCountry = new Label("Country: ");
			Label lbField = new Label("Field: ");
			
			ObservableList<String> optionsCountries = FXCollections.observableArrayList(ProgramRunner.getCurretOlympic().getCountryNames());
			cbCountries = new ComboBox<String>(optionsCountries);
			
			ObservableList<String> optionsFields = FXCollections.observableArrayList(ProgramRunner.getCurretOlympic().getFieldNames());
			cbFields = new ComboBox<String>(optionsFields);

			
			tfName = new TextField();
			lbTitle.setFont(new Font("Impact",30));
			hbField.getChildren().addAll(lbField, cbFields);
			hbField.setAlignment(Pos.CENTER);
			hbCountry.setAlignment(Pos.CENTER);
			hbName.setAlignment(Pos.CENTER);
			hbField.setPadding(new Insets(20));
			hbCountry.setPadding(new Insets(20));
			hbName.setPadding(new Insets(20));
			hbBtns.getChildren().addAll(btnSubmit,btnBack);
			hbBtns.setAlignment(Pos.CENTER);
			hbBtns.setSpacing(20);
			hbCountry.getChildren().addAll(lbCountry,cbCountries);
			hbName.getChildren().addAll(lbName, tfName);
			vbSet.getChildren().addAll(lbTitle, hbName, hbCountry, hbField, hbBtns);
			vbSet.setAlignment(Pos.CENTER);
			popUpBP.setCenter(vbSet);
			
			myStage.setScene(setWindow);
			
		}
		
		else if(scene.equalsIgnoreCase("Stadium Submit Window")){
			
			BorderPane popUpBP = new BorderPane();
			Scene setWindow = new Scene(popUpBP, 500, 500);
			popUpBP.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
			
			VBox vbSet = new VBox();
			HBox hbLocation = new HBox();
			HBox hbNumOfSeats = new HBox();
			HBox hbName = new HBox();
			HBox hbBtns = new HBox();
			
			Label lbTitle = new Label("Stadium Characteres");
			Label lbName = new Label("Name: ");
			Label lbNumOfSeats = new Label("NumOfSeats: ");
			Label lbLocation = new Label("Location: ");
			
			tfName = new TextField();
			tfNumOfSeats = new TextField();
			tfLocation = new TextField();
			
			lbTitle.setFont(new Font("Impact",30));
			hbLocation.setAlignment(Pos.CENTER);
			hbNumOfSeats.setAlignment(Pos.CENTER);
			hbName.setAlignment(Pos.CENTER);
			hbBtns.setAlignment(Pos.CENTER);
			hbLocation.setPadding(new Insets(20));
			hbNumOfSeats.setPadding(new Insets(20));
			hbName.setPadding(new Insets(20));
			hbBtns.setSpacing(20);
			hbBtns.getChildren().addAll(btnSubmit,btnBack);
			hbLocation.getChildren().addAll(lbLocation, tfLocation);
			hbNumOfSeats.getChildren().addAll(lbNumOfSeats, tfNumOfSeats);
			hbName.getChildren().addAll(lbName, tfName);
			vbSet.getChildren().addAll(lbTitle, hbName, hbLocation, hbNumOfSeats, hbBtns);
			vbSet.setAlignment(Pos.CENTER);
			popUpBP.setCenter(vbSet);
			
			myStage.setScene(setWindow);
			
		}
		
	
	}

	public static void mainLaunch(String[] args) {
		launch(args);
	}

	public static void fillMainTables(ArrayList<NationalTeam> countries, ArrayList<Competition> competitions) {
		ObservableList<NationalTeam> data1 = FXCollections.observableArrayList(countries);
		tvCountries.setEditable(true);
		tvCountries.setItems(data1);
		ObservableList<Competition> data2 = FXCollections.observableArrayList(competitions);
		tvCompetition.setEditable(true);
		tvCompetition.setItems(data2);
	}

	public static void fillAthleteTable(ArrayList<Athlete> athletes) {
		ObservableList<Athlete> data = FXCollections.observableArrayList(athletes);
		tvAthletes.setEditable(true);
		tvAthletes.setItems(data);
	}
	
	public static void fillCompetitorsTable(Competition competition) {
		if(competition.getType().contains("PERSONAL")) {
			ObservableList<Athlete> data = FXCollections.observableArrayList(competition.getPersonalCompetitors());
			tvAthletes.setEditable(true);
			tvAthletes.setItems(data);
		}
		else {
			ObservableList<NationalTeam> data = FXCollections.observableArrayList(competition.getNationalCompetitors());
			tvCountries.setEditable(true);
			tvCountries.setItems(data);
		}
		
	}
	
	public static NationalTeam getSelectedCountry() {
		for(NationalTeam country: ProgramRunner.getCurretOlympic().getCountries())
			if(country.getCountry().equalsIgnoreCase(cbCountries.getValue()))
				return country;
		return null;
	}
	
	public static eCompetition getSelectedField() {
		for(eCompetition field: ProgramRunner.getCurretOlympic().getFields())
			if(field.name().equalsIgnoreCase(cbFields.getValue()))
				return field;
		return null;
	}
	
	public static void setCurrentScene(String scene) {
		currentScene = scene;
	}
	
	public static String getCurrentScene() {
		return currentScene;
	}
	
	public static TextField getTfLocation() {
		return tfLocation;
	}
	
	public static void setTfLocation(TextField tfLocation) {
		VisualConstructor.tfLocation = tfLocation;
	}
	
	public static TextField getTfNumOfSeats() {
		return tfNumOfSeats;
	}
	
	public static void setNumOfSeats(TextField tfNumOfSeats) {
		VisualConstructor.tfNumOfSeats = tfNumOfSeats;
	}

	public static TextField getTfName() {
		return tfName;
	}

	public static void setTfName(TextField tfName) {
		VisualConstructor.tfName = tfName;
	}

	public static TextField getTfSDate() {
		return tfSDate;
	}

	public static void setTfSDate(TextField tfSDate) {
		VisualConstructor.tfSDate = tfSDate;
	}

	public static TextField getTfEdate() {
		return tfEdate;
	}

	public static void setTfEdate(TextField tfEdate) {
		VisualConstructor.tfEdate = tfEdate;
	}

	public static void exitProgram() {
		myStage.close();
	}

	public static NationalTeam getCurrentSelectedTeam() {
		return currentSelectedTeam;
	}

	public static void setCurrentSelectedTeam(NationalTeam value) {
		currentSelectedTeam = value;
	}

	public static void setCurrentSelectedCompetition(Competition value) {
		currentSelectedCompetition = value;
	}

	public static Competition getCurrentSelectedCompetition() {
		return currentSelectedCompetition;
	}

	public static TableView<Athlete> getAthleteTable() {
		return tvAthletes;
	}

	public static TableView<NationalTeam> getCountryTable() {
		return tvCountries;
	}
	
	public static TableView<Competition> getCompetitionTable() {
		return tvCompetition;
	}


	public static void resetMainTables() {
		tvCountries = new TableView<NationalTeam>();
		tvCompetition = new TableView<Competition>();
	}

	public static String getLastScene() {
		return lastScene;
	}

	public static void setLastScene(String lastScene) {
		VisualConstructor.lastScene = lastScene;
	}

}