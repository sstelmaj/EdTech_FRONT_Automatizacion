package org.sstelmaj.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class Visibility implements Question<Boolean> {

    private final Target target;

    public Visibility(Target target) {
        this.target = target;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return target.resolveFor(actor).isDisplayed();
    }

    public static Visibility of(Target target) {
        return new Visibility(target);
    }
}
