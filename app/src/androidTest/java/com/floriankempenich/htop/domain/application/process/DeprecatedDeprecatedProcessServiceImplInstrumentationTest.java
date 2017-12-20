package com.floriankempenich.htop.domain.application.process;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;


@RunWith(AndroidJUnit4.class)
public class DeprecatedDeprecatedProcessServiceImplInstrumentationTest {

    private DeprecatedDeprecatedProcessServiceImpl processService;

    @Test
    @Ignore
    public void sandboxTest() throws Exception {
        processService = createProcessService();
        ProcessDTO mostConsuming = processService.getMostCPUConsuming();

        fail(mostConsuming.toString());
    }

    private DeprecatedDeprecatedProcessServiceImpl createProcessService() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        return DeprecatedDeprecatedProcessServiceImpl_.getInstance_(appContext);
    }
}
