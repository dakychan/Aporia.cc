package aporia.cc.api.base.common.trait;

import aporia.cc.module.api.setting.Setting;

public interface Setupable {
    void setup(Setting... settings);
}