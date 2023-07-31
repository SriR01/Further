<template>
  <div class="routine-list">
    <RoutineCard
      v-for="routine in routines"
      :key="routine.routine_id"
      :routine="routine"
      :today-routine-id="todayRoutineId"
      @delete-routine="removeRoutine"
      @set-today-routine="setTodayRoutine"
    />
  </div>
</template>

<script>
import axios from "axios";
import store from "@/store/index";
import RoutineCard from "./RoutineCard.vue";

export default {
  components: {
    RoutineCard,
  },
  data() {
    return {
      routines: [],
      todayRoutineId: null,
      userId: store.state.user.id,
    };
  },
  methods: {
    fetchRoutines() {
      const userId = this.userId;
      axios
        .get(`/routine/user/${userId}`)
        .then((response) => {
          this.routines = response.data.map((routine) => ({
            ...routine,
            goals: [
              routine.goal_1,
              routine.goal_2,
              routine.goal_3,
              routine.goal_4,
              routine.goal_5,
            ],
          }));
        })
        .catch((error) => {
          console.error("Error fetching routines:", error);
        });
    },
    removeRoutine(routineId) {
      axios
        .delete(`/routine/delete/${routineId}`)
        .then(() => {
          this.fetchRoutines();
        })
        .catch((error) => {
          console.error("Error deleting routine:", error);
        });
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
}

@media (max-width: 768px) {
  .routine-list {
    grid-template-columns: 1fr;
  }
}
</style>
