package org.apereo.cas.acct;

import org.apereo.cas.config.CasAccountManagementWebflowAutoConfiguration;
import org.apereo.cas.util.crypto.CipherExecutor;
import lombok.val;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import org.apereo.cas.web.flow.BaseWebflowConfigurerTests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is {@link AccountRegistrationTokenCipherExecutorTests}.
 *
 * @author Misagh Moayyed
 * @since 7.1.0
 */
@Tag("Cipher")
@SpringBootTest(classes = {
    BaseWebflowConfigurerTests.SharedTestConfiguration.class,
    CasAccountManagementWebflowAutoConfiguration.class
}, properties = {
    "cas.account-registration.core.crypto.alg=" + ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256,
    "cas.account-registration.core.crypto.encryption.key=XHRK-X9JJewbKoPTzfi31702ipvmVfvG-GMbFPhmhrw",
    "cas.account-registration.core.crypto.signing.key=GM4TNYuqZL0Kgf5dgsPniJYnHA0fo_91uMBOmja0JDsDETPtQ56j_tFj7Jau-152sFKP83cPxChF6I9ExzAzMw"
})
public class AccountRegistrationTokenCipherExecutorTests {
    @Autowired
    @Qualifier("accountMgmtCipherExecutor")
    private CipherExecutor accountMgmtCipherExecutor;

    @Test
    void verifyOperation() throws Exception {
        val valueToEncode = UUID.randomUUID().toString();
        val encoded = accountMgmtCipherExecutor.encode(valueToEncode);
        assertEquals(valueToEncode, accountMgmtCipherExecutor.decode(encoded));
    }
}