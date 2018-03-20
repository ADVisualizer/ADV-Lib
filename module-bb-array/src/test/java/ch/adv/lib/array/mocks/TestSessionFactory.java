package ch.adv.lib.array.mocks;

import ch.adv.lib.array.model.ArrayElement;
import ch.adv.lib.array.model.ArrayRelation;
import ch.adv.lib.model.Session;
import ch.adv.lib.model.Snapshot;
import com.google.gson.Gson;
import com.google.inject.Inject;

public class TestSessionFactory {

    private Session session;
    private Gson gson;

    @Inject
    public TestSessionFactory( Session session,Snapshot snapshot,  ArrayElement e1,  ArrayElement e2, ArrayRelation r, Gson gson ) {
        this.session = session;
        this.gson = gson;
        e1.setContent("1");
        e1.setId(1);
        e1.setStyle(new TestStyle());
        e2.setContent("2");
        e2.setId(2);
        e2.setFixedPosX(10);
        e2.setFixedPosY(20);
        r.setSourceElementId(1);
        r.setTargetElementId(2);
        snapshot.addElement(e1);
        snapshot.addElement(e2);
        session.setNames("array", "testSession");
        session.setSnapshot(snapshot);
    }

    public Session getSession(){
        return session;
    }

    public String getSessionString(){
        return gson.toJson(session);
    }
}
