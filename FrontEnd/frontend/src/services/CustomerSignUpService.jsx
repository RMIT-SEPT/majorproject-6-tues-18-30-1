import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class CustomerSignUpService {
  async createCustomer(
    firstName,
    lastName,
    phoneNum,
    email,
    streetNum,
    streetName,
    suburb,
    postcode,
    state
  ) {
    axios
      .post(API_URL + "customers", {
        firstName,
        lastName,
        phoneNum,
        email,
        streetNum,
        streetName,
        suburb,
        postcode,
        state,
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  getCustomer() {
    return JSON.parse(localStorage.getItem("customers"));
  }
}

export default new CustomerSignUpService();
