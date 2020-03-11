/**
 * Author: Puan Xue Sheng
 *
 * Comments: This is a simple UI application created to practice and apply the tools that I have learnt from JavaFX.
 */


package sample;

import com.sun.javafx.scene.control.ContextMenuContent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static ObservableList<Contacts> supportList = FXCollections.observableArrayList(
            new Contacts("xue sheng","puan"),
            new Contacts("Denny","Wiranata"),
            new Contacts("Peng","Wei"),
            new Contacts("Stephen","Tang"),
            new Contacts("Hafiz","Sallim"),
            new Contacts("Sharath","Halner"),
            new Contacts("James","Ateanza"),
            new Contacts("Froniando","-")
    );

    public static ObservableList<Contacts> oppList = FXCollections.observableArrayList(
            new Contacts("Alex","Q"),
            new Contacts("Adam","A"),
            new Contacts("Adrian","B"),
            new Contacts("Addrey","E")

    );
    public static ObservableList<Contacts> saleList = FXCollections.observableArrayList(
            new Contacts("barry","Q"),
            new Contacts("simson","A"),
            new Contacts("xiao li","B"),
            new Contacts("xiao hong","E")

    );


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("My First UI using JavaFX");

        //create another stage for simulated login
        Stage loginStage = new Stage();
        GridPane loginRoot = new GridPane();
        //loginRoot.setGridLinesVisible(true);
        loginRoot.setHgap(10);
        loginRoot.setVgap(20);
        loginRoot.setPadding(new Insets(10,10,10,10));
            //create username and password fields
            Text loginText = new Text("Username");
            Text passText = new Text("Password");
            loginRoot.add(loginText,0,0);
            loginRoot.add(passText,0,1);
            TextField loginTextField = new TextField();
            PasswordField passTextField = new PasswordField();
            passTextField.setFont(Font.font("Arial",10));
            loginRoot.add(loginTextField,1,0);
            loginRoot.add(passTextField,1,1);


        Button loginBtn = new Button("Login");
        loginBtn.setAlignment(Pos.CENTER);
        loginRoot.add(loginBtn,0,2);
        Button clearBtn = new Button("Clear");
        clearBtn.setAlignment(Pos.CENTER);
        loginRoot.add(clearBtn,1,2);

        loginStage.setScene(new Scene(loginRoot,200,200));
        loginStage.show();

        //Add menu variables
        Menu mainMenu = new Menu("Menu");
        Menu settingMenu = new Menu("Settings");
        Menu otherMenu = new Menu("Others");
            //Adding menu items into the main menu
            Menu fileMenu = new Menu("File");
            Menu LanMenu = new Menu("Langauges");
                MenuItem JavaMenu = new MenuItem("Java");
                MenuItem LabVIEWMenu = new MenuItem("LabVIEW");
                MenuItem cMenu = new MenuItem("c#/.net");
                MenuItem PythonMenu = new MenuItem("Python");
                LanMenu.getItems().addAll(JavaMenu,LabVIEWMenu,cMenu,PythonMenu);
            fileMenu.getItems().add(LanMenu);
            MenuItem saveMenu = new MenuItem("Save");
            MenuItem saveAsMenu = new MenuItem("Save As");
            MenuItem exitMenu = new MenuItem("Exit");
            mainMenu.getItems().addAll(fileMenu,saveAsMenu,saveMenu,exitMenu);
        MenuBar myMenuBar = new MenuBar(mainMenu,settingMenu,otherMenu);

        //adding the main root vertical box to insert menu and main nodes
        VBox root = new VBox();
        root.getChildren().add(myMenuBar);
        root.setSpacing(20);

        //adding the horizontal box to the main root (for buttons, textfield and table list)
        HBox horizontalBox = new HBox();
        root.getChildren().add(horizontalBox);
        horizontalBox.setSpacing(30);
        //Adding in a vertical box for the left side buttons
        VBox LeftBox = new VBox();
        horizontalBox.getChildren().add(LeftBox);
        LeftBox.setPadding(new Insets(10,10,10,10));

        //adding buttons in to vertical box
        LeftBox.setSpacing(50);
        Button button1 = new Button("Support");
        Button button2 = new Button("Operation");
        Button button3 = new Button("Sales");
        LeftBox.getChildren().addAll(button1,button2,button3);

        //adding text display into the middle horizontalbox
        TextArea textArea = new TextArea("The Text Area");
        //textArea.setDisable(true);
        textArea.setPrefHeight(200);
        textArea.setPrefWidth(200);
        horizontalBox.getChildren().add(textArea);


        //creating observable list

        //adding table view to the right in the vertical Box
        TableView<Contacts> tvContact;
        tvContact= new TableView<Contacts>();
        tvContact.setPrefHeight(200);
        tvContact.setPrefWidth(500);

        //adding columns to the table
        TableColumn<Contacts, String> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tvContact.getColumns().add(column1);

        TableColumn<Contacts, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tvContact.getColumns().add(column2);

        TableColumn<Contacts, String> column3 = new TableColumn<>("Contact Number");
        column3.setCellValueFactory(new PropertyValueFactory<>("HPno"));
        tvContact.getColumns().add(column3);

        horizontalBox.getChildren().add(tvContact);

        //Clear and add other list to the table
