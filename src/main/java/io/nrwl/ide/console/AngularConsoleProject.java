package io.nrwl.ide.console;

import com.intellij.notification.Notification;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import static com.intellij.notification.NotificationType.INFORMATION;
import static io.nrwl.ide.console.ui.NgIcons.TOOL_WINDOW;

public class AngularConsoleProject implements ProjectComponent {
    private Project project;


    public AngularConsoleProject(@NotNull Project project) {
        this.project = project;
    }


    /**
     * Angular Project has always one root especially for the use of Angular Console
     */
    @Override
    public void projectOpened() {
        VirtualFile[] contentRoots = ProjectRootManager.getInstance(project).getContentRoots();

        Notification notification = new Notification("AngularConsole", TOOL_WINDOW, INFORMATION);
        notification.setTitle("Angular Console");
        notification.setContent("Project is Open " + contentRoots[0].getCanonicalPath());
        Notifications.Bus.notify(notification);
    }


    @Override
    public void projectClosed() {
        System.out.println("projectClosed called = " + project);
    }


    @NotNull
    @Override
    public String getComponentName() {
        return AngularConsoleProject.class.getSimpleName();
    }

}
