package org.sstelmaj.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.hooks.OpenBrowser;
import org.sstelmaj.tasks.RegisterUser;
import org.sstelmaj.tasks.LoginUser;
import org.sstelmaj.tasks.Logout;
import org.sstelmaj.tasks.NavigateToProtectedRoute;
import org.sstelmaj.questions.CurrentUrl;
import org.sstelmaj.questions.DashboardUsername;
import org.sstelmaj.ui.DashboardPage;
import org.sstelmaj.ui.LoginPage;
import org.sstelmaj.interactions.WaitForMilliseconds;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class AuthenticationStepDefinitions {

    private static final String RUN_SUFFIX = "_" + (System.currentTimeMillis() % 1000000);

    @Dado("que el docente se registra con usuario {string} y contrasena {string}")
    public void elDocenteSeRegistraConUsuarioYContrasena(String username, String password) {
        String uniqueUsername = username + RUN_SUFFIX;
        OnStage.theActorCalled("docente").remember("username", uniqueUsername);
        OnStage.theActorCalled("docente").remember("password", password);
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenBrowser.withUrl("http://localhost:5173/register"),
                RegisterUser.withCredentials(uniqueUsername, password)
        );
    }

    @Dado("es redirigido al dashboard")
    public void esRedirigidoAlDashboard() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(DashboardPage.COURSES_TITLE, isVisible()).forNoMoreThan(15).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(CurrentUrl.value(), containsString("/dashboard"))
        );
    }

    @Dado("el dashboard muestra el nombre de usuario {string}")
    public void elDashboardMuestraElNombreDeUsuario(String username) {
        String actualUsername = OnStage.theActorInTheSpotlight().recall("username");
        OnStage.theActorInTheSpotlight().should(
                seeThat(DashboardUsername.isDisplayedAs(actualUsername), is(true))
        );
    }

    @Cuando("el docente cierra sesion")
    public void elDocenteCierraSesion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Logout.fromDashboard()
        );
    }

    @Entonces("es redirigido a la pagina de login")
    public void esRedirigidoALaPaginaDeLogin() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(LoginPage.USERNAME_FIELD, isVisible()).forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(CurrentUrl.value(), containsString("/login"))
        );
    }

    @Cuando("intenta acceder a la ruta protegida {string}")
    public void intentaAccederALaRutaProtegida(String route) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavigateToProtectedRoute.at("http://localhost:5173" + route),
                WaitForMilliseconds.of(2000)
        );
    }

    @Cuando("inicia sesion con usuario {string} y contrasena {string}")
    public void iniciaSesionConUsuarioYContrasena(String username, String password) {
        String actualUsername = OnStage.theActorInTheSpotlight().recall("username");
        String loginUsername = (actualUsername != null) ? actualUsername : username;
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(LoginPage.USERNAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                LoginUser.withCredentials(loginUsername, password)
        );
    }
}
