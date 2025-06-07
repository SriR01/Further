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
            <p>{{ getFormattedTime(routineDt.start_time) }}</p>
          </div>
          <div class="detail-item">
            <h5>End Time</h5>
            <p>{{ getFormattedTime(routineDt.end_time) }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="card goals-card">
      <h5 class="card-title">Goals</h5>
      <div class="goals" v-if="goalsLoaded">
        <div :class="['goal-box', { completed: routineDt.goal_1_flag }]">
          <p class="goal-text">Goal 1: {{ trackingData.goal1 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_1_flag"
              @change="
                updateGoalFlagTime('goal_1_flag_time', routineDt.goal_1_flag);
                updateRoutine();
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_1_flag_time) }}
          </p>
        </div>
        <div :class="['goal-box', { completed: routineDt.goal_2_flag }]">
          <p class="goal-text">Goal 2: {{ trackingData.goal2 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_2_flag"
              @change="
                updateGoalFlagTime('goal_2_flag_time', routineDt.goal_2_flag);
                updateRoutine();
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_2_flag_time) }}
          </p>
        </div>
        <div :class="['goal-box', { completed: routineDt.goal_3_flag }]">
          <p class="goal-text">Goal 3: {{ trackingData.goal3 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_3_flag"
              @change="
                updateGoalFlagTime('goal_3_flag_time', routineDt.goal_3_flag);
                updateRoutine();
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_3_flag_time) }}
          </p>
        </div>
        <div :class="['goal-box', { completed: routineDt.goal_4_flag }]">
          <p class="goal-text">Goal 4: {{ trackingData.goal4 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_4_flag"
              @change="
                updateGoalFlagTime('goal_4_flag_time', routineDt.goal_4_flag);
                updateRoutine();
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_4_flag_time) }}
          </p>
        </div>
        <div :class="['goal-box', { completed: routineDt.goal_5_flag }]">
          <p class="goal-text">Goal 5: {{ trackingData.goal5 }}</p>
          <p class="goal-text">
            Complete?:
            <input
              type="checkbox"
              v-model="routineDt.goal_5_flag"
              @change="
                updateGoalFlagTime('goal_5_flag_time', routineDt.goal_5_flag);
                updateRoutine();
              "
            />
          </p>
          <p class="goal-text">
            Completion Time: {{ getFormattedTime(routineDt.goal_5_flag_time) }}
          </p>
        </div>
      </div>
      <div v-else class="loading-message">Loading goals...</div>
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
    <button @click="handleBack">Back</button>
  </div>
</template>

<script>
import axios from "axios";
import store from "@/stores/index";
const API_URL = import.meta.env.VITE_API_URL;
import moment from "moment";
import JournalCard from "@/components/JournalCard.vue";

export default {
  components: {
    JournalCard,
  },
  data() {
    return {
      userId: this.$route.params.userId || store.state.user.id,
      trackingData: {},
      goalsLoaded: false,
      journals: [
        {
          entry_subject: "",
          entry_data: "",
          entry_date: "",
        },
      ],
      journalData: null,
      routineDt: {
        routine_id: "",
        user_id: this.$route.params.userId || store.state.user.id,
        date_current: this.parsedDate,
        start_time: "",
        end_time: "",
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
      return this.$route.params.date || "";
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
    async fetchRoutineDtData(userId, date) {
      const endpoint = `${API_URL}/routine-dt/user/${userId}/date/${date}`;
      try {
        const response = await axios.get(endpoint);
        const routineDt = response.data;
        if (routineDt) {
          Object.assign(this.routineDt, routineDt);
          if (routineDt.routine_id) {
            await this.fetchRoutineData(routineDt.routine_id);
          }
        }
      } catch (error) {
        console.error("Failed to fetch routineDt data:", error);
      }
    },
    async fetchRoutineData(routineId) {
      try {
        const response = await axios.get(
          `${API_URL}/routine/${routineId}`
        );
        this.trackingData = response.data; // from /routine/{routineId}
        this.goalsLoaded = true;
        // Debug log:
        console.log("Routine data loaded:", this.trackingData);
      } catch (error) {
        console.error("Failed to fetch routine data:", error);
        this.goalsLoaded = false;
      }
    },
    async saveJournal(index) {
      const date = this.parsedDate;
      try {
        const response = await axios.post(`${API_URL}/journals`, {
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
      const date = this.parsedDate;
      if (date !== "") {
        this.routineDt.date_current = date;
        try {
          const response = await axios.post(
            `${API_URL}/routine-dt`,
            this.routineDt
          );
          console.log("Routine started:", response.data);
          this.routineDt.routine_id = response.data.routine_id;
          // Fetch routine data after starting
          if (this.routineDt.routine_id) {
            await this.fetchRoutineData(this.routineDt.routine_id);
          }
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

        const routineDtId = this.routineDt.routine_dt_id; // or routineDtId, depending on your property name
        const response = await axios.put(
          `${API_URL}/routine-dt/${routineDtId}`,
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

      const checkedGoals = [
        this.routineDt.goal_1_flag,
        this.routineDt.goal_2_flag,
        this.routineDt.goal_3_flag,
        this.routineDt.goal_4_flag,
        this.routineDt.goal_5_flag,
      ].filter(Boolean).length;

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
        const formattedDate = this.parsedDate;
        const response = await axios.get(
          `${API_URL}/journals/${this.userId}/${formattedDate}`
        );
        this.journalData = response.data;
      } catch (error) {
        console.error("Failed to fetch journal data:", error);
      }
    },
    getFormattedTime(time) {
      if (!time) return "";
      const formattedTime = new Date(time).toLocaleTimeString([], {
        hour: "2-digit",
        minute: "2-digit",
      });
      return formattedTime;
    },
    async deleteJournalEntry(entryId) {
      try {
        const response = await axios.delete(
          `${API_URL}/journals/delete/${entryId}`
        );
        console.log("Journal entry deleted:", response.data);
        this.journalData = null;
      } catch (error) {
        console.error("Failed to delete journal entry:", error);
      }
    },
    async testFetchCalendarDt() {
      const userId = this.userId;
      const date = "2025-06-05";
      try {
        const response = await axios.get(
          `${API_URL}/calendardt/user/${userId}/date/${date}`
        );
        console.log("Fetched CalendarDt:", response.data);
      } catch (error) {
        console.error("Error fetching CalendarDt:", error);
      }
    },
    async resetRoutineDtForToday() {
      const today = new Date();
      const localDate = today.getFullYear() + '-' +
        String(today.getMonth() + 1).padStart(2, '0') + '-' +
        String(today.getDate()).padStart(2, '0');
      await axios.put(`${API_URL}/calendardt/reset-routinedt`, null, {
        params: {
          userId: this.userId,
          date: localDate
        }
      });
      // Optionally refresh routines/calendar
      this.$refs.routineList.fetchRoutines();
      this.$refs.calendar.fetchCalendarDts();
    },
    async handleBack() {
      // Reset the RoutineDt for this user and date
      await axios.put(`${API_URL}/calendardt/reset-routinedt`, null, {
        params: {
          userId: this.userId,
          date: this.parsedDate
        }
      });
      // Optionally refresh routines/calendar if you have refs
      // this.$refs.routineList.fetchRoutines();
      // this.$refs.calendar.fetchCalendarDts();
      // Navigate back
      this.$router.back();
    },
  },
  mounted() {
    const userId = this.$route.params.userId;
    const date = this.$route.params.date;
    console.log("Fetching RoutineDt for", userId, date);
    if (userId && date) {
      this.fetchRoutineDtData(userId, date);
      this.fetchJournalData();
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
