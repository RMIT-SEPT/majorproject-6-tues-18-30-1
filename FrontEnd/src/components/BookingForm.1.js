import React, {Fragment} from 'react'
import BookingFormTable from './BookingFormTable'
import Datetime from 'react-datetime'
import moment from 'moment'
import {Link} from 'react-router-dom'
import Button from './Button'
import { formatTime, startTimeSelectOptions, endTimeSelectOptions } from '../helpers/bookingForm'

function BookingForm({ onMakeBooking, user, serviceData, date, updateCalendar, onShowBooking, disableRecurring, onToggleRecurring }) {
  // Disable sunday (day 0) on the calendar as an booking option
  const valid = function(current) {
    return current.day() !== 0
  }

  const handleEndDate = (dateArray) => {
    let recurringEndDate = []
    dateArray.forEach(item => {
      recurringEndDate.push(parseInt(item))
    })
    return recurringEndDate
  }

  // Format the recurring data into an array
  const handleRecurringData = (type, date) => {
    let recurringData = []
    if (type !== "none") {
      recurringData = [ date, type]
      recurringData[0][1] = recurringData[0][1] - 1
    } else {
        recurringData = []
    }
    return recurringData
  }

  // Array used for handleData function
  let dateArray = []

  // Update the current date in the application state
  const handleDate = event => {
    updateCalendar(moment(event)._i)
  }

  return (
    <Fragment>
      <div className="header__page">
        <h2 className="header__heading header__heading--sub">Level {serviceData.type} | {serviceData.name}</h2>
      </div>
      <form className="form__grid form--booking" onSubmit={event => {
          event.preventDefault()
          // Extract date array from current date in state
          const dateArray = moment(date)
            .format('Y M D')
            .split(' ')
            .map(item => parseInt(item, 10))
            dateArray[1] = dateArray[1] - 1
            // Data from input
            const formData = event.target.elements
            const roomId = roomData._id
            // startDate data
            const startTime = formatTime(formData.startTime.value)
            const startDate = [...dateArray, ...startTime]
            // endDate data
            const endTime = formatTime(formData.endTime.value)
            const endDate = [...dateArray, ...endTime]
            // Booking specifics
            const businessId = formData.business.value
            let recurringEnd = handleEndDate(formData.recurringEndDate.value.split('-'))
            const recurringType = formData.recurring.value
            let recurringData = handleRecurringData(recurringType, recurringEnd)
            const serviceType = formData.serviceType.value
            const description = formData.description.value
          onMakeBooking({ startDate, endDate, businessId, serviceType, description, recurringData })
        }}>
        <div className="content__calendar">
          <Datetime
            dateFormat="YYYY-MM-DD"
            timeFormat={false}
            input={false}
            utc={true}
            isValidDate={valid}
            onChange={event => handleDate(event._d)}
        />
        </div>
        <div className="content__table">
          <BookingFormTable roomData={roomData} date={date} onShowBooking={onShowBooking} />
        </div>
        <div className="content__form">
          <h3 className="header__heading header__heading--column">Make a Booking</h3>
          <div className="form__group form__group--margin-top">
            <label className="form__label form__label--booking">
              {'Start time'}
              <select name="startTime" className="form__input form__input--select">
                {startTimeSelectOptions.map(option => {
                  return option
                })}
              </select>
            </label>
          </div>
          <div className="form__group">
            <label className="form__label form__label--booking">
              {'End time'}
              <select name="endTime" className="form__input form__input--select">
                {endTimeSelectOptions.map(option => {
                  return option
                })}
              </select>
            </label>
          </div>
          <div className="form__group">
            <label className="form__label form__label--booking">
              {'Business'}
              <select name="business" defaultValue="Business" className="form__input form__input--select">
                <option value="Business 1">Business 1</option>
                <option value="Business 2">Business 2</option>
                <option value="Business 3">Business 3</option>
                <option value="Business 4">Business 4</option>
                <option value="Business 5">Business 5</option>
              </select>
            </label>
          </div>
          <div className="form__group">
            <label className="form__label form__label--booking">
              {'Recurring'}
              <span>
                <select name="recurring" defaultValue="none" onChange={(event) => onToggleRecurring(event.target.value)} className="form__input form__input--select">
                  <option value="none">Non recurring</option>
                  <option value="daily">Daily</option>
                  <option value="weekly">Weekly</option>
                  <option value="monthly">Monthly</option>
                </select>
              </span>
            </label>
          </div>
          <label className="form__label form__label--booking">
            {'Recurring end date'}
            <input type="date" name="recurringEndDate" disabled={disableRecurring} className="form__input--date"/>
          </label>
          <div className="form__group">
            <label className="form__label form__label--booking">
              {'Service Type'}
              <select name="Service Type" defaultValue="Service 1" className="form__input form__input--select">
                <option value="Service 1">Service 1</option>
                <option value="Service 2">Service 2</option>
                <option value="Service 3">Service 3</option>
              </select>
            </label>
          </div>
          <div className="form__group">
            <label className="form__label form__label--booking">
              {'Description'}
              <textarea type="textarea" name="description" className="form__input--textarea"></textarea>
            </label>
          </div>
          <div className="form__group--button">
            <Button className="button button__form--booking" text={'Submit'} />
            <Link to="/bookings" className="button button--alternative button__form--booking" >View availability</Link>
          </div>
        </div>
      </form>
    </Fragment>
  )
}

export default BookingForm
