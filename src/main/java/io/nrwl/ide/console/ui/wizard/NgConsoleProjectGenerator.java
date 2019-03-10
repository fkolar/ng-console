package io.nrwl.ide.console.ui.wizard;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import icons.NgIcons;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


/**
 * Main entry point to the NgConsole Wizard project generation process where it creates a custom builder to setup
 * custom environment, provides necessary Title which in the wizard menu and icon
 */
@SuppressWarnings("deprecation")
public class NgConsoleProjectGenerator extends WebProjectTemplate<Object> {

    @Nls
    @NotNull
    @Override
    public String getName() {
        return "Angular Console";
    }

    @Override
    public Icon getIcon() {
        return NgIcons.NEW_PROJECT;
    }


    @Override
    public String getDescription() {
        return "Create Angular Project using nrwl.io Angular Console";
    }


    @NotNull
    @Override
    public ModuleBuilder createModuleBuilder() {
        return new NgConsoleCreateProjectModuleBuilder(this);
    }

    @Override
    public void generateProject(@NotNull Project project, @NotNull VirtualFile baseDir,
                                @NotNull Object settings, @NotNull Module module) {

    }
}
