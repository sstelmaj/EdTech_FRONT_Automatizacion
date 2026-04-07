package org.sstelmaj.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class AlertMessage implements Question<String> {

    private final Target alertTarget;

    public AlertMessage(Target alertTarget) {
        this.alertTarget = alertTarget;
    }

    @Override
    public String answeredBy(Actor actor) {
        return alertTarget.resolveFor(actor).getText().trim();
    }

    public static AlertMessage from(Target alertTarget) {
        return new AlertMessage(alertTarget);
    }
}
