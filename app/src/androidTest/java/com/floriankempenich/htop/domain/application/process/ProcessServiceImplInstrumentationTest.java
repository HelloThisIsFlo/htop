package com.floriankempenich.htop.domain.application.process;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.floriankempenich.htop.domain.application.ProcessService;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(AndroidJUnit4.class)
public class ProcessServiceImplInstrumentationTest {

    private ProcessServiceImpl processService;

    @Test
    @Ignore
    public void sandboxTest() throws Exception {
        processService = createProcessService();
        ProcessDTO mostConsuming = processService.getMostCPUConsuming();

        fail(mostConsuming.toString());
    }

    private ProcessServiceImpl createProcessService() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        return ProcessServiceImpl_.getInstance_(appContext);
    }
}
