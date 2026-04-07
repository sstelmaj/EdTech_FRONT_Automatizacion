package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.ui.RegisterPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegisterUser implements Task {

    private final String username;
    private final String password;

    public RegisterUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(RegisterPage.USERNAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(username).into(RegisterPage.USERNAME_FIELD),
                Enter.theValue(password).into(RegisterPage.PASSWORD_FIELD),
                Enter.theValue(password).into(RegisterPage.CONFIRM_PASSWORD_FIELD),
                Click.on(RegisterPage.SUBMIT_BUTTON)
        );
    }

    public static RegisterUser withCredentials(String username, String password) {
        return Tasks.instrumented(RegisterUser.class, username, password);
    }
}
