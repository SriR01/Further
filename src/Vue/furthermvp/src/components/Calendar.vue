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
      <li v-for="day in weekdays" :key="day">{{ day }}</li>
    </ul>
    <div class="calendar-grid">
      <div
        v-for="day in daysInMonth"
        :key="day.date"
        class="calendar-square-wrapper"
      >
        <Square
          :dateValue="day.date"
          :calendarDt="findCalendarDtForDate(day.date)"
          @show-details="openTrackingDetails"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Square from "./Square.vue";
import store from "@/stores/index";
const API_URL = import.meta.env.VITE_API_URL;

export default {
  components: { Square },
  data() {
    return {
      weekdays: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
      currentDate: new Date().toISOString().split("T")[0],
      currentYear: new Date().getFullYear(),
      currentMonth: new Date().getMonth() + 1,
      userId: store.state.user.id,
      calendarDts: [],
      daysInMonth: [],
    };
  },
  methods: {
    async fetchCalendarDts() {
      try {
        const response = await axios.get(
          `${API_URL}/calendardt/getdts/${this.userId}`
        );
        this.calendarDts = response.data;
      } catch (error) {
        console.error("Failed to fetch calendar Dts:", error);
        this.calendarDts = [];
      }
    },
    findCalendarDtForDate(date) {
      // Match both "YYYY-MM-DD" and "YYYY-MM-DDTHH:mm:ssZ"
      return this.calendarDts.find(dt => (dt.dates ? dt.dates.split('T')[0] : '') === date);
    },
    getMonthName(year, month) {
      return new Date(year, month - 1, 1).toLocaleString("default", {
        month: "long",
      });
    },
    prevMonth() {
      if (this.currentMonth === 1) {
        this.currentMonth = 12;
        this.currentYear--;
      } else {
        this.currentMonth--;
      }
      this.generateDaysInMonth();
    },
    nextMonth() {
      if (this.currentMonth === 12) {
        this.currentMonth = 1;
        this.currentYear++;
      } else {
        this.currentMonth++;
      }
      this.generateDaysInMonth();
    },
    generateDaysInMonth() {
      const numDays = new Date(this.currentYear, this.currentMonth, 0).getDate();
      const days = [];
      for (let i = 1; i <= numDays; i++) {
        const date = `${this.currentYear}-${String(this.currentMonth).padStart(2, "0")}-${String(i).padStart(2, "0")}`;
        days.push({ date });
      }
      this.daysInMonth = days;
    },
    openTrackingDetails(calendarDt, dateValue) {
      // Only allow navigation if calendarDt exists
      if (calendarDt && calendarDt.user_id && dateValue) {
        this.$router.push({
          name: "TrackingDetailsPage",
          params: { userId: calendarDt.user_id, date: dateValue }
        });
      }
    }
  },
  async mounted() {
    await this.fetchCalendarDts();
    this.generateDaysInMonth();
  },
  watch: {
    currentMonth() {
      this.generateDaysInMonth();
    },
    currentYear() {
      this.generateDaysInMonth();
    }
  }
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
  transform: rotate(0);
}
.prev-month .arrow {
  transform: rotate(180deg);
}
.next-month .arrow {
  transform: rotate(180deg);
}
.nav {
  display: flex;
  list-style-type: none;
  text-align: center;
  padding: 0;
  margin-bottom: 10px;
}
.nav li {
  flex: 1;
  text-align: center;
}
.calendar-grid {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-gap: 10px;
}
.calendar-square-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
.square {
  width: 48px;
  height: 48px;
  background: #f9fafb;
  border-radius: 8px;
  border: 1px solid #e2e8f0;
  box-shadow: 0 1px 2px rgba(0,0,0,0.03);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  transition: border 0.2s, box-shadow 0.2s, background 0.2s;
  margin: 0 auto;
  position: relative;
  cursor: default;
  font-size: 1.1em;
  font-weight: 600;
  color: #2d3748;
}
.has-calendar-dt {
  border: 2px solid #38b2ac;
  cursor: pointer;
  background: #e6fffa;
  box-shadow: 0 2px 8px rgba(56,178,172,0.08);
}
.square.has-calendar-dt:hover {
  background: #b2f5ea;
  box-shadow: 0 4px 16px rgba(56,178,172,0.15);
}
.square-dot {
  width: 8px;
  height: 8px;
  background: #38b2ac;
  border-radius: 50%;
  margin-top: 4px;
}
.is-today {
  border: 2px solid #3182ce;
  background: #ebf8ff;
}
</style>