import axios from 'axios'

const API_URL = 'http://localhost:8080/customer'

class RegisterService {
  saveNewCustomers () {
    return axios.get(API_URL + '/save')
  }
}

export default new RegisterService()
