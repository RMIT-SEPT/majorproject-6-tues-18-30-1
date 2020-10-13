import React from 'react';
import logo from './logo.svg';
import './App.css';
import React, { Component, Fragment } from 'react'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect
} from 'react-router-dom'
import './css/style.css'
import moment from 'moment'

import BookingForm from './components/BookingForm'
import Button from './components/Button'


import Key from './components/Key'
import MyBookings from './components/MyBookings'
import NavBar from './components/NavBar'


import {
  signIn,
  signOut,
  signUp
} from './api/auth'

import { getDecodedToken } from './api/token'
import { makeBooking, deleteBooking, updateBooking } from './api/booking'
import Calendar from './components/Calendar'
import BookingModal from './components/BookingModal'
import { floorParams, filterParams, capacityParams, onFilterByService, onFilterByFeature, onFilterByCapacity, onFilterByAvailablity } from './helpers/filters'
import { initialRoom } from './helpers/services'

class App extends Component {
  state = {
    decodedToken: getDecodedToken(), // retrieves the token from local storage if valid, else will be null
    roomData: null,
    userBookings: null,
    calendarDate: new Date(),
    selectedBooking: null,
    filterParams: filterParams,
    capacityParams: capacityParams,
    serviceParam: 'all',
    availabilityParam: null,
    filteredData: null,
    checked: null,
    error: null,
    disableRecurring: true
  }

  
  onSignUp = ({ firstName, lastName, email, password }) => {
    signUp({ firstName, lastName, email, password }).then(decodedToken => {
      this.setState({ decodedToken })
    })
  }

  
  onSignIn = ({ email, password }) => {
    signIn({ email, password }).then(decodedToken => {
      this.setState({ decodedToken })
    })
  }

  
  onSignOut = () => {
    signOut()
    this.setState({ decodedToken: null })
  }

  setCalendarDate = date => {
    this.setState({ calendarDate: date })
  }

  onShowBooking = booking => {
    const selectedBooking = booking
    console.log('selectedBooking', selectedBooking)
    this.setState(() => ({ selectedBooking }))
  }

  onCloseBooking = () => {
    this.setState(() => ({ selectedBooking: null }))
  }

  
  onMakeBooking = ({ startDate, endDate, businessId, serviceType, userId, recurringData }) => {
    const bookingData = { startDate, endDate, businessId, serviceType, userId }
    const existingBookings = this.state.currentRoom.bookings

    
    try {
      makeBooking(
        { startDate, endDate, businessId, serviceType, userId, recurringData },
        existingBookings
      )
        .then(updatedBooking => {
          // If the new booking is successfully saved to the database
          alert(`${updatedBooking.name} successfully booked.`)
          updateBooking(this, updatedBooking, this.loadMyBookings)
        })
    } catch (err) {
      // If there is a booking clash and the booking could not be saved
      alert(
        'Your booking could not be saved. Please ensure it does not clash with an existing booking and that it is a valid time in the future.'
      )
      console.log(err)
    }
  }

  



  // setting the feature filter parameters
  onToggleFeature = feature => {
    // Get the filter parameters
    let filterParams = this.state.filterParams
    // Find the filter parameter that matches the the passed parameter
    let featureParam = filterParams.find(param => param.name === feature)
    // Toggle the value of the parameter, eg if false, set to true
    featureParam.value = !featureParam.value
    // Set state with the updated filter parameters
    this.setState({ filterParams: filterParams })
  }

  // setting the capacity filter parameters
  onToggleCapacity = capacity => {
    // Get the capacity parameters
    let capacityParams = this.state.capacityParams
    // Find the capacity parameter that matches the the passed parameter
    let capacityParam = capacityParams.find(param => param.id === capacity)
    // Toggle the value of the parameter, eg if false, set to true
    capacityParam.value = !capacityParam.value
    // Set state with the updated capacity parameters
    this.setState({ capacityParams: capacityParams })
  }

  // changing the boolean value for the display attribute for the recurring date input
  onToggleRecurring = (value) => {
    let disableRecurring
    if (value === 'none') {
      disableRecurring = true
    } else {
      disableRecurring = false
    }
    this.setState({disableRecurring: disableRecurring})
  }

  onSetServiceParam = value => {
		this.setState({ serviceParam: value })
  }

  onSetAvailabilityParam = availability => {
    this.setState({ availabilityParam: availability })
  }

