package io.nrwl.ide.console.ui;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.uiDesigner.core.GridLayoutManager;
import io.nrwl.ide.console.NGEvents;
import io.nrwl.ide.console.ui.state.NgConsoleState;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;

/**
 * Main NgConsole Panel used to render the main UI. It uses JavaFX which has a FX WebView to work with  HTML content.
 * <p>
 * TODO: Maybe try to pre-instantiate this Swing Panel in the application component and have it ready and use
 * the same instantiated component on all place as I see some loading time
 */
public class NgConsoleUI {
    /**
     * JavaFX object handling web content management such as loading and understanding HTML content. It can apply
     * styles, scripts, etcs.
     */
    private WebEngine webEngine;

    /**
     * JavaFX Panel bridging Java and FX world.
     */
    private JFXPanel webPanel;


    public NgConsoleUI() {
        doInit();
    }

    public JComponent getContent() {
        return webPanel;
    }


    /**
     * JavaFX is tricky in the way that it needs to have these several calls to work properly as we are embedding it
     * inside Swing components.
     * <p>
     * - First we need to make sure that JavaFX runtime will not accidentally shutdown
     * (https://bugs.openjdk.java.net/browse/JDK-8090517) when we release component that is using it and second
     * <p>
     * - Platform.runLater is required to set the run context within JavaFX application thread
     * <p>
     * In case we want to change the layout and embed some additional components such status bar this is the right
     * place to change the GridLayoutManager
     */
    private void doInit() {
        webPanel = new JFXPanel();
        webPanel.setLayout(new GridLayoutManager(1, 1));
        Platform.setImplicitExit(false);
        Platform.runLater(() -> buildWebView());
    }


    private void buildWebView() {
        WebView webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        Scene scene = new Scene(webView);
        webPanel.setScene(scene);

//        webEngine.load("https://github.com/ngx-meta/rules/");
        webEngine.load("http://www.google.com");

    }


    private void loadURL(final String url) {
        ApplicationManager.getApplication().invokeLater(() -> webEngine.load(url));
    }
}
