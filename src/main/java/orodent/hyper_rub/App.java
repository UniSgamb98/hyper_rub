package orodent.hyper_rub;

import javafx.application.Application;
import javafx.stage.Stage;
import org.controlsfx.dialog.LoginDialog;
import orodent.hyper_rub.framework.scenes.Scenes;
import orodent.hyper_rub.utils.GlobalContext;

public class App extends Application {
    @Override
    public void start(Stage stage) {
       login();
    }

    public void login(){
        LoginDialog loginDialog = new LoginDialog(null, null);
        loginDialog.getDialogPane().setHeaderText("");
        loginDialog.showAndWait().ifPresent(result -> {
            if (GlobalContext.dbManager.autenticate(result)) {
                GlobalContext.framework.preload(Scenes.First_page);
                GlobalContext.framework.changeScene(Scenes.First_page);
                GlobalContext.framework.show();
            }
            else login();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}