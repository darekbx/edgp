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
        titleParcel.writeValue(title);

        assertNotNull(titleParcel);

        Title unparceled = new Title(titleParcel);

        assertNotNull(unparceled);
        assertEquals(1, title.id);
        assertEquals("Name", title.name);
        assertEquals("Description", title.description);
        assertEquals("Categories", title.categories);
        assertEquals(121, title.coverId);
    }
}
