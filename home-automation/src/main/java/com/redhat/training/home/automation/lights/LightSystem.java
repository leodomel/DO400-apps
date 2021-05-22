package com.redhat.training.home.automation.lights;

public interface LightSystem {
    public void switchOff();
    public void switchOn();
    public boolean isOn();
}
