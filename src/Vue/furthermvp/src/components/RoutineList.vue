<template>
  <div class="routine-list">
    <div v-if="!routines.length">No routines found.</div>
    <RoutineCard
      v-for="routine in routines"
      :key="routine.routineId"
      :routine="routine"
      :today-routine-id="todayRoutineId"
      :user-id="userId"
      :highlighted="isFastest(routine.routineId)"
      @refresh-today-routine="handleRefreshTodayRoutine"
      @delete-routine="removeRoutine"
    />
    <!-- Debug info for each card -->
    <div
      v-for="routine in routines"
      :key="'debug-' + routine.routineId"
      style="font-size:12px;color:#888;"
    >
      Card: "{{ routine.routineName }}" | Fastest: "{{ fastestGoal }}"
    </div>
  </div>
</template>

<script>
import axios from "axios";
import RoutineCard from "./RoutineCard.vue";
import store from "@/stores/index";
const API_URL = import.meta.env.VITE_API_URL;

export default {
  name: "RoutineList",
  components: { RoutineCard },
  data() {
    return {
      routines: [],
      todayRoutineId: null,
      userId: store.state.user.id,
      fastestGoal: null,
    };
  },
  methods: {
    isFastest(routineId) {
      if (!routineId || !this.fastestGoal) return false;
      return Number(routineId) === Number(this.fastestGoal);
    },
    async fetchRoutines() {
      const response = await axios.get(`${API_URL}/routine/user/${this.userId}`);
      let data = Array.isArray(response.data) ? response.data : [response.data];
      this.routines = data.map((routine) => ({
        routineId: Number(routine.routineId || routine.routine_id),
        userId: routine.userId || routine.user_id,
        routineName: routine.routineName || routine.routine_name,
        startTime: routine.startTime || routine.start_time,
        endTime: routine.endTime || routine.end_time,
        goal1: routine.goal1 || routine.goal_1,
        goal2: routine.goal2 || routine.goal_2,
        goal3: routine.goal3 || routine.goal_3,
        goal4: routine.goal4 || routine.goal_4,
        goal5: routine.goal5 || routine.goal_5,
        routineDtId: routine.routineDtId || routine.routine_dt_id,
      }));
      console.log("Mapped routines:", this.routines.map(r => r.routineName));
      await this.fetchTodayRoutineId();
      await this.fetchOptimizedOrder();
    },
    async fetchTodayRoutineId() {
      const today = new Date();
      const localDate = today.getFullYear() + '-' +
        String(today.getMonth() + 1).padStart(2, '0') + '-' +
        String(today.getDate()).padStart(2, '0');
      try {
        const res = await axios.get(`${API_URL}/routine-dt/user/${this.userId}/date/${localDate}`);
        this.todayRoutineId = res.data ? Number(res.data.routineId || res.data.routine_id) : null;
      } catch (error) {
        this.todayRoutineId = null;
      }
    },
    async fetchOptimizedOrder() {
      if (!this.routines.length) return;
      const todayRoutineIds = this.routines.map(r => r.routineId);
      console.log("Sending to optimize-goal-order:", todayRoutineIds);
      try {
        const res = await axios.post(
          `${API_URL}/routine-dt/optimize-goal-order?userId=${this.userId}`,
          todayRoutineIds
        );
        console.log("Optimized order from backend:", res.data);
        this.fastestGoal = res.data && res.data.length ? res.data[0] : null;
        console.log("fastestGoal set to:", this.fastestGoal);
      } catch (error) {
        this.fastestGoal = null;
      }
    },
    removeRoutine(routineId) {
      axios
        .delete(`${API_URL}/routine/delete/${routineId}`)
        .then(() => {
          this.fetchRoutines();
        })
        .catch((error) => console.error("Error deleting routine:", error));
    },
    async handleRefreshTodayRoutine() {
      await this.fetchTodayRoutineId();
      await this.fetchOptimizedOrder();
    }
  },
  mounted() {
    this.fetchRoutines();
  },
};
</script>

<style scoped>
.routine-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  justify-items: center;
}

@media (max-width: 768px) {
  .routine-list {
    grid-template-columns: 1fr;
  }
}
</style>
