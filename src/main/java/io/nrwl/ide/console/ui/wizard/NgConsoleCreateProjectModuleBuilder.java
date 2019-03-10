package io.nrwl.ide.console.ui.wizard;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.WebModuleBuilder;
import com.intellij.openapi.module.WebModuleType;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.platform.ProjectGeneratorPeer;
import icons.NgIcons;
import io.nrwl.ide.console.ui.NgConsoleUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


/**
 * Creates new NGConsole module environment
 */
public class NgConsoleCreateProjectModuleBuilder extends WebModuleBuilder<Object> {

    @NonNls
    private static final Logger LOG = Logger.getInstance(NgConsoleCreateProjectModuleBuilder.class);
    private NgConsoleCreateProjectStep myCreateStep;


    public NgConsoleCreateProjectModuleBuilder(@NotNull WebProjectTemplate<Object> template) {
        super(template);
    }


    @Override
    public ModuleWizardStep[] createFinishingSteps(@NotNull WizardContext wizardContext,
                                                   @NotNull ModulesProvider modulesProvider) {
        myCreateStep = new NgConsoleCreateProjectStep(wizardContext.getWizard(), this);

        LOG.info("Creating new finishing step: " + myCreateStep);
        return new ModuleWizardStep[]{myCreateStep};
    }


    /**
     * Since we are hiding in the final step the Finish button we also need to make sure that it is visible again
     * when we press the previous button.
     */
    @Nullable
    @Override
    public ModuleWizardStep modifySettingsStep(@NotNull SettingsStep settingsStep) {
        ProjectGeneratorPeer<Object> peer = myGeneratorPeerLazyValue.getValue();
        peer.buildUI(settingsStep);

        WizardContext context = settingsStep.getContext();
        context.getWizard().updateButtons(false, true, true);

        LOG.info("modifySettingsStep step: " + myCreateStep);

        // make sure its finish button is visible again
        JButton nextButton = NgConsoleUtil.getNextButton(context.getWizard());
        if (nextButton != null) {
            nextButton.setVisible(true);
        }

        return new ModuleWizardStep() {
            @Override
            public JComponent getComponent() {
                return null;
            }

            @Override
            public void updateDataModel() {
                String projectPath = context.getProjectFileDirectory();
                String moduleRoot = settingsStep.getModuleNameLocationSettings().getModuleContentRoot();
            }
        };
    }


    @Override
    public String getDescription() {
        return "Angular Console Module Builder";
    }


    @Nullable
    @Override
    public String getBuilderId() {
        return "Angular Console";
    }


    @Override
    public Icon getNodeIcon() {
        return NgIcons.ACTION_GENERATE;
    }

    @Override
    public ModuleType getModuleType() {
        return WebModuleType.getInstance();
    }


    @Override
    public String getParentGroup() {
        return WebModuleBuilder.GROUP_NAME;
    }
}
