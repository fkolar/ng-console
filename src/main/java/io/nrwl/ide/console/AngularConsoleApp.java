package io.nrwl.ide.console;

import com.intellij.notification.Notification;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import static com.intellij.notification.NotificationType.INFORMATION;
import static io.nrwl.ide.console.ui.NgIcons.TOOL_WINDOW;

public class AngularConsoleApp implements ApplicationComponent {


    /**
     * 1. Create Runnable object or Lambda . Inside RUN startup background process
     * 2. Use ApplicationManager to execute bg task
     * 3. Check if we still need to use runReadAction or InvokeLater.
     *
     *
     * Something like:
     *
     * ApplicationManager.getApplication().executeOnPooledThread(() ->
     *         {
     *             ApplicationManager.getApplication().runReadAction(() -> {
     *
     *             });
     *         }
     *
     *  Investigate the com.intellij.execution.lineMarker.ExecutorAction to run actual actions. There is some plugin
     *  that uses it:
     *
     *  https://github.com/syuchan1005/NPMScriptRunner/blob/master/src/com/github/syuchan1005/npmscriptrunner/NpmRunLineMarkerContributor.java
     *  NpmPackageProjectGenerator - check for running node command NodeCommandLineUtil.createCommandLine
     *
     */
    @Override
    public void initComponent() {

        // Send Notification to the event log that Server is started (just for the test now)
        Notification notification = new Notification("AngularConsole", TOOL_WINDOW, INFORMATION);
        notification.setTitle("Angular Console");
        notification.setContent("Angular Console has started");
        Notifications.Bus.notify(notification);


        // Start Node JS server in background thread
    }

    @Override
    public void disposeComponent() {
        System.out.println("Releasing and shutting down everything..s = " + true);
    }

    @NotNull
    @Override
    public String getComponentName() {
        return AngularConsoleApp.class.getSimpleName();
    }
}
