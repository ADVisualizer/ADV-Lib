package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.lib.array.logic.mocks.ArraytestModule;
import ch.hsr.adv.lib.core.logic.domain.ADVElement;
import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.core.logic.domain.Snapshot;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class ArrayBuilderTest {

    @Inject
    private ArrayBuilder builder;

    @Inject
    private ArraytestModule testModule;

    private String testDescription;
    private Session actualSession;
    private Snapshot actualSnapshot;
    private ADVElement actualElement;

    @Before
    public void setUp() throws Exception {
        testDescription = "testDescription";
        actualSession = builder.build(testModule, this.testDescription);
        actualSnapshot = actualSession.getSnapshot();
        actualElement = actualSnapshot.getElements().get(1);
    }

    @Test
    public void buildSessionTest() {
        Assert.assertEquals(testModule.getSessionName(), actualSession
                .getSessionName());
        assertEquals("array", actualSession.getModuleName());
    }

    @Test
    public void buildSnapshotTest() {
        assertEquals(this.testDescription, actualSnapshot
                .getSnapshotDescription());
        assertEquals(2, actualSnapshot.getElements().size());
    }

    @Test
    public void buildElementTest() {
        assertEquals(1, actualElement.getId());
        assertEquals("10", actualElement.getContent());
        Assert.assertEquals(0, actualElement.getStyle().getFillColor());
        assertEquals(1, actualElement.getFixedPosX());
        assertEquals(2, actualElement.getFixedPosY());
    }

}