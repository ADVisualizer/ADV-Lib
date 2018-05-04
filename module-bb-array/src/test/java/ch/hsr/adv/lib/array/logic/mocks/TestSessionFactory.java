package ch.hsr.adv.lib.array.logic.mocks;

import ch.hsr.adv.lib.core.access.GsonProvider;
import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.core.logic.domain.Snapshot;
import ch.hsr.adv.lib.array.logic.domain.ArrayElement;
import com.google.inject.Inject;

public class TestSessionFactory {

    private Session session;
    private GsonProvider gsonProvider;

    @Inject
    public TestSessionFactory(Session session, Snapshot snapshot,
                              ArrayElement e1, ArrayElement e2, GsonProvider
                                      gsonProvider) {
        this.session = session;
        this.gsonProvider = gsonProvider;
        e1.setContent("1");
        e1.setId(1);
        e1.setStyle(new TestStyle());
        e2.setContent("2");
        e2.setId(2);
        e2.setFixedPosX(10);
        e2.setFixedPosY(20);
        snapshot.addElement(e1);
        snapshot.addElement(e2);
        session.setNames("array", "testSession");
        session.setSnapshot(snapshot);
    }

    public Session getSession() {
        return session;
    }

    public String getSessionString() {
        return gsonProvider.getMinifier().toJson(session);
    }
}
