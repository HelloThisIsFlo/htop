package com.floriankempenich.htop.module.kernel;

import android.util.Log;

import com.floriankempenich.htop.domain.application.process.ProcessDTO;
import com.floriankempenich.htop.domain.interfaces.DeprecatedRunningProcessesLister;
import com.jrummyapps.android.shell.CommandResult;
import com.jrummyapps.android.shell.Shell;

import org.androidannotations.annotations.EBean;

import java.util.Collections;
import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class DeprecatedRunningProcessesListerImpl implements DeprecatedRunningProcessesLister {

    @Override
    public List<ProcessDTO> getAll() {

        logRootCmd("ps -c | head");

        return Collections.emptyList();
    }

    private void logRootCmd(String cmd) {
        CommandResult cmdRes = Shell.SU.run(cmd);
        Log.d("Result:", cmdRes.toString());
    }

}
