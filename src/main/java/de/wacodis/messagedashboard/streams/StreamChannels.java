
package de.wacodis.messagedashboard.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
public interface StreamChannels {
    
    String JOBS_NEW = "jobs-new";
    String JOBS_STATUS = "jobs-status";
    String JOBS_DELETION = "jobs-deletion";
    String TOOLS_EXECUTE = "tools-execute";
    String TOOLS_FINISHED = "tools-finished";
    String TOOLS_FAILURE = "tools-failure";
    String DATA_AVAILABLE = "data-available";
    String DATA_ACCESSIBLE = "data-accessible";
 
    @Input(JOBS_NEW)
    SubscribableChannel receiveNewJob();
 
    @Input(JOBS_STATUS)
    SubscribableChannel receiveJobStatus();
    
    @Input(JOBS_DELETION)
    SubscribableChannel receiveJobDeletion();
    
    @Input(TOOLS_EXECUTE)
    SubscribableChannel receiveToolExecuted();
    
    @Input(TOOLS_FINISHED)
    SubscribableChannel receiveToolFinished();
    
    @Input(TOOLS_FAILURE)
    SubscribableChannel receiveToolFailure();
    
    @Input(DATA_AVAILABLE)
    SubscribableChannel receiveDataAvailable();
    
    @Input(DATA_ACCESSIBLE)
    SubscribableChannel receiveDataAccessible();
 
}
