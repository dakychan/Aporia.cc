package aporia.cc.common.trait;

import aporia.cc.api.feature.module.setting.Setting;

public interface Setupable {
    void setup(Setting... settings);
}