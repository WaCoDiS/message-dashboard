// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  api: 'http://localhost:8080/gs-guide-websocket',
  archiveUrl: 'http://localhost:8080/archive',
  topics: [
    '/topic/wacodis.test.jobs.new',
    '/topic/wacodis.test.jobs.status',
    '/topic/wacodis.test.jobs.deleted',
    '/topic/wacodis.test.tools.execute',
    '/topic/wacodis.test.tools.finished',
    '/topic/wacodis.test.tools.failure',
    '/topic/wacodis.test.data.available',
    '/topic/wacodis.test.data.accessible'
  ]
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
