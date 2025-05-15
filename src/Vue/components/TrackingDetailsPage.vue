<template>
  <div class="container">
    <div class="card">
      <h4>Start Routine to begin tracking,</h4>
      <button v-if="areNoGoalsMarked" @click="startRoutine">
        Start Routine
      </button>
      <button v-else class="update-button" @click="updateRoutine">
        Update Routine
      </button>
      <h4>Track goal completion and Update Routine</h4>
      <h2 class="card-title">Routine Details</h2>
      <div class="card-body">
        <div class="detail-row">
          <div class="detail-item">
            <h5>Date</h5>
            <p>{{ parsedDate }}</p>
          </div>
          <div class="detail-item">
            <h5>Start Time</h5>
            <p>{{ getFormattedTime(trackingData.start_time) }}</p>
          </div>
          <div class="detail-item">
            <h5>End Time</h5>
            <p>{{ getFormattedTime(trackingData.end_time) }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="card goals-card">
      <h5 class="card-title">Goals</h5>
      <div class="goals">
        <div :class="['goal-box', { completed: routineDt.goal_1_flag }]">
          <p class="goal-text">Goal 1: {{ trackingData.goal_1 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_1_flag"
              @change="
                updateGoalFlagTime('goal_1_flag_time', routineDt.goal_1_flag)
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_1_flag_time) }}
          </p>
        </div>
        <div :class="['goal-box', { completed: routineDt.goal_2_flag }]">
          <p class="goal-text">Goal 2: {{ trackingData.goal_2 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_2_flag"
              @change="
                updateGoalFlagTime('goal_2_flag_time', routineDt.goal_2_flag)
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_2_flag_time) }}
          </p>
        </div>
        <div :class="['goal-box', { completed: routineDt.goal_3_flag }]">
          <p class="goal-text">Goal 3: {{ trackingData.goal_3 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_3_flag"
              @change="
                updateGoalFlagTime('goal_3_flag_time', routineDt.goal_3_flag)
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_3_flag_time) }}
          </p>
        </div>
        <div :class="['goal-box', { completed: routineDt.goal_4_flag }]">
          <p class="goal-text">Goal 4: {{ trackingData.goal_4 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_4_flag"
              @change="
                updateGoalFlagTime('goal_4_flag_time', routineDt.goal_4_flag)
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_4_flag_time) }}
          </p>
        </div>
        <div :class="['goal-box', { completed: routineDt.goal_5_flag }]">
          <p class="goal-text">Goal 5: {{ trackingData.goal_5 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_5_flag"
              @change="
                updateGoalFlagTime('goal_5_flag_time', routineDt.goal_5_flag)
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_5_flag_time) }}
          </p>
        </div>
      </div>
    </div>
    <div
      class="card won-day-card"
      :class="{
        'won-day-win': routineDt.win_flag,
        'won-day-loss': !routineDt.win_flag,
      }"
    >
      <h5 class="card-title">Won the Day?</h5>
      <div class="card-body">
        <p class="won-day-text">{{ routineDt.win_flag ? "Win" : "Loss" }}</p>
      </div>
    </div>
    <h1>Daily Journal</h1>
    <div class="card journal-card">
      <h2 class="card-title">New Journal Entry</h2>
      <div class="card-body">
        <div
          v-for="(journal, index) in journals"
          :key="index"
          class="journal-entry"
        >
          <form @submit.prevent="saveJournal(index)">
            <div class="form-group">
              <label for="entrySubject">Subject</label>
              <input
                type="text"
                class="form-control"
                v-model="journal.entry_subject"
                required
              />
            </div>
            <div class="form-group">
              <label for="entryData">Entry</label>
              <textarea
                class="form-control"
                rows="3"
                v-model="journal.entry_data"
                required
              ></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Save Journal</button>
          </form>
        </div>
      </div>
      <JournalCard :journal="journalData" v-if="journalData" />
      <button
        v-if="journalData"
        type="button"
        class="btn btn-danger"
        @click="deleteJournalEntry(journalData.entry_id)"
      >
        Delete Journal
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import store from "@/store/index";
import moment from "moment";
import JournalCard from "@/components/JournalCard.vue";

