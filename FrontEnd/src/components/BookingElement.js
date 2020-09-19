import React from 'react'
import moment from 'moment'
import momentTimezone from 'moment-timezone'
import Button from './Button'

function BookingElement({
  bookingData,
  onDeleteBooking,
  serviceData
}) {

  const serviceInfo = findserviceInfo(bookingData.serviceId, serviceData)
  const startTime = momentTimezone.tz(bookingData.bookingStart, 'Australia/Sydney').format('h.mma')
  const endTime = momentTimezone.tz(bookingData.bookingEnd, 'Australia/Sydney').format('h.mma')

  return (
    <div className="booking__box">
      <div className="booking__innerbox--left">
        <h3 className="header__heading--sub--alt header__heading--small">{moment(bookingData.bookingStart).format('dddd, MMMM Do YYYY')}</h3>
        <p>{bookingData.businessId}</p>
        <p>{bookingData.userId}</p>
      </div>
      <div className="booking__innerbox--middle">
        <p>From {startTime} to {endTime}</p>
        <p>Duration {bookingData.duration}hrs</p>
        
      </div>
      <div className="booking__innerbox--right">
        <Button
          onClick={() => onDeleteBooking(bookingData.serviceId, bookingData._id)}
          text={`Delete`}
        />
      </div>
    </div>
  )
}

export default BookingElement