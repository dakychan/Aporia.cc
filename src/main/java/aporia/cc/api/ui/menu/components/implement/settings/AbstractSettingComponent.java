package aporia.cc.api.ui.menu.components.implement.settings;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import aporia.cc.module.api.setting.Setting;
import aporia.cc.api.ui.menu.components.AbstractComponent;

@Getter
@RequiredArgsConstructor
public abstract class AbstractSettingComponent extends AbstractComponent {
    private final Setting setting;
}
