
package de.wacodis.messagedashboard.streams;

import de.wacodis.messagedashboard.NewMessageHandler;
import de.wacodis.messagedashboard.model.AbstractDataEnvelope;
import de.wacodis.messagedashboard.model.ProductDescription;
import de.wacodis.messagedashboard.model.WacodisJobDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 *
 * @author <a href="mailto:m.rieke@52north.org">Matthes Rieke</a>
 */
@EnableBinding(StreamChannels.class)
public class StreamBinder implements InitializingBean {
    
    private static final Logger LOG = LoggerFactory.getLogger(StreamBinder.class.getName());
    
    @Autowired
    private NewMessageHandler handler;
    

    @Override
    public void afterPropertiesSet() throws Exception {
    }
    
    
    @StreamListener(StreamChannels.JOBS_NEW)
    public void receiveNewJob(WacodisJobDefinition job) {
        this.handler.publishWebSocket("/topic/wacodis.prod.jobs.new", job);
    }
 
    @StreamListener(StreamChannels.JOBS_STATUS)
    public void receiveJobStatus(WacodisJobDefinition job) {
        this.handler.publishWebSocket("/topic/wacodis.prod.jobs.status", job);
    }
    
    @StreamListener(StreamChannels.JOBS_DELETION)
    public void receiveJobDeletion(WacodisJobDefinition job) {
        this.handler.publishWebSocket("/topic/wacodis.prod.jobs.deleted", job);
    }
    
    @StreamListener(StreamChannels.TOOLS_EXECUTE)
    public void receiveToolExecuted(Object job) {
        this.handler.publishWebSocket("/topic/wacodis.prod.tools.execute", job);
    }
    
    @StreamListener(StreamChannels.TOOLS_FINISHED)
    public void receiveToolFinished(ProductDescription job) {
        this.handler.publishWebSocket("/topic/wacodis.prod.tools.finished", job);
    }
    
    @StreamListener(StreamChannels.DATA_AVAILABLE)
    public void receiveDataAvailable(AbstractDataEnvelope job) {
        this.handler.publishWebSocket("/topic/wacodis.prod.data.available", job);
    }
    
    @StreamListener(StreamChannels.DATA_ACCESSIBLE)
    public void receiveDataAccessible(AbstractDataEnvelope job) {
        this.handler.publishWebSocket("/topic/wacodis.prod.data.accessible", job);
    }
    
}
