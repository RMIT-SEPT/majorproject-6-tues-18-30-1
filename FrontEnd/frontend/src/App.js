import React, { Component } from "react";
import { useState } from "react";
import ReactDOM, { render } from "react-dom";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  withRouter,
} from "react-router-dom";

import logo from "./logo.svg";

import { Button } from "reactstrap";
import "bootstrap/dist/css/bootstrap.css";
import "./index.css";
import "./App.css";

import Header from "./components/Header";
import Home from "./components/Home";

import CustomerLoginService from "./services/CustomerLoginService";

import CustomerLoginForm from "./components/CustomerLoginForm";
import CustomerRegisterForm from "./components/CustomerRegisterForm";
import BusinessLoginForm from "./components/BusinessLoginForm";
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
      customerOption: false,
      businessOption: false,
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
                    customerOption: true,
                    businessOption: false,
                  });
                }}
              >
                Customer Log-In
              </Button>
              <span> </span>
              <Button
                style={{ fontSize: 30 }}
                color="warning"
                onClick={() => {
                  this.setState({
                    businessLogin: true,
                    customerLogin: false,
                    customerSignUp: false,
                    businessSignUp: false,
                    customerOption: false,
                    businessOption: true,
                  });
                }}
              >
                Business Log-In
              </Button>
            </div>

            <div></div>

            <div class="customer-log-in" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.customerLogin ? <CustomerLoginForm /> : null}
                </Route>
              </Switch>
            </div>

            <div class="customer-sign-up" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.customerSignUp ? <CustomerRegisterForm /> : null}
                </Route>
              </Switch>
            </div>

            <div class="business-log-in" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.businessLogin ? <BusinessLoginForm /> : null}
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

            <div class="business-option" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.businessOption ? (
                    <div class="business-sign-up-button" align="center">
                      <div className="registerBusinessMessage">
                        <span>Want to create an account for a business? </span>
                        <button
                          className="loginText"
                          onClick={() => {
                            this.setState({
                              customerLogin: false,
                              businessLogin: false,
                              customerSignUp: false,
                              businessOption: false,
                              businessSignUp: true,
                              customerOption: true,
                            });
                          }}
                        >
                          Sign Up
                        </button>
                      </div>
                    </div>
                  ) : null}
                </Route>
              </Switch>
            </div>

            <div class="customer-option" align="center">
              <Switch>
                <Route path="/" exact="true">
                  {this.state.customerOption ? (
                    <div className="registerMessage">
                      <span>Dont have an account? </span>
                      <button
                        className="loginText"
                        onClick={() => {
                          this.setState({
                            customerLogin: false,
                            businessLogin: false,
                            customerSignUp: true,
                            businessOption: true,
                            businessSignUp: false,
                            customerOption: false,
                          });
                        }}
                      >
                        Sign Up
                      </button>
                    </div>
                  ) : null}
                </Route>
              </Switch>
            </div>
          </div>

          <div className="container mt-3">
            <Switch>
              <Route
                exact
                path="/business-login"
                component={BusinessLoginForm}
              />
              <Route
                exact
                path="/business-signup"
                component={BusinessRegisterForm}
              />
              <Route
                exact
                path="/customer-login"
                component={CustomerLoginForm}
              />
              <Route
                exact
                path="/customer-signup"
                component={CustomerRegisterForm}
              />
              <Route
                exact
                path="/customer-register"
                component={CustomerRegisterForm}
              />
              <Route exact path="/login-test" component={CustomerLoginForm} />
              <Route exact path="/home" component={Home} />
            </Switch>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
