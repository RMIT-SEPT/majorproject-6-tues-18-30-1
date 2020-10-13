import React, { Component } from "react";
import { useState } from "react";
import ReactDOM, { render } from "react-dom";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import logo from "./logo.svg";

import { Button } from "reactstrap";
import "bootstrap/dist/css/bootstrap.css";
import "./index.css";
import "./App.css";

import Header from "./components/Header";
import Home from "./components/Home";
import CustomerLogInForm from "./components/CustomerLogInForm";
import CustomerSignUpForm from "./components/CustomerSignUpForm";
import BusinessLogInForm from "./components/BusinessLogInForm";
import BusinessSignupForm from "./components/BusinessSignupForm";

import CustomerLoginService from "./services/CustomerLoginService";

import BusinessLoginFormTest from "./components/BusinessLoginFormTest";
import LoginFormTest from "./components/LoginFormTest";
import RegisterForm from "./components/RegisterForm";
import BusinessRegisterForm from "./components/BusinessRegisterForm";

class App extends Component {
  constructor(props) {
    super(props);
    this.customerLogout = this.customerLogout.bind(this);
    this.state = {
      customerLogin: false,
      businessLogin: false,
      customerSignUp: false,
      businessSignUp: false,
      businessHome: false,
      customerHome: false,
      user: undefined,
    };
  }

  componentDidMount() {
    const userLoggedIn = CustomerLoginService.getCustomer();

    if (userLoggedIn) {
      this.setState({
        user: userLoggedIn,
        // businessHome: user.isBusinessOwner
        // customerHome: user.isCustomer
      });
    }
  }

  customerLogout() {
    CustomerLoginService.customerLogout();
  }

  render() {
    const { user, businessHome, customerHome } = this.state;

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
                  {this.state.customerLogin ? <LoginFormTest /> : null}
                </Route>
              </Switch>
            </div>

            <div class="customer-sign-up" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.customerSignUp ? <RegisterForm /> : null}
                </Route>
              </Switch>
            </div>

            <div class="business-log-in" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.businessLogin ? <BusinessLoginFormTest /> : null}
                </Route>
              </Switch>
            </div>

            <div class="business-sign-up" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.businessSignUp ? <BusinessRegisterForm /> : null}
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

          <div className="container mt-3">
            <Switch>
              <Route exact path={["/", "/home"]} component={Home} />
              <Route
                exact
                path="/business-login"
                component={BusinessLogInForm}
              />
              <Route
                exact
                path="/business-signup"
                component={BusinessSignupForm}
              />
              <Route
                exact
                path="/customer-login"
                component={CustomerLogInForm}
              />
              <Route
                exact
                path="/customer-signup"
                component={CustomerSignUpForm}
              />
              <Route exact path="/customer-register" component={RegisterForm} />
              <Route exact path="/login-test" component={LoginFormTest} />

              {/* <Route exact path="/profile" component={Profile} />
              <Route path="/user" component={BoardUser} />
              <Route path="/mod" component={BoardModerator} />
              <Route path="/admin" component={BoardAdmin} /> */}
            </Switch>
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
