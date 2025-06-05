<template>
  <div class="routine-list">
    <div v-if="!routines.length">No routines found.</div>
    <RoutineCard
      v-for="routine in routines"
      :key="routine.routineId"
      :routine="routine"
      :today-routine-id="todayRoutineId"
      @set-today-routine="setTodayRoutine"
      @delete-routine="removeRoutine"
    />
  </div>
</template>

<script>
import axios from "axios";
import RoutineCard from "./RoutineCard.vue";
import store from "@/stores/index"; // adjust as needed
const API_URL = import.meta.env.VITE_API_URL;

export default {
  name: "RoutineList",
  components: {
    RoutineCard,
  },
  data() {
    return {
      routines: [],
      todayRoutineId: null,
      userId: store.state.user.id, // ensure your store has the user id
    };
  },
  methods: {
    fetchRoutines() {
      axios
        .get(`${API_URL}/routine/user/${this.userId}`)
        .then((response) => {
          // Wrap in an array if a single object is returned
          let data = Array.isArray(response.data) ? response.data : [response.data];
          this.routines = data.map((routine) => ({
            routineId: routine.routineId || routine.routine_id,
            userId: routine.userId || routine.user_id,
            routineName: routine.routineName || routine.routine_name,
            startTime: routine.startTime || routine.start_time,
            endTime: routine.endTime || routine.end_time,
            goal1: routine.goal1 || routine.goal_1,
            goal2: routine.goal2 || routine.goal_2,
            goal3: routine.goal3 || routine.goal_3,
            goal4: routine.goal4 || routine.goal_4,
            goal5: routine.goal5 || routine.goal_5,
          }));
        })
        .catch((error) => {
          console.error("Error fetching routines:", error);
        });
    },
    removeRoutine(routineId) {
      axios
        .delete(`${API_URL}/routine/delete/${routineId}`)
        .then(() => {
          this.fetchRoutines();
        })
        .catch((error) => console.error("Error deleting routine:", error));
    },
    setTodayRoutine(routineId) {
      this.todayRoutineId = routineId;
    },
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
