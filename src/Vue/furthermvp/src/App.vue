<template>
  <div id="app">
    <div id="nav">
      <h1 class="app-title">Further</h1>
      <router-link
        v-bind:to="{ name: 'home' }"
        class="nav-link"
        style="color: white"
        >Home</router-link
      >
      <router-link
        v-bind:to="{ name: 'login' }"
        v-if="$store.state.token == ''"
        class="nav-link"
        style="color: white"
        >Login</router-link
      >
      <router-link
        v-bind:to="{ name: 'goal-list' }"
        v-if="$store.state.token != ''"
        class="nav-link"
        style="color: white"
        >Goal List</router-link
      >
      <router-link
        v-bind:to="{ name: 'routine' }"
        v-if="$store.state.token != ''"
        class="nav-link"
        style="color: white"
        >Routine</router-link
      >
      <router-link
        v-bind:to="{ name: 'calendar' }"
        v-if="$store.state.token != ''"
        class="nav-link"
        style="color: white"
        >Tracking</router-link
      >
      <router-link
        v-bind:to="{ name: 'Profile', params: { userId: $store.state.user.id } }"
        v-if="$store.state.token != ''"
        class="nav-link"
        style="color: white"
        >Profile</router-link
      >
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        class="nav-link"
        style="color: white"
        >Logout</router-link
      >
    </div>
    <router-view />
  </div>
</template>

<script>
export default {};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
  background-color: #333333;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.app-title {
  font-size: 20pt;
  color: white;
  margin-right: 10px;
}

.nav-link {
  font-weight: bold;
  font-size: 18pt;
  color: white;
  margin-right: 10px;
}

.nav-link.router-link-exact-active {
  color: #42b983;
}
</style>
