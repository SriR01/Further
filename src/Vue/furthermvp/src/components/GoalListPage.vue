<template>
  <div class="container">
    <h1>Goal List</h1>
    <h3>Create goals that attribute to your <b>daily</b> success</h3>
    <form @submit.prevent="submitForm">
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
              v-model="todo.goalType"
              placeholder="Enter area of improvement"
            />
            <input
              class="input"
              type="date"
              v-model="todo.dateCreated"
              placeholder="Date created"
              readonly
            />
          </div>
          <div class="goal-actions">
            <label>
              <input type="checkbox" v-model="todo.private" /> Private
            </label>
            <button v-if="!todo.goalId" @click="saveTodo(index)">Save</button>
            <button v-else @click="updateTodo(index, todo.goalId)">Save</button>
            <button class="delete" @click="deleteTodo(index, todo.goalId)">
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
      const currentDate = new Date().toISOString().split("T")[0];
      const newTodo = {
        goal: "",
        goalType: "",
        dateCreated: currentDate,
        private: false,
        userId: this.userId,
      };
      this.todos.push(newTodo);
    },
    saveTodo(index) {
      const todo = this.todos[index];
      if (todo.goal && todo.goalType) {
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
    deleteTodo(index, goalId) {
      axios
        .delete(`${API_URL}/goals/delete/${goalId}`)
        .then((response) => {
          this.todos.splice(index, 1);
          console.log(response.data);
          window.location.reload();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    updateTodo(index, goalId) {
      const updatedTodo = this.todos[index];
      if (updatedTodo.goal && updatedTodo.goalType) {
        axios
          .put(`${API_URL}/goals/${goalId}`, updatedTodo)
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
    submitForm() {
      console.log("Submitting form");
      // No form-level action currently
    },
    fetchUserTodos() {
      axios
        .get(`${API_URL}/goals/user/${this.userId}`)
        .then((response) => {
          this.todos = response.data.map((todo) => ({
            ...todo,
            goalId: todo.goalId,
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
