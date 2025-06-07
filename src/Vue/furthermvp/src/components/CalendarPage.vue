<template>
  <div>
    <h1>Select date to view or track progress</h1>
    <Calendar ref="calendar" @refresh-calendar="refreshCalendar" @square-clicked="navigateToTrackingDetails" />
  </div>
</template>

<script>
import Calendar from "@/components/Calendar.vue";

export default {
  components: {
    Calendar,
  },
  methods: {
    navigateToTrackingDetails(calendarDt, dateValue) {
      // Only navigate if calendarDt exists
      if (calendarDt && calendarDt.user_id && dateValue) {
        this.$router.push({
          name: "TrackingDetailsPage",
          params: { userId: calendarDt.user_id, date: dateValue }
        });
      }
    },
    refreshCalendar() {
      // Call the fetch method on the Calendar component
      this.$refs.calendar.fetchCalendarDts();
    },
  },
};
</script>
