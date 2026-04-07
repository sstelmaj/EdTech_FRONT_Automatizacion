package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.ui.DashboardPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Logout implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DashboardPage.LOGOUT_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(DashboardPage.LOGOUT_BUTTON)
        );
    }

    public static Logout fromDashboard() {
        return Tasks.instrumented(Logout.class);
    }
}
