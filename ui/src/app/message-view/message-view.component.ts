import { Component, OnInit } from '@angular/core';
import { SocketClientService } from '../socket-client.service';
import { environment } from 'src/environments/environment';

export class MessageContainer {
  date: Date;
  topic: string;
  messageNumber: number;
  message: any;
}

@Component({
  selector: 'app-message-view',
  templateUrl: './message-view.component.html',
  styleUrls: ['./message-view.component.scss']
})
export class MessageViewComponent implements OnInit {

  messageCount = 0;
  maxLength = 10;
  topics: string[];
  timeStamps: string[] = [];
  topicMessages: { [key: string]: MessageContainer[] } = {};
  messageArchive: MessageContainer[] = [];
  selectedMessage: MessageContainer;

  constructor(private socketClient: SocketClientService) { }

  ngOnInit() {
    this.topics = environment.topics;

    for (let i = 0; i < 10; i++) {
      this.timeStamps.push('');
    }

    this.topics.forEach(t => {
      this.topicMessages[t] = [];
      // set up the topics with empty content
      // required to get a timeline-style visualization
      for (let i = 0; i < 10; i++) {
        this.topicMessages[t].push({ date: null, topic: null, messageNumber: 0, message: null });
      }

      this.socketClient.onMessage(t).subscribe((msg: any) => {
        console.log('received msg on topic', t, msg);

        let archiveCandidates: MessageContainer[] = [];

        this.topics.forEach(innerTopic => {
          if (t === innerTopic) {
            const newMsg = { date: new Date(), topic: innerTopic, messageNumber: ++this.messageCount, message: msg};
            this.topicMessages[innerTopic].push(newMsg);
            this.timeStamps.push(newMsg.date.toLocaleTimeString());
            this.timeStamps.shift();
          } else {
            this.topicMessages[innerTopic].push({ date: null, topic: null, messageNumber: 0, message: null });
          }

          // ensure the length of maxLength
          const archiveCandidate = this.topicMessages[innerTopic].shift();
          if (archiveCandidate.messageNumber > 0) {
            archiveCandidates.push(archiveCandidate);
          }
        });

        archiveCandidates = archiveCandidates.sort((a, b) => {
          return a.messageNumber - b.messageNumber;
        });

        archiveCandidates.forEach(ac => this.messageArchive.unshift(ac));
      });
    });
  }

  selectMessage(topic: string, index: number) {
    this.selectedMessage = this.topicMessages[topic][index];
  }

  stringify(obj: any): string {
    return JSON.stringify(obj, null, 4);
  }

}
