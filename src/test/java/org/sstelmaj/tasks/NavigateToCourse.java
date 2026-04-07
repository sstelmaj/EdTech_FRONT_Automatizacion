package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.ui.DashboardPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateToCourse implements Task {

    private final String courseName;

    public NavigateToCourse(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DashboardPage.courseCard(courseName), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(DashboardPage.courseCard(courseName))
        );
    }

    public static NavigateToCourse withName(String courseName) {
        return Tasks.instrumented(NavigateToCourse.class, courseName);
    }
}
