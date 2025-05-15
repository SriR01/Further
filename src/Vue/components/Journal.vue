<template>
    <div class="container">
      <h1>Journal</h1>
  
      <div class="card mb-3" v-for="(journal, index) in journals" :key="index">
        <div class="card-body">
          <h5 class="card-title">{{ journal.entry_subject }}</h5>
          <p class="card-text">{{ journal.entry_data }}</p>
          <p class="card-text">{{ journal.entry_date }}</p>
  
          <div v-if="!journal.editMode">
            <button class="btn btn-primary" @click="editJournal(index)">Edit</button>
            <button class="btn btn-danger" @click="deleteJournal(index)">Delete</button>
          </div>
  
          <div v-else>
            <textarea class="form-control mb-3" rows="3" v-model="journal.entry_data"></textarea>
            <button class="btn btn-success" @click="saveJournal(index)">Save</button>
            <button class="btn btn-secondary" @click="cancelEdit(index)">Cancel</button>
          </div>
        </div>
      </div>
  
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">New Journal Entry</h5>
          <form @submit.prevent="addJournal">
            <div class="mb-3">
              <label for="entrySubject" class="form-label">Subject</label>
              <input type="text" class="form-control" v-model="newJournal.entry_subject" required>
            </div>
            <div class="mb-3">
              <label for="entryData" class="form-label">Entry</label>
              <textarea class="form-control" rows="3" v-model="newJournal.entry_data" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Add Journal</button>
          </form>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        journals: [],
        newJournal: {
          entry_subject: '',
          entry_data: '',
          entry_date: '',
        },
      };
    },
    methods: {
      addJournal() {
        const date = new Date().toLocaleDateString();
        this.newJournal.entry_date = date;
        this.journals.push({ ...this.newJournal });
        this.newJournal.entry_subject = '';
        this.newJournal.entry_data = '';
      },
      deleteJournal(index) {
        this.journals.splice(index, 1);
      },
      editJournal(index) {
        this.journals[index].editMode = true;
      },
      saveJournal(index) {
        this.journals[index].editMode = false;
      },
      cancelEdit(index) {
        this.journals[index].editMode = false;
      },
    },
  };
  </script>
  
  <style>
  .mb-3{
    margin: 10px;

  }
  </style>
  