  // get today's bookings for all 
  oneSetCurrentDateBookings = () => {
    const currentDate = moment().format('DD-MM-YYYY')
    
    const bookingData = this.state.bookingData
    
    let todaysBookings = []
    
    bookingData.forEach(service => {
     
      service.bookings.forEach(booking => {
        const bookingStart = moment(booking.bookingStart).format('DD-MM-YYYY')
        if (bookingStart === currentDate) {
          todaysBookings.push(booking)
        }
      })
    })
    console.log('todays bookings:', todaysBookings)
    // return todaysBookings
  }

  loadMyBookings = () => {
    let myBookings = []
    const userId = this.state.decodedToken

    this.state.serviceData.forEach(service => {
      
      service.bookings.forEach(booking => {
        if (booking.user === userId) {
          
          booking.UserId = service._id
          myBookings.push(booking)
        }
      })
    })
    this.setState({ userBookings: myBookings })
  }

  render() {
    

    

    
    return (
      <Router>
        <div id="app" className="App">
          <Fragment>
              <Switch>
                <Route path="/" exact render={() => (!!decodedToken && signedIn ?
                  (<Redirect to="/bookings" />) :
                  (<div className="wrapper__form">
                      <div className="header__page">
                        <h2 className="header__heading header__heading--sub--alt">Sign in with email</h2>
                      </div>
                      <SignInForm onSignIn={this.onSignIn} />
                      <h3 className="header__heading header__heading--sub--alt">Don't have an account?</h3>
                      <SignUpForm onSignUp={this.onSignUp} />
                    </div>
                  )
                )} />

              
                ))} />

                <Route path="/createbooking" exact render={requireAuth(
                  () => (
                    <Fragment>
                      {!!decodedToken &&
                        !!bookinhData &&
                        !!currentBooking && (
                          <div className="wrapper">
                            <header className="header header__nav header--flex">
                              <h1 className="header__heading header__heading--main"> Name Here</h1>
                              <NavBar
                                signOut={signOut}
                                loadMyBookings={loadMyBookings}
                                user={signedIn ? decodedToken.sub : null}
                              />
                            </header>
                            <div className="wrapper__content">
                              <BookingForm
                                user={decodedToken.email}
                                bookingdata={currentBooking}
                                onMakeBooking={this.onMakeBooking}
                                date={calendarDate}
                                disableRecurring={disableRecurring}
                                updateCalendar={setCalendarDate}
                                onShowBooking={this.onShowBooking}
                                onToggleRecurring={this.onToggleRecurring}
                              />
                            </div>
                              <BookingModal
                                selectedBooking={selectedBooking}
                                onCloseBooking={this.onCloseBooking}
                                onDeleteBooking={onDeleteBooking}
                                serviceData={serviceData}
                                user={decodedToken.email}
                              />
                        </div>
                      )}
                    </Fragment>
                  )
                )} />

                <Route path="/mybookings" exact render={requireAuth(() => (
                    <Fragment>
                      {!!decodedToken &&
                        !!bookingData && (
                          <div className="wrapper">
                            <div className="header header__nav header--flex">
                              <h1 className="header__heading header__heading--main"> Name Here</h1>
                              <NavBar
                                signOut={signOut}
                                loadMyBookings={loadMyBookings}
                                user={signedIn ? decodedToken.sub : null}
                              />
                            </div>
                            <div className="wrapper__content--bookings">
                              <div className="header__page">
                                <h2 className="header__heading header__heading--sub">My Bookings</h2>
                              </div>
                              <MyBookings
                                bookingData={bookingData}
                                user={decodedToken.email}
                                userBookings={userBookings}
                                onDeleteBooking={onDeleteBooking}
                              />
                            </div>
                          </div>
                        )}
                    </Fragment>
                  ))} />

                <Route render={({ location }) => <h2>
                      {' '}
                      Page Not Found: {location.pathname}{' '}
                    </h2>} />
            </Switch>
          </Fragment>
        </div>
      </Router>
    )
  }

  

  // When the App first renders
  componentDidMount() {
    this.load()
  }

  // When state changes
  componentDidUpdate(prevProps, prevState) {
    // If just signed in, signed up, or signed out,
    // then the token will have changed
    if (this.state.decodedToken !== prevState.decodedToken) {
      this.load()
    }
  }
}

export default App
