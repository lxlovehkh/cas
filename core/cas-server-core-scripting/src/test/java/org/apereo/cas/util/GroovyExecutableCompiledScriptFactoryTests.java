package org.apereo.cas.util;

import org.apereo.cas.config.CasCoreScriptingAutoConfiguration;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.test.CasTestExtension;
import org.apereo.cas.util.scripting.ExecutableCompiledScript;
import org.apereo.cas.util.scripting.ExecutableCompiledScriptFactory;
import org.apereo.cas.util.scripting.ScriptResourceCacheManager;
import lombok.val;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is {@link GroovyExecutableCompiledScriptFactoryTests}.
 *
 * @author Misagh Moayyed
 * @since 7.1.0
 */
@Tag("Groovy")
@ExtendWith(CasTestExtension.class)
@SpringBootTest(classes = {
    RefreshAutoConfiguration.class,
    CasCoreScriptingAutoConfiguration.class
})
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class GroovyExecutableCompiledScriptFactoryTests {

    @Autowired
    @Qualifier(ScriptResourceCacheManager.BEAN_NAME)
    private ScriptResourceCacheManager<String, ExecutableCompiledScript> scriptResourceCacheManager;

    @Test
    void verifyOperation() throws Exception {
        assertNotNull(scriptResourceCacheManager);
        val scriptFactory = ExecutableCompiledScriptFactory.getExecutableCompiledScriptFactory();
        assertNotNull(scriptFactory);
    }
}
