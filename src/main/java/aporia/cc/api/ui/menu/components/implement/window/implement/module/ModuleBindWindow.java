package aporia.cc.api.ui.menu.components.implement.window.implement.module;

import lombok.RequiredArgsConstructor;
import aporia.cc.module.api.Module;
import aporia.cc.api.ui.menu.components.implement.window.implement.AbstractBindWindow;

@RequiredArgsConstructor
public class ModuleBindWindow extends AbstractBindWindow {
    private final Module module;

    @Override
    protected int getKey() {
        return module.getKey();
    }

    @Override
    protected void setKey(int key) {
        module.setKey(key);
    }

    @Override
    protected int getType() {
        return module.getType();
    }

    @Override
    protected void setType(int type) {
        module.setType(type);
    }
}