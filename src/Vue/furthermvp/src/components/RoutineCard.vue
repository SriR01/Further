<template>
  <div
    :class="[
      'routine-card',
      Number(routine.routineId) === Number(todayRoutineId) ? 'active-routine' : '',
      Number(routine.routineId) !== Number(todayRoutineId) && todayRoutineId !== null ? 'dulled-out' : '',
      highlighted ? 'fastest-routine' : '' // <-- highlight if fastest
    ]"
  >
    <div class="routine-card-header" :class="{ 'active-header': Number(routine.routineId) === Number(todayRoutineId) }">
      <h3>
        {{ routine.routineName }}
        <span v-if="Number(routine.routineId) === Number(todayRoutineId)" class="badge-today">Today's Routine</span>
        <span v-if="highlighted" class="badge-fastest">Fastest</span>
      </h3>
    </div>
    <div class="routine-card-body">
      <p><b>Start:</b> {{ formatTime(routine.startTime) }}</p>
      <p><b>End:</b> {{ formatTime(routine.endTime) }}</p>
      <ul class="routine-card-goals">
        <li v-for="(goal, idx) in routineGoals" :key="idx">
          <span class="goal-dot"></span>{{ goal }}
        </li>
      </ul>
      <div class="routine-card-actions">
        <button class="delete-button" @click.stop="deleteRoutineCard">
          Delete
        </button>
        <button
          v-if="Number(routine.routineId) !== Number(todayRoutineId)"
          class="today-button"
          @click.stop="setAsTodayRoutine"
        >
          Set as Today's Routine
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
    todayRoutineId: { type: [Number, null], default: null },
    userId: { type: [Number, String], required: true },
    highlighted: { type: Boolean, default: false } // <-- Add this prop
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
      if (!time) return "";
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
        const today = new Date();
        const localDate = today.getFullYear() + '-' +
          String(today.getMonth() + 1).padStart(2, '0') + '-' +
          String(today.getDate()).padStart(2, '0');
        try {
          await axios.post(`${API_URL}/calendardt/set-today`, {
            user_id: this.userId,
            date: localDate,
            routine_id: this.routine.routineId,
            start_time: this.routine.startTime,
            end_time: this.routine.endTime
          });
          console.log("Payload sent to /calendardt/set-today:", {
            userId: this.userId,
            date: localDate,
            routineId: this.routine.routineId
          });
          await axios.post(`${API_URL}/routine-dt/set-today`, {
            user_id: this.userId,
            date: localDate,
            routine_id: this.routine.routineId,
            routine_dt_id: this.routine.routineDtId,
            start_time: this.routine.startTime,
            end_time: this.routine.endTime
          });
          console.log(`Today's routine set to: ${this.routine.routineName} (ID: ${this.routine.routineId})`);
          this.$emit("refresh-today-routine");
          this.$emit("refresh-calendar");
        } catch (error) {
          alert("Failed to set today's routine.");
          console.error(error);
        }
      }
    },
    async unsetTodayRoutine() {
      const today = new Date();
      const localDate = today.getFullYear() + '-' +
        String(today.getMonth() + 1).padStart(2, '0') + '-' +
        String(today.getDate()).padStart(2, '0');
      try {
        await axios.put(`${API_URL}/calendardt/reset-routinedt`, null, {
          params: {
            userId: this.routine.userId,
            date: localDate
          }
        });
        console.log("todayRoutineId after reset:", this.todayRoutineId);
        this.$emit("refresh-today-routine");
        this.$emit("refresh-calendar");
      } catch (error) {
        alert("Failed to unset today's routine.");
        console.error(error);
      }
    }
  }
};
</script>

<style scoped>
.routine-card {
  background: #f9fafb;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.07);
  padding: 24px;
  margin-bottom: 24px;
  border: 2px solid #e0e0e0;
  transition: box-shadow 0.3s, border 0.3s, filter 0.3s, opacity 0.3s;
  position: relative;
}
.routine-card.active-routine {
  border: 2.5px solid #1abc9c;
  box-shadow: 0 4px 16px rgba(26,188,156,0.15);
  font-weight: bold;
  opacity: 1;
  filter: none;
}
.routine-card.dulled-out {
  opacity: 0.5;
  filter: grayscale(0.7);
  border: 2px solid #e0e0e0;
}
.routine-card.fastest-routine {
  border: 2.5px solid #ffb300;
  box-shadow: 0 0 12px #ffecb3;
  background: #fffde7;
}
.routine-card-header {
  padding: 12px 0 8px 0;
  border-radius: 8px 8px 0 0;
  transition: background 0.3s, color 0.3s;
}
.routine-card-header.active-header {
  background: #1abc9c;
  color: #fff;
}
.routine-card-header h3 {
  margin: 0;
  font-size: 1.5rem;
  display: flex;
  align-items: center;
  gap: 10px;
}
.badge-today {
  background: #fff;
  color: #1abc9c;
  border: 1px solid #1abc9c;
  border-radius: 8px;
  padding: 2px 10px;
  font-size: 0.9rem;
  margin-left: 10px;
}
.badge-fastest {
  background: #ffb300;
  color: #fff;
  border-radius: 8px;
  padding: 2px 10px;
  font-size: 0.9rem;
  margin-left: 10px;
  border: 1px solid #ffb300;
}
.routine-card-body {
  margin-top: 10px;
}
.routine-card-goals {
  list-style: none;
  padding-left: 0;
  margin: 10px 0;
}
.routine-card-goals li {
  margin-bottom: 6px;
  color: #555;
  display: flex;
  align-items: center;
}
.goal-dot {
  display: inline-block;
  width: 8px;
  height: 8px;
  background: #1abc9c;
  border-radius: 50%;
  margin-right: 8px;
}
.routine-card-actions {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 10px;
  margin-top: 18px;
}
.delete-button,
.today-button,
.back-button {
  padding: 8px 16px;
  border: 2px solid #bbb;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  font-size: 1rem;
  background: #fff;
  color: #333;
  transition: background 0.2s, color 0.2s, border 0.2s;
  outline: none;
}
.delete-button {
  border-color: #ff4136;
  color: #ff4136;
}
.today-button {
  border-color: #1abc9c;
  color: #1abc9c;
}
.back-button {
  border-color: #bdbdbd;
  color: #bdbdbd;
}
.delete-button:hover { background: #ff4136; color: #fff; }
.today-button:hover { background: #1abc9c; color: #fff; }
.back-button:hover { background: #bdbdbd; color: #fff; }
input[type="text"], input[type="date"] {
  border: 2px solid #1abc9c;
  border-radius: 4px;
  padding: 6px 10px;
  margin-bottom: 8px;
  font-size: 1rem;
  outline: none;
  background: #fff;
  color: #333;
  width: 100%;
  box-sizing: border-box;
  transition: border 0.2s;
}
input[type="text"]:focus, input[type="date"]:focus {
  border: 2px solid #159c85;
}
</style>
