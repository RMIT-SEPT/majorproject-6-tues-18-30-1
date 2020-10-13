import React, { useState } from "react";
import axios from "axios";
import { withRouter } from "react-router-dom";
import { PostData, Redirect } from "../api/";

class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
      redirect: false,
    };
    this.login = this.login.bind(this);
  }

  login() {
    if (this.state.username && this.state.password) {
      PostData("login", this.state).then((result) => {
        let responseJson = result;

        if (responseJson.userData) {
          this.setState({ redirect: true });
        }

        console.log(responseJson);
      });
    }
  }

  onChange() {
    this.setState({ [e.target.name]: e.target.value });
  }

  render() {
    if (this.state.redirect) {
      return <Redirect to={"/customerhome"} />;
    }
    return (
      <div>
        <div className="card col-12 col-lg-4 login-card mt-2 hv-center">
          <form>
            <h2>Customer login</h2>
            <div className="form-group text-left">
              <label>Email</label>
              <input type="text" name="email" onChange={this.onChange} />
              <label>Password</label>
              <input type="password" name="password" onChange={this.onChange} />
              <input
                type="submit"
                value="Login"
                className="button"
                onClick={this.login}
              />
            </div>
          </form>
        </div>
      </div>
    );
  }
}
