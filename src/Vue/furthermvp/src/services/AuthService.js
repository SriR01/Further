import axios from 'axios';

const API_URL = import.meta.env.VITE_API_URL;

export default {
  login(user) {
    return axios.post(`${API_URL}/login`, user);
  },
  register(user) {
    return axios.post(`${API_URL}/register`, user);
  }
}
