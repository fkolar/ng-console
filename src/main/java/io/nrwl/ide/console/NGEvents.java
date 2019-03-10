package io.nrwl.ide.console;

import com.intellij.util.messages.Topic;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface NGEvents {
    Topic<NGEvents> NG_CONSOLE = Topic.create("NG Console", NGEvents.class);

    void onEvent(Type type, @NotNull Map<String, String> properties);

    enum Type {
        OnInit,
        ProjectOpened
    }
}
