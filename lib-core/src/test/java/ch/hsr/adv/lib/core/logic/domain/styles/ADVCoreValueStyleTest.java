package ch.hsr.adv.lib.core.logic.domain.styles;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStrokeStyle;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVValueStyle;
import ch.hsr.adv.commons.core.logic.util.ADVStyleException;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class ADVCoreValueStyleTest {

    @Inject
    private ADVValueStyle sut;

    @Test
    public void validValuesTest() {
        int exceptionCounter = 0;
        try {
            sut.setFillColor(0xaa00ff);
        } catch (ADVStyleException e) {
            exceptionCounter++;
        }
        try {
            sut.setStrokeColor(0xaa00ff);
        } catch (ADVStyleException e) {
            exceptionCounter++;
        }
        try {
            sut.setStrokeThickness(2);
        } catch (ADVStyleException e) {
            exceptionCounter++;
        }
        sut.setStrokeStyle(ADVStrokeStyle.DOTTED);
        assertEquals(0, exceptionCounter);
    }

    @Test(expected = ADVStyleException.class)
    public void invalidColorTooLowTest() throws ADVStyleException {
        sut.setFillColor(-1);
    }

    @Test(expected = ADVStyleException.class)
    public void invalidColorTooHighTest() throws ADVStyleException {
        sut.setStrokeColor(0xffffff1);
    }

    @Test(expected = ADVStyleException.class)
    public void invalidStrokethicknessTest() throws ADVStyleException {
        sut.setStrokeThickness(-1);
    }
}