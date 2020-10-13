import React from 'react'

export const serviceSorter = (serviceList, serviceNumber) => {
  
  let copiedList = serviceList.slice(0)
  
  
  let filteredList = copiedList.filter(service => {
    return service.number === serviceNumber
  })
  
  
  const numericalSort = serviceList => { 
    return serviceList.sort((first, second) => {
      const firstservice = first.name.replace(/\D+/, '')
      const secondservice = second.name.replace(/\D+/, '')
      if (parseInt(firstservice) > parseInt(secondservice)) {
        return 1
      } else {
        return 0
      }
    })
  }
  
  
  let nameRoom = numericalSort(
    filteredList.filter(service => service.name[0] === 'R')
  )
  
  
  let nameStudio = numericalSort(
    filteredList.filter(service => service.name[0] === 'S')
  )
  
 
  let nameOther = numericalSort(
    filteredList.filter(service => service.name[0] !== 'S' && service.name[0] !== 'R')
  )
  
 
  return nameService.concat(nameStudio).concat(nameOther)
}
