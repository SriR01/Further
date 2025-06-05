import { createRouter, createWebHistory } from 'vue-router';

import FurtherMainPage from '../components/FurtherMainPage.vue';
import Login from '../components/Login.vue';
import GoalListPage from '../components/GoalListPage.vue';
import RoutinePage from '../components/RoutinePage.vue';
import CalendarPage from '../components/CalendarPage.vue';
import Logout from '../components/Logout.vue';
import Register from '../components/Register.vue';

const routes = [
  { path: '/', name: 'home', component: FurtherMainPage },
  { path: '/login', name: 'login', component: Login },
  { path: '/goal-list', name: 'goal-list', component: GoalListPage },
  { path: '/routine', name: 'routine', component: RoutinePage },
  { path: '/calendar', name: 'calendar', component: CalendarPage },
  { path: '/logout', name: 'logout', component: Logout },
  { path: '/register', name: 'register', component: Register },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;