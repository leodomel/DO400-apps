package com.redhat.training.home.automation;

import com.redhat.training.home.automation.lights.LightSystem;
import com.redhat.training.home.automation.rules.Rule;
import com.redhat.training.home.automation.rules.RulesRepository;

public class HomeAutomation {

    LightSystem lightSystem;
    RulesRepository rulesRepository;

    public HomeAutomation(LightSystem lightSystem, RulesRepository rulesRepository) {
        this.lightSystem = lightSystem;
        this.rulesRepository = rulesRepository;
    }

    public void processConditions(RoomConditions conditions) {
        Iterable<Rule> rules = rulesRepository.getAll();
        
        for(Rule rule : rules){
            if(!rule.meets(conditions)){
                this.lightSystem.switchOff();
                return;
            }
        }
        this.lightSystem.switchOn();        
    }
}