export default {
  components: {
    JournalCard,
  },
  data() {
    return {
      userId: store.state.user.id,
      trackingData: {},
      journals: [
        {
          entry_subject: "",
          entry_data: "",
          entry_date: "",
        },
      ],
      journalData: null, // Add this line
      routineDt: {
        routine_id: "",
        user_id: store.state.user.id,
        date_current: this.parsedDate,
        goal_1_flag: false,
        goal_2_flag: false,
        goal_3_flag: false,
        goal_4_flag: false,
        goal_5_flag: false,
        goal_1_flag_time: "",
        goal_2_flag_time: "",
        goal_3_flag_time: "",
        goal_4_flag_time: "",
        goal_5_flag_time: "",
        win_flag: false,
        win_flag_time: "",
      },
    };
  },
  computed: {
    parsedDate() {
      return this.getParsedDate();
    },
    areNoGoalsMarked() {
      return (
        !this.routineDt.goal_1_flag &&
        !this.routineDt.goal_2_flag &&
        !this.routineDt.goal_3_flag &&
        !this.routineDt.goal_4_flag &&
        !this.routineDt.goal_5_flag
      );
    },
  },
  methods: {
    getParsedDate() {
      if (this.$route.params.calendarDt) {
        try {
          const calendarDt = JSON.parse(this.$route.params.calendarDt);
          const date = calendarDt.dates ? new Date(calendarDt.dates) : null;
          return date ? date.toISOString().split("T")[0] : "";
        } catch (error) {
          console.error("Failed to parse calendarDt:", error);
        }
      }
      console.log("Empty Date");
      return "";
    },
    async fetchRoutineDtData(userId, date) {
      const endpoint = `http://localhost:9000/routine-dt/${userId}/${date}`;
      try {
        const response = await axios.get(endpoint);
        const routineDt = response.data;
        if (routineDt != "") {
          this.routineDt = routineDt;
        }
      } catch (error) {
        console.error("Failed to fetch routineDt data:", error);
      }
    },
    async saveJournal(index) {
      const calendarDt = JSON.parse(this.$route.params.calendarDt);
      const date = calendarDt.dates ? new Date(calendarDt.dates) : null;
      try {
        const response = await axios.post("http://localhost:9000/journals", {
          user_id: this.userId,
          entry_subject: this.journals[index].entry_subject,
          entry_data: this.journals[index].entry_data,
          entry_date: date,
        });
        console.log("Journal entry saved:", response.data);
        window.location.reload();
      } catch (error) {
        console.error("Failed to save journal entry:", error);
      }
    },
    async startRoutine() {
      const parsedDate = this.getParsedDate();
      if (parsedDate !== "") {
        this.routineDt.date_current = parsedDate;
        try {
          const response = await axios.post(
            "http://localhost:9000/routine-dt",
            this.routineDt
          );
          console.log("Routine started:", response.data);
          this.routineDt.routine_id = response.data.routine_id;
        } catch (error) {
          console.error("Failed to start routine:", error);
        }
      } else {
        console.error("Failed to parse date. Invalid format.");
      }
    },
    async updateRoutine() {
      try {
        const formatTime = (time) => moment(time).toISOString();

        const requestData = {
          ...this.routineDt,
          goal_1_flag_time: formatTime(this.routineDt.goal_1_flag_time),
          goal_2_flag_time: formatTime(this.routineDt.goal_2_flag_time),
          goal_3_flag_time: formatTime(this.routineDt.goal_3_flag_time),
          goal_4_flag_time: formatTime(this.routineDt.goal_4_flag_time),
          goal_5_flag_time: formatTime(this.routineDt.goal_5_flag_time),
          win_flag_time: formatTime(this.routineDt.win_flag_time),
        };

        const response = await axios.put(
          "http://localhost:9000/routine-dt",
          requestData
        );
        console.log("Routine updated:", response.data);
      } catch (error) {
        console.error("Failed to update routine:", error);
      }
    },
    updateGoalFlagTime(goalFlagTimesKey, goalFlag) {
      if (goalFlag) {
        const currentTime = new Date().toLocaleString();
        this.routineDt[goalFlagTimesKey] = currentTime;
      } else {
        this.routineDt[goalFlagTimesKey] = "";
      }

      const checkedGoals = Object.values(this.routineDt).filter(
        (value) => value === true
      ).length;

      if (checkedGoals >= 3) {
        this.routineDt.win_flag = true;
        if (!this.routineDt.win_flag_time) {
          this.routineDt.win_flag_time = new Date().toLocaleString();
        }
      } else {
        this.routineDt.win_flag = false;
        this.routineDt.win_flag_time = "";
      }
    },
    async fetchJournalData() {
      try {
        const formattedDate = moment(this.getParsedDate()).format("YYYY-MM-DD");
        const response = await axios.get(
          `http://localhost:9000/journals/${this.userId}/${formattedDate}`
        );
        this.journalData = response.data;
      } catch (error) {
        console.error("Failed to fetch journal data:", error);
      }
    },
    async fetchRoutineData(routineId) {
      try {
        const response = await axios.get(
          `http://localhost:9000/routine/${routineId}`
        );
        this.trackingData = response.data;
      } catch (error) {
        console.error("Failed to fetch routine data:", error);
      }
    },
    getFormattedTime(time) {
      const formattedTime = new Date(time).toLocaleTimeString([], {
        hour: "2-digit",
        minute: "2-digit",
      });
      return formattedTime;
    },
    async deleteJournalEntry(entryId) {
      try {
        const response = await axios.delete(
          `http://localhost:9000/journals/delete/${entryId}`
        );
        console.log("Journal entry deleted:", response.data);

        // Remove the routine card from the HTML
        this.journalData = null;
      } catch (error) {
        console.error("Failed to delete journal entry:", error);
      }
    },
  },
  mounted() {
    if (this.$route.params.calendarDt) {
      try {
        const calendarDt = JSON.parse(this.$route.params.calendarDt);
        const routineId = calendarDt.routine_id;
        const currentDate = new Date().toISOString().split("T")[0];

        // Update routineId and date_current in routineDt
        this.routineDt.routine_id = routineId;
        this.routineDt.date_current = currentDate;

        // Fetch routine data
        this.fetchRoutineData(routineId);

        // Fetch journal data
        this.fetchJournalData();

        this.fetchRoutineDtData(this.userId, this.parsedDate);
      } catch (error) {
        console.error("Failed to parse calendarDt:", error);
      }
    } else {
      console.log("calendarDt is undefined");
    }
  },
};
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 100vh;
  background-color: #f2f2f2;
}

