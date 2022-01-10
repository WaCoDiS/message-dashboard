/*
 * Copyright 2019-2022 52°North Spatial Information Research GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
