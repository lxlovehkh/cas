package org.apereo.cas.support.geo;

import org.apereo.cas.authentication.adaptive.geo.GeoLocationService;
import org.apereo.cas.config.CasCoreScriptingAutoConfiguration;
import org.apereo.cas.config.CasGeoLocationAutoConfiguration;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.test.CasTestExtension;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import java.net.InetAddress;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is {@link GroovyGeoLocationServiceTests}.
 *
 * @author Misagh Moayyed
 * @since 6.6.0
 */
@Tag("Groovy")
@ExtendWith(CasTestExtension.class)
@SpringBootTest(classes = {
    RefreshAutoConfiguration.class,
    WebMvcAutoConfiguration.class,
    CasCoreScriptingAutoConfiguration.class,
    CasGeoLocationAutoConfiguration.class
}, properties = "cas.geo-location.groovy.location=classpath:/GroovyGeoLocation.groovy")
@EnableConfigurationProperties(CasConfigurationProperties.class)
class GroovyGeoLocationServiceTests {

    @Autowired
    @Qualifier(GeoLocationService.BEAN_NAME)
    private GeoLocationService geoLocationService;

    @Test
    void verifyOperation() throws Throwable {
        var results = geoLocationService.locate(InetAddress.getByName("www.google.com"));
        assertNotNull(results);
        results = geoLocationService.locate(1234.0D, 1234.0D);
        assertNotNull(results);
    }
}
