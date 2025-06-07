<template>
  <div
    class="square"
    :class="{
      'has-calendar-dt': !!calendarDt,
      'is-today': isToday
    }"
    @click="handleClick"
    :style="{ cursor: calendarDt ? 'pointer' : 'not-allowed' }"
  >
    <div class="square-date">{{ dayOfMonth }}</div>
    <div v-if="calendarDt" class="square-dot"></div>
  </div>
</template>

<script>
export default {
  props: {
    dateValue: String,
    calendarDt: Object,
  },
  computed: {
    dayOfMonth() {
      return this.dateValue ? Number(this.dateValue.split('-')[2]) : '';
    },
    isToday() {
      const today = new Date();
      const localDate = today.getFullYear() + '-' +
        String(today.getMonth() + 1).padStart(2, '0') + '-' +
        String(today.getDate()).padStart(2, '0');
      return this.dateValue === localDate;
    }
  },
  methods: {
    handleClick() {
      if (this.calendarDt) {
        this.$emit("show-details", this.calendarDt, this.dateValue);
      }
    },
  },
};
</script>

<style scoped>
.square {
  width: 48px;
  height: 48px;
  background: #f9fafb;
  border-radius: 8px;
  box-shadow: 0 1px 2px rgba(0,0,0,0.03);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  transition: border 0.2s, box-shadow 0.2s;
  margin: 0 auto;
  position: relative;
  cursor: default;
  border: 1px solid #e2e8f0;
}

.square-date {
  font-weight: 600;
  font-size: 1.1em;
  color: #2d3748;
  text-align: center;
}

.has-calendar-dt {
  border: 2px solid #38b2ac;
  cursor: pointer;
  background: #e6fffa;
  box-shadow: 0 2px 8px rgba(56,178,172,0.08);
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

.square:hover.has-calendar-dt {
  background: #b2f5ea;
  box-shadow: 0 4px 16px rgba(56,178,172,0.15);
}
</style>
