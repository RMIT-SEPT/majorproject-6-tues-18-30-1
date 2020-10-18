import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import CustomerService from "../services/CustomerService";
export default class BusinessHome extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      content: "",
    };
  }

  componentDidMount() {
    const loggedInUser = CustomerService.getCustomer();
  }

  render() {
    return (
      <div className="container">
        <header className="jumbotron">
          <h3>{this.state.content}</h3>
          <h2>Business Home</h2>
        </header>
      </div>
    );
  }
}
