package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.ProgramRunner;
import Controller.startWindowEventHandler;
import Controller.tableButtonHandler;
import Controller.tableMouseHandler;
import Model.Athlete;
import Model.Competition;
import Model.NationalTeam;
import Model.Stadium;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
import javafx.stage.Stage;

public class VisualConstructor extends Application {

	private static Stage myStage;

	private static TextField tfName;
	private static TextField tfSDate;
	private static TextField tfEdate;

	private static TableView<NationalTeam> tvCountries;
	private static TableView<Athlete> tvAthletes;
	private static TableView<Competition> tvCompetition;
	private static NationalTeam currentSelectedTeam;
	private static Stadium currentSelectedStadium;

	@Override
	public void start(Stage primaryStage) throws Exception {
		myStage = primaryStage;
		primaryStage.setTitle("Olympic Games Managment System - OGMS");
		setScene("details form");
		primaryStage.setResizable(false);
		primaryStage.show();
		tvCountries = new TableView<NationalTeam>();
		tvAthletes = new TableView<Athlete>();
		tvCompetition = new TableView<Competition>();
	}

	@SuppressWarnings("unchecked")
	public static void setScene(String scene) {

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

			startWindowEventHandler mainEvent = new startWindowEventHandler();
			btnAutoGen.setOnAction(mainEvent);
			btnManualGen.setOnAction(mainEvent);
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

			Button btnSubmit = new Button("Submit");
			Button btnGoBack = new Button("Exit");

			startWindowEventHandler handleForm = new startWindowEventHandler();
			btnSubmit.setOnAction(handleForm);
			btnGoBack.setOnAction(handleForm);
			hbButtons.setSpacing(20);
			hbButtons.setTranslateY(20);
			hbButtons.getChildren().addAll(btnSubmit, btnGoBack);
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

				tvCountries.getColumns().addAll(tcCountryName, tcCountryNumOfMedals);
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
				TableColumn<Competition, String> tcCompetition = new TableColumn<Competition, String>("Competition");
				tcCompetition.setCellValueFactory(new PropertyValueFactory<Competition, String>("type"));
				tcCompetition.setPrefWidth(150);

				TableColumn<Competition, String> tcField = new TableColumn<Competition, String>("Field");
				tcField.setCellValueFactory(new PropertyValueFactory<Competition, String>("field"));
				tcField.setPrefWidth(100);

				tvCompetition.getColumns().addAll(tcCompetition, tcField);
			}

			btnViewAthlethes.disableProperty()
					.bind(Bindings.isEmpty(tvCountries.getSelectionModel().getSelectedItems()));
			btnDeleteCountry.disableProperty()
					.bind(Bindings.isEmpty(tvCountries.getSelectionModel().getSelectedItems()));
			btnViewCompetition.disableProperty()
					.bind(Bindings.isEmpty(tvCompetition.getSelectionModel().getSelectedItems()));
			btnDeleteCompetition.disableProperty()
					.bind(Bindings.isEmpty(tvCompetition.getSelectionModel().getSelectedItems()));

			tableMouseHandler tableMouseEvent = new tableMouseHandler();
			tableButtonHandler tableButtonEvent = new tableButtonHandler();
			tvCountries.setOnMouseClicked(tableMouseEvent);
			tvCompetition.setOnMouseClicked(tableMouseEvent);
			btnViewAthlethes.setOnAction(tableButtonEvent);

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
			Button btnExit = new Button("Exit Program");
			Button btnPlay = new Button("Play The Olympics!");
			startWindowEventHandler exitHandle = new startWindowEventHandler();
			btnExit.setOnAction(exitHandle);
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
			Scene mainWindow = new Scene(mainWindowBP, 700, 700);
			mainWindowBP
					.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));

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

			Button btnBack = new Button("Go Back");
			startWindowEventHandler goBack = new startWindowEventHandler();
			btnBack.setOnAction(goBack);
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
			Scene mainWindow = new Scene(mainWindowBP, 700, 700);
			mainWindowBP
					.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));

			// COMPETITION TABLE
			Label lbCompetition = new Label();
			VBox competitionVB = new VBox();
			Button btnAddCompetition = new Button("Add Competition");
			Button btnDeleteCompetition = new Button("Delete Competition");
			competitionVB.getChildren().addAll(btnAddCompetition, btnDeleteCompetition);
			competitionVB.setAlignment(Pos.CENTER);
			competitionVB.setSpacing(10);
			lbCompetition.setPadding(new Insets(10));
			lbCompetition.setText("Current Competitions Of " + currentSelectedStadium.getName() + ":");
			lbCompetition.setFont(new Font("Impact", 15));

			if (tvCompetition.getColumns().isEmpty()) {
				TableColumn<Competition, String> tcAthleteName = new TableColumn<Competition, String>("Athlete Name");
				tcAthleteName.setCellValueFactory(new PropertyValueFactory<Competition, String>("athlete_name"));
				tcAthleteName.setPrefWidth(250);
				TableColumn<Competition, String> tcAthleteField = new TableColumn<Competition, String>("Competing Field");
				tcAthleteField.setCellValueFactory(new PropertyValueFactory<Competition, String>("field_name"));
				tcAthleteField.setPrefWidth(250);
				TableColumn<Competition, String> tcMedals = new TableColumn<Competition, String>("Achieved Medals");
				tcMedals.setPrefWidth(250);
				TableColumn<Competition, String> tcAthleteGold = new TableColumn<Competition, String>("Gold");
				tcAthleteGold.setCellValueFactory(new PropertyValueFactory<Competition, String>("gold_Medals"));
				TableColumn<Competition, String> tcAthleteSilver = new TableColumn<Competition, String>("Silver");
				tcAthleteSilver.setCellValueFactory(new PropertyValueFactory<Competition, String>("silver_Medals"));
				TableColumn<Competition, String> tcAthleteBronze = new TableColumn<Competition, String>("Bronze");
				tcAthleteBronze.setCellValueFactory(new PropertyValueFactory<Competition, String>("bronze_Medals"));
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

			Button btnBack = new Button("Go Back");
			startWindowEventHandler goBack = new startWindowEventHandler();
			btnBack.setOnAction(goBack);
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

	public static void setCurrentSelectedStadium(Stadium value) {
		currentSelectedStadium = value;
	}

	public static Stadium getCurrentSelectedStadium() {
		return currentSelectedStadium;
	}

	public static TableView<Athlete> getAthleteTable() {
		return tvAthletes;
	}

	public static TableView<NationalTeam> getCountryTable() {
		return tvCountries;
	}


	public static void resetMainTables() {
		tvCountries = new TableView<NationalTeam>();
		tvCompetition = new TableView<Competition>();
	}

}