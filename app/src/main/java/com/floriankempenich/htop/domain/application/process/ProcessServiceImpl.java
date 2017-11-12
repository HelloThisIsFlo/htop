package com.floriankempenich.htop.domain.application.process;

import com.floriankempenich.htop.domain.application.ProcessService;
import com.floriankempenich.htop.domain.interfaces.RunningProcessesLister;
import com.floriankempenich.htop.module.kernel.RunningProcessesListerImpl;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;
import java.util.stream.Collectors;

@EBean(scope = EBean.Scope.Singleton)
public class ProcessServiceImpl implements ProcessService {

    private RunningProcessesLister processesLister;

    @Bean(RunningProcessesListerImpl.class)
    public void setProcessesLister(RunningProcessesLister processesLister) {
        this.processesLister = processesLister;
    }

    @Override
    public List<ProcessDTO> getRunningProcess() {
        return processesLister
                .getAll()
                .stream()
                .sorted(this::byCPUPercentComparator)
                .collect(Collectors.toList());
    }

    @Override
    public ProcessDTO getMostCPUConsuming() {
        throw new IllegalStateException("Not implemented yet");
    }

    private int byCPUPercentComparator(ProcessDTO process1, ProcessDTO process2) {
        return Float.compare(process1.percentCPU, process2.percentCPU);
    }

}
