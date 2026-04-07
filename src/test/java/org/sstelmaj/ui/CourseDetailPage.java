package org.sstelmaj.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CourseDetailPage {

    public static final Target BACK_BUTTON = Target.the("boton volver")
            .locatedBy("//button[contains(text(),'Volver')]");

    public static final Target COURSE_NAME = Target.the("nombre del curso")
            .located(By.cssSelector("h1"));

    public static final Target STUDENT_COUNT = Target.the("conteo de estudiantes")
            .locatedBy("//div[contains(text(),'Estudiantes')]/preceding-sibling::div | //div[contains(text(),'Estudiantes')]/..//div[1]");

    public static final Target ACTIVITY_COUNT = Target.the("conteo de actividades")
            .locatedBy("//div[contains(text(),'Actividades evaluativas')]/preceding-sibling::div | //div[contains(text(),'Actividades evaluativas')]/..//div[1]");

    public static final Target ADD_STUDENT_BUTTON = Target.the("boton agregar estudiante")
            .locatedBy("//button[contains(text(),'Agregar estudiante')]");

    public static final Target DEFINE_PROGRAM_BUTTON = Target.the("boton definir programa")
            .locatedBy("//button[contains(text(),'Definir programa') or contains(text(),'Editar programa')]");

    public static final Target GRADES_TABLE = Target.the("tabla de calificaciones")
            .located(By.cssSelector("table"));
}
