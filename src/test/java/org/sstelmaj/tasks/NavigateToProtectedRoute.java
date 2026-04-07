package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.ui.LoginPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateToProtectedRoute implements Task {

    private final String route;

    public NavigateToProtectedRoute(String route) {
        this.route = route;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(route)
        );
    }

    public static NavigateToProtectedRoute at(String route) {
        return Tasks.instrumented(NavigateToProtectedRoute.class, route);
    }
}
