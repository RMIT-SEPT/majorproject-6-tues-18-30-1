import axios from "axios";

const API_URL = "http://localhost:8080/customers/";

class CustomerLoginService {
  async customerLogin(id) {
    const response = axios.get(API_URL + id).then(function (response) {
      console.log(response.data);
      console.log(response.status);
      console.log(response.statusText);
      console.log(response.headers);
      console.log(response.config);
    });

    // if (response.data.accessToken) {
    //   localStorage.setItem("customer", JSON.stringify(response.data));
    // }
    return response.data;
  }

  customerLogout() {
    localStorage.removeItem("customer");
  }

  getCustomer() {
    return JSON.parse(localStorage.getItem("customers"));
  }
}

export default new CustomerLoginService();
