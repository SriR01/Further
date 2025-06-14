<template>
  <div class="container">
    <h1>Goal List</h1>
    <h3>Create goals that attribute to your <b>daily</b> success</h3>
    <form @submit.prevent>
      <div class="goal-cards">
        <div v-for="(todo, index) in todos" :key="index" class="goal-card">
          <div class="goal-info">
            <input
              class="input input-large"
              type="text"
              v-model="todo.goal"
              placeholder="Enter goal"
            />
            <input
              class="input input-large"
              type="text"
              v-model="todo.goal_type"
              placeholder="Enter area of improvement"
            />
            <input
              class="input"
              type="date"
              v-model="todo.date_created"
              placeholder="Date created"
              readonly
            />
          </div>
          <div class="goal-actions">
            <label>
              <input type="checkbox" v-model="todo.private_goal" /> Private
            </label>
            <button v-if="!todo.goal_id" @click="saveTodo(index)">Save</button>
            <button v-else @click="updateTodo(index, todo.goal_id)">Save</button>
            <button class="delete" @click="deleteTodo(index, todo.goal_id)">
              Delete
            </button>
          </div>
        </div>
      </div>
      <button @click="addTodo">Add Goal</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import store from "@/stores/index";
const API_URL = import.meta.env.VITE_API_URL;

export default {
  data() {
    return {
      todos: [],
      userId: null,
    };
  },
  methods: {
    addTodo() {
      if (!this.userId) {
        alert("User not found. Please log in.");
        return;
      }
      const currentDate = new Date().toISOString().split("T")[0];
      const newTodo = {
        goal: "",
        goal_type: "",
        date_created: currentDate,
        private_goal: false,
        user_id: this.userId,
      };
      this.todos.push(newTodo);
    },
    saveTodo(index) {
      const todo = this.todos[index];
      if (todo.goal && todo.goal_type) {
        axios
          .post(`${API_URL}/goals`, todo)
          .then((response) => {
            this.todos[index] = response.data;
            window.location.reload();
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        console.error("Please fill in all fields.");
      }
    },
    updateTodo(index, goal_id) {
      const updatedTodo = this.todos[index];
      if (updatedTodo.goal && updatedTodo.goal_type) {
        axios
          .put(`${API_URL}/goals/${goal_id}`, updatedTodo)
          .then((response) => {
            console.log(response.data);
            window.location.reload();
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        console.error("Please fill in all fields.");
      }
    },
    deleteTodo(index, goal_id) {
      axios
        .delete(`${API_URL}/goals/delete/${goal_id}`)
        .then((response) => {
          this.todos.splice(index, 1);
          console.log(response.data);
          window.location.reload();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    fetchUserTodos() {
      axios
        .get(`${API_URL}/goals/user/${this.userId}`)
        .then((response) => {
          this.todos = response.data.map((todo) => ({
            goal_id: todo.goalId ?? todo.goal_id,
            goal: todo.goal,
            goal_type: todo.goal_type,
            date_created: todo.date_created,
            private_goal: todo.privateGoal ?? todo.private_goal,
            user_id: todo.user_id,
          }));
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  mounted() {
    this.userId = store.state.user?.id;
    if (this.userId) {
      this.fetchUserTodos();
    } else {
      console.warn("User ID not found in store.");
    }
  },
};
</script>

<style>
.container {
  padding: 20px;
}

h1 {
  margin: 20px;
}

.goal-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-bottom: 20px;
}

.goal-card {
  width: 400px;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  border: 1px solid black;
}

.input {
  width: 100%;
  height: 30px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 3px;
  margin-bottom: 10px;
}

.input-large {
  height: 40px;
}

.goal-info {
  margin-bottom: 10px;
}

.goal-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

button {
  padding: 8px 16px;
  background-color: #4caf50;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #45a049;
}

button:active {
  background-color: #3c903d;
}

label {
  display: flex;
  align-items: center;
  font-size: 14px;
}

input[type="checkbox"] {
  margin-right: 5px;
}

.delete {
  background-color: #e74c3c;
}

.delete:hover {
  background-color: #c93d30;
}

.delete:active {
  background-color: #a83228;
}
</style>
