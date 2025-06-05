<template>
  <div>
    <h1>Select date to view or track progress</h1>
    <Calendar @square-clicked="navigateToTrackingDetails" />
  </div>
</template>

<script>
import Calendar from "@/components/Calendar.vue";
const API_URL = import.meta.env.VITE_API_URL;

export default {
  components: {
    Calendar,
  },
  methods: {
    navigateToTrackingDetails(dateValue) {
      // Handle navigation to tracking details page using Vue Router
      this.$router.push({ name: "tracking", params: { dateValue } });
    },
  },
};
</script>
