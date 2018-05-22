package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.Snapshot;
import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.core.logic.mocks.TestConstants;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JukitoRunner.class)
@UseModules(GuiceCoreModule.class)
public class CoreStringifyerTest {
    private static final String SESSION_ID_TAG = "sessionId";
    private static final String SESSION_NAME_TAG = "sessionName";
    private static final String SNAPSHOTS_TAG = "snapshots";
    private static final String SNAPSHOT_ID_TAG = "snapshotId";
    private static final String MODULE_GROUPS_TAG = "moduleGroups";
    private static final String ELEMENTS_TAG = "elements";
    private static final String RELATIONS_TAG = "relations";
    private static final String FLAGS_TAG = "flags";
    private static final String MODULE_NAME_TAG = "moduleName";
    private Session testSession;
    private Snapshot testSnapshot;
    private ModuleGroup group;
    @Inject
    private CoreStringifyer sut;
    private ArrayList<ModuleGroup> moduleGroups;

    @Before
    public void setUp() throws IOException {
        testSession = new Session(TestConstants.SESSION_NAME);
        testSnapshot = new Snapshot();
        group = new ModuleGroup(TestConstants.MODULE_NAME);
        moduleGroups = new ArrayList<>();
        moduleGroups.add(group);
        testSnapshot.setModuleGroups(moduleGroups);
        testSession.setSnapshot(testSnapshot);
    }

    @Test
    public void stringifyTest() {
        // WHEN
        String actual = sut.stringify(testSession);

        // THEN
        assertTrue(actual.contains(SESSION_ID_TAG));
        assertTrue(actual.contains(SESSION_NAME_TAG));
        assertTrue(actual.contains(TestConstants.SESSION_NAME));
        assertTrue(actual.contains(SNAPSHOTS_TAG));
        assertTrue(actual.contains(SNAPSHOT_ID_TAG));
        assertTrue(actual.contains(MODULE_GROUPS_TAG));
        assertTrue(actual.contains(ELEMENTS_TAG));
        assertTrue(actual.contains(RELATIONS_TAG));
        assertTrue(actual.contains(FLAGS_TAG));
        assertTrue(actual.contains(MODULE_NAME_TAG));
        assertTrue(actual.contains(TestConstants.MODULE_NAME));
    }
}