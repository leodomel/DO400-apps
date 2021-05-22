package com.redhat.training.home.automation.lights;

public class ConsoleLightSystem implements LightSystem{
    private boolean on = false;

    public void switchOn(){
        System.out.println("Lights ON");
        this.on = true;
    }

    public void switchOff(){
        System.out.println("Lights OFF");
        this.on = false;
    }

    public boolean isOn(){ return this.on; }
}
