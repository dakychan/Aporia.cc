package aporia.cc.implement.screens.menu.components.implement.settings;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import aporia.cc.api.feature.module.setting.Setting;
import aporia.cc.implement.screens.menu.components.AbstractComponent;

@Getter
@RequiredArgsConstructor
public abstract class AbstractSettingComponent extends AbstractComponent {
    private final Setting setting;
}
