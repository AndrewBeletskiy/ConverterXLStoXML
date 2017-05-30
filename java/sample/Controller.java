package sample;
import com.sun.istack.internal.NotNull;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

import sample.Logger;
import sample.model.Declar;


public class Controller {
    @FXML
    private Button btnChooseFile;
    @FXML
    private Label lblOpenedFile;
    @FXML
    private Button btnConvertToXML;
    @FXML
    private ChoiceBox choiceStandart;

    private File xlsFile;
    private Stage stage;
    private Logger log;
    private Declar declar;

    @FXML
    private void initialize() {
        //if (log == null)
        //    log = new Logger(logName);
        choiceStandart.getItems().setAll("J1201008");
        choiceStandart.getSelectionModel().select(0);

    }

    public void setXlsFile(File xlsFile) {
        this.xlsFile = xlsFile;
        lblOpenedFile.setText("Выбрано файл: " + xlsFile.getName());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void chooseFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл XLS");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Файлы налоговой накладной(*.xls)","*.xls"));
        if (xlsFile!=null)
            fileChooser.setInitialDirectory(xlsFile.getParentFile());
        else
            fileChooser.setInitialDirectory(getCurrentDirectory());
        //List<File> files= fileChooser.showOpenMultipleDialog(stage);
        File chosenFile = fileChooser.showOpenDialog(stage);

        if (chosenFile != null && isXLSFile(chosenFile))
        {
            setXlsFile(chosenFile);
        } else if (chosenFile != null){
            showError("Неверное расширение файла.", "Вы выбрали файл с расширением ."
                    + getFileExtension(chosenFile)
                    + ". Необходимо выбирать файлы с расширением \".xls\"");
        }
        //initialize(chosenFile.getName() + "_log.txt");
    }
    private void showMessage(String title, String content) {
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle(title);
        message.setContentText(content);
        message.setHeaderText(null);
        message.showAndWait();
    }
    public void showMessage(String content) {
        showMessage("Information", content);
    }
    public boolean showMessageYesNo(String title, String content) {
        ButtonType btnYes = new ButtonType("Да");
        ButtonType btnNo = new ButtonType("Нет");
        Alert message = new Alert(Alert.AlertType.CONFIRMATION);
        message.getButtonTypes().setAll(btnYes, btnNo);
        message.setTitle(title);
        message.setContentText(content);
        message.setHeaderText(null);

        Optional<ButtonType> res = message.showAndWait();
        if (res.get() == btnYes) {
            return true;
        }
        return false;
    }
    public boolean showMessageYesNo(String content) {
        return showMessageYesNo("Вы уверенны?", content);
    }
    public void showError(String title, String content) {
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle(title);
        message.setContentText(content);
        message.setHeaderText(null);
        message.showAndWait();
    }
    public void showError(String content) {
        showError("ERROR!", content);
    }
    public File getCurrentDirectory() {
        return new File("/.");
    }
    private boolean isXLSFile(File file) {
        String extension = getFileExtension(file);
        return extension.equalsIgnoreCase("xls");
    }

    @NotNull
    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
    public void log(String logText) {
        log.write(logText);
    }
    @FXML
    public void convertToXmlButtonClick() {
        if (xlsFile == null) {
            showMessage("Вы не выбрали файл");
        }
        try {
            XlsReader reader = new XlsReader(xlsFile, this);
            declar = new Declar();
            DeclarReader declarReader = new DeclarReader(declar, reader, this);
            declarReader.read();
            if (declarReader.Errors.size() > 0){
                StringBuilder str = new StringBuilder();

                for (int i = 0; i < declarReader.Errors.size(); i++) {
                    str.append(declarReader.Errors.get(i).getMessage())
                       .append("\n");
                }
                showError("Ошибка: неверно заполнен файл \n" + str.toString());
                return;
            }
            renderToXML();
        } catch (IOException ex) {
            showError("Файл не доступен для чтения. Возможно он используется в другой программе.");
        }
    }
    public String getInFileName() {
        return xlsFile.getName().replaceFirst("[.][^.]+$", "");
    }
    public void renderToXML() {
        String text = declar.toXML();
        File xmlFile = new File(xlsFile.getPath().replaceFirst("[.][^.]+$", ".xml"));
        boolean isToConvert = true;
        if (xmlFile.exists())
        {
            isToConvert = showMessageYesNo("Этот файл уже был конвертирован. Продолжить?");
        }
        if (isToConvert) {
            try {
                xmlFile.createNewFile();
                //BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFile));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(xmlFile.getAbsolutePath()), "UTF8"));
                writer.write(text);
                writer.close();
                showMessage("Конвертация завершенна удачно.");
            } catch (Exception ex) {
                showMessage("Невозможно создать файл xml. Ошибка: " + ex.getMessage());
            }
        }

    }

}
