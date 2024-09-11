package orodent.hyper_rub.framework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import orodent.hyper_rub.App;
import orodent.hyper_rub.framework.scenes.Scenes;
import orodent.hyper_rub.utils.DBManager;

import java.io.IOException;
import java.util.ArrayList;

public class Framework {
    private Stage stage;
    private Scene scene;
    private ArrayList<Pair<Scenes,Parent>> sceneRoots;
    private DBManager dbManager;

    public Framework(DBManager dbManager) {
        stage = new Stage();
        scene = new Scene(new Group(),320,240);
        sceneRoots = new ArrayList<>();
        this.dbManager = dbManager;
    }

    /**
     * Cambia la scena visualizzata a schermo con quella specificata da <code>root</code> se presente nella lista di scene precaricate.
     * @param root il titolo della scena precaricata da mostrare.
     */
    public void changeScene(Scenes root) {
        for (Pair<Scenes,Parent> sceneRoot : sceneRoots) {
            if (sceneRoot.getKey().equals(root)) {
                scene.setRoot(sceneRoot.getValue());
            }
        }
    }

    /**
     * Precarica in memoria una scena
     * @param scene lista completa in <code>framework/scenes/Scenes</code>
     */
    public void preload(Scenes scene) {
        Parent root = null;
        try {
            switch (scene) {
                case First_page -> {
                    root = FXMLLoader.load(App.class.getResource("firstPage.fxml"));
                }
            }
            sceneRoots.add(new Pair<>(scene, root));
        } catch (IOException e) {
            e.printStackTrace(); //TODO logging da implementare
        }
    }

    /**
     * Scarica dalla memoria un scena precaricata in precedenza
     * @param scene lista completa in <code>framework/scenes/Scenes</code>
     */
    public void unload(Scenes scene) {
        sceneRoots.removeIf(sceneRoot -> sceneRoot.getKey().equals(scene));
    }

    public void show(){
        stage.setScene(scene);
        stage.show();
    }
}
