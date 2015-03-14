import javafx.application.Application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MapViewer extends Application {
    


    public MapViewer(String htmppath) {
        
    }
  
  @Override public void start(Stage stage) {
    WebView webview = new WebView();
    webview.getEngine().load(
      MapViewer.class.getResource("network.html").toExternalForm()            
    );
    stage.setScene(new Scene(webview));
    stage.show();
  }
}