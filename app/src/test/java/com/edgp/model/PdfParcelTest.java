package com.edgp.model;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by daba on 2016-12-21.
 */
@RunWith(RobolectricTestRunner.class)
public class PdfParcelTest {

    @Test
    public void testParcel() {
        List<Article> articles = new ArrayList<>(2);
        articles.add(new Article(200, "Article 1", "", "type"));
        articles.add(new Article(300, "Article 2", "", "type"));
        Pdf pdf = new Pdf(1, "Name", articles);

        Parcel pdfParcel = Parcel.obtain();
        pdf.writeToParcel(pdfParcel, 0);

        assertNotNull(pdfParcel);

        pdfParcel.setDataPosition(0);
        Pdf unparceled = (Pdf)Pdf.CREATOR.createFromParcel(pdfParcel);

        assertNotNull(unparceled);
        assertEquals(1, unparceled.id);
        assertEquals("Name", unparceled.name);
        assertEquals(2, unparceled.articles.size());
        assertEquals(500, unparceled.articles.get(0).id);
        assertEquals("Booklet 1", unparceled.articles.get(0).name);
        assertEquals(600, unparceled.articles.get(1).id);
        assertEquals("Booklet 2", unparceled.articles.get(1).name);
    }
}
