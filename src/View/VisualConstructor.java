package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.ProgramRunner;
import Controller.tableButtonHandler;
import Controller.tableMouseHandler;
import Controller.windowEventHandler;
import Model.Athlete;
import Model.Competition;
import Model.NationalTeam;
import Model.Olympic.eCompetition;
import Model.Referee;
import Model.Stadium;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VisualConstructor extends Application {

	private static Stage myStage;

	private static TextField tfName, tfSDate, tfEDate, tfNumOfSeats, tfLocation;
	private static TableView<NationalTeam> tvCountries;
	private static TableView<Athlete> tvAthletes;
	private static TableView<Competition> tvCompetition;
	private static TableView<Stadium> tvStadiums;
	private static TableView<Referee> tvReferees;
	private static NationalTeam currentSelectedTeam;
	private static Competition currentSelectedCompetition;
	private static Athlete currentSelectedAthlete;
	private static String currentScene, lastScene;
	private static ComboBox<String> cbCountries, cbFields;
	
	private static boolean finished,allSet;

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
		tvStadiums = new TableView<Stadium>();
		tvReferees = new TableView<Referee>();
		setFinished(false);
		setAllSet(false);
		
		JOptionPane.showMessageDialog(null,
				"Welcome User!\n\n"
				+ "Because the assigment did not speciefy a lot of aspects those were our guidelines:\n\n"
				+ "‣ All referees must be from represented countries in the Olympic, meaning you cannot add a referee from a country that is not in the Olympic.\n"
				+ "‣ The 'Competitors Table' in a competition will show you the total medals of the competitor and \bnot just\b the ones he recieved in the given competition.\n"
				+ "‣ When using 'Automatic' there are approximately 130 different countries in our 'worldCountries.txt' file.\n   we took the liberty to randomly pick 6 of them because when only having 4 competitions there is a slim chance of having 3 absalute winners.",
				"DISCLAIMER!", JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("unchecked")
	public static void setScene(String scene) {
		setLastScene(currentScene);
		setCurrentScene(scene);

		BorderPane mainWindowBP = new BorderPane();
		mainWindowBP.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
		
		tableButtonHandler tbHandler = new tableButtonHandler();
		tableMouseHandler tmHandler = new tableMouseHandler();
		windowEventHandler weHandler = new windowEventHandler();

		Button btnBack = new Button("Back");
		Button btnExit = new Button("Exit");
		Button btnSubmit = new Button("Submit");
		btnBack.setOnAction(weHandler);
		btnExit.setOnAction(weHandler);
		btnSubmit.setOnAction(weHandler);

		// DETAILS FORM -------------------------------------------------------------------------------------------

		 if (scene.equalsIgnoreCase("details form")) {
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
			tfEDate = new TextField();
			tfSDate.setPromptText("dd/mm/yyyy");
			tfEDate.setPromptText("dd/mm/yyyy");

			hbButtons.setSpacing(20);
			hbButtons.setTranslateY(20);
			hbButtons.getChildren().addAll(btnSubmit, btnExit);
			hbButtons.setAlignment(Pos.BASELINE_CENTER);
			hbName.getChildren().addAll(lbName, tfName);
			hbStartDate.getChildren().addAll(lbSDate, tfSDate);
			hbEndDate.getChildren().addAll(lbEDate, tfEDate);
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

			mainVB.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
			Scene detailsForm = new Scene(mainVB, 500, 300);
			myStage.setScene(detailsForm);
		}

			// START WINDOW --------------------------------------------------------------------------------------------
		else if (scene.equalsIgnoreCase("start window")) {
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
						new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));

				btnAutoGen.setOnAction(weHandler);
				btnManualGen.setOnAction(weHandler);
				myStage.setScene(startWindow);
			}

		// MAIN WINDOW ----------------------------------------------------------------------------------

		else if (scene.equalsIgnoreCase("main window")) {
			resetMainTables();
			resetCompetitorsTable();
			fillMainTables(ProgramRunner.getCurretOlympic().getCountries(), ProgramRunner.getCurretOlympic().getCompetitions());
			checkAllSet();
			
			GridPane mainGP = new GridPane();
			Scene mainWindow = new Scene(mainWindowBP, 800, 800);
			mainGP.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));

			// COUTRIES TABLE
			Label lbCountries = new Label();
			VBox CoutriesVB = new VBox();
			Button btnViewAthlethes = new Button("View Athletes");
			Button btnAddCountry = new Button("Add Country");
			Button btnDeleteCountry = new Button("Delete Country");
			tvCountries.setOnMouseClicked(tmHandler);
			btnDeleteCountry.setOnAction(tbHandler);
			btnAddCountry.setOnAction(tbHandler);

			CoutriesVB.getChildren().addAll(btnViewAthlethes, btnAddCountry, btnDeleteCountry);
			CoutriesVB.setAlignment(Pos.CENTER);
			CoutriesVB.setSpacing(10);
			lbCountries.setPadding(new Insets(5));
			lbCountries.setText("Participating Countries:");
			lbCountries.setFont(new Font("Impact", 20));

			if (tvCountries.getColumns().isEmpty()) {
				TableColumn<NationalTeam, String> tcCountryName = new TableColumn<NationalTeam, String>("Country Name");
				tcCountryName.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("sName"));
				tcCountryName.setPrefWidth(150);

				TableColumn<NationalTeam, String> tcCountryNumOfMedals = new TableColumn<NationalTeam, String>(
						"Total Medals");
				tcCountryNumOfMedals.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("sNumOfMedals"));
				tcCountryNumOfMedals.setPrefWidth(100);
				tcCountryNumOfMedals.setSortType(SortType.DESCENDING);

				tvCountries.getColumns().addAll(tcCountryName, tcCountryNumOfMedals);
				tvCountries.getSortOrder().clear();
				tvCountries.getSortOrder().add(tcCountryNumOfMedals);
			}
			

			// COMPETITIONS TABLE
			Label lbCompetitions = new Label();
			VBox competitionsVB = new VBox();
			Button btnViewCompetition = new Button("View Competition");
			Button btnViewAllReferees = new Button("View All Referees");
			Button btnViewAllStadiums = new Button("View All Stadiums");
			Button btnResults = new Button("End Olympics");
			btnResults.setOnAction(weHandler);
			btnResults.disableProperty().set(!isAllSet());
			competitionsVB.getChildren().addAll(btnViewCompetition, btnViewAllReferees, btnViewAllStadiums);
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
			if(!isFinished()) 
				btnDeleteCountry.disableProperty()
						.bind(Bindings.isEmpty(tvCountries.getSelectionModel().getSelectedItems()));
			else {
				btnDeleteCountry.disableProperty().unbind();
				btnDeleteCountry.setDisable(false);
				btnAddCountry.disableProperty().set(isFinished());
				btnDeleteCountry.disableProperty().set(isFinished());
				btnResults.setText("View Results");
			}
			
			btnViewAthlethes.disableProperty()
			.bind(Bindings.isEmpty(tvCountries.getSelectionModel().getSelectedItems()));
			btnViewCompetition.disableProperty()
			.bind(Bindings.isEmpty(tvCompetition.getSelectionModel().getSelectedItems()));
			
			
			tvCountries.setOnMouseClicked(tmHandler);
			tvCompetition.setOnMouseClicked(tmHandler);
			btnViewAthlethes.setOnAction(tbHandler);
			btnViewCompetition.setOnAction(tbHandler);
			btnViewAllStadiums.setOnAction(weHandler);
			btnViewAllReferees.setOnAction(weHandler);

			// VISUAL GRIDPANE
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

			mainWindowBP.setCenter(mainGP);
			HBox hbBot = new HBox();
			if(isAllSet())
				hbBot.getChildren().addAll(btnResults, btnExit);
			else
				hbBot.getChildren().addAll(new Label("Missing Referees or Judges,\nCheck your competitions."),btnResults, btnExit);
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
				hbTitle.getChildren().addAll(logo1, vbTitle, logo2);
				hbTitle.setSpacing(10);
				hbTitle.setAlignment(Pos.CENTER);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Could not find \'ologo.png\'!", "ERROR - File not found!",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			mainWindowBP.setTop(hbTitle);
			mainWindowBP.setBottom(hbBot);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			myStage.setScene(mainWindow);
		}

		// ATHLETE WINDOW
		// --------------------------------------------------------------------------------------------------------
		else if (scene.equalsIgnoreCase("athlete window")) {
			Scene mainWindow = new Scene(mainWindowBP, 800, 800);

			// ATHLETE TABLE
			Label lbAthletes = new Label();
			HBox athleteHB = new HBox();
			Button btnAddAthlete = new Button("Add Athlete");
			Button btnDeleteAthlete = new Button("Delete Athlete");
			btnAddAthlete.setOnAction(tbHandler);
			btnDeleteAthlete.setOnAction(tbHandler);
			btnAddAthlete.disableProperty().set(isFinished());
			btnDeleteAthlete.disableProperty().set(isFinished());
			
			athleteHB.getChildren().addAll(btnAddAthlete, btnDeleteAthlete, btnBack);
			athleteHB.setAlignment(Pos.CENTER);
			athleteHB.setSpacing(20);
			athleteHB.setPadding(new Insets(20));
			lbAthletes.setPadding(new Insets(20));
			lbAthletes.setFont(new Font("Impact", 15));
			
			tvAthletes.setOnMouseClicked(tmHandler);
			

			if (tvAthletes.getColumns().isEmpty()) {
				TableColumn<Athlete, String> tcAthleteName = new TableColumn<Athlete, String>("Athlete Name");
				tcAthleteName.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sName"));
				tcAthleteName.setPrefWidth(250);
				TableColumn<Athlete, String> tcAthleteField = new TableColumn<Athlete, String>("Competing Field");
				tcAthleteField.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sField"));
				tcAthleteField.setPrefWidth(250);
				TableColumn<Athlete, String> tcMedals = new TableColumn<Athlete, String>("Achieved Medals");
				tcMedals.setPrefWidth(250);
				TableColumn<Athlete, String> tcAthleteGold = new TableColumn<Athlete, String>("Gold");
				tcAthleteGold.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sGold"));
				TableColumn<Athlete, String> tcAthleteSilver = new TableColumn<Athlete, String>("Silver");
				tcAthleteSilver.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sSilver"));
				TableColumn<Athlete, String> tcAthleteBronze = new TableColumn<Athlete, String>("Bronze");
				tcAthleteBronze.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sBronze"));
				Circle goldC = new Circle(5, Color.GOLD);
				Circle silverC = new Circle(5, Color.SILVER);
				Circle bronzeC = new Circle(5, Color.SADDLEBROWN);
				goldC.setFill(Color.GOLD);
				silverC.setFill(Color.SILVER);
				bronzeC.setFill(Color.SADDLEBROWN);
				tcAthleteGold.setGraphic(goldC);
				tcAthleteSilver.setGraphic(silverC);
				tcAthleteBronze.setGraphic(bronzeC);

				tcMedals.getColumns().addAll(tcAthleteGold, tcAthleteSilver, tcAthleteBronze);
				tvAthletes.getColumns().addAll(tcAthleteName, tcAthleteField, tcMedals);
			}

			VBox vbTitle = new VBox();
			Label lbTitleName = new Label("Athletes Of " + currentSelectedTeam.getSName());
			lbTitleName.setFont(new Font("Impact", 50));
			vbTitle.getChildren().add(lbTitleName);
			vbTitle.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(vbTitle);
			mainWindowBP.setCenter(tvAthletes);
			mainWindowBP.setBottom(athleteHB);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			myStage.setScene(mainWindow);
		}
		
		// STADIUMS WINDOW
		// --------------------------------------------------------------------------------------------------------
		else if (scene.equalsIgnoreCase("stadiums window")) {
			Scene mainWindow = new Scene(mainWindowBP, 800, 800);

			Label lbStadiums = new Label();
			HBox stadiumsHB = new HBox();
			
			stadiumsHB.getChildren().addAll(btnBack);
			stadiumsHB.setAlignment(Pos.CENTER);
			stadiumsHB.setSpacing(20);
			stadiumsHB.setPadding(new Insets(20));
			lbStadiums.setPadding(new Insets(20));
			lbStadiums.setFont(new Font("Impact", 15));			

			if (tvStadiums.getColumns().isEmpty()) {
				TableColumn<Stadium, String> tcStadiumName = new TableColumn<Stadium, String>("Stadium Name");
				tcStadiumName.setCellValueFactory(new PropertyValueFactory<Stadium, String>("sName"));
				tcStadiumName.setPrefWidth(250);
				TableColumn<Stadium, String> tcStadiumLocation = new TableColumn<Stadium, String>("Location");
				tcStadiumLocation.setCellValueFactory(new PropertyValueFactory<Stadium, String>("sLocation"));
				tcStadiumLocation.setPrefWidth(250);
				TableColumn<Stadium, String> tcStadiumSeats = new TableColumn<Stadium, String>("Number Of Seats");
				tcStadiumSeats.setCellValueFactory(new PropertyValueFactory<Stadium, String>("sNumOfSeats"));
				tcStadiumSeats.setPrefWidth(250);

				tvStadiums.getColumns().addAll(tcStadiumName, tcStadiumLocation, tcStadiumSeats);
			}

			VBox vbTitle = new VBox();
			Label lbTitleName = new Label("Stadiums Of " + ProgramRunner.getCurretOlympic().getName());
			lbTitleName.setFont(new Font("Impact", 50));
			vbTitle.getChildren().add(lbTitleName);
			vbTitle.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(vbTitle);
			mainWindowBP.setCenter(tvStadiums);
			mainWindowBP.setBottom(stadiumsHB);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			myStage.setScene(mainWindow);
		}
	
		// REFEREES WINDOW
		// --------------------------------------------------------------------------------------------------------
		else if (scene.equalsIgnoreCase("referees window")) {
			Scene mainWindow = new Scene(mainWindowBP, 800, 800);

			Label lbReferees = new Label();
			HBox refereesHB = new HBox();
			
			refereesHB.getChildren().addAll(btnBack);
			refereesHB.setAlignment(Pos.CENTER);
			refereesHB.setSpacing(20);
			refereesHB.setPadding(new Insets(20));
			lbReferees.setPadding(new Insets(20));
			lbReferees.setFont(new Font("Impact", 15));

			if (tvReferees.getColumns().isEmpty()) {
				TableColumn<Referee, String> tcRefereeName = new TableColumn<Referee, String>("Name");
				tcRefereeName.setCellValueFactory(new PropertyValueFactory<Referee, String>("sName"));
				tcRefereeName.setPrefWidth(250);
				TableColumn<Referee, String> tcRefereeCountry = new TableColumn<Referee, String>("Country");
				tcRefereeCountry.setCellValueFactory(new PropertyValueFactory<Referee, String>("sCountry"));
				tcRefereeCountry.setPrefWidth(250);
				TableColumn<Referee, String> tcRefereeField = new TableColumn<Referee, String>("Field");
				tcRefereeField.setCellValueFactory(new PropertyValueFactory<Referee, String>("sField"));
				tcRefereeField.setPrefWidth(250);

				tvReferees.getColumns().addAll(tcRefereeName, tcRefereeCountry, tcRefereeField);
			}

			VBox vbTitle = new VBox();
			Label lbTitleName = new Label("Referees Of " + ProgramRunner.getCurretOlympic().getName());
			lbTitleName.setFont(new Font("Impact", 50));
			vbTitle.getChildren().add(lbTitleName);
			vbTitle.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(vbTitle);
			mainWindowBP.setCenter(tvReferees);
			mainWindowBP.setBottom(refereesHB);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			myStage.setScene(mainWindow);
		}

		// COMPETITORS WINDOW
		// --------------------------------------------------------------------------------------------------------
		else if (scene.equalsIgnoreCase("competitors window")) {
			resetMainTables();
			resetCompetitorsTable();
			
			Scene mainWindow = new Scene(mainWindowBP, 800, 800);

			Button btnSetScore = new Button("Set Score");
			Label lbCompetitor = new Label();
			HBox competitorHB = new HBox();
			btnSetScore.setOnAction(tbHandler);
			
			competitorHB.getChildren().addAll(btnSetScore, btnBack);
			competitorHB.setAlignment(Pos.CENTER);
			competitorHB.setSpacing(20);
			competitorHB.setPadding(new Insets(20));
			lbCompetitor.setPadding(new Insets(20));
			lbCompetitor.setFont(new Font("Impact", 15));
			
			tvAthletes.setOnMouseClicked(tmHandler);
			tvCountries.setOnMouseClicked(tmHandler);
			
			if(tvAthletes.getColumns().isEmpty())
				if(currentSelectedCompetition.getType().contains("PERSONAL")) {
					fillAthleteTable(currentSelectedCompetition.getPersonalCompetitors());
					btnSetScore.disableProperty().bind(Bindings.isEmpty(tvAthletes.getSelectionModel().getSelectedCells()));
					
					TableColumn<Athlete, String> tcCompetitorName = new TableColumn<Athlete, String>("Name");
					tcCompetitorName.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sName"));
					tcCompetitorName.setPrefWidth(200);
					TableColumn<Athlete, String> tcCompetitorCountry = new TableColumn<Athlete, String>("Country");
					tcCompetitorCountry.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sCountry"));
					tcCompetitorCountry.setPrefWidth(200);
					TableColumn<Athlete, String> tcMedals = new TableColumn<Athlete, String>("Achieved Medals");
					tcMedals.setPrefWidth(250);
					TableColumn<Athlete, String> tcGold = new TableColumn<Athlete, String>("Gold");
					tcGold.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sGold"));
					TableColumn<Athlete, String> tcSilver = new TableColumn<Athlete, String>("Silver");
					tcSilver.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sSilver"));
					TableColumn<Athlete, String> tcBronze = new TableColumn<Athlete, String>("Bronze");
					tcBronze.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sBronze"));
					Circle goldC = new Circle(5, Color.GOLD);
					Circle silverC = new Circle(5, Color.SILVER);
					Circle bronzeC = new Circle(5, Color.SADDLEBROWN);
					goldC.setFill(Color.GOLD);
					silverC.setFill(Color.SILVER);
					bronzeC.setFill(Color.SADDLEBROWN);
					tcGold.setGraphic(goldC);
					tcSilver.setGraphic(silverC);
					tcBronze.setGraphic(bronzeC);
					tcMedals.getColumns().addAll(tcGold,tcSilver,tcBronze);
					
					TableColumn<Athlete, String> tcCompetitorScore = new TableColumn<Athlete, String>("Score");
					
					if(currentSelectedCompetition.getFieldName().contains("RUNNING")) {
						tcCompetitorScore.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sBestRun"));
						tcCompetitorScore.setSortType(SortType.ASCENDING);
					}
					else {
						tcCompetitorScore.setCellValueFactory(new PropertyValueFactory<Athlete, String>("sBestJump"));
						tcCompetitorScore.setSortType(SortType.DESCENDING);
					}
					
					tcCompetitorScore.setPrefWidth(100);
					tvAthletes.getColumns().addAll(tcCompetitorName, tcCompetitorCountry, tcCompetitorScore, tcMedals);
					tvAthletes.getSortOrder().clear();
					tvAthletes.getSortOrder().add(tcCompetitorScore);
					mainWindowBP.setCenter(tvAthletes);
				}
				else {
					fillMainTables(ProgramRunner.getCurretOlympic().getCountries(), ProgramRunner.getCurretOlympic().getCompetitions());
					btnSetScore.disableProperty().bind(Bindings.isEmpty(tvCountries.getSelectionModel().getSelectedCells()));
					
					TableColumn<NationalTeam, String> tcCompetitorName = new TableColumn<NationalTeam, String>("Name");
					tcCompetitorName.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("sName"));
					tcCompetitorName.setPrefWidth(300);
					TableColumn<NationalTeam, String> tcMedals = new TableColumn<NationalTeam, String>("Achieved Medals");
					tcMedals.setPrefWidth(250);
					TableColumn<NationalTeam, String> tcGold = new TableColumn<NationalTeam, String>("Gold");
					tcGold.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("sGold"));
					TableColumn<NationalTeam, String> tcSilver = new TableColumn<NationalTeam, String>("Silver");
					tcSilver.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("sSilver"));
					TableColumn<NationalTeam, String> tcBronze = new TableColumn<NationalTeam, String>("Bronze");
					tcBronze.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("sBronze"));
					Circle goldC = new Circle(5, Color.GOLD);
					Circle silverC = new Circle(5, Color.SILVER);
					Circle bronzeC = new Circle(5, Color.SADDLEBROWN);
					goldC.setFill(Color.GOLD);
					silverC.setFill(Color.SILVER);
					bronzeC.setFill(Color.SADDLEBROWN);
					tcGold.setGraphic(goldC);
					tcSilver.setGraphic(silverC);
					tcBronze.setGraphic(bronzeC);
					tcMedals.getColumns().addAll(tcGold,tcSilver,tcBronze);
					
					TableColumn<NationalTeam, String> tcCompetitorScore = new TableColumn<NationalTeam, String>("Score");
					
					if(currentSelectedCompetition.getFieldName().contains("RUNNING")) {
						tcCompetitorScore.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("sBestRun"));
						tcCompetitorScore.setSortType(SortType.ASCENDING);
					}
					else {
						tcCompetitorScore.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("sBestJump"));
						tcCompetitorScore.setSortType(SortType.DESCENDING);
					}
					
					if(isFinished()) {
						btnSetScore.disableProperty().unbind();
						btnSetScore.setDisable(true);
					}
					
					tcCompetitorScore.setPrefWidth(100);
					tvCountries.getColumns().addAll(tcCompetitorName, tcCompetitorScore, tcMedals);
					tvCountries.getSortOrder().clear();
					tvCountries.getSortOrder().add(tcCompetitorScore);
					mainWindowBP.setCenter(tvCountries);
				}

			VBox vbTitle = new VBox();
			Label lbTitleName = new Label("Competitors Of " + currentSelectedCompetition.getType() + " " + currentSelectedCompetition.getFieldName());
			lbTitleName.setFont(new Font("Impact", 50));
			vbTitle.getChildren().add(lbTitleName);
			vbTitle.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(vbTitle);
			mainWindowBP.setBottom(competitorHB);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			myStage.setScene(mainWindow);
		}
		
		// Competition Window
		// ----------------------------------------------------------------------------------------------------------------

		else if (scene.equalsIgnoreCase("competition window")) {
			Scene mainWindow = new Scene(mainWindowBP, 500, 500);

			VBox competitionVB = new VBox();
			Button btnSetReferee = new Button("Set Referee");
			Button btnSetStadium = new Button("Set Stadium");
			Button btnViewCompetitiors = new Button("View Competitors");
			btnViewCompetitiors.setOnAction(weHandler);
			btnSetReferee.setOnAction(weHandler);
			btnSetStadium.setOnAction(weHandler);
			btnSetReferee.disableProperty().set(isFinished());
			btnSetStadium.disableProperty().set(isFinished());

			Label lbStadium = new Label("Stadium");
			Label lbReferee = new Label("Referee");
			lbStadium.setFont(new Font("Impact", 20));
			lbReferee.setFont(new Font("Impact", 20));
			Label lbStadiumContent, lbRefereeContent;

			if (currentSelectedCompetition.getStadium() == null)
				lbStadiumContent = new Label("NO STADIUM SELECTED!");
			else
				lbStadiumContent = new Label("Name: " + currentSelectedCompetition.getStadium().getSName() + "\nSeats: "
						+ currentSelectedCompetition.getStadium().getSNumOfSeats() + "\nLocation: "
						+ currentSelectedCompetition.getStadium().getSLocation());
			
			if (currentSelectedCompetition.getReferee() == null)
				lbRefereeContent = new Label("NO REFEREE SELECTED!");
			else
				lbRefereeContent = new Label("Name: " + currentSelectedCompetition.getReferee().getName() + "\nCountry: " + currentSelectedCompetition.getReferee().getCountry().getSName());

			VBox stadiumVB = new VBox();
			VBox refereeVB = new VBox();
			lbStadiumContent.setFont(new Font("Impact",15));
			lbRefereeContent.setFont(new Font("Impact",15));
			stadiumVB.getChildren().addAll(lbStadium, lbStadiumContent);
			refereeVB.getChildren().addAll(lbReferee, lbRefereeContent);
			stadiumVB.setAlignment(Pos.CENTER);
			refereeVB.setAlignment(Pos.CENTER);
			
			VBox vbTitle = new VBox();
			Label lbTitleName = new Label(currentSelectedCompetition.getType() + " " + currentSelectedCompetition.getFieldName());
			lbTitleName.setFont(new Font("Impact", 50));
			vbTitle.getChildren().add(lbTitleName);
			vbTitle.setAlignment(Pos.CENTER);
			
			competitionVB.getChildren().addAll(stadiumVB, refereeVB, btnViewCompetitiors, btnSetReferee, btnSetStadium, btnBack);
			competitionVB.setAlignment(Pos.CENTER);
			competitionVB.setSpacing(10);
			
			mainWindowBP.setTop(vbTitle);
			mainWindowBP.setCenter(competitionVB);
			mainWindowBP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			myStage.setScene(mainWindow);
		}
		 
		else if (scene.equalsIgnoreCase("Score Submit Window")) {

			Scene setWindow = new Scene(mainWindowBP, 500, 500);

			VBox vbSet = new VBox();
			HBox hbTitle = new HBox();
			HBox hbScore = new HBox();
			HBox hbBtns = new HBox();

			Label lbTitle = new Label("Set Score");
			Label lbName = new Label("Score: ");
			Label lbHint = new Label();
			
			if(currentSelectedCompetition.getFieldName().contains("RUNNING"))
				lbHint.setText("Note that a valid score for a 200 meter run is between: 10 to 50");
			else
				lbHint.setText("Note that a valid score for freejumping is between: 0 to 3");
			
			tfName = new TextField();
			
			lbTitle.setFont(new Font("Impact", 40));
			hbTitle.getChildren().add(lbTitle);
			hbTitle.setAlignment(Pos.CENTER);
			hbScore.setAlignment(Pos.CENTER);
			hbScore.setPadding(new Insets(20));
			hbBtns.getChildren().addAll(btnSubmit, btnBack);
			hbBtns.setAlignment(Pos.CENTER);
			hbBtns.setSpacing(20);
			hbScore.getChildren().addAll(lbName, tfName);
			vbSet.getChildren().addAll(lbHint, hbScore, hbBtns);
			vbSet.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(hbTitle);
			mainWindowBP.setCenter(vbSet);

			myStage.setScene(setWindow);
		}

		else if (scene.equalsIgnoreCase("Human Submit Window")) {

			Scene setWindow = new Scene(mainWindowBP, 500, 500);

			VBox vbSet = new VBox();
			HBox hbTitle = new HBox();
			HBox hbField = new HBox();
			HBox hbCountry = new HBox();
			HBox hbName = new HBox();
			HBox hbBtns = new HBox();

			Label lbTitle = new Label("Referee Details");
			Label lbName = new Label("Name: ");
			Label lbCountry = new Label("Country: ");
			Label lbField = new Label("Field: ");

			ObservableList<String> optionsCountries = FXCollections.observableArrayList(ProgramRunner.getCurretOlympic().getCountryNames());
			cbCountries = new ComboBox<String>(optionsCountries);
			ObservableList<String> optionsFields = FXCollections.observableArrayList(ProgramRunner.getCurretOlympic().getFieldNames());
			optionsFields.add("RUNNING, HIGHJUMPING");
			cbFields = new ComboBox<String>(optionsFields);

			tfName = new TextField();
			lbTitle.setFont(new Font("Impact", 40));
			hbTitle.getChildren().add(lbTitle);
			hbTitle.setAlignment(Pos.CENTER);
			hbField.getChildren().addAll(lbField, cbFields);
			hbField.setAlignment(Pos.CENTER);
			hbCountry.setAlignment(Pos.CENTER);
			hbName.setAlignment(Pos.CENTER);
			hbField.setPadding(new Insets(20));
			hbCountry.setPadding(new Insets(20));
			hbName.setPadding(new Insets(20));
			hbBtns.getChildren().addAll(btnSubmit, btnBack);
			hbBtns.setAlignment(Pos.CENTER);
			hbBtns.setSpacing(20);
			hbCountry.getChildren().addAll(lbCountry, cbCountries);
			hbName.getChildren().addAll(lbName, tfName);
		
			if(lastScene.equalsIgnoreCase("athlete window")) {
				lbTitle.setText("Athlete Details");
				vbSet.getChildren().addAll(hbName, hbField, hbBtns);
			}
			else
				vbSet.getChildren().addAll(hbName, hbCountry, hbBtns);
			
			vbSet.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(hbTitle);
			mainWindowBP.setCenter(vbSet);

			myStage.setScene(setWindow);
		}

		else if (scene.equalsIgnoreCase("Stadium Submit Window")) {

			Scene setWindow = new Scene(mainWindowBP, 500, 500);

			VBox vbSet = new VBox();
			HBox hbTitle = new HBox();
			HBox hbLocation = new HBox();
			HBox hbNumOfSeats = new HBox();
			HBox hbName = new HBox();
			HBox hbBtns = new HBox();

			Label lbTitle = new Label("Stadium Details");
			Label lbName = new Label("Name: ");
			Label lbNumOfSeats = new Label("Number Of Seats: ");
			Label lbLocation = new Label("Location: ");

			tfName = new TextField();
			tfNumOfSeats = new TextField();
			tfLocation = new TextField();

			lbTitle.setFont(new Font("Impact", 40));
			hbTitle.getChildren().add(lbTitle);
			hbTitle.setAlignment(Pos.CENTER);
			hbLocation.setAlignment(Pos.CENTER);
			hbNumOfSeats.setAlignment(Pos.CENTER);
			hbName.setAlignment(Pos.CENTER);
			hbBtns.setAlignment(Pos.CENTER);
			hbLocation.setPadding(new Insets(20));
			hbNumOfSeats.setPadding(new Insets(20));
			hbName.setPadding(new Insets(20));
			hbBtns.setSpacing(20);
			hbBtns.getChildren().addAll(btnSubmit, btnBack);
			hbLocation.getChildren().addAll(lbLocation, tfLocation);
			hbNumOfSeats.getChildren().addAll(lbNumOfSeats, tfNumOfSeats);
			hbName.getChildren().addAll(lbName, tfName);
			vbSet.getChildren().addAll(hbName, hbLocation, hbNumOfSeats, hbBtns);
			vbSet.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(hbTitle);
			mainWindowBP.setCenter(vbSet);
			
			myStage.setScene(setWindow);
		}
		
		else if (scene.equalsIgnoreCase("Country Submit Window")) {

			Scene setWindow = new Scene(mainWindowBP, 500, 500);

			VBox vbSet = new VBox();
			HBox hbTitle = new HBox();
			HBox hbName = new HBox();
			HBox hbBtns = new HBox();

			Label lbTitle = new Label("Country Details");
			Label lbName = new Label("Name: ");

			tfName = new TextField();

			lbTitle.setFont(new Font("Impact", 40));
			hbTitle.getChildren().add(lbTitle);
			hbTitle.setAlignment(Pos.CENTER);
			hbName.setAlignment(Pos.CENTER);
			hbBtns.setAlignment(Pos.CENTER);
			hbName.setPadding(new Insets(20));
			hbBtns.setSpacing(20);
			hbBtns.getChildren().addAll(btnSubmit, btnBack);
			hbName.getChildren().addAll(lbName, tfName);
			vbSet.getChildren().addAll(hbName, hbBtns);
			vbSet.setAlignment(Pos.CENTER);
			mainWindowBP.setTop(hbTitle);
			mainWindowBP.setCenter(vbSet);
			
			myStage.setScene(setWindow);
		}
	
	//Final Window -------------------------------------------------------------------------------------
		else if(scene.equalsIgnoreCase("Final Window")) {
		

			Scene setWindow = new Scene(mainWindowBP, 500, 500);

			VBox vbSet = new VBox();
			VBox vbFirst = new VBox();
			VBox vbSecond = new VBox();
			VBox vbThird = new VBox();

			HBox hbTitle = new HBox();
			HBox hbPodium = new HBox();
			HBox hbBtns = new HBox();

			Label lbTitle = new Label(ProgramRunner.getCurretOlympic().getName() + " Winners!");
			Label lbFirst = new Label();
			Label lbSecond = new Label();
			Label lbThird = new Label();
			
			Rectangle rcFirst = new Rectangle(100,250);
			Rectangle rcSecond = new Rectangle(100,175);
			Rectangle rcThird = new Rectangle(100,100);
			
			rcFirst.setFill(Color.GOLD);
			rcSecond.setFill(Color.SILVER);
			rcThird.setFill(Color.SANDYBROWN);
			
			lbFirst.setText(ProgramRunner.getCurretOlympic().findWinners(1));
			lbSecond.setText(ProgramRunner.getCurretOlympic().findWinners(2));
			lbThird.setText(ProgramRunner.getCurretOlympic().findWinners(3));
			
			vbFirst.getChildren().addAll(lbFirst, rcFirst);
			vbSecond.getChildren().addAll(lbSecond, rcSecond);
			vbThird.getChildren().addAll(lbThird, rcThird);
			
			vbFirst.setAlignment(Pos.BOTTOM_CENTER);
			vbSecond.setAlignment(Pos.BOTTOM_CENTER);
			vbThird.setAlignment(Pos.BOTTOM_CENTER);

			hbPodium.getChildren().addAll(vbSecond,vbFirst,vbThird);

			lbTitle.setFont(new Font("Impact", 40));
			hbTitle.getChildren().add(lbTitle);
			hbTitle.setAlignment(Pos.CENTER);
			hbPodium.setAlignment(Pos.BOTTOM_CENTER);
			hbBtns.setAlignment(Pos.CENTER);
			hbPodium.setPadding(new Insets(20));
			hbPodium.setSpacing(50);
			hbBtns.setSpacing(20);
			hbBtns.setPadding(new Insets(20));
			hbBtns.getChildren().addAll(btnBack, btnExit);
			vbSet.getChildren().addAll(hbPodium ,hbBtns);
			vbSet.setAlignment(Pos.BOTTOM_CENTER);
			mainWindowBP.setTop(hbTitle);
			mainWindowBP.setCenter(vbSet);
			
			myStage.setScene(setWindow);
		}
	}

	private static void checkAllSet() {
		for(Competition com : ProgramRunner.getCurretOlympic().getCompetitions())
			if(com.getStadium() == null || com.getReferee() == null)
				return;
		
		setAllSet(true);
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
		if (competition.getType().contains("PERSONAL")) {
			ObservableList<Athlete> data = FXCollections.observableArrayList(competition.getPersonalCompetitors());
			tvAthletes.setEditable(true);
			tvAthletes.setItems(data);
		} else {
			ObservableList<NationalTeam> data = FXCollections.observableArrayList(competition.getNationalCompetitors());
			tvCountries.setEditable(true);
			tvCountries.setItems(data);
		}
	}
	
	public static void fillStadiumsTable(ArrayList<Competition> competitions) {
		ArrayList<Stadium> stadiums = new ArrayList<Stadium>();
		for(Competition com : competitions)
			if(!(com.getStadium()==null))
				stadiums.add(com.getStadium());
		ObservableList<Stadium> data = FXCollections.observableArrayList(stadiums);
		tvStadiums.setEditable(true);
		tvStadiums.setItems(data);
	}
	
	public static void fillRefereesTable(ArrayList<Competition> competitions) {
		ArrayList<Referee> referees = new ArrayList<Referee>();
		for(Competition com : competitions)
			if(!(com.getReferee()==null))
				referees.add(com.getReferee());
		ObservableList<Referee> data = FXCollections.observableArrayList(referees);
		tvReferees.setEditable(true);
		tvReferees.setItems(data);
	}

	public static NationalTeam getSelectedCountry() {
		for (NationalTeam country : ProgramRunner.getCurretOlympic().getCountries())
			if (country.getSName().equalsIgnoreCase(cbCountries.getValue()))
				return country;
		return null;
	}

	public static eCompetition getSelectedField() {
		if(cbFields.getValue().contains("RUNNING, HIGHJUMPING"))
			return null;
		for (eCompetition field : ProgramRunner.getCurretOlympic().getFields())
			if (field.name().equalsIgnoreCase(cbFields.getValue()))
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

	public static TextField getTfEDate() {
		return tfEDate;
	}

	public static void setTfEDate(TextField tfEdate) {
		VisualConstructor.tfEDate = tfEdate;
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
	
	public static void resetCompetitorsTable() {
		tvAthletes = new TableView<Athlete>();
		tvCountries = new TableView<NationalTeam>();
	}
	
	public static String getLastScene() {
		return lastScene;
	}

	public static void setLastScene(String lastScene) {
		VisualConstructor.lastScene = lastScene;
	}

	public static void setCurrentSelectedAthlete(Athlete athlete) {
		 currentSelectedAthlete = athlete;
	}
	
	public static Athlete getCurrentSelectedAthlete() {
		return currentSelectedAthlete;
	}

	public static boolean isFinished() {
		return finished;
	}

	public static void setFinished(boolean finished) {
		VisualConstructor.finished = finished;
	}

	public static boolean isAllSet() {
		return allSet;
	}

	public static void setAllSet(boolean allSet) {
		VisualConstructor.allSet = allSet;
	}
}