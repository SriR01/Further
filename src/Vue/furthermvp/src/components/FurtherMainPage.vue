<template>
  <div class="main-container">
    <div class="motivation">
      <h2 class="achievement-tracking">
        <b
          >Track your daily achievements, Further will track your lifelong
          progress.</b
        >
      </h2>
    </div>
    <div class="power-list-description">
      <p class="push-notification">
        Further sends notifications incrementally as a reminder
      </p>
    </div>

    <div id="demo" class="carousel slide" data-bs-ride="carousel">
      <!-- Indicators/dots -->
      <div class="carousel-indicators">
        <button
          type="button"
          data-bs-target="#demo"
          data-bs-slide-to="0"
          class="active"
        ></button>
        <button
          type="button"
          data-bs-target="#demo"
          data-bs-slide-to="1"
        ></button>
        <!--Sign up-->
        <button
          type="button"
          data-bs-target="#demo"
          data-bs-slide-to="2"
        ></button>
        <!--Goal List-->
        <button
          type="button"
          data-bs-target="#demo"
          data-bs-slide-to="3"
        ></button>
        <!--Routine Image-->
        <button
          type="button"
          data-bs-target="#demo"
          data-bs-slide-to="4"
        ></button>
        <!--Tracking Image-->
      </div>

      <!-- The slideshow/carousel -->
      <div class="carousel-inner">
        <div class="carousel-item active">
          <div class="winning-description">
            <ul class="winning-list">
              <p><u>The Power List is a hyper-focused To-Do list</u></p>
              <li>
                Complete at least <b>three</b> out of the <b>five tasks</b> to
                <b>Win the Day</b>
              </li>
              <li>
                Win <b>four</b> out of <b>seven days</b> to <b>Win the Week</b>
              </li>
              <li>
                Win <b>three</b> out of <b>four weeks</b> to
                <b>Win the Month</b>
              </li>
              <li>
                Win <b>seven</b> out of <b>12 months</b>, and your life will
                have changed.
              </li>
            </ul>
          </div>
        </div>
        <div class="carousel-item">
          <div class="sign-up-container">
            <img
              class="signin"
              src="https://previews.dropbox.com/p/thumb/AB51o7u9mHCA8R2zX2EqkfAAlO34t6rKfyJdNAmxDiwBbS8RXR6qTc00hV4Wma3FuMGb14EpjQ0aIuk9y5VFHgPK16C-W0JaUbpBTCXzz7yGwsvL-8usD_rEnxPrA890GCEqDkLkLe1bULJ75rWENeziJ3XYChxgsm6Hqix_4zarDDXRaD1YGJ5T38R5IKOUZGlGdYv9bUNEAZiMezAepmdRIZS-FDAP7nASvotzATJTb0rYK04IQyUNpzv5e84HOw0RYMjH8k0WZQT-gZpVdUcMSEszK7Y4llh6PGf2c34X6bW6ypHYsw_z-9-e_EA1fauOlvW8C03YU_9au3rK86pdElXu2S77z_RL51I99jAIxvAdFmfHLoN7tgzDRsN4jj4/p.png"
            />
          </div>
        </div>
        <div class="carousel-item">
          <div class="goal-list-container">
            <h3 class="goal-list">
              Transcribe all goals into the Further Goal List
            </h3>
            <img
              src="https://previews.dropbox.com/p/thumb/AB4_L7icL9_9Rd5k30-37aaHSpuo_wZ2rayiaeIUSnevLitCzjhUMXaAVREyVjULoFWE49xudw2quN5zz7DEFqv-P5yLIxhropXsN39iILYap4L5GQbRCNr-jpug644K4mO5A4paEYoAsTcegZEu8OXohX1Se5GBmdQ9izYGaNqgA78I3H6K0nPgiv34jJSHse7y_hZ_8IewFyKeG2NL1DIlGZrs93FwA_ELYfYZm9i3L6hpvr2Ty3PikUrjqQmKwlwtx7_iVhqu9PeTtueANyK5A9t2JeXVlmaMSbTo5aivLrSdZ_hAY4qjAarMkZkuP3b88K_9aSwI_SF0fOEv5aYQli3BzKD0iFCvJnXzhMgzal9Ve0_ocHALvgivitZ8_Xk/p.png"
            />
            <h3></h3>
          </div>
        </div>
        <div class="carousel-item">
          <div class="power-list-tasks">
            <h3 class="power-list-intro">Create a Routine:</h3>
            <img
              src="https://previews.dropbox.com/p/thumb/AB5oqrdb1VQP2vcmNH6Hut3Ssy14BqE-5irQBb5NlcmNVesWarSUHMFPcBu4QKA3aXfyd9JSgqCDHD0Vhy-JuSONCTydJLvgau5PmKpaf61t33A9vaNKTnSSpY6cZk3-zgoI-Bh_ifrgKtWoKmTdureM3XgDCPIPzCXxOHObXB6TaivQHtPpML5IPn8bEByFxYvtDOCtr1L-ifx0p_tV6TAT4GKfzkTc7TNNqDwaVAqXMG05zNLfb_D7WR90JAABhL98QHbwEjoXTiCo47jQz27WTZe2pPOYK2W2Bwj2s-PjCfBzBHu0u1ZRi0mlWuyTlgBXYJ6c8Fop1g2q1zAgL7ml1P2y4fU3Gi3oarO6B3IV-ogf6WA6E4grOLToKmmgEyM/p.png"
            />
            <b
              >If you only accomplished these five goals, your day would be a
              success.</b
            >
          </div>
        </div>
        <div class="carousel-item">
          <div class="tracking-container">
            <h3 class="tracking">Tracking your Routine:</h3>
            <img
              src="https://previews.dropbox.com/p/thumb/AB6Z3BgqJH9ZDyBgzda99uEEKIW4ffR_ErDF6d9aNBBMiZICqMi02mH2-Ksc-HRwzISDiPZj931ZLKc9Mufw1GLMID_A9xFDkRtnpzO4755Ogta8DeoxuT-idrekgr5ON1PhudxCM1Acv7IpW4VAx0NTd1r6WMoRHtSTimmpC8ZI2T_RvdlsNaSp-y1t6GsenlTawEOYPRMF5NTID0sRYp7rLwr6vj2tAtVUwmRzHeAovf0Gx6-7GsHDcWV4rNJ0LuX51mXklRfHRuSn6XSM8E51KNuE3PJwl64fZgr2ybB4mmP0oVFrLU34fRvGngw8hZzaYHyhHM8lvb_4JbJwFSortY78mE6Ny1LqR25LqAKBtlCCU8Bj0ftoQp-4OCr5jLE/p.png"
            />
          </div>
        </div>
        <div class="carousel-item">
          <div class="tracking-container">
            <h3 class="calendar">Check your Calendar:</h3>
            <img
              src="https://previews.dropbox.com/p/thumb/AB7GPDOLzZsrV1Rr4Ne5UC3sV_yOg0VL0TGE-5iDIV_FWQXi4ZQudulFyS7PiUMXjs6QuCnAVk8zq-To2wPpI3hU2i3_VOWI1r_RWqmto9c9Yk_PzT9l20LAgtovojqc-OJAwBwXfdHKF-B8Iw0qbX_EHg0mQVZgsbE8__NbmFYkz7hWxKv7wLklhkOFFgTwZotadKrWE3EYMabuh5IJWFb7CU8wnsD5rrKDnoUNim_5W_OZcg0WmZ8yozzm4L_RqOZV7YwjUZokNqaa1r2snSjO2-bL6dong7pwrjyYOaiL0CoqQUgtV5g77op5NrKr_XxMK2JcHAxYYxVfqcbyEqBgLa92nXYCv5uSR_w5dwIHrVirv7wfNJ5Tkx28MuJUr1U/p.png"
            />
          </div>
        </div>
      </div>

      <!-- Left and right controls/icons -->
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#demo"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon"></span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#demo"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon"></span>
      </button>
    </div>

    <footer>
      <p class="footer-text">© 2023 Further. All rights reserved.</p>
    </footer>
  </div>
