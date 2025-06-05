<template>
  <div class="calendar">
    <div class="month-selector">
      <button class="prev-month" @click="prevMonth">
        <i class="arrow left"></i>
      </button>
      <div class="month-label">
        {{ getMonthName(currentYear, currentMonth) }} {{ currentYear }}
      </div>
      <button class="next-month" @click="nextMonth">
        <i class="arrow right"></i>
      </button>
    </div>
    <ul class="nav">
      <li>Sun</li>
      <li>Mon</li>
      <li>Tue</li>
      <li>Wed</li>
      <li>Thu</li>
      <li>Fri</li>
      <li>Sat</li>
    </ul>

    <div class="calendar-grid">
      <div class="calendar-week-wrapper">
        <div
          v-for="(week, index) in calendarWeeks"
          :key="index"
          class="calendar-week"
        >
          <div v-if="index === 0" class="weekday-label"></div>
          <div
            v-for="day in week.days"
            :key="day.date"
            class="calendar-square"
            :class="{
              'current-date': isCurrentDate(day.date),
              'normal-square':
                day.date === null ||
                !isCurrentDate(day.date) | (isWinSquare(day.date) == undefined),
            }"
            @click="squareClicked(day.date)"
          >
            <p class="square-date">{{ day.dayOfMonth }}</p>
            <label class="square-win" v-if="isWinSquare(day.date)">
              {{ day.isWin }}
            </label>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const API_URL = import.meta.env.VITE_API_URL;
import store from "@/stores/index";

export default {
  data() {
    return {
      weekdays: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
      calendarWeeks: [],
      currentDate: new Date().toISOString().split("T")[0],
      currentYear: 0,
      currentMonth: 0,
      userId: store.state.user.id,
    };
  },
  methods: {
    isCurrentDate(date) {
      const currentDate = new Date(this.currentDate);
      return currentDate.toISOString().split("T")[0] === date;
    },
    async isWinSquare(date) {
      try {
        const userId = this.userId;
        const endpoint = `http://localhost:9000/routine-dt/${userId}/${date}`;
        const response = await axios.get(endpoint);
        const routineDt = response.data;
        console.log(routineDt.win_flag);
        if (routineDt.win_flag) {
          return "Day Won";
        } else if (routineDt.win_flag === false) {
          return "Day Lost";
        }
      } catch (error) {
        return false; // Return a default value in case of an error
      }
    },
    getDayOfMonth(date) {
      return new Date(date).getDate();
    },
    getMonthName(year, month) {
      return new Date(year, month - 1, 1).toLocaleString("default", {
        month: "long",
      });
    },
    prevMonth() {
      this.currentMonth = (this.currentMonth - 1 + 12) % 12;
      if (this.currentMonth === 0) {
        this.currentYear--;
      }
      this.generateCalendar();
    },
    nextMonth() {
      this.currentMonth = (this.currentMonth + 1) % 12 || 12;
      if (this.currentMonth === 1) {
        this.currentYear++;
      }
      this.generateCalendar();
    },
    async generateCalendar() {
      const numDays = new Date(
        this.currentYear,
        this.currentMonth,
        0
      ).getDate();
      const startingDay = new Date(
        this.currentYear,
        this.currentMonth - 1,
        1
      ).getDay();

      this.calendarWeeks = [];
      let week = { startingDate: null, days: [] };

      for (let i = 0; i < startingDay; i++) {
        week.days.push({ date: null, dayOfMonth: null });
      }

      for (let i = 1; i <= numDays; i++) {
        const year = this.currentYear;
        const month = this.currentMonth;
        const day = i;
        const date = `${year}-${String(month).padStart(2, "0")}-${String(
          day
        ).padStart(2, "0")}`;

        if (week.days.length === 7) {
          this.calendarWeeks.push(week);
          week = { startingDate: null, days: [] };
        }

        await this.updateIsWin(week, date, day);
      }

      if (week.days.length > 0) {
        this.calendarWeeks.push(week);
      }
    },
    squareClicked(date) {
      this.currentDate = date;
      this.getCalendarDt(date);
    },
    async getCalendarDt(date) {
      try {
        const url = `http://localhost:9000/calendardt/getdt/${this.userId}/${date}`;
        const response = await axios.get(url);
        const calendarDt = response.data;

        if (calendarDt) {
          this.$router.push({
            name: "tracking",
            params: { calendarDt: JSON.stringify(calendarDt) },
          });
        } else {
          alert("Routine details not set");
          console.error("Invalid calendarDt:", calendarDt);
        }
      } catch (error) {
        alert("Routine details not set");
        console.error("Failed to fetch calendarDt:", error);
      }
    },
    async updateIsWin(week, date, day) {
      const isWin = await this.isWinSquare(date);
      week.days.push({ date, dayOfMonth: day, isWin });
    },
  },
  mounted() {
    const currentDate = new Date();
    this.currentYear = currentDate.getFullYear();
    this.currentMonth = currentDate.getMonth() + 1;

    const options = { year: "numeric", month: "2-digit", day: "2-digit" };
    this.currentDate = currentDate.toLocaleDateString("en-US", options);
    this.generateCalendar();
  },
};
</script>


<style scoped>
.calendar {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

.month-selector {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.month-label {
  font-size: 24px;
  display: flex;
  align-items: center;
}

.prev-month,
.next-month {
  font-size: 24px;
  background-color: transparent;
  border: none;
  cursor: pointer;
}

.arrow {
  display: inline-block;
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 10px 8px 10px 0;
  border-color: transparent #000 transparent transparent;
}

.prev-month .arrow {
  transform: rotate(0);
}

.next-month .arrow {
  transform: rotate(180deg);
}

.calendar-grid {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-gap: 10px;
}

.weekday-label {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
}

.calendar-week-wrapper {
  display: flex;
  flex-direction: column;
}

.calendar-week {
  display: flex;
  flex-direction: row;
  border: 1px solid black;
}

.calendar-square {
  display: flex;
  height: 80px;
  width: 80px;
  background-color: #f2f2f2;
  border-radius: 5px;
  cursor: pointer;
  border: 1px solid black;
}

.current-date {
  background-color: #b3d4fc;
}

.win-square {
  background-color: green;
}

.lose-square {
  background-color: red;
}

.square-date {
  font-weight: bold;
}

.square-date,
.square-date:hover {
  color: #333;
}

.empty-square {
  height: 80px;
  width: 80px;
}

.weekdayName {
  display: inline-block;
}

.nav {
  display: flex;
  list-style-type: none;
}

.nav li {
  flex: 1;
  text-align: center;
}

.calendar-square {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.square-win {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>


