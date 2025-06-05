<template>
  <div
    :class="[
      'routine-card',
      { 'active-routine': routine.routineId === todayRoutineId },
      {
        'dulled-out':
          routine.routineId !== todayRoutineId && todayRoutineId !== null,
      },
    ]"
  >
    <div class="routine-card-header">
      <h3>{{ routine.routineName }}</h3>
    </div>
    <div class="routine-card-body">
      <p>Start Time: {{ formatTime(routine.startTime) }}</p>
      <p>End Time: {{ formatTime(routine.endTime) }}</p>
      <ul class="routine-card-goals">
        <li v-for="(goal, idx) in routineGoals" :key="idx">{{ goal }}</li>
      </ul>
      <div class="routine-card-actions">
        <button class="delete-button" @click.stop="deleteRoutineCard">
          Delete
        </button>
        <button
          v-if="routine.routineId !== todayRoutineId"
          class="today-button"
          @click.stop="setAsTodayRoutine"
        >
          Today's Routine
        </button>
        <button
          v-else
          class="back-button"
          @click.stop="unsetTodayRoutine"
        >
          Back
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const API_URL = import.meta.env.VITE_API_URL;

export default {
  props: {
    routine: { type: Object, required: true },
    todayRoutineId: { type: [Number, null], default: null }
  },
  computed: {
    routineGoals() {
      return [
        this.routine.goal1,
        this.routine.goal2,
        this.routine.goal3,
        this.routine.goal4,
        this.routine.goal5,
      ].filter(Boolean);
    }
  },
  methods: {
    formatTime(time) {
      const date = new Date(time);
      let hours = date.getHours();
      const minutes = date.getMinutes();
      const ampm = hours >= 12 ? "PM" : "AM";
      hours %= 12;
      hours = hours ? hours : 12;
      return `${hours}:${minutes.toString().padStart(2, "0")} ${ampm}`;
    },
    deleteRoutineCard() {
      this.$emit("delete-routine", this.routine.routineId);
    },
    async setAsTodayRoutine() {
      if (this.routine.routineId !== this.todayRoutineId) {
        const today = new Date().toISOString().split("T")[0]; // "2025-06-06"
        try {
          await axios.post(`${API_URL}/calendardt/set-today`, {
            userId: this.routine.userId,
            date: today,
            routineId: this.routine.routineId
          });
          await axios.post(`${API_URL}/routine-dt/set-today`, {
            userId: this.routine.userId,
            date: today,
            routineId: this.routine.routineId
          });
          this.$emit("set-today-routine", this.routine.routineId);
        } catch (error) {
          alert("Failed to set today's routine.");
          console.error(error);
        }
      }
    },
    unsetTodayRoutine() {
      this.$emit("set-today-routine", null);
    }
  }
};
</script>

<style scoped>
.routine-card {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin-bottom: 20px;
  transition: box-shadow 0.3s;
  cursor: pointer;
}

.routine-card.active-routine {
  border: 2px solid #1abc9c;
}

.routine-card.dulled-out {
  opacity: 0.5;
  pointer-events: none;
  border: none;
}

.routine-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.routine-card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.routine-card h3 {
  margin: 0;
  font-size: 24px;
  color: #333;
}

.routine-card-body {
  margin-top: 10px;
}

.routine-card-goals {
  list-style: none;
  padding-left: 0;
}

.routine-card-goals li {
  margin-bottom: 5px;
  color: #555;
}

.routine-card-goals input[type="checkbox"] {
  margin-right: 5px;
}

.routine-card-tracking {
  width: 100%;
  height: 10px;
  background-color: #f2f2f2;
  border-radius: 5px;
  overflow: hidden;
  margin-top: 10px;
}

.routine-card-tracking-bar {
  height: 100%;
  background-color: #1abc9c;
  border-radius: 5px;
  transition: width 0.3s;
}

.routine-card-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.delete-button {
  background-color: #ff4136;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
}

.today-button {
  background-color: #0074d9;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
}

.back-button {
  background-color: #ff4136;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  opacity: 0.7;
}

@media (max-width: 768px) {
  .routine-card {
    padding: 16px;
    margin-bottom: 16px;
  }

  .routine-card-header {
    margin-bottom: 8px;
  }

  .routine-card h3 {
    font-size: 20px;
  }

  .routine-card-progress {
    height: 6px;
  }

  .routine-card-progress-bar {
    border-radius: 3px;
  }

  .routine-card-goals li {
    font-size: 14px;
  }

  .routine-card-tracking {
    height: 6px;
  }
}
</style>
