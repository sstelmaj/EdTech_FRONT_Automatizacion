package org.sstelmaj.stepdefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.questions.Visibility;
import org.sstelmaj.questions.AlertMessage;
import org.sstelmaj.tasks.ExportBulletin;
import org.sstelmaj.ui.ExportModal;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class ExportStepDefinitions {

    @Cuando("exporta el boletin del estudiante {string} en formato {string}")
    public void exportaElBoletinDelEstudianteEnFormato(String studentName, String format) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ExportBulletin.forStudent(studentName, format)
        );
    }

    @Cuando("abre el modal de exportacion del estudiante {string} en formato {string}")
    public void abreElModalDeExportacionDelEstudianteEnFormato(String studentName, String format) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ExportBulletin.openModalFor(studentName, format)
        );
    }

    @Cuando("confirma la exportacion del boletin")
    public void confirmaLaExportacionDelBoletin() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ExportBulletin.clickExport()
        );
    }

    @Entonces("se muestra el mensaje de exportacion exitosa en formato {string}")
    public void seMuestraElMensajeDeExportacionExitosaEnFormato(String format) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ExportModal.SUCCESS_ALERT, isVisible()).forNoMoreThan(5).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(AlertMessage.from(ExportModal.SUCCESS_ALERT),
                        containsString("Boletin exportado exitosamente en formato " + format))
        );
    }

    @Entonces("se muestra la advertencia de notas vacias")
    public void seMuestraLaAdvertenciaDeNotasVacias() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ExportModal.EMPTY_GRADES_WARNING, isVisible()).forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(Visibility.of(ExportModal.EMPTY_GRADES_WARNING), is(true))
        );
    }
}
