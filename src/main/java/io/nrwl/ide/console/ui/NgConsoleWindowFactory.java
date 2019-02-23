package io.nrwl.ide.console.ui;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Window Factory for instantiating ToolWindow JPanel. This tool windows appears on the right
 * side of the IDE and should hold a web view
 */
public class NgConsoleWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        NgConsoleUI ngConsoleWindow = new NgConsoleUI();

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content console = contentFactory.createContent(ngConsoleWindow.getContent(), "", false);
        toolWindow.getContentManager().addContent(console);
    }
}
