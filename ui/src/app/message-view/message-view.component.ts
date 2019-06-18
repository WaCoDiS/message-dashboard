import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import { SocketClientService } from '../socket-client.service';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

export class MessageContainer {
  date: Date;
  topic: string;
  messageNumber: number;
  message: any;
}

@Pipe({
  name: 'filteredTopics',
  pure: false
})
export class FilteredTopicsPipe implements PipeTransform {
  transform(allMessages: MessageContainer[], selectedTopic: string) {
    return allMessages.filter(m => {
      return !selectedTopic || selectedTopic === undefined || selectedTopic === 'all' || selectedTopic === m.topic;
    });
  }
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
  selectedTopic: string;
  timeStamps: string[] = [];
  topicMessages: { [key: string]: MessageContainer[] } = {};
  messageArchive: MessageContainer[] = [];
  selectedMessage: MessageContainer;

  constructor(private socketClient: SocketClientService, private http: HttpClient) { }

  ngOnInit() {
    this.topics = environment.topics;

    for (let i = 0; i < 10; i++) {
      this.timeStamps.push('');
    }

    this.retrieveArchivedMessages();

    this.topics.forEach(t => {
      this.topicMessages[t] = [];
      // set up the topics with empty content
      // required to get a timeline-style visualization
      for (let i = 0; i < 10; i++) {
        this.topicMessages[t].push({ date: null, topic: null, messageNumber: 0, message: null });
      }

      this.socketClient.onMessage(t).subscribe((msg: any) => {
        console.log('received msg on topic', t, msg);

        const newMsg = { date: new Date(), topic: t, messageNumber: ++this.messageCount, message: msg} as MessageContainer;
        this.storeMessage(t, newMsg);
      });
    });
  }

  storeMessage(t: string, newMsg: MessageContainer): any {
    let archiveCandidates: MessageContainer[] = [];

    this.topics.forEach(innerTopic => {
      if (t === innerTopic) {
        this.topicMessages[innerTopic].push(newMsg);
        this.timeStamps.push(newMsg.date.toISOString());
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
  }

  retrieveArchivedMessages(): any {
    this.http.get<MessageContainer[]>(environment.archiveUrl).subscribe(msgs => {
      msgs.forEach(m => {
        if (this.topics.indexOf(m.topic) >= 0) {
          if (typeof(m.date) === 'string') {
            m.date = new Date(m.date);
          }
          m.messageNumber = ++this.messageCount;
          this.storeMessage(m.topic, m);
        }
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
