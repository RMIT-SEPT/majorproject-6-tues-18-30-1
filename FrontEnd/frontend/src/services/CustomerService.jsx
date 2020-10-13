import axios from "axios";
const API_URL = "http://localhost:8080/api/";

class CustomerService {
  getCustomerDetails() {
    const customer = JSON.parse(localStorage.getItem("customer"));

    if (customer && customer.accessToken) {
      return { Authorization: "Bearer " + customer.accessToken }; // for Spring Boot back-end
    } else {
      return {};
    }
  }

  createCustomerBooking(id) {
    const response = axios.post(API_URL + id + "bookings");
    return response;
  }

  getCustomerBookings(id) {
    const response = axios.get(API_URL + id + "bookings");
    return response;
  }

  getCustomerDetails(id) {
    const response = axios.get(API_URL + id);
    return response;
  }

  getCustomer() {
    return JSON.parse(localStorage.getItem("customers"));
  }

  returnCustomerDetails(id) {
    return axios.get(API_URL + "customers/" + id, {
      headers: this.getCustomerDetails(),
    });
  }
}
export default new CustomerService();
