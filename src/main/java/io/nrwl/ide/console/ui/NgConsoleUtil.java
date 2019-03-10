package io.nrwl.ide.console.ui;

import com.intellij.ide.wizard.AbstractWizard;

import javax.swing.*;
import java.lang.reflect.Method;

public class NgConsoleUtil {

    public static JButton getNextButton(AbstractWizard wizard) {
        JButton button = null;

        try {
            Method getNextButton = AbstractWizard.class.getDeclaredMethod("getNextButton");
            if (getNextButton != null) {
                getNextButton.setAccessible(true);
                button = (JButton) getNextButton.invoke(wizard);
            }
        } catch (Exception e) {
        }
        return button;
    }
}