</template>

<script>
import "bootstrap/dist/js/bootstrap.js";
import "bootstrap/dist/css/bootstrap.css";
const API_URL = import.meta.env.VITE_API_URL;

export default {
  name: "home",
  props: {},
};
</script>

<style>
/* Existing styles */

body {
  margin: 0;
  padding: 0;
}

nav.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background: #cfd8dc;
}

nav header {
  display: flex;
  flex-direction: column;
}

.main-container {
  font-family: Avenir, "Caviar Dreams", monospace;
  background: whitesmoke;
  padding: 2rem;
}

.app-name,
.motto {
  font-size: 20pt;
  margin: 0;
  text-align: left;
  color: black;
}

ul.navbar-list {
  display: flex;
  list-style: none;
  justify-content: space-between;
  margin-right: auto;
}

ul.navbar-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-right: auto;
}

ul.navbar-nav li {
  margin-right: 1rem;
}

.app-description {
  background: #708090;
  margin: 20px 0;
  text-align: center;
  font-size: 18pt;
  padding: 1rem;
  color: black;
}

.power-list-description {
  font-size: 18pt;
  background: white;
  padding: 1px;
  color: black;
}

.power-list-tasks {
  margin-bottom: 30px;
}

.power-list-intro {
  margin-bottom: 10px;
}

.power-list,
.winning-description,
.push-notification,
.achievement-tracking {
  font-size: 18pt;
  margin: 30px;
  color: black;
}

.winning-list,
.power-list {
  list-style-type: none;
}

.winning-list {
  background-color: #cfd8dc;
}

.footer-text {
  font-size: 16pt;
  margin: 10px;
  color: #708090;
}

/* New styles to align power-list with power-list-intro */
.power-list {
  padding: 0;
  margin: 0;
}

.power-list li {
  margin-bottom: 10px;
}

.carousel-control-prev,
.carousel-control-next {
  width: 50px;
  height: 50px;
  background-color: rgba(0, 0, 0, 0.3);
  border-radius: 50%;
  border: none;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: background-color 0.3s;
}

.carousel-control-prev:hover,
.carousel-control-next:hover {
  background-color: rgba(0, 0, 0, 0.5);
}

.carousel-control-prev:focus,
.carousel-control-next:focus {
  outline: none;
}

.carousel-control-prev-icon,
.carousel-control-next-icon {
  width: 30px;
  height: 30px;
  background-color: transparent;
  border: none;
  display: inline-block;
  position: relative;
}

.carousel-control-prev-icon:before,
.carousel-control-next-icon:before {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-style: solid;
  border-width: 0.15em 0.15em 0 0;
}

.carousel-control-prev-icon:before {
  transform: translate(-50%, -50%) rotate(135deg);
}

.carousel-control-next-icon:before {
  transform: translate(-50%, -50%) rotate(-45deg);
}

.carousel-item {
  width: 100%; /* Adjust the width to your desired size */
  height: 50%; /* Adjust the height to your desired size */
  overflow: hidden;
}

.carousel-item img {
  width: 100%;
  object-fit: cover;
}
</style>

