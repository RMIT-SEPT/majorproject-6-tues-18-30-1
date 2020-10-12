import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class CustomerLoginService {
  async customerLogin(username, password) {
    const response = await axios.post(API_URL + "authorise", {
      username,
      password,
    });
    if (response.data.accessToken) {
      localStorage.setItem("customer", JSON.stringify(response.data));
    }
    return response.data;
  }

  customerLogout() {
    localStorage.removeItem("customer");
  }

  createCustomer(firstName, lastName, email, password, phoneNumber) {
    return axios.post(API_URL + "customer", {
      firstName,
      lastName,
      email,
      password,
      phoneNumber,
    });
  }

  getCustomer() {
    return JSON.parse(localStorage.getItem("customer"));
  }
}

export default new CustomerLoginService();
