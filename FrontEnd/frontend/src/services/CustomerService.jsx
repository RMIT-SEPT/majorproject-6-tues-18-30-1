import axios from "axios";
const API_URL = "http://localhost:8080/api/test/";

class CustomerService {
  getCustomerDetails() {
    const customer = JSON.parse(localStorage.getItem("customer"));

    if (customer && customer.accessToken) {
      return { Authorization: "Bearer " + user.accessToken }; // for Spring Boot back-end
    } else {
      return {};
    }
  }

  returnCustomerDetails() {
    return axios.get(API_URL + "customer", {
      headers: this.getCustomerDetails(),
    });
  }
}

export default new CustomerService();
