import React from 'react'
import BookingElement from './BookingElement'

function MyBookings({
  user,
  userBookings,
  onDeleteBooking,
  bookingData
}) {

  return (
    <div className="wrapper__bookings">
      <div className="booking__user-info">
        <div className="avatar"><img src={Avatar}/></div>
        <h2>{user}</h2>
      </div>
      <div className="user-booking-container">
        { !!userBookings ?
          (
            Object.keys(userBookings).map(key =>
              <BookingElement
                key={key}
                bookingData={bookingData}
                bookingData={userBookings[key]}
                onDeleteBooking={onDeleteBooking}
              />)
           ) : (<p>You have not yet made any bookings</p>)
        }
      </div>
    </div>
  )
}

export default MyBookings