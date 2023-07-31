<template>
  <div class="routine-card">
    <h1>Create a Routine</h1>
    <div :class="['routine-card-header', { selected: selected }]">
      <div class="title-container">
        <input
          type="text"
          v-model="routineName"
          class="routine-name-input"
          @input="routineName = $event.target.value"
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
                v-for="(todo, todoIndex) in todos"
                :key="todoIndex"
                :value="todo.goal"
              >
                {{ todo.goal }}
              </option>
            </select>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import store from "@/store/index";

export default {
  props: {
    initialRoutineName: {
      type: String,
      required: true,
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
      startTime: "",
      endTime: "",
      selected: false,
      todos: [],
    };
  },
  computed: {
    limitedGoals() {
      return this.goals.slice(0, 5);
    },
  },
  methods: {
    createRoutine() {
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
        goal_1: this.goals[0].goal,
        goal_2: this.goals[1].goal,
        goal_3: this.goals[2].goal,
        goal_4: this.goals[3].goal,
        goal_5: this.goals[4].goal,
      };

      axios
        .post("/routine", routine)
        .then(() => {
          console.log("Routine created successfully");
          window.location.reload();
        })
        .catch((error) => {
          console.error("Error creating routine:", error);
        });
    },
    fetchUserTodos() {
      const userId = this.userId;
      axios
        .get(`/goals/users/${userId}`)
        .then((response) => {
          this.todos = response.data.map((todo) => ({
            ...todo,
            goal_id: todo.goal_id,
          }));

          this.goals = this.todos.map((todo) => ({
            goal: "Select a goal",
            completed: todo.completed,
          }));
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  mounted() {
    this.fetchUserTodos();
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
</style>
