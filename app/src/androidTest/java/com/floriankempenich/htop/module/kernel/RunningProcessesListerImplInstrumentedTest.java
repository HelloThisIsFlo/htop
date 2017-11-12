package com.floriankempenich.htop.module.kernel;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.floriankempenich.htop.domain.interfaces.RunningProcessesLister;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class RunningProcessesListerImplInstrumentedTest {

    private RunningProcessesLister runningProcessesLister;

    @Before
    public void setUp() throws Exception {
        runningProcessesLister = createProcessService();
    }

    private RunningProcessesListerImpl createProcessService() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        return RunningProcessesListerImpl_.getInstance_(appContext);
    }

    @Test
    public void processListIsNotEmtpy() throws Exception {
        assertFalse(runningProcessesLister.getAll().isEmpty());
    }

    @Test
    @Ignore
    public void debugProcessList() throws Exception {
        fail(runningProcessesLister.getAll().toString());
    }

}
