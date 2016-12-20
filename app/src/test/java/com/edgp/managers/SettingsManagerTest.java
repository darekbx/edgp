package com.edgp.managers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-12-19.
 */
@RunWith(RobolectricTestRunner.class)
public class SettingsManagerTest {

    private SettingsManager settingsManager;

    @Before
    public void setUp() {
        settingsManager = new SettingsManager(RuntimeEnvironment.application);
    }

    @After
    public void clean() {
        settingsManager.clear();
    }

    @Test
    public void schemeTest() throws Exception {
        String scheme = settingsManager.getScheme();
        assertNull(scheme);

        settingsManager.setScheme("Scheme");

        assertEquals("Scheme", settingsManager.getScheme());
    }

    @Test
    public void authorityTest() throws Exception {
        String authority = settingsManager.getAuthority();
        assertNull(authority);

        settingsManager.setAuthority("Authority");

        assertEquals("Authority", settingsManager.getAuthority());
    }

    @Test
    public void apiKeyTest() throws Exception {
        String apiKey = settingsManager.getApiKey();
        assertNull(apiKey);

        settingsManager.setApiKey("Key");

        assertEquals("Key", settingsManager.getApiKey());
    }
}