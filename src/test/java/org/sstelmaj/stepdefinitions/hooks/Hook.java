package org.sstelmaj.stepdefinitions.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class Hook {

    @Before
    public void startStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
