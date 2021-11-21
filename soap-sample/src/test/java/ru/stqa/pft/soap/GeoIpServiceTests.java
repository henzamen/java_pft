package ru.stqa.pft.soap;


import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {

    @Test(priority = 1)
    public void testMyIp() {
        String countryCode = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("77.45.191.186");
        assertTrue(countryCode.contains("Country>RU<"));
    }

    @Test(priority = 2)
    public void testInvalidIp() {
        String countryCode = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("77.45.191.1o6");
        assertNull(countryCode);
    }

}