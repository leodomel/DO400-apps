package com.redhat.training.home.automation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.redhat.training.home.automation.lights.ConsoleLightSystem;
import com.redhat.training.home.automation.lights.LightSystem;
import com.redhat.training.home.automation.rules.InMemoryRulesRepository;
import com.redhat.training.home.automation.rules.RulesRepository;

import org.junit.jupiter.api.BeforeEach;

public class HomeAutomationTest {
    
    LightSystem lightSystem;
    HomeAutomation home;
    RulesRepository rulesRepository;

    @BeforeEach
    public void setUp(){
        this.lightSystem = new ConsoleLightSystem();
        this.rulesRepository = new InMemoryRulesRepository();
        this.home = new HomeAutomation(this.lightSystem, this.rulesRepository);
    }

    @Test
    public void switchOffLightUnderEnoughDayLight(){

        this.home.processConditions(new RoomConditions(0.20, true));
        assertFalse(this.lightSystem.isOn());
    }

    @Test
    public void switchOnLightsUnderLowDayLight(){

        this.home.processConditions(new RoomConditions(0.19, true));
        assertTrue(this.lightSystem.isOn());
    }

    @Test
    public void transitionToLightsOnUnderEnoughDaylight(){
        this.lightSystem.switchOn();
        this.home.processConditions(new RoomConditions(0.3, true));
        assertFalse(this.lightSystem.isOn());
    }

    @Test
    public void switchOffLightsAtNightIfNoPresenceDetected(){
        home.processConditions(new RoomConditions(0.1, false));
        assertFalse(lightSystem.isOn());
    }

}
