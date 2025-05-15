<template>
  <div>
    <div>
      <h1><b>Create a routine</b> to structure your daily success</h1>
      <h3>Select <b>Today's Routine</b> to track today's routine</h3>
    </div>
    <div class="routine-page">
      <div class="create-routine-card">
        <CreateRoutineCard />
      </div>
      <div class="routine-list">
        <RoutineList :routines="routines" @delete-routine="deleteRoutine" />
      </div>
    </div>
  </div>
</template>


<script>
import RoutineList from "@/components/RoutineList.vue";
import CreateRoutineCard from "@/components/CreateRoutineCard.vue";
import axios from "axios";

export default {
  components: {
    RoutineList,
    CreateRoutineCard,
  },
  data() {
    return {
      routines: [],
    };
  },
  methods: {
    async deleteRoutine(routineId) {
      try {
        await axios.delete(`http://localhost:9000/routine/delete/${routineId}`);
        this.$refs.routineList.removeRoutine(routineId);
      } catch (error) {
        alert("Routine can't be deleted");
        console.error("Error deleting routine:", error);
      }
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
