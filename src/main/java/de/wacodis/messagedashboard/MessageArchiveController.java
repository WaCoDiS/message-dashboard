
package de.wacodis.messagedashboard;

import com.google.common.collect.EvictingQueue;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Matthes Rieke <m.rieke@52north.org>
 */
@RestController
public class MessageArchiveController {
    
    private EvictingQueue<MessageContainer> archiveQueue = EvictingQueue.create(50);

    @RequestMapping(method = RequestMethod.GET, path = "/archive")
    public synchronized List<MessageContainer> archivedMessages() {
        return this.archiveQueue.stream().collect(Collectors.toList());
    }

    public synchronized void storeMessage(String topic, Object msg) {
        this.archiveQueue.add(new MessageContainer(new Date(), topic, msg));
    }
    
}
