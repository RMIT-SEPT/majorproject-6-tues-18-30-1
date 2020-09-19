import React from 'react'
import moment from 'moment'
import api from './init'

export function listServices() {
  return api.get('/services').then(res => res.data)
}
