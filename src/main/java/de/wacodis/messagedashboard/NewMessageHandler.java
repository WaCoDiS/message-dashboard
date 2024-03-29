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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.messagedashboard;

import de.wacodis.messagedashboard.model.AbstractDataEnvelope;
import de.wacodis.messagedashboard.model.CopernicusDataEnvelope;
import de.wacodis.messagedashboard.model.ProductDescription;
import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author matthes
 */
@Component
public class NewMessageHandler implements InitializingBean {
    
    private static final Logger LOG = LoggerFactory.getLogger(NewMessageHandler.class);
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    private MessageArchiveController archive;
    
    @Override
    public void afterPropertiesSet() throws Exception {
//        new Thread(() -> {
//            for (int i = 0; i < 100000; i++) {
//                try {
//                    Thread.sleep(5000);
//                    this.dummyEnvelope();
//                } catch (Exception ex) {
//                    java.util.logging.Logger.getLogger(NewMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }).start();
    }
    
    public void handleNewProduct(ProductDescription r) {
    }
    
    
    public void dummyEnvelope() throws Exception {
        String[] targetTopics = new String[] {
            "/topic/data-envelope",
            "/topic/new-job",
            "/topic/new-process-result"
        };
        Random rand = new Random();
        CopernicusDataEnvelope env = new CopernicusDataEnvelope();
        env.setSourceType(AbstractDataEnvelope.SourceTypeEnum.COPERNICUSDATAENVELOPE);
        env.setSatellite(CopernicusDataEnvelope.SatelliteEnum._2);
        env.setDatasetId(UUID.randomUUID());
        env.setIdentifier("S2A_MSI_---");
        
        this.simpMessagingTemplate.convertAndSend(
                targetTopics[rand.nextInt(targetTopics.length)],
                env);
    }
    
    public void publishWebSocket(String topic, Object msg) {
        this.simpMessagingTemplate.convertAndSend(topic, msg);
        this.archive.storeMessage(topic, msg);
        LOG.debug("Published message on topic {}", topic);
    }
    
}
