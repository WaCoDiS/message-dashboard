export const environment = {
  production: true,
  api: '/message-dashboard/gs-guide-websocket',
  archiveUrl: '/message-dashboard/archive',
  topics: [
    '/topic/wacodis.prod.jobs.new',
    '/topic/wacodis.prod.jobs.status',
    '/topic/wacodis.prod.tools.execute',
    '/topic/wacodis.prod.tools.finished',
    '/topic/wacodis.prod.data.available',
    '/topic/wacodis.prod.data.accessible'
  ]
};
