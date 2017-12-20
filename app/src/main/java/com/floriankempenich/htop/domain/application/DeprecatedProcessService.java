package com.floriankempenich.htop.domain.application;

import com.floriankempenich.htop.domain.application.process.ProcessDTO;

import java.time.Duration;
import java.util.List;

public interface DeprecatedProcessService {

    List<ProcessDTO> getRunningProcess();
    ProcessDTO getMostCPUConsuming();

    /**
     * TODO: Implement this method
     *
     * Return the process that has been consuming the most CPU in the last X duration.
     *
     * eg:
     *     `getMostCPUConsumingInTheLast(Duration.ofSeconds(30))`
     *     Will return the most CPU consuming process in the last 30 seconds.
     *
     * Note to self:
     * Keep the service stateless, have a job that fills a cache w/ the running processes
     * every X seconds.
     * Then the service only analyze and make sense of all this data to extract the desired result.
     *
     * @param period Duration to analyze (Max: 5 min)
     * @return Most CPU Consuming Process within the last X seconds/minutes/etc...
     */
    ProcessDTO getMostCPUConsumingInTheLast(Duration period);
}
