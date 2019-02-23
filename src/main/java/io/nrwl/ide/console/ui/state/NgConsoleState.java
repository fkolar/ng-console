package io.nrwl.ide.console.ui.state;


import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import io.nrwl.ide.console.NGEvents;

import java.util.Map;

/**
 * Shared state between components
 */
public class NgConsoleState {
    private Project project;
    private VirtualFile currentProjectRoot;

    public NgConsoleState() {
    }


    public void setProject(Project project) {
        this.project = project;
    }


    public void sendEvent(NGEvents.Type type, Map<String, String> properties) {
        NGEvents ngEvents = project.getMessageBus().syncPublisher(NGEvents.NG_CONSOLE);
        ngEvents.onEvent(type, properties);
    }

    public VirtualFile getCurrentProjectRoot() {
        return currentProjectRoot;
    }

    public void setCurrentProjectRoot(VirtualFile currentProjectRoot) {
        this.currentProjectRoot = currentProjectRoot;
    }
}
