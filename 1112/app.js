import List from './components/List.js';
import Regist from './components/Create.js';
import Read from './components/Read.js';

const router = new VueRouter({
  routes: [
    {
      path: '/',
      name: 'list',
      component: List,
    },
    {
      path: '/regist',
      name: 'regist',
      component: Regist,
    },
    {
      path: '/Read/:no',
      name: 'Read',
      component: Read,
    },
  ],
});

const app = new Vue({
  el: '#app',
  router,
});
