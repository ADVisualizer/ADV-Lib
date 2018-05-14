package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.core.logic.mocks.TestADVModule;
import ch.hsr.adv.lib.core.logic.mocks.TestConstants;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class CoreBuilderTest {
    @Inject
    private CoreBuilder sut;
    @Inject
    private TestADVModule testModule;
    @Inject
    private ADVModule autoMockModule;

    @Test
    public void buildNormalModuleTest() {
        // WHEN
        Session actual = sut.build(testModule, TestConstants.SNAPSHOT_DESC);

        // THEN
        assertEquals(TestConstants.SESSION_NAME, actual.getSessionName());
        assertEquals(TestConstants.SNAPSHOT_DESC, actual.getSnapshot()
                .getSnapshotDescription());
    }

    @Test
    public void buildNullModuleTest() {
        // WHEN
        Session actual = sut.build(autoMockModule, TestConstants.SNAPSHOT_DESC);

        // THEN
        assertEquals(autoMockModule.getSessionName(), actual.getSessionName());
        assertEquals(TestConstants.SNAPSHOT_DESC, actual.getSnapshot()
                .getSnapshotDescription());
    }

}