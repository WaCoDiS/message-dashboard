import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {
  MatInputModule,
  MatRippleModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatChipsModule,
  MatDividerModule,
  MatCardModule,
  MatToolbarModule,
  MatTabsModule,
  MatSelectModule,
  MatFormFieldModule
} from '@angular/material';

import {PlatformModule} from '@angular/cdk/platform';
import {ObserversModule} from '@angular/cdk/observers';
import { FormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HighlightModule, HIGHLIGHT_OPTIONS } from 'ngx-highlightjs';
 
import json from 'highlight.js/lib/languages/json';

import { AppComponent } from './app.component';
import { SocketClientService } from './socket-client.service';
import { MessageViewComponent, FilteredTopicsPipe } from './message-view/message-view.component';
import { HttpClientModule } from '@angular/common/http';



/**
 * Import every language you wish to highlight here
 * NOTE: The name of each language must match the file name its imported from
 */
export function getHighlightLanguages() {
  return {
    json: () => import('highlight.js/lib/languages/json')
  };
}

@NgModule({
  declarations: [
    AppComponent,
    MessageViewComponent,
    FilteredTopicsPipe
  ],
  imports: [
    BrowserModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatRippleModule,
    MatChipsModule,
    MatDividerModule,
    MatCardModule,
    MatSelectModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatTabsModule,
    ObserversModule,
    PlatformModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    FlexLayoutModule,
    HighlightModule
  ],
  providers: [
    SocketClientService,
    {
      provide: HIGHLIGHT_OPTIONS,
      useValue: {
        languages: getHighlightLanguages()
      }
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
