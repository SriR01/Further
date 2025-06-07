<template>
  <div>
    <div>
      <h1><b>Create a routine</b> to structure your daily success</h1>
      <h3>Select <b>Today's Routine</b> to track today's routine</h3>
    </div>
    <div class="routine-page">
      <div class="create-routine-card">
        <CreateRoutineCard @routineCreated="refreshRoutines" />
      </div>
      <div class="routine-list">
        <RoutineList
          ref="routineList"
          :user-id="userId"
          @delete-routine="deleteRoutine"
        />
      </div>
    </div>
  </div>
</template>


<script>
import RoutineList from "@/components/RoutineList.vue";
import CreateRoutineCard from "@/components/CreateRoutineCard.vue";
const API_URL = import.meta.env.VITE_API_URL;

import axios from "axios";

export default {
  components: {
    RoutineList,
    CreateRoutineCard,
  },
  data() {
    return {
      routines: [],
      todayRoutineId: null,
    };
  },
  methods: {
    async deleteRoutine(routineId) {
      try {
        await axios.delete(`${API_URL}/routine/delete/${routineId}`);
        this.$refs.routineList.removeRoutine(routineId);
      } catch (error) {
        alert("Routine can't be deleted");
        console.error("Error deleting routine:", error);
      }
    },
    refreshRoutines() {
      // Call the fetchRoutines function defined on RoutineList via a template ref.
      this.$refs.routineList.fetchRoutines();
    },
    fetchRoutines() {
      console.log("Fetching routines for user:", this.userId, API_URL);
      axios
        .get(`${API_URL}/routine/user/${this.userId}`)
        .then((response) => {
          console.log("Routine response:", response.data);
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
          console.log("Mapped routines:", this.routines);
        })
        .catch((error) => {
          alert("Error fetching routines: " + error);
          console.error("Error fetching routines:", error);
        });
    },
    setTodayRoutine(routineId) {
      this.todayRoutineId = routineId;
      // Optionally, you can also navigate to the routine details or perform other actions
    },
  },
};
</script>

<style scoped>
.routine-page {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding: 40px;
}

.routine-list {
  flex: 1;
  max-width: 800px;
  margin-right: 40px;
}

.create-routine-card {
  max-width: 400px;
}

@media (max-width: 768px) {
  .routine-page {
    flex-direction: column;
    align-items: center;
  }

  .routine-list {
    margin-right: 0;
    margin-bottom: 40px;
  }
}
</style>
