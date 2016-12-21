package com.edgp.model;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-12-21.
 */
@RunWith(RobolectricTestRunner.class)
public class IssueParcelTest {

    @Test
    public void testParcel() {
        List<Booklet> booklets = new ArrayList<>(2);
        booklets.add(new Booklet(500, "Booklet 1"));
        booklets.add(new Booklet(600, "Booklet 2"));
        Issue issue = new Issue(1, "Name", booklets);

        Parcel issueParcel = Parcel.obtain();
        issue.writeToParcel(issueParcel, 0);

        assertNotNull(issueParcel);

        issueParcel.setDataPosition(0);
        Issue unparceled = (Issue)Issue.CREATOR.createFromParcel(issueParcel);

        assertNotNull(unparceled);
        assertEquals(1, unparceled.id);
        assertEquals("Name", unparceled.name);
        assertEquals(2, unparceled.booklets.size());
        assertEquals(500, unparceled.booklets.get(0).id);
        assertEquals("Booklet 1", unparceled.booklets.get(0).name);
        assertEquals(600, unparceled.booklets.get(1).id);
        assertEquals("Booklet 2", unparceled.booklets.get(1).name);
    }
}
