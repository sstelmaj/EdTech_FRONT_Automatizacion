package org.sstelmaj.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.annotations.Step;

public class WaitForMilliseconds implements Interaction {

    private final long milliseconds;

    public WaitForMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    @Override
    @Step("{0} espera #milliseconds milisegundos para que la UI se actualice")
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static WaitForMilliseconds of(long milliseconds) {
        return Tasks.instrumented(WaitForMilliseconds.class, milliseconds);
    }
}