.card {
  width: 600px;
  margin-bottom: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 18px;
  font-weight: bold;
  padding: 10px;
  background-color: #f5f5f5;
  border-bottom: 1px solid #ddd;
  margin: 0;
}

.card-body {
  padding: 20px;
}

.detail-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.detail-item {
  flex: 1;
  text-align: center;
}

.goal-box {
  padding: 10px;
  border-top: 1px solid #ddd;
}

.goal-box h6 {
  margin: 0;
}

.won-day-card {
  width: 400px;
  margin-bottom: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.won-day-text {
  font-size: 18px;
  font-weight: bold;
  text-align: center;
  margin: 0;
}

.journal-card {
  width: 800px;
  margin-bottom: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.journal-entry {
  margin-bottom: 10px;
}

.loading-message {
  text-align: center;
}

@media (max-width: 1400px) {
  .container {
    padding: 20px;
  }

  .card,
  .won-day-card,
  .journal-card {
    width: 100%;
  }
}

.goal-box {
  padding: 10px;
  border-top: 1px solid #ddd;
}

.goal-box.completed {
  background-color: green;
}

.goal-box:not(.completed) {
  background-color: red;
}
.goal-text {
  color: black;
  font-weight: bold;
}

.won-day-win {
  background-color: green;
}

.won-day-loss {
  background-color: red;
}

.won-day-text {
  /* ...existing styles... */
  color: white;
  font-weight: bold;
}

.start-button,
.update-button {
  padding: 10px 20px;
  font-size: 16px;
  font-weight: bold;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 20px;
}

.update-button {
  background-color: #f44336;
}
</style>
