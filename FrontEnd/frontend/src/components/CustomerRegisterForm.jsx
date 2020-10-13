import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";

import CustomerSignUpService from "../services/CustomerSignUpService";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const email = (value) => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const validateName = (value) => {
  if (value.length < 1 || value.length > 30) {
    return (
      <div className="alert alert-danger" role="alert">
        Input must be between 1 and 30 characters.
      </div>
    );
  }
};

// const vpassword = (value) => {
//   if (value.length < 6 || value.length > 40) {
//     return (
//       <div className="alert alert-danger" role="alert">
//         The password must be between 6 and 40 characters.
//       </div>
//     );
//   }
// };

export default class RegisterForm extends Component {
  constructor(props) {
    super(props);
    this.handleRegister = this.handleRegister.bind(this);
    // this.onChangeUsername = this.onChangeUsername.bind(this);
    // this.onChangePassword = this.onChangePassword.bind(this);

    this.onChangeFirstName = this.onChangeFirstName.bind(this);
    this.onChangeLastName = this.onChangeLastName.bind(this);
    this.onChangePhoneNum = this.onChangePhoneNum.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangeStreetNum = this.onChangeStreetNum.bind(this);
    this.onChangeStreetName = this.onChangeStreetName.bind(this);
    this.onChangeSuburb = this.onChangeSuburb.bind(this);
    this.onChangePostcode = this.onChangePostcode.bind(this);
    this.onChangeState = this.onChangeState.bind(this);

    this.state = {
      firstName: "",
      lastName: "",
      phoneNum: "",
      email: "",
      streetNum: "",
      streetName: "",
      suburb: "",
      postcode: "",
      state: "",
      successful: false,
      message: "",
    };
  }

  // onChangeUsername(e) {
  //   this.setState({
  //     username: e.target.value,
  //   });
  // }

  onChangeFirstName(e) {
    this.setState({
      firstName: e.target.value,
    });
  }

  onChangeLastName(e) {
    this.setState({
      lastName: e.target.value,
    });
  }

  onChangePhoneNum(e) {
    this.setState({
      phoneNum: e.target.value,
    });
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value,
    });
  }

  onChangeStreetNum(e) {
    this.setState({
      streetNum: e.target.value,
    });
  }

  onChangeStreetName(e) {
    this.setState({
      streetName: e.target.value,
    });
  }

  onChangeSuburb(e) {
    this.setState({
      suburb: e.target.value,
    });
  }

  onChangePostcode(e) {
    this.setState({
      postcode: e.target.value,
    });
  }

  onChangeState(e) {
    this.setState({
      state: e.target.value,
    });
  }

  // onChangePassword(e) {
  //   this.setState({
  //     password: e.target.value,
  //   });
  // }

  handleRegister(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false,
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      CustomerSignUpService.createCustomer(
        this.state.firstName,
        this.state.lastName,
        this.state.phoneNum,
        this.state.email,
        this.state.streetNum,
        this.state.streetName,
        this.state.suburb,
        this.state.postcode,
        this.state.state
        // this.state.username,
        // this.state.email,
        // this.state.password
        // ).then(
        //   (response) => {
        //     this.setState({
        //       message: response.data.message,
        //       successful: true,
        //     });
        //   },
        //   (error) => {
        //     const resMessage =
        //       (error.response &&
        //         error.response.data &&
        //         error.response.data.message) ||
        //       error.message ||
        //       error.toString();

        //     this.setState({
        //       successful: false,
        //       message: resMessage,
        //     });
        //   }
      );
    }
  }

  render() {
    return (
      <div className="col-md-12">
        <div class="page-header">
          <br></br>
          <h1>Customer Sign-Up</h1>
          <br></br>
        </div>
        <div className="card card-container">
          <Form
            onSubmit={this.handleRegister}
            ref={(c) => {
              this.form = c;
            }}
          >
            {!this.state.successful && (
              <div>
                <div className="form-group">
                  <label htmlFor="firstname">First name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="firstname"
                    value={this.state.firstName}
                    onChange={this.onChangeFirstName}
                    validations={[required, validateName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="username">Last name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="lastname"
                    value={this.state.lastName}
                    onChange={this.onChangeLastName}
                    validations={[required, validateName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="phonenumber">Phone number</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="phonenumber"
                    value={this.state.phoneNum}
                    onChange={this.onChangePhoneNum}
                    validations={[required, validateName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="email">Email</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="email"
                    value={this.state.email}
                    onChange={this.onChangeEmail}
                    validations={[required, email]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="streetNum">Street number</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="streetNum"
                    value={this.state.streetNum}
                    onChange={this.onChangeStreetNum}
                    validations={[required, validateName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="streetNum">Street name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="streetName"
                    value={this.state.streetName}
                    onChange={this.onChangeStreetName}
                    validations={[required, validateName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="suburb">Suburb</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="suburb"
                    value={this.state.suburb}
                    onChange={this.onChangeSuburb}
                    validations={[required, validateName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="postcode">Postcode</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="postcode"
                    value={this.state.postcode}
                    onChange={this.onChangePostcode}
                    validations={[required, validateName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="state">State</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="state"
                    value={this.state.state}
                    onChange={this.onChangeState}
                    validations={[required, validateName]}
                  />
                </div>

                {/* <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    validations={[required, vpassword]}
                  />
                </div> */}

                <div className="form-group">
                  <button className="btn btn-primary btn-block">Sign Up</button>
                </div>
              </div>
            )}

            {this.state.message && (
              <div className="form-group">
                <div
                  className={
                    this.state.successful
                      ? "alert alert-success"
                      : "alert alert-danger"
                  }
                  role="alert"
                >
                  {this.state.message}
                </div>
              </div>
            )}
            <CheckButton
              style={{ display: "none" }}
              ref={(c) => {
                this.checkBtn = c;
              }}
            />
          </Form>
        </div>
      </div>
    );
  }
}
