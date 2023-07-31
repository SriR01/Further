<template>
  <div class="square" @click="toggleDetails">
    <div class="square-date">{{ dateValue }}</div>
    <div v-if="showDetails" class="square-details">
      <!-- Display additional details if needed -->
    </div>
  </div>
</template>

<script>
export default {
  props: {
    dateValue: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      showDetails: false,
    };
  },
  methods: {
    toggleDetails() {
      this.showDetails = !this.showDetails;
    },
  },
};
</script>

<style scoped>
.square {
  background-color: #f0f0f0;
  padding: 10px;
  cursor: pointer;
}

.square-date {
  font-weight: bold;
  text-align: center;
}

.square-details {
  margin-top: 10px;
}
</style>
