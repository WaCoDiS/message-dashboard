export const environment = {
  production: true,
  api: '/gs-guide-websocket',
  archiveUrl: '/archive',
  topics: [
    '/topic/wacodis.prod.jobs.new',
    '/topic/wacodis.prod.jobs.status',
    '/topic/wacodis.prod.jobs.deleted',
    '/topic/wacodis.prod.tools.execute',
    '/topic/wacodis.prod.tools.finished',
    '/topic/wacodis.prod.tools.failure',
    '/topic/wacodis.prod.data.available',
    '/topic/wacodis.prod.data.accessible'
  ]
};
