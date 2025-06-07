import { createRouter, createWebHistory } from 'vue-router';

import FurtherMainPage from '../components/FurtherMainPage.vue';
import Login from '../components/Login.vue';
import GoalListPage from '../components/GoalListPage.vue';
import RoutinePage from '../components/RoutinePage.vue';
import CalendarPage from '../components/CalendarPage.vue';
import Logout from '../components/Logout.vue';
import Register from '../components/Register.vue';
import TrackingDetailsPage from '../components/TrackingDetailsPage.vue';
import Profile from '../components/Profile.vue'; // <-- Add this import

const routes = [
  { path: '/', name: 'home', component: FurtherMainPage },
  { path: '/login', name: 'login', component: Login },
  { path: '/goal-list', name: 'goal-list', component: GoalListPage },
  { path: '/routine', name: 'routine', component: RoutinePage },
  { path: '/calendar', name: 'calendar', component: CalendarPage },
  { path: '/logout', name: 'logout', component: Logout },
  { path: '/register', name: 'register', component: Register },
  {
    path: '/tracking-details/:userId/:date',
    name: 'TrackingDetailsPage',
    component: TrackingDetailsPage,
    props: true,
  },
  {
    path: '/profile/:userId',
    name: 'Profile',
    component: Profile,
    props: route => ({ userId: Number(route.params.userId) }), // <-- fix here
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
