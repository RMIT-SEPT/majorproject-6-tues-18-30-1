import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import CustomerService from "../services/CustomerService";
export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      content: "",
      logout: false,
      updatedetails: false,
      createbooking: false,
      viewbooking: false,
    };
  }

  componentDidMount() {
    const loggedInUser = CustomerService.getCustomer();
    const bookings = CustomerService.getCustomerBookings(this.state.id);
  }

  render() {
    if (this.state.logout) {
      return <Redirect to={"/"} />;
    }

    return (
      <div className="container">
        <header className="jumbotron">
          <h3>{this.state.content}</h3>
          <h2>Customer Home</h2>
        </header>

        <button
          className="create-booking"
          onClick={() => {
            this.setState({
              createbooking: true,
            });
          }}
        >
          Create booking
        </button>

        <button
          className="view-bookings"
          onClick={() => {
            this.setState({
              viewbookings: true,
            });
          }}
        >
          View my bookings
        </button>

        <button
          className="update-details"
          onClick={() => {
            this.setState({
              updatedetails: true,
            });
          }}
        >
          Update my details
        </button>

        <button
          className="log-out"
          onClick={() => {
            this.setState({
              logout: true,
            });
          }}
        >
          Log out
        </button>
      </div>
    );
  }
}
