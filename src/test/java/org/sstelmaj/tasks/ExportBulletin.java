package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.interactions.WaitForMilliseconds;
import org.sstelmaj.ui.ExportModal;
import org.sstelmaj.ui.GradesTable;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ExportBulletin implements Task {

    private final String studentName;
    private final String format;
    private final int mode; // 0 = full flow, 1 = open modal only, 2 = click export only

    private static final String FORMAT_KEY = "exportFormat";

    private ExportBulletin(String studentName, String format, int mode) {
        this.studentName = studentName;
        this.format = format;
        this.mode = mode;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (mode == 2) {
            String rememberedFormat = actor.recall(FORMAT_KEY);
            actor.attemptsTo(
                    Click.on(ExportModal.exportButton(rememberedFormat))
            );
            return;
        }

        actor.remember(FORMAT_KEY, format);
        actor.attemptsTo(
                WaitUntil.the(GradesTable.exportButton(studentName), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(GradesTable.exportButton(studentName)),
                WaitUntil.the(ExportModal.TITLE, isVisible()).forNoMoreThan(10).seconds(),
                WaitForMilliseconds.of(500),
                Click.on(ExportModal.formatOption(format)),
                WaitForMilliseconds.of(500)
        );

        if (mode == 0) {
            actor.attemptsTo(
                    Click.on(ExportModal.exportButton(format))
            );
        }
    }

    public static ExportBulletin forStudent(String studentName, String format) {
        return new ExportBulletin(studentName, format, 0);
    }

    public static ExportBulletin openModalFor(String studentName, String format) {
        return new ExportBulletin(studentName, format, 1);
    }

    public static ExportBulletin clickExport() {
        return new ExportBulletin(null, null, 2);
    }
}
