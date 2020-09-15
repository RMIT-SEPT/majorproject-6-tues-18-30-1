import React, { Component } from "react";
import { useState } from "react";
import ReactDOM, { render } from "react-dom";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import logo from "./logo.svg";

import { Button } from "reactstrap";
import "bootstrap/dist/css/bootstrap.css";
import "./index.css";
import "./App.css";

import Header from "./components/Header";
import CustomerLogInForm from "./components/CustomerLogInForm";
import CustomerSignUpForm from "./components/CustomerSignUpForm";
import BusinessLogInForm from "./components/BusinessLogInForm";
import BusinessSignupForm from "./components/BusinessSignupForm";

// const homePage = (
//   <div id="options">
//     <h2>I am a...</h2>
//     <Button>Customer</Button>
//     <Button>Business</Button>
//   </div>
// );

// class HomePage extends Component {
//   render() {
//     return homePage;
//   }
// }

class App extends Component {
  state = {
    customerLogin: false,
    businessLogin: false,
    customerSignUp: false,
    businessSignUp: false,
  };

  render() {
    return (
      <Router>
        <div>
          <div className="App">
            <Header />
            <div class="buttons" align="center">
              <Button
                style={{ fontSize: 30 }}
                color="danger"
                onClick={() => {
                  this.setState({
                    customerLogin: true,
                    businessLogin: false,
                    customerSignUp: false,
                    businessSignUp: false,
                  });
                }}
              >
                Customer
              </Button>
              <span></span>
              <Button
                style={{ fontSize: 30 }}
                color="warning"
                onClick={() => {
                  this.setState({
                    businessLogin: true,
                    customerLogin: false,
                    customerSignUp: false,
                    businessSignUp: false,
                  });
                }}
              >
                Business owner
              </Button>
            </div>
            <div class="customer-log-in" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.customerLogin ? <CustomerLogInForm /> : null}
                </Route>
              </Switch>
            </div>
            <div class="customer-sign-up" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.customerSignUp ? <CustomerSignUpForm /> : null}
                </Route>
              </Switch>
            </div>
            <div class="business-log-in" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.businessLogin ? <BusinessLogInForm /> : null}
                </Route>
              </Switch>
            </div>

            <div class="business-sign-up" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.businessSignUp ? <BusinessSignupForm /> : null}
                </Route>
              </Switch>
            </div>

            <div class="sign-up-button" align="center">
              <div className="registerMessage">
                <span>Dont have an account? </span>
                <button
                  className="loginText"
                  onClick={() => {
                    this.setState({
                      customerLogin: false,
                      businessLogin: false,
                      customerSignUp: true,
                      businessSignUp: false,
                    });
                  }}
                >
                  Sign Up
                </button>
              </div>
            </div>
          </div>
        </div>
      </Router>
    );
  }
}

// class AppClass extends Component {
//   state = {
//     isLoading: true,
//     groups: []
//   };

//   async successfulMount() {
//     const reply = await fetch ('users');
//     const body = await response.json();
//     this.setState({ groups: body, isLoading: false });
//   }
// }

// render() {
//   const {groups, isLoading} = this.state;

//   if(isLoading) {
//     return <p>Processing your request...</p>;
//   }

//   return (
//     <div className=""
//   )
// }

export default App;
