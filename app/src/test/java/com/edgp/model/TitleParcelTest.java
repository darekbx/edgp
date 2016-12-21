package com.edgp.model;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-12-20.
 */
@RunWith(RobolectricTestRunner.class)
public class TitleParcelTest {

    @Test
    public void testParcel() {
        Title title = new Title(1, "Name", "Description", "Categories", 121);

        Parcel titleParcel = Parcel.obtain();
        title.writeToParcel(titleParcel, 0);

        assertNotNull(titleParcel);

        titleParcel.setDataPosition(0);
        Title unparceled = (Title)Title.CREATOR.createFromParcel(titleParcel);

        assertNotNull(unparceled);
        assertEquals(1, unparceled.id);
        assertEquals("Name", unparceled.name);
        assertEquals("Description", unparceled.description);
        assertEquals("Categories", unparceled.categories);
        assertEquals(121, unparceled.coverId);
    }
}
