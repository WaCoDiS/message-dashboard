export const environment = {
  production: true,
  api: 'http://localhost:8080/gs-guide-websocket',
  topics: [
    '/topic/wacodis.prod.jobs.new',
    '/topic/wacodis.prod.jobs.status',
    '/topic/wacodis.prod.tools.execute',
    '/topic/wacodis.prod.tools.finished',
    '/topic/wacodis.prod.data.available',
    '/topic/wacodis.prod.data.accessible'
  ]
};
