package ch.adv.lib.core.domain.styles;

import ch.adv.lib.core.util.ADVStyleException;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class ADVValueStyleTest {
    @Inject
    private ADVValueStyle styleUnderTest;

    @Test
    public void validValuesTest() {
        int exceptionCounter = 0;
        try {
            styleUnderTest.setFillColor(0xaa00ff);
        } catch (ADVStyleException e) {
            exceptionCounter++;
        }
        try {
            styleUnderTest.setStrokeColor(0xaa00ff);
        } catch (ADVStyleException e) {
            exceptionCounter++;
        }
        try {
            styleUnderTest.setStrokeThickness(2);
        } catch (ADVStyleException e) {
            exceptionCounter++;
        }
        styleUnderTest.setStrokeStyle(ADVStrokeStyle.DOTTED);
        assertEquals(0, exceptionCounter);
    }

    @Test(expected = ADVStyleException.class)
    public void invalidColorTooLowTest() throws ADVStyleException {
        styleUnderTest.setFillColor(-1);
    }

    @Test(expected = ADVStyleException.class)
    public void invalidColorTooHighTest() throws ADVStyleException {
        styleUnderTest.setStrokeColor(0xffffff1);
    }

    @Test(expected = ADVStyleException.class)
    public void invalidStrokethicknessTest() throws ADVStyleException {
        styleUnderTest.setStrokeThickness(-1);
    }
}