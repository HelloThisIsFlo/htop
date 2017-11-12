package com.floriankempenich.htop.module.kernel;

import android.util.Log;

import com.floriankempenich.htop.domain.application.process.ProcessDTO;
import com.floriankempenich.htop.domain.interfaces.RunningProcessesLister;
import com.jrummyapps.android.shell.CommandResult;
import com.jrummyapps.android.shell.Shell;

import org.androidannotations.annotations.EBean;

import java.util.Collections;
import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class RunningProcessesListerImpl implements RunningProcessesLister {

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
