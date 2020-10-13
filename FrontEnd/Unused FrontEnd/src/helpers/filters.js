import moment from 'moment'
import { formatTime, timeSelectOptions} from '../helpers/bookingForm'

// Initial room filter parameters
export const serviceParams = [ {name: '8', value: false}, {name: '13', value: false}, {name: 'all', value: false}]

// initial feature filter parameters
export const serviceParams = [ 
  {name: 'macLab', value: false},
  {name: 'pcLab', value: false},
  {name: 'tv', value: false},
  {name: 'opWalls', value: false},
  {name: 'projector', value: false} ]

// Initial Capacity parameters
export const capacityParams = [
  {capacity: 16, id: '16slots', value: false},
  {capacity: 18, id: '18slots', value: false},
  {capacity: 20, id: '20slots', value: false},
  {capacity: 24, id: '24slots', value: false},
  {capacity: 40, id: '40slots', value: false},
]

// Filtering Functions


export  const onFilterByService = (param, filteredData) => {
  if (param === 'all') {
    return filteredData
  } else {
    return filteredData.filter(service => service.number === param)
  }
}

// Filter data by feature
export const onFilterByFeature = (params, filteredData) => {
  params.forEach(feature => {
    if (feature.name === 'macLab' && feature.value === true) {
      filteredData = filteredData.filter(service => service.assets.macLab === true)
    } else if (feature.name === 'pcLab' && feature.value === true) {
      filteredData = filteredData.filter(service=> service.assets.pcLab === true)
    } else if (feature.name === 'tv' && feature.value === true) {
      filteredData = filteredData.filter(service => service.assets.tv === true)
    } else if (feature.name === 'opWall' && feature.value === true) {
      filteredData = filteredData.filter(service => service.assets.opWalls === true)
    } else if (feature.name === 'projector' && feature.value === true) {
      filteredData = filteredData.filter(service => service.assets.projector === true)
    }
  })
  return filteredData
}

// Filter data by capacity
export const onFilterByCapacity = (params, filteredData) => {
  let servicesByCapacity = []
  params.forEach(capacity => {
    if (capacity.value === true) {
      servicesByCapacity.push(...filteredData.filter(service => service.capacity === capacity.capacity))
    }
  })
  if (servicesByCapacity.length > 0) {
    return servicesByCapacity
  } else {
    return filteredData
  }
}

// Filter data by availability
export const onFilterByAvailablity = (params, filteredData) => {
  if (params === 'fullyAvail') {
    filteredData = filteredData.filter(service=> service.bookings.length === 0)
  } else if (params === 'partAvail') {
    filteredData = filteredData.filter(room => service.bookings.length > 0)
  } else if (params === 'fullBooked') {
    filteredData =
      !filteredData.filter(service => service.bookings.length > 0) &&
      !filteredData.filter(service => service.bookings.length === 0)
  }
  return filteredData
}
