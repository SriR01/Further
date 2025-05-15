<template>
  <div class="journal-card">
    <h2 class="card-title">Journal Entry</h2>
    <div class="card-body">
      <div class="journal-entry">
        <h5>Subject: {{ journal.entry_subject }}</h5>
        <p>Date: {{ journal.entry_date }}</p>
        <p>Entry: {{ journal.entry_data }}</p>
      </div>
    </div>
  </div>
</template>
  
  <script>
export default {
  props: {
    journal: {
      type: Object,
      required: true,
    },
  },
};
</script>
  
  <style>
.journal-card {
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

.journal-entry {
  margin-bottom: 10px;
}
</style>
  