//        tvContact.getItems().clear();
//        tvContact.getItems().addAll(List1);

        Scene scene = new Scene(root,900,300);
        primaryStage.setScene(scene);
        //primaryStage.show();


        //all my events
        button1.addEventHandler(ActionEvent.ACTION,(ActionEvent)->
        {
            tvContact.getItems().clear();
            tvContact.getItems().addAll(supportList);
            textArea.setText("You have selected the Supports \ndepartment" +
                    " please select \nindividual member" +
                    " to get their \ndetailed contact information");

        });
        button2.addEventHandler(ActionEvent.ACTION,(ActionEvent)->
        {
            tvContact.getItems().clear();
            tvContact.getItems().addAll(oppList);
            textArea.setText("You have selected the Operations \ndepartment" +
                    " please select \nindividual member" +
                    " to get their \ndetailed contact information");
        });
        button3.addEventHandler(ActionEvent.ACTION,(ActionEvent)->
        {
            tvContact.getItems().clear();
            tvContact.getItems().addAll(saleList);
            textArea.setText("You have selected the Sales \ndepartment" +
                    " please select \nindividual member" +
                    " to get their \ndetailed contact information");
        });

        //event to populate the textArea when each cell is selected:
        tvContact.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(tvContact.getSelectionModel().getSelectedItem() != null)
                {
                    TableView.TableViewSelectionModel selectionModel = tvContact.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    //String item = tvContact.getItems().get(tablePosition.getRow()).getFirstName();
                    int item = tablePosition.getRow();
                    //String test = tvContact.get
                    System.out.println("Selected Value " + item);
                    //System.out.println(supportList.get(0).toString());
                    textArea.setText(tvContact.getItems().get(item).toString());
                }
            }
        });

        //event to close the program
        exitMenu.addEventHandler(ActionEvent.ACTION,(ActionEvent)->
        {
            primaryStage.close();
            //Platform.exit();
        });

        //login btn pressed event to open up the primarystage
        loginBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent)->
        {
            if(loginTextField.getText().compareTo("admin") ==0)
            {
                if(passTextField.getText().compareTo("admin")==0)
                {
                    loginStage.close();
                    primaryStage.show();
                }
            }
            else
            {
                Stage errorStage = new Stage();
                VBox errorRoot = new VBox();
                Text loginErrorText = new Text("Error! Invalid Login");
                errorRoot.getChildren().add(loginErrorText);
                errorStage.setScene(new Scene(errorRoot,100,100));
                errorStage.show();
                loginTextField.clear();
                passTextField.clear();
            }

        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
