package org.sstelmaj.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class TextOf implements Question<String> {

    private final Target target;

    public TextOf(Target target) {
        this.target = target;
    }

    @Override
    public String answeredBy(Actor actor) {
        return target.resolveFor(actor).getText().trim();
    }

    public static TextOf the(Target target) {
        return new TextOf(target);
    }
}
