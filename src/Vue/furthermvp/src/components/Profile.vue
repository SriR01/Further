<template>
  <div class="container">
    <div class="card profile-card">
      <h2 class="card-title">Profile</h2>
      <h6 style="text-align:center; margin-bottom: 1.5rem;">Add your email for daily Further Analytics</h6>
      <form @submit.prevent="updateProfile">
        <div class="form-group">
          <label for="email">Email</label>
          <input v-model="profile.email" id="email" type="email" class="form-control" :readonly="!editing" required />
        </div>
        <div class="form-group">
          <label for="firstName">First Name</label>
          <input v-model="profile.firstName" id="firstName" type="text" class="form-control" :readonly="!editing" />
        </div>
        <div class="form-group">
          <label for="lastName">Last Name</label>
          <input v-model="profile.lastName" id="lastName" type="text" class="form-control" :readonly="!editing" />
        </div>
        <div class="form-group">
          <label for="bio">Bio</label>
          <textarea v-model="profile.bio" id="bio" class="form-control" :readonly="!editing"></textarea>
        </div>
        <div class="profile-actions">
          <button v-if="!editing" type="button" class="btn btn-primary" @click="editing = true">Edit</button>
          <button v-if="editing" type="submit" class="btn btn-success">Save</button>
          <button v-if="editing" type="button" class="btn btn-secondary" @click="cancelEdit">Cancel</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const API_URL = import.meta.env.VITE_API_URL;

export default {
  props: {
    userId: {
      type: Number,
      required: false,
      default: null
    }
  },
  data() {
    return {
      profile: {
        email: "",
        firstName: "",
        lastName: "",
        bio: ""
      },
      originalProfile: {},
      editing: false
    };
  },
  methods: {
    async fetchProfile() {
      const id = this.userId || this.$route.params.userId;
      if (!id) return;
      try {
        const response = await axios.get(`${API_URL}/profile/${id}`);
        if (response.data) {
          this.profile = {
            email: response.data.email,
            firstName: response.data.first_name, // map snake_case to camelCase
            lastName: response.data.last_name,
            bio: response.data.bio
          };
          this.originalProfile = { ...this.profile };
        }
      } catch (error) {
        console.error("Failed to fetch profile:", error);
      }
    },
    async updateProfile() {
      const id = this.userId || this.$route.params.userId;
      try {
        const payload = {
          email: this.profile.email,
          first_name: this.profile.firstName,
          last_name: this.profile.lastName,
          bio: this.profile.bio,
          user_id: id
        };
        await axios.put(`${API_URL}/profile/${id}`, payload);
        this.editing = false;
        this.originalProfile = { ...this.profile };
      } catch (error) {
        alert("Failed to update profile.");
        console.error(error);
      }
    },
    cancelEdit() {
      this.profile = { ...this.originalProfile };
      this.editing = false;
    }
  },
  mounted() {
    this.fetchProfile();
  }
};
</script>

<style scoped>
.profile-card {
  max-width: 500px;
  margin: 2rem auto;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.07);
  background: #fff;
}
.card-title {
  margin-bottom: 1.5rem;
  text-align: center;
}
.form-group {
  margin-bottom: 1rem;
}
.form-control[readonly] {
  background: #f9fafb;
  color: #888;
}
.profile-actions {
  display: flex;
  gap: 1rem;
  justify-content: flex-end;
  margin-top: 1.5rem;
}
</style>