/**
 *
 */
package notekeeper;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * @author hadin
 *
 */
public class MainLayoutController implements Initializable {

    @FXML
    private Button addNewNoteBtn;
    @FXML
    private ListView<?> listView;
    @FXML
    private ToggleButton ltrBtn;
    @FXML
    private ToggleButton rtlBtn;
    @FXML
    private TextArea noteArea;
    @FXML
    private ComboBox<Integer> sizeCombo;
    @FXML
    private ComboBox<String> fontCombo;
    @FXML
    private TextField titleTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private AnchorPane notePane;
    @FXML
    private Button deleteBtn;
    private String[] fontURLS = {"file:src/fonts/calibri.ttf", "file:src/fonts/trado.TTF", "file:src/fonts/Bahij Nassim-Regular.ttf",
         "file:src/fonts/Sahel.ttf", "file:src/fonts/VeggiMed.otf"};
    private ObservableList<String> fontNames = FXCollections.observableArrayList("Calibri", "Trado", "Bahij Nassim", "Sahel", "Veggi");
    private ObservableList<Integer> fontSize = FXCollections.observableArrayList(6, 8, 10, 11, 12, 14, 16, 18, 22, 26, 30);
    private ToggleGroup noteToolbarGroup;
    private ToggleGroup listToolbarGroup;

    // add new note, also add new entry in the list
    @FXML
    void addNewNoteBtnAction(ActionEvent event) {

    }
    
    @FXML
    void deleteBtnAction(ActionEvent event) {

    }

    // left to right toggle button action
    @FXML
    void ltrBtnAction(ActionEvent event) {
        noteArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        noteArea.setPromptText("Write Something");
    }

    // right to left toggle button action
    @FXML
    void rtlBtnAction(ActionEvent event) {
        noteArea.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        noteArea.setPromptText("اینجا یادداشت کنید");
    }

    /**
     * creates the buttons and toggle groups and sets icons to the buttons
     */
    private void createToggleGroup() {
        // create 2 toggle groups for both toolbars
        noteToolbarGroup = new ToggleGroup();
        listToolbarGroup = new ToggleGroup();

        // lrt and rtl toggle buttons
        ltrBtn.setToggleGroup(noteToolbarGroup);
        ltrBtn.setSelected(true);
        rtlBtn.setToggleGroup(noteToolbarGroup);

        // add icon to the buttons
        addNewNoteBtn.setGraphic(new ImageView(new Image("/icons/plus.png")));
        rtlBtn.setGraphic(new ImageView(new Image("/icons/rtl.png")));
        ltrBtn.setGraphic(new ImageView(new Image("/icons/ltr.png")));
    }

    /**
     * Save the document dynamically when user changes or writes anything.
     */
    private void saveDynamically() {
        noteArea.setOnKeyTyped(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println("Saved");
            }

        });
    }

    /**
     * Initialize Fonts and Combo boxes for font families and font sizes
     */
    private void initializeFonts() {
        noteArea.setFont(Font.loadFont("file:src/fonts/calibri.ttf", 13));
        fontCombo.setItems(fontNames);
        fontCombo.getSelectionModel().selectFirst();
        fontCombo.setOnAction((event) -> {
            int pathIndex = fontCombo.getSelectionModel().getSelectedIndex();
            noteArea.setFont(Font.loadFont(fontURLS[pathIndex], sizeCombo.getSelectionModel().getSelectedItem()));
        });

        sizeCombo.setItems(fontSize);
        sizeCombo.getSelectionModel().select(4);
        sizeCombo.setOnAction((ActionEvent event) -> {
            noteArea.setFont(Font.loadFont(fontURLS[fontCombo.getSelectionModel().getSelectedIndex()], sizeCombo.getSelectionModel().getSelectedItem()));
        });
    }

    // main method for running the controller class
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createToggleGroup(); // initialize buttons icons, button groups for toggle buttons
        saveDynamically();
        initializeFonts();
        listView.getSelectionModel().selectedItemProperty().addListener(listener -> {
            if (listView.getSelectionModel().isEmpty()) {
                System.err.println("list is empty");
            }
        });
        if (listView.getSelectionModel().isEmpty()) {
            notePane.setDisable(true);
        }
    }

}
