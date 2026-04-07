package org.sstelmaj.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DashboardPage {

    public static final String URL = "/dashboard";

    public static final Target USERNAME_DISPLAY = Target.the("nombre de usuario en dashboard")
            .locatedBy("//p[contains(text(),'{0}')]");

    public static final Target LOGOUT_BUTTON = Target.the("boton salir")
            .locatedBy("//button[contains(text(),'Salir')]");

    public static final Target COURSES_TITLE = Target.the("titulo mis cursos")
            .locatedBy("//h2[contains(text(),'Mis Cursos')]");

    public static final Target CREATE_COURSE_BUTTON = Target.the("boton crear curso")
            .locatedBy("//button[contains(text(),'Crear curso')]");

    public static final Target EMPTY_STATE = Target.the("estado sin cursos")
            .locatedBy("//div[contains(text(),'No tienes cursos creados')]");

    public static Target courseCard(String courseName) {
        return Target.the("card del curso " + courseName)
                .locatedBy("//button[contains(.,'" + courseName + "')]");
    }
}
