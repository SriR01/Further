<template>
  <div class="routine-card">
    <h1>Create a Routine</h1>
    <div :class="['routine-card-header', { selected: selected }]">
      <div class="title-container">
        <input
          type="text"
          v-model="routineName"
          class="routine-name-input"
        />
      </div>
      <div class="button-container">
        <button @click="createRoutine" class="create-button">
          Create Routine
        </button>
      </div>
    </div>
    <div class="routine-card-body">
      <div class="time-inputs">
        <div class="input-group">
          <label for="start-time">Start Time:</label>
          <input
            type="time"
            v-model="startTime"
            id="start-time"
            class="time-text"
          />
        </div>
        <div class="input-group">
          <label for="end-time">End Time:</label>
          <input
            type="time"
            v-model="endTime"
            id="end-time"
            class="time-text"
          />
        </div>
      </div>
      <div class="goals-container">
        <div
          v-for="(goal, index) in limitedGoals"
          :key="index"
          class="goal-item"
        >
          <div class="goal-input">
            <label :for="'goal-' + (index + 1)">Goal {{ index + 1 }}:</label>
            <select
              v-model="goal.goal"
              :id="'goal-' + (index + 1)"
              class="goal-text"
            >
              <option value="Select a goal">Select a goal</option>
              <option
                v-for="(goalObj, goalIndex) in userGoals"
                :key="goalIndex"
                :value="goalObj.goal"
              >
                {{ goalObj.goal }}
              </option>
            </select>
          </div>
        </div>
      </div>
      <div v-if="successMessage" class="success-message">{{ successMessage }}</div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import store from "@/stores/index";
const API_URL = import.meta.env.VITE_API_URL;

export default {
  props: {
    initialRoutineName: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      routineName: this.initialRoutineName,
      userId: store.state.user.id,
      goals: [
        { goal: "Select a goal" },
        { goal: "Select a goal" },
        { goal: "Select a goal" },
        { goal: "Select a goal" },
        { goal: "Select a goal" },
      ],
      userGoals: [],
      startTime: "",
      endTime: "",
      selected: false,
      successMessage: "",
    };
  },
  computed: {
    limitedGoals() {
      return this.goals.slice(0, 5);
    },
  },
  methods: {
    createRoutine() {
      if (!this.startTime || !this.endTime) {
        alert("Please fill in both start and end times.");
        return;
      }

      const currentDate = new Date();
      const startTime = new Date(
        currentDate.getFullYear(),
        currentDate.getMonth(),
        currentDate.getDate(),
        parseInt(this.startTime.split(":")[0]),
        parseInt(this.startTime.split(":")[1])
      ).toISOString();

      const endTime = new Date(
        currentDate.getFullYear(),
        currentDate.getMonth(),
        currentDate.getDate(),
        parseInt(this.endTime.split(":")[0]),
        parseInt(this.endTime.split(":")[1])
      ).toISOString();

      const routine = {
        user_id: this.userId,
        routine_name: this.routineName,
        start_time: startTime,
        end_time: endTime,
        goal1: this.goals[0].goal,
        goal2: this.goals[1].goal,
        goal3: this.goals[2].goal,
        goal4: this.goals[3].goal,
        goal5: this.goals[4].goal,
      };

      axios
        .post(`${API_URL}/routine`, routine)
        .then(() => {
          this.successMessage = "Routine created and set as today!";
          this.$emit("routineCreated");
          setTimeout(() => { this.successMessage = ""; }, 3000); // Hide after 3s
        })
        .catch((error) => {
          console.error("Error creating routine:", error);
        });
    },
    fetchUserGoals() {
      if (!this.userId) {
        console.warn("User ID is not available.");
        return;
      }

      axios
        .get(`${API_URL}/goals/user/${this.userId}`)
        .then((response) => {
          this.userGoals = response.data;
        })
        .catch((error) => {
          console.error("Error fetching goals:", error);
        });
    },
  },
  mounted() {
    // Only fetch goals; routine fetching is handled elsewhere.
    this.fetchUserGoals();
  },
};
</script>

<style scoped>
.routine-card {
  background-color: #f8fafc;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  padding: 8px;
  margin-bottom: 16px;
  width: 75%;
}

.routine-card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.title-container {
  flex-grow: 1;
  background-color: #ffffff;
  border: 1px solid #cbd5e0;
  border-radius: 4px;
  padding: 8px;
  margin-right: 16px;
}

.routine-name-input {
  width: 100%;
  background-color: transparent;
  border: none;
  font-size: 18px;
  font-weight: bold;
  outline: none;
}

.button-container {
  display: flex;
  align-items: center;
}

.create-button {
  background-color: #38b2ac;
  color: #ffffff;
  border: none;
  border-radius: 4px;
  padding: 6px 12px;
  font-size: 14px;
  cursor: pointer;
}

.routine-card-body {
  position: relative;
}

.time-inputs {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.input-group {
  display: flex;
  align-items: center;
}

label {
  font-size: 16px;
  margin-right: 8px;
}

.time-text {
  width: 100%;
  padding: 6px;
  border: 1px solid #cbd5e0;
  border-radius: 4px;
  font-size: 16px;
}

.goals-container {
  position: relative;
}

.goal-item {
  display: flex;
  align-items: center;
  margin-bottom: 8px;
}

.goal-input {
  flex-grow: 1;
  margin-right: 8px;
}

.goal-text {
  width: 100%;
  padding: 6px;
  border: 1px solid #cbd5e0;
  border-radius: 4px;
  font-size: 16px;
}

.tracking-bar {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 0;
  height: 4px;
  background-color: #38b2ac;
  transition: width 0.3s ease;
}

.selected {
  border-color: #38b2ac;
}

.success-message {
  color: #38b2ac;
  font-weight: bold;
  margin-top: 10px;
}
</style>
