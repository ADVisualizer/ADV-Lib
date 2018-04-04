package ch.adv.lib.array;

import ch.adv.lib.array.mocks.ArraytestModule;
import ch.adv.lib.core.domain.ADVElement;
import ch.adv.lib.core.domain.ADVRelation;
import ch.adv.lib.core.domain.Session;
import ch.adv.lib.core.domain.Snapshot;
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
    private ADVRelation actualRelation;

    @Before
    public void setUp() throws Exception {
        testDescription = "testDescription";
        actualSession = builder.build(testModule, this.testDescription);
        actualSnapshot = actualSession.getSnapshot();
        actualElement = actualSnapshot.getElements().get(1);
        actualRelation = actualSnapshot.getRelations().get(0);
    }

    @Test
    public void buildSessionTest() {
        assertEquals(testModule.getSessionName(), actualSession
                .getSessionName());
        assertEquals("array", actualSession.getModuleName());
    }

    @Test
    public void buildSnapshotTest() {
        assertEquals(this.testDescription, actualSnapshot
                .getSnapshotDescription());
        assertEquals(2, actualSnapshot.getElements().size());
        assertEquals(1, actualSnapshot.getRelations().size());
    }

    @Test
    public void buildElementTest() {
        assertEquals(1, actualElement.getElementId());
        assertEquals("10", actualElement.getContent());
        Assert.assertEquals("testColor", actualElement.getStyle().getFillColor());
        assertEquals(1, actualElement.getFixedPosX());
        assertEquals(2, actualElement.getFixedPosY());
    }

    @Test
    public void buildRelationTest() {
        assertEquals(0, actualRelation.getSourceElementId());
        assertEquals(1, actualRelation.getTargetElementId());
        assertEquals(null, actualRelation.getLabel());
        assertEquals(null, actualRelation.getStyle());
    }
}