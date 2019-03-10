package io.nrwl.ide.console.ui.wizard;

import com.intellij.ide.util.projectWizard.AbstractNewProjectStep.AbstractCallback;
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.platform.DirectoryProjectGenerator;
import io.nrwl.ide.console.ui.NgConsoleUI;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NgConsoleSettingsStep extends ProjectSettingsStepBase<Object> {

    private JPanel myMainPanel;


    public NgConsoleSettingsStep(DirectoryProjectGenerator<Object> projectGenerator, AbstractCallback callback) {
        super(projectGenerator, callback);
        myMainPanel = new JPanel(new BorderLayout());
    }


    @Override
    public JPanel createPanel() {
        JPanel panel = super.createPanel();
        myCreateButton.setVisible(false);

        JButton finishButton = new JButton("CLICK ME TO FINISH");
        NgConsoleUI consoleUI = ServiceManager.getService(NgConsoleUI.class);


        finishButton.addActionListener(e -> {
            myCreateButton.doClick();
//            myWizard.close(DialogWrapper.OK_EXIT_CODE);
        });

        myMainPanel.add(finishButton, BorderLayout.NORTH);
        myMainPanel.add(consoleUI.getContent(), BorderLayout.CENTER);
        myMainPanel.setBorder(null);
        return myMainPanel;
    }


    @Override
    public boolean checkValid() {
        String text = myLocationField.getTextField().getText().trim();
        if (Files.exists(Paths.get(text))) {
            setErrorText("NgConsole: Path already exists");
            return false;

        }
        return super.checkValid();
    }


}
