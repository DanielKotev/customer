import axios from 'axios'

const API_URL = 'http://localhost:8080/customer'

class CustomerService {
  getAllCustomers () {
    return axios.get(API_URL + '/all')
  }
}

export default new CustomerService()
