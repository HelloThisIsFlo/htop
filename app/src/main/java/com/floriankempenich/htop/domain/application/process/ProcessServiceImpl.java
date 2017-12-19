package com.floriankempenich.htop.domain.application.process;

import com.floriankempenich.htop.domain.application.ProcessService;
import com.floriankempenich.htop.domain.interfaces.DeprecatedRunningProcessesLister;
import com.floriankempenich.htop.module.kernel.DeprecatedRunningProcessesListerImpl;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EBean(scope = EBean.Scope.Singleton)
public class ProcessServiceImpl implements ProcessService {

    private DeprecatedRunningProcessesLister processesLister;

    @Bean(DeprecatedRunningProcessesListerImpl.class)
    public void setProcessesLister(DeprecatedRunningProcessesLister processesLister) {
        this.processesLister = processesLister;
    }

    @Override
    public List<ProcessDTO> getRunningProcess() {
        return getSortedProcessList();
    }

    @Override
    public ProcessDTO getMostCPUConsuming() {
        return Optional.of(getSortedProcessList())
                .filter(this::notEmpty)
                .map(this::getFirst)
                .orElse(null);
    }

    private <T> boolean notEmpty(List<T> list) {
        return !(list.isEmpty());
    }

    private <T> T getFirst(List<T> list) {
        return list.get(0);
    }

    private List<ProcessDTO> getSortedProcessList() {
        return processesLister
                .getAll()
                .stream()
                .sorted(this::byCPUPercentComparator)
                .collect(Collectors.toList());
    }

    private int byCPUPercentComparator(ProcessDTO process1, ProcessDTO process2) {
        return Float.compare(process1.percentCPU, process2.percentCPU);
    }

    @Override
    public ProcessDTO getMostCPUConsumingInTheLast(Duration period) {
        throw new IllegalStateException("Not yet implemented!");
    }
}
